package Manager;

import IOFile.IOFile;
import Interface.CRUD;
import MenuPrinter.MenuPrinter;
import Model.Account;
import Model.Promotion;
import Validation.Validation;
import com.jakewharton.fliptables.FlipTable;
import com.jakewharton.fliptables.FlipTableConverters;
import de.vandermeer.asciitable.AT_Row;
import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class PromotionManager implements Serializable, CRUD<Promotion> {
    private IOFile<Promotion> file;
    private ArrayList<Promotion> promotions;
    private final String filePath = "src/Data/PromotionList.txt";

    public PromotionManager() {
        this.file = new IOFile<Promotion>();
        this.promotions = (ArrayList<Promotion>) file.readFromFile(filePath);
        resetStaticIndex();
    }

    public ArrayList<Promotion> getPromotions() {
        return promotions;
    }

    public void setPromotions(ArrayList<Promotion> promotions) {
        this.promotions = promotions;
    }

    @Override
    public void add(Promotion promotion) {
        promotions.add(promotion);
        file.writeToFile(promotions, filePath);
    }

    public void add(Scanner scanner) {
        System.out.println("⏩ Enter promotion title: ");
        String title = scanner.nextLine();
        System.out.println("⏩ Enter promotion content: ");
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
        promotions.add(new Promotion(title, content, convertStringToLocalDate(start), convertStringToLocalDate(end)));
        MenuPrinter.addPromotionSuccessfully();
        file.writeToFile(promotions, filePath);
    }

    @Override
    public void delete() {
        Scanner scanner = new Scanner(System.in);
        displayForAdmin();
        String input = "";
        do {
            System.out.println("⏩ Enter id of product to delete: ");
            input = scanner.nextLine();
            if (!Validation.checkInteger(input)) {
                MenuPrinter.wrongInput();
            } else if (Integer.parseInt(input) < 0 || Integer.parseInt(input) >= promotions.size()) {
                MenuPrinter.outOfBound();
            }
        } while (!Validation.checkInteger(input) || Integer.parseInt(input) < 0 || Integer.parseInt(input) >= promotions.size());
        int index = Integer.parseInt(input);
        if (!Validation.checkYN("Are you sure to delete this product (Y/N)")) {
            return;
        } else {
            MenuPrinter.deleteProduct();
            promotions.remove(index);
        }
        file.writeToFile(promotions, filePath);
    }

    @Override
    public void update() {
        Scanner scanner = new Scanner(System.in);
        displayForAdmin();
        String input = "";
        do {
            System.out.println("⏩ Enter id of product to update: ");
            input = scanner.nextLine();
            if (!Validation.checkInteger(input)) {
                MenuPrinter.wrongInput();
            } else if (Integer.parseInt(input) < 0 || Integer.parseInt(input) >= promotions.size()) {
                MenuPrinter.outOfBound();
            }
        } while (!Validation.checkInteger(input) || Integer.parseInt(input) < 0 || Integer.parseInt(input) >= promotions.size());
        int index = Integer.parseInt(input);
        System.out.println("⏩ Update promotion title: ");
        String title = scanner.nextLine();
        if (!title.equals("")) {
            promotions.get(index).setTitle(title);
        }
        System.out.println("⏩ Update promotion content: ");
        String content = scanner.nextLine();
        if (!content.equals("")){
            promotions.get(index).setContent(content);
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
            promotions.get(index).setStartDate(convertStringToLocalDate(start));
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
            promotions.get(index).setStartDate(convertStringToLocalDate(end));
        }
        System.out.println("✅ Update promotion successfully!");
        file.writeToFile(promotions, filePath);
    }

    @Override
    public void display() {
        System.out.println("Under construction!");
    }

    public void displayForUser() {
        if (promotions.isEmpty()) {
            MenuPrinter.noPromotion();
            return;
        }
        ArrayList<Promotion> newPromotions = new ArrayList<>();
        for (Promotion p : promotions) {
            if (p.getStatus().equals("Live")) {
                newPromotions.add(p);
            }
        }
        String[] headers = {"Title", "Content", "Start Date", "End Date",};
        Object[][] data = new Object[promotions.size()][7];
        for (int i = 0; i < promotions.size(); i++) {
            data[i] = new Object[]{promotions.get(i).getTitle(), promotions.get(i).getContent(), promotions.get(i).getStartDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                    promotions.get(i).getEndDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))};
            System.out.println(FlipTableConverters.fromObjects(headers, data));
        }
    }

    public void displayForAdmin() {
        String[] headers = {"ID", "Index", "Title", "Content", "Start Date", "End Date", "Status"};
        if (promotions.isEmpty()) {
            String[][] data = new String[][]{
                    {"NA", "NA", "NA", "NA", "NA", "NA", "NA"}
            };
            System.out.println(FlipTable.of(headers, data));
        } else {
            Object[][] data = new Object[promotions.size()][7];
            for (int i = 0; i < promotions.size(); i++) {
                data[i] = new Object[]{String.valueOf(promotions.get(i).getId()), i, promotions.get(i).getTitle(), promotions.get(i).getContent(), promotions.get(i).getStartDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                        promotions.get(i).getEndDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), promotions.get(i).getStatus()};
            }
            System.out.println(FlipTableConverters.fromObjects(headers, data));
        }
    }

    private LocalDate convertStringToLocalDate(String date) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(date, dtf);
    }

    private void resetStaticIndex() {
        if (!promotions.isEmpty()) {
            Account.INDEX = promotions.get(promotions.size() - 1).getId();
        }
    }
}


