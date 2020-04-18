import java.io.Serializable;

/**
 * This Account class is used to create an account and performs 
 * the operations of an account listed in the AccountInterface.
 * @author Kishan Patel
 */
public class Account implements AccountInterface, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2790775043386079933L;
	
	/**
	 * The field variables are first declared.
	 */
	private String firstName;
	private String lastName;
	private String userName;
	private String email;
	private String password;
	private int securityCode;
	private boolean loggedIn;
	private int remainingLoginAttempts;
	
	/**
	 * This is a constructor to create an account.
	 * @param firstName The first name of the user.
	 * @param lastName The last name of the user.
	 * @param userName The username of the user's account.
	 * @param email The email address of the user's account.
	 * @param password The password of the user's account.
	 */
	public Account(String firstName, String lastName, String userName, String email, String password, int securityCode) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.securityCode = securityCode;
		this.loggedIn = false;
		this.remainingLoginAttempts = 3;
	}
	
    /**
     * This is a getter to get the first name of the user.
	 * @return firstName The first name of the user.
	 */
	public String getFirstName() {
		return this.firstName;
	}

	/**
	 * This is a getter to get the last name of the user.
	 * @return lastName The last name of the user.
	 */
	public String getLastName() {
		return this.lastName;
	}

	/**
	 * This is a getter to get the username of the user's account.
	 * @return userName The user name of the user's account.
	 */
	public String getUserName() {
		return this.userName;
	}

	/**
	 * This is a getter to get the email address of the user's account.
	 * @return email The email address of the user's account.
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * This is a getter to get the password of the user's account.
	 * @return password The password of the user's account
	 */
	public String getPassword() {
		return this.password;
	}
	
	/**
	 * This is a getter to get the security code of the user's account.
	 * @return securityCode The security code of the user's account.
	 */
	public int getSecurityCode() {
		return this.securityCode;
	}

	/**
	 * This is a getter to check if the user is logged in to their account.
	 * @return True if the user is logged in, otherwise false.
	 */
	public boolean isLoggedIn() {
		if (this.loggedIn == true) {
			return true;
		}
		return false;
	}
	
	/**
	 * This is a getter to the remaining login attempts the user has before 
	 * their account is blocked.
	 * @return remainingLoginAttempts The remaining login attempts the user has.
	 */
	public int getRemainingLoginAttempts() {
		return this.remainingLoginAttempts;
	}

	/**
	 * This is a setter to set the first name of the user.
	 * @param firstName The firstName to set.
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * This is a setter to set the last name of the user.
	 * @param lastName The lastName to set.
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * This is a setter to set the username of the user's account.
	 * @param userName The userName to set.
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * This is a setter to set the email address of the user's account.
	 * @param email The email to set.
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * This is a setter to set the password of the user's account.
	 * @param password The password to set.
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * This is a setter to set the security code of the user's account.
	 * @param securityCode The security code to set.
	 */
	public void setSecurityCode(int securityCode) {
		this.securityCode = securityCode;
	}

	/**
	 * This is a setter to set the logged in status of the user's account.
	 * @param loggedIn The loggedIn status to set.
	 */
	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}
	
	/**
	 * This is a setter to set the remaining login attempts the user has.
	 * @param remainingLoginAttempts The remaining login attempts to set.
	 */
	public void setRemainingLoginAttempts(int remainingLoginAttempts) {
		this.remainingLoginAttempts = remainingLoginAttempts;
	}
	
	/**
	 * This is a method to check if the user is online.
	 * @return True if the user is online, otherwise false.
	 */
	public boolean isOnline() {
		return this.isLoggedIn();
	}
    
    /**
     * This method sets the field variable LoggedIn to false once the user 
     * logs out of their account.
     */
    @Override
    public void logout() {
    	this.setLoggedIn(false);
    }
    
    /**
     * This method sets all the field variables to null as the user has deleted 
     * their account.
     */
    @Override
    public void deleteAccount() {
    	this.setFirstName(null);
    	this.setLastName(null);
    	this.setUserName(null);
    	this.setEmail(null);
    	this.setPassword(null);
    	this.setSecurityCode(0);
    	this.setLoggedIn(false);
    	
    }

    /**
	 * This method checks if the password given by the user 
	 * matches the password of the account.
     * @param password The provided password by the user.
     * @return True if the password matches the password of the account,
	 * otherwise false.
     */
    @Override
    public boolean checkPassword(String password) {
		if (password.equals(this.getPassword())) {
			return true;
		} else{
			return false;
		}
    }
    
	/**
	 * This method checks if the security code given by the user 
	 * matches the security code of the account.
	 * @param securityCode The security code given by the user.
	 * @return True if the security code matches the security code of 
	 * the account, otherwise false.
	 */
	@Override
	public boolean checkSecurityCode(int securityCode) {
		if (securityCode == this.getSecurityCode()) {
			return true;
		} else{
			return false;
		}
	}

    /** 
	 * This method changes the password of the account.
	 * The method checks if the oldpassword input by the user is equal to 
	 * the current password.
	 * If it is, then the password is set to the new password.
	 * Otherwise it is not.
	 * @param oldPassword Old password input by user
	 * @param newPassword New password to set.
     */
	@Override
    public String changePassword(String oldPassword, String newPassword) {
    	if (checkPassword(oldPassword)) {
    		this.setPassword(newPassword);
    		return "success";
    	} else {
    		return "failure";
    	}
    }	
	
	/**
	 * This toString method defines how to print an Account object.
	 * @return Account object as a String.
	 */
	@Override
	public String toString() {
		return " [ " + firstName + ", " + lastName + ", Username: " + userName + ", Email Address: "
				+ email + ", Password: " + password + ", SecurityCode: " + securityCode +  " ]";
	}
}
