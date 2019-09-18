package sort;

import java.lang.Math;

public class Stopwatch {
	
	private final long start;
	
	public Stopwatch() {
		start = System.currentTimeMillis();
	}
	
	public double elapsedTime() {
		double now = System.currentTimeMillis();
		return (now-start) / 1000.0;
	}

}
