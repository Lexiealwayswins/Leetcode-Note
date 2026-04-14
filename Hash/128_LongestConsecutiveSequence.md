# 128. Longest Consecutive Sequence - Medium

## Problem Statement:
Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.

You must write an algorithm that runs in O(n) time.


### Example 1:

Input: nums = [100,4,200,1,3,2]
Output: 4
Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.

### Example 2:

Input: nums = [0,3,7,2,5,8,4,6,0,1]
Output: 9

### Example 3:

Input: nums = [1,0,1,2]
Output: 3

### Constraints:

- 0 <= nums.length <= 105
- -109 <= nums[i] <= 109

## Solution Notes:
- Hash
- You must write an algorithm that runs in O(n) time, so we have to use hash
- Use set to deduplicate and search set is O(1)
- Find the smallest number first and then add 1 each time to see whether it exist in the set.


## Codes:
```TypeScript
function longestConsecutive(nums: number[]): number {
    const set = new Set<number>(nums);
    let count = 0;
    for (const n of set) {
        if (!set.has(n - 1)) {
            let next = n + 1;
            while (set.has(next)) {
                next++;
            }
            count = Math.max(count, next - n);
        }
    }
    return count;
};
```