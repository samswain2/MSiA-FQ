package winnie-hw5;

/*
 * This is a monthly object that records appointments that happen monthly
 * */

public class Monthly extends Appointment {
	
	private int day;
	private int start_month;
	private int start_year;
	private int end_month;
	private int end_year;
	
	
	public Monthly(String description, int day, int start_month,
			int start_year, int end_month, int end_year) {
		super(description);
		this.day = day;
		this.start_month = start_month;
		this.start_year = start_year;
		this.end_month = end_month;
		this.end_year = end_year;
	}
	
	@Override
	public String toString() {
		return "Monthly Appointment Description: " + super.description + "\n" 
				+ "Day: " + this.day + "\n" 
				+ "Start Month: " + this.start_month + "\n"
				+ "Start Year: " + this.start_year + "\n"
				+ "End Month: " + this.end_month + "\n"
				+ "End Year: " + this.end_year;
	}
	
	@Override
	// pre: takes in month, year, and day
	// post: returns true if the given date is in range monthly
	//       otherwise return false
	public boolean occursOn(int year, int month, int day) {
		if (year == this.start_year) {
			if (month >= this.start_month) {
				return day == this.day;
			} else {
				return false;
			}
		} else if (year > this.start_year && year < this.end_year) {
			return day == this.day;
		} else if (year == this.end_year) {
			if (month <= this.start_month) {
				return day == this.day;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
}
