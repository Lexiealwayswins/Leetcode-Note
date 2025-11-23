# 90. Subsets II - Medium

## Problem Statement:  
Given an integer array nums that may contain duplicates, return all possible 
subsets (the power set).

The solution set must not contain duplicate subsets. Return the solution in any order.

### Example 1:  

**Input:** nums = [1,2,2]  
**Output:** [[],[1],[1,2],[1,2,2],[2],[2,2]]  

### Example 2:  

**Input:** nums = [0]  
**Output:** [[],[0]]  
 

### Constraints:  

- 1 <= nums.length <= 10  
- -10 <= nums[i] <= 10  

## Solution Notes:   
- Similar to problem 78
- Sort the array in the first place
- deduplicate in the for loop

## Codes:  
```Java
class Solution {
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> path = new ArrayList<>();
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        getPath(nums, 0);
        return res;
    }

    public void getPath(int[] nums, int start){
        res.add(new ArrayList<>(path));
        if (start > nums.length) return;
        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1]) continue;
            path.add(nums[i]);
            getPath(nums, i + 1);
            path.remove(path.size() - 1);
        }
    }
}
```Java