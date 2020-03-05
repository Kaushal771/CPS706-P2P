import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class UDPClient {

	String screenName;
	final int hostPort;
	final int clientPort = 4444;
	
	public UDPClient(String name, int hostPort) {
		screenName = name;
		this.hostPort = hostPort; 
	}
	
    public void send() {
    	new Thread() {
    		@Override
            public void run() {
    			try {
			        
			        // connect to server and open up IO streams
			        Socket socket = new Socket("localhost", hostPort);
			        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
			        System.err.println("Connected to " + hostPort + " on port " + clientPort);
			        String userInput;
			        
			       while ((userInput = stdIn.readLine()) != null) {
			
			            // read line of client
			            String s = stdIn.readLine();
			
			            // send over socket to server
			            out.println("[" + screenName + "]: " + s);
			
			            // get reply from server and print it out
			            out.println(in.readLine());
			       }
			
			        // close IO streams, then socket
			        System.err.println("Closing connection to " + hostPort);
			        out.close();
			        in.close();
			        socket.close();
    
    			} catch(Exception e1 ) {
    				e1.printStackTrace();
    			}
    		}
    	}.start();
	}	
}