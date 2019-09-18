package sort;

public class Insertion {
	
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
	
	/**
	 * regular insertion sort
	 * @param x - the input array containing products that need to be sorted.
	 */
	public static void sortInsert( Product[] x ) {
		for (int i = 1; i < x.length; i++) {
			int j = i;
			while (j > 0) {
				if (x[j].getSales() < x[j-1].getSales()) {
					exch(x, j, j-1);
					j--;
				}
				else if (x[j].getSales() > x[j-1].getSales()) {
					break;
				}
				else {
					if (x[j].getProductID().compareTo(x[j-1].getProductID()) < 0) {
						exch(x, j, j-1);
						j--;
					}
				}
			}
		}
	}
	
	
	/**
	 * insertion sort using Comparable
	 * @param x - the input array containing products that need to be sorted.
	 * @param n - the size of the input array
	 */
	public static void sortComparable ( Comparable[] x, int n ) {
		for (int i = 1; i < n; i++) {
			for (int j = i; j > 0 && less(x[j], x[j-1]); j--) {
				exch(x, j, j-1);
			}
		}
		
	}
	
	/**
	 * optimized insertion sort
	 * @param x - the input array containing products that need to be sorted.
	 * @param n - the size of the input array
	 */
	public static int binSearch (Comparable[] x, Comparable y, int low, int high) {
		if (low != high) {
			int mid = (low+high)/2;
			if (x[mid].compareTo(y) == 0) return mid;
			if (x[mid].compareTo(y) < 0) return binSearch(x, y, low, mid);
			if (x[mid].compareTo(y) > 0) return binSearch(x, y, mid+1, high);
		}
		
		if (less(x[low], y)) return low;
		return low -1;
	}
	
	public static void sortBinary ( Comparable[] x, int n ) {
		for (int i = 1; i < n; i++) {
			if (less(x[i], x[i-1])) {
				int placement = binSearch(x, x[i], 0, i-1);
				for (int cur = i; cur > placement && cur > 0; cur--) {
					exch(x, cur, cur-1);
				}
			}
		}
	}
}




































