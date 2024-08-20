# 105. Construct Binary Tree from Preorder and Inorder Traversal - Medium

## Problem Statement:

Given two integer arrays preorder and inorder where preorder is the preorder traversal of a binary tree and inorder is the inorder traversal of the same tree, construct and return the binary tree.

 

### Example 1:  
![](https://assets.leetcode.com/uploads/2021/02/19/tree.jpg)

**Input:** preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]  
**Output:** [3,9,20,null,null,15,7]  

### Example 2:  

**Input:** preorder = [-1], inorder = [-1]  
**Output:** [-1]  
 

### Constraints:  
- 1 <= preorder.length <= 3000
- inorder.length == preorder.length
- -3000 <= preorder[i], inorder[i] <= 3000
- preorder and inorder consist of unique values.
- Each value of inorder also appears in preorder.
- preorder is guaranteed to be the preorder traversal of the tree.
- inorder is guaranteed to be the inorder traversal of the tree.

## Solution Notes:  
- Use hashmap to store the index
- The first element of preorder array is the root, then we find the index of the root in inorder array. 
- Use recursion to get the left and right subtrees.

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
    HashMap<Integer, Integer> hm;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0 || inorder.length == 0) return null;
        hm = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            hm.put(inorder[i], i);
        }
        return getTree(preorder, 0, preorder.length, inorder, 0, inorder.length);
    }

    public TreeNode getTree(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd){
        if (preStart >= preEnd || inStart >= inEnd) return null;

        int rootIndex = hm.get(preorder[preStart]);
        TreeNode root = new TreeNode(inorder[rootIndex]);

        root.left = getTree(preorder, preStart + 1, preStart + 1 + rootIndex - inStart, inorder, inStart, rootIndex);
        root.right = getTree(preorder, preStart + 1 + rootIndex - inStart, preEnd, inorder, rootIndex + 1, inEnd);

        return root;
    }
}
```Java