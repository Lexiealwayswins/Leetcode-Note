# 404. Sum of Left Leaves - Easy

## Problem Statement:

Given the root of a binary tree, return the sum of all left leaves.

A leaf is a node with no children. A left leaf is a leaf that is the left child of another node.

 
# Example 1:  
![](https://assets.leetcode.com/uploads/2021/04/08/leftsum-tree.jpg)

**Input:** root = [3,9,20,null,null,15,7]  
**Output:** 24  
**Explanation:** There are two left leaves in the binary tree, with values 9 and 15 respectively.  

# Example 2:    

**Input:** root = [1]  
**Output:** 0  
 

## Constraints:  

- The number of nodes in the tree is in the range [1, 1000].
- -1000 <= Node.val <= 1000

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
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) return 0;

        int leftLeaves = 0;
        if (root.left != null && root.left.left == null && root.left.right == null) {
        leftLeaves = root.left.val;
        }

        int sum = leftLeaves + sumOfLeftLeaves(root.left) + sumOfLeftLeaves(root.right);
        
        return sum;
    }
}
```Java

