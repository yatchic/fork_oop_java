package other_tree.file;


import other_tree.tree.Tree;

import java.io.*;

import java.util.Map;
import java.util.TreeMap;

public class File<T1, T2, T3, T4, T5, T6> implements FileInterface {
    private String otvet = "";
    /**
     * Читает содержимое с жесткого диска и перезаписывает treeMap.
     */

        public TreeMap<Integer, Tree<T1, T2, T3, T4, T5, T6>> readFromfileToTree (String fileName){
            try (FileInputStream fileIn = new FileInputStream(fileName);
                 ObjectInputStream in = new ObjectInputStream(fileIn)) {
                return (TreeMap<Integer, Tree<T1, T2, T3, T4, T5, T6>>) in.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                return null;
            }
        }



    /**
     * Сохраняет содержимое treeMap на жесткий диск.
     *
     * @param fileName
     */

    public void  writeToFile(TreeMap<Integer, Tree<T1,T2,T3, T4,T5,T6>> familyTree, String fileName) {

        try (FileOutputStream fileOut = new FileOutputStream(fileName);

             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(familyTree);

        } catch (IOException e) {

            e.printStackTrace();
        }
    }



    /**
     * Читает из файла и выводит в консоль содержимое ранее сохраненной treeMap.
     */

    @Override
    public void print(String fileName) {
        try (FileInputStream fileIn = new FileInputStream(fileName);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            TreeMap<Integer, Tree<T1, T2, T3, T4, T5, T6>> tree = (TreeMap<Integer, Tree<T1, T2, T3, T4, T5, T6>>) in.readObject();

            for (Map.Entry<Integer, Tree<T1, T2, T3, T4, T5, T6>> entry : tree.entrySet()) {
                Integer key = entry.getKey();
                Tree<T1, T2, T3, T4, T5, T6> value = entry.getValue();
                otvet = (String) value.getA()+", "+(String) value.getB()+", "+(String) value.getC()+", "+(String) value.getD()+", "+(String) value.getE()+", "+(String) value.getF();
                System.out.println(toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return otvet;
    }
}
