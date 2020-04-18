/**
 * This class handle messages, the sender and the recipient.
 * 
 * @author Miyo Takahashi
 * @version 2020-03-13
 */
public class MessageHandler {
	private String to; // or chatroom name
	private String from;
	private String msg;
	/**
	 * Constructor of MessageHandler.
	 * 
	 * @param to the recipient.
	 * @param from the sender.
	 * @param msg body of the message.
	 */
	public MessageHandler(String to, String from, String msg) {
		this.to = to;
		this.from = from;
		this.msg = msg;
	}
	public String getTo() {
		return to;
	}
	public String getFrom() {
		return from;
	}
	public String getMsg() {
		return msg;
	}
}
