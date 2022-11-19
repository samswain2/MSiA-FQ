import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class Appointment implements Serializable {

    static ArrayList<Appointment> appointments = new ArrayList<Appointment>();
    protected String description;

    public Appointment(String description) {
        this.description = description;
    }

    public static void main(String[] args) {
        int menu_action = 0;

        while (menu_action != 4) {
            menu_action = get_initial_action();
            perform_menu_action(menu_action);
            System.out.println("----------------------------------------");
            System.out.println("Appointments List:");
            System.out.println(appointments);
            System.out.println("----------------------------------------");
        }
    }

    private static void perform_menu_action(int menu_action_type) {
        if (menu_action_type == 1) {
            customize_appointments();
        } else if (menu_action_type == 2) {
            save_load_apt();
        } else if (menu_action_type == 3) {
            Scanner sc = new Scanner(System.in);

            int day = 1;
            int month = 1;
            int year = 2022;

            while (true) {
                System.out.println("----------------------------------------");
                System.out.println("Please enter a valid date for the items below:");
                System.out.println("----------------------------------------");
                System.out.println("Please enter a day");
                day = sc.nextInt();
                System.out.println("Please enter a month");
                month = sc.nextInt();
                System.out.println("Please enter a year");
                year = sc.nextInt();
                System.out.println("----------------------------------------");
                System.out.println("Appointments on: " + day + "/" + month + "/" + year);
                System.out.println("----------------------------------------");

                try {
                    for (int i = 0; i < appointments.size(); i++) {
                        if (appointments.get(i).occursOn(year, month, day)) {
                            System.out.println("Appointment Description: " + appointments.get(i).description);
                            System.out.println("Class ID: " + appointments.get(i));
                            if (!(i == appointments.size() - 1)) {
                                System.out.println("----------------------------------------");
                            }
                        }
                    }
                    break;
                } catch (Exception e) {
                    System.out.println("----------------------------------------");
                    System.out.println("Those numbers do not make up a valid dd/mm/yyyy");
                    System.out.println("----------------------------------------");
                }

            }

        } else if (menu_action_type != 4) {
            System.out.println("Please enter a valid starting option from the menu");
            System.out.println("----------------------------------------");
        }
    }

    private static void save_load_apt() {
        Scanner sc = new Scanner(System.in);
        System.out.println("----------------------------------------");
        System.out.println("Save or load appointments?");
        System.out.println("***To load file please place this java file in the same location as a saved file named Schedule_Data.txt***");
        System.out.println("1: Save");
        System.out.println("2: Load");
        System.out.println("----------------------------------------");
        int save_or_load = sc.nextInt();

        while (true) {
            if (save_or_load == 1) {
                try {
                    System.out.println("----------------------------------------");
                    writeFile();
                    System.out.println("Wrote to Schedule_Data.txt");
                    break;
                } catch (IOException e) {
                    System.out.println("----------------------------------------");
                    System.out.println("Something went wrong while writing the file");
                    System.out.println("----------------------------------------");
                    break;
                }
            } else if (save_or_load == 2) {

                try {
                    readFile();
                    System.out.println("----------------------------------------");
                    System.out.println("Read file Schedule_Data.txt");
                    break;
                } catch (IOException | ClassNotFoundException e) {
                    System.out.println("----------------------------------------");
                    System.out.println("Something went wrong while reading the file");
                    System.out.println("----------------------------------------");
                    break;
                }
            } else {
                System.out.println("----------------------------------------");
                System.out.println("Please type 1 (Save) or 2 (Load) only from the save or load appointments menu above");
                System.out.println("----------------------------------------");
            }
        }
    }

    private static void writeFile() throws IOException {
        FileOutputStream fos = new FileOutputStream("Schedule_Data.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(appointments);
        oos.close();
    }

    private static void readFile() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("Schedule_Data.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);
        Appointment.appointments = (ArrayList<Appointment>) ois.readObject();
        ois.close();
    }

    private static int get_initial_action() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please select an option from the menu below:");
        System.out.println("1: Add new appointment");
        System.out.println("2: Save/Load appointments");
        System.out.println("3: Check for appointments on a date");
        System.out.println("4: Exit System");
        System.out.println("----------------------------------------");
        return sc.nextInt();
    }

    private static void customize_appointments() {
        Scanner sc_str = new Scanner(System.in);
        Scanner sc_int = new Scanner(System.in);
        while (true) {
            System.out.println("----------------------------------------");
            System.out.println("Please enter a description of your appointment(s)");
            System.out.println("----------------------------------------");
            String apt_desc = sc_str.nextLine();
            Appointment desc = new Appointment(apt_desc);
            System.out.println("----------------------------------------");
            System.out.println("Please select frequency of appointments");
            System.out.println("1: Once");
            System.out.println("2: Daily");
            System.out.println("3: Monthly");
            System.out.println("----------------------------------------");
            int apt_type = sc_int.nextInt();

            if (apt_type == 1) {
                System.out.println("----------------------------------------");
                System.out.println("Chose Onetime apt");
                appointments.add(new Onetime(desc.description));
                break;
            } else if (apt_type == 2) {
                System.out.println("----------------------------------------");
                System.out.println("Chose Daily apt");
                appointments.add(new Daily(desc.description));
                break;
            } else if (apt_type == 3) {
                System.out.println("----------------------------------------");
                System.out.println("Chose Monthly apt");
                appointments.add(new Monthly(desc.description));
                break;
            } else {
                System.out.println("----------------------------------------");
                System.out.println("Please choose an option from the frequency menu above");
                System.out.println("----------------------------------------");
            }
        }

    }

    public boolean occursOn(int year, int month, int day) {
        return false;
    }
}