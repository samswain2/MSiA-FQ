import java.io.Serial;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class Monthly extends Appointment {
    @Serial
    private static final long serialVersionUID = 2835454391849154185L;
    public LocalDate date_start;
    public LocalDate date_end;

    public Monthly(String description) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("----------------------------------------");
                System.out.println("Please enter a valid starting appointment date in format dd/mm/yyyy");
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
                System.out.println("Please enter a valid ending appointment date in format mm/yyyy that is after the start date");
                System.out.println("----------------------------------------");
                String end = sc.nextLine();
                int day_ended = date_start.getDayOfMonth();
                String day_ended_string = String.valueOf(day_ended);
                if (day_ended < 10) day_ended_string = convert_under_ten_int_to_string(day_ended);
                end = day_ended_string + "/" + end;
                date_end = LocalDate.parse(end, formatter);
                if (!date_end.isBefore(date_start)) {
                    break;
                }
            } catch (Exception e) {
                System.out.println("----------------------------------------");
                System.out.println("That is not a valid date in the format mm/yyyy that is after the start date");
                System.out.println("----------------------------------------");
            }
        }
    }

    @Override
    public boolean occursOn(int year, int month, int day) {
        LocalDate date_check = ints_to_date(year, month, day);

        int monthsBetween = (int) ChronoUnit.MONTHS.between(
                YearMonth.from(date_start),
                YearMonth.from(date_end));

        LocalDate[] dates_in_period = new LocalDate[monthsBetween+1];

        for (int i = 0; i < monthsBetween; i++) {
            dates_in_period[i] = date_start.plusMonths(i);
        }

        dates_in_period[dates_in_period.length-1] = date_end;

        for (int i = 0; i < dates_in_period.length; i++){
            if (date_check.equals(dates_in_period[i])) {
                return true;
            }
        }
        return false;
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
