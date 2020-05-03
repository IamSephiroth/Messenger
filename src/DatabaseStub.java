import java.io.IOException;
import java.util.HashMap;

/**
 * This class if for testing ClientManager class without connecting
 * @author Miyo Takahashi
 * @version 2020-04-25
 *
 */
public class DatabaseStub implements DBIface {
	HashMap<String, String> pwd = new HashMap<String, String>();
	public void signUp(String userName, String firstName, String lastName,
			String password, String email, int securityCode) {
		pwd.put(userName, password);
	}
	public boolean login(String userName, String password) throws IOException {
		return pwd.get(userName).equals(password);
	}
}