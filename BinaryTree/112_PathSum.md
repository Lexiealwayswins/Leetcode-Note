# 112. Path Sum - Easy

## Problem Statement:

Given the root of a binary tree and an integer targetSum, return true if the tree has a root-to-leaf path such that adding up all the values along the path equals targetSum.

A leaf is a node with no children.

### Example 1:  
![](https://assets.leetcode.com/uploads/2021/01/18/pathsum1.jpg)

**Input:** root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22  
**Output:** true  
**Explanation:** The root-to-leaf path with the target sum is shown.  

### Example 2:  
![](https://assets.leetcode.com/uploads/2021/01/18/pathsum2.jpg)

**Input:** root = [1,2,3], targetSum = 5  
**Output:** false  
**Explanation:** There two root-to-leaf paths in the tree:  
(1 --> 2): The sum is 3.  
(1 --> 3): The sum is 4.  
There is no root-to-leaf path with sum = 5.  

### Example 3:  

**Input:** root = [], targetSum = 0  
**Output:** false  
**Explanation:** Since the tree is empty, there are no root-to-leaf paths.  
 

## Constraints:

- The number of nodes in the tree is in the range [0, 5000].
- -1000 <= Node.val <= 1000
- -1000 <= targetSum <= 1000

## Solution Notes:  
### 1. Recursion
- Use preorder recursion
- Sustract the node value in each recursion, and then check whether the target sum is 0 in the end

### 2. Iteration
- Use a stack object to store both the node and the sum

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
// Recursion
class Solution {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;
        targetSum -= root.val;
        
        if (root.left == null && root.right == null) return targetSum == 0;

        if (root.left != null) {
            boolean l = hasPathSum(root.left, targetSum);
            if (l) return true;
        }

        if (root.right != null) {
            boolean r = hasPathSum(root.right, targetSum);
            if (r) return true;
        }

        return false;
    }
}

// Iteration
class Solution {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;
        Stack<Object> s = new Stack<>();
        s.push(root);
        s.push(root.val);
        while(!s.isEmpty()){
            Integer sum = (Integer) s.pop();
            TreeNode curr = (TreeNode) s.pop();
            if (curr.left == null && curr.right == null && sum == targetSum) {
                return true;
            }
            if (curr.right != null) {
                s.push(curr.right);
                s.push(sum + curr.right.val);
            }
            if (curr.left != null) {
                s.push(curr.left);
                s.push(sum + curr.left.val);
            }
        }
        return false;
    }
}
```Java