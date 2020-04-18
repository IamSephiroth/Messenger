import java.io.Serializable;
import java.net.InetAddress;



public class ChatUsers implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8328918636071924607L;
	
	private String userName;
	private int userID;
	private InetAddress ipAddress;
	private int userPort;
	
	
	
	public ChatUsers(String userName, int userID, InetAddress ipAddress, int userPort) {
		this.userName = userName;
		this.userID = userID;
		this.ipAddress = ipAddress;
		this.userPort = userPort;
	}


	public String getUserName() {
		return userName;
	}

	public int getUserID() {
		return userID;
	}

	public InetAddress getIpAddress() {
		return ipAddress;
	}

	public int getUserPort() {
		return userPort;
	}
	
	
	

}
