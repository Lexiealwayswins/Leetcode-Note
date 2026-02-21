# 144. Binary Tree Preorder Traversal - Easy

## Problem Statement:

Given the root of a binary tree, return the preorder traversal of its nodes' values.
 
### Example 1:  

**Input:** root = [1,null,2,3]  
**Output:** [1,2,3]
**Explanation:** 
![](https://assets.leetcode.com/uploads/2024/08/29/screenshot-2024-08-29-202743.png)

### Example 2:  

**Input:** root = [1,2,3,4,5,null,8,null,null,6,7,9]  
**Output:** [1,2,4,5,6,7,3,8,9]  
**Explanation:** 
![](https://assets.leetcode.com/uploads/2024/08/29/tree_2.png)

### Example 3:

**Input:** root = []
**Output:** []

### Example 4:

**Input:** root = [1]
**Output:** [1]

### Constraints:  
- The number of nodes in the tree is in the range [0, 100].
- -100 <= Node.val <= 100 

## Solution Notes:  
- DFS Recursion
- DFS Iteration

## Codes:
```Python
# Solution 1: DFS Recursion
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def preorderTraversal(self, root: Optional[TreeNode]) -> List[int]:
        res = []
        def dfs(node):
            if not node: return

            res.append(node.val)
            dfs(node.left)
            dfs(node.right)

        dfs(root)
        return res

# Solution 2: DFS Recursion - Neat Version
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def preorderTraversal(self, root: Optional[TreeNode]) -> List[int]:
        if not root: return []
        return [root.val] + self.preorderTraversal(root.left) + self.preorderTraversal(root.right)

# Solution 3: DFS Iteration
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def preorderTraversal(self, root: Optional[TreeNode]) -> List[int]:
        if not root: return []
        stack = [root]
        res = []
        while stack:
            curr = stack.pop()
            res.append(curr.val)
            if curr.right:
                stack.append(curr.right)
            if curr.left:
                stack.append(curr.left)
        return res
```

```JavaScript
// Solution 1: DFS Recursion
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
 * @return {number[]}
 */
var preorderTraversal = function(root) {
    let res = []
    const dfs = (node) => {
        if (!node) return

        res.push(node.val);
        dfs(node.left);
        dfs(node.right);
    }
    dfs(root);
    return res; 
};


// Solution 2: DFS Recursion - Neat Version
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
 * @return {number[]}
 */
var preorderTraversal = function(root) {
    return root ? [
        root.val,
        ...preorderTraversal(root.left),
        ...preorderTraversal(root.right)
    ] : [];
};

// Solution 3: DFS Iteration
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
 * @return {number[]}
 */
var preorderTraversal = function(root) {
    if (!root) return [];
    let stack = [root];
    let res = [];
    let curr;
    while (stack.length) {
        curr = stack.pop();
        res.push(curr.val);
        curr.right && stack.push(curr.right);
        curr.left && stack.push(curr.left);
    }
    return res;
};
```

```Java
//  Solution 1: DFS Recursion
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
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        preorder(root, list);
        return list;
    }

    public void preorder(TreeNode root, List<Integer> list){
        if (root == null) return;
        list.add(root.val);
        preorder(root.left, list);
        preorder(root.right, list);
    }
}

// Solution 2: DFS Iteration
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
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            TreeNode node = stack.pop();
            res.add(node.val);
            if (node.right != null){
                stack.push(node.right);
            }
            if (node.left != null){
                stack.push(node.left);
            }
        }
        return res;
    } 
}
```Java