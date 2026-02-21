# 102. Binary Tree Level Order Traversal - Medium

## Problem Statement:

Given the root of a binary tree, return the level order traversal of its nodes' values. (i.e., from left to right, level by level).
 
### Example 1:  
![](https://assets.leetcode.com/uploads/2021/02/19/tree1.jpg)

**Input:** root = [3,9,20,null,null,15,7] 
**Output:** \[[3],[9,20],[15,7]]


### Example 2:  

**Input:** root = [1]  
**Output:** \[[1]]  

### Example 3:

**Input:** root = []
**Output:** []

### Constraints:  
- The number of nodes in the tree is in the range [0, 2000].
- 1000 <= Node.val <= 1000

## Solution Notes:  
- BFS

## Codes:
```Python
# Solution 1: BFS Iteration
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def levelOrder(self, root: Optional[TreeNode]) -> List[List[int]]:
        if not root: return []
        res = []
        deque = collections.deque([root])
        while deque:
            level = []
            for _ in range(len(deque)):
                curr = deque.popleft()
                level.append(curr.val)
                curr.left and deque.append(curr.left)
                curr.right and deque.append(curr.right)
            res.append(level)
        return res

# Solution 2: BFS Recursion
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def levelOrder(self, root: Optional[TreeNode]) -> List[List[int]]:
        if not root: return []
        res = []
        
        def BFS (node, level):
            if not node: return

            if level == len(res):
                res.append([])

            res[level].append(node.val)
            BFS(node.left, level + 1)
            BFS(node.right, level + 1)

        BFS(root, 0)

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
 * @return {number[][]}
 */
var levelOrder = function(root) {
    if (!root) return [];
    let res = [];
    let queue = [root];
    while (queue.length) {
        let level = [];
        let count = queue.length;
        while (count > 0) {
            let curr = queue.shift();
            level.push(curr.val);
            curr.left && queue.push(curr.left);
            curr.right && queue.push(curr.right);
            count--;
        }
        res.push(level);
    }
    return res;
};
```

```Java
// Solution 1: BFS Iteration
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
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (root == null) return res;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);

        while(!queue.isEmpty()){
            ArrayList<Integer> item = new ArrayList<>();
            int len = queue.size();
            while (len > 0){
                TreeNode curr = queue.poll();
                item.add(curr.val);
                if (curr.left != null){
                    queue.offer(curr.left);
                }
                if (curr.right != null){
                    queue.offer(curr.right);
                }

                len--;
            }
            res.add(item);
        }
        return res;
    }
}

// Solution 2: BFS Recursion
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
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        Integer deep = 0;
        traversal(res, root, deep);
        return res;
    }

    public void traversal(List<List<Integer>> res, TreeNode root, Integer deep){
        if (root == null) return;
        deep++;

        if (res.size() < deep) {
            ArrayList<Integer> item = new ArrayList<>();
            res.add(item);
        }
        res.get(deep - 1).add(root.val);
        traversal(res, root.left, deep);
        traversal(res, root.right, deep);
    }
}
```