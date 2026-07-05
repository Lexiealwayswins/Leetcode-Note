# 152. Maximum Product Subarray - Medium

## Problem Statement:  

Given an integer array nums, find a subarray that has the largest product, and return the product.

The test cases are generated so that the answer will fit in a 32-bit integer.

Note that the product of an array with a single element is the value of that element.

### Example 1:

**Input**: nums = [2,3,-2,4]
**Output**: 6
**Explanation**: [2,3] has the largest product 6.

### Example 2:

**Input**: nums = [-2,0,-1]
**Output**: 0
**Explanation**: The result cannot be 2, because [-2,-1] is not a subarray.
 

### Constraints:

- 1 <= nums.length <= 2 * 104
- -10 <= nums[i] <= 10
- The product of any subarray of nums is guaranteed to fit in a 32-bit integer.


## Solution Notes:  
- Dynamic Programming

## Codes:
```Java
class Solution {
    public int maxProduct(int[] nums) {
        int currMin = 1;
        int currMax = 1;
        int res = nums[0];

        int a, b;
        for (int n: nums) {
            // 维护最小值是以防负负得正，从而得到最大值
            a = n * currMin;
            b = n * currMax;
            currMin = Math.min(n, Math.min(a, b));
            currMax = Math.max(n, Math.max(a, b));
            // 储存结果值不参与下一轮最大值选拔，保证currMin和currMax都是连续的
            res = Math.max(res, currMax);
        }

        return res;
    }
}
```

