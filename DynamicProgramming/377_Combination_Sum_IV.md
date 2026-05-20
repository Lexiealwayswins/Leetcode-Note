# 377. Combination Sum IV - Medium

## Problem Statement:  

Given an array of distinct integers nums and a target integer target, return the number of possible combinations that add up to target.

The test cases are generated so that the answer can fit in a 32-bit integer.

### Example 1:  

Input: nums = [1,2,3], target = 4
Output: 7
Explanation:
The possible combination ways are:
(1, 1, 1, 1)
(1, 1, 2)
(1, 2, 1)
(1, 3)
(2, 1, 1)
(2, 2)
(3, 1)
Note that different sequences are counted as different combinations.

### Example 2:  

Input: nums = [9], target = 3
Output: 0


### Constraints:

- 1 <= nums.length <= 200
- 1 <= nums[i] <= 1000
- All the elements of nums are unique.
- 1 <= target <= 1000
 

Follow up: What if negative numbers are allowed in the given array? How does it change the problem? What limitation we need to add to the question to allow negative numbers?

## Solution Notes:  
- Dynamic Programming: 
- 如果求组合数就是外层for循环遍历物品，内层for遍历背包。
- 如果求排列数就是外层for遍历背包，内层for循环遍历物品。

## Codes:
```Python
# Optimized：
class Solution:
    def combinationSum4(self, nums: List[int], target: int) -> int:
        dp = [0] * (target + 1)  # 创建动态规划数组，用于存储组合总数
        dp[0] = 1  # 初始化背包容量为0时的组合总数为1

        for i in range(1, target + 1):  # 遍历背包容量
            for j in nums:  # 遍历物品列表
                if i >= j:  # 当背包容量大于等于当前物品重量时
                    dp[i] += dp[i - j]  # 更新组合总数

        return dp[-1]  # 返回背包容量为target时的组合总数

# 2D array
class Solution:
    def combinationSum4(self, nums: List[int], target: int) -> int:
        # dp[][j]和为j的组合的总数
        dp = [[0] * (target+1) for _ in nums]
        
        for i in range(len(nums)):
            dp[i][0] = 1
            
        # 这里不能初始化dp[0][j]。dp[0][j]的值依赖于dp[-1][j-nums[0]]
            
        for j in range(1, target+1):
            for i in range(len(nums)):
                
                if j - nums[i] >= 0:
                    dp[i][j] = (
                        # 不放nums[i]
                        # i = 0 时，dp[-1][j]恰好为0，所以没有特殊处理
                        dp[i-1][j] +
                        # 放nums[i]。对于和为j的组合，只有试过全部物品，才能知道有几种组合方式。所以取最后一个物品dp[-1][j-nums[i]]
                        dp[-1][j-nums[i]]
                    )
                else:
                    dp[i][j] = dp[i-1][j]
        return dp[-1][-1]

```

```TypeScript
function combinationSum4(nums: number[], target: number): number {
    const dp: number[] = new Array(target + 1).fill(0);
    dp[0] = 1;
    // 遍历背包
    for (let i = 1; i <= target; i++) {
        // 遍历物品
        for (let j = 0, length = nums.length; j < length; j++) {
            if (i >= nums[j]) {
                dp[i] += dp[i - nums[j]];
            }
        }
    }
    return dp[target];
};
```

```Java
class Solution {
    public int combinationSum4(int[] nums, int target) {
        int[][] dp = new int[nums.length][target + 1];
        for (int i = 0; i < nums.length; i++) {
            dp[i][0] = 1;
        }

        for (int j = 1; j <= target; j++)  {
            for (int i = 0; i < nums.length; i++) {
                if (j < nums[i]) {
                    if (i > 0) dp[i][j] = dp[i - 1][j];
                } else {
                    if (i == 0) {
                        dp[i][j] = dp[nums.length - 1][j - nums[i]];
                    } else {
                        dp[i][j] = dp[i - 1][j] + dp[nums.length - 1][j - nums[i]];
                    }
                }
            }
        }
        return dp[nums.length - 1][target];
    }
}
```

