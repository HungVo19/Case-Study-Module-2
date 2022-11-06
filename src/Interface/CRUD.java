package Interface;

import Model.Account;
import Model.Product;

import java.util.ArrayList;

public interface CRUD<E> {
    public void add(E e);

    public void delete();

    public void update();

    public void display();

}
