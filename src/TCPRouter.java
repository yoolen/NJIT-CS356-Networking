public class TCPRouter{
	public static String[] hostnames = {"http://192.168.0.143", "http://192.168.0.121", null, null}; // Router0 will be my laptop (client), Router1 will be my desktop (server), Router2 will be something on AFS (not yet implemented, Router3 will be something on OSL (not yet implemented)
	public static int[] ports = {11111, 12345, -1, -1};
}
