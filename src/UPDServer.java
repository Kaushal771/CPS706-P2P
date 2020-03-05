import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;

public class UPDServer {
//TODO: not multithreaded -- only one client at a time
	
    public static void main(String[] args) throws Exception {

    	if (args.length < 1) {
    		return;
    	}
    	
    	int port = Integer.parseInt(args[0]);
    	
        ServerSocket serverSocket = new ServerSocket(port);
        System.err.println("Started server on port " + port);

        while (true) {

            Socket clientSocket = serverSocket.accept();
            System.err.println("Accepted connection from client");

            BufferedReader  in  = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
         
            String s;
            while ((s = in.readLine()) != null) {
                out.println("recieved: " + s);
            }

            System.err.println("Closing connection with client");
            out.close();
            in.close();
            clientSocket.close();
        }
    }
}