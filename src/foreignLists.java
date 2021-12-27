import java.util.LinkedList;

public class foreignLists extends MergeableHeapUnsorted{

    private static LinkedList<Double> numbers = new LinkedList<>();

    // O(m) when m is number of values inserted
    public void insert(double num) {
        if(numbers.indexOf(num) == -1) {
            numbers.add(num);
            super.insert(num);
        }
    }
}
