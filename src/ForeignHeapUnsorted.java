/**
 * Heap type c - unsorted and with different values this acts the same as an
 * unsorted heap but makes sure that any inputed number hasn't been used yet in
 * *any* of the heaps so far note that there are a few ways to interoperate this
 * question, we picked this one because that's what we understood of the term - "Foreign".
 *
 * Sum up of methods and their complexity (explained more in the pdf)
 * Constructor: time - O(1), space - O(1)
 * Insert: time - O(n), space - O(1)
 * Minimum: time - O(1), space - O(1)
 * ExtractMin: time - O(n), space - O(1)
 * Union: time - O(n), space - O(1)
 * Print: time - O(n), space - O(1)
 */
public class ForeignHeapUnsorted extends MergeableHeapUnsorted {
	

	/**
	 * Insert a value into the heap. if the number has already been used in any
	 * other heap then the function does nothing.
	 * Space complexity - O(1), no extra variables
	 * Time complexity - O(m) when m is the total length of all heaps
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
		// continue inserting as normal
		super.insert(num);
	}
}
