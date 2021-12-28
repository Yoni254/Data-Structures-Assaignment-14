/**
 * The basic layout of a heap class complete with all the abstract commands needed.
 *
 * this is used for easier writing and polymorphism.
 * while this doesn't really function like a heap, we call it that because that's what they call it in the assignment
 */
public abstract class Heap {
    public abstract void insert(double num);
    public abstract double minimum();
    public abstract void extractMin();
    public abstract Heap union(Heap L2);
}
