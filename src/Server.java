import java.io.*;
import java.net.*;
import java.util.ArrayList;

/**
 * Accept connection from client and pass the connection to a new instance of
 * ClientManager.
 * This class contains the main method.
 */
public class Server {
	//	private Set<Socket> clients = new HashSet<Socket>();
	//	private Map<String, Socket> clients = new HashMap<String, Socket>();

	/**
	 * Setup a ServerSocket in the Server class.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		OutputStream toClient;
		InputStream fromClient;
		Socket clientSocket;
		// TODO Does ServerSocket need to be initialised with null?
		ServerSocket serverSocket = null;
		int port = 50000;
		ArrayList<ClientManager> clientManagers = new ArrayList<ClientManager>();
		/*
		 * Open a socket on the named port.
		 */
		try {
			serverSocket = new ServerSocket(port);
			System.out.println("Waiting for a client.");
		} catch (IOException e) {
			System.out.println("Could not open a socket on port: " + port);
		}
		/*
		 * Keep waiting for connection request from a client.
		 */
		while (true) {
			try {
				clientSocket = serverSocket.accept();
				System.out.println("Client accepted");
				// TODO add timeout
				/*
				 * Add the accepted client to clientManagers.
				 */
				ClientManager cm = new ClientManager(clientSocket);
				clientManagers.add(cm);
				/*
				 * Construct a thread with the runnable object.
				 * TODO Learn Runnable
				 */
				Thread thread = new Thread();
				thread.start();
			} catch (Exception e) {
				System.out.println("Something went wrong in the server.");
			}
		}
	}
}