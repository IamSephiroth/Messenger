import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;
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
 * @version 2020-03-15
 * 
 * @param <E>
 */
public class ClientManager implements Runnable {
	private Socket client = null;
	private ObjectOutputStream toClient;
	private ObjectInputStream fromClient;
	//	private Object input;
	private String input; 
	//	private String userID;
	//	private InetAddress ipAddress;
	//	private int userPort;
	// key is username, value is clientManager
	private static HashMap<String, ClientManager> clientMap = 
			new HashMap<String, ClientManager>();
	// TODO add users to activeUser they they login and remove when they log out.
//	private static ArrayList<ClientManager> activeUsers =
//			new ArrayList<ClientManager>();

	/**
	 * Constructor of ClientManager class.
	 * comm with given client
	 * 
	 * @param client Incoming socket from a particular client.
	 */
	public ClientManager(Socket client) {//, String userID, InetAddress ipAddress, int userPort) {
		this.client = client;
		//		this.userID = userID;
		//		this.ipAddress = ipAddress;
		//		this.userPort = userPort;
		//		
	}

	/**
	 * Given a username, return the associated ClientManager.
	 * 
	 * @return Object of ClientManager.
	 */
	public static ClientManager getClientManager(String userName) {
		return clientMap.get(userName);
	}
	/**
	 * Add a new user name and ClientManager object generated on the user
	 * to clientMap.
	 * 
	 * @param userName unique user name to the user.
	 * @param cm ClientManager object that communicates with the associated user.
	 */
	private void addClient(String userName, ClientManager cm) {
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
			input = (String) fromClient.readObject();
			//			/*
			//			 * show message that we got input, while we have input form the client
			//			 * if the input is of type Account, store as account info,
			//			 * if input is String, store as message. Stop here until receiving an input.
			//			 */
			//input = fromClient.readObject();
			// the client can send object of Account / MessageHandler
			// TODO receive string
			/*
			 * Switch based on the input number.
			 * 0 - login
			 * 1 - sign up
			 * 2 - change password
			 * 3 - reset password
			 * 4 - change security code
			 * 5 - reset security code
			 * 6 - send private chat
			 * 7 - send broadcast message
			 * 8 - log out
			 * 9 - delete account
			 * 10 - search for a chat
			 */
			while (input != null) {
				System.out.println("Got " + input + " from client");
				String param;
				String[] option = input.split("#", 2);
				param = option[1];
				switch (option[0]) {
				case "0":
					login(param);
					break;
				case "1":
					signUp(param);
					break;
				case "2":
					changePassword(param);
					break;
				case "3":
					resetPassword(param);
					break;
				case "4":
					changeSecurityCode(param);
					break;
				case "5":
					resetSecurityCode(param);
					break;
				case "6":
					sendPrivateChat(param);
					break;
				case "7":
					broadcast(param);
					break;
				case "8":
//					searchChat(param);
					break;
				default:
					System.out.println("Invalid command.");
					break;
				}
				input = (String) fromClient.readObject();
			}
		} catch (Exception e) {
			System.out.println("Error handling input from client");
		}
	}


	//				// older version with infinite loop 
	//				System.out.println("Got " + input + " from client");
	//				/*
	//				 * If an Account object is sent to the server, it gets stored
	//				 * in the appropriate table of database.
	//				 * Otherwise, the message is sent to the other people in the
	//				 * chat and stored in the database.
	//				 */
	//				if (input instanceof Account) {
	//					/*
	//					 * This message is of type Account.
	//					 * split message and store in database
	//					 */ 
	//					storeAccount((Account) input);
	//					/*
	//					 * Set target user if they are online.
	//					 * Notify the client if they are offline.
	//					 */
	//					//				} else if (input instanceOf Destination) {
	//					//					// receive destination info object(String, boolean)
	//					//					if(input.isOnline()) {
	//					//						destination = input.getUserName();
	//					//					} else {
	//					//						System.out.println("That user is not online.");
	//					//					}
	//					// if object of MessageHandler is input	
	//				} else {
	//					MessageHandler mh = (MessageHandler) fromClient.readObject();
	//					//					String to = "1";
	//					//					String from = "2";
	//					//					String msg = "test message";
	//					//					MessageHandler mh = new MessageHandler(to, from, msg);
	//					/*
	//					 * Send message's message to people in chat and store in DB.
	//					 */
	//					ClientManager target;
	//					// TODO give target the appropriate value
	//					// do not handle target in Server class, do it here
	//					//String userName = destination;
	//					//					if (userName != null) {
	//					target = getClientManager(mh.getTo());
	//					target.sendMessage(mh.getFrom(), mh.getMsg());
	//					System.out.println("sending message...");
	//				}
	//			}
	//			input = fromClient.readObject();			
	//			//			}
	//			/*
	//			 * Close resources when the connection is over.
	//			 */
	//			toClient.close();
	//			fromClient.close();
	//			/*
	//			 * Close Socket of this client.
	//			 */
	//			client.close();
	//			System.out.println("Client has left the server.");
	//		} catch (IOException io) {
	//			System.out.println("Something went wrong. Ending service to client.");
	//		} catch (ClassNotFoundException c) {
	//			System.out.println("Something went wrong. Ending service to client.");
	//		}
	//	}



	//	/**
	//	 * Close connection.
	//	 */
	//	public void close() 
	/**
	 * TODO
	 * 
	 * @param input account info.
	 */
		private void signUp(String input) {
			/*
			 * Split the Account info first name and last name etc,
			 * and store in database.
			 */
			String[] accountDetail = input.split("#");
			String firstName = accountDetail[0];
			String lastName = accountDetail[1];
			String userName = accountDetail[2];
			String email = accountDetail[3];
			String password = accountDetail[4];
			String securityCodeString = accountDetail[5];
			int securityCode = Integer.parseInt(securityCodeString);
			addClient(userName, this);
			
		
			
			//TODO DB
//			String firstName = input.getFirstName();
//			String lastName = input.getLastName();
//			String userName = input.getUserName();
//			String email = input.getEmail();
//			String password = input.getPassword();
//			int securityCode = input.getSecurityCode();
//			addClient(userName, this);
			/*
			 * Know which account this thread is handling.
			 */		
		}
	/**
	 * 
	 * @return whether successfully logged in.
	 */
	//	private boolean login(String[] param) {
	//		
	//	}
	//	private void sendPrivateChat(String[] param) {
	//		sendMessage(param[1], param[2]);
	//	}
	/**
	 * Return userName of this object.
	 * 
	 * @return
	 */
		public String getUserName() {
			// TODO 
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
	 * Generate an ClientManager object of the recipient, and call
	 * sendMessage method on that object.
	 * 
	 * @param param the recipient and the message body.
	 */
	private void sendPrivateChat(String param) {
		// TODO handle # in message if split method does not do it.
		String[] msgInfo = param.split("#", 2);
		ClientManager target = getClientManager(msgInfo[0]);
		String sender = this.getUserName();
		if (clientMap.get(msgInfo[1]) != null) {
			// TODO get sender info
			target.sendMessage(sender, msgInfo[1]);
		}
	}
	private void sendMessage(String from, String msg) {
		System.out.println("Reached sendMessage method in ClientManager class");
		try {
			toClient.writeObject(from + " : " + msg);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error: sendMessage");
		}
	}
	
	
	/**
	 * Broadcast message to all users currently logged in.
	 * 
	 * @param param
	 */
	private void broadcast(String param) {
		String[] msgInfo = param.split("#", 2);

		String sender = this.getUserName();
		String msg = msgInfo[1];
//		Iterator<ClientManager> iter = new Iterator<ClientManager>(clientMap);
//		while (iter.hasNext()) {
//			iter.next();
		clientMap.forEach((username, clientManager) -> {clientManager.sendMessage(sender, msg);});
//		ClientManager target = getClientManager(sender);
//			target.sendMessage(sender, msg);
		}

	public void login(String param) throws IOException {
		String username;
		String password;
		String[] loginInfo = param.split("#");
		username = loginInfo[0];
		password = loginInfo[1];
		
		String storedPassword = "";
		// storedPassword = -retrieve password for that user from database-
			
		if(password.equals(storedPassword)) {
			// loggedIn = true;
			toClient.writeUTF("success");
		}
		else
			toClient.writeUTF("failure");	
	}
	
	public void changePassword(String param) {
		String username;
		String currentPassword;
		String newPassword;
		
		String[] info = param.split("#");
		username = info[0];
		currentPassword = info[1];
		newPassword = info[2];
		
		String storedPassword = "";
		// storedPassword = -retrieve password for that user from database-  
		
		if(currentPassword.equals(storedPassword)) {
			// query to update password of the user in database
			try {
				toClient.writeUTF("success");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			try {
				toClient.writeUTF("failure");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		
	}
	
	public void resetPassword(String param) throws IOException {
		String username;
		String securitycode;
		String newPassword;
		
		String[] info = param.split("#");
		username = info[0];
		securitycode = info[1];
		newPassword = info[2];
		
		String storedSecuritycode = "";
		// storedPassword = -retrieve password for that user from database-
		
		if(securitycode.equals(storedSecuritycode)) {
			// query to update security code in database
			toClient.writeUTF("success");
		}
		else {
			toClient.writeUTF("failure");
		}
	}
	
	public void changeSecurityCode(String param) throws IOException {
		String username;
		String currentSecurityCode;
		String newSecurityCode;
		
		String[] info = param.split("#");
		username = info[0];
		currentSecurityCode = info[1];
		newSecurityCode = info[2];
		
		String storedSecurityCode = "";
		// storedSecurityCode = -retrieve code for that user from database-  
		
		if(currentSecurityCode.equals(storedSecurityCode)) {
			// query to update code of the user in database
			toClient.writeUTF("success");
		}
		else {
			toClient.writeUTF("failure"); 
		}
		
	}
	
	public void resetSecurityCode(String param) throws IOException {
		String username;
		String password;
		String newSecurityCode;
		
		String[] info = param.split("#");
		username = info[0];
		password = info[1];
		newSecurityCode = info[2];
		
		String storedPassword = "";
		// storedPassword = -retrieve password for that user from database-
		
		if(password.equals(storedPassword)) {
			// query to update security code in database
			toClient.writeUTF("success");
		}
		else {
			toClient.writeUTF("failure");
		}
	}
	
	public void logOut(String param) {
		String username;
		
		String[] input = param.split("#", 0);
		username = input[0];
		// remove user from online list
		
	}
	
	public void deleteAccount(String param) {
		String username;
		String[] input = param.split("#", 0);
		username = input[0];
		// remove user Account from database.
	}
	
//	public void sendOnlineArray() {
//		-send array to all logged in clients- broadcast
//	}
//	/**
//	 * Send message input to the current client.
//	 * Call this method on the target(destination) client.
//	 * 
//	 * @param input message to the target.
//	 */
//	public void sendMessage(String param) {
//		String recepient;
//		String msg;
//		String[] loginInfo = param.split("#", 0);
//		recepient = loginInfo[0];
//		msg = loginInfo[1];
//		
//	}
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
