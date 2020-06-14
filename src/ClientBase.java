import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * TODO remove eveth dependent on socket, 
 * @author Mayo
 *
 */
public class ClientBase {
	private BufferedReader fromUser;
	private InputStream fromServer;
	private OutputStream toServer;
	private Account user;
	/**
	 * Constructor to create a client. The constructor configures user input
	 * and set the fromServer and toServer streams to the supplied values.
	 * @param is InputStream to read messages from the server.
	 * @param os OutputStream to write messages to the server.
	 */
	public ClientBase(InputStream is, OutputStream os) {
		fromUser = new BufferedReader(new InputStreamReader(System.in));
		fromServer = is; 
		toServer = os;
	}
	/**
	 * Construct a partial ClientBase. This is needed in case a sub-class
	 * wants to construct the input or output streams in their own constructor.
	 */
	protected ClientBase() {
		fromUser = new BufferedReader(new InputStreamReader(System.in));
	}
	/**
	 * Sets the fromServer and toServer streams to the supplied value.
	 * @param is InputStream to read messages from the server.
	 * @param os OutputStream to write messages to the server.
	 */
	protected void connect(InputStream is, OutputStream os) {
		fromServer = is; 
		toServer = os;
	}
	/**
	 * Closes all the streams.
	 */
	public void closeStreams() {
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
	}
	/**
	 * Asks the user whether they want to sign in or sign up.
	 * If existing, the Client is requested to login.
	 * If new, the Client is requested to create an account.
	 * Otherwise, the Client is asked the question again until they 
	 * answer with "1" or "2".
	 */
	public void loginOrSignUp() throws Exception {
		System.out.print("Are you an existing or new user?"
				+ " Type '1' for existing, '2' otherwise.");
		String input = fromUser.readLine();
		if (input.equals("1")) {
			login();
		} else if (input.equals("2")) {
			signUp();
		} else {
			loginOrSignUp();
		}
	}
	/**
	 * Login to an existing account. Check if the combination of the username
	 * and the password entered by the user matches the data stored in the
	 * database. Allow up to 3 login attempts. Ask the user to reset their
	 * password after 3 fails.
	 */
	public void login() throws Exception {
		System.out.println("Login to account.");
		/*
		 * Get input from the user.
		 */
		System.out.print("Please enter your username: ");
		String username = fromUser.readLine();
		System.out.print("Please enter your password: ");
		String password = fromUser.readLine();
		/*
		 * TODO form message following the protocol
		 * Send login request to the server.
		 */
	}
	/**
	 * Requests the user to create a new account.
	 * @throws IOException 
	 */
	public void signUp() throws IOException {
		/*
		 * Creates an instance of SignUp to make JSON format String.
		 * Writes this string to toServer to communicate.
		 * TODO change the input to fromUser.
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
		 * Writes sign up message to toServer.
		 */
		su.send(toServer);
	}
	/**
	 * Asks the user to confirm if they want to logout or not.
	 *   + If "yes", log the user out.
	 *   + If "no", the user can continue to send messages.
	 *   + Otherwise, asks the user the question again until they answer with 
	 *     "yes" or "no".
	 */
	public void logout() throws IOException {
		System.out.print("Are you sure you want to logout? ");
		String input = fromUser.readLine();
		if (input.equals("no")) {
			sendText();
		} else if (input.equals("yes")) {
			user.logout();
			System.out.println("Logged out!");
			// TODO send logout message to the server.
		} else {
			logout();
		}
	}
	/**
	 * Asks the user to confirm if they want to delete their account or not.
	 *   + If "no", the user can continue to send messages.
	 *   + If "yes", delete the account and disconnect the user.
	 *   + Otherwise, asks the user the question again until they answer with 
	 *     "yes" or "no".
	 */
	public void deleteAccount() throws IOException {
		System.out.print("Are you sure you want to delete account? ");
		String input = fromUser.readLine();
		if (input.equals("no")) {
			sendText();
		} else if (input.equals("yes")) {
			user.deleteAccount();
			System.out.println("Account Deleted!");
			// TODO send delete account message to the server.
		} else {
			deleteAccount();
		}
	}
	/**
	 * Asks the user what they want to send and the recipient, and writes that
	 * to toServer. 
	 * If an error occurs, notify the user by an error message informing 
	 * them that the message was not sent.
	 * If the Client sends change password, change security code or reset security code, they 
	 * will be asked to confirm if they want to proceed with their choice and make the changes.
	 * If the Client sends logout or delete account, they will be asked to confirm if they 
	 * want to proceed with their choice.
	 * TODO remove sec code, and change the way to ask the user what they want
	 * to do. what if the user wants to send "logout" as text?
	 * TODO get recipient
	 */
	public void sendText() throws Exception {
		String textToSend = "";
		while (!textToSend.equals("logout") && !textToSend.equals("delete account")) {
			if(textToSend.equals("change password")) {
				// TODO send change password message to the server.
			} else if(textToSend.equals("change security code")) {
				// TODO send change sec code message to the server.
			} else if(textToSend.equals("reset security code")) {
				// TODO send reset sec code message to the server.
			}
			System.out.print(user.getUserName() + ": ");
			textToSend = fromUser.readLine();
			//        		toServer.writeObject(user.getUserName() + ": " + textToSend);
		}
		if (textToSend.equals("logout")) {
			logout();
		} else {
			deleteAccount();
		}
	}
	/**
	 * 
	 */
	public void receiveMessages() throws IOException {
		while (user.isLoggedIn()) {
			byte[] b = new byte[2048];
			int length = fromServer.read(b);
			System.out.println("Received: " + b);
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
			//sendText().start();
			//Simultaneously the client is able to receive messages.
			receiveMessages();
			//Once the user has logged out, or if they delete their account, the connection is closed.
			closeStreams();
		} catch (Exception e) {
			closeStreams();
		}
	}
}