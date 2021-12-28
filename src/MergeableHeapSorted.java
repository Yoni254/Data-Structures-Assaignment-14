public class MergeableHeapSorted extends Heap {

    private Node head;
    private Node tail;

    public MergeableHeapSorted() {
        this.head = null;
    }

    public void insert(double num) {
        if (head == null) {
            head = new Node(num);
            tail = head;
            return;
        }
        // should work
        Node current = head;

        if (current.key > num) {
            Node temp = head;
            head = new Node(num);
            head.next = temp;
            return;
        }

        while(current.next != null) {
            if (current.key < num && num < current.next.key) {
                Node temp = current.next;
                current.next = new Node(num);
                current.next.next = temp;
                return;
            }
            current = current.next;
        }
        tail.next = new Node(num);
        tail = tail.next;
    }

    public double minimum() {
        return head.key;
    }

    public void extractMin() {
        head = head.next;
    }

    public MergeableHeapSorted union(Heap L2) {
        MergeableHeapSorted united = new MergeableHeapSorted();
        // some wierd merge sort like algorithm
        Node currentL1 = this.head;
        Node currentL2 = ((MergeableHeapSorted)L2).head;
        united.insert(Double.MAX_VALUE);

        // start building from the end, every time insert the smallest value of the 2 pointers into the tail and advance further
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

        united.tail.next = (currentL1 == null) ? new Node(currentL2) : new Node(currentL1);
        united.head = united.head.next;
        return united;
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        Node current = head;
        while(current != null) {
            str.append(current.key).append(", ");
            current = current.next;
        }
        return str.substring(0, str.length() - 2);
    }
}
