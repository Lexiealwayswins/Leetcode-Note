# 491. Non-decreasing Subsequences - Medium

## Problem Statement:  
Given an integer array nums, return all the different possible non-decreasing subsequences of the given array with at least two elements. You may return the answer in any order.

### Example 1:

**Input:** nums = [4,6,7,7]
**Output:** [[4,6],[4,6,7],[4,6,7,7],[4,7],[4,7,7],[6,7],[6,7,7],[7,7]]

### Example 2:

**Input:** nums = [4,4,3,2,1]
**Output:** [[4,4]]
 

### Constraints:

- 1 <= nums.length <= 15
- -100 <= nums[i] <= 100

## Solution Notes:   
- Because the array cannot be sorted, we cannot compare the adjacent numbers to deduplicated. Instead, use the HashSet to depublicate in each recursion

## Codes:  
```Java
class Solution {
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> path = new ArrayList<>();
    public List<List<Integer>> findSubsequences(int[] nums) {
        getPath(nums, 0);
        return res;
    }

    public void getPath(int[] nums, int start){
        if (path.size() > 1) {
            res.add(new ArrayList<>(path));
        }

        HashSet<Integer> hs = new HashSet<>();
        for (int i = start; i < nums.length; i++) {
            
            if (!path.isEmpty() && nums[i] < path.get(path.size() - 1) || hs.contains(nums[i])) continue;
            path.add(nums[i]);
            hs.add(nums[i]);
            getPath(nums, i + 1);
            path.remove(path.size() - 1);
        }
    }
}
```Java