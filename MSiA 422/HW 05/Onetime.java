import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Onetime extends Appointment{

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    LocalDate get_daily() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter Onetime appointment date in format dd/mm/yyyy");
        String daily = sc.next();
        return LocalDate.parse(daily, formatter);
    }

}
