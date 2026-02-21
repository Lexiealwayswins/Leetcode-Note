# 101. Symmetric Tree - Easy

## Problem Statement:

Given the root of a binary tree, check whether it is a mirror of itself (i.e., symmetric around its center).
 
### Example 1:  
![](https://assets.leetcode.com/uploads/2021/02/19/symtree1.jpg)

**Input:** root = \[1,2,2,3,4,4,3]
**Output:** true


### Example 2:  
![](https://assets.leetcode.com/uploads/2021/02/19/symtree2.jpg)

**Input:** root = \[1,2,2,null,3,null,3]
**Output:** false


### Constraints:  
- The number of nodes in the tree is in the range [1, 1000].
- -100 <= Node.val <= 100

## Solution Notes:  
- Recursion
- Iteration

## Codes:
```Python
# Solution 1: Recursion
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def isSymmetric(self, root: Optional[TreeNode]) -> bool:
        if not root: return True
        return self.compare(root.left, root.right)

    def compare (self, l, r):
        if not l and not r: return True
        if not l or not r or l.val != r.val: return False
        return self.compare(l.left, r.right) and self.compare(l.right, r.left)

# Solution 2: Iteration
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def isSymmetric(self, root: Optional[TreeNode]) -> bool:
        if not root: return True
        stack = [root.left, root.right]

        while stack:
            l = stack.pop()
            r = stack.pop()
            if not l and not r: continue
            if not l or not r or l.val != r.val: return False
            stack.append(l.left)
            stack.append(r.right)
            stack.append(l.right)
            stack.append(r.left)
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
 * @param {TreeNode} root
 * @return {boolean}
 */
var isSymmetric = function(root) {
    if (!root) return true;

    const compare = (l, r) => {
        if (!l && !r) return true;
        if (!l || !r || l.val != r.val) return false;
        return compare(l.left, r.right) && compare(l.right, r.left);
    }

    return compare(root.left, root.right);
};
```

```Java
// Solution 1: Recursion
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
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return false;
        return compare(root.left, root.right);
    }

    public boolean compare(TreeNode l, TreeNode r){
        if (l == null && r == null) return true;
        if (l ==  null || r == null || l.val != r.val) return false;
        boolean compareOut = compare(l.left, r.right);
        boolean compareIn = compare(l.right, r.left);

        return compareOut && compareIn;
    }
}

// Solution 2: BFS
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
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return false;
        Deque<TreeNode> dq = new LinkedList<>();
        dq.offerFirst(root.left);
        dq.offerLast(root.right);
        while(!dq.isEmpty()){
            TreeNode l = dq.pollFirst();
            TreeNode r = dq.pollLast();

            if (l == null && r == null) continue;
            if (l == null || r == null || l.val != r.val) return false;

            dq.offerFirst(l.right);
            dq.offerFirst(l.left);
            dq.offerLast(r.left);
            dq.offerLast(r.right);
        }
        return true;
    }
}
```