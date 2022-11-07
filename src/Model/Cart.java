package Model;

import Manager.ProductManager;
import Manager.SystemManager;
import Validation.Validation;
import com.jakewharton.fliptables.FlipTable;
import com.jakewharton.fliptables.FlipTableConverters;

import java.io.Serializable;
import java.util.ArrayList;

public class Cart implements Serializable {
    private Account cartId;
    private ArrayList<Product> cart;
    private double totalPrice;
    private ProductManager productManager;

    public Cart(Account account, SystemManager systemManager) {
        this.cartId = account;
        this.cart = new ArrayList<>();
        this.productManager = systemManager.getProductManager();
    }

    public Account getCartId() {
        return cartId;
    }

    public void setCartId(Account cartId) {
        this.cartId = cartId;
    }

    public ArrayList<Product> getCart() {
        return cart;
    }

    public void setCart(ArrayList<Product> cart) {
        this.cart = cart;
    }

    public void addItem(int index, int quantity) {
        for (Product p : cart) {
            if (p.equals(productManager.getProducts().get(index))) {
                int temp = p.getQuantity();
                p.setQuantity(temp + quantity);
                updateInventory();
                return;
            }
        }
        Product tempP = productManager.getProducts().get(index);
        cart.add(tempP);
        tempP.setQuantity(quantity);
        updateInventory();
    }

    public void removeItem(int index, int quantity) {
        int temp = cart.get(index).getQuantity();
        cart.get(index).setQuantity(temp - quantity);
        updateInventory();
    }

    public void checkOut() {
        display();
        if (!Validation.checkYN("Confirm your purchase (Y/N)")) {
            return;
        } else {
            System.out.println("Purchase successfully!");
            updateInventory();
        }
    }


    public void displayPurchaseHistory() {

    }

    public void updateInventory() {
        for (int i = 0; i < productManager.getProducts().size(); i++) {
            for (Product product : cart) {
                if (productManager.getProducts().get(i).equals(product)) {
                    int temp = productManager.getProducts().get(i).getQuantity();
                    productManager.getProducts().get(i).setQuantity(temp - product.getQuantity());
                }
            }
        }
        productManager.getFile().writeToFile(productManager.getProducts(), "src/Data/ProductsList.txt");
    }

    public double getTotalPrice() {
        double amount = 0;
        for (Product p : cart) {
            amount += p.getAmount();
        }
        return amount;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void display() {
        String[] headers = {"Name", "Price", "Quantity", "Amount"};
        Object[][] data = new Object[cart.size() + 1][4];
        for (int i = 0; i < cart.size(); i++) {
            data[i] = new Object[]{cart.get(i).getName(), String.valueOf(cart.get(i).getPrice()), String.valueOf(cart.get(i).getQuantity()), String.valueOf(cart.get(i).getAmount())};
        }
        data[cart.size()] = new Object[]{"", "", "Your Total", getTotalPrice()};
        System.out.println(FlipTableConverters.fromObjects(headers, data));
    }
}

