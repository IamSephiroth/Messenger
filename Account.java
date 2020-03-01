import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Scanner;

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
     * This method checks if the password provided by the user is correct.
     * If it is correct, the field variable loggedIn becomes true and the 
     * remainingLoginAttempts is reset to 3.
     * Otherwise the remainingLoginAttempts is reduced by one and a warning 
     * is printed to the user. If the user has zero remainingLoginAttempts, 
     * they are required to reset their password.
     * @param password The password provided by the user.
     */
	@Override
    public void login(String password) {
    	// If the password is correct, the user is logged in.
    	if (password.equals(this.getPassword())) {
    		this.setLoggedIn(true);
    		this.setRemainingLoginAttempts(3);
    	}
    	
    	// Otherwise, the remaining login attempts is reduced by one. 
    	int remainingLoginAttempts = this.getRemainingLoginAttempts() - 1;
    	this.setRemainingLoginAttempts(remainingLoginAttempts);
    	
    	// If the user has no more login attempts, they are required to reset their password.
    	if (remainingLoginAttempts == 0) {
    		System.out.println(remainingLoginAttempts + " login attempts remaining. Please reset password.");
    		this.resetPassword();
    	} else {
    		System.out.println("Warning, incorrect password! " + remainingLoginAttempts + 
					   " login attempts remaining.");
    	}
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
	 * If the old password matches the current password, the password of the 
	 * account is changed.
	 * Otherwise, a warning message is printed to the user.
     */
	@Override
	public void changePassword() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Please enter your current password: ");
		try {
			String password = scanner.next(); //user inputs the password.
			
			/*
			 * If the password is correct, the user is requested to enter 
			 * a new password. This changes their password. 
			 * A message is also printed to inform the user of the successful change.
			 */
			if (this.checkPassword(password)) {
				System.out.print("Correct password. Please enter new password: ");
				try {
					String newPassword = scanner.next();
					this.setPassword(newPassword);
					this.setRemainingLoginAttempts(3);
					System.out.println("Password successfully changed.");
					
				} catch (Exception e) {
					System.out.println("Error, try again. ");
					changePassword(); 
				}
			} else {
				System.out.println("Incorrect password, try again.");
				changePassword(); 
			}
			
			scanner.close();
			
		} catch (Exception e) {
			System.out.println("Error, try again.");
			changePassword();
		}
	}
   
	/**
	 * This method resets the password of the account.
	 * If the security code given by the user is equal to the security code of 
	 * the account, the user is able to reset their password and the 
	 * remainingLoginAttempts is reset to 3.
	 * If the security code is not correct, a warning message is printed to the user.
	 */
	@Override
	public void resetPassword() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Please enter your security code: ");
		try {
			int securityCode = scanner.nextInt(); //user inputs the security code.
			
			/*
			 * If the security code is correct, the user is requested to enter 
			 * a new password. This resets their password and resets the remaining 
			 * login attempts. A message is also printed to inform the user of the 
			 * successful change.
			 */
			if (this.checkSecurityCode(securityCode)) {
				System.out.print("Correct security code. Please enter new password: ");
				try {
					String newPassword = scanner.next();
					this.setPassword(newPassword);
					this.setRemainingLoginAttempts(3);
					System.out.println("Password successfully changed.");
					
				} catch (Exception e) {
					System.out.println("Error, try again. ");
					resetPassword(); 
				}
				
			} else {
				if (Integer.toString(securityCode).length() == 4) {
					// If the input code is the correct length but does not match, it is incorrect.
					System.out.println("Inorrect security code. ");
					resetPassword();
					
				} else {
					// If the input code is the incorrect length, it is invalid.
					System.out.println("Error, enter a four digit security code e.g 1234.");
					resetPassword();
				}
			}
			
			scanner.close();
			
		} catch (InputMismatchException e) {
			// If the input code is not an integer, it is invalid.
			System.out.println("Error, enter a four digit security code e.g 1234.");
			resetPassword();
		}
	}

	/**
	 * This method changes the security code of the account.
	 * If the old security code matches the current security code, the security 
	 * of the account is changed.
	 * Otherwise, a warning message is printed to the user.
	 */
	@Override	
	public void changeSecurityCode() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Please enter your current security code: ");
		try {
			int securityCode = scanner.nextInt(); //user inputs the security code.
			
			/*
			 * If the security code is correct, the user is requested to enter 
			 * a new security code. This changes their security code. 
			 * A message is also printed to inform the user of the successful change.
			 */
			if (this.checkSecurityCode(securityCode)) {
				System.out.print("Correct security code. Please enter new security code: ");
				try {
					int newSecurityCode = scanner.nextInt();
					
					if (Integer.toString(newSecurityCode).length() == 4) {
						// If the new input code is the correct length, it is set as the new security code.
						this.setSecurityCode(newSecurityCode);
						System.out.println("Security code successfully changed. ");
						
					} else {
						// If the new input code is the incorrect length, it is invalid.
						System.out.println("Error, enter a four digit security code e.g 1234.");
						changeSecurityCode();
					}
					
				} catch (InputMismatchException e) {
					// If the input code is not an integer, it is invalid.
					System.out.println("Error, enter a four digit security code e.g 1234.");
					changeSecurityCode(); 
				}
				
			} else {
				if (Integer.toString(securityCode).length() == 4) {
					// If the input code is the correct length but does not match, it is incorrect.
					System.out.println("Inorrect security code. ");
					changeSecurityCode();
					
				} else {
					// If the input code is the incorrect length, it is invalid.
					System.out.println("Error, enter a four digit security code e.g 1234.");
					changeSecurityCode();
				}
			}
			
			scanner.close();
			
		} catch (InputMismatchException e) {
			// If the input code is not an integer, it is invalid.
			System.out.println("Error, enter a four digit security code e.g 1234.");
			changeSecurityCode();
		}
			
	}

	/**
	 * This method resets the security code of the account.
	 * If the password given by the user is equal to the password of the account, 
	 * the user is able to reset their security code.
	 * If the password is not correct, a warning message is printed to the user.
	 */
	@Override
	public void resetSecurityCode() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Please enter your password: ");
		try {
			String password = scanner.next(); //user inputs the password.
			
			/*
			 * If the password is correct, the user is requested to enter 
			 * a new security code. This resets their security code. 
			 * A message is also printed to inform the user of the successful change.
			 */
			if (this.checkPassword(password)) {
				System.out.print("Correct password. Please enter new security code: ");
				try {
					int newSecurityCode = scanner.nextInt();
					
					if (Integer.toString(newSecurityCode).length() == 4) {
						// If the input code is the correct length, it is set as the new security code.
						this.setSecurityCode(newSecurityCode);
						System.out.println("Security code successfully changed. ");
						
					} else {
						// If the input code is the incorrect length, it is invalid.
						System.out.println("Error, enter a four digit security code e.g 1234.");
						resetSecurityCode();
					}
					
				} catch (InputMismatchException e) {
					// If the input code is not an integer, it is invalid.
					System.out.println("Error, enter a four digit security code e.g 1234.");
					resetSecurityCode(); 
				}
				
			} else {
				System.out.println("Incorrect password, try again.");
				resetSecurityCode(); 
			}
			scanner.close();
			
		} catch (Exception e) {
			System.out.println("Error, try again. ");
			resetSecurityCode();
		}
	}
	
	/**
	 * Main method to test the methods.
	 * REMEMBER TO DELETE THE MAIN METHOD!
	 */
	public static void main(String[] args) {
		Account test = new Account("John", "Smith", "JS", "js@bham", "password", 1234);
//		System.out.println(test.checkPassword("password")); //Should return true
//		System.out.println(test.checkPassword("hello")); //Should return false
//		test.resetPassword(); //Reset password to hello
//		System.out.println(test.checkPassword("password")); //Should return false
//		System.out.println(test.checkPassword("hello")); //Should return true
		
//		test.login("hello"); //Incorrect, 2 attempts left
//		test.login("hello"); //Incorrect, 1 attempt left
//		test.login("hello"); //Incorrect, 0 attempts left. Forced to reset password.
		
//		test.resetSecurityCode();
		
//		test.changePassword();
		
		test.changeSecurityCode();	
	}
}
