# 617. Merge Two Binary Trees - Easy

## Problem Statement:

You are given two binary trees root1 and root2.

Imagine that when you put one of them to cover the other, some nodes of the two trees are overlapped while the others are not. You need to merge the two trees into a new binary tree. The merge rule is that if two nodes overlap, then sum node values up as the new value of the merged node. Otherwise, the NOT null node will be used as the node of the new tree.

Return the merged tree.

Note: The merging process must start from the root nodes of both trees.


### Example 1:  
![](https://assets.leetcode.com/uploads/2021/02/05/merge.jpg)

**Input:** root1 = [1,3,2,5], root2 = [2,1,3,null,4,null,7]
**Output:** [3,4,5,5,4,null,7]

### Example 2:  

**Input:** root1 = [1], root2 = [1,2]
**Output:** [2,2]
 

### Constraints:  
- The number of nodes in both trees is in the range [0, 2000].
- -104 <= Node.val <= 104

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
 * @param {TreeNode} root1
 * @param {TreeNode} root2
 * @return {TreeNode}
 */
var mergeTrees = function(root1, root2) {
    if (!root1) return root2;
    if (!root2) return root1;

    let queue = [[root1, root2]];
    while (queue.length) {
        let curr = queue.shift();
        let curr1 = curr[0];
        let curr2 = curr[1];
        curr1.val += curr2.val;
        if (curr1.left && curr2.left) {
            queue.push([curr1.left, curr2.left]);
        } else if (!curr1.left) {
            curr1.left = curr2.left;
        }

        if (curr1.right && curr2.right) {
            queue.push([curr1.right, curr2.right]);
        } else if (!curr1.right) {
            curr1.right = curr2.right;
        }
    }
    return root1;
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
    def mergeTrees(self, root1: Optional[TreeNode], root2: Optional[TreeNode]) -> Optional[TreeNode]:
        if not root1: return root2
        if not root2: return root1
        root = TreeNode()
        root.val += root1.val + root2.val
        root.left = self.mergeTrees(root1.left, root2.left)
        root.right = self.mergeTrees(root1.right, root2.right)

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
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null) return root2;
        if (root2 == null) return root1;

        root1.val += root2.val;
        root1.left = mergeTrees(root1.left, root2.left);
        root1.right = mergeTrees(root1.right, root2.right);

        return root1;
    }
}
```