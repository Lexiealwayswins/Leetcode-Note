# 62. Unique Paths - Medium

## Problem Statement:  
![](https://kamacoder.com/problempage.php?pid=1046)

小明是一位科学家，他需要参加一场重要的国际科学大会，以展示自己的最新研究成果。他需要带一些研究材料，但是他的行李箱空间有限。这些研究材料包括实验设备、文献资料和实验样本等等，它们各自占据不同的空间，并且具有不同的价值。
小明的行李空间为 N，问小明应该如何抉择，才能携带最大价值的研究材料，每种研究材料只能选择一次，并且只有选与不选两种选择，不能进行切割。

Xiaoming is a scientist who needs to attend an important international scientific conference to present his latest research findings. He needs to bring some research materials, but his suitcase has limited space. These materials include experimental equipment, literature, and experimental samples, each occupying different spaces and possessing different values.

### Input Statement:

- 第一行包含两个正整数，第一个整数 M 代表研究材料的种类，第二个正整数 N，代表小明的行李空间。
- 第二行包含 M 个正整数，代表每种研究材料的所占空间。 
- 第三行包含 M 个正整数，代表每种研究材料的价值。

- The first line contains two positive integers: the first integer M represents the type of research material, and the second positive integer N represents Xiaoming's luggage space.

- The second line contains M positive integers, representing the space occupied by each type of research material.

- The third line contains M positive integers, representing the value of each type of research material.

### Output Statement:

- 输出一个整数，代表小明能够携带的研究材料的最大价值。

- Output an integer representing the maximum value of the research materials that Xiaoming can carry.

### Example 1:  

**Input:**
```Bash
6 1
2 2 3 1 5 2
2 3 1 5 4 3
```
**Output:** 5
**Explanation:**: 
小明能够携带 6 种研究材料，但是行李空间只有 1，而占用空间为 1 的研究材料价值为 5，所以最终答案输出 5。 
数据范围：
1 <= N <= 5000
1 <= M <= 5000
研究材料占用空间和价值都小于等于 1000

Xiaoming can carry 6 types of research materials, but his luggage space is only 1 unit. The research material occupying 1 unit of space has a value of 5, so the final output is 5.

Data range:
1 <= N <= 5000
1 <= M <= 5000
The space occupied and value of research materials are both less than or equal to 1000.

## Solution Notes:  
- Dynamic Programming typical backpack problem
- Solution 1: Using 2D array
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
// Solution 1: using 2D array
const readline = require('readline').createInterface({
    input: process.stdin,
    output: process.stdout
});

let input = [];

readline.on('line', (line) => {
    input.push(line);
});

readline.on('close', () => {
    const [n, bagWeight] = input[0].split(' ').map(Number);
    const weight = input[1].split(' ').map(Number);
    const value = input[2].split(' ').map(Number);

    // const dp = new Array(n).fill(0).map(() => new Array(bagWeight + 1).fill(0));
    const dp = Array.from({ length: n }, () => new Array(bagWeight + 1).fill(0));

    // i is item index, j is bag's total weight
    // dp[i][j] is max bag value
    for (let i = 0; i < n; i++) dp[i][0] = 0;
    for (let j = weight[0]; j <= bagWeight; j++) {
        dp[0][j] = value[0];
    }

    for (let i = 1; i < n; i++) {
        for (let j = 0; j <= bagWeight; j++) {
            dp[i][j] = weight[i] > j
                ? dp[i - 1][j]
                : Math.max(dp[i - 1][j], dp[i - 1][j - weight[i]] + value[i]);
        }
    }

    console.log(dp[n - 1][bagWeight]);
});

// Solution 2: using 1D array
const readline = require('readline').createInterface({
    input: process.stdin,
    output: process.stdout
});

let input = [];

readline.on('line', (line) => {
    input.push(line);
});

readline.on('close', () => {
    const [n, bagWeight] = input[0].split(' ').map(Number);
    const weight = input[1].split(' ').map(Number);
    const value = input[2].split(' ').map(Number);

    const dp = new Array(bagWeight + 1).fill(0);
    dp[0] = 0;

    // i is item index, j is bag's total weight
    // dp[j] is max bag value
    for (let i = 0; i < n; i++) {
        for (let j = bagWeight; j >= weight[i]; j--) {
            dp[j] = Math.max(dp[j], dp[j - weight[i]] + value[i]);
        }
    }

    console.log(dp[bagWeight]);
});
```

