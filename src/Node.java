public class Node {

    public double key;
    public Node next;
    public Node previous;

    public Node(double key) {
        this.key = key;
    }

    public void setChild(Node node) {
        this.next = node;
    }

}
