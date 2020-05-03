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
 * This class may make use of code provided in lectures and previous worksheets.
 * @author Kishan Patel
 * @version 2020-05-04
 */
public class Client {

    private Socket server;
    private BufferedReader fromUser;
    private OutputStream toServer;
    private InputStream fromServer;
    private Account user;

    /**
     * Constructor to create a client. The constructor opens a socket connection 
     * with the server and also gets the server's input and output streams.
     * @param serverName The server to connect to.
     */
    public Client(String serverName){
    	System.out.println("Establishing connection. Please wait.");
    	try {
    		// Creates a socket to talk to serverName at port 50000.
    		server = new Socket(serverName, 50000); 
    		
    		// Set up the streams to send and receive messages to and from the server.
    		fromUser = new BufferedReader(new InputStreamReader(System.in));
        	toServer = server.getOutputStream();
        	fromServer = server.getInputStream();
        	
        	System.out.println("Connected to " + serverName);
        	
    	} catch (UnknownHostException unknownHostException) {
    		System.err.println("Unknown host: " + serverName);
    		
        } catch (IOException ioException) {
    		System.err.println("Server not found.");        
        } 
    }

    /**
     * This closeConnection method is like a finalise method, it closes all of the 
     * streams and ends the connection with the server.
     */
    public void closeConnection() {
    	System.out.println("Closing the connection to " + server.getInetAddress().getHostName() + ".");
        // Close the streams
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
			server.close();
			System.out.println("Connection closed.");
		} catch (IOException e) {
			System.err.println("Could not close connection to " + server.getInetAddress().getHostName() + ".");
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
    	System.out.print("Are you an existing or new user? ");
    	try {
    		String response = fromUser.readLine();
    		if (response.equals("existing")) {
    			//Existing users are taken to the login page.
    			login();
    		} else if (response.equals("new")) {
    			//New users are taken to the sign up page.
    			signUp();
    		} else {
    			//If neither, the user is asked the question again.
    			loginOrSignUp();
    		}
    	} catch (Exception e) {
    		System.err.println("Error, try again.");
    		closeConnection();
    	}
    }
    
    /**
     * This method makes the client log in to their account.
     */
    public void login() {
    	/*
    	 * Connect to database, ask user to enter username and password.
    	 * Check that that combination exists in the database, if it does 
    	 * then log the user into their account.
    	 * If it is incorrect, the user tries again. 
    	 */
    	System.out.println("Login to account.");
    	try {
    		System.out.print("Please enter your username: ");
    		String username = fromUser.readLine();
    		System.out.print("Please enter your password: ");
    		String password = fromUser.readLine();
    		/*
    		 * If the username and password combination is correct, get the account 
    		 * object and set the "user" object to that object.
    		 */
    	}  catch(Exception e) {
    		System.err.println("Error, try again. ");
    		closeConnection();
    	} 
    	
    	/* 
    	 * If the user has no remaining login attempts, they are requested to 
    	 * reset their password.
    	 */
    	try {
        	if (user.getRemainingLoginAttempts() == 0) {
//        		user.resetPassword(fromUser);
//        		toServer.reset();
        		//toServer.writeObject(user);
        	}
    	} catch (Exception e) {
    		System.out.println("Error, try again.");
    	}

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
    		String response = fromUser.readLine();
			if (response.equals("no")) {
				sendMessage();
			} else if (response.equals("yes")) {
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
    		String response = fromUser.readLine();
			if (response.equals("no")) {
				sendMessage();
			} else if (response.equals("yes")) {
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
