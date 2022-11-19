package winnie-hw5;

/*
 * This is a daily object that records appointments that happen everyday.
 * */


public class Daily extends Appointment {
	
	private String start_date;
	private String end_date;
	private int start_year;
	private int start_month;
	private int start_day;
	private int end_year;
	private int end_month;
	private int end_day;
	
	public Daily(String description, String start_date, String end_date) {
		super(description);
		this.start_date = start_date;
		this.end_date = end_date;
		int length_start = start_date.length();
		this.start_year = Integer.parseInt(start_date.substring(length_start-4, length_start));
		this.start_day = Integer.parseInt(start_date.substring(3, 5));
		this.start_month = Integer.parseInt(start_date.substring(0, 2));
		int length_end = end_date.length();
		this.end_year = Integer.parseInt(end_date.substring(length_end-4, length_end));
		this.end_day = Integer.parseInt(end_date.substring(3, 5));
		this.end_month = Integer.parseInt(end_date.substring(0, 2));
	}
	
	@Override
	public String toString() {
		return "Daily Appointment Description: " + super.description 
				+ "\n" + "Start Date: " + this.start_date + "\n" + "End Date: " + this.end_date;
	}
	
	@Override
	// pre: takes in given year, month, and day
	// post: returns true if the given day is in the range of start_date and end_date
	//       otherwise returns false.
	public boolean occursOn(int year, int month, int day) {
		if (year == this.start_year) {
			if (month > this.start_month) {
				return true;
			} else if (this.start_month == month) {
				return day >= this.start_day;
			} else {
				return false;
			}
		} else if ((year > this.start_year) && (year < this.end_year)) {
			return true;
		} else if (year == this.end_year) {
			if (month < this.end_month) {
				return true;
			} else if (month == this.end_month) {
				return day <= this.end_day;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}
