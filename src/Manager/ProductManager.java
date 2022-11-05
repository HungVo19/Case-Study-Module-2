package Manager;

import IOFile.IOFile;
import Interface.CRUD;
import MenuPrinter.MenuPrinter;
import Model.Category;
import Model.Product;
import Validation.Validation;
import com.jakewharton.fliptables.FlipTable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class ProductManager implements Serializable, CRUD<Product> {
    private IOFile<Product> file;
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
        System.out.println("Enter product name:");
        String name = scanner.nextLine();
        if (checkNameAvailability(name) == false) {
            int newQuantity = products.get(getIndexByName(name)).getQuantity() + 1;
            products.get(getIndexByName(name)).setQuantity(newQuantity);
            System.out.println("✅ Product " + name + "is already existed. It's quantity is increased by 1! ");
        } else {
            String price = "";
            do {
                System.out.println("⏩ Enter price");
                price = scanner.nextLine();
                if (Validation.checkNumberOnly(price) == false) {
                    MenuPrinter.wrongInput();
                } else if (price.isEmpty()) {

                }
            } while (Validation.checkNumberOnly(price) == false);
            String quantity = "";
            do {
                System.out.println("⏩ Enter quantity:");
                quantity = scanner.nextLine();
                if (Validation.checkNumberOnly(price) == false) {
                    MenuPrinter.wrongInput();
                }  else if (price.isEmpty()) {
                    quantity = "0";
                }
            } while (Validation.checkNumberOnly(price) == false);
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
                    products.add(new Product(name, Double.parseDouble(price), Integer.parseInt(quantity), categories.get(0)));
                    break;
                case 2:
                    products.add(new Product(name, Double.parseDouble(price), Integer.parseInt(quantity), categories.get(1)));
                    break;
                case 3:
                    products.add(new Product(name, Double.parseDouble(price), Integer.parseInt(quantity), categories.get(2)));
                    break;
                case 0:
                    break;
            }
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

    private boolean checkNameAvailability(String name) {
        String lowerCaseName = name.toLowerCase();
        boolean flag = true;
        for (Product p : products) {
            if (p.getName().contains(lowerCaseName)) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    @Override
    public void delete() {

    }

    @Override
    public void update() {

        file.writeToFile(products, filePath);
    }

    @Override
    public void display() {
        if (products.isEmpty()) {
            String[] headers = {"ID", "Category", "Name", "Price", "Quantity"};
            String[][] data = new String[][]{
                    {"NA", "NA", "NA", "NA", "NA"}
            };
            System.out.println(FlipTable.of(headers, data));

        } else {
            for (Product p : products) {
                p.display();
            }
        }
    }

    private void resetStaticIndex() {
        if (!products.isEmpty()) {
            Product.INDEX = products.get(products.size() - 1).getId();
        }
    }
}
