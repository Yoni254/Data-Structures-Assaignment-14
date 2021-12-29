import java.util.LinkedList;

/**
 * Heap type c - unsorted and with different values this acts the same as an
 * unsorted heap but makes sure that any inputed number hasn't been used yet in
 * *any* of the heaps so far note that there are a few ways to interoperate this
 * question, we picked this one because that's what we understood of the term -
 * "׳–׳¨׳™׳�"
 */
public class ForeignHeapUnsorted extends MergeableHeapUnsorted {
	

	/**
	 * Insert a value into the heap. if the number has already been used in any
	 * other heap then the function does nothing
	 * space complexity - O(1), no extra variables
	 * time complexity - O(m) when m is the total length of all heaps
	 * @param num (double) value to insert into the heap
	 */
	public void insert(double num) {
		// for every single heap, check if the given num hasn't been used it
		// this is because the heaps are foreign and therefor no values should repeat
		for (Node head : heads) {
			Node Current = head;
			while (Current != null) {
				if (Current.key == num)
					return;
				Current = Current.next;
			}
		}
		super.insert(num);
	}
}
