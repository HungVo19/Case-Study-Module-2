import Manager.SystemManager;
import MenuPrinter.MenuPrinter;
import Model.Cart;
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

    public static void run() {
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
                    display();
                    break;
                case 2:
                    guestSearching();
                    break;
                case 3:
                    showNotification();
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

    public static void display() {
        do {
            systemManager.getProductManager().displayForUser();
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
                    display();
                    break;
                case 2:
                    guestSearching();
                    break;
                case 3:
                    activeUserProfileActivities(username);
                    break;
                case 4:
                    showNotification();
                    break;
                case 5:
                    activeCartActivities(username);
                    break;
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
                    systemManager.getAccountManager().update(index,scanner);
                    break;
                case 2:
                    systemManager.getAccountManager().changePass(index,scanner);
                    break;
                case 3:
                   if(!Validation.checkYN("⛔ Delete this account permanently (Y/N) ?")) {
                       return;
                   } else {
                       systemManager.getAccountManager().delete(index);
                       run();
                   }

                case 0:
                    return;
            }
        } while (true);
    }

    public static void activeCartActivities(String username) {
        int index = systemManager.getAccountManager().getIndex(username);
        Cart cart = new Cart(systemManager.getAccountManager().getAccounts().get(index), systemManager);
        do {
            cart.display();
            MenuPrinter.cartPage();
            System.out.print("☛ Enter your choice: ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    systemManager.getProductManager().displayForPurchase();
                    System.out.println("Enter item and quantity to add to cart: ");
                    int index1 = Integer.parseInt(scanner.nextLine());
                    System.out.println("Enter item and quantity to add to cart: ");
                    int quantity = Integer.parseInt(scanner.nextLine());
                    cart.addItem(index1, quantity);
                    break;
                case 2:
                    System.out.println("Enter item and quantity to remove:");
                    int index2 = Integer.parseInt(scanner.nextLine());
                    System.out.println("Enter item and quantity to remove:");
                    int quantity1 = Integer.parseInt(scanner.nextLine());
                    cart.removeItem(index2,quantity1);
                    break;
                case 3:
                    cart.checkOut();
                    break;
                case 0:
                    return;
            }
        }while (true);
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
                    activeNotificationsManagerActivities();
                    break;
                case 4:
                case 5:
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
                    do {
                        systemManager.getProductManager().display();
                        System.out.print("☛ Press 0 to return ");
                        String input1 = "";
                        do {
                            input1 = scanner.nextLine();
                            if (!Validation.underConstruction(input1)) {
                                MenuPrinter.wrongInput();
                            }
                        } while (!Validation.underConstruction(input1));
                        int choice1 = Integer.parseInt(input1);
                        if (choice1 == 0) {
                            return;
                        }
                    } while (true);
//                    break;
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
                    do {
                        systemManager.getAccountManager().displayAll();
                        System.out.print("☛ Press 0 to return ");
                        String input1 = "";
                        do {
                            input1 = scanner.nextLine();
                            if (!Validation.underConstruction(input1)) {
                                MenuPrinter.wrongInput();
                            }
                        } while (!Validation.underConstruction(input1));
                        int choice1 = Integer.parseInt(input1);
                        if (choice1 == 0) {
                            return;
                        }
                    } while (true);
//                    break;
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

    public static void activeNotificationsManagerActivities() {
        do {
            MenuPrinter.manageNotificationPage();
            String input = "";
            do {
                input = scanner.nextLine();
                if (!Validation.checkManageNotificationsPageOption(input)) {
                    MenuPrinter.wrongInput();
                }
            } while (!Validation.checkManageNotificationsPageOption(input));
            int choice = Integer.parseInt(input);
            switch (choice) {
                case 1:
                    do {
                        systemManager.getNotificationManager().displayForAdmin();
                        System.out.print("☛ Press 0 to return ");
                        String input1 = "";
                        do {
                            input1 = scanner.nextLine();
                            if (!Validation.underConstruction(input1)) {
                                MenuPrinter.wrongInput();
                            }
                        } while (!Validation.underConstruction(input1));
                        int choice1 = Integer.parseInt(input1);
                        if (choice1 == 0) {
                            return;
                        }
                    } while (true);
//                    break;
                case 2:
                    systemManager.getNotificationManager().add(scanner);
                    break;
                case 3:
                    systemManager.getNotificationManager().update();
                    break;
                case 4:
                    systemManager.getNotificationManager().delete();
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

    public static void showNotification() {
        do {
            systemManager.getNotificationManager().displayForUser();
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
