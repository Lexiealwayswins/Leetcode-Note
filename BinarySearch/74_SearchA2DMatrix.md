# 74. Search a 2D Matrix - Medium

## Problem Statement:

You are given an m x n integer matrix matrix with the following two properties:

- Each row is sorted in non-decreasing order.
- The first integer of each row is greater than the last integer of the previous row.

Given an integer target, return true if target is in matrix or false otherwise.

You must write a solution in O(log(m * n)) time complexity.

### Example 1:
![](https://assets.leetcode.com/uploads/2020/10/05/mat.jpg)

**Input:** matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
**Output:** true

### Example 2:
![](https://assets.leetcode.com/uploads/2020/10/05/mat2.jpg)

**Input:** matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
**Output:** false


### Constraints:
- m == matrix.length
- n == matrix[i].length
- 1 <= m, n <= 100
- -10^4 <= matrix[i][j], target <= 10^4

## Solution Notes:
- Binary Search
- be careful about the boundary

## Codes:
- TypeScript
```TypeScript
function searchMatrix(matrix: number[][], target: number): boolean {
    let r_l = 0, r_r = matrix.length - 1;
    let c_l = 0, c_r = matrix[0].length - 1;

    while (r_l <= r_r && c_l <= c_r) {
        let r_m = r_l + Math.floor((r_r - r_l) / 2);
        if (target < matrix[r_m][0]) {
            r_r = r_m - 1;
        } else if (target > matrix[r_m][matrix[0].length - 1]) {
            r_l = r_m + 1;
        } else {
            let c_m = c_l + Math.floor((c_r - c_l) / 2);
            if (target < matrix[r_m][c_m]) {
                c_r = c_m - 1;
            } else if (target > matrix[r_m][c_m]) {
                c_l = c_m + 1;
            } else {
                return true;
            }
        }
    }
    return false;
};
```