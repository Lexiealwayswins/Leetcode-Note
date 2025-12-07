# 69. Sqrt(x) - Easy

## Problem Statement:
Given a non-negative integer x, return the square root of x rounded down to the nearest integer. The returned integer should be non-negative as well.

You must not use any built-in exponent function or operator.

For example, do not use pow(x, 0.5) in c++ or x ** 0.5 in python.

### Example 1:

**Input**: x = 4
**Output**: 2
**Explanation**: The square root of 4 is 2, so we return 2.

### Example 2:

**Input**: x = 8
**Output**: 2
**Explanation**: The square root of 8 is 2.82842..., and since we round it down to the nearest integer, 2 is returned.

### Constraints:

0 <= x <= 231 - 1

## Solution Notes:
- Binary Search, be careful about the boundary
- Square may be bigger than the int range, try to use long type

## Codes:
- Python
```Python
class Solution:
    def mySqrt(self, x: int) -> int:
        l, r = 0, x
        while l <= r:
            m = l + (r - l) // 2
            if m * m < x:
                l = m + 1
            elif m * m > x:
                r = m - 1
            else:
                return m
        return r
```

- JavaScript
```JavaScript
/**
 * @param {number} x
 * @return {number}
 */
var mySqrt = function(x) {
    let l = 0, r = x;
    while (l <= r) {
        let m = Math.floor(l + (r - l) / 2);
        if (m * m < x) {
            l = m + 1;
        } else if (m * m > x) {
            r = m - 1;
        } else {
            return m;
        }
    }
    return r;
};
```

- Java
```Java
class Solution {
    public int mySqrt(int x) {
        if (x == 0 || x == 1) return x;
        int l = 0, r = x;
        while (l <= r) {
            int m = l + (r - l) / 2;
            long sqr = (long) m * m;
            if (sqr == x) {
                return m;
            } else if (sqr < x) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return r;
    }
}
```