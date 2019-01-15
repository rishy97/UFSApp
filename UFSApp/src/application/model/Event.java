/** User.java - Object class for the user
 * @author xyg752
 * Rishabh Monga
 */
package application.model;
import java.io.File;
import java.io.FileNotFoundException;
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
		
		if ( this.numberOfExpectedVolunteers < this.volunteers.size()  ) {
			this.numberOfExpectedVolunteers = this.volunteers.size();
		}
		return;
	}
	
	public void removeVolunteer( Volunteer oldVolunteer ) {
		this.volunteers.remove(oldVolunteer);
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
			
			Volunteer newVolunteer = new Volunteer(tokens[0], tokens[1]);
			newVolunteer.setStartTime(tokens[2]);
			newVolunteer.setEndTime(tokens[3]);
			
			addVolunteer(newVolunteer);
		}
		
		scan.close();
	}
	
}
