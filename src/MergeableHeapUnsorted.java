import java.util.LinkedList;

/**
 * Heap type b - unsorted heap, numbers are kept by order of insertion.
 *
 * Sum up of methods and their complexity (explained more in the pdf)
 * Constructor: time - O(1), space - O(1)
 * Insert: time - O(1), space - O(1)
 * Minimum: time - O(1), space - O(1)
 * ExtractMin: time - O(n), space - O(1)
 * Union: time - O(n+m), space - O(1)
 * Print: time - O(n), space - O(1)
 */
public class MergeableHeapUnsorted extends Heap {

    protected Node tail;
    protected Node min;
    // this is used for the foreign lists - space complexity of O(m) when m is the number of lists made by user
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
     * Space complexity - O(1)
     * Time complexity - O(1)
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
     * Time complexity - O(1)
     * Space complexity - O(1)
     * @return (double) the minimum value in the heap.
     */
    public double minimum() {
        return this.min.key;
    }


    /**
     * Assuming the heap isn't empty, this function removes the smallest number from the heap without printing it.
     * Because we already track the minimum value, we know it's location and therefor can directly access it.
     * note: in case of duplicates, only the first is removed
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
    	// find a new minimum value, this is O(n)
        this.min = findMin();
    }


    /**
     * Merge this heap together with another given heap. the order of the merge is x1, x2, ..., xn, y1, y2, ..., ym
     * when x1, ..., xn are the different nodes of this heap
     * and y1, ..., ym are the different nodes of the given heap.
     *
     * Time complexity - O(n+m)
     * Space complexity - O(1), not counting the returned Heap
     * @param L2 the second heap to merge
     * @return a new merged heap
     */
    public MergeableHeapUnsorted union(Heap L2) {
        MergeableHeapUnsorted united = new MergeableHeapUnsorted();
        MergeableHeapUnsorted l2Casted = ((MergeableHeapUnsorted)L2);

        // connect the tail of this heap to the head of the second heap
        // then use the copy constructor to create a new heap with identical nodes in value
        // then separate back the tail and the head
        // total of O(n+m) due to the copy constructor
        this.tail.next = l2Casted.head;
        l2Casted.head.previous = this.tail;
        united.head = new Node(this.head);
        this.tail.next = null;
        l2Casted.head.previous = null;

        // find the tail of the united heap O(n+m)
        Node current = united.head;
        while (current.next != null) {
            current = current.next;
        }
        united.tail = current;

        // find the minimum of the united heap O(n)
        united.min = united.findMin();
        return united;
        // total of O(3n+m) = O(n+m) time complexity
    }


    /*
      Private function to find the smallest value in the heap
      used for Union and ExtractMin
      Time complexity - O(n)
      Space complexity - O(1)
      @return the Node containing the minimum value
     */
    private Node findMin() {
        Node currentMin = head;
        Node current = head;
        // loop on all nodes and update the minimum value every time a new one is found
        while(current != null) {
            if (current.key < currentMin.key) {
                currentMin = current;
            }
            current = current.next;
        }
        return currentMin;
    }

}
