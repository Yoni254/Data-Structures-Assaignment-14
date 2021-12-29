import java.util.LinkedList;

public class MergeableHeapUnsorted extends Heap {

    protected Node tail;
    protected Node min;
    protected static LinkedList<Node> heads = new LinkedList<>();

    /**
     * class builder, create an empty list
     * space complexity - O(1)
     * time complexity - O(1)
     */
    public MergeableHeapUnsorted() {
        this.head = null;
        this.min = new Node(Double.MAX_VALUE);

    }

    /**
     * inserts a new value into the heap to the end of it without sorting
     * @param num (double) the number to insert
     */
    public void insert(double num) {
        // if the heap is empty start a new one
        if (head == null) {
            head = new Node(num);
            tail = head;
            heads.add(head); 
        }
        else {
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
     * assuming the heap isn't empty this function returns the smallest value in the heap without deleting it
     * space complexity - O(1)
     * time complexity - O(1)
     * @return (double) the minimum value in the heap
     */
    public double minimum() {
        return this.min.key;
    }

    // O(n) bc findMin
    public void extractMin() {
    	if (this.min.previous == null)
    		head = min.next;
    	else if(this.min.next != null)
    		this.min.previous.next = this.min.next;
    	else
    		this.min.previous.next = null;
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
