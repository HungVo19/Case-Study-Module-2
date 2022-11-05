package Validation;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
    private final static String usernameRegex = "^(?=[a-zA-Z].{6})[a-zA-Z0-9]+(?:-[a-zA-Z0-9]+)?$";
    private final static String emailRegex = ".";
    private final static String telRegex = ".";
    private final static String guestHomePageOptionRegex = "^[0-4]$";
    private final static String userHomePageOptionRegex = "^[0-5]$";
    private final static String adminHomePageOptionRegex = "^[0-5]$";
    private final static String manageProductsPageOptionRegex = "^[0-6]$";
    private final static String numberRegex = "^[0-9]*$";
    private final static String categoryChoiceRegex = "^[0-3]*$";

    public static boolean validateUsername (String username) {
        Pattern pattern = Pattern.compile(usernameRegex);
        Matcher matcher = pattern.matcher(username);
        return matcher.matches();
    }
    public static boolean validateEmail (String username) {
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(username);
        return matcher.matches();
    }
    public static boolean validateTelNumber (String username) {
        Pattern pattern = Pattern.compile(telRegex);
        Matcher matcher = pattern.matcher(username);
        return matcher.matches();
    }

    public static boolean checkGuestHomePageOption (String input) {
        Pattern pattern = Pattern.compile(guestHomePageOptionRegex);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }
    public static boolean checkUserHomePageOption (String input) {
        Pattern pattern = Pattern.compile(userHomePageOptionRegex);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }
    public static boolean checkAdminHomePageOption (String input) {
        Pattern pattern = Pattern.compile(adminHomePageOptionRegex);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

    public static boolean checkManageProductsPageOption (String input) {
        Pattern pattern = Pattern.compile(manageProductsPageOptionRegex);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

    public static boolean checkYN(String question) {
        Scanner scanner = new Scanner(System.in);
        String input;
        do {
            System.out.print(question);
            input = scanner.next().trim().toUpperCase();
        } while (!input.matches("[YN]"));
        return input.equalsIgnoreCase("Y");
    }

    public static boolean checkNumberOnly(String input) {
        Pattern pattern = Pattern.compile(numberRegex);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

    public static boolean checkCategoryChoice (String input) {
        Pattern pattern = Pattern.compile(categoryChoiceRegex);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }
}
