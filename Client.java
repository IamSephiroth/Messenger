import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 
 * @author Kishan Patel
 *
 */
public class Client {

    private Socket server;
    private BufferedReader fromUser;
    private ObjectOutputStream toServer;
    private ObjectInputStream fromServer;
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
        	toServer = new ObjectOutputStream(server.getOutputStream());
        	fromServer = new ObjectInputStream(server.getInputStream());
        	
        	System.out.println("Connected to " + serverName);
        	
        	// Initialise user object 
        	user = new Account(null, null, null, null, null, 0);

        	
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
    	
    }
    
    /**
     * This method makes the Client create a new account.
     */
    public void signUp() {
    	System.out.println("Create new account.");
    	try {
			System.out.print("Please enter your first name: ");
			user.setFirstName(fromUser.readLine());
			System.out.print("Please enter your last name: ");
			user.setLastName(fromUser.readLine());
			System.out.print("Please create your username: ");
			user.setUserName(fromUser.readLine());
			System.out.print("Please enter your email address: ");
			user.setEmail(fromUser.readLine());
			System.out.print("Please create your password: ");
			user.setPassword(fromUser.readLine());
			System.out.print("Please create your security code: ");
			/*
			 * This while loop ensures that the security code is made up of four 
			 * numerical digits.
			 */
			while (Integer.toString(user.getSecurityCode()).length() != 4) {
				try {
					//Read the user input as a String.
					String securityCode = fromUser.readLine(); 
					try {
						/*
						 *  Parse the input as an Integer. Then check its length. If the length is 4, the 
						 *  account is successfully created. Otherwise there is an error and it has to be 
						 *  input again.
						 *  If the user input cannot be parsed as an Integer, again the Client gets an error 
						 *  and has to input the code again.
						 */
						user.setSecurityCode(Integer.parseInt(securityCode)); 
						if (securityCode.length() == 4) {
							System.out.println("Account successfully created.");
						} else {
							System.out.print("Error, please create a four digit security code, e.g 1234: ");
							fromUser = new BufferedReader(new InputStreamReader(System.in));
						}
					} catch (NumberFormatException e) {
						System.out.print("Error, please create a four digit security code, e.g 1234: ");
						fromUser = new BufferedReader(new InputStreamReader(System.in));
					}
				} catch (Exception e) {
					System.out.print("Error, please create a four digit security code, e.g 1234: ");
					fromUser = new BufferedReader(new InputStreamReader(System.in));
				}
			}
    		user.setRemainingLoginAttempts(3);
    		
    	} catch(Exception e) {
    		System.err.println("Error, try again. ");
    		closeConnection();
    	} 
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
				closeConnection();
			} else {
				logout();
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
     * If they Client sends logout, they will be asked to confirm if they 
     * want to logout or not.
     */
	public void sendMessage() {
		try {
        	String messageToSend = "";
        	while (!messageToSend.equals("logout")) {
        		System.out.print(user.getUserName() + ": ");
        		messageToSend = fromUser.readLine();
        		toServer.writeObject(user.getUserName() + ": " + messageToSend);
        	}
        	logout();	
		} catch(Exception e) {
			System.err.println("Message not sent!");
			sendMessage();
		}
	}
    
	/**
	 * The run method is what the client actually does.
	 * The Client first logs in to their account or signs up to a new account. 
	 * The Client can then send messages to the Sever.
	 * 
	 * EDIT JAVADOC WHEN CHANGE THE RUN METHOD!!! 
	 */
	public void run() {	
		try {
			//Client is asked to login or sign up.
			loginOrSignUp();
			sendMessage();	
		} catch(Exception e) {
			System.err.println("Socket communication broke.");
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