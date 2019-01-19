/** User.java - Object class for the user
 * @author xyg752
 * Rishabh Monga
 */
package application.model;
import java.util.Date;

public class Volunteer {
	//private variables
	private String firstName;
	private String lastName;
	private String startTime;
	private String endTime;
	
	public Volunteer( String firstName, String lastName ) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.startTime = new Date().toString().substring(11, 16);
		this.endTime = "??:??";
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
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public void setStartTimeToNow() {
		this.startTime = new Date().toString().substring(11, 16);
		this.endTime = "??:??";
	}
	public void setEndTimeToNow() {
		this.endTime = new Date().toString().substring(11, 16);
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	public String toString() {
		String result = "";
		result += this.firstName + "," + this.lastName + "," + this.getStartTime() + "," + this.getEndTime();
		return result;
	}
	
}
