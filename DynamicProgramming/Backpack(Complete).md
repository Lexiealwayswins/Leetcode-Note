# Backpack(Complete)

## Problem Statement:  
![](https://kamacoder.com/problempage.php?pid=1052)

小明是一位科学家，他需要参加一场重要的国际科学大会，以展示自己的最新研究成果。他需要带一些研究材料，但是他的行李箱空间有限。这些研究材料包括实验设备、文献资料和实验样本等等，它们各自占据不同的重量，并且具有不同的价值。
小明的行李箱所能承担的总重量是有限的，问小明应该如何抉择，才能携带最大价值的研究材料，每种研究材料可以选择无数次，并且可以重复选择。

Xiaoming is a scientist who needs to attend an important international scientific conference to present his latest research findings. He needs to bring some research materials, but his suitcase has limited space. These materials include experimental equipment, literature, and experimental samples, each with different weight and value.

Xiaoming's suitcase has a limited total weight capacity. How should Xiaoming choose his materials to carry the most valuable research materials? He can choose each type of material an unlimited number of times, and can repeat the selection process.

### Input Statement:

- 第一行包含两个整数，n，v，分别表示研究材料的种类和行李所能承担的总重量 
- 接下来包含 n 行，每行两个整数 wi 和 vi，代表第 i 种研究材料的重量和价值

- The first line contains two integers, n and v, representing the type of research material and the total weight the luggage can carry, respectively. 
- The following n lines each contain two integers wi and vi, representing the weight and value of the i-th type of research material.

### Output Statement:

- 输出一个整数，代表小明能够携带的研究材料的最大价值。

- Output an integer representing the maximum value of the research materials that Xiaoming can carry.

### Example 1:  

**Input:**
```Bash
4 5
1 2
2 4
3 4
4 5
```
**Output:** 10
**Explanation:**: 
第一种材料选择五次，可以达到最大值。
数据范围：
1 <= n <= 10000;
1 <= v <= 10000;
1 <= wi, vi <= 10^9.

The first material can be selected five times to reach its maximum value.

Data range:
1 <= n <= 10000;
1 <= v <= 10000;
1 <= wi, vi <= 10^9.

## Solution Notes:  
- Dynamic Programming typical complete backpack problem
- Solution 1: Using 2D array
    - i is item index, j is bag's total weight
    - dp[i][j] is max bag value
    - Initialize dp[i][0] with 0
    - Initialize dp[0][j]: dp[0][j] = dp[0][j - weight[0]] + value[0];
    - dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - weight[i]] + value[i])

- Solution 2: Using 1D array
    - copy dp[i - 1] layer to dp[i], then dp[i][j] = max(dp[i][j], dp[i][j - weight[i]] + value[i])
    - then dp[j] = max(dp[j], dp[j - weight[i]] + value[i])
    - The order of the two nested for loops doesn't matter when using 1D array in complete backpack problems.


## Codes:
```JavaScript
// Solution 1: using 2D array
const readline = require('readline').createInterface({
    input: process.stdin,
    output: process.stdout
})

let input = [];

readline.on('line', (line) => {
    input.push(line);
})

readline.on('close', () => {
    const [n, weight] = input[0].split(" ").map(Number);
    const w = new Array(n).fill(0);
    const v = new Array(n).fill(0);

    for (let i = 1; i <= n; i++) {
        [w[i - 1], v[i - 1]] = input[i].split(" ").map(Number);
    }

    const dp = Array.from({ length: n }, () => new Array(weight + 1).fill(0));

    for (let j = w[0]; j <= weight; j++) {
        dp[0][j] = dp[0][j - w[0]] + v[0];
    }

    for (let i = 1; i < n; i++) {
        for (let j = 1; j <= weight; j++) {
            dp[i][j] = w[i] > j
                ? dp[i - 1][j]
                : Math.max(dp[i - 1][j], dp[i][j - w[i]] + v[i]);
        }
    }
    console.log(dp[n - 1][weight]);
});

// Solution 2: using 1D array
const readline = require('readline').createInterface({
    input: process.stdin,
    output: process.stdout
})

let input = [];

readline.on('line', (line) => {
    input.push(line);
})

readline.on('close', () => {
    const [n, weight] = input[0].split(" ").map(Number);
    const w = new Array(n).fill(0);
    const v = new Array(n).fill(0);

    for (let i = 1; i <= n; i++) {
        [w[i - 1], v[i - 1]] = input[i].split(" ").map(Number);
    }

    const dp = new Array(weight + 1).fill(0);

    for (let i = 0; i < n; i++) {
        for (let j = 1; j <= weight; j++) {
            dp[j] = w[i] > j
                ? dp[j]
                : Math.max(dp[j], dp[j - w[i]] + v[i]);
        }
    }
    console.log(dp[weight]);
});
```

```Java
// Solution 1: using 2D array
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int weight = scanner.nextInt();

        int[] w = new int[n];
        int[] v = new int[n];

        for (int i = 0; i < n; i++) {
            w[i] = scanner.nextInt();
            v[i] = scanner.nextInt();
        }

        int[][] dp = new int[n][weight + 1];

        for(int j = w[0]; j <= weight; j++) {
            dp[0][j] = dp[0][j - w[0]] + v[0];
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= weight; j++) {
                if (j < w[i]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - w[i]] + v[i]);
                }
            }
        }
        System.out.println(dp[n - 1][weight]);
        scanner.close();

    }
}

// Solution 2: using 1D array
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int weight = scanner.nextInt();

        int[] w = new int[n];
        int[] v = new int[n];

        for (int i = 0; i < n; i++) {
            w[i] = scanner.nextInt();
            v[i] = scanner.nextInt();
        }

        int[] dp = new int[weight + 1];

        for (int i = 0; i < n; i++) {
            for (int j = 1; j <= weight; j++) {
                if (j < w[i]) {
                    continue;
                } else {
                    dp[j] = Math.max(dp[j], dp[j - w[i]] + v[i]);
                }
            }
        }
        System.out.println(dp[weight]);
        scanner.close();

    }
}
```

