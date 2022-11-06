import Manager.SystemManager;
import MenuPrinter.MenuPrinter;
import Model.Account;
import Model.Category;
import Model.Role;
import Validation.Validation;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final ArrayList<Category> categories = new ArrayList<>();
    private static final ArrayList<Role> roles = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);
    private static final SystemManager systemManager = new SystemManager();

    static {
        categories.add(new Category("iPhone"));
        categories.add(new Category("Macbook"));
        categories.add(new Category("Apple Watch"));

        roles.add(new Role("admin"));
        roles.add(new Role("user"));

        systemManager.getAccountManager().add(new Account("admin", "123456", roles.get(0)));
    }

    public static void main(String[] args) {
        SystemManager systemManager = new SystemManager();

        do {
            MenuPrinter.showGuestHomePage();
            String input = "";
            do {
                input = scanner.nextLine();
                if (!Validation.checkGuestHomePageOption(input)) {
                    MenuPrinter.wrongInput();
                }
            } while (!Validation.checkGuestHomePageOption(input));
            int choice = Integer.parseInt(input);
            switch (choice) {
                case 1:
                    systemManager.getProductManager().displayForUser();
                    break;
                case 2:
                    guestSearching();
                    break;
                case 3:
                    System.out.println("⏩ Enter username:");
                    String username = scanner.nextLine();
                    System.out.println("⏩ Enter password:");
                    String password = scanner.nextLine();
                    if (systemManager.getAccountManager().signInUser(username, password) && !systemManager.getAccountManager().signInAdmin(username, password)) {
                        MenuPrinter.welcomeSignIn();
                        MenuPrinter.welcomeBanner(username);
                        activeUserActivities();
                    } else if (systemManager.getAccountManager().signInAdmin(username, password)) {
                        MenuPrinter.welcomeSignIn();
                        activeAdminActivities();
                    } else {
                        MenuPrinter.signInFailure();
                        break;
                    }
                    break;
                case 4:
                    MenuPrinter.showSignUpPage();
                    systemManager.getAccountManager().signUp(roles.get(1));
                    activeUserActivities();
                    break;
                case 0:
                    System.exit(0);
                    break;
            }
        } while (true);
    }

    public static void activeUserActivities() {
        do {
            MenuPrinter.showUserHomePage();
            String input = "";
            do {
                input = scanner.nextLine();
                if (!Validation.checkUserHomePageOption(input)) {
                    MenuPrinter.wrongInput();
                }
            } while (!Validation.checkGuestHomePageOption(input));
            int choice = Integer.parseInt(input);
            switch (choice) {
                case 1:

                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 0:
                    MenuPrinter.signOutBanner();
                    return;
            }
        } while (true);
    }

    public static void activeAdminActivities() {
        do {
            MenuPrinter.showAdminHomePage();
            String input = "";
            do {
                input = scanner.nextLine();
                if (!Validation.checkAdminHomePageOption(input)) {
                    MenuPrinter.wrongInput();
                }
            } while (!Validation.checkGuestHomePageOption(input));
            int choice = Integer.parseInt(input);
            switch (choice) {
                case 1:
                    activeManageProductsActivities();
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 0:
                    MenuPrinter.signOutBanner();
                    return;
            }
        } while (true);
    }

    public static void activeManageProductsActivities() {
        do {
            MenuPrinter.manageProductsPage();
            String input = "";
            do {
                input = scanner.nextLine();
                if (!Validation.checkManageProductsPageOption(input)) {
                    MenuPrinter.wrongInput();
                }
            } while (!Validation.checkManageProductsPageOption(input));
            int choice = Integer.parseInt(input);
            switch (choice) {
                case 1:
                    systemManager.getProductManager().display();
                    break;
                case 2:
                    systemManager.getProductManager().add(categories);
                    break;
                case 3:
                    systemManager.getProductManager().update(categories);
                    break;
                case 4:
                    systemManager.getProductManager().delete();
                    break;
                case 5:
                    systemManager.getProductManager().searchByPriceRange(scanner);
                    break;
                case 6:
                    systemManager.getProductManager().searchByKeyWord(scanner);
                    break;
                case 0:
                    MenuPrinter.signOutBanner();
                    return;
            }
        } while (true);
    }

    public static void guestSearching(){
        do {
            MenuPrinter.searchPage();
            String input = "";
            do {
                input = scanner.nextLine();
                if (!Validation.checkSearchPageOption(input)) {
                    MenuPrinter.wrongInput();
                }
            } while (!Validation.checkSearchPageOption(input));
            int choice = Integer.parseInt(input);
            switch (choice) {
                case 1:
                    systemManager.getProductManager().searchByPriceRange(scanner);
                    break;
                case 2:
                    systemManager.getProductManager().searchByKeyWord(scanner);
                    break;
                case 0:
                    return;
            }
        } while (true);
    }
}
