/**
 * The basic layout of a heap class complete with all the abstract commands needed.
 *
 * this is used for easier writing and polymorphism.
 * while this doesn't really function like a heap, we call it that because that's what they call it in the assignment.
 */
public abstract class Heap {
    protected Node head;
    public abstract void insert(double num);
    public abstract double minimum();
    public abstract void extractMin();
    public abstract Heap union(Heap L2);

    /**
     * Converts the Heap into a string of numbers seperated by ", ".
     * @return string representing the heap.
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        Node current = head;
        // run on every node connected to the head and append the key to the string
        while(current != null) {
            str.append(current.key).append(", ");
            current = current.next;
        }
        // remove the last ", " and return the result
        return str.substring(0, str.length() - 2);
    }
}
