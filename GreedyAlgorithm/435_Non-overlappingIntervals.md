# 435. Non-overlapping Intervals - Medium

## Problem Statement:  

Given an array of intervals intervals where intervals[i] = [starti, endi], return the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.

Note that intervals which only touch at a point are non-overlapping. For example, [1, 2] and [2, 3] are non-overlapping.


### Example 1:  

**Input:**: intervals = [[1,2],[2,3],[3,4],[1,3]]
**Output:**: 1
**Explanation:**: [1,3] can be removed and the rest of the intervals are non-overlapping.

### Example 2:  

**Input:**: intervals = [[1,2],[1,2],[1,2]]
**Output:**: 2
**Explanation:**: You need to remove two [1,2] to make the rest of the intervals non-overlapping.

### Example 3:  

**Input:**: intervals = [[1,2],[2,3]]
**Output:**: 0
**Explanation:**: You don't need to remove any of the intervals since they're already non-overlapping.
 

### Constraints:

- 1 <= intervals.length <= 105
- intervals[i].length == 2
- -5 * 104 <= starti < endi <= 5 * 104

## Solution Notes:  
- Greedy Algorithm: sort the array firstly, and try to record the overlapping range
- Similar to problem 452

## Codes:
```TypeScript
function eraseOverlapIntervals(intervals: number[][]): number {
    intervals.sort((a, b) => {
        if (a[0] === b[0]) {
            return a[1] - b[1];
        }
        return a[0] - b[0];
    })

    let res: number = 0;
    for (let i = 1; i < intervals.length; i++) {
        if (intervals[i - 1][1] > intervals[i][0]) {
            res++;
            // intervals[i][0] = Math.max(intervals[i - 1][0], intervals[i][0]);
            intervals[i][1] = Math.min(intervals[i - 1][1], intervals[i][1]);
        }
    }
    return res;
};

```

