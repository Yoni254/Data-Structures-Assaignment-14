public abstract class Heap {
    public abstract void insert(double num);
    public abstract double minimum();
    public abstract void extractMin();
    public abstract Heap union(Heap L1, Heap L2);
}
