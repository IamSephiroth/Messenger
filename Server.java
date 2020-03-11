import java.io.*;
import java.net.*;
import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.Map;
//import java.util.Set;
//import java.util.TreeSet;
import java.util.Vector;

public class Server {
	
//	private Set<Socket> clients = new HashSet<Socket>();
//	private Map<String, Socket> clients = new HashMap<String, Socket>();
	
	static Vector<ServerThread> Users = new Vector<>(); // vector to store clients that are connected to the server
	
	static int counter = 0; // counter for clients
	static Account user;
	
	static ArrayList<String> accountName = new ArrayList<String>();
	static ArrayList<ObjectOutputStream> chatLog = new ArrayList<ObjectOutputStream>();
	
    /**
	 * Setup a ServerSocket in the Server class.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		ServerSocket ss = null;
		try {
			ss = new ServerSocket(50000);
			System.out.println("Server is listening on port 50000");
			System.out.println("Waiting for a client.");
		} catch (IOException e) {
			System.err.println("could not listen on port: " + 50000);
		}

		try {
			while (true) {
				Socket clientSocket = ss.accept();
				System.out.println("Client accepted");
							
				ServerThread chatThread = new ServerThread(clientSocket, user); // new handler created for each client
				Thread clients = new Thread(chatThread);
				Users.add(chatThread);
				System.out.println("Client added to active users list");
				clients.start();
				counter++;
				
			}
		} catch (Exception e) {
				try {
					ss.close();
				} catch (IOException io) {
					System.out.println("Could not close serverSocket" + io.getMessage());
				}
			}
		}
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
