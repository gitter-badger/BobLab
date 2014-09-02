package com.bobwang.amazonTest.test;

import org.junit.Assert;
import org.junit.Test;

import com.bobwang.amazonTest.Q1;

public class Q1Test {

	private Q1 test = new Q1();
	private String input = "";

	@Test
	public void testNormalCases() {
		input = "fes(int) (print xxxxx)";
		Assert.assertEquals(2, test.countParentheses(input));
	}

	@Test
	public void testAbnormalCases() {
		input = "()aaa (bbb(sss))ss))";
		Assert.assertEquals(3,test.countParentheses(input));

		input = "(((()))))))))";
		Assert.assertEquals(4, test.countParentheses(input));

		input = "((((((((((((((((((((((((((()))))))))";
		Assert.assertEquals(9, test.countParentheses(input));
	}

	@Test
	public void testEdgeCases() {
		input = null;
		Assert.assertEquals(0,test.countParentheses(input));
		input = "";
		Assert.assertEquals(0,test.countParentheses(input));
	}

}
