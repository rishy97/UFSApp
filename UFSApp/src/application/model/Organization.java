package application.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class Organization {
	private HashMap<String, User> users;
	private String name;
	
	public Organization(String name) {
		this.name = name;
		users = new HashMap<String, User>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void addUser(String firstName, String lastName) {
		if ( this.findUser(firstName, lastName) == null )
			this.users.put(firstName.toLowerCase() + "+" + lastName.toLowerCase(), new User(firstName.toLowerCase(), lastName.toLowerCase()));
	}
	
	public void addUser(String firstName, String lastName, String email ) {
		if ( this.findUser(firstName, lastName) == null )
			this.users.put(firstName.toLowerCase() + "+" + lastName.toLowerCase(), new User(firstName.toLowerCase(), lastName.toLowerCase(), email.toLowerCase()));
	}
	
	public User findUser( String firstName, String lastName ) {
		for( User temp : users.values()) {
			if ( temp.getFirstName().equals(firstName.toLowerCase()) && temp.getLastName().equals(lastName.toLowerCase())) {
				return temp;
			}
		}
		
		return null;
	}
	
	public String getEmailAddreses() {
		String result = "";
		
		if ( users.values().isEmpty() ) {
			return "";
		}
		
		for( User temp: users.values() ) {
			result += temp.getEmailAddress() + "\n";
		}
		return result;
	}
	
	public void loadUsers( String filename ) throws FileNotFoundException {
		File file = new File( filename );
		Scanner scan = new Scanner( file );
		
		while( scan.hasNextLine()) {
			String line = scan.nextLine();
			String [] tokens = line.split(",");
			
			if ( tokens[0].equals("First Name") ) {
				continue;
			}
			
			User newUser = new User(tokens[0], tokens[1], tokens[2]);
			newUser.setLastMeeting(tokens[5]);
			newUser.setLastVolunteerEvent(tokens[6]);
			newUser.setMeetings(Integer.parseInt(tokens[3]));
			newUser.setVolunteerEvents(Integer.parseInt(tokens[4]));
			String username = tokens[0] + "+" + tokens[1];
//			System.out.println(username + "\n" + newUser.toString());
			
			users.put(username, newUser);
		}
		
		scan.close();
	}
	
	public void saveUsers( String filename ) throws IOException {
		File file = new File( filename );
		FileWriter printer = new FileWriter( file );
		
		printer.write( this.toString() );

		printer.close();
	}
	
	public String toString() {
		String result = "First Name,Last Name,Email Address,Meeting Count,Volunteer Event Count,Last Meeting,Last Volunteer Event\n";
		
		for( User temp : users.values() ) {
			result += temp.getFirstName().toLowerCase() + "," + temp.getLastName().toLowerCase() + "," + temp.getEmailAddress().toLowerCase()  + "," + temp.getMeetings() + "," + temp.getVolunteerEvents() + "," + temp.getLastMeeting() + "," + temp.getLastVolunteerEvent() + "\n";
		}
		
		return result;
	}
}
