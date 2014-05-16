package com.bobwang.leetcode;

import java.util.*;

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
