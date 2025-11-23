# 46. Permutations - Medium

## Problem Statement: 
Given an array nums of distinct integers, return all the possible permutations. You can return the answer in any order.

### Example 1:

**Input:** nums = [1,2,3]
**Output:** [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
### Example 2:

**Input:** nums = [0,1]
**Output:** [[0,1],[1,0]]
### Example 3:

**Input:** nums = [1]
**Output:** [[1]]
 

### Constraints:

- 1 <= nums.length <= 6
- -10 <= nums[i] <= 10
- All the integers of nums are unique.

## Solution Notes:   
- Because evetytime we search from the start, we don't need the start index, just start from 0 index in for loops in each recursion

## Codes:  
```Java
class Solution {
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> path = new ArrayList<>();
    public List<List<Integer>> permute(int[] nums) {
        getPath(nums);
        return res;
    }

    public void getPath(int[] nums){
        if (path.size() == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (path.contains(nums[i])) continue;
            path.add(nums[i]);
            getPath(nums);
            path.remove(path.size() - 1);
        }
    }
}
```Java