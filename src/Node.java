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
            this.next = new Node(source.next);
        }
    }


    public void setChild(Node node) {
        this.next = node;
    }

}
