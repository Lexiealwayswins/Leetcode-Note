# 110. Balanced Binary Tree - Easy

## Problem Statement:

Given a binary tree, determine if it is height-balanced.  

### Example 1:  
![](https://assets.leetcode.com/uploads/2020/10/06/balance_1.jpg)  

**Input:** root = [3,9,20,null,null,15,7]  
**Output:** true  

### Example 2:  
![](https://assets.leetcode.com/uploads/2020/10/06/balance_2.jpg)  

**Input:** root = [1,2,2,3,3,null,null,4,4]  
**Output:** false  

### Example 3:  

**Input:** root = []  
**Output:** true  
 
## Constraints:
- The number of nodes in the tree is in the range [0, 5000].
- -104 <= Node.val <= 104

## Solution Notes:  
- Use recursion
- The balance tree is a tree which the height difference of its left tree and its right tree is less than 2.

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
    public boolean isBalanced(TreeNode root) {
        return checkBalance(root) != -1;
    }
    public int checkBalance(TreeNode root){
        if (root == null) return 0;

        int l = checkBalance(root.left);
        int r = checkBalance(root.right);

        if (l == -1 || r == -1) return -1; 
        if (l - r > 1 || r - l > 1) return -1;

        return Math.max(l, r) + 1;
    }
}
```Java