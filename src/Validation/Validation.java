package Validation;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
    private final static String usernameRegex = "^(?=[a-zA-Z].{6})[a-zA-Z0-9]+(?:-[a-zA-Z0-9]+)?$";
    private final static String emailRegex = ".";
    private final static String telRegex = ".";
    private final static String guestHomePageOptionRegex = "^[0-5]$";
    private final static String userHomePageOptionRegex = "^[0-6]$";
    private final static String adminHomePageOptionRegex = "^[0-6]$";
    private final static String manageProductsPageOptionRegex = "^[0-6]$";
    private final static String managePromotionPageOptionRegex = "^[0-4]$";
    private final static String manageAccountsPageOptionRegex = "^[0-4]$";
    private final static String manageProfilePageOptionRegex = "^[0-2]$";
    private final static String integerRegex = "^[0-9]+$";
    private final static String categoryChoiceRegex = "^[0-4]$";
    private final static String updateProductPageChoiceRegex = "^[0-3]$";
    private final static String searchPageChoiceRegex = "^[0-2]$";
    private final static String underConstructionRegex = "^[0]$";
    private final static String dateRegex = "([0-2][0-9]|(3)[0-1])[-|\\/](((0)[0-9])|((1)[0-2]))[-|\\/]\\d{4}";

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

    public static boolean checkInteger(String input) {
        Pattern pattern = Pattern.compile(integerRegex);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

    public static boolean checkCategoryChoice (String input) {
        Pattern pattern = Pattern.compile(categoryChoiceRegex);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }
    public static Double parseDouble(String myString) {
        final String Digits = "(\\p{Digit}+)";
        final String HexDigits = "(\\p{XDigit}+)";

        final String Exp = "[eE][+-]?" + Digits;
        final String fpRegex = ("[\\x00-\\x20]*" +
                "[+-]?(" +
                "NaN|" +
                "Infinity|" +

                "(((" + Digits + "(\\.)?(" + Digits + "?)(" + Exp + ")?)|" +
                "(\\.(" + Digits + ")(" + Exp + ")?)|" +
                "((" +
                "(0[xX]" + HexDigits + "(\\.)?)|" +

                "(0[xX]" + HexDigits + "?(\\.)" + HexDigits + ")" +
                ")[pP][+-]?" + Digits + "))" + "[fFdD]?))" + "[\\x00-\\x20]*");

        if (Pattern.matches(fpRegex, myString))
            return Double.valueOf(myString);
        else {
            return null;
        }
    }

    public static boolean updateProductPageChoice (String input) {
        Pattern pattern = Pattern.compile(updateProductPageChoiceRegex);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

//    public static  boolean checkRangeFrom0ToSize (String choice, int size) {
//        String regex =
//        Pattern pattern = Pattern.compile("^[1-5]$");
//        Matcher matcher = pattern.matcher(choice);
//        return matcher.matches();
//    }

    public static boolean searchKeyWord (String word,String keyword) {
        Pattern pattern = Pattern.compile(".*\\b(" + keyword + ")\\b.*");
        Matcher matcher = pattern.matcher(word);
        return matcher.matches();
    }

    public static boolean checkSearchPageOption(String input) {
        Pattern pattern = Pattern.compile(searchPageChoiceRegex);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }public static boolean underConstruction(String input) {
        Pattern pattern = Pattern.compile(underConstructionRegex);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

    public static boolean checkManagePromotionsPageOption (String input) {
        Pattern pattern = Pattern.compile(managePromotionPageOptionRegex);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

    public static boolean checkDateInput (String input) {
        Pattern pattern = Pattern.compile(dateRegex);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

    public static boolean checkManageAccountsPageOption (String input) {
        Pattern pattern = Pattern.compile(manageProfilePageOptionRegex);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

    public static boolean checkManageProfilePageOption (String input) {
        Pattern pattern = Pattern.compile(manageAccountsPageOptionRegex);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }



}
