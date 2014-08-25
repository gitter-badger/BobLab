package com.bobwang.leetcode;

import java.util.HashMap;
import java.util.Map;

public class MaxPointsonaLine {

	public static void main(String[] args) {
		Point[] ptArr = new Point[] { new Point(0, 0), new Point(1, 10), new Point(1, 10), new Point(2, 0), new Point(2, 1), new Point(1, 3) };
		System.out.println(MaxPointsonaLine.maxPoints(ptArr));
		ptArr = new Point[] { new Point(0, 0) };
		System.out.println(MaxPointsonaLine.maxPoints(ptArr));
	}

	public static int maxPoints(Point[] points) {
		Map<Float, Integer> maxPointMap = new HashMap<Float, Integer>();
		int max = 0;


		return max;
	}
}

class Point {
	int x;
	int y;

	Point() {
		x = 0;
		y = 0;
	}

	Point(int a, int b) {
		x = a;
		y = b;
	}
}
