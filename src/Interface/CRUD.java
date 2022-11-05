package Interface;

import Model.Account;

public interface CRUD<E> {
    public void add(E e);

    public void delete();

    public void update();

    public void display();

}
