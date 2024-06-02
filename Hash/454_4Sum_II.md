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