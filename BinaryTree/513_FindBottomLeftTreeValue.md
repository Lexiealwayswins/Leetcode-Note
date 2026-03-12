# 513. Find Bottom Left Tree Value - Medium

## Problem Statement:

Given the root of a binary tree, return the leftmost value in the last row of the tree.

### Example 1:  
![](https://assets.leetcode.com/uploads/2020/12/14/tree1.jpg)

**Input:** root = [2,1,3]  
**Output:** 1  

### Example 2:  
![](https://assets.leetcode.com/uploads/2020/12/14/tree2.jpg)

**Input:** root = [1,2,3,4,null,5,6,null,null,7]  
**Output:** 7  

## Constraints:

- The number of nodes in the tree is in the range [1, 104].
- -231 <= Node.val <= 231 - 1

## Solution Notes:  
- Use recursion
- Use BFS Iteration

## Codes:
```Python
# BFS Iteration
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def findBottomLeftValue(self, root: Optional[TreeNode]) -> int:
        if not root: return
        if not root.left and not root.right: return root.val
        res = 0
        deque = collections.deque([root])
        while deque:
            count = len(deque)
            for i in range(count):
                curr = deque.popleft()
                if i == 0:
                    res = curr.val
                if curr.left: deque.append(curr.left)
                if curr.right: deque.append(curr.right)
        return res
```

```JavaScript
// Recursion
/**
 * Definition for a binary tree node.
 * function TreeNode(val, left, right) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.left = (left===undefined ? null : left)
 *     this.right = (right===undefined ? null : right)
 * }
 */
/**
 * @param {TreeNode} root
 * @return {number}
 */
var findBottomLeftValue = function(root) {
    if (!root) return;
    if (!root.left && !root.right) return root.val;

    let max_depth = -Infinity;
    let res = 0;

    const traversal = (node, level) => {
        if (!node) return;
        if (!node.left && !node.right) {
            if (level > max_depth) {
                max_depth = level;
                res = node.val;
            }
            return;
        }
        if (node.left) traversal(node.left, level + 1);
        if (node.right) traversal(node.right, level + 1);
        return;
    }
    traversal(root, 0);
    return res;
};
```

```Java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    private int maxDeep = 0;
    private int res = Integer.MIN_VALUE;
    public int findBottomLeftValue(TreeNode root) {
        int deep = 1;
        traversal(root, deep);
        return res;
    }

    public void traversal(TreeNode root, int deep){
        if (root == null) return;
        if (root.left == null && root.right == null){
            if (maxDeep < deep){
                maxDeep = deep;
                res = root.val;
            }
        }

        if (root.left != null) traversal(root.left, deep + 1);
        if (root.right != null) traversal(root.right, deep + 1);
    }
}
```Java
