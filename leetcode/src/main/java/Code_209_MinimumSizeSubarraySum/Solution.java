package Code_209_MinimumSizeSubarraySum;

import java.util.Arrays;

/**
 * @author pengtao.geng on 2020/1/31 3:38 下午
 */
public class Solution {

	public int[] minSubArrayLen(int[] nums, int s) {
		int[] minArray = null;
		for (int i = 0; i < nums.length; i++) {
			for (int j = i; j < nums.length; j++) {
				int sum = sumArray(nums, i, j);
				if (sum >= s) {
					if (minArray == null) {
						minArray = Arrays.copyOfRange(nums, i, j + 1);
					} else {
						if (minArray.length > (j - i + 1)) {
							minArray = Arrays.copyOfRange(nums, i, j + 1);
						}
					}
				}
			}
		}
		return minArray;
	}

	private int sumArray(int[] nums, int start, int end) {
		int sum = 0;
		for (int i = start; i <= end; i++) {
			sum += nums[i];
		}
		return sum;
	}
}
