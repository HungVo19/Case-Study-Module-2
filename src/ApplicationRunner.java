import Manager.SystemManager;
import MenuPrinter.MenuPrinter;
import Model.Category;
import Model.Role;
import Validation.Validation;

import java.util.ArrayList;
import java.util.Scanner;

public class ApplicationRunner {
    private static final ArrayList<Category> categories = new ArrayList<>();
    private static final ArrayList<Role> roles = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);
    private static final SystemManager systemManager = new SystemManager();

    static {
        categories.add(new Category("iPhone"));
        categories.add(new Category("Macbook"));
        categories.add(new Category("Apple Watch"));

//        roles.add(new Role("admin"));
        roles.add(new Role("user"));
    }

    public void run() {
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
                    showPromotion();
                    break;
                case 4:
                    System.out.println("⏩ Enter username:");
                    String username = scanner.nextLine();
//                    System.out.println("⏩ Enter password:");
//                    String password = scanner.nextLine();
                    if (systemManager.getAccountManager().checkUsernameAvailability(username)) {
                        MenuPrinter.usernameNotFound();
                        break;
                    } else {
                        System.out.println("⏩ Enter password:");
                        String password = scanner.nextLine();
                        if (systemManager.getAccountManager().signInUser(username, password) && !systemManager.getAccountManager().signInAdmin(username, password)) {
                            MenuPrinter.welcomeSignIn();
                            activeUserActivities(username);
                        } else if (systemManager.getAccountManager().signInAdmin(username, password)) {
                            MenuPrinter.welcomeSignIn();
                            activeAdminActivities();
                        } else {
                            MenuPrinter.signInFailure();
                            break;
                        }
                    }
                    break;
                case 5:
                    MenuPrinter.showSignUpPage();
//                    systemManager.getAccountManager().signUp(roles.get(1));
                    systemManager.getAccountManager().signUp((roles));
                    int index = systemManager.getAccountManager().getAccounts().size() - 1;
                    String username1 = systemManager.getAccountManager().getAccounts().get(index).getUsername();
                    activeUserActivities(username1);
                    break;
                case 0:
                    System.exit(0);
                    break;
            }
        } while (true);
    }

    public static void activeUserActivities(String username) {
        do {
            MenuPrinter.welcomeBanner(username);
            MenuPrinter.showUserHomePage();
            String input = "";
            do {
                input = scanner.nextLine();
                if (!Validation.checkUserHomePageOption(input)) {
                    MenuPrinter.wrongInput();
                }
            } while (!Validation.checkUserHomePageOption(input));
            int choice = Integer.parseInt(input);
            switch (choice) {
                case 1:
                    systemManager.getProductManager().displayForUser();
                    break;
                case 2:
                    guestSearching();
                    break;
                case 3:
                    activeUserProfileActivities(username);
                    break;
                case 4:
                    showPromotion();
                    break;
                case 5:
                case 6:
                    showUnderConstructionPage();
                    break;
                case 0:
                    MenuPrinter.signOutBanner();
                    return;
            }
        } while (true);
    }

    public static void activeUserProfileActivities(String username) {
        do {
            int index = systemManager.getAccountManager().getIndex(username);
            systemManager.getAccountManager().getAccounts().get(index).display();
            MenuPrinter.manageProfilePage();
            String input = "";
            do {
                input = scanner.nextLine();
                if (!Validation.checkManageProfilePageOption(input)) {
                    MenuPrinter.wrongInput();
                }
            } while (!Validation.checkManageProfilePageOption(input));
            int choice = Integer.parseInt(input);
            switch (choice) {
                case 1:
                    break;
                case 2:
                    break;
                case 0:
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
                    activeProductsManagerActivities();
                    break;
                case 2:
                    activeAccountsManagerActivities();
                    break;
                case 3:
                    activePromotionsManagerActivities();
                    break;
                case 4:
                case 5:
                case 6:
                    showUnderConstructionPage();
                    break;
                case 0:
                    MenuPrinter.signOutBanner();
                    return;
            }
        } while (true);
    }

    public static void activeProductsManagerActivities() {
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

    public static void activeAccountsManagerActivities() {
        do {
            MenuPrinter.manageAccountsPage();
            String input = "";
            do {
                input = scanner.nextLine();
                if (!Validation.checkManageAccountsPageOption(input)) {
                    MenuPrinter.wrongInput();
                }
            } while (!Validation.checkManageAccountsPageOption(input));
            int choice = Integer.parseInt(input);
            switch (choice) {
                case 1:
                    systemManager.getAccountManager().displayAll();
                    break;
                case 2:
                    systemManager.getAccountManager().signUp(roles);
                    break;
                case 3:
                    systemManager.getAccountManager().update();
                    break;
                case 4:
                    systemManager.getAccountManager().delete();
                    break;
                case 0:
                    return;
            }
        } while (true);
    }

    ;

    public static void guestSearching() {
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

    public static void activePromotionsManagerActivities() {
        do {
            MenuPrinter.managePromotionPage();
            String input = "";
            do {
                input = scanner.nextLine();
                if (!Validation.checkManagePromotionsPageOption(input)) {
                    MenuPrinter.wrongInput();
                }
            } while (!Validation.checkManagePromotionsPageOption(input));
            int choice = Integer.parseInt(input);
            switch (choice) {
                case 1:
                    systemManager.getPromotionManager().displayForAdmin();
                    break;
                case 2:
                    systemManager.getPromotionManager().add(scanner);
                    break;
                case 3:
                    systemManager.getPromotionManager().update();
                    break;
                case 4:
                    systemManager.getPromotionManager().delete();
                    break;
                case 0:
                    return;
            }
        } while (true);
    }

    ;


    public static void showUnderConstructionPage() {
        do {
            MenuPrinter.underConstruction();
            String input = "";
            do {
                input = scanner.nextLine();
                if (!Validation.underConstruction(input)) {
                    MenuPrinter.wrongInput();
                }
            } while (!Validation.underConstruction(input));
            int choice = Integer.parseInt(input);
            if (choice == 0) {
                return;
            }
        } while (true);
    }

    public static void showPromotion() {
        do {
            systemManager.getPromotionManager().displayForUser();
            System.out.print("☛ Press 0 to return ");
            String input = "";
            do {
                input = scanner.nextLine();
                if (!Validation.underConstruction(input)) {
                    MenuPrinter.wrongInput();
                }
            } while (!Validation.underConstruction(input));
            int choice = Integer.parseInt(input);
            if (choice == 0) {
                return;
            }
        } while (true);
    }
}