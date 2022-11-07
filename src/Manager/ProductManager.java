package Manager;

import IOFile.IOFile;
import Interface.CRUD;
import MenuPrinter.MenuPrinter;
import Model.Category;
import Model.Product;
import Validation.Validation;
import com.jakewharton.fliptables.FlipTable;
import com.jakewharton.fliptables.FlipTableConverters;

import java.io.Serializable;
import java.util.*;

public class ProductManager implements Serializable, CRUD<Product> {
    private final IOFile<Product> file;
    private ArrayList<Product> products;
    private final String filePath = "src/Data/ProductsList.txt";

    public ProductManager() {
        file = new IOFile<Product>();
        products = (ArrayList<Product>) file.readFromFile(filePath);
        resetStaticIndex();
    }

    @Override
    public void add(Product product) {
        products.add(product);
        file.writeToFile(products, filePath);
    }

    public void add(ArrayList<Category> categories) {
        Scanner scanner = new Scanner(System.in);
        String name = "";
        do {
            System.out.println("Enter product name:");
            name = scanner.nextLine();
            if (name.isEmpty()) {
                System.out.println("⛔ name cannot be empty!");
            }
        } while (name.isEmpty());
        if (!checkNameAvailability(name)) {
            int newQuantity = products.get(getIndexByName(name)).getQuantity() + 1;
            products.get(getIndexByName(name)).setQuantity(newQuantity);
            System.out.println("✅ Product " + name + " is already existed. It's quantity is increased by 1! ");
        } else {
            Double price = null;
            String tempPrice = "";
            do {
                System.out.println("⏩ Enter price");
                tempPrice = scanner.nextLine();
                if (tempPrice.isEmpty()) {
                    price = 0.0;
                    break;
                } else if (Validation.parseDouble(tempPrice) != null) {
                    price = Double.parseDouble(tempPrice);
                    break;
                } else {
                    MenuPrinter.wrongInput();
                }
            } while (Validation.parseDouble(tempPrice) == null);
            Integer quantity = null;
            String tempQuantity = "";
            do {
                System.out.println("⏩ Enter quantity:");
                tempQuantity = scanner.nextLine();
                if (tempQuantity.isEmpty()) {
                    quantity = 0;
                    break;
                } else if (Validation.checkInteger(tempQuantity)) {
                    quantity = Integer.parseInt(tempQuantity);
                    break;
                } else {
                    MenuPrinter.wrongInput();
                }
            } while (!Validation.checkInteger(tempQuantity));
            String choice = "";
            do {
                MenuPrinter.showCategories();
                choice = scanner.nextLine();
                if (!Validation.checkCategoryChoice(choice)) {
                    MenuPrinter.wrongInput();
                }
            } while (!Validation.checkCategoryChoice(choice));
            switch (Integer.parseInt(choice)) {
                case 1:
                    products.add(new Product(name, price, quantity, categories.get(0)));
                    break;
                case 2:
                    products.add(new Product(name, price, quantity, categories.get(1)));
                    break;
                case 3:
                    products.add(new Product(name, price, quantity, categories.get(2)));
                    break;
                case 4:
                    System.out.println("⏩ Enter name of new category: ");
                    String newCategory = scanner.nextLine();
                    Category category = new Category(newCategory);
                    products.add(new Product(name, price, quantity, category));
                case 0:
                    products.add(new Product(name, price, quantity, null));
                    break;
            }
            MenuPrinter.addSuccessfully();
            file.writeToFile(products, filePath);
        }
    }

    private int getIndexByName(String name) {
        int index = -1;
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getName().equalsIgnoreCase(name)) {
                index = i;
            }
        }
        return index;
    }

    //not yet check space at end of line
    private boolean checkNameAvailability(String name) {
        boolean flag = true;
        for (Product p : products) {
            if (p.getName().equalsIgnoreCase(name)) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    @Override
    public void delete() {
        Scanner scanner = new Scanner(System.in);
        display();
        String input = "";
        do {
            System.out.println("⏩ Enter id of product to delete: ");
            input = scanner.nextLine();
            if (!Validation.checkInteger(input)) {
                MenuPrinter.wrongInput();
            } else if (Integer.parseInt(input) < 0 || Integer.parseInt(input) >= products.size()) {
                MenuPrinter.outOfBound();
            }
        } while (!Validation.checkInteger(input) || Integer.parseInt(input) < 0 || Integer.parseInt(input) >= products.size());
        int index = Integer.parseInt(input);
        if (!Validation.checkYN("Are you sure to delete this product (Y/N)")) {
            return;
        } else {
            MenuPrinter.deleteProduct();
            products.remove(index);
        }
        file.writeToFile(products, filePath);
    }

    @Override
    public void update() {
        return;
    }

    public void update(ArrayList<Category> categories) {
        Scanner scanner = new Scanner(System.in);
        this.products = (ArrayList<Product>) file.readFromFile(filePath);
        display();
        String input = "";
        do {
            System.out.println("Enter id of product to update: ");
            input = scanner.nextLine();
            if (!Validation.checkInteger(input)) {
                MenuPrinter.wrongInput();
            } else if (Integer.parseInt(input) < 0 || Integer.parseInt(input) >= products.size()) {
                MenuPrinter.outOfBound();
            }
        } while (!Validation.checkInteger(input) || Integer.parseInt(input) < 0 || Integer.parseInt(input) >= products.size());
        int index = Integer.parseInt(input);

        String name = "";
        System.out.println("✅ Update product name:");
        name = scanner.nextLine();
        int temp = products.get(index).getQuantity();
        if (name.isEmpty()) {
            name = products.get(index).getName();
        } else if (!checkNameAvailability(name)) {
            int newQuantity = products.get(getIndexByName(name)).getQuantity() + temp;
            products.get(getIndexByName(name)).setQuantity(newQuantity);
            System.out.println("✅ Product " + name + " is already existed. It's quantity is increased by " + temp + " correspondingly! ");
            products.remove(index);
            file.writeToFile(products, filePath);
            return;
        }
        products.get(index).setName(name);

        Double price = null;
        String tempPrice = "";
        do {
            System.out.println("⏩ Update price");
            tempPrice = scanner.nextLine();
            if (tempPrice.isEmpty()) {
                price = products.get(index).getPrice();
                break;
            } else if (Validation.parseDouble(tempPrice) != null) {
                price = Double.parseDouble(tempPrice);
                break;
            } else {
                MenuPrinter.wrongInput();
            }
        } while (Validation.parseDouble(tempPrice) == null);
        products.get(index).setPrice(price);

        Integer quantity = null;
        String tempQuantity = "";
        do {
            System.out.println("⏩ Update quantity:");
            tempQuantity = scanner.nextLine();
            if (tempQuantity.isEmpty()) {
                quantity = products.get(index).getQuantity();
                break;
            } else if (Validation.checkInteger(tempQuantity)) {
                quantity = Integer.parseInt(tempQuantity);
                break;
            } else {
                MenuPrinter.wrongInput();
            }
        } while (!Validation.checkInteger(tempQuantity));
        products.get(index).setQuantity(quantity);


        String choice = "";
        do {
            MenuPrinter.showCategories();
            choice = scanner.nextLine();
            if (!Validation.checkCategoryChoice(choice)) {
                MenuPrinter.wrongInput();
            }
        } while (!Validation.checkCategoryChoice(choice));
        switch (Integer.parseInt(choice)) {
            case 1:
                products.get(index).setCategory(categories.get(0));
                break;
            case 2:
                products.get(index).setCategory(categories.get(1));
                break;
            case 3:
                products.get(index).setCategory(categories.get(2));
                break;
            case 4:
                System.out.println("⏩ Enter name of new category: ");
                String newCategory = scanner.nextLine();
                Category category = new Category(newCategory);
                products.get(index).setCategory(category);
            case 0:
                break;
        }
        MenuPrinter.updateSuccessfully();
        file.writeToFile(products, filePath);
    }

    public void searchByPriceRange(Scanner scanner) {
        this.products = (ArrayList<Product>) file.readFromFile(filePath);
        if (products.isEmpty()) {
            System.out.println("⛔ All product are temporally out of stock!");
            System.out.println("⛔ Please come back later!");
            return;
        } else {
            System.out.println("Enter price range to search:");
            Double min = null;
            String tempMin = "";
            do {
                System.out.println("⏩ Enter minimum price:");
                tempMin = scanner.nextLine();
                if (tempMin.isEmpty()) {
                    min = 0.0;
                    break;
                } else if (Validation.parseDouble(tempMin) != null) {
                    min = Double.parseDouble(tempMin);
                    break;
                } else {
                    MenuPrinter.wrongInput();
                }
            } while (Validation.parseDouble(tempMin) == null);

            Double max = null;
            String tempMax = "";
            do {
                System.out.println("⏩ Enter maximum price:");
                tempMax = scanner.nextLine();
                if (tempMax.isEmpty()) {
                    max = 0.0;
                    break;
                } else if (Validation.parseDouble(tempMax) != null) {
                    max = Double.parseDouble(tempMax);
                    break;
                } else if (Double.parseDouble(tempMax) <= min) {
                    System.out.println("⛔ Maximum price should be greater than minimum price");
                } else {
                    MenuPrinter.wrongInput();
                }
            } while (Validation.parseDouble(tempMax) == null || Double.parseDouble(tempMax) <= min);

            ArrayList<Product> tempList = new ArrayList<>();
            for (Product p : products) {
                if (p.getPrice() <= max && p.getPrice() >= min) {
                    tempList.add(p);
                }
            }
            Collections.sort(tempList, Comparator.comparingDouble(Product::getPrice));
            display(tempList);
        }
    }

    public void searchByKeyWord(Scanner scanner) {
        if (products.isEmpty()) {
            System.out.println("⛔ All product are temporally out of stock!");
            System.out.println("⛔ Please come back later!");
        } else {
            System.out.println("⏩ Enter key word:");
            String input = scanner.nextLine();
            ArrayList<Product> tempList = new ArrayList<>();
            for (Product p : products) {
                if ((p.getName()).toLowerCase().contains(input.toLowerCase())) {
                    tempList.add(p);
                }
            }
            display(tempList);
        }
    }

    public double getTotalMoney() {
        this.products = (ArrayList<Product>) file.readFromFile(filePath);
        double totalMoney = 0;
        for (Product p : products) {
            totalMoney += p.getAmount();
        }
        return totalMoney;
    }

    public Integer getTotalQuantity() {
        this.products = (ArrayList<Product>) file.readFromFile(filePath);
        Integer totalQuantity = 0;
        for (Product p : products) {
            totalQuantity += p.getQuantity();
        }
        return totalQuantity;
    }

    @Override
    public void display() {
        this.products = (ArrayList<Product>) file.readFromFile(filePath);
        String[] headers = {"ID", "Index", "Category", "Name", "Price", "Quantity", "Inventory"};
        if (products.isEmpty()) {
            String[][] data = new String[][]{
                    {"NA", "NA", "NA", "NA", "NA", "NA"}
            };
            System.out.println(FlipTable.of(headers, data));
        } else {
            Object[][] data = new Object[products.size() + 1][5];
            for (int i = 0; i < products.size(); i++) {
                if (products.get(i).getCategory() == null) {
                    data[i] = new Object[]{String.valueOf(products.get(i).getId()), i, "NA", products.get(i).getName(), String.valueOf(products.get(i).getPrice()), String.valueOf(products.get(i).getQuantity()), String.valueOf(products.get(i).getAmount())};
                } else {
                    data[i] = new Object[]{String.valueOf(products.get(i).getId()), i, String.valueOf(products.get(i).getCategory().getName()), products.get(i).getName(), String.valueOf(products.get(i).getPrice()), String.valueOf(products.get(i).getQuantity()), String.valueOf(products.get(i).getAmount())};
                }
            }
            data[products.size()] = new Object[]{"", "", "", "", "", String.valueOf(getTotalQuantity()), String.valueOf(getTotalMoney())};
            System.out.println(FlipTableConverters.fromObjects(headers, data));
        }
    }

    public void displayForUser() {
        this.products = (ArrayList<Product>) file.readFromFile(filePath);
        String[] headers = {"Category", "Name", "Price", "Quantity"};
        if (products.isEmpty()) {
            String[][] data = new String[][]{
                    {"NA", "NA", "NA", "NA"}
            };
            System.out.println(FlipTable.of(headers, data));
        } else {
            Object[][] data = new Object[products.size()][5];
            for (int i = 0; i < products.size(); i++) {
                if (products.get(i).getCategory() == null) {
                    data[i] = new Object[]{"NA", products.get(i).getName(), String.valueOf(products.get(i).getPrice()), String.valueOf(products.get(i).getQuantity())};
                } else {
                    data[i] = new Object[]{String.valueOf(products.get(i).getCategory().getName()), products.get(i).getName(), String.valueOf(products.get(i).getPrice()), String.valueOf(products.get(i).getQuantity())};
                }
            }
            System.out.println(FlipTableConverters.fromObjects(headers, data));
        }
    }

    public void display(ArrayList<Product> products) {
        String[] headers = {"Category", "Name", "Price", "Quantity"};
        if (products.isEmpty()) {
            String[][] data = new String[][]{
                    {"NA", "NA", "NA", "NA"}
            };
            System.out.println(FlipTable.of(headers, data));
        } else {
            Object[][] data = new Object[products.size()][5];
            for (int i = 0; i < products.size(); i++) {
                if (products.get(i).getCategory() == null) {
                    data[i] = new Object[]{"NA", products.get(i).getName(), String.valueOf(products.get(i).getPrice()), String.valueOf(products.get(i).getQuantity())};
                } else {
                    data[i] = new Object[]{String.valueOf(products.get(i).getCategory().getName()), products.get(i).getName(), String.valueOf(products.get(i).getPrice()), String.valueOf(products.get(i).getQuantity())};
                }
            }
            System.out.println(FlipTableConverters.fromObjects(headers, data));
        }
    }

    private void resetStaticIndex() {
        if (!products.isEmpty()) {
            Product.INDEX = products.get(products.size() - 1).getId();
        }
    }
}
