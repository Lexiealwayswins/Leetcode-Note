1. Two Sum - Easy

## Problem Statement:

Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

You can return the answer in any order.

### Example 1:

**Input:** nums = [2,7,11,15], target = 9
**Output:** [0,1]
Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
Example 2:

**Input:** nums = [3,2,4], target = 6
**Output:** [1,2]
Example 3:

**Input:** nums = [3,3], target = 6
**Output:** [0,1]

## Solution Notes:
- Python can use set, dict
- Javascript can use set, map
- Java can use HashMap
- Also it can use two Pointers, but more complicated

## Codes:
```Python
# Solution 1:
class Solution:
    def twoSum(self, nums: List[int], target: int) -> List[int]:
        seen = set()
        for i, num in enumerate(nums):
            left = target - num
            if left in seen:
                return [nums.index(left), i]
            seen.add(num)
        return []

# Solution 2:
class Solution:
    def twoSum(self, nums: List[int], target: int) -> List[int]:
        seen = {}
        for i, num in enumerate(nums):
            left = target - num
            if left in seen:
                return [seen[left], i]
            seen[num] = i
        return []
```

```JavaScript
// Solution 1:
/**
 * @param {number[]} nums
 * @param {number} target
 * @return {number[]}
 */
var twoSum = function(nums, target) {
    let seen = {};
    for (let i = 0; i < nums.length; i++) {
        if (seen[target - nums[i]] !== undefined) {
            return [seen[target - nums[i]], i];
        }
        seen[nums[i]] = i;
    }
    return [];
};

// Solution 2:
/**
 * @param {number[]} nums
 * @param {number} target
 * @return {number[]}
 */
var twoSum = function(nums, target) {
    let seen = new Map();
    for (let i = 0; i < nums.length; i++) {
        if (seen.has(target - nums[i])) {
            return [seen.get(target - nums[i]), i];
        }
        seen.set(nums[i], i);
    }
    return [];
};
```

```Java
// Hashmap Approach:
class Solution {
    public int[] twoSum(int[] nums, int target) {
        int[] ret = new int[2];

        HashMap<Integer, Integer> hm = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int tmp = target - nums[i];
            if (hm.containsKey(tmp)) {
                ret[0] = hm.get(tmp);
                ret[1] = i;
            }
            hm.put(nums[i], i);
        }

        return ret;
    }
}

\\ Two Pointer Approach:

public int[] twoSum(int[] nums, int target) {
    int m=0,n=0,k,board=0;
    int[] res=new int[2];
    int[] tmp1=new int[nums.length];
    //备份原本下标的nums数组
    System.arraycopy(nums,0,tmp1,0,nums.length);
    //将nums排序
    Arrays.sort(nums);
    //双指针
    for(int i=0,j=nums.length-1;i<j;){
        if(nums[i]+nums[j]<target)
            i++;
        else if(nums[i]+nums[j]>target)
            j--;
        else if(nums[i]+nums[j]==target){
            m=i;
            n=j;
            break;
        }
    }
    //找到nums[m]在tmp1数组中的下标
    for(k=0;k<nums.length;k++){
        if(tmp1[k]==nums[m]){
            res[0]=k;
            break;
        }
    }
    //找到nums[n]在tmp1数组中的下标
    for(int i=0;i<nums.length;i++){
        if(tmp1[i]==nums[n]&&i!=k)
            res[1]=i;
    }
    return res;
}
```Java



