import java.lang.*;
import java.io.*;
import java.net.*;

public class Router1 {
	public static void main(String args[]) {
		int[] costTableLocal = {1, 0, 1, -1};
		int[] costTableRemote = null;
		String data = "That is the question!";
		ServerSocket srvr = null;
		Socket skt = null;
		
		try {
			srvr = new ServerSocket(12345);
			System.out.println("Waiting on client...");
			skt = srvr.accept();
			System.out.print("Server has connected!\n");

			BufferedReader in = new BufferedReader(new InputStreamReader(skt.getInputStream()));
			System.out.print("Server received string: '");
			while (!in.ready()) {}
			System.out.print(in.readLine()); // Read one line and output it
			System.out.print("'\n");
			

			PrintWriter out = new PrintWriter(skt.getOutputStream(), true);
			System.out.print("Server sending string: '" + data + "'\n");
			out.print(data);
			out.close();
			in.close();
		} catch(Exception e) {
			System.out.print("Whoops! Server didn't work!\n");
		} finally {
			try {
				skt.close();			
				srvr.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
