package sort;

public class Heap {
	
	/**
	 * Swaps 2 Comparables in an array.
	 * @param a - the array of Comparables.
	 * @param i - the index of the first Comparable to be swapped.
	 * @param j - the index of the second Comparable to be swapped.
	 */
	private static void exch(Comparable[] a, int i, int j) {
		Comparable t = a[i];
		a[i] = a[j];
		a[j] = t;
	}
	
	/**
	 * Checks to see if one Comparable is less than another.
	 * @param v The first Comparable.
	 * @param w The second Comparable.
	 * @return Returns true if Comparable v is less than Comparable w.
	 */
	private static boolean less(Comparable v, Comparable w) {
		return v.compareTo(w) < 0;
	}
	
	private static void sink(Comparable[] a, int k, int n) {
		while (2*k <= n) {
			int j = 2*k;
			if (j < n && less(a[j], a[j+1])) j++;
			if (!less(a[k], a[j])) break;
			exch(a, k, j);
			k = j;
			
		}
	}
	
	/**
	 * heap sort using Comparable
	 * @param x - the input array containing jobs that need to be sorted.
	 * @param n - the size of the input array
	 */
	public static void sortHeap ( Comparable[] x, int n ) {
		for (int k = n/2; k >= 1; k--) {
			sink(x, k, n);
		}
		while (n > 1) {
			exch(x, 1, n--);
			sink(x, 1, n);
		}
	}
}
