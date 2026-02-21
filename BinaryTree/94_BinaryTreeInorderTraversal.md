# 94. Binary Tree Inorder Traversal - Easy

## Problem Statement:

Given the root of a binary tree, return the inorder traversal of its nodes' values.
 
### Example 1:  

**Input:** root = [1,null,2,3]  
**Output:** [1,3,2]
**Explanation:** 
![](https://assets.leetcode.com/uploads/2024/08/29/screenshot-2024-08-29-202743.png)

### Example 2:  

**Input:** root = [1,2,3,4,5,null,8,null,null,6,7,9]  
**Output:** [4,2,6,5,7,1,3,9,8]
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
    def inorderTraversal(self, root: Optional[TreeNode]) -> List[int]:
        res = []

        def dfs(node):
            if not node: return

            dfs(node.left)
            res.append(node.val)
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
    def inorderTraversal(self, root: Optional[TreeNode]) -> List[int]:
        if not root: return []
        return self.inorderTraversal(root.left) + [root.val] + self.inorderTraversal(root.right)

# Solution 3: DFS Iteration - reverse of preorder
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def inorderTraversal(self, root: Optional[TreeNode]) -> List[int]:
        if not root: return []
        res = []
        stack = []
        curr = root
        while curr or len(stack):
            if curr:
                stack.append(curr)
                curr = curr.left
            else:
                curr = stack.pop()
                res.append(curr.val)
                curr = curr.right
        
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
var inorderTraversal = function(root) {
    let res = []
    const dfs = (node) => {
        if (!node) return

        dfs(node.left);
        res.push(node.val);
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
var inorderTraversal = function(root) {
    return root ? [
        ...inorderTraversal(root.left),
        root.val,
        ...inorderTraversal(root.right),
    ] : [];
};

// Solution 3: DFS Iteration - reverse of preorder
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
var inorderTraversal = function(root) {
    if (!root) return [];
    let res = [];
    let stack = [];
    curr = root;
    while (stack.length || curr) {
        if (curr) {
            stack.push(curr);
            curr = curr.left;
        } else {
            curr = stack.pop();
            res.push(curr.val);
            curr = curr.right;
        }
    }
    return res;
};

```

```Java
// Solution 1: DFS Recursion
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
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inorder(root, list);
        return list;
    }

    public void inorder(TreeNode root, List<Integer> list){
        if (root == null) return;

        inorder(root.left, list);
        list.add(root.val);
        inorder(root.right, list);
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
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        while(curr != null || !stack.isEmpty()){
            if (curr != null){
                stack.push(curr);
                curr = curr.left;
            } else{
                curr = stack.pop();
                res.add(curr.val);
                curr = curr.right;
            }
        }
        return res;
    }
}
```Java