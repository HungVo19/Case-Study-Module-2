package Manager;

import IOFile.IOFile;
import Model.Account;
import Model.Cart;
import Model.Product;

import java.io.Serializable;
import java.util.ArrayList;

public class CartManager implements Serializable {
    private final IOFile<Cart> file;
    private ArrayList<Cart> carts;
    private final String filePath = "src/Data/CartsList.txt";

    public CartManager() {
        file = new IOFile<Cart>();
        carts = (ArrayList<Cart>) file.readFromFile(filePath);
    }

    public ArrayList<Cart> getCarts() {
        return carts;
    }

    public void setCarts(ArrayList<Cart> carts) {
        this.carts = carts;
    }
}
