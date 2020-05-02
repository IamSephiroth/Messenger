import java.io.IOException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Scanner;

/**
 * this class is to test JSON.
 * Each command had its own class.
 * 
 * @author Miyo Takahashi
 * @version 2020-05-02
 */
class SignUp {
	public String command;
	public String firstName;
	public String lastName;
	public String username;
	public String email;
	public String password;
}

class LogIn {
	public String command;
	public String username;
	public String password;
}

class LogOut {
	public String command;
}

class ChangeUsername {
	public String command;	
}

public class JSONtest {
	public static void main(String[] args) {
		command();
		/*
		 * Depending on the command, ask for corresponding data.
		 */
		
	}
	public static void command() {
		/*
		 * ask for command
		 */
		System.out.println("What do you want to do? pick a number:\n"
				+ "1. sign up\n"
				+ "2. log in\n"
				+ "3. log out\n"
				+ "4. send one to one message\n"
				+ "5. more functions comming...\n");
		Scanner s = new Scanner(System.in);
		String command = s.next();
		
		switch(command) {
			case "1":
				// ask for personal info and send to the server
				signUp();
				break;
			case "2":
				logIn();
				break;
			default:
				System.out.println("Error! input a digit from 1 to 4.");
		}
	}
	private static void logIn() {
		// TODO Auto-generated method stub
		
	}
	private static void signUp() {
		Scanner s = new Scanner(System.in);
		System.out.println("first name:");
		String firstName = s.next();
		System.out.println("last name:");
		String lastName = s.next();
		System.out.println("username:");
		String username= s.next();
		System.out.println("email address:");
		String email = s.next();
		System.out.println("password:");
		String password = s.next();
				
		/*
		 * Create an instance of SignUp to make JSON format String.
		 * Pass this string to the server to communicate.
		 */
		SignUp su = new SignUp();
		su.command = "sign up";
		su.firstName = firstName;
		su.lastName = lastName;
		su.username = username;
		su.email = email;
		su.password = password;
		
		ObjectMapper om = new ObjectMapper();
		try {
			String json = om.writeValueAsString(su);
			System.out.println(json);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		/*
		 * I guess the above is better way to generate JSON message?
		 */
		String script = "{\"firstName\": \"" + firstName + "\","
			   	 + "\"lastName\": \"" + lastName + "\","
			     + "\"username\": \"" + username + "\","
			     + "\"email\": \"" + email + "\","
			     + "\"password\": \"" + password + "\"}";

	}
}