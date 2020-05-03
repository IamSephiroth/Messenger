import java.io.IOException;
import java.io.OutputStream;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Scanner;

/**
 * This class is to test generating JSON format from user input.
 * Each command has its own class to specify data required.
 * TODO restrict the format of the data members. i.e. email has to be []
 * 
 * @author Miyo Takahashi
 * @version 2020-05-02
 */
class LogIn {//implements CtoS {
	private String command;
	private String username;
	private String password;
}

class LogOut {//implements CtoS {
	private String command;
}

class ChangeUsername {//implements CtoS {
	private String command;	
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
		/*
		 * Create an instance of SignUp to make JSON format String.
		 * Pass this string to the server to communicate.
		 */
		SignUp su = new SignUp();
		Scanner s = new Scanner(System.in);
		System.out.println("first name:");
		su.setFirstName(s.next());
		System.out.println("last name:");
		su.setLastName(s.next());
		System.out.println("username:");
		su.setUsername(s.next());
		System.out.println("email address:");
		su.setEmail(s.next());
		System.out.println("password:");
		su.setPassword(s.next());
		s.close();

		ObjectMapper om = new ObjectMapper();
		try {
			String json = om.writeValueAsString(su);
			System.out.println(json);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
}
//define interface of client to server / server to client messages
//have 