# 230. Kth Smallest Element in a BST - Medium

## Problem Statement:

Given the root of a binary tree, invert the tree, and return its root.
 
### Example 1:  
![](https://assets.leetcode.com/uploads/2021/01/28/kthtree1.jpg)

Input: root = [3,1,4,null,2], k = 1
Output: 1


### Example 2:  
![](https://assets.leetcode.com/uploads/2021/01/28/kthtree2.jpg)

Input: root = [5,3,6,2,4,null,null,1], k = 3
Output: 3


### Constraints:  
- The number of nodes in the tree is n.
- 1 <= k <= n <= 104
- 0 <= Node.val <= 104


Follow up: If the BST is modified often (i.e., we can do insert and delete operations) and you need to find the kth smallest frequently, how would you optimize?

Answer： 
我会把 BST 改造成 Order Statistic Tree，在每个节点维护子树大小。
查询 kth smallest 时根据左子树大小决定往哪走，复杂度 O(log n)。
同时用 AVL 或 Red‑Black Tree 保证树的平衡，使 insert/delete 也保持 O(log n)。这是频繁修改 + 高频查询的最优方案。


## Solution Notes:  
- In order traversal

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

function kthSmallest(root: TreeNode | null, k: number): number {
    let res: number = 0;
    let count = 0;
    function traversal(node: TreeNode | null, k: number) {
        if (!node) return;
        traversal(node.left, k);
        count++;
        if (count === k) res = node.val;
        traversal(node.right, k);
    }
    traversal(root, k);
    return res;
};
```