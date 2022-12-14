package IOFile;

import javax.imageio.IIOException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class IOFile<E> {
//    public void writeFile(ArrayList<E> list, String path) {
//        File file = new File(path);
//        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file))) {
//            if (!file.exists()) {
//                file.createNewFile();
//            }
//            objectOutputStream.writeObject(file);
//        } catch (IOException ex) {
//            System.out.println(ex.getMessage());
//        }
//    }
//
//    public ArrayList<E> readFile(String path) {
//        File file = new File(path);
//        ArrayList<E> list = new ArrayList<>();
//        try (FileInputStream fileInputStream = new FileInputStream(file)) {
//            if (fileInputStream.available() > 0) {
//                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
//                list = (ArrayList<E>) objectInputStream.readObject();
//                objectInputStream.close();
//            }
//            return list;
//        } catch (IOException | ClassNotFoundException ex) {
//            ex.printStackTrace();
//        }
//        return list;
//    }

    public List<E> readFromFile(String path) {
        List<E> lists = new ArrayList<>();
        try {
            File file = new File(path);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileInputStream fileInput = new FileInputStream(file);
            if (fileInput.available() > 0) {
                ObjectInputStream objectInput = new ObjectInputStream(fileInput);
                lists = (List<E>) objectInput.readObject();
                objectInput.close();
            }
            return lists;
        } catch (IOException | ClassNotFoundException exception) {
            System.out.println(exception.getMessage());
        }
        return lists;
    }

    public void writeToFile(List<E> lists, String path) {
        try (FileOutputStream fileOutput = new FileOutputStream(path)) {
            ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);
            objectOutput.writeObject(lists);
            objectOutput.close();
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }

    }
}
