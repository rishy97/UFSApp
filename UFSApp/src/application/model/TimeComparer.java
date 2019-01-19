package application.model;

public class TimeComparer {
	
	public static String returnPMFixed( String time ) {
		String [] currentTime = time.split(":");
		if (currentTime[0].trim().equals("??")) {
			return currentTime[0] + ":" + currentTime[1];
		}
		int hours = Integer.parseInt(currentTime[0].trim());
		if ( hours > 12 )
			hours -= 12;
		currentTime[0] = String.valueOf(hours);
		return currentTime[0] + ":" + currentTime[1];
	}
}
