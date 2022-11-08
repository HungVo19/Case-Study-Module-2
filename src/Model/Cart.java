package Model;

import Manager.ProductManager;
import Manager.SystemManager;
import Validation.Validation;
import com.jakewharton.fliptables.FlipTable;
import com.jakewharton.fliptables.FlipTableConverters;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

public class Cart implements Serializable {
    private Account cartId;
    private Map<Product, Integer> cart;
    private double totalPrice;

    public Cart(Account account) {
        this.cartId = account;
        this.cart = new HashMap<>();
    }

    public Cart(Account cartId, Map<Product, Integer> cart) {
        this.cartId = cartId;
        this.cart = new HashMap<>();
    }

    public Account getCartId() {
        return cartId;
    }

    public void setCartId(Account cartId) {
        this.cartId = cartId;
    }

    public void addItem(Product product, int quantity) {
        Set<Product> productSet = cart.keySet();
        boolean check = false;
        for (Product p : productSet) {
            if (p.getId().equals(product.getId())) {
                int temp = cart.get(p);
                cart.put(p, (quantity + temp));
                check = true;
            }
        }
        if (!check) {
            cart.put(product, quantity);
        }
    }

    public void removeItem(Product product, int quantity) {
        Set<Product> productSet = cart.keySet();
        boolean check = false;
        for (Product p : productSet) {
            if (p.getId().equals(product.getId())) {
                int temp = cart.get(p);
                cart.put(p, (temp - quantity));
                check = true;
            }
        }
        if (!check) {
            System.out.println("Cart is empty!");;
        }
    }


    public void clearCart() {
        cart.clear();
    }

    public ArrayList<Product> getCart() {
        return (ArrayList<Product>) cart.keySet()
                .stream()
                .flatMap(item -> Collections.nCopies(cart.get(item), item).stream())
                .collect(Collectors.toList());
    }

    public void display() {
        Set<Product> productSet = cart.keySet();
        for (Product p : productSet) {
            p.display();
            System.out.println("Số lượng:" + cart.get(p));
        }

    }
}





