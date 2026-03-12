# 700. Search in a Binary Search Tree - Easy

## Problem Statement:

You are given the root of a binary search tree (BST) and an integer val.

Find the node in the BST that the node's value equals val and return the subtree rooted with that node. If such a node does not exist, return null.


### Example 1:  
![](https://assets.leetcode.com/uploads/2021/01/12/tree1.jpg)

**Input:** root = [4,2,7,1,3], val = 2
**Output:** [2,1,3]

### Example 2:  
![](https://assets.leetcode.com/uploads/2021/01/12/tree2.jpg)

**Input:** root = [4,2,7,1,3], val = 5
**Output:** []
 

### Constraints:  
- The number of nodes in the tree is in the range [1, 5000].
- 1 <= Node.val <= 107
- root is a binary search tree.
- 1 <= val <= 107

## Solution Notes:  
- BFS Iteration
- Use recursion to get the left and right subtrees.

## Codes:
```JavaScript
/**
 * Definition for a binary tree node.
 * function TreeNode(val, left, right) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.left = (left===undefined ? null : left)
 *     this.right = (right===undefined ? null : right)
 * }
 */
/**
 * @param {TreeNode} root
 * @param {number} val
 * @return {TreeNode}
 */
var searchBST = function(root, val) {
    if (!root || root.val === val) {
        return root;
    }
    return searchBST(root.left, val) || searchBST(root.right, val);
};
```

```Python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def searchBST(self, root: Optional[TreeNode], val: int) -> Optional[TreeNode]:
     if not root or root.val == val: return root

     stack = [root]
     while stack:
        curr = stack.pop()
        if curr.val == val: return curr
        if curr.right: stack.append(curr.right)
        if curr.left: stack.append(curr.left)

     return None
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
class Solution {
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) return null;
        
        if (root.val == val) {
            return root;
        } else if (val < root.val){
            return searchBST(root.left, val);
        } else {
            return searchBST(root.right, val);
        }
    }
}
```