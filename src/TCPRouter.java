import java.io.*; 
import java.net.*; 


public class TCPRouter{
	public static String[] hostnames = {"192.168.1.143", "192.168.1.121", null, null}; // Router0 will be my laptop (client), Router1 will be my desktop (server), Router2 will be something on AFS (not yet implemented, Router3 will be something on OSL (not yet implemented)
	public static int[] ports = {54321, 12345, -1, -1};
	
	protected Socket socket = null;
	private int[] costTable;
	private int[] returnedTable;
	private int routerID;
	private int connectedID;
	private boolean up2Date;
	
	public TCPRouter(int[] costTable, int routerID, boolean up2Date){
		this.costTable = costTable;
		this.routerID = routerID;
		this.up2Date = up2Date;
	}
	
	public int getRouterID() {
		return routerID;
	}
	
	public void connect(String hostname, int port){
		try{
			this.socket = new Socket(hostname, port);
		} catch (IOException ioe){
			System.out.printf("There was an error connecting to the router at %s: %s", hostname, ioe.getMessage());
		}
	}
	
	public void disconnect(){
		try {
			this.socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void send(){
		try {
			OutputStream os = this.socket.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os);
			BufferedWriter bw = new BufferedWriter(osw);
			bw.write(String.valueOf(this.routerID) + '\n');
			for(int cost: this.costTable){
				bw.write(String.valueOf(cost) + '\n');
			}
			bw.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void listen(){
		try {
			InputStream is = this.socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			
			connectedID = Integer.parseInt(br.readLine());
			returnedTable = new int[4];
			for(int i = 0; i < 4; i++){
				returnedTable[i] = Integer.parseInt(br.readLine());
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public String toString(){
		String table = String.format("Cost Table for Router%s\n",String.valueOf(this.routerID));
		for(int i = 0; i < 4; i++){
			table += String.format("Distance to Router%d: %s\n", i, this.routerID==i?"local":String.valueOf(this.costTable[i]));
		}
		return table;
	}
}