import java.io.IOException;
import java.io.OutputStream;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Defines messages between clients and the server.
 * 
 * @author Miyo Takahashi
 * @version 2020-05-03
 */
public class Message {
	private String command;
	public Message(String command) {
		this.command = command;
	}
	public void setCommand(String command) {
		this.command = command;
	}
	public String getCommand() {
		return command;
	}
	/*
	 * Send this message.
	 */
	public void send(OutputStream out) throws IOException {
		ObjectMapper om = new ObjectMapper();
		try {
			String json = om.writeValueAsString(this);
			out.write(json.getBytes());
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
}
class SignUp extends Message implements CtoS {
	private String firstName;
	private String lastName;
	private String username;
	private String email;
	private String password;

	public SignUp() {
		super("sign up reply");
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUsername() {
		return username;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmail() {
		return email;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword() {
		return password;
	}

	public void run() {

	}

	/*
	 * Handle the message in the ClientManager provided.
	 */
	@Override
	public void handle(ClientManager cm) {
		cm.signUp(this);
	}
}
class SignUpReply extends Message implements StoC {
	private int status;
	public SignUpReply(int status) {
		super("sign up reply");
		this.status = status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	public int getStatus() {
		return status;
	}

	@Override
	public void handle(Client c) {
		// TODO Auto-generated method stub

	}
}

