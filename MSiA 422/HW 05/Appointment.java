import java.util.Scanner;

public class Appointment {

    public static void main(String[] args) {
        int menu_action = 0;

        while (menu_action != 4) {
            menu_action = get_initial_action();
            perform_menu_action(menu_action);
        }
    }

    private static void perform_menu_action(int menu_action_type) {
        if (menu_action_type == 1) {
            freq_choice();
        } else if (menu_action_type == 2) {
            save_load_apt();
        } else if (menu_action_type == 3) {
            System.out.println("This is all appointments");
        }
    }

    private static void save_load_apt() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Save or load appointments?");
        System.out.println("1: Save");
        System.out.println("2: Load");
        int save_or_load = sc.nextInt();
    }

    private static int get_initial_action() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please select an option from the menu below:");
        System.out.println("1: Add new appointment");
        System.out.println("2: Save/Load appointments");
        System.out.println("3: Print all appointments");
        System.out.println("4: Exit System");
        return sc.nextInt();
    }

    private static void freq_choice() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please select frequency of appointments");
        System.out.println("1: Once");
        System.out.println("2: Daily");
        System.out.println("3: Monthly");
        int apt_type = sc.nextInt();

        if (apt_type == 1) {
            System.out.println("Chose daily apt");
        } else if (apt_type == 2) {
            System.out.println("Chose weekly apt");
        } else if (apt_type == 3) {
            System.out.println("Chose monthly apt");
        } else {
            System.out.println("Something went wrong");
        }
    }
}
