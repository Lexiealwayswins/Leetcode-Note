# 572. Subtree of Another Tree - Easy

## Problem Statement:

Given the roots of two binary trees root and subRoot, return true if there is a subtree of root with the same structure and node values of subRoot and false otherwise.

A subtree of a binary tree tree is a tree that consists of a node in tree and all of this node's descendants. The tree tree could also be considered as a subtree of itself.

### Example 1:  
![](https://assets.leetcode.com/uploads/2021/04/28/subtree1-tree.jpg)

**Input:** root = [3,4,5,1,2], subRoot = [4,1,2]  
**Output:** true  

### Example 2:  
![](https://assets.leetcode.com/uploads/2021/04/28/subtree2-tree.jpg)

**Input:** root = [3,4,5,1,2,null,null,null,null,0], subRoot = [4,1,2]  
**Output:** false  
 
## Constraints:

- The number of nodes in the root tree is in the range [1, 2000].
- The number of nodes in the subRoot tree is in the range [1, 1000].
- -104 <= root.val <= 104
- -104 <= subRoot.val <= 104

## Solution Notes:  
- Use two recrusion processes
- One recursion is to check the start of the subtree
- Another recursion is to check whether the subtree is identical to the subroot

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
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        return check(root, subRoot);
    }

    public boolean check(TreeNode root, TreeNode subRoot) {
        if (root == null) return false;

        if (root.val == subRoot.val) {
            if (isSame(root, subRoot)) return true;
        }

        return check(root.left, subRoot) || check(root.right, subRoot);
    }

    public boolean isSame(TreeNode root, TreeNode subRoot){
        if (root == null && subRoot == null) return true;
        if (root == null || subRoot == null || root.val != subRoot.val) return false;

        if (!isSame(root.left, subRoot.left)) return false;
        if (!isSame(root.right, subRoot.right)) return false;

        return true;
    }
}

```Java