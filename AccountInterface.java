/**
 * This interface ensures that all classes that implement this interface have the 
 * required methods in the class.
 * @author Kishan Patel
 */
public interface AccountInterface {	
	/**
	 * This logout method, when implemented, should set the field variable 
	 * loggedIn to false;
	 */
	public void logout();
	
	/**
	 * This delete account method, when implemented, should set all the field 
	 * variables to null.
	 */
	public void deleteAccount();
	
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
	 * First the system should check if the old password provided is equal 
	 * to the current password of the account.
	 * If so, it should change the password of the account to the new password. 
	 * Otherwise it should not change the password of the account.
	 * @param oldPassword Old password input by user
	 * @param newPassword New password to set.
	 */
	public String changePassword(String oldPassword, String newPassword);
	
}
