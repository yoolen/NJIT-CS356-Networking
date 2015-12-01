import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

//import java.io.*; 
//import java.net.*; 

public class TestSocket {
	public static void main(String[] args){
		Socket socket = null;
		System.out.println(TCPRouter.hostnames[1] + ":" + TCPRouter.ports[1]);
		try {
			socket = new Socket(TCPRouter.hostnames[1], TCPRouter.ports[1]);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
