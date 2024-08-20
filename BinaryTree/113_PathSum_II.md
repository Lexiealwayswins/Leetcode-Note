# 113. Path Sum II - Medium

## Problem Statement:

Given the root of a binary tree and an integer targetSum, return all root-to-leaf paths where the sum of the node values in the path equals targetSum. Each path should be returned as a list of the node values, not node references.

A root-to-leaf path is a path starting from the root and ending at any leaf node. A leaf is a node with no children.

### Example 1:  
![](https://assets.leetcode.com/uploads/2021/01/18/pathsumii1.jpg)

**Input:** root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22  
**Output:** [[5,4,11,2],[5,8,4,5]]  
**Explanation:** There are two paths whose sum equals targetSum:  
5 + 4 + 11 + 2 = 22  
5 + 8 + 4 + 5 = 22  

### Example 2:  
![](https://assets.leetcode.com/uploads/2021/01/18/pathsum2.jpg)

**Input:** root = [1,2,3], targetSum = 5  
**Output:** []  

### Example 3:  

**Input:** root = [1,2], targetSum = 0  
**Output:** []  
 

### Constraints:  
- The number of nodes in the tree is in the range [0, 5000].  
- -1000 <= Node.val <= 1000  
- -1000 <= targetSum <= 1000  

## Solution Notes:  
### 1. Recursion
- Use preorder recursion, don't forget to make path go back to the subroot when switching from left to right recursion  

### 2. Iteration
- Use a object stack to store the root and sum in the meantime, and use another stack to store the path. Don't forget to create new Arraylist when getting a new path  
- Use the preorder method to get the path  


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
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;

        List<Integer> path = new ArrayList<>();
        getPath(root, targetSum, res, path);
        return res;
    }

    public void getPath(TreeNode root, int targetSum, List<List<Integer>> res, List<Integer> path){
        path.add(root.val);
        if (root.left == null && root.right == null){
            if (targetSum - root.val == 0) res.add(new ArrayList(path));
        }

        if (root.left != null) {
            getPath(root.left, targetSum - root.val, res, path);
            path.remove(path.size() - 1);
        }

        if (root.right != null) {
            getPath(root.right, targetSum - root.val, res, path);
            path.remove(path.size() - 1);
        }
    }
}

// Iteration
class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;

        Stack<Object> stack = new Stack<>();
        stack.push(root);
        stack.push(root.val);

        Stack<List<Integer>> s = new Stack<>();
        s.push(new ArrayList());

        while(!stack.isEmpty()){
            Integer sum = (Integer) stack.pop();
            TreeNode curr = (TreeNode) stack.pop();
            List<Integer> path = s.pop();
            path.add(curr.val);

            if (curr.left == null && curr.right == null){
                if (sum == targetSum) res.add(path);

            }
            if (curr.right != null) {
                stack.push(curr.right);
                stack.push(curr.right.val + sum);
                s.push(new ArrayList(path));
            }
            
            if (curr.left != null) {
                stack.push(curr.left);
                stack.push(curr.left.val + sum);
                s.push(new ArrayList(path));
            }
        }
        return res;
    }
}

```Java
