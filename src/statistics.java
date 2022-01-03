public class statistics {
    public static void main(String[] args){
        MergeableHeapUnsorted heap = new MergeableHeapUnsorted();
        MergeableHeapUnsorted heap2 = new MergeableHeapUnsorted();
        int n = 1000000;
        for(int i=0; i<n; i++){
            heap.insert(i);
            heap2.insert(n-1);
        }
        double start = System.currentTimeMillis();
        heap.union(heap2);
        double finish = System.currentTimeMillis();
        System.out.println(finish - start);
    }
}
