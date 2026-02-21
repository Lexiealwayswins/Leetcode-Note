# 111. Minimum Depth of Binary Tree - Easy

## Problem Statement:

Given a binary tree, find its minimum depth.

The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.

Note: A leaf is a node with no children.
 
### Example 1:  
![](https://assets.leetcode.com/uploads/2020/11/26/tmp-tree.jpg)

**Input:** root = [3,9,20,null,null,15,7] 
**Output:** \[[3],[9,20],[15,7]]


### Example 2:  

**Input:** root = \[2,null,3,null,4,null,5,null,6] 
**Output:** 5


### Constraints:  
- The number of nodes in the tree is in the range [0, 105].
- -1000 <= Node.val <= 1000

## Solution Notes:  
- Recursion
- BFS

## Codes:
```Python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def minDepth(self, root: Optional[TreeNode]) -> int:
        if not root: return 0

        l = self.minDepth(root.left)
        r = self.minDepth(root.right)

        if l and not r:
            return 1 + l

        elif not l and r:
            return 1 + r
        else:
            return 1 + min(l, r)
```

```JavaScript
// Solution 1: Recursion
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
 * @return {number}
 */
var minDepth = function(root) {
    if (!root) return 0;

    let l = minDepth(root.left);
    let r = minDepth(root.right);
    if (l && !r) {
        return 1 + l;
    } else if (!l && r) {
        return 1 + r;
    } else {
        return 1 + Math.min(l, r);
    }
};

// Solution 2: 
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
 * @return {number}
 */
var minDepth = function(root) {
    if (!root) return 0;
    res = 0;
    let queue = [root];
    while (queue) {
        count = queue.length;
        while (count > 0) {
            curr = queue.shift();
            curr.left && queue.push(curr.left);
            curr.right && queue.push(curr.right);
            if (!curr.left && !curr.right) return res + 1;
            count--;
        }
        res++;
    }
    return res;
};
```

```Java
// Solution 1: BFS 
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
    public int minDepth(TreeNode root) {
        int res = 0;
        if (root == null) return res;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while(!q.isEmpty()){
            for (int i = q.size(); i > 0; i--){
                TreeNode curr = q.poll();
                if (curr.left != null) q.offer(curr.left);
                if (curr.right != null) q.offer(curr.right);
                if (curr.left == null && curr.right == null){
                    return res + 1;
                }
            }
            res++;
        }
        return res;
    }
}

// Solution 2: Recursion
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
    public int minDepth(TreeNode root) {
        if (root == null) return 0;

        int left = minDepth(root.left);
        int right = minDepth(root.right);

        if (left == 0 && right != 0) {
            return 1 + right;
        } else if (left != 0 && right == 0) {
            return 1 + left;
        } else {
            return 1 + Math.min(left, right);
        }
    }
}
```