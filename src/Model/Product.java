package Model;

import com.jakewharton.fliptables.FlipTable;

import java.io.Serializable;

public class Product implements Serializable {
    public static Long INDEX = Long.valueOf(0);
    private Long id;
    private String name;
    private Double price;
    private Integer quantity;
    private Category category;

    public Product(String name, Double price, Integer quantity, Category category) {
        this.id = Long.valueOf(++INDEX);
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void decreaseQuantity(int quantity) {
        int temp = getQuantity();
        setQuantity(temp -quantity);
    }

    public void increaseQuantity(int quantity) {
        int temp = getQuantity();
        setQuantity(temp+quantity);
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Double getAmount() {
        return price * quantity;
    }

    public void display () {
        String[] headers = {"ID", "Category", "Name", "Price", "Quantity"};
        String[][] data;
        if (category != null) {
            data = new String[][]{
                    {String.valueOf(this.id), String.valueOf(this.category.getName()), this.name, String.valueOf(this.price), String.valueOf(this.quantity)}
            };
        } else {
            data = new String[][]{
                    {String.valueOf(this.id), "NA", this.name, String.valueOf(this.price), String.valueOf(this.quantity)}
            };
        }
        System.out.println(FlipTable.of(headers, data));
    }
}
