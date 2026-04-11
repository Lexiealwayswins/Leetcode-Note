# 968. Binary Tree Cameras - Hard

## Problem Statement:  

You are given the root of a binary tree. We install cameras on the tree nodes where each camera at a node can monitor its parent, itself, and its immediate children.

Return the minimum number of cameras needed to monitor all nodes of the tree.


### Example 1:  
![](https://assets.leetcode.com/uploads/2018/12/29/bst_cameras_01.png)

**Input:**: root = [0,0,null,0,0]
**Output:**: 1
**Explanation:**: One camera is enough to monitor all nodes if placed as shown.

### Example 2:  
![](https://assets.leetcode.com/uploads/2018/12/29/bst_cameras_02.png)

**Input:**: root = [0,0,null,0,null,0,null,null,0]
**Output:**: 2
**Explanation:**: At least two cameras are needed to monitor all nodes of the tree. The above image shows one of the valid configurations of camera placement.
 

### Constraints:

- The number of nodes in the tree is in the range [1, 1000].
- Node.val == 0

## Solution Notes:  
- Greedy Algorithm: traveral from the buttom, and record different status

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

function minCameraCover(root: TreeNode | null): number {
    if (!root) return 0;
    if (!root.left && !root.right) return 1;

    let count = 0;

    // camera: 1, covered: 2, not covered: 0
    function traversal (node: TreeNode | null): number {
        if (!node) return 2;

        const l = traversal(node.left);
        const r = traversal(node.right);

        if (l === 0 || r === 0) {
            count++;
            return 1;
        }

        if (l === 1 || r === 1) return 2;
        if (l === 2 && r === 2) return 0;

        return -1;
    }

    // if root node is not covered, it should add a camera
    if (traversal(root) === 0) {
        count++;
    }

    return count;
};
```

