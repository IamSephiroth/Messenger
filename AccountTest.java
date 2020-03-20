package GUI;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * This Class contains various tests for the methods in the Account class.
 * @author Kishan Patel
 *
 */
class AccountTest {
	//Two account objects that will be used in the tests.
	private Account test1;
	private Account test2;

	/**
	 * This is a piece of code that should be executed before each test.
	 * It creates a two new objects that will be used throughout.
	 */
	@BeforeEach
	public void beforeEach() {
		test1 = new Account("John", "Smith", "JS", "js@bham", "password", 1234);
		test2 = new Account("Mary", "Anderson", "M_Anderson", "ma@bham", "MaRyAnDeRsOn", 1111);
	}
	
	/**
	 * The following set of tests test the getters
	 */
	@Test
	void testGetters() {
		String expectedFirstName1 = "John";
		String actualFirstName1 = test1.getFirstName();
		assertEquals(expectedFirstName1, actualFirstName1, "Error, expected first name does not equal actual first name.");
		
		String expectedFirstName2 = "Mary";
		String actualFirstName2 = test2.getFirstName();
		assertEquals(expectedFirstName2, actualFirstName2, "Error, expected first name does not equal actual first name.");
		
		String expectedLastName1 = "Smith";
		String actualLastName1 = test1.getLastName();
		assertEquals(expectedLastName1, actualLastName1, "Error, expected last name does not equal actual last name.");
		
		String expectedLastName2 = "Anderson";
		String actualLastName2 = test2.getLastName();
		assertEquals(expectedLastName2, actualLastName2, "Error, expected last name does not equal actual last name.");
		
		String expectedUserName1 = "JS";
		String actualUserName1 = test1.getUserName();
		assertEquals(expectedUserName1, actualUserName1, "Error, expected username does not equal actual username.");
		
		String expectedUserName2 = "M_Anderson";
		String actualUserName2 = test2.getUserName();
		assertEquals(expectedUserName2, actualUserName2, "Error, expected username does not equal actual username.");
		
		String expectedEmail1 = "js@bham";
		String actualEmail1 = test1.getEmail();
		assertEquals(expectedEmail1, actualEmail1, "Error, expected email does not equal actual email.");
		
		String expectedEmail2 = "ma@bham";
		String actualEmail2 = test2.getEmail();
		assertEquals(expectedEmail2, actualEmail2, "Error, expected email does not equal actual email.");
		
		String expectedPassword1 = "password";
		String actualPassword1 = test1.getPassword();
		assertEquals(expectedPassword1, actualPassword1, "Error, expected password does not equal actual password.");
		
		String expectedPassword2 = "MaRyAnDeRsOn";
		String actualPassword2 = test2.getPassword();
		assertEquals(expectedPassword2, actualPassword2, "Error, expected password does not equal actual password.");
		
		int expectedSecurityCode1 = 1234;
		int actualSecurityCode1 = test1.getSecurityCode();
		assertEquals(expectedSecurityCode1, actualSecurityCode1, "Error, expected security code does not equal actual security code.");
		
		int expectedSecurityCode2 = 1111;
		int actualSecurityCode2 = test2.getSecurityCode();
		assertEquals(expectedSecurityCode2, actualSecurityCode2, "Error, expected security code does not equal actual security code.");
		
		boolean expectedLoggedIn1 = false;
		boolean actualLoggedIn1 = test1.isLoggedIn();
		assertEquals(expectedLoggedIn1, actualLoggedIn1, "Error, expected loggedIn does not equal actual loggedIn.");
		
		boolean expectedLoggedIn2 = false;
		boolean actualLoggedIn2 = test2.isLoggedIn();
		assertEquals(expectedLoggedIn2, actualLoggedIn2, "Error, expected loggedIn does not equal actual loggedIn.");
		
		int expectedRemainingLoginAttempts1 = 3;
		int actualRemainingLoginAttempts1 = test1.getRemainingLoginAttempts();
		assertEquals(expectedRemainingLoginAttempts1, actualRemainingLoginAttempts1, "Error, expected log in attempts does not equal actual log in attempts.");
		
		int expectedRemainingLoginAttempts2 = 3;
		int actualRemainingLoginAttempts2 = test2.getRemainingLoginAttempts();
		assertEquals(expectedRemainingLoginAttempts2, actualRemainingLoginAttempts2, "Error, expected log in attempts does not equal actual log in attempts.");
	}
	
	/**
	 * The following set of tests test the setters.
	 */
	@Test
	void TestSetter() {
		test1.setFirstName("Johny");
		String expectedFirstName1 = "Johny";
		String actualFirstName1 = test1.getFirstName();
		assertEquals(expectedFirstName1, actualFirstName1, "Error, expected first name does not equal actual first name.");
		
		test2.setFirstName("Martha");
		String expectedFirstName2 = "Martha";
		String actualFirstName2 = test2.getFirstName();
		assertEquals(expectedFirstName2, actualFirstName2, "Error, expected first name does not equal actual first name.");
		
		test1.setLastName("Smithy");
		String expectedLastName1 = "Smithy";
		String actualLastName1 = test1.getLastName();
		assertEquals(expectedLastName1, actualLastName1, "Error, expected last name does not equal actual last name.");
		
		test2.setLastName("Johnson");
		String expectedLastName2 = "Johnson";
		String actualLastName2 = test2.getLastName();
		assertEquals(expectedLastName2, actualLastName2, "Error, expected last name does not equal actual last name.");
		
		test1.setUserName("J_S");
		String expectedUserName1 = "J_S";
		String actualUserName1 = test1.getUserName();
		assertEquals(expectedUserName1, actualUserName1, "Error, expected username does not equal actual username.");
		
		test2.setUserName("M_J");
		String expectedUserName2 = "M_J";
		String actualUserName2 = test2.getUserName();
		assertEquals(expectedUserName2, actualUserName2, "Error, expected username does not equal actual username.");
		
		test1.setEmail("j_s@bham");
		String expectedEmail1 = "j_s@bham";
		String actualEmail1 = test1.getEmail();
		assertEquals(expectedEmail1, actualEmail1, "Error, expected email does not equal actual email.");
		
		test2.setEmail("m_j@bham");
		String expectedEmail2 = "m_j@bham";
		String actualEmail2 = test2.getEmail();
		assertEquals(expectedEmail2, actualEmail2, "Error, expected email does not equal actual email.");
		
		test1.setPassword("newpassword");
		String expectedPassword1 = "newpassword";
		String actualPassword1 = test1.getPassword();
		assertEquals(expectedPassword1, actualPassword1, "Error, expected password does not equal actual password.");
		
		test2.setPassword("marthaJohnson");
		String expectedPassword2 = "marthaJohnson";
		String actualPassword2 = test2.getPassword();
		assertEquals(expectedPassword2, actualPassword2, "Error, expected password does not equal actual password.");
		
		test1.setSecurityCode(9999);
		int expectedSecurityCode1 = 9999;
		int actualSecurityCode1 = test1.getSecurityCode();
		assertEquals(expectedSecurityCode1, actualSecurityCode1, "Error, expected security code does not equal actual security code.");
		
		test2.setSecurityCode(1862);
		int expectedSecurityCode2 = 1862;
		int actualSecurityCode2 = test2.getSecurityCode();
		assertEquals(expectedSecurityCode2, actualSecurityCode2, "Error, expected security code does not equal actual security code.");
		
		test1.setLoggedIn(true);
		boolean expectedLoggedIn1 = true;
		boolean actualLoggedIn1 = test1.isLoggedIn();
		assertEquals(expectedLoggedIn1, actualLoggedIn1, "Error, expected loggedIn does not equal actual loggedIn.");
		
		test2.setLoggedIn(true);
		boolean expectedLoggedIn2 = true;
		boolean actualLoggedIn2 = test2.isLoggedIn();
		assertEquals(expectedLoggedIn2, actualLoggedIn2, "Error, expected loggedIn does not equal actual loggedIn.");
		
		test1.setRemainingLoginAttempts(2);
		int expectedRemainingLoginAttempts1 = 2;
		int actualRemainingLoginAttempts1 = test1.getRemainingLoginAttempts();
		assertEquals(expectedRemainingLoginAttempts1, actualRemainingLoginAttempts1, "Error, expected log in attempts does not equal actual log in attempts.");
		
		test2.setRemainingLoginAttempts(0);
		int expectedRemainingLoginAttempts2 = 0;
		int actualRemainingLoginAttempts2 = test2.getRemainingLoginAttempts();
		assertEquals(expectedRemainingLoginAttempts2, actualRemainingLoginAttempts2, "Error, expected log in attempts does not equal actual log in attempts.");
	}
	
	/**
	 * The following set of tests test the isOnline method
	 */
	@Test
	void testIsOnline() {
		test1.setLoggedIn(true);
		test2.setLoggedIn(false);
		
		boolean expectedOnline1 = true;
		boolean actualOnline1 = test1.isOnline();
		assertEquals(expectedOnline1, actualOnline1, "Error, expected loggedIn does not equal actual loggedIn.");
		
		boolean expectedOnline2 = false;
		boolean actualOnline2 = test2.isOnline();
		assertEquals(expectedOnline2, actualOnline2, "Error, expected loggedIn does not equal actual loggedIn.");
	}
	
	/**
	 * The following set of tests test the logout method
	 */
	@Test
	void testLogout() {
		test1.setLoggedIn(true);
		test2.setLoggedIn(true);
		
		test1.logout();
		boolean expectedLoggedIn1 = false;
		boolean actualLoggedIn1 = test1.isLoggedIn();
		assertEquals(expectedLoggedIn1, actualLoggedIn1, "Error, expected loggedIn does not equal actual loggedIn.");
		
		test2.logout();
		boolean expectedLoggedIn2 = false;
		boolean actualLoggedIn2 = test2.isLoggedIn();
		assertEquals(expectedLoggedIn2, actualLoggedIn2, "Error, expected loggedIn does not equal actual loggedIn.");
	}
	
	/**
	 * The following set of tests test the deleteAccount method
	 */
	@Test
	void testDeleteAccount() {
		test1.deleteAccount();
		test2.deleteAccount();
		String expectedFirstName = null;
		String expectedLastName = null;
		String expectedUserName = null;
		String expectedEmail = null;
		String expectedPassword = null;
		int expectedSecurityCode = 0;
		
		String actualFirstName1 = test1.getFirstName();
		assertEquals(expectedFirstName, actualFirstName1, "Error, expected first name does not equal actual first name.");
		String actualLastName1 = test1.getLastName();
		assertEquals(expectedLastName, actualLastName1, "Error, expected last name does not equal actual last name.");
		String actualUserName1 = test1.getUserName();
		assertEquals(expectedUserName, actualUserName1, "Error, expected username does not equal actual username.");
		String actualEmail1 = test1.getEmail();
		assertEquals(expectedEmail, actualEmail1, "Error, expected email does not equal actual email.");
		String actualPassword1 = test1.getPassword();
		assertEquals(expectedPassword, actualPassword1, "Error, expected password does not equal actual password.");
		int actualSecurityCode1 = test1.getSecurityCode();
		assertEquals(expectedSecurityCode, actualSecurityCode1, "Error, expected security code does not equal actual security code.");
		
		String actualFirstName2 = test2.getFirstName();
		assertEquals(expectedFirstName, actualFirstName2, "Error, expected first name does not equal actual first name.");
		String actualLastName2 = test2.getLastName();
		assertEquals(expectedLastName, actualLastName2, "Error, expected last name does not equal actual last name.");
		String actualUserName2 = test2.getUserName();
		assertEquals(expectedUserName, actualUserName2, "Error, expected username does not equal actual username.");
		String actualEmail2 = test2.getEmail();
		assertEquals(expectedEmail, actualEmail2, "Error, expected email does not equal actual email.");
		String actualPassword2 = test2.getPassword();
		assertEquals(expectedPassword, actualPassword2, "Error, expected password does not equal actual password.");
		int actualSecurityCode2 = test2.getSecurityCode();
		assertEquals(expectedSecurityCode, actualSecurityCode2, "Error, expected security code does not equal actual security code.");
	}
	
	/**
	 * The following set of tests test the checkPassword method
	 */
	@Test
	void testCheckPassword() {
		boolean expected1 = true;
		boolean actual1 = test1.checkPassword("password");
		assertEquals(expected1, actual1, "Error, expected does not equal actual.");
		
		boolean expected2 = false;
		boolean actual2 = test2.checkPassword("password");
		assertEquals(expected2, actual2, "Error, expected does not equal actual.");
	}
	
	/**
	 * The following set of tests test the checkSecurityCode method
	 */
	@Test
	void testCheckSecurityCode() {
		boolean expected1 = false;
		boolean actual1 = test1.checkSecurityCode(0000);
		assertEquals(expected1, actual1, "Error, expected does not equal actual.");
		
		boolean expected2 = true;
		boolean actual2 = test2.checkSecurityCode(1111);
		assertEquals(expected2, actual2, "Error, expected does not equal actual.");
	}
	
	/**
	 * The following set of tests test the changePassword method
	 */
	@Test
	void testChangePassword() {
		String expected1 = "success";
		String actual1 = test1.changePassword("password", "newPassword");
		assertEquals(expected1, actual1, "Error, expected does not equal actual.");
		boolean expected1a = true;
		boolean actual1a = test1.checkPassword("newPassword");
		assertEquals(expected1a, actual1a, "Error, expected does not equal actual.");
		
		String expected2 = "failure";
		String actual2 = test2.changePassword("MaRyANDeRsOn", "newPassword");
		assertEquals(expected2, actual2, "Error, expected does not equal actual.");
		boolean expected2a = false;
		boolean actual2a = test2.checkPassword("newPassword");
		assertEquals(expected2a, actual2a, "Error, expected does not equal actual.");
		boolean expected2b = true; 
		boolean actual2b = test2.checkPassword("MaRyAnDeRsOn");
		assertEquals(expected2b, actual2b, "Error, expected does not equal actual.");
		
	}
	
	/**
	 * The following set of tests test the toString method
	 */
	@Test
	void testToString() {
		String expected1 = " [ John, Smith, Username: JS, Email Address: js@bham, Password: password, SecurityCode: 1234 ]";
		String actual1 = test1.toString();
		assertEquals(expected1, actual1, "Error, expected does not equal actual.");
		
		String expected2 = " [ Mary, Anderson, Username: M_Anderson, Email Address: ma@bham, Password: MaRyAnDeRsOn, SecurityCode: 1111 ]";
		String actual2 = test2.toString();
		assertEquals(expected2, actual2, "Error, expected does not equal actual.");
	}
	
}
