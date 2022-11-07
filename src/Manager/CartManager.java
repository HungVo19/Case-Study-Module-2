package Manager;

import IOFile.IOFile;
import Model.Account;
import Model.Cart;
import Model.Product;

import java.io.Serializable;
import java.util.ArrayList;

public class CartManager implements Serializable {
    private IOFile<ArrayList<Cart>> file;
    private ArrayList<Cart> bills;
    public CartManager(Account account, SystemManager systemManager) {
        file = new IOFile<ArrayList<Cart>>();
        bills = new ArrayList<>();
    }

    public void display() {
        for (Cart b: bills) {
            b.display();
        }
    }

}
