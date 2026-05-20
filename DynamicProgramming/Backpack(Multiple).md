# Backpack problem(Multiple)

## Problem Statement:  
![](https://kamacoder.com/problempage.php?pid=1066)

你是一名宇航员，即将前往一个遥远的行星。在这个行星上，有许多不同类型的矿石资源，每种矿石都有不同的重要性和价值。你需要选择哪些矿石带回地球，但你的宇航舱有一定的容量限制。 
给定一个宇航舱，最大容量为 C。现在有 N 种不同类型的矿石，每种矿石有一个重量 w[i]，一个价值 v[i]，以及最多 k[i] 个可用。不同类型的矿石在地球上的市场价值不同。你需要计算如何在不超过宇航舱容量的情况下，最大化你所能获取的总价值。

You are an astronaut about to travel to a distant planet. This planet has many different types of mineral resources, each with varying importance and value. You need to choose which minerals to bring back to Earth, but your spacecraft has a limited capacity.

Given a spacecraft with a maximum capacity of C, there are N different types of minerals, each with a weight w[i], a value v[i], and a maximum of k[i] available. The different types of minerals have different market values ​​on Earth. You need to calculate how to maximize the total value you can obtain without exceeding the spacecraft's capacity.

### Input Statement:

- 输入共包括四行，第一行包含两个整数 C 和 N，分别表示宇航舱的容量和矿石的种类数量。 
- 接下来的三行，每行包含 N 个正整数。具体如下： 
- 第二行包含 N 个整数，表示 N 种矿石的重量。 
- 第三行包含 N 个整数，表示 N 种矿石的价格。 
- 第四行包含 N 个整数，表示 N 种矿石的可用数量上限。

- The input consists of four lines. The first line contains two integers C and N, representing the spacecraft's capacity and the quantity of each type of ore, respectively.

- The next three lines each contain N positive integers. Specifically:

- The second line contains N integers, representing the weight of each of the N types of ore.

- The third line contains N integers, representing the price of each of the N types of ore.

- The fourth line contains N integers, representing the maximum available quantity of each of the N types of ore.

### Output Statement:

- 输出一个整数，代表获取的最大价值。

- Output an integer representing the maximum value obtained.

### Example 1:  

**Input:**
```Bash
10 3
1 3 4
15 20 30
2 3 2
```
**Output:** 90


Data range:
- 1 <= C <= 2000;
- 1 <= N <= 100;
- 1 <= w[i], v[i], k[i] <= 1000;

## Solution Notes:  
- Dynamic Programming multiple backpack problem
- Which is the same as 01 backpack problem only if we pick the items one by one

```text
	重量	价值	数量
物品0	1	15	1
物品0	1	15	1
物品1	3	20	1
物品1	3	20	1
物品1	3	20	1
物品2	4	30	1
物品2	4	30	1
```
    - i is item index, j is bag's total weight
    - dp[i][j] is max bag value
    - Initialize dp[i][0] with 0
    - Initialize dp[0][j] with value[i]
    - dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weight[i]] + value[i])

- Solution 2: Using 1D array
    - copy dp[i - 1] layer to dp[i], then dp[i][j] = max(dp[i][j], dp[i][j - weight[i]] + value[i])
    - then dp[j] = max(dp[j], dp[j - weight[i]] + value[i]); 
    - be careful that it has be to traversal reversely so that one item can only be put into the back once!
    - for example: item 0, weight[0] = 1, value[0] = 15
        - if traversal in order: 
            - dp[1] = dp[1 - weight[0]] + value[0] = 15
            - dp[2] = dp[2 - weight[0]] + value[0] = 30
            - item 0 is put twice!

        - if traversal reversely:
            - dp[2] = dp[2 - weight[0]] + value[0] = 15 （dp array has been initialized to 0）
            - dp[1] = dp[1 - weight[0]] + value[0] = 15
            - item 0 is put once!
    - 2D array has no need to traverse reversely because dp[i][j] is calculated from [i - 1][j], currrent dp[i][j] won't be covered.



## Codes:
```JavaScript
// 二进制优化版本
const readline = require('readline').createInterface({
    input: process.stdin,
    output:process.stdout
});

let input = [];

readline.on('line', (line) => {
    input.push(line);
});

readline.on('close', () => {
    const [C, N] = input[0].split(" ").map(Number);
    const w = input[1].split(" ").map(Number);
    const v = input[2].split(" ").map(Number);
    const n = input[3].split(" ").map(Number);

    const dp = new Array(C + 1).fill(0);

    for (let i = 0; i < N; i++) {
        let rest = n[i];
        for (let k = 1; k <= n[i]; k *= 2) {
            if (k > rest) break;
            for(let j = C; j >= k * w[i]; j--)  {
                dp[j] = Math.max(dp[j], dp[j - k * w[i]] + k * v[i]);
                
            }
            rest -= k;
        }
        if (rest > 0) {
            for (let j = C; j >= rest * w[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - rest * w[i]] + rest * v[i]);
            }
        }
    }
    console.log(dp[C]);
})
```

```Java
// 二进制优化版本：
import java.util.Scanner;

class MultiKnapsack {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int C = sc.nextInt();  // 背包容量
        int N = sc.nextInt();  // 物品种类
        
        int[] dp = new int[C + 1];
        
        for (int i = 0; i < N; i++) {
            int w = sc.nextInt();  // 重量
            int v = sc.nextInt();  // 价值
            int n = sc.nextInt();  // 数量
            
            // 二进制优化：把 n 个物品拆成 1,2,4,8... 这些物品
            for (int k = 1; k <= n; k *= 2) {
                int currW = w * k;
                int currV = v * k;
                n -= k;
                
                // 01背包倒序
                for (int j = C; j >= currW; j--) {
                    dp[j] = Math.max(dp[j], dp[j - currW] + currV);
                }
            }
            
            // 处理剩下的数量
            if (n > 0) {
                int currW = w * n;
                int currV = v * n;
                for (int j = C; j >= currW; j--) {
                    dp[j] = Math.max(dp[j], dp[j - currW] + currV);
                }
            }
        }
        
        System.out.println(dp[C]);
        sc.close();
    }
}

// 遍历物品的时候，如果物品特别多，这种解法可能超时
import java.util.Scanner;
public class Main {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        int C = sc.nextInt();
        int N = sc.nextInt();
        int[] w = new int[N];
        int[] v = new int[N];
        int[] n = new int[N];

        for (int i = 0; i < N; i++) w[i] = sc.nextInt();
        for (int i = 0; i < N; i++) v[i] = sc.nextInt();
        for (int i = 0; i < N; i++) n[i] = sc.nextInt();

        int[] dp = new int[C + 1];

        int count;
        for (int i = 0; i < N; i++) {
            count = n[i];
            while (count > 0) {
                for (int j = C; j > 0; j--) {
                    if (j >= w[i]) {
                        dp[j] = Math.max(dp[j], dp[j - w[i]] + v[i]);
                    }
                }
                count--;
            }
        }
        System.out.println(dp[C]);
        sc.close();
    }
}
```

