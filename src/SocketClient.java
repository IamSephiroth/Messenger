import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketClient extends ClientBase {
    private Socket clientSocket;
    private int port = 50000;
    /**
     * Cons
     * @param host
     */
    public SocketClient(String host) {
    	super();
    	System.out.println("Establishing connection. Please wait.");
   		try {
    		clientSocket = new Socket(host, port);
   		} catch (UnknownHostException e) {
   			System.err.println("Unknown host: " + host);
   		} catch (IOException e) {
    		System.err.println("IOException");        
   		}
    }
    /**
     * Closes all streams and connection with the server.
     */
    public void closeStreams() {
    	System.out.println("Closing the connection to " +
    			clientSocket.getInetAddress().getHostName() + ".");
        super.closeStreams();
    	try {
			clientSocket.close();
			System.out.println("Connection closed.");
		} catch (IOException e) {
			System.err.println("Could not close connection to " +
					clientSocket.getInetAddress().getHostName() + ".");
		} 
    }
}