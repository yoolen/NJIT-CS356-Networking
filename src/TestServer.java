import java.lang.*;
import java.io.*;
import java.net.*;

public class TestServer {
	public static void main(String[] args){
				Socket socket = null;
				ServerSocket serverSocket = null;
				ServerSocket srvr = null;
				try{
					//create connection
					int port = TCPRouter.ports[1];
					srvr = new ServerSocket(port);
					socket = srvr.accept();
				} catch(Exception e) {
					
				}
	}
}
