/** User.java - Object class for the user
 * @author xyg752
 * Rishabh Monga
 */
package application.model;
import java.util.Date;

public class User {
	//private variables
	private String firstName;
	private String lastName;
	private String emailAddress;
	private String lastMeeting;
	private String lastVolunteerEvent;
	private String totalVolunteerHours;
	private Integer meetings;
	private Integer volunteerEvents;

	public User(String firstName, String lastName, String emailAddress) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
	}
	
	public User(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = "";
	}

	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getEmailAddress() {
		if ( this.emailAddress.isEmpty() ) {
			return "";
		}
		return emailAddress;
	}
	
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getLastMeeting() {
		return lastMeeting;
	}

	public void setLastMeeting(String lastMeeting) {
		this.lastMeeting = lastMeeting;
	}
	
	public void setLatestMeetingToToday() {
		this.lastMeeting = new Date().toString();
	}

	public Integer getMeetings() {
		return meetings;
	}

	public void setMeetings(Integer meetings) {
		this.meetings = meetings;
	}
	
	public Integer getVolunteerEvents() {
		return volunteerEvents;
	}

	public void setVolunteerEvents(Integer volunteerEvents) {
		this.volunteerEvents = volunteerEvents;
	}

	public String getLastVolunteerEvent() {
		return lastVolunteerEvent;
	}

	public void setLastVolunteerEvent(String lastVolunteerEvent) {
		this.lastVolunteerEvent = lastVolunteerEvent;
	}
	
	public void setLastVolunteerEventToToday() {
		this.lastVolunteerEvent = new Date().toString();
	}

	public String getTotalVolunteerHours() {
		return totalVolunteerHours;
	}

	public void setTotalVolunteerHours(String totalVolunteerHours) {
		this.totalVolunteerHours = totalVolunteerHours;
	}
}
