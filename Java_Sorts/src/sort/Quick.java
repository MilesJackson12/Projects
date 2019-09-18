package sort;

public class Quick {
	
	/**
	 * Checks to see if one Comparable is less than another.
	 * @param v The first Comparable.
	 * @param w The second Comparable.
	 * @return Returns true if Comparable v is less than Comparable w.
	 */
	private static boolean less(Comparable v, Comparable w) {
		return v.compareTo(w) < 0;
	}
	
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
	
	private static int partition(Comparable[] a, int lo, int hi) {
		int i = lo;
		int j = hi + 1;
		Comparable v = a[lo];
		while(true) {
			while (less(a[++i], v)) if (i == hi) break;
			while (less(v, a[--j])) if (j == lo) break;
			if (i >= j) break;
			exch(a, i, j);
		}
		exch(a, lo, j);
		return j;
	}
	
	/**
	 * basic quick sort
	 * @param x - the input array containing products that need to be sorted.
	 */
	public static void sortBasicQuick (  Product[] x ) {
		StdRandom.shuffle(x);
		sortBasicQuick(x, 0, x.length-1);
	}
	
	private static void sortBasicQuick (Comparable[] a, int lo, int hi) {
		if (hi <= lo) return;
		int j = partition(a, lo, hi);
		sortBasicQuick(a, lo, j-1);
		sortBasicQuick(a, j+1, hi);
	}
	/**
	 * three partition quick sort using Comparable
	 * @param x - the input array containing products that need to be sorted.
	 * @param n - the size of the input array
	 */
	public static void sortThreePartition ( Comparable[] x, int n ) {
		StdRandom.shuffle(x);
		sortThreePartition(x, 0, n-1);
	}

	private static void sortThreePartition (Comparable[] a, int lo, int hi) {
		if (hi <= lo) return;
		int lt = lo;
		int i = lo+1;
		int gt = hi;
		Comparable v = a[lo];
		
		while (i <= gt) {
			int cmp = a[i].compareTo(v);
			if (cmp < 0) exch(a, lt++, i++);
			else if (cmp > 0) exch(a, i, gt--);
			else i++;
		}
		
		sortThreePartition(a, lo, lt-1);
		sortThreePartition(a, gt + 1, hi);
	}
}






























