import java.util.LinkedList;

/**
 * Heap type b - unsorted heap, numbers are kept by order of insertion
 */
public class MergeableHeapUnsorted extends Heap {

    protected Node tail;
    protected Node min;

    // this is used for the foreign lists
    protected static LinkedList<Node> heads = new LinkedList<>();

    /**
     * Class constructor, create an empty list.
     * Space complexity - O(1)
     * Time complexity - O(1)
     */
    public MergeableHeapUnsorted() {
        this.head = null;
        this.min = new Node(Double.MAX_VALUE);

    }

    /**
     * Inserts a new value into the heap to the end of it without sorting.
     * @param num (double) the number to insert.
     */
    public void insert(double num) {
        // if the heap is empty start a new one
        if (head == null) {
            head = new Node(num);
            tail = head;
            heads.add(head); 
        } else {
            // else, insert the node at the end and set it to the tail
            Node newNode = new Node(num);
            newNode.previous = tail;
            tail.next = newNode;
            tail = tail.next;
        }

        // keep track of the minimum value
        if (num < this.min.key) {
            this.min = tail;
        }
    }

    /**
     * Assuming the heap isn't empty this function returns the smallest value in the heap without deleting it.
     * Space complexity - O(1)
     * Time complexity - O(1)
     * @return (double) the minimum value in the heap.
     */
    public double minimum() {
        return this.min.key;
    }

    /**
     * Assuming the heap isn't empty, this function removes the smallest number from the heap without printing it.
     * Because we already track the minimum value, we know it's location and therefor can directly access it.
     * Time complexity - O(n) (finding the new minimum)
     * Space complexity - O(1)
     */
    public void extractMin() {
        if (min == null) return;

        if (min.previous == null) {
            // in case the minimum value is the head
            min.next.previous = null;
            head = min.next;
        } else if (min.next == null) {
            // in case the minimum value is the tail
            min.previous.next = null;
            tail = min.previous;
        } else {
            // in case the minimum value is in the middle
            min.previous.next = min.next;
            min.next.previous = min.previous;
        }
    	// find a new minimum value
        this.min = findMin();
    }

    // O(n)
    public MergeableHeapUnsorted union(Heap L2) {
        MergeableHeapUnsorted united = new MergeableHeapUnsorted();
        MergeableHeapUnsorted l2Casted = ((MergeableHeapUnsorted)L2);

        // connect L2 to this heap then create a copy and disconnect the two
        this.tail.next = l2Casted.head;
        l2Casted.head.previous = this.tail;
        united.head = new Node(this.head);
        this.tail.next = null;
        l2Casted.head.previous = null;

        // find the tail of the united heap
        Node current = united.head;
        while (current.next != null) {
            current = current.next;
        }
        united.tail = current;

        // find the minimum
        united.min = united.findMin();
        return united;
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
