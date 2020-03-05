
public class runTogether {

	public static void main(String[] args) throws Exception {
		int serverPort = 8013;
		String screenName = "127.0.0.1";
		UDPServer server = new UDPServer(serverPort);
		UDPClient client = new UDPClient(screenName, serverPort);
		
		server.start();
		client.send();
	}
}
