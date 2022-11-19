package winnie-hw5;

/*
 * This is a abstract class has two method: toString and occursOn
 * */


public abstract class Appointment {
	
	protected String description;
	
	public Appointment(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Appointment Description: " + description;
	}
	
	
	// pre: takes in year, month, and day
	// post: return true if the date given is in range of the appointment
	//       otherwise return false
	public boolean occursOn(int year, int month, int day) {
		return false;
	}
}
