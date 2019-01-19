/** User.java - Object class for the user
 * @author xyg752
 * Rishabh Monga
 */
package application.model;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Event {
	private String eventName;
	private String date;
	private String startTime;
	private String endTime;
	private Integer numberOfExpectedVolunteers;
	private String description;
	private ArrayList<Volunteer> volunteers;
	
	public Event( ) {
		this.volunteers = new ArrayList<Volunteer>();
	}
	
	public Event( String name, int numberOfExpectedVolunteers, String date, String startTime, String endTime, String description) {
		this.eventName = name;
		this.numberOfExpectedVolunteers = numberOfExpectedVolunteers;
		this.date = date;
		this.startTime = startTime;
		this.endTime = endTime;
		this.description = description;
		this.volunteers = new ArrayList<Volunteer>();
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public Integer getNumberOfExpectedVolunteers() {
		return numberOfExpectedVolunteers;
	}

	public void setNumberOfExpectedVolunteers(Integer numberOfExpectedVolunteers) {
		this.numberOfExpectedVolunteers = numberOfExpectedVolunteers;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	public void setDateToToday() {
		this.date = new Date().toString();
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ArrayList<Volunteer> getVolunteers() {
		return volunteers;
	}

	public void setVolunteers(ArrayList<Volunteer> volunteers) {
		this.volunteers = volunteers;
	}
	
	public int getNumberOfVolunteers() {
		return this.volunteers.size();
	}
	
	public void addVolunteer( Volunteer newVolunteer ) {
		this.volunteers.add(newVolunteer);
		return;
	}
	
	public void removeVolunteer( Volunteer oldVolunteer ) {
		this.volunteers.remove(oldVolunteer);
	}
	
	public Volunteer findVolunteer( String firstName, String lastName ) {
		for( Volunteer temp: volunteers ) {
			if( temp.getFirstName().equals(firstName) && temp.getLastName().equals(lastName)) {
				return temp;
			}
		}
		return null;
	}
	
	public void loadEvent(String filename) throws FileNotFoundException {
		File file = new File( filename );
		Scanner scan = new Scanner( file );
		
		if (!this.volunteers.isEmpty()) {
			this.volunteers.clear();
		}
		
		this.eventName = scan.nextLine();
		this.date = scan.nextLine();
		this.startTime = scan.nextLine();
		this.endTime = scan.nextLine();
		this.numberOfExpectedVolunteers = Integer.getInteger(scan.nextLine());
		this.description = scan.nextLine();
		
		while( scan.hasNextLine()) {
			String line = scan.nextLine();
			String [] tokens = line.split(",");
			
			if (tokens[0].equals("Volunteer First Name"))
				continue;
			
			Volunteer newVolunteer = new Volunteer(tokens[0], tokens[1]);
			newVolunteer.setStartTime(tokens[2]);
			newVolunteer.setEndTime(tokens[3]);
			
			addVolunteer(newVolunteer);
		}
		
		scan.close();
	}
	
	public void saveEvent( String filename ) throws IOException {
		File file = new File( filename );
		FileWriter printer = new FileWriter( file );
		
		printer.write(this.getEventName() + "\n" + this.date + "\n" 
					+ this.getStartTime() + "\n" + this.getEndTime() 
					+ "\n" + this.numberOfExpectedVolunteers + "\n"
					+ this.description + "\n" + "Volunteer First Name, Volunteer Last Name, Start Time, End Time\n"
					+ this.volunteersToString());

		printer.close();
	}
	
	public String volunteersToString() {
		String result = "";
		for( Volunteer temp : volunteers ) {
			result += temp.toString() + "\n";
		}
		return result;
	}
	
	public String displayVolunteers() {
		String result = "";
		for( Volunteer temp: volunteers ) {
			result += String.format("%-16s%-16s\t\t\t%-5s - %5s\n",temp.getFirstName(), temp.getLastName(), TimeComparer.returnPMFixed(temp.getStartTime()), TimeComparer.returnPMFixed(temp.getEndTime()) );
			//result += temp.getFirstName() + " " + temp.getLastName() + "\t\t\t" + temp.getStartTime() + temp.getEndTime() + "\n";
		}
		return result;
	}
	
}
