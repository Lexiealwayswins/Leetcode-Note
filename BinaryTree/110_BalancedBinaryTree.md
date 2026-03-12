# 110. Balanced Binary Tree - Easy

## Problem Statement:

Given a binary tree, determine if it is height-balanced.  

### Example 1:  
![](https://assets.leetcode.com/uploads/2020/10/06/balance_1.jpg)  

**Input:** root = [3,9,20,null,null,15,7]  
**Output:** true  

### Example 2:  
![](https://assets.leetcode.com/uploads/2020/10/06/balance_2.jpg)  

**Input:** root = [1,2,2,3,3,null,null,4,4]  
**Output:** false  

### Example 3:  

**Input:** root = []  
**Output:** true  
 
## Constraints:
- The number of nodes in the tree is in the range [0, 5000].
- -104 <= Node.val <= 104

## Solution Notes:  
- Use recursion
- The balance tree is a tree which the height difference of its left tree and its right tree is less than 2.

## Codes:
```Python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def isBalanced(self, root: Optional[TreeNode]) -> bool:
        if not root: return True

        def getLevel(node):
            if not node: return 0

            l, r = getLevel(node.left), getLevel(node.right)
            if l == -1 or r == -1 or abs(l - r) > 1:
                return -1 # Use -1 to mark all the non-balanced branches
            return 1 + max(l, r)
        
        return True if getLevel(root) != -1 else False
```

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
 * @return {boolean}
 */
var isBalanced = function(root) {
    if (!root) return true;

    const getLevel = (node) => {
        if (!node) return 0;
        const l = getLevel(node.left), r = getLevel(node.right);
        if (l === -1 || r === -1 || Math.abs(l - r) > 1) {
            return -1;
        }
        return 1 + Math.max(l, r);
    }

    return getLevel(root) === -1 ? false : true;
};
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
    public boolean isBalanced(TreeNode root) {
        return checkBalance(root) != -1;
    }
    public int checkBalance(TreeNode root){
        if (root == null) return 0;

        int l = checkBalance(root.left);
        int r = checkBalance(root.right);

        if (l == -1 || r == -1) return -1; 
        if (l - r > 1 || r - l > 1) return -1;

        return Math.max(l, r) + 1;
    }
}
```Java