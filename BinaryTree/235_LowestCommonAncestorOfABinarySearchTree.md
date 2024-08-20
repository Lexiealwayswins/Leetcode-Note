# 235. Lowest Common Ancestor of a Binary Search Tree - Medium

## Problem Statement:

Given a binary search tree (BST), find the lowest common ancestor (LCA) node of two given nodes in the BST.

According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”

### Example 1:  
![](https://assets.leetcode.com/uploads/2018/12/14/binarysearchtree_improved.png)  
**Input:** root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8  
**Output:** 6  
**Explanation:** The LCA of nodes 2 and 8 is 6.  

### Example 2:  
![](https://assets.leetcode.com/uploads/2018/12/14/binarysearchtree_improved.png)  

**Input:** root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4  
**Output:** 2  
**Explanation:** The LCA of nodes 2 and 4 is 2, since a node can be a descendant of itself according to the LCA definition.  

### Example 3:  

**Input:** root = [2,1], p = 2, q = 1  
**Output:** 2  

### Constraints:  

- The number of nodes in the tree is in the range [2, 105].
- -109 <= Node.val <= 109
- All Node.val are unique.
- p != q
- p and q will exist in the BST.

## Solution Notes:  
### 1. Recursion
- Don't forget this is a binary search tree

### 2. Iteration
- Use a while loop in iteration

## Codes:
```Java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
// Recursion
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (p.val < root.val && q.val < root.val) return lowestCommonAncestor(root.left, p, q);
        if (p.val > root.val && q.val > root.val) return lowestCommonAncestor(root.right, p, q);
        return root;
    }
}

// Iteration
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        while (true) {
            if (p.val < root.val && q.val < root.val) {
                root = root.left;
            } else if (p.val > root.val && q.val > root.val) {
                root = root.right;
            } else {
                break;
            }
        }
        return root;
    }
}

```Java