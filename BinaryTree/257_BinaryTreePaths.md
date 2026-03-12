# 257. Binary Tree Paths - Easy

## Problem Statement:

Given the root of a binary tree, return all root-to-leaf paths in any order.

A leaf is a node with no children.


### Example 1:  

![](https://assets.leetcode.com/uploads/2021/03/12/paths-tree.jpg)  

**Input:** root = [1,2,3,null,5]  
**Output:** ["1->2->5","1->3"]  

### Example 2:  

**Input:** root = [1]  
**Output:** ["1"]  
 
### Constraints:  

The number of nodes in the tree is in the range [1, 100].  
- -100 <= Node.val <= 100  

## Solution Notes:  
### 1. Use recursion
- The end case should be the leaf and record the path
- Don't forget to go back to the previous node in path when returning to the previous level in recursion

### 2. Use Stack
- Use a object stack to store the TreeNode and the path String in the meantime

## Codes:

```Python
# SOlution 1: Recursion
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def binaryTreePaths(self, root: Optional[TreeNode]) -> List[str]:
        if not root: return []
        res = []
        path = []
        self.traversal(root, res, path)
        return res

    def traversal(self, node: Optional[TreeNode], res: List[string], path: List[int]) -> None:
        if not node: return
        path.append(node.val)
        if not node.left and not node.right:
            res.append("->".join(map(str, path)))
        
        if node.left: 
            self.traversal(node.left, res, path)
            path.pop()
        if node.right:
            self.traversal(node.right, res, path)
            path.pop()
        
# Solution 2: Iteration
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def binaryTreePaths(self, root: Optional[TreeNode]) -> List[str]:
        if not root: return []
        res = []
        stack = [root]
        path_st = [str(root.val)]
        while stack:
            curr = stack.pop()
            path = path_st.pop()
            if not curr.left and not curr.right:
                res.append(path)
            if curr.right:
                stack.append(curr.right)
                path_st.append(path + "->" + str(curr.right.val))
            if curr.left:
                stack.append(curr.left)
                path_st.append(path + "->" + str(curr.left.val))
        return res

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
 * @return {string[]}
 */
var binaryTreePaths = function(root) {
    if (!root) return [];
    let res = [];

    const traversal = (node, path) => {
        if (!node) return;
        path += node.val;
        if (!node.left && !node.right) {
            res.push(path);
        }
        if (node.left) {
            traversal(node.left, path + "->");
        }
        if (node.right) {
            traversal(node.right, path + "->");
        }
    }
    traversal(root, "");
    return res;
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

// Recursion
class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        if (root == null) return res;
        List<Integer> path = new ArrayList<>();
        getPath(res, path, root);
        return res;
    }

    public void getPath(List<String> res, List<Integer> path, TreeNode root){
        path.add(root.val);
        if (root != null && root.left == null && root.right == null) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < path.size() - 1; i++){
                sb.append(path.get(i) + "->");
            }
            sb.append(path.get(path.size() - 1));
            res.add(sb.toString());
            return;
        }
        if (root.left != null){
            getPath(res, path, root.left);
            path.remove(path.size() - 1);
        }
        
        if (root.right != null) {
            getPath(res, path, root.right);
            path.remove(path.size() - 1);
        }
    }
}

// Stack
class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        if (root == null) return res;
        Stack<Object> stack = new Stack<>();
        stack.push(root);
        stack.push(root.val + "");
        while(!stack.isEmpty()){
            String path = (String) stack.pop();
            TreeNode curr =  (TreeNode) stack.pop();
            
            if (curr.left == null && curr.right == null) {
                res.add(path);
            }
            if (curr.left != null) {
                stack.push(curr.left);
                stack.push(path + "->" + curr.left.val);
            }
            if (curr.right != null) {
                stack.push(curr.right);
                stack.push(path + "->" + curr.right.val);
            }
        }
        return res;
    }
}
```Java