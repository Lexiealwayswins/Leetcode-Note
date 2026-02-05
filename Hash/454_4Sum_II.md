# 454. 4 Sum II - Medium

## Problem Statement:
Given four integer arrays nums1, nums2, nums3, and nums4 all of length n, return the number of tuples (i, j, k, l) such that:

- 0 <= i, j, k, l < n
- nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0

### Example 1:

**Input:** nums1 = [1,2], nums2 = [-2,-1], nums3 = [-1,2], nums4 = [0,2]  
**Output**: 2  
**Explanation**: 

The two tuples are:  
1. (0, 0, 0, 1) -> nums1[0] + nums2[0] + nums3[0] + nums4[1] = 1 + (-2) + (-1) + 2 = 0
2. (1, 1, 0, 0) -> nums1[1] + nums2[1] + nums3[0] + nums4[0] = 2 + (-1) + (-1) + 0 = 0

### Example 2:

**Input:** nums1 = [0], nums2 = [0], nums3 = [0], nums4 = [0]  
**Output:** 1
 

### Constraints:

- n == nums1.length
- n == nums2.length
- n == nums3.length
- n == nums4.length
- 1 <= n <= 200
- -228 <= nums1[i], nums2[i], nums3[i], nums4[i] <= 228

## Solution Notes:
- Using a hashmap to store the sum of first two arrays and the times of the sum
- Go through the rest two arrays to search for the key of (0 - sum) in the hashmap
- add the value of search results, and return this value

## Codes:
```Python
class Solution:
    def fourSumCount(self, nums1: List[int], nums2: List[int], nums3: List[int], nums4: List[int]) -> int:
        dic = {}
        for a in nums1:
            for b in nums2:
                if a + b in dic:
                    dic[a + b] += 1
                else:
                    dic[a + b] = 1
        count = 0
        for c in nums3:
            for d in nums4:
                if 0 - c - d in dic:
                    count += dic[0 - c - d]
        return count
```

```JavaScript
/**
 * @param {number[]} nums1
 * @param {number[]} nums2
 * @param {number[]} nums3
 * @param {number[]} nums4
 * @return {number}
 */
var fourSumCount = function(nums1, nums2, nums3, nums4) {
    let map = new Map();
    for (const a of nums1) {
        for (const b of nums2) {
            map.set(a + b, (map.get(a + b) || 0) + 1);
        }
    }

    let count = 0;

    for (const c of nums3) {
        for (const d of nums4) {
            if (map.has(0 - c - d)) {
                count += map.get(0 - c - d);
            }
        }
    }

    return count;
};
```

```Java
class Solution {
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        HashMap<Integer, Integer> hm = new HashMap<>();
        int ret = 0;
        for (int i: nums1) {
            for (int j: nums2){
                int add = i + j;
                hm.put(add, hm.getOrDefault(add, 0) + 1);
            }
        }

        for (int i: nums3) {
            for (int j: nums4){
                int search = 0 - (i + j);
                ret += hm.getOrDefault(search, 0);
            }
        }

        return ret;

    }
}

```