# 100. Same Tree - Easy

## Problem Statement:

Given the root of a binary tree, check whether it is a mirror of itself (i.e., symmetric around its center).
 
### Example 1:  
![](https://assets.leetcode.com/uploads/2020/12/20/ex1.jpg)

**Input:** p =  \[1,2,3], q = \[1,2,3]
**Output:** true


### Example 2:  
![](https://assets.leetcode.com/uploads/2020/12/20/ex2.jpg)

**Input:** root = p = \[1,2], q = \[1,null,2]
**Output:** false


### Example 3:  
![](https://assets.leetcode.com/uploads/2020/12/20/ex3.jpg)

**Input:** p = \[1,2,1], q = \[1,1,2]
**Output:** false


### Constraints:  
- The number of nodes in both trees is in the range [0, 100].
- -104 <= Node.val <= 104

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
    def isSameTree(self, p: Optional[TreeNode], q: Optional[TreeNode]) -> bool:
        if not p and not q: return True
        if not p or not q or p.val != q.val: return False

        return self.isSameTree(p.left, q.left) and self.isSameTree(p.right, q.right)

# Solution 2: Iteration
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def isSameTree(self, p: Optional[TreeNode], q: Optional[TreeNode]) -> bool:
        if not p and not q: return True
        if not p or not q or p.val != q.val: return False

        stack = [p, q]
        while stack:
            r = stack.pop()
            l = stack.pop()
            if not l and not r: continue
            if not l or not r or l.val != r.val: return False
            stack.append(l.left)
            stack.append(r.left)
            stack.append(l.right)
            stack.append(r.right)
        return True
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
 * @param {TreeNode} p
 * @param {TreeNode} q
 * @return {boolean}
 */
var isSameTree = function(p, q) {
    if (!p && !q) return true;
    if (!p || !q || p.val != q.val) return false;
    return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
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
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;

        if (p.val != q.val) return false;

        boolean l = isSameTree(p.left, q.left);
        boolean r = isSameTree(p.right, q.right);

        return l && r;
    }
}

// Solution 2: Iteration
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
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;

        Stack<TreeNode> s = new Stack<>();
        s.push(p);
        s.push(q);
        while(!s.isEmpty()){
            TreeNode l = s.pop();
            TreeNode r = s.pop();
            if (r.val != l.val) return false;
            if (r.left == null && l.left == null && r.right == null && l.right == null) continue;
            if ((r.left == null && l.left != null) || (r.left != null && l.left == null) || (r.right != null && l.right == null) || (r.right == null && l.right != null)) return false;
            if (l.left != null) s.push(l.left);
            if (r.left != null) s.push(r.left);
            if (l.right != null) s.push(l.right);
            if (r.right != null) s.push(r.right);
        }
        return true;
    }
}
```