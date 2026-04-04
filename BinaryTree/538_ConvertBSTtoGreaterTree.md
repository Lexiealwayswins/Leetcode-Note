# 538. Convert BST to Greater Tree - Medium

## Problem Statement:

Given the root of a Binary Search Tree (BST), convert it to a Greater Tree such that every key of the original BST is changed to the original key plus the sum of all keys greater than the original key in BST.

As a reminder, a binary search tree is a tree that satisfies these constraints:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.


### Example 1:  
![](https://assets.leetcode.com/uploads/2019/05/02/tree.png)

**Input:** root = \[4,1,6,0,2,5,7,null,null,null,3,null,null,null,8]
**Output:** \[30,36,21,36,35,26,15,null,null,null,33,null,null,null,8]

### Example 2:  

**Input:** root = \[0,null,1]
**Output:** \[1,null,1]
 

### Constraints:  
- The number of nodes in the tree is in the range [0, 104].
- -104 <= Node.val <= 104
- All the values in the tree are unique.
- root is guaranteed to be a valid binary search tree.

Note: This question is the same as 1038: https://leetcode.com/problems/binary-search-tree-to-greater-sum-tree/

## Solution Notes:  
- recursion
- reverse inorder traversal

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

function convertBST(root: TreeNode | null): TreeNode | null {
    if (!root) return null;
    let sum = 0;
    function traversal(node: TreeNode | null): void {
        if (!node) return null;
        traversal(node.right);
        sum += node.val;
        node.val = sum;
        traversal(node.left);
    }
    traversal(root);
    return root;
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
        self.count = 0

    def convertBST(self, root: Optional[TreeNode]) -> Optional[TreeNode]:
        if not root: return None

        self.convertBST(root.right)
        self.count += root.val
        root.val = self.count
        self.convertBST(root.left)

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
class Solution {
    int sum;
    public TreeNode convertBST(TreeNode root) {
        sum = 0;
        getTree(root);
        return root;
    }

    public void getTree(TreeNode root){
        if (root == null) return;

        getTree(root.right);

        sum += root.val;
        root.val = sum;

        getTree(root.left);
    }
}
```