import java.io.*; 
import java.net.*; 

class TCPServer extends TCPRouter{ 
	ServerSocket ssocket = null;
	
	public TCPServer(int[] costTable, int routerID, boolean up2Date) {
		super(costTable, routerID, up2Date);
		// TODO Auto-generated constructor stub
	}
	
	public void setSocket(){
		try {
			this.socket = this.ssocket.accept();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public void setSsocket(int port){
		try{
			this.ssocket = new ServerSocket(port);
			ssocket.setSoTimeout(60000);
		} catch (IOException ioe){
			System.out.println(ioe);
		}
	}
	
	public void closeSsocket(){
		try{
			ssocket.close();
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
//
//	public static void main(String argv[]) throws Exception{ 
//		String clientSentence; 
//		String capitalizedSentence; 
//		ServerSocket welcomeSocket = new ServerSocket(6789); 
//  
//		while(true){ 
//			Socket connectionSocket = welcomeSocket.accept(); 
//			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream())); 
//			DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream()); 
//
//			clientSentence = inFromClient.readLine(); 
//			capitalizedSentence = clientSentence.toUpperCase() + '\n'; 
//
//			outToClient.writeBytes(capitalizedSentence); 
//		} 
//	} 
} 

