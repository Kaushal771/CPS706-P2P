import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class UDPClient {

    public static void main(String[] args) throws Exception {
        String screenName = args[0];
        String host       = args[1];
        int port          = 4444;

        // connect to server and open up IO streams
        Socket socket = new Socket(host, port);
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        System.err.println("Connected to " + host + " on port " + port);
        String userInput;
        
        while ((userInput = stdIn.readLine()) != null) {

            // read line of client
            String s = in.readLine();

            // send over socket to server
            out.println("[" + screenName + "]: " + s);

            // get reply from server and print it out
            out.println(in.readLine());
        }

        // close IO streams, then socket
        System.err.println("Closing connection to " + host);
        out.close();
        in.close();
        socket.close();
    }
}