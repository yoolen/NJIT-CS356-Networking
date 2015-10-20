import java.lang.*;
import java.io.*;
import java.net.*;

public class Router1 {
	public static void main(String args[]) {
		int routerID = 1;
		int connectID;
		int[] costTableLocal = {1, 0, 1, -1};
		int[] costTableRemote = null;
		ServerSocket srvr = null;
		Socket socket = null;
		
		try {
			srvr = new ServerSocket(12345);
			System.out.println("Waiting on client...");
			socket = srvr.accept();
			System.out.print("Server has connected!\n");

			BufferedReader buffRead = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			//received routing table from router0
			connectID = Integer.parseInt(buffRead.readLine());
			costTableRemote = new int[4];
			for(int i = 0; i < 4; i++){
				String in = buffRead.readLine();
				costTableRemote[i] = Integer.parseInt(in);
			}
			
			// Send table to router0
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			//send routing table over to server
			bw.write(String.valueOf(routerID) + "\n");
			for(int i: costTableLocal){
				bw.write(String.valueOf(i) + "\n");	
			}
			bw.flush();
			
		} catch(Exception e) {
			System.out.print("Whoops! Server didn't work!\n");
			System.out.print(e.getMessage());
		} finally {
			try {
				socket.close();			
				srvr.close();
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
