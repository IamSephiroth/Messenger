/**
 * Interface for messages from client to server.
 * 
 * @author Miyo Takahashi
 * @version 2020-05-03
 */
public interface CtoS {
	/*
	 * Handle the message received in the ClientManager provided.
	 */
	public void handle(ClientManager cm);
}
