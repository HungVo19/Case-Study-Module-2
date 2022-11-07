package MenuPrinter;

import de.vandermeer.asciitable.AT_Row;
import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.asciitable.CWC_LongestWordMin;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;

public class MenuPrinter {
    public static void showGuestHomePage() {
        AsciiTable atHeader = new AsciiTable();
        atHeader.addRule();
        AT_Row row = atHeader.addRow("APPLE ONLINE STORE");
        row.getCells().get(0).getContext().setTextAlignment(TextAlignment.CENTER);
        atHeader.addRule();
        row.setPaddingTopChar('v');
        row.setPaddingBottomChar('^');
        row.setPaddingLeftChar('>');
        row.setPaddingRightChar('<');
        row.setTextAlignment(TextAlignment.CENTER);
        row.setPadding(1);
        System.out.println(atHeader.render(40));

        AsciiTable atHeader1 = new AsciiTable();
        atHeader1.addRule();
        AT_Row row1 = atHeader1.addRow("STORE. The best way to buy the products you love");
        row1.getCells().get(0).getContext().setTextAlignment(TextAlignment.CENTER);
        atHeader1.addRule();
        System.out.println(atHeader1.render(40));

        AsciiTable atHeader2 = new AsciiTable();
        atHeader2.addRule();
        AT_Row row2 = atHeader2.addRow("*Warning! For Demo usage only!*");
        row2.getCells().get(0).getContext().setTextAlignment(TextAlignment.CENTER);
        atHeader2.addRule();
        System.out.println(atHeader2.render(40));

        AsciiTable at = new AsciiTable();
        at.addRule();
        at.addRow("1", "Browse products");
        at.addRule();
        at.addRow("2", "Search Apple.com");
        at.addRule();
        at.addRow("3", "Promotion");
        at.addRule();
        at.addRow("4", "Log in to your account");
        at.addRule();
        at.addRow("5", "New customer? Register here");
        at.addRule();
        at.addRow("0", "Exit");
        at.addRule();
        at.getRenderer().setCWC(new CWC_LongestWordMin(new int[]{-1, 36}));
        String rend = at.render();
        System.out.println(rend);
        System.out.print("☛ Enter your choice: ");
    }

    public static void showSearchPage() {

    }

    public static void showLoginPage() {

    }

    public static void showSignUpPage() {
        AsciiTable atHeader = new AsciiTable();
        atHeader.addRule();
        AT_Row row1 = atHeader.addRow("WELCOME TO APPLE STORE");
        row1.getCells().get(0).getContext().setTextAlignment(TextAlignment.CENTER);
        atHeader.addRule();
        AT_Row row2 = atHeader.addRow("Create account");
        row2.getCells().get(0).getContext().setTextAlignment(TextAlignment.CENTER);
        atHeader.addRule();
        System.out.println(atHeader.render(40));
    }

    public static void showUserHomePage() {
        AsciiTable atHeader = new AsciiTable();
        atHeader.addRule();
        AT_Row row = atHeader.addRow("CUSTOMER HOME PAGE");
        row.getCells().get(0).getContext().setTextAlignment(TextAlignment.CENTER);
        atHeader.addRule();
        row.setPaddingTopChar('v');
        row.setPaddingBottomChar('^');
        row.setPaddingLeftChar('>');
        row.setPaddingRightChar('<');
        row.setTextAlignment(TextAlignment.CENTER);
        row.setPadding(1);
        System.out.println(atHeader.render(40));

        AsciiTable atHeader1 = new AsciiTable();
        atHeader1.addRule();
        AT_Row row1 = atHeader1.addRow("The Apple experience. Do even more with Apple products and services");
        row1.getCells().get(0).getContext().setTextAlignment(TextAlignment.CENTER);
        atHeader1.addRule();
        System.out.println(atHeader1.render(40));

        AsciiTable at = new AsciiTable();
        at.addRule();
        at.addRow("1", "Browse products");
        at.addRule();
        at.addRow("2", "Search Apple.com");
        at.addRule();
        at.addRow("3", "Manage Your Apple Store account");
        at.addRule();
        at.addRow("4", "Promotion");
        at.addRule();
        at.addRow("5", "Your Cart");
        at.addRule();
        at.addRow("6", "Purchase history");
        at.addRule();
        at.addRow("0", "Sign out");
        at.addRule();
        at.getRenderer().setCWC(new CWC_LongestWordMin(new int[]{-1, 36}));
        String rend = at.render();
        System.out.println(rend);
        System.out.print("☛ Enter your choice: ");
    }

    public static void showAdminHomePage() {
        AsciiTable atHeader = new AsciiTable();
        atHeader.addRule();
        AT_Row row = atHeader.addRow("ADMIN HOME PAGE");
        row.getCells().get(0).getContext().setTextAlignment(TextAlignment.CENTER);
        atHeader.addRule();
        row.setPaddingTopChar('v');
        row.setPaddingBottomChar('^');
        row.setPaddingLeftChar('>');
        row.setPaddingRightChar('<');
        row.setTextAlignment(TextAlignment.CENTER);
        row.setPadding(1);
        System.out.println(atHeader.render(40));

        AsciiTable atHeader1 = new AsciiTable();
        atHeader1.addRule();
        AT_Row row1 = atHeader1.addRow("By CUSTOMERS. For CUSTOMERS");
        row1.getCells().get(0).getContext().setTextAlignment(TextAlignment.CENTER);
        atHeader1.addRule();
        System.out.println(atHeader1.render(40));

        AsciiTable at = new AsciiTable();
        at.addRule();
        at.addRow("1", "Manage products");
        at.addRule();
        at.addRow("2", "Manage users");
        at.addRule();
        at.addRow("3", "Manage promotion");
        at.addRule();
        at.addRow("4", "View total income");
        at.addRule();
        at.addRow("5", "View bills");
        at.addRule();
        at.addRow("6", "Chat with customers");
        at.addRule();
        at.addRow("0", "Sign out");
        at.addRule();
        at.getRenderer().setCWC(new CWC_LongestWordMin(new int[]{-1, 36}));
        String rend = at.render();
        System.out.println(rend);
        System.out.print("☛ Enter your choice: ");
    }

    public static void confirmDelete() {
        System.out.println("Are you sure to delete this item?");
    }

    public static void welcomeSignIn() {
        System.out.println("✅ Sign in successfully!");
        System.out.println("⌛ Loading System");
    }

    public static void signInFailure() {
        System.out.println("⛔ Wrong username or password");
    }

    public static void usernameNotFound() {
        System.out.println("⛔ username not found!");
    }

    public static void manageProductsPage() {
        AsciiTable atHeader = new AsciiTable();
        atHeader.addRule();
        AT_Row row = atHeader.addRow("ADMIN HOME PAGE");
        row.getCells().get(0).getContext().setTextAlignment(TextAlignment.CENTER);
        atHeader.addRule();
        row.setPaddingTopChar('v');
        row.setPaddingBottomChar('^');
        row.setPaddingLeftChar('>');
        row.setPaddingRightChar('<');
        row.setTextAlignment(TextAlignment.CENTER);
        row.setPadding(1);
        System.out.println(atHeader.render(40));
        AsciiTable atHeader1 = new AsciiTable();
        atHeader1.addRule();
        AT_Row row1 = atHeader1.addRow("PRODUCT MANAGER");
        row1.getCells().get(0).getContext().setTextAlignment(TextAlignment.CENTER);
        atHeader1.addRule();
        System.out.println(atHeader1.render(40));

        AsciiTable at = new AsciiTable();
        at.addRule();
        at.addRow("1", "Display all products");
        at.addRule();
        at.addRow("2", "Add new product");
        at.addRule();
        at.addRow("3", "Update product");
        at.addRule();
        at.addRow("4", "Delete product");
        at.addRule();
        at.addRow("5", "Search product by price range");
        at.addRule();
        at.addRow("6", "Search product by keyword");
        at.addRule();
        at.addRow("0", "Back");
        at.addRule();
        at.getRenderer().setCWC(new CWC_LongestWordMin(new int[]{-1, 36}));
        String rend = at.render();
        System.out.println(rend);
        System.out.print("☛ Enter your choice: ");
    }

    public static void welcomeBanner(String username) {
        AsciiTable atHeader = new AsciiTable();
        atHeader.addRule();
        AT_Row row1 = atHeader.addRow(" WELCOME " + username);
        row1.getCells().get(0).getContext().setTextAlignment(TextAlignment.CENTER);
        atHeader.addRule();
        System.out.println(atHeader.render(40));
    }

    public static void userBanner(String username) {
        AsciiTable atHeader = new AsciiTable();
        atHeader.addRule();
        AT_Row row1 = atHeader.addRow(username);
        row1.getCells().get(0).getContext().setTextAlignment(TextAlignment.CENTER);
        atHeader.addRule();
        System.out.println(atHeader.render(40));
    }

    public static void signOutBanner() {
        System.out.println("✅ Sign out successfully!");
        System.out.println("⌛ Loading System");
    }

    public static void wrongInput() {
        System.out.println("⛔ Wrong input. Try again");
    }

    public static void deleteProduct() {
        System.out.println("✅ Delete successfully");
    }


    public static void updateSuccessfully() {
        System.out.println("✅ Update successfully");
        System.out.println("⌛ Loading System");

    }

    public static void showCategories() {
        AsciiTable atHeader1 = new AsciiTable();
        atHeader1.addRule();
        AT_Row row1 = atHeader1.addRow("CATEGORY OPTION");
        row1.getCells().get(0).getContext().setTextAlignment(TextAlignment.CENTER);
        atHeader1.addRule();
        System.out.println(atHeader1.render(40));

        AsciiTable at = new AsciiTable();
        at.addRule();
        at.addRow("1", "iPhone");
        at.addRule();
        at.addRow("2", "Macbook");
        at.addRule();
        at.addRow("3", "Apple watch");
        at.addRule();
        at.addRow("4", "Create new category");
        at.addRule();
        at.addRow("0", "Later");
        at.addRule();
        at.getRenderer().setCWC(new CWC_LongestWordMin(new int[]{-1, 36}));
        String rend = at.render();
        System.out.println(rend);
        System.out.print("☛ Enter your choice: ");
    }

    public static void outOfBound() {
        System.out.println("⛔ Index out of bound. Try again");
    }

    public static void addSuccessfully() {
        System.out.println("✅ Product added successfully!");
        System.out.println("⌛ Loading System");
    }

    public static void addPromotionSuccessfully() {
        System.out.println("✅ Promotion added successfully!");
        System.out.println("⌛ Loading System");
    }

    public static void searchPage() {
        AsciiTable atHeader1 = new AsciiTable();
        atHeader1.addRule();
        AT_Row row1 = atHeader1.addRow("SEARCH SAND FOUND");
        row1.getCells().get(0).getContext().setTextAlignment(TextAlignment.CENTER);
        atHeader1.addRule();
        System.out.println(atHeader1.render(40));

        AsciiTable at = new AsciiTable();
        at.addRule();
        at.addRow("1", "Search by Price range");
        at.addRule();
        at.addRow("2", "Search by keywword");
        at.addRule();
        at.addRow("0", "Back");
        at.addRule();
        at.getRenderer().setCWC(new CWC_LongestWordMin(new int[]{-1, 36}));
        String rend = at.render();
        System.out.println(rend);
        System.out.print("☛ Enter your choice: ");
    }

    public static void underConstruction () {
        AsciiTable atHeader = new AsciiTable();
        atHeader.addRule();
        AT_Row row1 = atHeader.addRow("OOPS!");
        row1.getCells().get(0).getContext().setTextAlignment(TextAlignment.CENTER);
        AT_Row row2 = atHeader.addRow("UNDER CONSTRUCTION");
        row2.getCells().get(0).getContext().setTextAlignment(TextAlignment.CENTER);
        atHeader.addRule();
        System.out.println(atHeader.render(40));
        System.out.print("☛ Press 0 to return ");
    }

    public static void managePromotionPage() {
        AsciiTable atHeader = new AsciiTable();
        atHeader.addRule();
        AT_Row row = atHeader.addRow("ADMIN HOME PAGE");
        row.getCells().get(0).getContext().setTextAlignment(TextAlignment.CENTER);
        atHeader.addRule();
        row.setPaddingTopChar('v');
        row.setPaddingBottomChar('^');
        row.setPaddingLeftChar('>');
        row.setPaddingRightChar('<');
        row.setTextAlignment(TextAlignment.CENTER);
        row.setPadding(1);
        System.out.println(atHeader.render(40));
        AsciiTable atHeader1 = new AsciiTable();
        atHeader1.addRule();
        AT_Row row1 = atHeader1.addRow("PROMOTION MANAGER");
        row1.getCells().get(0).getContext().setTextAlignment(TextAlignment.CENTER);
        atHeader1.addRule();
        System.out.println(atHeader1.render(40));

        AsciiTable at = new AsciiTable();
        at.addRule();
        at.addRow("1", "Display all promotion");
        at.addRule();
        at.addRow("2", "Add new promotion");
        at.addRule();
        at.addRow("3", "Update promotion");
        at.addRule();
        at.addRow("4", "Delete promotion");
        at.addRule();
        at.addRow("0", "Back");
        at.addRule();
        at.getRenderer().setCWC(new CWC_LongestWordMin(new int[]{-1, 36}));
        String rend = at.render();
        System.out.println(rend);
        System.out.print("☛ Enter your choice: ");
    }

    public static void noPromotion () {
        AsciiTable atHeader = new AsciiTable();
        atHeader.addRule();
        AT_Row row = atHeader.addRow("New Promotion coming soon.Stay tune!");
        row.getCells().get(0).getContext().setTextAlignment(TextAlignment.CENTER);
        atHeader.addRule();
        System.out.println(atHeader.render(40));
        System.out.print("☛ Press 0 to return ");
    }

    public static void manageAccountsPage() {
        AsciiTable atHeader = new AsciiTable();
        atHeader.addRule();
        AT_Row row = atHeader.addRow("ADMIN HOME PAGE");
        row.getCells().get(0).getContext().setTextAlignment(TextAlignment.CENTER);
        atHeader.addRule();
        row.setPaddingTopChar('v');
        row.setPaddingBottomChar('^');
        row.setPaddingLeftChar('>');
        row.setPaddingRightChar('<');
        row.setTextAlignment(TextAlignment.CENTER);
        row.setPadding(1);
        System.out.println(atHeader.render(40));
        AsciiTable atHeader1 = new AsciiTable();
        atHeader1.addRule();
        AT_Row row1 = atHeader1.addRow("USERS MANAGER");
        row1.getCells().get(0).getContext().setTextAlignment(TextAlignment.CENTER);
        atHeader1.addRule();
        System.out.println(atHeader1.render(40));

        AsciiTable at = new AsciiTable();
        at.addRule();
        at.addRow("1", "Display all accounts");
        at.addRule();
        at.addRow("2", "Add new account");
        at.addRule();
        at.addRow("3", "Update account");
        at.addRule();
        at.addRow("4", "Delete account");
        at.addRule();
        at.addRow("0", "Back");
        at.addRule();
        at.getRenderer().setCWC(new CWC_LongestWordMin(new int[]{-1, 36}));
        String rend = at.render();
        System.out.println(rend);
        System.out.print("☛ Enter your choice: ");
    }

    public static void manageProfilePage() {
        AsciiTable at = new AsciiTable();
        at.addRule();
        at.addRow("1", "Update information");
        at.addRule();
        at.addRow("2", "Change password");
        at.addRule();
        at.addRow("3", "Delete account");
        at.addRule();
        at.addRow("0", "Back");
        at.addRule();
        at.getRenderer().setCWC(new CWC_LongestWordMin(new int[]{-1, 36}));
        String rend = at.render();
        System.out.println(rend);
        System.out.print("☛ Enter your choice: ");
    }
}



