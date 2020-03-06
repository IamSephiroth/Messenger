import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
/**
 * 
 * @author Mayo
 *
 * @param <E>
 */
public class ServerThread implements Runnable {
	private Socket client = null;
	/**
	 * 
	 * @param client
	 */
	public ServerThread(Socket client) {
		this.client = client;
	}
	/**
	 * 
	 */
	public void run() {
		ObjectOutputStream toClient;
		ObjectInputStream fromClient;
		Object input;
		try {
			toClient = new ObjectOutputStream(client.getOutputStream());
			fromClient = new ObjectInputStream(client.getInputStream());
			while ((input = fromClient.readObject()) != null) {
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
					sendMessage((String) input);
				}
				
				
			}
			
			toClient.close();
			fromClient.close();
			client.close();
			System.out.println("Client has left the server.");
		} catch (IOException io) {
			System.out.println("Something went wrong. Ending service to client.");
		} catch (ClassNotFoundException c) {
			System.out.println("Something went wrong. Ending service to client.");
		}
	}
	/**
	 * 
	 * @param input
	 */
	public void storeAccount(Account input) {
		/*
		 * Split the Account into firstname and lastname etc and store in database.
		 */
	}
	/**
	 * 
	 * @param input
	 */
	public void sendMessage(String input) {
		
	}
}