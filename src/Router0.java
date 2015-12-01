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
		boolean updated = true;
		BufferedWriter bw;
		BufferedReader br;
		Socket[] sockets = new Socket[4];

		try {
			// First print local table
			System.out.println("Initial status:");
			printTable(thisRouterID, costTableLocal);
			for(int i = 1; i < sockets.length; i++){
				sockets[i] = new Socket(TCPRouter.hostnames[i],TCPRouter.ports[i]);
			}
			while(updated){
				updated = false;
				// Send data to external routers
				for(int i = 1; i < TCPRouter.hostnames.length; i++){
					// Create a connection to the external routers
					socket = sockets[i];
					// Send table to external routers
					bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
					//send routing table over to server
					bw.write(String.valueOf(thisRouterID) + "\n");
					for(int cost: costTableLocal){
						bw.write(String.valueOf(cost) + "\n");	
					}
					bw.flush();
					
					System.out.println("\nClient data sent!");
					System.out.println("Waiting on server...");

					// Read	from router1
					br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					//received routing table from other router
					thatRouterID = Integer.parseInt(br.readLine());
					costTableRemote = new int[4];
					for(int j = 0; j < 4; j++){
						String in = br.readLine();
						costTableRemote[j] = Integer.parseInt(in);
					}

					System.out.println("Server data received!\n");
					//printTable(thatRouterID, costTableRemote);

					// Update original table with new values
					System.out.println("Calculating new distances...\n");
					for(int k = 0; k < costTableRemote.length; k++){
						// check values in other table (-1 means infinity so don't do anything)
						if(costTableRemote[k] == -1){
							continue;
						} else if(costTableLocal[k] + costTableRemote[k] < costTableLocal[k]){
							costTableLocal[k] = costTableLocal[k] + costTableRemote[k];
							updated = true;
						}
					}
					
					// Print out updated table
					System.out.println("New distances:");
					printTable(thisRouterID, costTableLocal);
					
					bw.close();		
				}
			}
		} catch(Exception e) {
			System.out.print("Whoops! Client didn't work!\n");
		} finally {
			try{
				for(int i = 1; i < sockets.length; i++){
					sockets[i].close();
				}
				
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
