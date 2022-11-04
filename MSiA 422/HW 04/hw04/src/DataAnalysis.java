import java.awt.*;
import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;

import static java.lang.Math.round;

public class DataAnalysis {

    //  Init row and column numbers
    private static final int ROWS = 14;
    private static final int COLUMNS = 3;

    public static void main(String[] args) throws FileNotFoundException {

//      Read file
        String[][] data = readFile();
//      Get salary array
        List<Integer> salary = get_salary_arr(data);
        double mean = get_mean(salary);
//      Get array of employee ages
        List<Integer> ages = get_age_list(data);

//      Print all results asked for in homework 04
        System.out.println("Info about this file:");
        System.out.println("-----------------------------------");
        System.out.println("1. There are " + salary.size() + " employees in this file.");
        System.out.println("2. " + get_highest(data, salary) + " has the highest salary.");
        System.out.println("3. The average salary is " + round(mean) + " dollars.");
        System.out.println("4. " + above_ave(salary, mean) + " employees make above the average.");
        System.out.println("5. The average age of employees is " + round(get_mean(ages)) + ".");

    }

    private static List<Integer> get_age_list(String[][] array) {
//      Init temp array to be filled in later
        List<Integer> temp = Arrays.asList(new Integer[array.length]);
//      Fill in temp array with salaries from data
        for (int i = 0; i < array.length; i++) {
            temp.set(i, get_age(array[i][1]));
        }
        return temp;
    }

    //  Get age from date given in dataframe
    public static int get_age(String input) {
//      Set date format, get date from input
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate date = LocalDate.parse(input, formatter);
        LocalDateTime now = LocalDateTime.now();

        return Period.between(date, LocalDate.from(now)).getYears();
//        return now;
    }

    //    Get count of above average employee salaries
    private static int above_ave(List<Integer> array, double mean) {

        int a = 0;
//      Check if each salary is above mean
        for (Integer integer : array) {
            if (integer > mean) {
                a += 1;
            }
        }
        return a;
    }

    //    Get array of just salaries
    private static List<Integer> get_salary_arr(String[][] array) {
//      Init temp array to be filled in later
        List<Integer> temp = Arrays.asList(new Integer[array.length]);
//      Fill in temp array with salaries from data
        for (int i = 0; i < array.length; i++) {
            temp.set(i, Integer.parseInt(array[i][2]));
        }
        return temp;
    }

    //    Get highest salary
    private static String get_highest(String[][] data, List<Integer> salary) {

        int m = 0;
//      Check for highest salary index
        for (int i = 0; i < data.length; i++) {
            if (salary.get(i) > salary.get(m)) {
                m = i;
            }
        }
        return data[m][0];
    }

    //    Get mean of salaries
    private static double get_mean(List<Integer> array) {

        double m = 0;
//      Add all numbers in input array
        for (Integer integer : array) {
            m += integer;
        }
        return m / array.size();
    }

    //    Read selected file
    private static String[][] readFile() throws FileNotFoundException {
//      Init data array
        String[][] numArray = new String[ROWS][COLUMNS];
        Scanner sc = new Scanner(chooseTextFile());
//      read all lines in file
        while (sc.hasNextLine()) {
            for (int i = 0; i < numArray.length; i++) {
//              Split line in file by commas
                String[] line = sc.nextLine().trim().split(",");
                for (int j = 0; j < line.length; j++) {
//                  Save values in the initialized array
                    numArray[i][j] = String.valueOf(line[j]);
                }
            }
        }
        return numArray;
    }

    //    Choose file to be read
    private static File chooseTextFile() {
//      Pop up dialog box to select file
        FileDialog dialog = new FileDialog((Frame) null, "Select File To Open");
        dialog.setMode(FileDialog.LOAD);
        dialog.setVisible(true);
//      Get selected file
        File[] file = dialog.getFiles();
        return file[0];
    }
}
