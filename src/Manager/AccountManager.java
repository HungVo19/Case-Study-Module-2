package Manager;

import IOFile.IOFile;
import Interface.CRUD;
import MenuPrinter.MenuPrinter;
import Model.Account;
import Model.Role;
import Validation.Validation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class AccountManager implements Serializable, CRUD<Account> {
    private IOFile<Account> file;
    private ArrayList<Account> accounts;
    private final String filePath = "src/Data/AccountsList.txt";

    public AccountManager() {
        file = new IOFile<Account>();
        accounts = (ArrayList<Account>) file.readFromFile(filePath);
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

        if (Validation.checkYN("⛔ Are you sure to delete this account?")) {

        }
        ;
    }

    @Override
    public void update() {

        System.out.println("✅ Update account successfully!");
        file.writeToFile(accounts, filePath);
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

    public boolean checkUserSignIn (String username, String password) {
        for (Account acc: accounts) {
            if (acc.getUsername().equals(username) && acc.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public void signUp(Role role) {
        Scanner scanner = new Scanner(System.in);
        String username = "";
        do {
            System.out.println("⏩ Username should have a minimum length of 7, contains only number,letter");
            System.out.println("⏩ Enter Username:");
            username = scanner.nextLine();
            if (username.contains("admin")) {
                System.out.println("⛔ Username cannot be admin!");
            }
            else if (!Validation.validateUsername(username)) {
                System.out.println("⛔ Username is not applicable!");
                System.out.println("⛔ Try again!");
            }
            else if (!checkUsernameAvailability(username)) {
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
        }while (password.isEmpty());

        if (Validation.checkYN("⏳ Do you want to update detail now (Y/N)")) {
            System.out.println("⏩ Enter name:");
            String name = scanner.nextLine();
            System.out.println("⏩ Enter email:");
            String email = scanner.nextLine();
            System.out.println("⏩ Enter phone number:");
            String tel = scanner.nextLine();
            accounts.add(new Account(username, password, name, email, tel, role));
        } else {
            accounts.add(new Account(username, password, new Role("user")));
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
}
