# 257. Binary Tree Paths - Easy

## Problem Statement:

Given the root of a binary tree, return all root-to-leaf paths in any order.

A leaf is a node with no children.


### Example 1:  

![](https://assets.leetcode.com/uploads/2021/03/12/paths-tree.jpg)  

**Input:** root = [1,2,3,null,5]  
**Output:** ["1->2->5","1->3"]  

### Example 2:  

**Input:** root = [1]  
**Output:** ["1"]  
 
### Constraints:  

The number of nodes in the tree is in the range [1, 100].  
- -100 <= Node.val <= 100  

## Solution Notes:  
### 1. Use recursion
- The end case should be the leaf and record the path
- Don't forget to go back to the previous node in path when returning to the previous level in recursion

### 2. Use Stack
- Use a object stack to store the TreeNode and the path String in the meantime

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
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        if (root == null) return res;
        List<Integer> path = new ArrayList<>();
        getPath(res, path, root);
        return res;
    }

    public void getPath(List<String> res, List<Integer> path, TreeNode root){
        path.add(root.val);
        if (root != null && root.left == null && root.right == null) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < path.size() - 1; i++){
                sb.append(path.get(i) + "->");
            }
            sb.append(path.get(path.size() - 1));
            res.add(sb.toString());
            return;
        }
        if (root.left != null){
            getPath(res, path, root.left);
            path.remove(path.size() - 1);
        }
        
        if (root.right != null) {
            getPath(res, path, root.right);
            path.remove(path.size() - 1);
        }
    }
}

// Stack
class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        if (root == null) return res;
        Stack<Object> stack = new Stack<>();
        stack.push(root);
        stack.push(root.val + "");
        while(!stack.isEmpty()){
            String path = (String) stack.pop();
            TreeNode curr =  (TreeNode) stack.pop();
            
            if (curr.left == null && curr.right == null) {
                res.add(path);
            }
            if (curr.left != null) {
                stack.push(curr.left);
                stack.push(path + "->" + curr.left.val);
            }
            if (curr.right != null) {
                stack.push(curr.right);
                stack.push(path + "->" + curr.right.val);
            }
        }
        return res;
    }
}
```Java