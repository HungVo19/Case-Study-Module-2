package Manager;

import IOFile.IOFile;
import Interface.CRUD;
import Model.Bill;

import java.io.Serializable;
import java.util.ArrayList;

public class BillManager implements Serializable, CRUD<Bill> {
    private ArrayList<Bill> bills;
    private IOFile<Bill> file;
    private final String filePath = "src/Data/BillsList.txt";

    public BillManager() {
        file = new IOFile<Bill>();
        bills = (ArrayList<Bill>) file.readFromFile(filePath);
    }

    @Override
    public void add(Bill bill) {

    }

    public void add() {

    }

    public void delete() {

    }

    public void update() {

    }

    public void display() {

    }
}
