package Model;

import Manager.ProductManager;
import Manager.SystemManager;
import Validation.Validation;
import com.jakewharton.fliptables.FlipTable;
import com.jakewharton.fliptables.FlipTableConverters;
import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.asciitable.CWC_LongestWordMin;

import java.io.Serializable;
import java.time.Clock;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Cart implements Serializable {
    private Account cartId;
    private ArrayList<Product> cart;
    private double totalPrice;

    public Cart(Account account) {
        this.cartId = account;
        this.cart = new ArrayList<>();
    }

    public ArrayList<Product> getCart() {
        return cart;
    }

    public void setCart(ArrayList<Product> cart) {
        this.cart = cart;
    }

    public Account getCartId() {
        return cartId;
    }

    public void setCartId(Account cartId) {
        this.cartId = cartId;
    }

    public void addITem(Product product) {
        cart.add(product);
    }

    public void addItem(Product product, int quantity) {
        if (!checkItemExist(product)) {
            cart.add(product);
            cart.get(cart.size() - 1).setQuantity(quantity);
        } else {
            int index = -1;
            for (int i = 0; i < cart.size(); i++) {
                if (cart.get(i).getName().equals(product.getName())) {
                    index = i;
                    break;
                }
            }
            int temp = cart.get(index).getQuantity();
            cart.get(index).setQuantity(temp + quantity);
        }
    }

    public void removeItem(int index, int quantity) {
        int temp = cart.get(index).getQuantity();
        if (temp <= quantity) {
            cart.remove(index);
        } else {
            cart.get(index).setQuantity(temp - quantity);
        }
    }

    public boolean checkItemExist(Product product) {
        for (Product p : cart) {
            if (p.getName().equals(product.getName())) {
                return true;
            }
        }
        return false;
    }


    public void clearCart() {
        cart.clear();
    }

    public void display() {
        String[] headers = {"Index", "Category", "Name", "Price", "Quantity"};
//        if (cart.isEmpty()) {
//            String[][] data = new String[][]{
//                    {"NA", "NA", "NA","NA"}
//            };
//            System.out.println(FlipTable.of(headers, data));
//        } else {
        Object[][] data = new Object[cart.size()][5];
        for (int i = 0; i < cart.size(); i++) {
//                if (cart.get(i).getCategory() == null) {
//                    data[i] = new Object[]{"NA", cart.get(i).getName(), String.valueOf(cart.get(i).getPrice())};
////                } else {
            data[i] = new Object[]{i, String.valueOf(cart.get(i).getCategory().getName()), cart.get(i).getName(), String.valueOf(cart.get(i).getPrice()), String.valueOf(cart.get(i).getQuantity())};
//                }
        }
        System.out.println(FlipTableConverters.fromObjects(headers, data));
    }

    public Double getTotalAmount() {
        double amount = 0;
        for (Product p : cart) {
            amount += p.getAmount();
        }
        return amount;
    }

    public Integer getTotalQuantity() {
        int quantity = 0;
        for (Product p : cart) {
            quantity += p.getQuantity();
        }
        return quantity;
    }

    public void checkOut() {
        String[] headers = {"Name", "Price", "Quantity"};
        Object[][] data = new Object[cart.size()][5];
        for (int i = 0; i < cart.size(); i++) {
            data[i] = new Object[]{cart.get(i).getName(),
                    String.valueOf(cart.get(i).getPrice()), String.valueOf(cart.get(i).getQuantity())};
        }
        System.out.println(FlipTableConverters.fromObjects(headers, data));

        AsciiTable at = new AsciiTable();
        at.addRule();
        at.addRow("Purchase successfully!");
        at.addRule();
        at.addRow("Total Quantity " + getTotalQuantity() + " products");
        at.addRule();
        at.addRow("Total amount " + getTotalAmount() + "$" );
        at.addRule();
        at.addRow("Your bill is created at " + LocalDate.now(Clock.systemUTC()));
        at.addRule();
        at.addRow("Thank you for shopping with us");
        at.addRule();
        String rend = at.render();
        System.out.println(rend);
    }

}






