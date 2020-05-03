import static org.junit.jupiter.api.Assertions.*;

import java.net.ServerSocket;
import java.net.Socket;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ClientManagerTest {
	Socket csocket = new Socket();
	Client tc1;
	Client tc2;
	ClientManager cm1;
	ClientManager cm2;
	
	String oneToOne= "6#recipient#hello";
	
	@BeforeEach
	void setup() {
		ServerSocket ss = new ServerSocket(50000);
		ClientManager.setDB(new DatabaseStub());
		tc1 = new Client("localhost"); 
		cm1 = new ClientManager(ss.accept());
		tc2 = new Client("localhost");
		cm2 = new ClientManager(ss.accept());	
	}
	
	@Test
	void sendPrivateChat() {
		//assertEquals();
	}
	
	@Test
	void sendMessage() {
		
	}

	@Test
	void broadcast () {
		
	}
	
	@Test
	void login() {
		
	}

	@Test
	void changePassword() {
		
	}

	@Test
	void resetPassword() {
		
	}

	@Test
	void logout() {
		
	}

	@Test
	void deleteAccount() {
		
	}


}
