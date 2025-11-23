47. Permutations II - Medium

## Problem Statement: 
Given a collection of numbers, nums, that might contain duplicates, return all possible unique permutations in any order.

### Example 1:  

**Input:** nums = [1,1,2]
**Output:**
[[1,1,2],
 [1,2,1],
 [2,1,1]]

### Example 2:  

**Input:** nums = [1,2,3]
**Output:** [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 

### Constraints:  

- 1 <= nums.length <= 8
- -10 <= nums[i] <= 10

## Solution Notes:   
- Because evetytime we search from the start, we don't need the start index, just start from 0 index in for loops in each recursion
- Use a boolean array to record the number which has been added to path
- used[i - 1] == 0 means it's a new round and this number has been used before, so we need to skip it in the new round to avoid duplication

## Codes:  
```Java
class Solution {
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> path = new ArrayList<>();
    int[] used;
    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        used = new int[nums.length];
        Arrays.fill(used, 0);
        getPath(nums);
        return res;
    }

    public void getPath(int[] nums){
        if (path.size() == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1] && used[i - 1] == 0) continue;

            if (used[i] == 0) {
                path.add(nums[i]);
                used[i] = 1;
                getPath(nums);
                path.remove(path.size() - 1);
                used[i] = 0;
            }
        }
    }
}
```Java