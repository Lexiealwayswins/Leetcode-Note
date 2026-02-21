# 572. Subtree of Another Tree - Easy

## Problem Statement:

Given the roots of two binary trees root and subRoot, return true if there is a subtree of root with the same structure and node values of subRoot and false otherwise.

A subtree of a binary tree tree is a tree that consists of a node in tree and all of this node's descendants. The tree tree could also be considered as a subtree of itself.

### Example 1:  
![](https://assets.leetcode.com/uploads/2021/04/28/subtree1-tree.jpg)

**Input:** root = [3,4,5,1,2], subRoot = [4,1,2]  
**Output:** true  

### Example 2:  
![](https://assets.leetcode.com/uploads/2021/04/28/subtree2-tree.jpg)

**Input:** root = [3,4,5,1,2,null,null,null,null,0], subRoot = [4,1,2]  
**Output:** false  
 
## Constraints:

- The number of nodes in the root tree is in the range [1, 2000].
- The number of nodes in the subRoot tree is in the range [1, 1000].
- -104 <= root.val <= 104
- -104 <= subRoot.val <= 104

## Solution Notes:  
- Use two recrusion processes
- One recursion is to check the start of the subtree
- Another recursion is to check whether the subtree is identical to the subroot

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
 * @param {TreeNode} root
 * @param {TreeNode} subRoot
 * @return {boolean}
 */
var isSubtree = function(root, subRoot) {
    if (!root && !subRoot) return true;
    if (!root) return false;

    const isSame = (l, r) => {
        if (!l && !r) return true;
        if (!l || !r || l.val != r.val) return false;
        return isSame(l.left, r.left) && isSame(l.right, r.right);
    }   

    const check = (l, r) => {
        if (!l) return false;
        if (l.val == r.val) {
            if (isSame(l, r)) return true;
        }
        return check(l.left, r) || check(l.right, r);
    }

    return check(root, subRoot);
};

// Or shorter codes
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
 * @param {TreeNode} subRoot
 * @return {boolean}
 */
var isSubtree = function(root, subRoot) {
    if (!root && !subRoot) return true;
    if (!root) return false;

    const isSame = (l, r) => {
        if (!l && !r) return true;
        if (!l || !r || l.val != r.val) return false;
        return isSame(l.left, r.left) && isSame(l.right, r.right);
    }   

    if (isSame(root, subRoot)) return true;
    return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
};
```

```Python
# Iteration
class Solution:
    def isSameTree(self, a, b):
        stack = [(a, b)]
        while stack:
            x, y = stack.pop()
            if not x and not y:
                continue
            if not x or not y or x.val != y.val:
                return False
            stack.append((x.left, y.left))
            stack.append((x.right, y.right))
        return True

    def isSubtree(self, root, subRoot):
        if not subRoot:
            return True
        if not root:
            return False

        stack = [root]
        while stack:
            node = stack.pop()
            if node.val == subRoot.val:
                if self.isSameTree(node, subRoot):
                    return True
            if node.left:
                stack.append(node.left)
            if node.right:
                stack.append(node.right)

        return False

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
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        return check(root, subRoot);
    }

    public boolean check(TreeNode root, TreeNode subRoot) {
        if (root == null) return false;

        if (root.val == subRoot.val) {
            if (isSame(root, subRoot)) return true;
        }

        return check(root.left, subRoot) || check(root.right, subRoot);
    }

    public boolean isSame(TreeNode root, TreeNode subRoot){
        if (root == null && subRoot == null) return true;
        if (root == null || subRoot == null || root.val != subRoot.val) return false;

        if (!isSame(root.left, subRoot.left)) return false;
        if (!isSame(root.right, subRoot.right)) return false;

        return true;
    }
}

```Java