import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * This class contains the main method.
 * Accept connection from client and pass the connection to a new instance of
 * ClientManager.
 * 
 *
 */
public class Server {
	//	private Set<Socket> clients = new HashSet<Socket>();
	//	private Map<String, Socket> clients = new HashMap<String, Socket>();

	/**
	 * Setup a ServerSocket in the Server class.
	 * 
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		ObjectOutputStream toClient;
		ObjectInputStream fromClient;
		Socket client = null;
		// List of currently online clients.
		// ArrayList<Socket> clients = new ArrayList<Socket>();
		// ClientManager know about client sockets as well.
		ArrayList<ClientManager> clientManagers = new ArrayList<ClientManager>();

		ServerSocket serverSocket = null;
		try {
			/*
			 *  Instantiate ServerSocket object.
			 */
			serverSocket = new ServerSocket(50000);
			System.out.println("Waiting for a client.");

			//acceptClients();
		} catch (IOException e) {
			System.err.println("could not listen on port: " + 50000);
		}
		try {
			while (true) {
				/*
				 *  Accept connection from a client.
				 *  ClientSocket is a unique socket of the accepted client.
				 */
				Socket clientSocket = serverSocket.accept();
				/*
				 *  Add client to the list of clients currently online.
				 */
				// clients.add(clientSocket);
				System.out.println("Client accepted");
				/*
				 *  Construct a object of ClientManager class with the newly
				 *  accepted client.
				 */
				ClientManager s = new ClientManager(clientSocket);
				clientManagers.add(s);
				/*
				 *  Construct a thread with the runnable object.
				 */
				Thread a = new Thread(s);
				/*
				 *  Execute the run method in ClientManager class.
				 */
				a.start();
				/*
				 * Handle sender and receiver.
				 * Listen to message form server and check which account the thread handles.
				 * Send the message to all other objects of ClientManager.
				 */

			}
		} catch (Exception e) {
			try {
				/*
				 *  Close the connection from server end.
				 */
				serverSocket.close();
			} catch (IOException io) {
				System.out.println("Could not close serverSocket" + io.getMessage());
			}
		}
	}

	/**
	 * Miyo added
	 */
	//	public static Server instance;
	//	private ArrayList<ChatRoom> activeRooms;
	//	private ArrayList<Client> activeUsers;
	//	/**
	//	 * Returns Server instance, generate one if it does not exist.
	//	 * 
	//	 * @return
	//	 */
	//	public static Server getInstance() {
	//		if (instance == null) {
	//			instance = new Server();
	//		}
	//		return instance;
	//	}
	//	
	//	public Server() {
	//		activeRooms = new ArrayList<ChatRoom>();
	//		activeUsers = new ArrayList<Client>();
	//	}
}

/**
 * Wait for a client to connect. 
 */
//	public static void acceptClients() {
//		clients = new ArrayList<Client>();
//		while (true) {
//			try {
//				Socket socket = serverSocket.accept();
//				ClientThread client = new ClientThread(socket);
//				Thread thread = new Thread(client);
//				thread.start();
//				clients.add(client);
//			} catch (IOException e) {
//				System.out.println("Accept failed on: " + portNumber);
//			}
//		}
//	}
/**
 * This closeConnection method is like a finalise method, it closes all of the 
 * streams and ends the connection with a client.
 */
//    public static void closeConnection() {
//    	// Close the streams
//        try {
//        	toClient.close();
//		} catch (IOException e) {
//			System.err.println("Could not close stream.");
//		}
//        
//        try {
//        	fromClient.close();
//		} catch (IOException e) {
//			System.err.println("Could not close stream.");
//		}
//        
//        try {
//			fromClient.close();
//		} catch (Exception e) {
//			System.err.println("Could not close stream.");
//		}
//        
//        System.out.println("Client has left the server.");
//    }