import java.io.BufferedReader;

/**
 * This interface ensures that all classes that implement this interface have the 
 * required methods in the class.
 * @author Kishan Patel
 */
public interface AccountInterface {
	/**
	 * This login method, when implemented, should check that the 
	 * password entered is the same as the password of the user's account.
	 * If it is the same, the user is logged in and so the field variable 
	 * loggedIn should be set to true. Also, remainingLoginAttempts should be 
	 * reset to 3.
	 * If the password is not the same, the remainingLoginAttempts should be 
	 * reduced by one and a warning should be printed. 
	 * If the user has zero remainingLoginAttempts, they are required to reset 
	 * their password. The resetPassword method should be called by the Client 
	 * class, not in the login method.
	 * @param password The password entered by the user.
	 */
	public void login(String password);
	
	/**
	 * This logout method, when implemented, should set the field variable 
	 * loggedIn to false;
	 */
	public void logout();
	
	/**
	 * This method, when implemented, should check if the password given 
	 * by the user matches the password of the account.
	 * @param password The password given by the user.
	 * @return True if the password matches the password of the account,
	 * otherwise false.
	 */
	public boolean checkPassword(String password);
	
	/**
	 * This method, when implemented, should check if the security code 
	 * given by the user matches the security code of the account.
	 * @param securityCode The security code given by the user.
	 * @return True if the security code matches the security code of 
	 * the account, otherwise false.
	 */
	public boolean checkSecurityCode(int securityCode);
	
	/**
	 * This method, when implemented, should change the password of the 
	 * account.
	 * First, the system should confirm that the user wants to change their 
	 * password.
	 * Then, the old password must be compared with the current password, 
	 * if it is correct, then the user should be able to change the account's 
	 * password to the new password. If it is not correct, a warning message 
	 * should be printed to the user.
	 * @param fromUser The input stream of the user.
	 */
	public void changePassword(BufferedReader fromUser);
	
	/**
	 * This method, when implemented, should reset the password of the account.
	 * First, the system should confirm that the user wants to reset their 
	 * password.
	 * Then, the security code must be compared with the security code of the 
	 * account, if it is correct, then the user should be able to reset the 
	 * account's password to the new password and the remainingLoginAttempts 
	 * should be reset to 3. If the security code is not correct, a warning 
	 * message should be printed to the user.
	 * @param fromUser The input stream of the user.
	 */
	public void resetPassword(BufferedReader fromUser);
	
	/**
	 * This method, when implemented, should change the security code of the 
	 * account.
	 * First, the system should confirm that the user wants to change their 
	 * security code.
	 * Then, then old security code must be compared with the current security code, 
	 * if it is correct, then the user should be able to change the account's 
	 * security code to the new security code. If it is not correct, a warning message 
	 * should be printed to the user.
	 * @param fromUser The input stream of the user.
	 */
	public void changeSecurityCode(BufferedReader fromUser);
	
	/**
	 * This method, when implemented, should reset the security code of the account.
	 * First, the system should confirm that the user wants to reset their security
	 * code.
	 * Then, the password must be compared with the password of the account, if it 
	 * is correct, then the user should be able to reset the account's security code 
	 * to the new security code. If the password is not correct, a warning message
	 * should be printed to the user.
	 * @param fromUser The input stream of the user.
	 */
	public void resetSecurityCode(BufferedReader fromUser);
}
