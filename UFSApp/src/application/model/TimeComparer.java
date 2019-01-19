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
	
	public static String countHours( String time1, String time2 ) {
		String difference = "";
		
		String [] startTime = time1.split(":");
		String [] endTime = time2.split(":");
		
		int hours1 =  Integer.parseInt(startTime[0].trim());
		int seconds1 = Integer.parseInt(startTime[1].trim());
		
		int hours2 =  Integer.parseInt(endTime[0].trim());
		int seconds2 = Integer.parseInt(endTime[1].trim());
		
		
		
		return difference;
	}
}
