# 701. Insert into a Binary Search Tree - Medium

## Problem Statement:  

You are given the root node of a binary search tree (BST) and a value to insert into the tree. Return the root node of the BST after the insertion. It is guaranteed that the new value does not exist in the original BST.  

Notice that there may exist multiple valid ways for the insertion, as long as the tree remains a BST after insertion. You can return any of them.  


### Example 1:  
![](https://assets.leetcode.com/uploads/2020/10/05/insertbst.jpg)

**Input:** root = [4,2,7,1,3], val = 5  
**Output:** [4,2,7,1,3,5]  
**Explanation:** Another accepted tree is:  
![](https://assets.leetcode.com/uploads/2020/10/05/bst.jpg)

### Example 2:  

**Input:** root = [40,20,60,10,30,50,70], val = 25  
**Output:** [40,20,60,10,30,50,70,null,null,25]  

### Example 3:  

**Input:** root = [4,2,7,1,3,null,null,null,null,null,null], val = 5  
**Output:** [4,2,7,1,3,5]  
 

### Constraints:

- The number of nodes in the tree will be in the range [0, 104].
- -108 <= Node.val <= 108
- All the values Node.val are unique.
- -108 <= val <= 108
- It's guaranteed that val does not exist in the original BST.

## Solution Notes:  
### 1. Recursion
- Don't forget that this recursion needs to return the root

### 2. Iteration
- Use a parent node to record to the parent root of subtree when iteration

## Codes:
```TypeScript
/**
 * Definition for a binary tree node.
 * class TreeNode {
 *     val: number
 *     left: TreeNode | null
 *     right: TreeNode | null
 *     constructor(val?: number, left?: TreeNode | null, right?: TreeNode | null) {
 *         this.val = (val===undefined ? 0 : val)
 *         this.left = (left===undefined ? null : left)
 *         this.right = (right===undefined ? null : right)
 *     }
 * }
 */

function insertIntoBST(root: TreeNode | null, val: number): TreeNode | null {
    if (!root) return new TreeNode(val);
    if (val < root.val) root.left = insertIntoBST(root.left, val);
    if (val > root.val) root.right = insertIntoBST(root.right, val);
    return root;
};
```

```Python
# Iteration:
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def insertIntoBST(self, root: Optional[TreeNode], val: int) -> Optional[TreeNode]:
        if not root: return TreeNode(val)
        curr = root
        while True:
            if curr.val > val: 
                if curr.left:
                    curr = curr.left
                else:
                    curr.left = TreeNode(val)
                    break
            elif curr.val < val: 
                if curr.right:
                    curr = curr.right
                else:
                    curr.right = TreeNode(val)
                    break
        return root
```
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
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);
        if (val < root.val) root.left = insertIntoBST(root.left, val);
        if (val > root.val) root.right = insertIntoBST(root.right, val);
        return root;
    }
}

// Iteration
class Solution {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);
        TreeNode curr = root;
        TreeNode parent = root;
        while(curr != null){
            parent = curr;
            if (val < curr.val) {
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }

        if (val < parent.val) {
            parent.left = new TreeNode(val);
        } else {
           parent.right = new TreeNode(val); 
        }

        return root;
    }
}
```Java
