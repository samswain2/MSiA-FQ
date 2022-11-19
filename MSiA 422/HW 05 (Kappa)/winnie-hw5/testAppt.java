package winnie-hw5;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;


public class testAppt {

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		ArrayList<Appointment> appointments = new ArrayList<Appointment>();
		System.out.println("Welcome to the Calendar!!");
		System.out.println();
		while (true) {
			System.out.println();
			System.out.println("Please choose: ");
			System.out.println("A - Add Appointment");
			System.out.println("B - Save Appointment");
			System.out.println("C - Check Appointment");
			System.out.println("E - Exit");
			String choice = in.nextLine();
			if (choice.toUpperCase().equals("A")) {
				System.out.println("Please Give me a Description of this Appointment!");
				String description = in.nextLine();
				while (true) {
					System.out.println("Please Choose Your Appointment Type! " +
									"(Onetime/ Daily/ Monthly): ");
					String type = in.nextLine();
					if (type.toLowerCase().equals("onetime") ||
						type.toLowerCase().equals("daily") ||
						type.toLowerCase().equals("monthly")) {
						appointments.add(addAppointment(in, type, description));
						break;
					} else {
						System.out.println("Please Type in Valid Appointment Type!!!");
					}
				}
			} else if (choice.toUpperCase().equals("B")) {
				saveAppointment(appointments);
			} else if (choice.toUpperCase().equals("C")) {
				checkAppointment(in, appointments);
			} else if (choice.toUpperCase().equals("E")) {
				break;
			} else {
				System.out.println("Please Give Valid Choice!!!");
			}
			
		}
	}
	
	// pre: takes in a scanner, the type of appointment, and the description of appointment
	// post: return an appointment based on user input.
	public static Appointment addAppointment(Scanner in, String type, String description) {
		type = type.toLowerCase();
		if (type.equals("onetime")) {
			while (true) {
				System.out.println("Please Give the Date in MM/DD/YYYY format (eg. 03/01/2021): ");
				String date = in.nextLine();
				if (date.matches("\\d{2}/\\d{2}/\\d{4}")) {
					try {
						int length = date.length();
						LocalDate temp = LocalDate.parse(date.substring(length-4, length) + "-" +
								date.substring(0,2) + "-" + date.substring(3,5));
						return new Onetime(description, date);
					} catch (Exception e) {
						System.out.println("Please enter valid day!");
					}
				} else {
					System.out.println("Please Give me Date in Required Format!!!!");
				}
			}
		} else if (type.equals("daily")) {
			while (true) {
				System.out.println("Please Give the Start Date in MM/DD/YYYY format (eg. 03/01/2021): ");
				String start_date = in.nextLine();
				System.out.println("Please Give the Start Date in MM/DD/YYYY format (eg. 03/01/2021): ");
				String end_date = in.nextLine();
				if (start_date.matches("\\d{2}/\\d{2}/\\d{4}") &&
					end_date.matches("\\d{2}/\\d{2}/\\d{4}")) {
					try {
						int length = start_date.length();
						LocalDate before = LocalDate.parse(start_date.substring(length-4, length) + "-" +
											start_date.substring(0,2) + "-" + start_date.substring(3,5));
						String after = end_date.substring(length-4, length) + "-" +
								end_date.substring(0,2) + "-" + end_date.substring(3,5);
						LocalDate end = LocalDate.parse(after);
						if (before.isBefore(end)) {
							return new Daily(description, start_date, end_date);
						} else {
							System.out.println("Error! Start date should be before end date!!!");
						}
					} catch (Exception e) {
						System.out.println("Invalid Date time! Please enter again!");
					}
				} else {
					System.out.println("Please Give me Date in Required Format!!!!");
				}
			}
		} else {
			int day = 0;
			int start_month = 0;
			int start_year = 0;
			int end_month = 0;
			int end_year = 0;
			
			while (true) {
			    try {
			    	System.out.println("Please enter the day: ");
			    	day = Integer.parseInt(in.nextLine());
			    	System.out.println("Please enter the start_month: ");
			    	start_month = Integer.parseInt(in.nextLine());
			    	System.out.println("Please enter the start_year: ");
			    	start_year = Integer.parseInt(in.nextLine());
			    	System.out.println("Please enter the end_month: ");
			    	end_month = Integer.parseInt(in.nextLine());
			    	System.out.println("Please enter the end_year: ");
			    	end_year = Integer.parseInt(in.nextLine());
			    } catch (NumberFormatException ignore) {
			        System.out.println("Invalid input");
			    }
			    
			    if (start_month > 12 || end_month > 12) {
			    	System.out.println("Invalid Month number! Please enter again!");
			    } else {
			    	if (end_year == start_year) {
				    	if (start_month < end_month) {
				    		return new Monthly(description, day, start_month, start_year,
					   				end_month, end_year);
				    	} else {
				    		System.out.println("Error! Start month should before end month! Please enter again!");
				    	}
				    } else if (end_year > start_year) {
				    	return new Monthly(description, day, start_month, start_year,
				   				end_month, end_year);
				    } else {
				    	System.out.println("Error! Start year should before end year! Please enter again!");
				    }
			    }
			}
		}
	}
	
	// pre: takes in a list of appointments
	// post: save them to a txt file
	public static void saveAppointment(ArrayList<Appointment> appointments) throws IOException {
		if (appointments.size() == 0) {
			System.out.println("There's no appointment in the system!");
		} else {
			FileWriter myWriter = new FileWriter("appointments.txt");
			for (int i = 0; i < appointments.size(); i++) {
				myWriter.write(appointments.get(i) + "\n");
				myWriter.write("\n");
			}
			myWriter.close();
		}
	}
	
	// pre: takes in a sanner and a list of appointments
	// post: print out any appointment on a given day
	public static void checkAppointment(Scanner in, ArrayList<Appointment> appointments) {
		if (appointments.size() == 0) {
			System.out.println("There's no appointment in the system!");
		} else {
			int day = 0;
			int month = 0;
			int year = 0;
			while (true) {
				try {
					System.out.println("Please enter the day: ");
					day = Integer.parseInt(in.nextLine());
					System.out.println("Please enter the month: ");
					month = Integer.parseInt(in.nextLine());
					System.out.println("Please enter the year: ");
					year = Integer.parseInt(in.nextLine());
					break;
				} catch(NumberFormatException e) {
					System.out.println("Invalid input");
				}
			}
			for (int i = 0; i < appointments.size(); i++) {
				if (appointments.get(i).occursOn(year, month, day)) {
					System.out.println(appointments.get(i));
					System.out.println();
				}
			}
		}
	}
}
