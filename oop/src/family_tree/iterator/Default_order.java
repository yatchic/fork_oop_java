package family_tree.iterator;

import org.jetbrains.annotations.NotNull;

import java.util.*;

public class Default_order implements Iterable<Map.Entry<Integer, String>> {

    private final HashMap<Integer, String> age = new HashMap<>();



    public void add(int key, String value) {

        age.put(key, value);

    }


    /**
     * По-хорошему здесь никакой сортировки нет, в каком порядке были найдены значения в таком выводятся в консоль.
     */
    @NotNull
    @Override
    public Iterator<Map.Entry<Integer, String>> iterator() {
        List<Map.Entry<Integer, String>> sortedEntries = new ArrayList<>(age.entrySet());


        return sortedEntries.iterator();
    }


    class AgeComparator implements Comparator<Map.Entry<Integer, String>> {
        @Override
        public int compare(Map.Entry<Integer, String> entry1, Map.Entry<Integer, String> entry2) {
            return entry1.getKey().compareTo(entry2.getKey());
        }
    }



    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (Iterator<Map.Entry<Integer, String>> it = iterator(); it.hasNext(); ) {
            Map.Entry<Integer, String> entry = it.next();
            Integer personId = entry.getKey();
            String personAge = entry.getValue();
            sb.append(personId).append("  ").append(personAge).append("\n");
        }

        System.out.println(sb.toString());
        return sb.toString();
    }

}
