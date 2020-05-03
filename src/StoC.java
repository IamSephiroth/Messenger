/**
 * Interface for messages from server to client.
 *  
 * @author Miyo Takahashi
 * @version 2020-05-03
 */
public interface StoC {
	/*
	 * Handle the message received in the Client provided.
	 */
	public void handle(Client c);
}
