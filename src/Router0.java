import java.net.Socket;
import java.lang.*;
import java.io.*;
import java.net.*;

public class Router0 {
	public static void main(String args[]) {
		int routerID = 0;
		int connectID;
		int[] costTableLocal = {0, 1, 4, 7};
		int[] costTableRemote = null;
		Socket socket = null;

		try {
			// First print local table
			System.out.println("Initial status:");
			printTable(routerID, costTableLocal);

			// Create connection to server
			socket = new Socket("192.168.0.121", 12345);

			
			
			// Send table to router1
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			//send routing table over to server
			bw.write(String.valueOf(routerID) + "\n");
			for(int i: costTableLocal){
				bw.write(String.valueOf(i) + "\n");	
			}
			bw.flush();

			System.out.println("\nClient data sent!");
			System.out.println("Waiting on server...");

			// Read	from router1
			BufferedReader buffRead = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			//received routing table from other router
			connectID = Integer.parseInt(buffRead.readLine());
			costTableRemote = new int[4];
			for(int i = 0; i < 4; i++){
				String in = buffRead.readLine();
				costTableRemote[i] = Integer.parseInt(in);
			}

			System.out.println("Server data received!\n");
			printTable(connectID, costTableRemote);

			bw.close();
		} catch(Exception e) {
			System.out.print("Whoops! Client didn't work!\n");
		} finally {
			try{
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public static void printTable(int ID, int[] table){
		String output = String.format("Cost Table for Router%s\n",String.valueOf(ID));
		output += "Destination  Interface  Link Cost\n";
		for(int i = 0; i < 4; i++){
			output += String.format("%11d%11s%11s\n", i, table[i]==-1?"n/a":ID==i?"local":String.valueOf((3-ID+i)%4), String.valueOf(table[i]));
		}
		System.out.print(output);
	}

} 
