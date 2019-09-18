package sort;

import java.lang.Math;

public class Merge {
	
	private static Comparable[] aux;
	
	/**
	 * Checks to see if one Comparable is less than another.
	 * @param v The first Comparable.
	 * @param w The second Comparable.
	 * @return Returns true if Comparable v is less than Comparable w.
	 */
	private static boolean less(Comparable v, Comparable w) {
		return v.compareTo(w) < 0;
	}
	

	private static void merge(Comparable[]a, int lo, int mid, int hi) {
		int i = lo;
		int j = mid + 1;
		
		for (int k = lo; k <= hi; k++) {
			aux[k] = a[k];
		}
		for (int k = lo; k <= hi; k++) {
			if (i > mid) a[k] = aux[j++];
			else if (j > hi)  a[k] = aux[i++];
			else if (less(aux[j], aux[i])) a[k] = aux[j++];
			else a[k] = aux[i++];
		}
		
		
	}
	
	/**
	 * top-down merge sort using Comparable
	 * @param x - the input array containing products that need to be sorted.
	 * @param n - the size of the input array
	 */
	public static void sortMergeTD ( Comparable[] x, int n ) {
		aux = new Comparable[n];
		sortMergeTD(x, 0, n-1);
	}
	
	private static void sortMergeTD( Comparable[] a, int lo, int hi) {
		if (hi <= lo) return;
		int mid = lo + (hi - lo)/2;
		sortMergeTD(a, lo, mid);
		sortMergeTD(a, mid+1, hi);
		merge(a, lo, mid, hi);
	}
	
	/**
	 * bottom-up merge sort using Comparable
	 * @param x - the input array containing products that need to be sorted.
	 * @param n - the size of the input array
	 */
	public static void sortMergeBU ( Comparable[] x, int n ) {
		aux = new Comparable[n];
		for (int len = 1; len < n; len *= 2) {
			for (int lo = 0; lo < n-len; lo += len+len) {
				merge(x, lo, lo+len-1, Math.min(lo+len+len-1, n-1));
			}
		}
	}
}































