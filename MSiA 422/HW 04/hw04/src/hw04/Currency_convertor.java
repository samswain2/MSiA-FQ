package hw04;
import java.util.Scanner;

public class Currency_convertor {
	
	public static void main(String[] args) {
		
		converter_func();
		
	}
	
	static String converter_func() {
		
		Scanner user_input = new Scanner(System.in);
		System.out.println("Hello, please enter the price for one dollar in Japanese yen...");
		
		String rate = user_input.nextLine();
		user_input.close();
		System.out.println(rate);
		
		double d = -1;
		
		try {
	        d = Double.parseDouble(rate);
	    } catch (NumberFormatException nfe) {
	        return "Please input a number (n >= 0) to convert...";
	    }
		
		System.out.println(String.valueOf(d));
		
		Scanner switch_to = new Scanner(System.in);
		
		System.out.println("Please select an option from the menu:");
		System.out.println("1: Convert USD ($) to Yen (¥)");
		System.out.println("2: Convert Yen (¥) to USD ($)");
		
		String menu_option = switch_to.nextLine();
		switch_to.close();
		
		if (menu_option == "1") {
			d = d * 2;
		} else if (menu_option == "2"){
			d = d * 2;
		} else {
			return "";
		}	
		
		return "String";
	}
}