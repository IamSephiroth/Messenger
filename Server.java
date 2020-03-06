import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Server {
//	private Set<Socket> clients = new HashSet<Socket>();
//	private Map<String, Socket> clients = new HashMap<String, Socket>();
	
    /**
	 * Setup a ServerSocket in the Server class.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		ObjectOutputStream toClient;
	    ObjectInputStream fromClient;
		Socket client = null;
		ArrayList<Socket> clients = new ArrayList<Socket>();
			ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(50000);
			System.out.println("Waiting for a client.");
			//acceptClients();
		} catch (IOException e) {
			System.err.println("could not listen on port: " + 50000);
		}
		try {
			while (true) {
				Socket clientSocket = serverSocket.accept();
				clients.add(clientSocket); //Add client to the list of clients online.
				System.out.println("Client accepted");
				ServerThread s = new ServerThread(clientSocket);
				Thread a = new Thread(s);
				a.start();
			}
		} catch (Exception e) {
				try {
					serverSocket.close();
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