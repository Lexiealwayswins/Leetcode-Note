# 98. Validate Binary Search Tree - Medium

## Problem Statement:

Given the root of a binary tree, determine if it is a valid binary search tree (BST).  

A valid BST is defined as follows:  

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.  
 

### Example 1:  
![](https://assets.leetcode.com/uploads/2020/12/01/tree1.jpg)

**Input:** root = [2,1,3]  
**Output:** true  

### Example 2:  
![](https://assets.leetcode.com/uploads/2020/12/01/tree2.jpg)

**Input:** root = [5,1,4,null,null,3,6]  
**Output:** false  
**Explanation:** The root node's value is 5 but its right child's value is 4.  
 

### Constraints:  
- The number of nodes in the tree is in the range [1, 104].  
- -231 <= Node.val <= 231 - 1  

## Solution Notes:  
- Inorder DFS Traversal
- Recursion

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

function isValidBST(root: TreeNode | null): boolean {
    if (!root) return true;
    let curr: TreeNode | null = root;
    let pre: TreeNode | null = null;
    let stack: TreeNode[] = [];
    while (curr || stack.length) {
        if (curr) {
            stack.push(curr);
            curr = curr.left;
        } else {
            curr = stack.pop();
            if (pre && pre.val >= curr.val) return false;
            pre = curr;
            curr = curr.right;
        }
    }
    return true;
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
    def __init__(self):
        self.pre = None

    def isValidBST(self, root: Optional[TreeNode]) -> bool:
        if not root: return True
        l = self.isValidBST(root.left)
        if self.pre != None and self.pre.val >= root.val:
            return False
        self.pre = root
        r = self.isValidBST(root.right)
        return l and r
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
    TreeNode prev;
    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        boolean l = isValidBST(root.left);
        
        if (prev != null && prev.val >= root.val) return false;
        prev = root;

        boolean r = isValidBST(root.right);

        return l && r;
    }
}
```