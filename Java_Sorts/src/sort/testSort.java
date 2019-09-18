/**
 * 
 */
package sort;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.StringTokenizer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author miles
 *
 */
public class testSort {

	Product[] p1 = new Product[16];
	Product[] p2 = new Product[64];
	Product[] p3 = new Product[256];
	Product[] p4 = new Product[1024];
	Product[] p5 = new Product[4096];
	Product[] p6 = new Product[16384];
	Product[] p7 = new Product[65536];
	
	@Before
	public void setUp() throws Exception {
		File file = new File("data/a1_in.txt"); 
		  
		BufferedReader br = new BufferedReader(new FileReader(file)); 
		  
		String input1 = br.readLine();
		input1 = input1.replace("n", "");
		
		String input2 = br.readLine();
		input2 = input2.replace("n", "");
		
		String input3 = br.readLine();
		input3 = input3.replace("n", "");
		
		String input4 = br.readLine();
		input4 = input4.replace("n", "");
		
		String input5 = br.readLine();
		input5 = input5.replace("n", "");
		
		String input6 = br.readLine();
		input6 = input6.replace("n", "");
		
		String input7 = br.readLine();
		input7 = input7.replace("n", "");

		StringTokenizer i1 = new StringTokenizer(input1); 
		StringTokenizer i2 = new StringTokenizer(input2); 
		StringTokenizer i3 = new StringTokenizer(input3);
		StringTokenizer i4 = new StringTokenizer(input4); 
		StringTokenizer i5 = new StringTokenizer(input5); 
		StringTokenizer i6 = new StringTokenizer(input6); 
		StringTokenizer i7 = new StringTokenizer(input7); 

		
		int i = 0;
		while (i1.hasMoreTokens()) {
			String pID = i1.nextToken(",");
			String productID = pID.replace("{", "");
			String s = i1.nextToken(",");
			String salesStr = s.replace("}", "");
			int sales = Integer.parseInt(salesStr);
			p1[i] = new Product(productID, sales);
			i += 1;
		}
		
		i = 0;
		while (i2.hasMoreTokens()) {
			String pID = i2.nextToken(",");
			String productID = pID.replace("{", "");
			String s = i2.nextToken(",");
			String salesStr = s.replace("}", "");
			int sales = Integer.parseInt(salesStr);
			p2[i] = new Product(productID, sales);
			i += 1;
		}
		
		i = 0;
		while (i3.hasMoreTokens()) {
			String pID = i3.nextToken(",");
			String productID = pID.replace("{", "");
			String s = i3.nextToken(",");
			String salesStr = s.replace("}", "");
			int sales = Integer.parseInt(salesStr);
			p3[i] = new Product(productID, sales);
			i += 1;
		}
		
		i = 0;
		while (i4.hasMoreTokens()) {
			String pID = i4.nextToken(",");
			String productID = pID.replace("{", "");
			String s = i4.nextToken(",");
			String salesStr = s.replace("}", "");
			int sales = Integer.parseInt(salesStr);
			p4[i] = new Product(productID, sales);
			i += 1;
		}
		
		i = 0;
		while (i5.hasMoreTokens()) {
			String pID = i5.nextToken(",");
			String productID = pID.replace("{", "");
			String s = i5.nextToken(",");
			String salesStr = s.replace("}", "");
			int sales = Integer.parseInt(salesStr);
			p5[i] = new Product(productID, sales);
			i += 1;
		}
		
		i = 0;
		while (i6.hasMoreTokens()) {
			String pID = i6.nextToken(",");
			String productID = pID.replace("{", "");
			String s = i6.nextToken(",");
			String salesStr = s.replace("}", "");
			int sales = Integer.parseInt(salesStr);
			p6[i] = new Product(productID, sales);
			i += 1;
		}
		
		i = 0;
		while (i7.hasMoreTokens()) {
			String pID = i7.nextToken(",");
			String productID = pID.replace("{", "");
			String s = i7.nextToken(",");
			String salesStr = s.replace("}", "");
			int sales = Integer.parseInt(salesStr);
			p7[i] = new Product(productID, sales);
			i += 1;
		}
	
		br.close();
	}
	
	public boolean isSorted(Comparable[] x, int n) {
		for (int i = 0; i < n-1; i++) {
			if (x[i].compareTo(x[i+1]) > 0) {
				return false;
			}
		}
		return true;
	}
	
	public boolean isSortedForHeap(Comparable[] x, int n) {
		for (int i = 1; i < n; i++) {
			if (x[i].compareTo(x[i+1]) > 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	
	@Test
	public void testSortBasicQuick() {
		
		Stopwatch QTimer1 = new Stopwatch();
		Quick.sortBasicQuick(p1);
		double QTime1 = QTimer1.elapsedTime();
		
		Stopwatch QTimer2 = new Stopwatch();
		Quick.sortBasicQuick(p2);
		double QTime2 = QTimer2.elapsedTime();
		
		Stopwatch QTimer3 = new Stopwatch();
		Quick.sortBasicQuick(p3);
		double QTime3 = QTimer3.elapsedTime();
		
		Stopwatch QTimer4 = new Stopwatch();
		Quick.sortBasicQuick(p4);
		double QTime4 = QTimer4.elapsedTime();
		
		Stopwatch QTimer5 = new Stopwatch();
		Quick.sortBasicQuick(p5);
		double QTime5 = QTimer5.elapsedTime();
		
		Stopwatch QTimer6 = new Stopwatch();
		Quick.sortBasicQuick(p6);
		double QTime6 = QTimer6.elapsedTime();
		
		Stopwatch QTimer7 = new Stopwatch();
		Quick.sortBasicQuick(p7);
		double QTime7 = QTimer7.elapsedTime();
		
//		System.out.println(QTime1);
//		System.out.println(QTime2);
//		System.out.println(QTime3);
//		System.out.println(QTime4);
//		System.out.println(QTime5);
//		System.out.println(QTime6);
//		System.out.println(QTime7);
//		
		assert isSorted(p1, 16);
		assert isSorted(p2, 64);
		assert isSorted(p3, 256);
		assert isSorted(p4, 1024);
		assert isSorted(p5, 4096);
		assert isSorted(p6, 16384);
		assert isSorted(p7, 65536);
	}

	
	@Test
	public void testSortThreePartition() {
		
		Stopwatch TQTimer1 = new Stopwatch();
		Quick.sortThreePartition(p1, 16);
		double TQTime1 = TQTimer1.elapsedTime();
		
		Stopwatch TQTimer2 = new Stopwatch();
		Quick.sortThreePartition(p2, 64);
		double TQTime2 = TQTimer2.elapsedTime();
		
		Stopwatch TQTimer3 = new Stopwatch();
		Quick.sortThreePartition(p3, 256);
		double TQTime3 = TQTimer3.elapsedTime();
		
		Stopwatch TQTimer4 = new Stopwatch();
		Quick.sortThreePartition(p4, 1024);
		double TQTime4 = TQTimer4.elapsedTime();
		
		Stopwatch TQTimer5 = new Stopwatch();
		Quick.sortThreePartition(p5, 4096);
		double TQTime5 = TQTimer5.elapsedTime();
		
		Stopwatch TQTimer6 = new Stopwatch();
		Quick.sortThreePartition(p6, 16384);
		double TQTime6 = TQTimer6.elapsedTime();
		
		Stopwatch TQTimer7 = new Stopwatch();
		Quick.sortThreePartition(p7, 65536);
		double TQTime7 = TQTimer7.elapsedTime();
		
//		System.out.println(TQTime1);
//		System.out.println(TQTime2);
//		System.out.println(TQTime3);
//		System.out.println(TQTime4);
//		System.out.println(TQTime5);
//		System.out.println(TQTime6);
//		System.out.println(TQTime7);
		
		assert isSorted(p1, 16);
		assert isSorted(p2, 64);
		assert isSorted(p3, 256);
		assert isSorted(p4, 1024);
		assert isSorted(p5, 4096);
		assert isSorted(p6, 16384);
		assert isSorted(p7, 65536);
	}
	
	@Test
	public void testSortMergeBU() {
		
		Stopwatch MBUTimer1 = new Stopwatch();
		Merge.sortMergeBU(p1, 16);
		double MBUTime1 = MBUTimer1.elapsedTime();
		
		Stopwatch MBUTimer2 = new Stopwatch();
		Merge.sortMergeBU(p2, 64);
		double MBUTime2 = MBUTimer2.elapsedTime();
		
		Stopwatch MBUTimer3 = new Stopwatch();
		Merge.sortMergeBU(p3, 256);
		double MBUTime3 = MBUTimer3.elapsedTime();
		
		Stopwatch MBUTimer4 = new Stopwatch();
		Merge.sortMergeBU(p4, 1024);
		double MBUTime4 = MBUTimer4.elapsedTime();
		
		Stopwatch MBUTimer5 = new Stopwatch();
		Merge.sortMergeBU(p5, 4096);
		double MBUTime5 = MBUTimer5.elapsedTime();
		
		Stopwatch MBUTimer6 = new Stopwatch();
		Merge.sortMergeBU(p6, 16384);
		double MBUTime6 = MBUTimer6.elapsedTime();
		
		Stopwatch MBUTimer7 = new Stopwatch();
		Merge.sortMergeBU(p7, 65536);
		double MBUTime7 = MBUTimer7.elapsedTime();
		
//		System.out.println(MBUTime1);
//		System.out.println(MBUTime2);
//		System.out.println(MBUTime3);
//		System.out.println(MBUTime4);
//		System.out.println(MBUTime5);
//		System.out.println(MBUTime6);
//		System.out.println(MBUTime7);
		
		assert isSorted(p1, 16);
		assert isSorted(p2, 64);
		assert isSorted(p3, 256);
		assert isSorted(p4, 1024);
		assert isSorted(p5, 4096);
		assert isSorted(p6, 16384);
		assert isSorted(p7, 65536);
	}
	
	@Test
	public void testSortMergeTD() {
		
		Stopwatch MTDTimer1 = new Stopwatch();
		Merge.sortMergeTD(p1, 16);
		double MTDTime1 = MTDTimer1.elapsedTime();
		
		Stopwatch MTDTimer2 = new Stopwatch();
		Merge.sortMergeTD(p2, 64);
		double MTDTime2 = MTDTimer2.elapsedTime();
		
		Stopwatch MTDTimer3 = new Stopwatch();
		Merge.sortMergeTD(p3, 256);
		double MTDTime3 = MTDTimer3.elapsedTime();
		
		Stopwatch MTDTimer4 = new Stopwatch();
		Merge.sortMergeTD(p4, 1024);
		double MTDTime4 = MTDTimer4.elapsedTime();
		
		Stopwatch MTDTimer5 = new Stopwatch();
		Merge.sortMergeTD(p5, 4096);
		double MTDTime5 = MTDTimer5.elapsedTime();
		
		Stopwatch MTDTimer6 = new Stopwatch();
		Merge.sortMergeTD(p6, 16384);
		double MTDTime6 = MTDTimer6.elapsedTime();
		
		Stopwatch MTDTimer7 = new Stopwatch();
		Merge.sortMergeTD(p7, 65536);
		double MTDTime7 = MTDTimer7.elapsedTime();
		
//		System.out.println(MTDTime1);
//		System.out.println(MTDTime2);
//		System.out.println(MTDTime3);
//		System.out.println(MTDTime4);
//		System.out.println(MTDTime5);
//		System.out.println(MTDTime6);
//		System.out.println(MTDTime7);
		
		assert isSorted(p1, 16);
		assert isSorted(p2, 64);
		assert isSorted(p3, 256);
		assert isSorted(p4, 1024);
		assert isSorted(p5, 4096);
		assert isSorted(p6, 16384);
		assert isSorted(p7, 65536);
	}
	
	@Test
	public void testSortHeap() throws Exception {
		Product[] hp1 = new Product[17];
		Product[] hp2 = new Product[65];
		Product[] hp3 = new Product[257];
		Product[] hp4 = new Product[1025];
		Product[] hp5 = new Product[4097];
		Product[] hp6 = new Product[16385];
		Product[] hp7 = new Product[65537];
		

		File file = new File("data/a1_in.txt"); 
		  
		BufferedReader br = new BufferedReader(new FileReader(file)); 
		  
		String input1 = br.readLine();
		input1 = input1.replace("n", "");
		
		String input2 = br.readLine();
		input2 = input2.replace("n", "");
		
		String input3 = br.readLine();
		input3 = input3.replace("n", "");
		
		String input4 = br.readLine();
		input4 = input4.replace("n", "");
		
		String input5 = br.readLine();
		input5 = input5.replace("n", "");
		
		String input6 = br.readLine();
		input6 = input6.replace("n", "");
		
		String input7 = br.readLine();
		input7 = input7.replace("n", "");

		StringTokenizer i1 = new StringTokenizer(input1); 
		StringTokenizer i2 = new StringTokenizer(input2); 
		StringTokenizer i3 = new StringTokenizer(input3); 
		StringTokenizer i4 = new StringTokenizer(input4); 
		StringTokenizer i5 = new StringTokenizer(input5); 
		StringTokenizer i6 = new StringTokenizer(input6); 
		StringTokenizer i7 = new StringTokenizer(input7); 
		

		
		int i = 1;
		while (i1.hasMoreTokens()) {
			String pID = i1.nextToken(",");
			String productID = pID.replace("{", "");
			String s = i1.nextToken(",");
			String salesStr = s.replace("}", "");
			int sales = Integer.parseInt(salesStr);
			hp1[i] = new Product(productID, sales);
			i += 1;
		}
		
		i = 1;
		while (i2.hasMoreTokens()) {
			String pID = i2.nextToken(",");
			String productID = pID.replace("{", "");
			String s = i2.nextToken(",");
			String salesStr = s.replace("}", "");
			int sales = Integer.parseInt(salesStr);
			hp2[i] = new Product(productID, sales);
			i += 1;
		}
		
		i = 1;
		while (i3.hasMoreTokens()) {
			String pID = i3.nextToken(",");
			String productID = pID.replace("{", "");
			String s = i3.nextToken(",");
			String salesStr = s.replace("}", "");
			int sales = Integer.parseInt(salesStr);
			hp3[i] = new Product(productID, sales);
			i += 1;
		}
		
		i = 1;
		while (i4.hasMoreTokens()) {
			String pID = i4.nextToken(",");
			String productID = pID.replace("{", "");
			String s = i4.nextToken(",");
			String salesStr = s.replace("}", "");
			int sales = Integer.parseInt(salesStr);
			hp4[i] = new Product(productID, sales);
			i += 1;
		}
		
		i = 1;
		while (i5.hasMoreTokens()) {
			String pID = i5.nextToken(",");
			String productID = pID.replace("{", "");
			String s = i5.nextToken(",");
			String salesStr = s.replace("}", "");
			int sales = Integer.parseInt(salesStr);
			hp5[i] = new Product(productID, sales);
			i += 1;
		}
		
		i = 1;
		while (i6.hasMoreTokens()) {
			String pID = i6.nextToken(",");
			String productID = pID.replace("{", "");
			String s = i6.nextToken(",");
			String salesStr = s.replace("}", "");
			int sales = Integer.parseInt(salesStr);
			hp6[i] = new Product(productID, sales);
			i += 1;
		}
		
		i = 1;
		while (i7.hasMoreTokens()) {
			String pID = i7.nextToken(",");
			String productID = pID.replace("{", "");
			String s = i7.nextToken(",");
			String salesStr = s.replace("}", "");
			int sales = Integer.parseInt(salesStr);
			hp7[i] = new Product(productID, sales);
			i += 1;
		}
	
		br.close();
		
		Stopwatch heapTimer1 = new Stopwatch();
		Heap.sortHeap(hp1, 16);
		double heapTime1 = heapTimer1.elapsedTime();
		
		Stopwatch heapTimer2 = new Stopwatch();
		Heap.sortHeap(hp2, 64);
		double heapTime2 = heapTimer2.elapsedTime();
		
		Stopwatch heapTimer3 = new Stopwatch();
		Heap.sortHeap(hp3, 256);
		double heapTime3 = heapTimer3.elapsedTime();
		
		Stopwatch heapTimer4 = new Stopwatch();
		Heap.sortHeap(hp4, 1024);
		double heapTime4 = heapTimer4.elapsedTime();
		
		Stopwatch heapTimer5 = new Stopwatch();
		Heap.sortHeap(hp5, 4096);
		double heapTime5 = heapTimer5.elapsedTime();
		
		Stopwatch heapTimer6 = new Stopwatch();
		Heap.sortHeap(hp6, 16384);
		double heapTime6 = heapTimer6.elapsedTime();
		
		Stopwatch heapTimer7 = new Stopwatch();
		Heap.sortHeap(hp7, 65536);
		double heapTime7 = heapTimer7.elapsedTime();
		
//		System.out.println(heapTime1);
//		System.out.println(heapTime2);
//		System.out.println(heapTime3);
//		System.out.println(heapTime4);
//		System.out.println(heapTime5);
//		System.out.println(heapTime6);
//		System.out.println(heapTime7);
		
		
		assert isSortedForHeap(hp1, 16);
		assert isSortedForHeap(hp2, 64);
		assert isSortedForHeap(hp3, 256);
		assert isSortedForHeap(hp4, 1024);
		assert isSortedForHeap(hp5, 4096);
		assert isSortedForHeap(hp6, 16384);
		assert isSortedForHeap(hp7, 65536);
	}
	
	@Test
	public void testSortInsert() {
		Insertion.sortInsert(p1);
		Insertion.sortInsert(p2);
		Insertion.sortInsert(p3);
		Insertion.sortInsert(p4);
		Insertion.sortInsert(p5);
		
		assert isSorted(p1, 16);
		assert isSorted(p2, 64);
		assert isSorted(p3, 256);
		assert isSorted(p4, 1024);
		assert isSorted(p5, 4096);
	}
	
	@Test
	public void testSortComparable() {
	
		Stopwatch compTimer1 = new Stopwatch();
		Insertion.sortComparable(p1, 16);
		double compTime1 = compTimer1.elapsedTime();
		
		Stopwatch compTimer2 = new Stopwatch();
		Insertion.sortComparable(p2, 64);
		double compTime2 = compTimer2.elapsedTime();
		
		Stopwatch compTimer3 = new Stopwatch();
		Insertion.sortComparable(p3, 256);
		double compTime3 = compTimer3.elapsedTime();
		
		Stopwatch compTimer4 = new Stopwatch();
		Insertion.sortComparable(p4, 1024);
		double compTime4 = compTimer4.elapsedTime();
		
		Stopwatch compTimer5 = new Stopwatch();
		Insertion.sortComparable(p5, 4096);
		double compTime5 = compTimer5.elapsedTime();
		
//		Stopwatch compTimer6 = new Stopwatch();
//		Insertion.sortComparable(p6, 16384);
//		double compTime6 = compTimer6.elapsedTime();
//	
//		Stopwatch compTimer7 = new Stopwatch();
//		Insertion.sortComparable(p7, 65536);
//		double compTime7 = compTimer7.elapsedTime();
//	
	
//		System.out.println(compTime1);
//		System.out.println(compTime2);
//		System.out.println(compTime3);
//		System.out.println(compTime4);
//		System.out.println(compTime5);
//		System.out.println(compTime6);
//		System.out.println(compTime7);
		
		assert isSorted(p1, 16);
		assert isSorted(p2, 64);
		assert isSorted(p3, 256);
		assert isSorted(p4, 1024);
		assert isSorted(p5, 4096);
//		assert isSorted(p6, 16384);
//		assert isSorted(p7, 65536);
	}
	
//	@Test
//	public void testSortBinary() {
//		Insertion.sortBinary(p1, 16);
//		Insertion.sortBinary(p2, 64);
//		Insertion.sortBinary(p3, 256);
//		Insertion.sortBinary(p4, 1024);
//		Insertion.sortBinary(p5, 4096);
//		
//		assert isSorted(p1, 16);
//		assert isSorted(p2, 64);
//		assert isSorted(p3, 256);
//		assert isSorted(p4, 1024);
//		assert isSorted(p5, 4096);
//	}

}
