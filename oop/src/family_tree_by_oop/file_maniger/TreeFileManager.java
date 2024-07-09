package family_tree_by_oop.file_maniger;

import family_tree_by_oop.order.TreeSorter;
import java.io.*;


public class TreeFileManager<A, B, C, D, E, F> implements InterfaceForTreeFileManager, Serializable {
    private static final long serialVersionUID = 1L;
    private String otvet = "";
    private TreeSorter<A, B, C, D, E, F> treeSorter;

    public void setTreeSorter(TreeSorter<A, B, C, D, E, F> treeSorter) {
        this.treeSorter = treeSorter;
    }
@Override
    public void readFromFile(String fileName) {
        try (FileInputStream fileIn = new FileInputStream(fileName);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            treeSorter = (TreeSorter<A, B, C, D, E, F>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void writeToFile(String fileName) {
        try (FileOutputStream fileOut = new FileOutputStream(fileName);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(treeSorter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return otvet;
    }



}
