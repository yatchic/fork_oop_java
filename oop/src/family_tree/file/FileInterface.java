package family_tree.file;

import family_tree.family.FamilyTree;

import java.util.HashMap;

public interface FileInterface {
    HashMap<Integer, FamilyTree> readFromfileToFamilyTree(String fileName);

    void writeToFile(HashMap<Integer, FamilyTree> familyTree, String fileName);

    void print(String fileName);
}
