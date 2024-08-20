# 106. Construct Binary Tree from Postorder and Inorder Traversal - Medium

## Problem Statement:

Given two integer arrays inorder and postorder where inorder is the inorder traversal of a binary tree and postorder is the postorder traversal of the same tree, construct and return the binary tree.

 

### Example 1:  
![](https://assets.leetcode.com/uploads/2021/02/19/tree.jpg)

**Input:** inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]  
**Output:** [3,9,20,null,null,15,7]  

### Example 2:  

**Input:** inorder = [-1], postorder = [-1] 
**Output:** [-1]  
 

### Constraints:  
- 1 <= inorder.length <= 3000
- postorder.length == inorder.length
- -3000 <= inorder[i], postorder[i] <= 3000
- inorder and postorder consist of unique values.
- Each value of postorder also appears in inorder.
- inorder is guaranteed to be the inorder traversal of the tree.
- postorder is guaranteed to be the postorder traversal of the tree.

## Solution Notes:  
- Use hashmap to store the index
- The last element of postorder array is the root, then we find the index of the root in inorder array. 
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
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder.length == 0 || postorder.length == 0) return null;
        hm = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            hm.put(inorder[i], i);
        }
        
        return getTree(inorder, 0, inorder.length, postorder, 0, postorder.length);
    }

    public TreeNode getTree(int[] inorder, int inStart, int inEnd, int[] postorder, int poStart, int poEnd){
        if (inStart >= inEnd || poStart >= poEnd) return null;

        int rootIndex = hm.get(postorder[poEnd - 1]);
        TreeNode root = new TreeNode(inorder[rootIndex]);

        root.left = getTree(inorder, inStart, rootIndex, postorder, poStart, poStart + rootIndex - inStart);
        root.right = getTree(inorder, rootIndex + 1, inEnd, postorder, poStart + rootIndex - inStart, poEnd - 1);

        return root;
    }
}
```Java