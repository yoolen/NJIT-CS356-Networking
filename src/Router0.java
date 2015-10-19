import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.lang.*;
import java.io.*;
import java.net.*;

public class Router0 {
//		   public static void main(String args[]) {
//		      try {
//		         Socket skt = new Socket("localhost", 1234);
//		         BufferedReader in = new BufferedReader(new
//		            InputStreamReader(skt.getInputStream()));
//		         System.out.print("Received string: '");
//
//		         while (!in.ready()) {}
//		         System.out.println(in.readLine()); // Read one line and output it
//
//		         System.out.print("'\n");
//		         in.close();
//		      }
//		      catch(Exception e) {
//		         System.out.print("Whoops! It didn't work!\n");
//		      }
//		   }
		
	
	

		// This will act as the client
		public static void main(String argv[]) throws Exception{
			
		// Begin by preparing table of initial costs
			
			int[] costTable = {0, 1, 3, 7};
			
			TCPRouter router0 = new TCPRouter(costTable, 0, false);
			router0.connect(TCPRouter.hostnames[1], TCPRouter.ports[1]);
			router0.send();
			router0.listen();
			router0.disconnect();
			
		// Open a connection to each router on the network and send own router number and initial least known cost
			
			
			
//		// Wait for reply from server
//			System.out.println(router0);
//			
//			String sentence; 
//	        String modifiedSentence; 
//
//	        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in)); 
//	        Socket clientSocket = new Socket("hostname", 6789); 
//
//	        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream()); 
//	        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); 
//	        
//	        sentence = inFromUser.readLine(); 
//	        outToServer.writeBytes(sentence + '\n'); 
//	        modifiedSentence = inFromServer.readLine(); 
//
//	        System.out.println("FROM SERVER: " + modifiedSentence); 
//	        clientSocket.close(); 
		} 
} 
