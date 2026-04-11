# 763. Partition Labels - Medium

## Problem Statement:  

Given an array of intervals intervals where intervals[i] = [starti, endi], return the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.

Note that intervals which only touch at a point are non-overlapping. For example, [1, 2] and [2, 3] are non-overlapping.


### Example 1:  

**Input:**: s = "ababcbacadefegdehijhklij"
**Output:**: [9,7,8]
**Explanation:**:
The partition is "ababcbaca", "defegde", "hijhklij".
This is a partition so that each letter appears in at most one part.
A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits s into less parts.

### Example 2:  

**Input:**: s = "eccbbbbdec"
**Output:**: [10]
 

### Constraints:

- 1 <= s.length <= 500
- s consists of lowercase English letters.

## Solution Notes:  
- Greedy Algorithm
- Solution 1: Collect the range of all characters, then Check the overlapping ranges
- Solution 2: Collect the biggest index of each characters, then check where it ends
## Codes:
```TypeScript
// Solution 1:
function partitionLabels(s: string): number[] {
    const map = new Map<string, number[]>();
    // Collect the range of all characters
    for (let i = 0; i < s.length; i++) {
        if (map.has(s[i])) {
            map.set(s[i], [map.get(s[i])[0], i]);
        } else {
            map.set(s[i], [i, i]);
        }
    }
    // Check the overlapping ranges
    const res: number[] = [];
    const arr: number[][] = [...map.values()];
    arr.sort((a, b) => a[0] - b[0]);
    for (let i = 1; i < arr.length; i++) {
        if (arr[i - 1][1] > arr[i][0]) {
            arr[i][0] = Math.min(arr[i - 1][0], arr[i][0]);
            arr[i][1] = Math.max(arr[i - 1][1], arr[i][1]);
        } else {
            res.push(arr[i - 1][1] - arr[i - 1][0] + 1);
        }
    }
    res.push(arr[arr.length - 1][1] - arr[arr.length - 1][0] + 1);
    return res;
};

// Solution 2:
function partitionLabels(s: string): number[] {
    const map = new Map<string, number>();
    // Collect the biggest index all characters
    for (let i = 0; i < s.length; i++) {
        map.set(s[i], i);
    }
    // Check the ends and store results
    const res: number[] = [];
    let left = 0; 
    let right = 0;
    for (let i = 0; i < s.length; i++) {
        right = Math.max(map.get(s[i]), right);
        if (i === right) {
            res.push(right - left + 1);
            left = i + 1;
        }
    }
    return res;
};

```

