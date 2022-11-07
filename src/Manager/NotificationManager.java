package Manager;

import IOFile.IOFile;
import Interface.CRUD;
import MenuPrinter.MenuPrinter;
import Model.Account;
import Model.Notification;
import Validation.Validation;
import com.jakewharton.fliptables.FlipTable;
import com.jakewharton.fliptables.FlipTableConverters;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class NotificationManager implements Serializable, CRUD<Notification> {
    private final IOFile<Notification> file;
    private ArrayList<Notification> notifications;
    private final String filePath = "src/Data/NotificationsList.txt";

    public NotificationManager() {
        this.file = new IOFile<Notification>();
        this.notifications = (ArrayList<Notification>) file.readFromFile(filePath);
        resetStaticIndex();
    }

    public ArrayList<Notification> getnotifications() {
        return notifications;
    }

    public void setnotifications(ArrayList<Notification> notifications) {
        this.notifications = notifications;
    }

    @Override
    public void add(Notification notification) {
        notifications.add(notification);
        file.writeToFile(notifications, filePath);
    }

    public void add(Scanner scanner) {
        System.out.println("⏩ Enter notification title: ");
        String title = scanner.nextLine();
        System.out.println("⏩ Enter notification content: ");
        String content = scanner.nextLine();
        String start = "";
        do {
            System.out.println("⏩ Enter start date (Format dd/MM/yyy): ");
            start = scanner.nextLine();
            if (!Validation.checkDateInput(start)) {
                MenuPrinter.wrongInput();
            }
        } while (!Validation.checkDateInput(start));

        String end = "";
        do {
            System.out.println("⏩ Enter end date (Format dd/MM/yyy): ");
            end = scanner.nextLine();
            if (!Validation.checkDateInput(end)) {
                MenuPrinter.wrongInput();
            }
        } while (!Validation.checkDateInput(end));
        notifications.add(new Notification(title, content, convertStringToLocalDate(start), convertStringToLocalDate(end)));
        MenuPrinter.addNotificationSuccessfully();
        file.writeToFile(notifications, filePath);
    }

    @Override
    public void delete() {
        Scanner scanner = new Scanner(System.in);
        displayForAdmin();
        String input = "";
        do {
            System.out.println("⏩ Enter id of notification to delete: ");
            input = scanner.nextLine();
            if (!Validation.checkInteger(input)) {
                MenuPrinter.wrongInput();
            } else if (Integer.parseInt(input) < 0 || Integer.parseInt(input) >= notifications.size()) {
                MenuPrinter.outOfBound();
            }
        } while (!Validation.checkInteger(input) || Integer.parseInt(input) < 0 || Integer.parseInt(input) >= notifications.size());
        int index = Integer.parseInt(input);
        if (!Validation.checkYN("Are you sure to delete this notification (Y/N)")) {
            return;
        } else {
            MenuPrinter.deleteProduct();
            notifications.remove(index);
        }
        file.writeToFile(notifications, filePath);
    }

    @Override
    public void update() {
        Scanner scanner = new Scanner(System.in);
        displayForAdmin();
        String input = "";
        do {
            System.out.println("⏩ Enter index of notification to update: ");
            input = scanner.nextLine();
            if (!Validation.checkInteger(input)) {
                MenuPrinter.wrongInput();
            } else if (Integer.parseInt(input) < 0 || Integer.parseInt(input) >= notifications.size()) {
                MenuPrinter.outOfBound();
            }
        } while (!Validation.checkInteger(input) || Integer.parseInt(input) < 0 || Integer.parseInt(input) >= notifications.size());
        int index = Integer.parseInt(input);
        System.out.println("⏩ Update notification title: ");
        String title = scanner.nextLine();
        if (!title.equals("")) {
            notifications.get(index).setTitle(title);
        }
        System.out.println("⏩ Update notification content: ");
        String content = scanner.nextLine();
        if (!content.equals("")){
            notifications.get(index).setContent(content);
        }
        String start = "";
        do {
            System.out.println("⏩ Update start date (Format dd/MM/yyy): ");
            start = scanner.nextLine();
            if (!Validation.checkDateInput(start)) {
                MenuPrinter.wrongInput();
            }
        } while (!Validation.checkDateInput(start));
        if (!start.equals("")) {
            notifications.get(index).setStartDate(convertStringToLocalDate(start));
        }

        String end = "";
        do {
            System.out.println("⏩ Update end date (Format dd/MM/yyy): ");
            end = scanner.nextLine();
            if (!Validation.checkDateInput(end)) {
                MenuPrinter.wrongInput();
            }
        } while (!Validation.checkDateInput(end));
        if (!end.equals("")) {
            notifications.get(index).setStartDate(convertStringToLocalDate(end));
        }
        System.out.println("✅ Update notification successfully!");
        file.writeToFile(notifications, filePath);
    }

    @Override
    public void display() {
        System.out.println("Under construction!");
    }

    public void displayForUser() {
        if (notifications.isEmpty()) {
            MenuPrinter.noNotification();
        } else {
            boolean flag = false;
            for (Notification p: notifications) {
                if (p.getStatus().equals("Live")){
                    flag = true;
                    break;
                };
            }
            if (!flag) {
                MenuPrinter.noNotification();
            }else {
                ArrayList<Notification> newNotifications = new ArrayList<>();
                for (Notification p : notifications) {
                    if (p.getStatus().equals("Live")) {
                        newNotifications.add(p);
                    }
                }
                String[] headers = {"Title", "Content", "Start Date", "End Date",};
                Object[][] data = new Object[newNotifications.size()][7];
                for (int i = 0; i < newNotifications.size(); i++) {
                    data[i] = new Object[]{newNotifications.get(i).getTitle(), newNotifications.get(i).getContent(), newNotifications.get(i).getStartDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                            newNotifications.get(i).getEndDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))};
                    System.out.println(FlipTableConverters.fromObjects(headers, data));
                }
            }
        }
    }

    public void displayForAdmin() {
        String[] headers = {"ID", "Index", "Title", "Content", "Start Date", "End Date", "Status"};
//        if (notifications.isEmpty()) {
//            String[][] data = new String[][]{
//                    {"NA", "NA", "NA", "NA", "NA", "NA", "NA"}
//            };
//            System.out.println(FlipTable.of(headers, data));
//        } else {
            Object[][] data = new Object[notifications.size()][7];
            for (int i = 0; i < notifications.size(); i++) {
                data[i] = new Object[]{String.valueOf(notifications.get(i).getId()), i, notifications.get(i).getTitle(), notifications.get(i).getContent(), notifications.get(i).getStartDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                        notifications.get(i).getEndDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), notifications.get(i).getStatus()};
            }
            System.out.println(FlipTableConverters.fromObjects(headers, data));
//        }
    }

    private LocalDate convertStringToLocalDate(String date) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(date, dtf);
    }

    private void resetStaticIndex() {
        if (!notifications.isEmpty()) {
            Account.INDEX = notifications.get(notifications.size() - 1).getId();
        }
    }
}


