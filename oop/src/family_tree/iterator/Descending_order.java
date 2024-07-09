package family_tree.iterator;

import org.jetbrains.annotations.NotNull;

import java.util.*;

public class Descending_order implements Iterable<Map.Entry<Integer, String>> {

    private final HashMap<Integer, String> age = new HashMap<>();


    /**
     * Добавляет в age из familyTree найденные отношения при запросе методом question.
     */
    public void add(int key, String value) {

        age.put(key, value);

    }


    /**
     *Сортирует по убыванию age
     */
    @NotNull
    @Override
    public Iterator<Map.Entry<Integer, String>> iterator() {
        List<Map.Entry<Integer, String>> sortedEntries = new ArrayList<>(age.entrySet());
        sortedEntries.sort(new Descending_order.AgeComparator());

        return sortedEntries.iterator();
    }

    /**
     * В момент сортировки сравнивает значения.
     */
    class AgeComparator implements Comparator<Map.Entry<Integer, String>> {
        @Override
        public int compare(Map.Entry<Integer, String> entry1, Map.Entry<Integer, String> entry2) {
            return entry2.getKey().compareTo(entry1.getKey());
        }
    }


    /**
     * Читает отсортированный age и выводит в консоль.
     */
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
