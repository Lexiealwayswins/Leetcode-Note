# Interval Sum

## Problem Statement:

[KamaCoderLink](https://kamacoder.com/problempage.php?pid=1070)  
Given an integer array Array, calculate the sum of the elements within each specified interval.

### Input Description
- The first line contains an integer n, the length of the array.

- The next n lines each contain one integer, representing the elements of the array.

- Following that, each line contains two integers a and b (with b ≥ a), representing the indices of an interval. Input continues until the end of file.

### - Output Description
For each specified interval, output the sum of the elements within that interval.

### Example:

**Input**:  
```Text
5
1
2
3
4
5
0 1
1 3
```
**Output**:   
```Text
3
9
```

### Constraints:
- Data range: 0 < 𝑛 ≤ 100000

## Solution Notes:
- Using Accumulate Sum

## Codes:

- Python
```Python
import sys
input = sys.stdin.read

def main():
    data = input().split()
    n = int(data[0])
    arr = []
    for i in range(1, n + 1):
        arr.append(int(data[i]))
    
    sum_arr = []
    add = 0
    for i in range(1, n + 1):
        add += int(data[i])
        sum_arr.append(add)
    
    index = 1 + n
    res = []
    while index < len(data):
        a = int(data[index])
        b = int(data[index + 1])

        if a == 0:
            res.append(int(sum_arr[b]))
        else:
            res.append(int(sum_arr[b]) - int(sum_arr[a - 1]))

        index += 2
    
    for i in range(len(res)):
        print(res[i])


if __name__ == "__main__":
    main()
    
```

- JavaScript
```JavaScript
function solution() {
    const readline = require("readline");
    const rl = readline.createInterface({
        input: process.stdin,
        output: process.stdout
    });

    let data = [];
    rl.on('line', (line) => {
        data.push(line.trim());
    })

    rl.on('close', () => {
        const n = parseInt(data[0]);

        let arr = [];
        let sum = 0;
        for (let i = 1; i < n + 1; i++) {
            sum += parseInt(data[i]);
            arr.push(sum);
        }

        for (let i = n + 1; i < data.length; i++) {
            let [l, r] = data[i].split(" ").map(x => parseInt(x));
            
            if (l === 0) {
                console.log(arr[r]);
            } else {
                console.log(arr[r] - arr[l - 1]);
            }
        }
    });
}

solution();
```