package family_tree.file;


import family_tree.family.FamilyTree;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class FileClass implements FileInterface {
            private String otvet="";

      /**
     * Перезаписывает содержимым с жесткого диска в HashMap.
     * @param fileName
     */
    @Override
    public   HashMap<Integer, FamilyTree> readFromfileToFamilyTree(String fileName) {
        File f = new File(fileName);
        try (FileInputStream fileIn = new FileInputStream(fileName);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
               return( HashMap<Integer, FamilyTree>) in.readObject();

        } catch ( Exception e) {
            e.printStackTrace();
        }
        return null;
    }




    /**
     * Сохраняет содержимое familyTree на жесткий диск.
     * @param fileName
     */
    @Override
    public void  writeToFile(HashMap<Integer, FamilyTree> familyTree, String fileName) {

      try (FileOutputStream fileOut = new FileOutputStream(fileName);

             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(familyTree);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Читает из файла и выводит в консоль содержимое ранее сохраненной hashMap.
     */
    @Override

    public void print(String fileName) {
        try (FileInputStream fileIn = new FileInputStream(fileName);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            HashMap<Integer, FamilyTree> familyTree = (HashMap<Integer, FamilyTree>) in.readObject();


            for (Map.Entry<Integer, FamilyTree> entry : familyTree.entrySet()) {
                Integer key = entry.getKey();
                FamilyTree value = entry.getValue();
                otvet= key+", "+value.person + ", " + value.relationType + ", " + value.relatedTo ;
                System.out.println(toString());

            }
        } catch ( Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return  otvet;
    }
}//конец класса
