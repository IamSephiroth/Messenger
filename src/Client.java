import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * This Client class creates a Client on the command line using 
 * 		java Client insert_hostname
 * Once the Client is connected and into their account, typing logout will 
 * ask the user if they are sure they want to logout, if yes, this ends the 
 * client and the connection with the server.
 * 
 * @author Kishan Patel
 * @version 2020-05-10
 */
public class Client {
    private Socket clientSocket;
    private BufferedReader fromUser;
    private OutputStream toServer;
    private InputStream fromServer;
    private Account user;
    private int port = 50000;

    /**
     * Constructor to create a client. The constructor opens a socket connection 
     * with the server and also gets the server's input and output streams.
     * @param host The server to connect to. (127.0.0.1 refers to localhost)
     */
    public Client(String host) {
    	System.out.println("Establishing connection. Please wait.");
    	try {
    		clientSocket = new Socket(host, port);
    		// TODO InputStreamReader? 
    		fromUser = new BufferedReader(new InputStreamReader(System.in));
        	
    		toServer = clientSocket.getOutputStream();
        	fromServer = clientSocket.getInputStream();
        	System.out.println("Connected to " + host);
    	} catch (UnknownHostException unknownHostException) {
    		System.err.println("Unknown host: " + host);
        } catch (IOException ioException) {
    		System.err.println("IOException");        
        } 
    }
    /**
     * This closeConnection method is like a finalise method, it closes all of the 
     * streams and ends the connection with the server.
     */
    public void closeConnection() {
    	System.out.println("Closing the connection to " +
    			clientSocket.getInetAddress().getHostName() + ".");
        try {
			toServer.close();
		} catch (IOException e) { 
			System.err.println("Could not close stream.");
		}
        try {
			fromServer.close();
		} catch (IOException e) {
			System.err.println("Could not close stream.");
		}
        try {
			fromUser.close();
		} catch (Exception e) {
			System.err.println("Could not close stream.");
		}
        try {
			clientSocket.close();
			System.out.println("Connection closed.");
		} catch (IOException e) {
			System.err.println("Could not close connection to " +
					clientSocket.getInetAddress().getHostName() + ".");
		} 
    }
    /**
     * This method asks the client whether they want to sign in or 
     * sign up for a new account.
     * If existing, the Client is made to login.
     * If new, the Client is made to create an account.
     * Otherwise, the Client is asked the question again until they 
     * answer with existing or new.
     */
    public void loginOrSignUp() {
    	System.out.print("Are you an existing or new user?"
    			+ " Type '1' for existing, '2' otherwise.");
    	try {
    		String input = fromUser.readLine();
    		if (input.equals("1")) {
    			login();
    		} else if (input.equals("2")) {
    			signUp();
    		} else {
    			loginOrSignUp();
    		}
    	} catch (Exception e) {
    		System.err.println("Something went wrong. Please try again.");
    		closeConnection();
    	}
    }
	/**
	 * Login to an existing account. Check if the combination of the username
	 * and the password entered by the user matches the data stored in the
	 * database. Allow up to 3 login attempts. Ask the user to reset their
	 * password after 3 fails.
	 */
    public void login() {
    	System.out.println("Login to account.");
    	/*
    	 * Get input from the user.
    	 */
    	try {
    		System.out.print("Please enter your username: ");
    		String username = fromUser.readLine();
    		System.out.print("Please enter your password: ");
    		String password = fromUser.readLine();
    	} catch (Exception e) {
    		System.err.println("Error, try again. ");
    		closeConnection();
    	}
    	/*
    	 * TODO form message following the protocol
    	 * Send login request to the server.
    	 */
    	
    }
    /**
     * This method makes the Client create a new account.
     * @throws IOException 
     */
    public void signUp() throws IOException {
		/*
		 * Create an instance of SignUp to make JSON format String.
		 * Pass this string to the server to communicate.
		 */
		SignUp su = new SignUp();
		Scanner s = new Scanner(System.in);
		System.out.println("first name:");
		su.setFirstName(s.next());
		System.out.println("last name:");
		su.setLastName(s.next());
		System.out.println("username:");
		su.setUsername(s.next());
		System.out.println("email address:");
		su.setEmail(s.next());
		System.out.println("password:");
		su.setPassword(s.next());
		s.close();
		/*
		 * Convert SignUp object to JSON String.
		 * TODO is this just for checking JSON format?
		 */
		ObjectMapper om = new ObjectMapper();
		try {
			String json = om.writeValueAsString(su);
			System.out.println(json);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		/*
		 * Send sign up message to the server.
		 */
		su.send(toServer);
	}
    
    /**
     * This method asks the Client to confirm if they want to logout or not.
     * If no, the Client can continue to send messages.
     * If yes, the Client is logged out.
     * Otherwise the Client is asked the question again until they answer with 
     * yes or no.
     */
    public void logout() {
    	System.out.print("Are you sure you want to logout? ");
    	try {
    		String input = fromUser.readLine();
			if (input.equals("no")) {
				sendMessage();
			} else if (input.equals("yes")) {
				user.logout();
				System.out.println("Logged out!");
//				toServer.reset(); //Reset outputStream to clear cache.
//    			toServer.writeObject(user); // After the user logs out, their account gets sent to server.
//				closeConnection();
			} else {
				logout();
			}
		} catch (IOException e) {
			System.err.println("Error, could not logout.");
			closeConnection();
		}
    }
    
    /**
     * This method asks the Client to confirm if they want to delete their account or not.
     * If no, the Client can continue to send messages.
     * If yes, the Client's account is deleted and they are disconnected
     * Otherwise the Client is asked the question again until they answer with 
     * yes or no.
     */
    public void deleteAccount() {
    	System.out.print("Are you sure you want to delete account? ");
    	try {
    		String input = fromUser.readLine();
			if (input.equals("no")) {
				sendMessage();
			} else if (input.equals("yes")) {
				user.deleteAccount();
				System.out.println("Account Deleted!");
//				toServer.reset(); //Reset outputStream to clear cache.
//    			toServer.writeObject(user); // After the user deletes their account, it gets sent to server to be deleted from the database.
//				closeConnection();
			} else {
				deleteAccount();
			}
		} catch (IOException e) {
			System.err.println("Error, could not logout.");
			closeConnection();
		}
    }
    
    /**
     * This method allows the Client to send messaged to the Server. 
     * If an error occurs, the Client is notified by an error message informing 
     * them that the message was not sent.
     * If the Client sends change password, change security code or reset security code, they 
     * will be asked to confirm if they want to proceed with their choice and make the changes.
     * If the Client sends logout or delete account, they will be asked to confirm if they 
     * want to proceed with their choice.
     */
	public void sendMessage() {
		try {
        	String messageToSend = "";
        	while (!messageToSend.equals("logout") && !messageToSend.equals("delete account") ) {
        		if(messageToSend.equals("change password")) {
//        			user.changePassword(fromUser);
//        			toServer.reset(); //Reset outputStream to clear cache.
//        			toServer.writeObject(user); // After the user changes their password, their account gets sent to server.
        		} else if(messageToSend.equals("change security code")) {
//        			user.changeSecurityCode(fromUser);
//        			toServer.reset(); //Reset outputStream to clear cache.
//        			toServer.writeObject(user); // After the user changes their security code, their account gets sent to server.
        		} else if(messageToSend.equals("reset security code")) {
//        			user.resetSecurityCode(fromUser);
//        			toServer.reset(); //Reset outputStream to clear cache.
//        			toServer.writeObject(user); // After the user changes their security code, their account gets sent to server.
        		}
        		System.out.print(user.getUserName() + ": ");
        		messageToSend = fromUser.readLine();
//        		toServer.writeObject(user.getUserName() + ": " + messageToSend);
        	}
        	if (messageToSend.equals("logout")) {
        		logout();
        	} else {
        		deleteAccount();
        	}
		} catch(Exception e) {
			System.err.println("Message not sent!");
			sendMessage();
		}
	}
	
	/**
	 * 
	 */
	Thread sendMessages = new Thread(new Runnable() {
		@Override
		public void run() {
			sendMessage();
			return;
		}
	});
	
	/**
	 * 
	 */
	public void receiveMessages() {
		while (user.isLoggedIn()) {
			try {
				byte[] b = new byte[2048];
				int length = fromServer.read(b);
				System.out.println("Received: " + b);
			} catch (IOException e) {
				System.out.println("Could not receive message.");
			}
		}
	}
	
//	Thread receiveMessages = new Thread(new Runnable() {
//		@Override
//		public void run() {
//			while (ableToReceiveMessages) {
//				try {
//					Object msg = fromServer.readObject();
//					System.out.println("Received: " + msg);
//				} catch (ClassNotFoundException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//			return;
//		}
//	});
    
	/**
	 * The run method is what the client actually does.
	 * The Client first logs in to their account or signs up to a new account. 
	 * The Client can then send messages to the Server and simultaneously receive 
	 * messages from the server.
	 * Once the Client has logged out, or if they delete their account, the 
	 * connection is closed.
	 * 
	 * EDIT JAVADOC WHEN CHANGE THE RUN METHOD!!! 
	 */
	public void run() {	
		try {
			//Client is asked to login or sign up.
			loginOrSignUp();
			//Once logged in, a thread is started that allows the client to send messages.
			sendMessages.start();
			//Simultaneously the client is able to receive messages.
			receiveMessages();
			//Once the user has logged out, or if they delete their account, the connection is closed.
			closeConnection();
		} catch(Exception e) {
			System.err.println("Socket communication broke.");
			closeConnection();
		}
	}
	
   
	/**
	 * Main method to create the client
	 * @param args The server name input from the command line to connect to.
	 */
    public static void main(String[] args) {
        if (args.length != 1) {
        	System.err.println("Usage: java LaunchClient hostname");
        } else {
    		Client client = new Client(args[0]);
    		client.run();
        }
    }
}
