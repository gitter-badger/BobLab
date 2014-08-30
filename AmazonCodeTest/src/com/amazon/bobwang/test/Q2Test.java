package com.amazon.bobwang.test;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;

import com.amazon.bobwang.Q2;
import com.amazon.bobwang.Q2_Quicksort;

public class Q2Test {

	// There are two solutions, quick sort is much faster than knapsack.
	private Q2 test = new Q2_Quicksort();
	// private Q2 test = new Q2_Knapsack();
	private String input = "";

	@Test
	public void testYESCases() {
		input = "1,-2,3,4,-5,6,7,8";
		assertEquals("YES", test.check(input));
		input = "1,10,100,1000,10000,11111";
		assertEquals("YES", test.check(input));

		// If max is too large, OutOfMemeoryError may occur.
		StringBuilder buffer = new StringBuilder();
		int max = 10000;
		for (int i = -max; i < max; i++) {
			buffer.append(i).append(",");
		}
		buffer.append(max);
		assertEquals("YES", test.check(buffer.toString()));
	}

	@Test
	public void testNOCases() {
		input = "1,23";
		assertEquals("NO", test.check(input));
		input = "1,2,100,1000,10000";
		assertEquals("NO", test.check(input));
	}

	@Test
	public void testInvalidInput() {
		input = "1";
		assertEquals("NO", test.check(input));
		input = "1, 3";
		assertEquals("NO", test.check(input));
		input = "1,1.1,1*3";
		assertEquals("NO", test.check(input));
	}

	@Test
	public void testEdgeCases() {
		input = null;
		Assert.assertEquals("NO", test.check(input));
		input = "";
		Assert.assertEquals("NO", test.check(input));
	}

}
