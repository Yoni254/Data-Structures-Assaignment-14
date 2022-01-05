/**
 *
 */
public class Node {

    public double key;
    public Node next;

    public Node(double key) {
        this.key = key;
    }

    // clone
    public Node(Node source) {
        this.key = source.key;
        Node current = this;
        Node sourceCurrent = source;
        while (sourceCurrent.next != null) {
            current.next = new Node(sourceCurrent.next.key);
            current = current.next;
            sourceCurrent = sourceCurrent.next;

        }
    }

}
