package Manager;

import IOFile.IOFile;
import Interface.CRUD;
import MenuPrinter.MenuPrinter;
import Model.Account;
import Model.Role;
import Validation.Validation;
import com.jakewharton.fliptables.FlipTable;
import com.jakewharton.fliptables.FlipTableConverters;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class AccountManager implements Serializable, CRUD<Account> {
    private final IOFile<Account> file;
    private ArrayList<Account> accounts;
    private final String filePath = "src/Data/AccountsList.txt";

    public AccountManager() {
        file = new IOFile<Account>();
        accounts = (ArrayList<Account>) file.readFromFile(filePath);
        if (accounts.isEmpty()) {
            Account ADMIN = new Account("admin", "123456");
            ADMIN.setRole(new Role("admin"));
        }
        resetStaticIndex();
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(ArrayList<Account> accounts) {
        this.accounts = accounts;
    }

    @Override
    public void add(Account account) {
        accounts.add(account);
        file.writeToFile(accounts, filePath);
    }

    @Override
    public void delete() {
        Scanner scanner = new Scanner(System.in);
        displayAll();
        String input = "";
        do {
            System.out.println("⏩ Enter id of product to delete: ");
            input = scanner.nextLine();
            if (!Validation.checkInteger(input)) {
                MenuPrinter.wrongInput();
            } else if (Integer.parseInt(input) < 0 || Integer.parseInt(input) >= accounts.size()) {
                MenuPrinter.outOfBound();
            }
        } while (!Validation.checkInteger(input) || Integer.parseInt(input) < 0 || Integer.parseInt(input) >= accounts.size());
        int index = Integer.parseInt(input);
        if (!Validation.checkYN("Are you sure to delete this account permanently (Y/N)")) {
            return;
        } else {
            MenuPrinter.deleteProduct();
            accounts.remove(index);
        }
        file.writeToFile(accounts, filePath);
    }

    @Override
    public void update() {
        Scanner scanner = new Scanner(System.in);
        displayAll();
        String input = "";
        do {
            System.out.println("⏩ Enter index of product to update: ");
            input = scanner.nextLine();
            if (!Validation.checkInteger(input)) {
                MenuPrinter.wrongInput();
            } else if (Integer.parseInt(input) < 0 || Integer.parseInt(input) >= accounts.size()) {
                MenuPrinter.outOfBound();
            }
        } while (!Validation.checkInteger(input) || Integer.parseInt(input) < 0 || Integer.parseInt(input) >= accounts.size());
        int index = Integer.parseInt(input);

        System.out.println("⏩ Change password: ");
        String password = scanner.nextLine();
        if (!password.equals("")) {
            accounts.get(index).setPassword(password);
        }

        System.out.println("⏩ Update name: ");
        String name = scanner.nextLine();
        if (!name.equals("")) {
            accounts.get(index).setName(name);
        }
        System.out.println("⏩ Update email: ");
        String email = scanner.nextLine();
        if (!email.equals("")) {
            accounts.get(index).setEmail(email);
        }

        String tel = scanner.nextLine();
        if (!tel.equals("")) {
            accounts.get(index).setTel(tel);
        }
        System.out.println("✅ Update account successfully!");
        file.writeToFile(accounts, filePath);
    }

    public void update (int index) {

    }

    @Override
    public void display() {
        for (Account acc : accounts) {
            acc.display();
        }
    }

    private void resetStaticIndex() {
        if (!accounts.isEmpty()) {
            Account.INDEX = accounts.get(accounts.size() - 1).getId();
        }
    }

    public boolean signInUser(String username, String password) {
        return checkUserSignIn(username, password);
    }

    public boolean signInAdmin(String username, String password) {
        return username.equals("admin") && password.equals("123456");
    }

    public boolean checkUserSignIn(String username, String password) {
        for (Account acc : accounts) {
            if (acc.getUsername().equals(username) && acc.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public void signUp(ArrayList<Role> roles) {
        Scanner scanner = new Scanner(System.in);
        String username = "";
        do {
            System.out.println("⏩ Username must have a minimum length of 7 and contains only number,letter");
            System.out.println("⏩ Enter Username:");
            username = scanner.nextLine();
            if (username.toLowerCase().contains("admin")) {
                System.out.println("⛔ Username cannot contain 'admin'!");
            } else if (!Validation.validateUsername(username)) {
                System.out.println("⛔ Username is not applicable!");
                System.out.println("⛔ Try again!");
            } else if (!checkUsernameAvailability(username)) {
                System.out.println("⛔ This username is already existed!");
                System.out.println("⛔ Try another one!");
            }
        } while (!Validation.validateUsername(username) || (!checkUsernameAvailability(username)));

        String password = "";
        do {
            System.out.println("⏩ Enter password:");
            password = scanner.nextLine();
            if (password.isEmpty()) {
                System.out.println("⛔ Password cannot be empty!");
            }
        } while (password.isEmpty());

        if (Validation.checkYN("⏳ Do you want to update detail now (Y/N)")) {
            System.out.println("⏩ Enter name:");
            String name = scanner.nextLine();
            System.out.println("⏩ Enter email:");
            String email = scanner.nextLine();
            System.out.println("⏩ Enter phone number:");
            String tel = scanner.nextLine();
            accounts.add(new Account(username, password, name, email, tel, roles.get(0)));
        } else {
            accounts.add(new Account(username, password, roles.get(0)));
        }
        System.out.println("✅ New account is created successfully!");
        System.out.println("⌛ Loading System");
        file.writeToFile(accounts, filePath);
    }

    public boolean checkUsernameAvailability(String username) {
        boolean flag = !username.contains("admin");
        for (Account acc : accounts) {
            if (acc.getUsername().equals(username)) {
                flag = false;
            }
        }
        return flag;
    }

    public int getIndex(String username) {
        int index = -1;
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getUsername().equals(username)) {
                index = i;
            }
        }
        return index;
    }

    public void displayForAdmin() {
        String[] headers = {"ID", "Index", "Username", "Password", "Name", "Email", "Tel", "Role"};
        if (filterUser().isEmpty()) {
            String[][] data = new String[][]{
                    {"NA", "NA", "NA", "NA", "NA", "NA", "NA", "NA"}
            };
            System.out.println(FlipTable.of(headers, data));
        } else {
            Object[][] data = new Object[filterUser().size()][7];
            for (int i = 0; i < filterUser().size(); i++) {
                data[i] = new Object[]{filterUser().get(i).getId(), i, filterUser().get(i).getUsername(), filterUser().get(i).getPassword(), filterUser().get(i).getName(),
                        filterUser().get(i).getEmail(), filterUser().get(i).getTel(), filterUser().get(i).getRole().getStatus()};
            }
            System.out.println(FlipTableConverters.fromObjects(headers, data));
        }
    }

    public void displayAll() {
        String[] headers = {"Index", "Username", "Password", "Name", "Email", "Tel", "Role"};
//        if (accounts.isEmpty()) {
//            String[][] data = new String[][]{
//                    {"NA", "NA", "NA", "NA", "NA", "NA", "NA", "NA"}
//            };
//            System.out.println(FlipTable.of(headers, data));
//        } else {
        Object[][] data = new Object[filterUser().size()][7];
        for (int i = 0; i < accounts.size(); i++) {
            data[i] = new Object[]{i, accounts.get(i).getUsername(), accounts.get(i).getPassword(), accounts.get(i).getName(),
                    accounts.get(i).getEmail(), accounts.get(i).getTel(), accounts.get(i).getRole().getStatus()};
        }
        System.out.println(FlipTableConverters.fromObjects(headers, data));
//        }
    }

    public void displayProfile(int i) {
        String[] headers = {"Index", "Username", "Password", "Name", "Email", "Tel", "Role"};
        Object[][] data = {
                {i, accounts.get(i).getUsername(), accounts.get(i).getPassword(), accounts.get(i).getName(),
                        accounts.get(i).getEmail(), accounts.get(i).getTel(), accounts.get(i).getRole().getStatus()}
        };
        System.out.println(FlipTableConverters.fromObjects(headers, data));
    }

    public void displayAlter() {
        for (Account acc : accounts) {
            acc.display();
        }
    }

    private ArrayList<Account> filterUser() {
        ArrayList<Account> normalUsers = new ArrayList<>();
        for (Account acc : accounts) {
            if (!acc.getRole().getStatus().equals("admin")) {
                normalUsers.add(acc);
            }
        }
        return normalUsers;
    }
}
