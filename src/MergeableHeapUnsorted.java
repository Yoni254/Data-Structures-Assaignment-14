import java.util.LinkedList;

public class MergeableHeapUnsorted extends Heap {

    private Node head;
    private Node tail;
    private Node min;

    // O(1)
    public MergeableHeapUnsorted() {
        this.head = null;
        this.min = new Node(Double.MAX_VALUE);

    }

    // O(1)
    public void insert(double num) {
        if (head == null) {
            head = new Node(num);
            tail = head;
        }
        else {
            Node newNode = new Node(num);
            newNode.previous = tail;
            tail.setChild(newNode);
            tail = tail.next;
        }

        // keep track of the minimum value
        if (num < this.min.key) {
            this.min = tail;
        }
    }

    // O(1)
    public double minimum() {
        return this.min.key;
    }

    // O(n) bc findMin
    public void extractMin() {
        min.previous.next = min.next;
        this.min = findMin();
    }

    // O(1)
    public MergeableHeapUnsorted union(Heap L1, Heap L2) {
        MergeableHeapUnsorted united = new MergeableHeapUnsorted();
        united.head = ((MergeableHeapUnsorted)L1).head;
        united.tail = ((MergeableHeapUnsorted)L1).tail;
        united.tail.next = ((MergeableHeapUnsorted)L2).head;
        united.tail = ((MergeableHeapUnsorted)L2).tail;
        united.min = (((MergeableHeapUnsorted)L1).min.key < ((MergeableHeapUnsorted)L2).min.key) ?
                ((MergeableHeapUnsorted)L1).min : ((MergeableHeapUnsorted)L2).min;
        return united;
    }

    // O(n)
    public String toString() {
        String str = "";
        Node current = head;
        while(current != null) {
            str += current.key + ", ";
            current = current.next;
        }
        return str.substring(0, str.length() - 2);
    }

    // O(n)
    private Node findMin() {
        Node currentMin = head;
        Node current = head;
        while(current != null) {
            if (current.key < currentMin.key) {
                currentMin = current;
            }
            current = current.next;
        }
        return currentMin;
    }

}
