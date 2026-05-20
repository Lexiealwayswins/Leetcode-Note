# 121. Best Time to Buy and Sell Stock - Easy

## Problem Statement:  

You are given an array prices where prices[i] is the price of a given stock on the ith day.

You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.

Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.


### Example 1:

**Input:** prices = [7,1,5,3,6,4]
**Output:** 5
**Explanation:** Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.

### Example 2:

**Input:** prices = [7,6,4,3,1]
**Output:** 0
**Explanation:** In this case, no transactions are done and the max profit = 0.
 

### Constraints:

- 1 <= prices.length <= 105
- 0 <= prices[i] <= 104

## Solution Notes:  
- Dynamic Programming
- Use a two element array to store results

## Codes:
```TypeScript
function maxProfit(prices: number[]): number {
    if (prices.length <= 1) return 0;
    const dp: number[][] = Array.from({ length: prices.length }, () => new Array(2).fill(0));
    // dp[i][0]不持有股票的最大现金
    // dp[i][1]持有股票的最大现金
    dp[0][1] = -prices[0]; // 记得要初始化0天持有的现金
    // 初始情况现金为0，第一次买股票后的持有现金就是 -prices[i]
    for (let i = 1; i < prices.length; i++) {
        // 不持有两种情况：延续昨天的不持有，或者昨天持有今天卖了
        dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
        // 持有两种情况：延续昨天的持有，或者昨天不持有今天买了
        dp[i][1] = Math.max(dp[i - 1][1], -prices[i]); // 因为只允许买一次
    }
    return dp[prices.length - 1][0];
};

// 优化内存版
function maxProfit(prices: number[]): number {
    if (prices.length <= 1) return 0;
    const dp: number[][] = Array.from({ length: 2 }, () => new Array(2).fill(0));
    // dp[i][0]不持有股票的最大现金
    // dp[i][1]持有股票的最大现金
    dp[0][1] = -prices[0]; // 记得要初始化0天持有的现金
    // 初始情况现金为0，第一次买股票后的持有现金就是 -prices[i]
    for (let i = 1; i < prices.length; i++) {
        // 不持有两种情况：延续昨天的不持有，或者昨天持有今天卖了
        dp[i % 2][0] = Math.max(dp[(i - 1) % 2][0], dp[(i - 1) % 2][1] + prices[i]);
        // 持有两种情况：延续昨天的持有，或者昨天不持有今天买了
        dp[i % 2][1] = Math.max(dp[(i - 1) % 2][1], -prices[i]);
    }
    return dp[(prices.length - 1) % 2][0];
};
```

```Java
// 贪心算法
class Solution {
    public int maxProfit(int[] prices) {
        // 找到一个最小的购入点
        int low = Integer.MAX_VALUE;
        // res不断更新，直到数组循环完毕
        int res = 0;
        for(int i = 0; i < prices.length; i++){
            low = Math.min(prices[i], low);
            res = Math.max(prices[i] - low, res);
        }
        return res;
    }
}

// 动态规划
// 解法1
class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int length = prices.length;
        // dp[i][0]代表第i天持有股票的最大收益
        // dp[i][1]代表第i天不持有股票的最大收益
        int[][] dp = new int[length][2];
        int result = 0;
        dp[0][0] = -prices[0];
        dp[0][1] = 0;
        for (int i = 1; i < length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], -prices[i]);
            dp[i][1] = Math.max(dp[i - 1][0] + prices[i], dp[i - 1][1]);
        }
        return dp[length - 1][1];
    }
}

// 动态规划一维数组
class Solution {
  public int maxProfit(int[] prices) {
    int[] dp = new int[2];
    // 记录一次交易，一次交易有买入卖出两种状态
    // 0代表持有，1代表卖出
    dp[0] = -prices[0];
    dp[1] = 0;
    // 可以参考斐波那契问题的优化方式
    // 我们从 i=1 开始遍历数组，一共有 prices.length 天，
    // 所以是 i<=prices.length
    for (int i = 1; i <= prices.length; i++) {
      // 前一天持有；或当天买入
      dp[0] = Math.max(dp[0], -prices[i - 1]);
      // 如果 dp[0] 被更新，那么 dp[1] 肯定会被更新为正数的 dp[1]
      // 而不是 dp[0]+prices[i-1]==0 的0，
      // 所以这里使用会改变的dp[0]也是可以的
      // 当然 dp[1] 初始值为 0 ，被更新成 0 也没影响
      // 前一天卖出；或当天卖出, 当天要卖出，得前一天持有才行
      dp[1] = Math.max(dp[1], dp[0] + prices[i - 1]);
    }
    return dp[1];
  }
}
```
