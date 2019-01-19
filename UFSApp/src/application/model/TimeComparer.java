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
	
	public static String returnPMunfixed( String time1, String time2 ) {
		String [] startTime = time1.split(":");
		String [] endTime = time2.split(":");
		
		int hours1 =  Integer.parseInt(startTime[0].trim());
		int minutes1 = Integer.parseInt(startTime[1].trim());
		int hours2 =  Integer.parseInt(endTime[0].trim());
		int minutes2 = Integer.parseInt(endTime[1].trim());
		int totalHours = hours2 - hours1;
		int totalMinutes;
		
		if ( hours2 < hours1 ) {
			hours2+=12;
		}
		
		return hours2 + ":" + minutes2;
	}
	
	public static String addTotalHours( String oldTotal, String moreTime ) {
		String sum = "";
		
		String [] time1 = oldTotal.split(":");
		String [] time2 = moreTime.split(":");
		
		int hours1 =  Integer.parseInt(time1[0].trim());
		int minutes1 = Integer.parseInt(time1[1].trim());
		int hours2 =  Integer.parseInt(time2[0].trim());
		int minutes2 = Integer.parseInt(time2[1].trim());
		
		int totalHours = hours2 + hours1;
		int totalMinutes = minutes1 + minutes2;
		
		if ( totalMinutes > 60 ) {
			totalMinutes-= 60;
			totalHours++;
		}
		
		sum = totalHours + ":" + totalMinutes;
		
		return sum;
	}
	
	public static String countHours( String time1, String time2 ) {
		String difference = "";
		
		String [] startTime = time1.split(":");
		String [] endTime = time2.split(":");
		
		int hours1 =  Integer.parseInt(startTime[0].trim());
		int minutes1 = Integer.parseInt(startTime[1].trim());
		int hours2 =  Integer.parseInt(endTime[0].trim());
		int minutes2 = Integer.parseInt(endTime[1].trim());
		int totalHours = hours2 - hours1;
		int totalMinutes;
		
		if ( minutes1 > minutes2 ) {
			totalMinutes = 60 + minutes2 - minutes1;
			totalHours--;
		} else {
			totalMinutes = minutes2 - minutes1;
		}
		
		difference = totalHours + ":" + totalMinutes;
		
		return difference;
	}
}
