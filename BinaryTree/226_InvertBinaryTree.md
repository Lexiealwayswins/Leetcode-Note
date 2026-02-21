# 226. Invert Binary Tree - Easy

## Problem Statement:

Given the root of a binary tree, invert the tree, and return its root.
 
### Example 1:  
![](https://assets.leetcode.com/uploads/2021/03/14/invert1-tree.jpg)

**Input:** root = \[4,2,7,1,3,6,9]
**Output:** \[4,7,2,9,6,3,1]


### Example 2:  
![](https://assets.leetcode.com/uploads/2021/03/14/invert2-tree.jpg)

**Input:** root = \[2,1,3]
**Output:** \[2,3,1]

### Example 3:  

**Input:** root = \[]
**Output:** \[]


### Constraints:  
- The number of nodes in the tree is in the range [0, 100].
- -100 <= Node.val <= 100

## Solution Notes:  
- Recursion
- BFS
- DFS

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
    def invertTree(self, root: Optional[TreeNode]) -> Optional[TreeNode]:
        if not root: return root
        
        root.left, root.right = root.right, root.left
        self.invertTree(root.left)
        self.invertTree(root.right)

        return root

# Solution 2: BFS
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def invertTree(self, root: Optional[TreeNode]) -> Optional[TreeNode]:
        if not root: return root
        deque = collections.deque([root])
        while deque:
            for _ in range(len(deque)):
                curr = deque.popleft()
                curr.left, curr.right = curr.right, curr.left
                curr.left and deque.append(curr.left)
                curr.right and deque.append(curr.right)
        return root

# Solution 3: Preorder DFS
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def invertTree(self, root: Optional[TreeNode]) -> Optional[TreeNode]:
        if not root: return root
        stack = [root]
        while stack:
            curr = stack.pop()
            curr.left, curr.right = curr.right, curr.left
            curr.right and stack.append(curr.right)
            curr.left and stack.append(curr.left)
        return root

# Solution 4: Postorder DFS
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def invertTree(self, root: Optional[TreeNode]) -> Optional[TreeNode]:
        if not root: return root
        stack = [root]
        while stack:
            curr = stack.pop()
            curr.left, curr.right = curr.right, curr.left
            curr.left and stack.append(curr.left)
            curr.right and stack.append(curr.right)

        return root
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
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;

        TreeNode tmp = root.right;
        root.right = invertTree(root.left);
        root.left = invertTree(tmp);

        return root;
    }
}

// Solution 2: postorder DFS 
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
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        Stack<TreeNode> s = new Stack<>();
        s.push(root);
        while(!s.isEmpty()){
            for (int i = 0; i < s.size(); i++){
                TreeNode curr = s.pop();
                swap(curr);
                if (curr.left != null) s.push(curr.left);
                if (curr.right != null) s.push(curr.right);
            }
        }
        return root;
    }

    public void swap(TreeNode node){
        TreeNode tmp = node.right;
        node.right = node.left;
        node.left = tmp;
    }
}

// Solution 3: preorder DFS 
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
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        Stack<TreeNode> s = new Stack<>();
        s.push(root);
        while(!s.isEmpty()){
            TreeNode curr = s.pop();
            swap(curr);
            if (curr.right != null) s.push(curr.right);
            if (curr.left != null) s.push(curr.left);
        }
        return root;
    }

    public void swap(TreeNode node){
        TreeNode tmp = node.right;
        node.right = node.left;
        node.left = tmp;
    }
}
```