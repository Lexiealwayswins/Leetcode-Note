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

## Codes:
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
