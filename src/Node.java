/**
 *
 */
public class Node {

    public double key;
    public Node next;
    public Node previous;

    public Node(double key) {
        this.key = key;
    }

    // clone
    public Node(Node source) {
        this.key = source.key;
        if (source.next != null) {
            Node next = new Node(source.next);
            this.next = next;
            next.previous = this;
        }
    }

}
