package Manager;

import IOFile.IOFile;
import Interface.CRUD;
import Model.Product;
import com.jakewharton.fliptables.FlipTable;

import java.io.Serializable;
import java.util.ArrayList;

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

    @Override
    public void delete() {

    }

    @Override
    public void update() {

        file.writeToFile(products,filePath);
    }

    @Override
    public void display() {
        if(products.isEmpty()) {
            String[] headers = {"ID", "Category", "Name", "Price", "Quantity"};
            String[][] data = new String[][]{
                        {"NA", "NA", "NA", "NA", "NA"}
                };
            System.out.println(FlipTable.of(headers, data));

        }else {
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
