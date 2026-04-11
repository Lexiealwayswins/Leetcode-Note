# 1005. Maximize Sum Of Array After K Negations - Easy

## Problem Statement:  

Given an integer array nums and an integer k, modify the array in the following way:

choose an index i and replace nums[i] with -nums[i].
You should apply this process exactly k times. You may choose the same index i multiple times.

Return the largest possible sum of the array after modifying it in this way.


### Example 1:  

**Input:**: nums = [4,2,3], k = 1
**Output:**: 5
**Explanation:**: Choose index 1 and nums becomes [4,-2,3].

### Example 2:  

**Input:**: nums = [3,-1,0,2], k = 3
**Output:**: 6
**Explanation:**: Choose indices (1, 2, 2) and nums becomes [3,1,0,2].

### Example 3:  

**Input:**: nums = [2,-3,-1,5,-4], k = 2
**Output:**: 13
**Explanation:**: Choose indices (1, 4) and nums becomes [2,3,-1,5,4].


### Constraints:

- 1 <= nums.length <= 104
- -100 <= nums[i] <= 100
- 1 <= k <= 10^4

## Solution Notes:  
- Greedy Algorithm: sort the array according to absolute value firstly
- Don't forget we may choose the same index i multiple times so that the rest k can always be index 0

## Codes:
```TypeScript
function largestSumAfterKNegations(nums: number[], k: number): number {
    nums.sort((a, b) => Math.abs(a) - Math.abs(b));
    let res = 0;
    for (let i = nums.length - 1; i > 0; i--) {
        if (k > 0 && nums[i] < 0) {
            nums[i] = - nums[i];
            k--;
        }
        if (i > 0) res += nums[i];
    }
    if (k > 0 && k % 2 === 1) nums[0] = - nums[0];
     
    return res + nums[0];
};
```

