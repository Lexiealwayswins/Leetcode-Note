# 42. Trapping Rain Water - Hard

## Problem Statement:

Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.

### Example 1:
![](https://assets.leetcode.com/uploads/2018/10/22/rainwatertrap.png)

**Input:** height = [0,1,0,2,1,0,1,3,2,1,2,1]
**Output:** 6
**Explanation:** The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped.

### Example 2:

**Input:** height = [4,2,0,3,2,5]
**Output:** 9

### Constraints:

- n == height.length
- 1 <= n <= 2 * 104
- 0 <= height[i] <= 105

## Solution Notes:
- similar to problem 503
- try to record the mid

## Codes:
```Java
class Solution {
    public int trap(int[] height) {
        Stack<Integer> st = new Stack<>();
        int res = 0;
        st.push(0);

        for (int i = 1; i < height.length; i++) {
            while (!st.isEmpty() && height[st.peek()] < height[i]) {
                int mid = st.pop();
                if (!st.isEmpty()) {
                    int h = Math.min(height[st.peek()], height[i]) - height[mid];
                    int w = i - st.peek() - 1;
                    res += h * w;
                }
            }
            st.push(i);
        }
        return res;
    }
}
```

```TypeScript
function trap(height: number[]): number {
    let res = 0;
    const stack: number[] = [];
    stack.push(0);
    for (let i = 1; i < height.length; i++) {
        while (stack.length && height[stack[stack.length - 1]] < height[i]) {
            let mid = stack.pop();
            if (stack.length) {
                const h = Math.min(height[stack[stack.length - 1]], height[i]) - height[mid];
                const w = i - stack[stack.length - 1] - 1;
                res += h * w;
            }
        }
        stack.push(i);
    }
    return res;
};
```