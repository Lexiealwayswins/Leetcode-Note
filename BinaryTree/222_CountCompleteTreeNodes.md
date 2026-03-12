# 222. Count Complete Tree Nodes - Easy

## Problem Statement:

Given the root of a complete binary tree, return the number of the nodes in the tree.

According to Wikipedia, every level, except possibly the last, is completely filled in a complete binary tree, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.

Design an algorithm that runs in less than O(n) time complexity.
 
### Example 1:  
![](https://assets.leetcode.com/uploads/2021/01/14/complete.jpg)

**Input:** root = \[1,2,3,4,5,6]
**Output:** 6


### Example 2:  
**Input:** root = \[]
**Output:** 0

### Example 3:  
**Input:** root = \[1]
**Output:** 1


### Constraints:  
- The number of nodes in the tree is in the range [0, 5 * 104]
- 0 <= Node.val <= 5 * 104

## Solution Notes:  
- Recursion
- Iteration


## Codes:
```Python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def countNodes(self, root: Optional[TreeNode]) -> int:
        if not root: return 0
        l = self.countNodes(root.left)
        r = self.countNodes(root.right)
        return 1 + l + r
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
 * @return {number}
 */
var countNodes = function(root) {
    if (!root) return 0;
    let queue = [root];
    let res = 0;
    while (queue.length) {
        let count = queue.length;
        while (count > 0) {
            let curr = queue.shift();
            res++;
            curr.left && queue.push(curr.left);
            curr.right && queue.push(curr.right);
            count--;
        }
    }
    return res;
};
```

```Java
// Recursion:
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
    public int countNodes(TreeNode root) {
        return count(root);
    }

    public int count(TreeNode root){
        if (root == null) return 0;
        
        int l = count(root.left);
        int r = count(root.right);

        return 1 + l + r;
    }
}

// Iteration:
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
    public int countNodes(TreeNode root) {
        int count = 0;
        if (root == null) return count;
        Stack<TreeNode> s = new Stack<>();
        s.push(root);
        while(!s.isEmpty()){
            TreeNode curr = s.pop();
            count++;
            if (curr.left != null) s.push(curr.left);
            if (curr.right != null) s.push(curr.right);
        }
        return count;
    }
}
```