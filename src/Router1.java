import java.io.*;

public class Router1 {
	// This will act as the server
			public static void main(String argv[]) throws Exception{
				
			// Begin by preparing table of initial costs
				int[] costTable = {1, 0, 1, -1}; // a value of -1 indicates no connection
								
				TCPServer router1 = new TCPServer(costTable, 1, false);
				
				router1.setSsocket(12345);
				
				while(true){
					System.out.println("Listening...");
					router1.setSocket();
					router1.listen();
					router1.send();
				}
						
				
			// Wait for reply from server
//				System.out.println(router1);
//				
//				String sentence; 
//		        String modifiedSentence; 
	//
//		        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in)); 
//		        Socket clientSocket = new Socket("hostname", 6789); 
	//
//		        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream()); 
//		        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); 
//		        
//		        sentence = inFromUser.readLine(); 
//		        outToServer.writeBytes(sentence + '\n'); 
//		        modifiedSentence = inFromServer.readLine(); 
	//
//		        System.out.println("FROM SERVER: " + modifiedSentence); 
//		        clientSocket.close(); 
			} 
}
