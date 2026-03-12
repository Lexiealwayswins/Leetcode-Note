# 530. Minimum Absolute Difference in BST - Easy

## Problem Statement:
Given the root of a Binary Search Tree (BST), return the minimum absolute difference between the values of any two different nodes in the tree.
 

### Example 1:  
![](https://assets.leetcode.com/uploads/2021/02/05/bst1.jpg)

**Input:** root = [4,2,6,1,3] 
**Output:** 1  

### Example 2:  
![](https://assets.leetcode.com/uploads/2021/02/05/bst2.jpg)

**Input:** root = [1,0,48,null,null,12,49]  
**Output:** 1  
 

### Constraints:  
The number of nodes in the tree is in the range [2, 104].
0 <= Node.val <= 105

Note: This question is the same as 783: https://leetcode.com/problems/minimum-distance-between-bst-nodes/

## Solution Notes:  
- Inorder DFS Traversal
- Recursion

## Codes:
```Python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def getMinimumDifference(self, root: Optional[TreeNode]) -> int:
        if not root: return 0
        res = float('inf')
        pre = None
        curr = root
        stack = []
        while curr or stack:
            if curr:
                stack.append(curr)
                curr = curr.left
            else:
                curr = stack.pop()
                if pre: res = min(res, abs(pre.val - curr.val))
                pre = curr
                curr = curr.right
        return res
```

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

function getMinimumDifference(root: TreeNode | null): number {
    if (!root) return 0;
    let pre: TreeNode | null = null;
    let min: number = Infinity;
    const getMin = (node: TreeNode | null): null => {
        if (!node) return;
        getMin(node.left);
        if (pre) min = Math.min(min, Math.abs(pre.val - node.val));
        pre = node;
        getMin(node.right);
    }
    getMin(root);
    return min;
};
```

```Python
```

```Java
// Recursion
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
    int min = Integer.MAX_VALUE;
    TreeNode pre;
    public int getMinimumDifference(TreeNode root) {
        if (root == null) return min;
        getMin(root);
        return min;
    }

    public void getMin(TreeNode root){
        if (root == null) return;

        getMin(root.left);
        if (pre != null) min = Math.min(min, root.val - pre.val);
        pre = root;

        getMin(root.right);
    }
}

// DFS Iteration
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
    TreeNode pre;
    public int getMinimumDifference(TreeNode root) {
        if (root == null) return 0;
        int res = Integer.MAX_VALUE;
        Stack<TreeNode> s = new Stack<>();
        TreeNode curr = root;

        while(curr != null || !s.isEmpty()){
            if (curr != null){
                s.push(curr);
                curr = curr.left;
            } else {
                curr = s.pop();
                if (pre != null) res = Math.min(res, curr.val - pre.val);
                pre = curr;
                curr = curr.right;
            }
        }
        return res;
    }
}
```