# 349. Intersection of Two Arrays - Easy

## Problem Statement:
Given two integer arrays nums1 and nums2, return an array of their intersection. Each element in the result must be unique and you may return the result in any order.

### Example 1:

**Input:**: nums1 = [1,2,2,1], nums2 = [2,2]
**Output:**: [2]

### Example 2:

**Input:**: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
**Output:**: [9,4]
**Explanation:** [4,9] is also accepted.
 

### Constraints:

- 1 <= nums1.length, nums2.length <= 1000
- 0 <= nums1[i], nums2[i] <= 1000

## Solution Notes:
- Using hashsets to do de-duplication

## Codes:

```Java
class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) return new int[0];

        HashSet<Integer> set1 = new HashSet<>();
        HashSet<Integer> ret = new HashSet<>();

        for (int i: nums1) {
            set1.add(i);
        }

        for (int i: nums2) {
            if (set1.contains(i)) ret.add(i);
        }
        
        return ret.stream().mapToInt(Integer::intValue).toArray();

    }
}
```