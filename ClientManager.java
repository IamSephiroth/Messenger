import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement; 

/**
 * 
 * ServerSocket class to handle each clients. Each client has one thread.
 * It is not a Thread, it is Runnable. this is what thread runs. 
 * TODO register new user
 * TODO login existing user
 * TODO send message to the right client
 * TODO Client get message only after sending one -> fix it.
 * 
 * @author Miyo Takahashi
 * @version 2020-03-12
 * 
 * @param <E>
 */
public class ClientManager implements Runnable {
	private Socket client = null;
	private ObjectOutputStream toClient;
	private ObjectInputStream fromClient;
	private Object input;
	// key is username, value is clientManager
	private static HashMap<String, ClientManager> clientMap = new HashMap<String, ClientManager>();
	/**
	 * Constructor of ClientManager class.
	 * comm with given client
	 * 
	 * @param client Incoming socket from a particular client.
	 */
	public ClientManager(Socket client) {
		this.client = client;
	}
	/**
	 * Given a username, return the associated ClientManager.
	 * 
	 * @return Object of ClientManager.
	 */
	public static ClientManager getClientManager(String userName) {
		return clientMap.get(userName);
	}
	public static void addClient(String userName, ClientManager cm) {
		clientMap.put(userName, cm);
	}
	/**
	 * To be executed when a client is connected.
	 * 
	 */
	public void run() {
//		ObjectOutputStream toClient;
//		ObjectInputStream fromClient;
//		Object input;
		try {
			// what to send to/received from a client
			toClient = new ObjectOutputStream(client.getOutputStream());
			fromClient = new ObjectInputStream(client.getInputStream());
			/*
			 * show message that we got input, while we have input form the client
			 * if the input is of type Account, store as account info,
			 * if input is String, store as message. Stop here until receiving an input.
			 */
			input = fromClient.readObject();
			// TODO 
			while (input != null) {
				System.out.println("Got " + input + " from client");
				/*
				 * If an Account object is sent to the server, it gets stored
				 * in the appropriate table of database.
				 * Otherwise, the message is sent to the other people in the
				 * chat and stored in the database.
				 */
				if (input instanceof Account) {
					/*
					 * This message is of type Account.
					 * split message and store in database
					 */ 
					storeAccount((Account) input);
				} else {
					/*
					 * Send message's message to people in chat and store in DB.
					 */
					ClientManager target;
					// TODO give target the appropriate value
					// do not handle target in Server class, do it here
					String userName = "1";
					target = getClientManager(userName);
					target.sendMessage((String) input);
					System.out.println("sending message...");
				}
				input = fromClient.readObject();	
				
			}
			/*
			 * Close resources when the connection is over.
			 */
			toClient.close();
			fromClient.close();
			/*
			 * Close Socket of this client.
			 */
			client.close();
			System.out.println("Client has left the server.");
		} catch (IOException io) {
			System.out.println("Something went wrong. Ending service to client.");
		} catch (ClassNotFoundException c) {
			System.out.println("Something went wrong. Ending service to client.");
		}
	}
//	/**
//	 * Close connection.
//	 */
//	public void close() 
	/**
	 * TODO
	 * 
	 * @param input account info.
	 */
	public void storeAccount(Account input) {
		/*
		 * Split the Account info first name and last name etc,
		 * and store in database.
		 */
		String firstName = input.getFirstName();
		String lastName = input.getLastName();
		String userName = input.getUserName();
		String email = input.getEmail();
		String password = input.getPassword();
		int securityCode = input.getSecurityCode();
		addClient(userName, this);
		/*
		 * Know which account this thread is handling.
		 */		
	}
	/**
	 * Return userName of this object.
	 * 
	 * @return
	 */
	public String getUserName() {
		return null;//this.getUserName();
	}
//	/**
//	 * Check whether the user is logged in, if not, compare the username
//	 * and the password and log in if they match.
//	 * 
//	 * @param input
//	 * @return
//	 */
//	public boolean isLoggedIn(Account input) {
//		if (DB.username == input.getUserName() &&
//				DB.password == input.getPassword()) {
//			return true;
//		}
//	}
//	/**
//	 * Given Account object, register a new user.
//	 * 
//	 * @param input
//	 * @return whether the user is successfully registered.
//	 */
//	public boolean addUser(Account input) {
//		
//	}
//	/**
//	 * Given Account object, remove the user form the database.
//	 * 
//	 * @param input
//	 * @return whether the user is successfully removed.
//	 */
//	public boolean removeUser(Account input) {
//		
//	}
//	
	// TODO log in check
	// TODO group chat
	
	/**
	 * Send message input to the current client.
	 * Call this method on the target(destination) client.
	 * 
	 * @param input message to the target.
	 */
	public void sendMessage(String input) {
		System.out.println("reached sendMessage method in ClientManager class");
		System.out.println(input);
		try {
			toClient.writeObject(input);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
//		PrintWriter out;
//		try {		
//			out = new PrintWriter(input);
//			out.println(input);
//			out.flush();
//		} catch (IOException e) {
//			/*
//			 * Show error message.
//			 */
//			System.out.println("Error in sendMessage");
//			e.printStackTrace();
//		}
	}
//	/**
//	 * 1.load JDBC driver(generate Class object)
//	 */
//	Class.forName();
//	/**
//	 * 2. Establish connection with database.
//	 */
//	DriverManager.getConnection();
//	/**
//	 * 3. Send SQL statement.
//	 */
//	executeQuery();
//	executeUpdate();
//	/**
//	 * Obtain result from the database.
//	 */
//	ResultSet result = new ResultSet();
//	/**
//	 * 5. Terminate connection with the database.
//	 * close
//	 */
//	connection.close();
//	executeQuery.close();
//	executeUpdate.close();
//	result.close();
