# 543. Diameter of Binary Tree - Easy

## Problem Statement:

Given the root of a binary tree, return the length of the diameter of the tree.

The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.

The length of a path between two nodes is represented by the number of edges between them.
 
### Example 1:  
![](https://assets.leetcode.com/uploads/2021/03/06/diamtree.jpg)

Input: root = [1,2,3,4,5]
Output: 3
Explanation: 3 is the length of the path [4,2,1,3] or [5,2,1,3].

![](https://assets.leetcode.com/uploads/2020/09/04/del_node_supp.jpg)

### Example 2:  

Input: root = [1,2]
Output: 1


### Constraints:  
- The number of nodes in the tree is in the range [1, 104].
- -100 <= Node.val <= 100

## Solution Notes: 
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

function diameterOfBinaryTree(root: TreeNode | null): number {
    let res = 0;
    function traversal(node: TreeNode | null): number {
        if (!node) return 0;
        
        const l = traversal(node.left);
        const r = traversal(node.right); 
        res = Math.max(res, l + r);

        return 1 + Math.max(l, r);
    }
    traversal(root);
    return res;
};
```
