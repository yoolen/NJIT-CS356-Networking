import java.net.Socket;
import java.lang.*;
import java.io.*;
import java.net.*;

public class Router0 {
	public static void main(String args[]) {
		int thisRouterID = 0;
		int thatRouterID;
		int[] costTableLocal = {0, 1, 3, 7};
		int[] costTableRemote = null;
		Socket socket = null;

		try {
			// First print local table
			System.out.println("Initial status:");
			printTable(thisRouterID, costTableLocal);

			// Create connection to server
			socket = new Socket("192.168.0.121", 12345);

			
			
			// Send table to router1
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			//send routing table over to server
			bw.write(String.valueOf(thisRouterID) + "\n");
			for(int i: costTableLocal){
				bw.write(String.valueOf(i) + "\n");	
			}
			bw.flush();

			System.out.println("\nClient data sent!");
			System.out.println("Waiting on server...");

			// Read	from router1
			BufferedReader buffRead = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			//received routing table from other router
			thatRouterID = Integer.parseInt(buffRead.readLine());
			costTableRemote = new int[4];
			for(int i = 0; i < 4; i++){
				String in = buffRead.readLine();
				costTableRemote[i] = Integer.parseInt(in);
			}

			System.out.println("Server data received!\n");
			//printTable(thatRouterID, costTableRemote);

			// Update original table with new values
			System.out.println("Calculating new distances...\n");
			for(int i = 0; i < costTableRemote.length; i++){
				// check values in other table (-1 means infinity so don't do anything)
				if(costTableRemote[i] == -1){
					continue;
				} else if(costTableLocal[thatRouterID] + costTableRemote[i] < costTableLocal[i]){
					costTableLocal[i] = costTableLocal[thatRouterID] + costTableRemote[i];
				}
			}
			
			// Print out updated table
			System.out.println("New distances:");
			printTable(thisRouterID, costTableLocal);
			
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
