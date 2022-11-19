package winnie-hw5;

/*
 * This is an onetime object which records appointments that only happen once.
 * */

public class Onetime extends Appointment{
	
	private String date;
	private int year;
	private int month;
	private int day;
	
	public Onetime(String description, String date) {
		super(description);
		this.date = date;
		int length = date.length();
		this.year = Integer.parseInt(date.substring(length-4, length));
		this.day = Integer.parseInt(date.substring(3, 5));
		this.month = Integer.parseInt(date.substring(0, 2));
	}
	
	@Override
	public String toString() {
		return "One time Appointment Description: " + this.description + "\n" + "Date: " + this.date;
	}
	
	@Override
	// pre: takes in year, month, and day
	// post: return true if the given day is on the appointment day, otherwise return false
	public boolean occursOn(int year, int month, int day) {
		return (this.year == year) && (this.month == month) && (this.day == day);
	}
}
