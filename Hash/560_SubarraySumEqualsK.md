# 560. Subarray Sum Equals K - Medium

## Problem Statement:
Given an array of integers nums and an integer k, return the total number of subarrays whose sum equals to k.

A subarray is a contiguous non-empty sequence of elements within an array.

### Example 1:

**Input:** nums = [1,1,1], k = 2
**Output:** 2

### Example 2:

**Input:** nums = [1,2,3], k = 3
**Output:** 2

### Constraints:

- 1 <= nums.length <= 2 * 104
- -1000 <= nums[i] <= 1000
- -107 <= k <= 107

## Solution Notes:
- Hash: total[i] - total[j] = k, then just need to check whether total[j] = total[i] - k is in the hash
- Don't use sliding window because it's not in order and may have negative elements

## Codes:
```TypeScript
function subarraySum(nums: number[], k: number): number {
    const map = new Map<number, number>();
    let total = 0;
    let count = 0;
    map.set(0, 1); // when total === k, then total - k === 0
    for (const n of nums) {
        total += n;
        if (map.has(total - k)) {
            count += map.get(total - k);
        }
        map.set(total, (map.get(total) || 0) + 1);
    }
    
    return count;
};
```