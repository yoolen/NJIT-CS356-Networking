import java.net.Socket;
import java.lang.*;
import java.io.*;
import java.net.*;

public class Router0 {
	public static void main(String args[]) {
		int[] costTableLocal = {0, 1, 4, 7};
		int[] costTableRemote = null;
		Socket skt = null;
		
		try {
			skt = new Socket("localhost", 12345);

			String data = "To be or not to be?";
			// First send table to router1
			PrintWriter out = new PrintWriter(skt.getOutputStream(), true);
			System.out.print("Client sending string: '" + data + "'\n");
			out.println(data);
			
			
			System.out.println("Client string sent!");
			System.out.println("Waiting on server...");

			
			BufferedReader in = new BufferedReader(new InputStreamReader(skt.getInputStream()));
			System.out.print("Client received string: '");
			while (!in.ready()) {}
			System.out.println(in.readLine()); // Read one line and output it
			System.out.print("'\n");
			in.close();
			out.close();
		} catch(Exception e) {
			System.out.print("Whoops! Client didn't work!\n");
		} finally {
			try{
				skt.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
} 
