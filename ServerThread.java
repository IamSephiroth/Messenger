import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.StringTokenizer;
/**
 * 
 * @author Mayo
 *
 * @param <E>
 */
public class ServerThread implements Runnable {
	Socket client;
	private Account user;
	String userName;
	DataInputStream fromClient;
	DataOutputStream toClient;
	
	
	PrintWriter pw; // writing data to a file
	static FileWriter fw; // write char by char to a file
	static BufferedWriter bw; 
	
	/**
	 * 
	 * @param client
	 */
	public ServerThread(Socket client, Account user) {
		this.client = client;
		this.user = user;
				
		try {
			fw = new FileWriter("C:\\Users\\sanji\\OneDrive\\Desktop\\userList", true); // true = file not cleared, content appended
		} 
		catch (IOException e) {
			System.err.println("File not found.");
		} 
		
		bw = new BufferedWriter(fw); // writing a string at a time 
		pw = new PrintWriter(bw, true); // writes the data to a file, true = auto flush
	}
	/**
	 * 
	 */
	public void run() {
		ObjectOutputStream toClient;
		ObjectInputStream fromClient;
		Object input;
		int counter = 0;
		
		try {
			
			toClient = new ObjectOutputStream(client.getOutputStream());
			fromClient = new ObjectInputStream(client.getInputStream());
			
			while ((input = fromClient.readObject()) != null ) {
				
				System.out.println("Got " + input + " from client");
				
				if (input instanceof Account ) {
					while (true) {
						// check to see client enters unique username
						if(counter > 0) {
							toClient.writeUTF("Username already exists");
						}
						else {
							toClient.writeUTF("Unique username required");
						}
						
						userName = fromClient.readUTF();
						if (userName == null) {
							return;
						}
						if (!Server.accountName.contains(userName)) {
							Server.accountName.add(userName);
							break;
						}
						counter++;
						// need to store account object in database
						
					}
					
					toClient.writeUTF("Username Accepted" + userName);
					Server.chatLog.add(toClient);
				}
				
				else {
					while (true) {
						// 1 - 1 message
						userName = user.getUserName();
					
						try {
							String message = fromClient.readUTF();
							System.out.println("Message Received: " +message);
							
							if(message.equals("logout") ) {
								user.loggedIn = false;
								client.close();
								break;
							}
							
							StringTokenizer messageRecipient = new StringTokenizer(message, "#");
							String msg = messageRecipient.nextToken();
							String recipient = messageRecipient.nextToken();
							
							for (ServerThread mc : Server.Users) {
								
								if (mc.userName.equals(recipient) && mc.user.loggedIn == true) {
									mc.toClient.writeUTF(this.userName+ " : " + msg);
									break;
								}
							}
						}
						catch (IOException e) {
								System.err.println("Could not send message.");
						
						}					
						toClient.close();
						fromClient.close();
						client.close();
						System.out.println("Client has left the server.");
					} 
				}
					
				}
			}
			
			 
			
		
	
				
			
				catch (IOException io) {
				System.out.println("Something went wrong. Ending service to client.");
				} 
				catch (ClassNotFoundException c) {
				System.out.println("Something went wrong. Ending service to client.");
				}
		}
		
		
//		if (message == null) {
//		return;
//	}
//	pw.println(Username + ": " + message);
//	
//	for (PrintWriter writer : Server.printWriters) {
//		writer.println(Username + ": " + message);
//	}
	
		
		
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
