package com.bobwang.lintcode.misc;

import java.util.*;

/**
 * Given an array of integers, find two numbers such that they add up to a
 * specific target number.
 * 
 * The function twoSum should return indices of the two numbers such that they
 * add up to the target, where index1 must be less than index2. Please note that
 * your returned answers (both index1 and index2) are not zero-based.
 * 
 * You may assume that each input would have exactly one solution.
 * 
 * Input: numbers={2, 7, 11, 15}, target=9 Output: index1=1, index2=2
 * 
 * @author cwang
 * 
 */
public class TwoSum {

	public static void main(String[] args) {
		TwoSum test = new TwoSum();
		int[] result = test.twoSum(new int[] { 2, 3, 8, 11 }, 11);

		for (int i = 0; i < result.length; i++)
			System.out.println(result[i]);

	}

	public int[] twoSum(int[] numbers, int target) {
		int len = numbers.length;
		assert (len >= 2);

		int[] ret = new int[2];

		Map<Integer, Integer> answerMap = new HashMap<Integer, Integer>();
		for (int i = 0; i < numbers.length; i++) {
			int remainder = target - numbers[i];
			if (!answerMap.containsKey(numbers[i]))
				answerMap.put(remainder, i);

			if (answerMap.containsKey(numbers[i])) {
				int idx = answerMap.get(numbers[i]);
				if (idx < i) {
					ret[0] = idx + 1;
					ret[1] = i + 1; // +1 for not zero-based
				}
			}
		}

		return ret;
	}
}
