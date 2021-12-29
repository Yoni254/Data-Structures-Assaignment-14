import java.util.LinkedList;

/**
 * Heap type c - unsorted and with different values this acts the same as an
 * unsorted heap but makes sure that any inputed number hasn't been used yet in
 * *any* of the heaps so far note that there are a few ways to interoperate this
 * question, we picked this one because that's what we understood of the term -
 * "׳–׳¨׳™׳�"
 */
public class ForeignHeapUnsorted extends MergeableHeapUnsorted {

	// stores all numbers we've had so far - space complexity of O(n)
	

	/**
	 * Insert a value into the heap. if the number has already been used in any
	 * other heap then the function does nothing
	 * 
	 * @param num (double) value to insert into the heap
	 */

	public void insert(double num) {
		for (Node head : heads) {
			Node Current = head;
			if (Current != null) {
				while (Current != null) {
					if (Current.key == num)
						return;
					Current = Current.next;
				}
			}
		}
		super.insert(num);
	}
}
