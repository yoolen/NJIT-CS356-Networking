import java.io.*; 
import java.net.*; 

class TCPServer extends TCPRouter{ 
	public TCPServer(int[] costTable, int routerID, boolean up2Date) {
		super(costTable, routerID, up2Date);
		// TODO Auto-generated constructor stub
	}

	public static void main(String argv[]) throws Exception{ 
		String clientSentence; 
		String capitalizedSentence; 
		ServerSocket welcomeSocket = new ServerSocket(6789); 
  
		while(true){ 
			Socket connectionSocket = welcomeSocket.accept(); 
			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream())); 
			DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream()); 

			clientSentence = inFromClient.readLine(); 
			capitalizedSentence = clientSentence.toUpperCase() + '\n'; 

			outToClient.writeBytes(capitalizedSentence); 
		} 
	} 
} 

