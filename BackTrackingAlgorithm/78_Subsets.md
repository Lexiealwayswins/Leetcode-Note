# 78. Subsets - Medium

## Problem Statement:  

Given an integer array nums of unique elements, return all possible 
subsets(the power set).

The solution set must not contain duplicate subsets. Return the solution in any order.

### Example 1:  

**Input:** nums = [1,2,3]  
**Output:** [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]  

### Example 2:  

**Input:** nums = [0]  
**Output:** [[],[0]]  
 
### Constraints:  

- 1 <= nums.length <= 10
- -10 <= nums[i] <= 10
- All the numbers of nums are unique

## Solution Notes:   
- Traverse each path in recursion

## Codes:  
```Java
class Solution {
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> path = new ArrayList<>();
    public List<List<Integer>> subsets(int[] nums) {
        getPath(nums, 0);
        return res;
    }

    public void getPath(int[] nums, int start){
        res.add(new ArrayList<>(path));
        if (start > nums.length) {
            return;
        }

        for (int i = start; i < nums.length; i++) {
            path.add(nums[i]);
            getPath(nums, i + 1);
            path.remove(path.size() - 1);
        }
    }
}
```Java