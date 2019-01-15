package application.model;

public class TimeComparer {
	public static boolean compareTime(String time1, String time2) {
		//TODO:
		return true;
	}
	
	public static String checkPM( String time ) {
		String [] currentTime = time.split(":");
		int hours = Integer.parseInt(currentTime[0]);
		if ( hours > 12 ) {
			hours -= 12;
		}
		currentTime[0] = String.valueOf(hours);
		return currentTime[0] + ":" + currentTime[1];
	}
}
