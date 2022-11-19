import java.io.Serial;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Daily extends Appointment {
    @Serial
    private static final long serialVersionUID = 5641060110501461883L;
    public LocalDate date_start;
    public LocalDate date_end;

    public Daily(String description) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("----------------------------------------");
                System.out.println("Please enter starting appointment date in format dd/mm/yyyy");
                System.out.println("----------------------------------------");
                String start = sc.nextLine();
                date_start = LocalDate.parse(start, formatter);
                break;
            } catch (Exception e) {
                System.out.println("----------------------------------------");
                System.out.println("That is not a valid date in the format dd/mm/yyyy");
                System.out.println("----------------------------------------");
            }
        }

        while (true) {
            try {
                System.out.println("----------------------------------------");
                System.out.println("Please enter a valid ending appointment date in format dd/mm/yyyy that is after the start date");
                System.out.println("----------------------------------------");
                String end = sc.nextLine();
                date_end = LocalDate.parse(end, formatter);
                if (!date_end.isBefore(date_start)) {
                    break;
                }
            } catch (Exception e) {
                System.out.println("----------------------------------------");
                System.out.println("That is not a valid date in the format dd/mm/yyyy");
                System.out.println("----------------------------------------");
            }
        }
    }

    @Override
    public boolean occursOn(int year, int month, int day) {
        LocalDate date_check = ints_to_date(year, month, day);
        return (!date_check.isBefore(date_start)) && (date_check.isBefore(date_end.plusDays(1)));
    }

    public LocalDate ints_to_date(int year, int month, int day) {
        String day_string = String.valueOf(day);
        String month_string = String.valueOf(month);
        String year_string = String.valueOf(year);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        if (month < 10) month_string = convert_under_ten_int_to_string(month);
        if (day < 10) day_string = convert_under_ten_int_to_string(day);

        String date_string = day_string + "/" + month_string + "/" + year_string;
        return LocalDate.parse(date_string, formatter);
    }

    public String convert_under_ten_int_to_string(int time) {
        return "0" + time;
    }

}
