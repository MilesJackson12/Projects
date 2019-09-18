package sort;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.util.StringTokenizer;

public class Product implements Comparable<Product>{
	
	private final String productID;
	private int sales;
	
	public Product(String productID, int sales) {
		this.productID = productID;
		this.sales = sales;
	}
	
	public int getSales() {
		return this.sales;
	}
	
	public String getProductID() {
		return this.productID;
	}
	
	public void setSales(int sales) {
		this.sales = sales;
	}
	
	public String toString() {
		return "(" + this.productID + ", " + this.sales + ")";
	}
	
	@Override
	public int compareTo(Product j)
	{
		if(this.sales < j.sales) return -1;
		if(this.sales > j.sales) return 1;
		
		return this.productID.compareTo(j.productID);
	}
	
	
	/*
	 * public static void main(String[] args) throws IOException { File file = new
	 * File("data/a1_in.txt");
	 * 
	 * BufferedReader br = new BufferedReader(new FileReader(file));
	 * 
	 * String input1 = br.readLine(); String input2 = br.readLine(); String input3 =
	 * br.readLine(); String input4 = br.readLine(); String input5 = br.readLine();
	 * 
	 * StringTokenizer i1 = new StringTokenizer(input1); StringTokenizer i2 = new
	 * StringTokenizer(input2); StringTokenizer i3 = new StringTokenizer(input3);
	 * StringTokenizer i4 = new StringTokenizer(input4); StringTokenizer i5 = new
	 * StringTokenizer(input5);
	 * 
	 * Product[] p1 = new Product[16]; int i = 0; while (i1.hasMoreTokens()) {
	 * String pID = i1.nextToken(","); String productID = pID.replace("{", "");
	 * String s = i1.nextToken(","); String salesStr = s.replace("}", ""); int sales
	 * = Integer.parseInt(salesStr); p1[i] = new Product(productID, sales); i += 1;
	 * }
	 * 
	 * Product[] p2 = new Product[64]; i = 0; while (i2.hasMoreTokens()) { String
	 * pID = i2.nextToken(","); String productID = pID.replace("{", ""); String s =
	 * i2.nextToken(","); String salesStr = s.replace("}", ""); int sales =
	 * Integer.parseInt(salesStr); p2[i] = new Product(productID, sales); i += 1; }
	 * 
	 * Product[] p3 = new Product[256]; i = 0; while (i3.hasMoreTokens()) { String
	 * pID = i3.nextToken(","); String productID = pID.replace("{", ""); String s =
	 * i3.nextToken(","); String salesStr = s.replace("}", ""); int sales =
	 * Integer.parseInt(salesStr); p3[i] = new Product(productID, sales); i += 1; }
	 * 
	 * Product[] p4 = new Product[1024]; i = 0; while (i4.hasMoreTokens()) { String
	 * pID = i4.nextToken(","); String productID = pID.replace("{", ""); String s =
	 * i4.nextToken(","); String salesStr = s.replace("}", ""); int sales =
	 * Integer.parseInt(salesStr); p4[i] = new Product(productID, sales); i += 1; }
	 * 
	 * Product[] p5 = new Product[4096]; i = 0; while (i5.hasMoreTokens()) { String
	 * pID = i5.nextToken(","); String productID = pID.replace("{", ""); String s =
	 * i1.nextToken(","); String salesStr = s.replace("}", ""); int sales =
	 * Integer.parseInt(salesStr); p5[i] = new Product(productID, sales); i += 1; }
	 * 
	 * }
	 */
}

