/**
 * Heap type a - sorted by key value in ascending order.
 *
 * Sum up of methods and their complexity (explained more in the pdf)
 *  * Constructor: time - O(1), space - O(1)
 *  * Insert: time - O(n), space - O(1)
 *  * Minimum: time - O(1), space - O(1)
 *  * ExtractMin: time - O(1), space - O(1)
 *  * Union: time - O(n+m), space - O(1)
 *  * Print: time - O(n), space - O(1)
 */
public class MergeableHeapSorted extends Heap {

    private Node tail;


    /**
     * Class constructor, create an empty list.
     * Space complexity - O(1)
     * Time complexity - O(1)
     */
    public MergeableHeapSorted() {
        this.head = null;
    }


    /**
     * Inserts a new value into the correct location inside the sorted heap
     * Time complexity - O(n)
     * Space complexity - O(1)
     * @param num (double) the number to insert.
     */
    public void insert(double num) {
        // if the heap is empty
        if (head == null) {
            head = new Node(num);
            tail = head;
            return;
        }

        // if the num is the smallest value, set it as the new head
        Node current = head;
        if (current.key > num) {
            Node temp = head;
            head = new Node(num);
            head.next = temp;
            return;
        }

        // run across all nodes until a spot where the one next is bigger yet the one before is smaller equal and insert there
        while(current.next != null) {
            if (current.key <= num && num < current.next.key) {
                Node temp = current.next;
                current.next = new Node(num);
                current.next.next = temp;
                return;
            }
            current = current.next;
        }

        // if non found then place the number at the end of the heap
        tail.next = new Node(num);
        tail = tail.next;
    }


    /**
     * Assuming the heap isn't empty this function returns the smallest value in the heap without deleting it.
     * Because the heap is sorted, the minimum value is the head
     * Time complexity - O(1)
     * Space complexity - O(1)
     * @return (double) the minimum value in the heap.
     */
    public double minimum() {
        return head.key;
    }


    /**
     * Assuming the heap isn't empty, this function removes the smallest number from the heap without printing it.
     * Because the heap is sorted, the minimum is the first number and the one after it is the new minimum
     * note: in case of duplicates, only the first is removed
     * Time complexity - O(1)
     * Space complexity - O(1)
     */
    public void extractMin() {
        head = head.next;
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
    public MergeableHeapSorted union(Heap L2) {
        MergeableHeapSorted united = new MergeableHeapSorted();
        // pointers to the heads
        Node currentL1 = this.head;
        Node currentL2 = L2.head;
        // placeholder head
        united.insert(Double.MAX_VALUE);

        // start building from the end, every time insert the smallest value of the 2 pointers into the tail and advance further
        // this is similar to the part combining 2 arrays in a merge sort algorithm
        while (currentL1 != null && currentL2 != null) {
            if (currentL1.key < currentL2.key) {
                united.tail.next = new Node(currentL1.key);
                united.tail = united.tail.next;
                currentL1 = currentL1.next;
            }
            else {
                united.tail.next = new Node(currentL2.key);
                united.tail = united.tail.next;
                currentL2 = currentL2.next;
            }
        }
        // when one of the pointers reached the end of thr heap, insert what's left of the second one into the end
        united.tail.next = (currentL1 == null) ? new Node(currentL2) : new Node(currentL1);
        united.tail.next.previous = united.tail; // this is not handled by the constructor

        // find the new tail of the united heap O(n+m)
        Node current = united.tail;
        while (current.next != null) {
            current = current.next;
        }
        united.tail = current;

        // remove the placeholder head
        united.head = united.head.next;
        return united;
    }
}
