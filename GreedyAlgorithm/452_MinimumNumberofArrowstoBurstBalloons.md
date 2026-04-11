# 452. Minimum Number of Arrows to Burst Balloons - Medium

## Problem Statement:  

There are some spherical balloons taped onto a flat wall that represents the XY-plane. The balloons are represented as a 2D integer array points where points[i] = [xstart, xend] denotes a balloon whose horizontal diameter stretches between xstart and xend. You do not know the exact y-coordinates of the balloons.

Arrows can be shot up directly vertically (in the positive y-direction) from different points along the x-axis. A balloon with xstart and xend is burst by an arrow shot at x if xstart <= x <= xend. There is no limit to the number of arrows that can be shot. A shot arrow keeps traveling up infinitely, bursting any balloons in its path.

Given the array points, return the minimum number of arrows that must be shot to burst all balloons.


### Example 1:  

**Input:** points = [[10,16],[2,8],[1,6],[7,12]]
**Output:** 2
**Explanation:**  The balloons can be burst by 2 arrows:
- Shoot an arrow at x = 6, bursting the balloons [2,8] and [1,6].
- Shoot an arrow at x = 11, bursting the balloons [10,16] and [7,12].

### Example 2:  

Input: points = [[1,2],[3,4],[5,6],[7,8]]
Output: 4
Explanation: One arrow needs to be shot for each balloon for a total of 4 arrows.

### Example 3:  

Input: points = [[1,2],[2,3],[3,4],[4,5]]
Output: 2
Explanation: The balloons can be burst by 2 arrows:
- Shoot an arrow at x = 2, bursting the balloons [1,2] and [2,3].
- Shoot an arrow at x = 4, bursting the balloons [3,4] and [4,5].
 

### Constraints:

- 1 <= points.length <= 105
- points[i].length == 2
- -2^31 <= xstart < xend <= 2^31 - 1

## Solution Notes:  
- Greedy Algorithm: sort the array firstly, and try to record the overlapping range

## Codes:
```TypeScript
// Solution 1: modify the original array to record overlapping range
function findMinArrowShots(points: number[][]): number {
    let res: number = 1;
    points.sort((a, b) => a[0] - b[0]);
    for (let i = 1; i < points.length; i++) {
        if (points[i - 1][1] < points[i][0]) {
            res++;
        } else {
            points[i][1] = Math.min(points[i - 1][1], points[i][1]);
        }
    }
    return res;
};

// SOlution 2: use extra container to record the overlapping range
function findMinArrowShots(points: number[][]): number {
    let res: number = 1;
    let l = 0, r = 0;
    points.sort((a, b) => a[0] - b[0]);
    for (let i = 1; i < points.length; i++) {
        if (l === 0 && r === 0) {
            l = Math.max(points[i - 1][0], points[i][0]);
            r = Math.min(points[i - 1][1], points[i][1]);
        } else {
            l = Math.max(l, points[i][0]);
            r = Math.min(r, points[i][1]);
        }
        if (l > r) {
            l = 0;
            r = 0;
            res++;
        }
    }
    return res;
};

```

