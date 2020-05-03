import java.io.IOException;

public interface DBIface {
	public void signUp(String username, String firstname, String lastname,
			String password, String email, int securytiyCode);
	public boolean login(String userName, String password) throws IOException;

}
