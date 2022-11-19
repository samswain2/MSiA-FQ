import java.io.Serial;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Onetime extends Appointment {
    @Serial
    private static final long serialVersionUID = 7207226617847630914L;
    public LocalDate apt_date;
    public int day;
    public int month;
    public int year;
    public Onetime(String description) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("----------------------------------------");
                System.out.println("Please enter Onetime appointment date in format dd/mm/yyyy");
                System.out.println("----------------------------------------");
                String once = sc.nextLine();
                apt_date = LocalDate.parse(once, formatter);
                break;
            } catch (Exception e) {
                System.out.println("----------------------------------------");
                System.out.println("That is not a valid date in the format dd/mm/yyyy");
                System.out.println("----------------------------------------");
            }
        }
        year = apt_date.getYear();
        month = apt_date.getMonthValue();
        day = apt_date.getDayOfMonth();
    }

    @Override
    public boolean occursOn(int year, int month, int day) {
        return (this.year == year) && (this.month == month) && (this.day == day);
    }
}
