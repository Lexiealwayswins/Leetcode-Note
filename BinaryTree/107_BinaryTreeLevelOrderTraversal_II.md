# 107. Binary Tree Level Order Traversal II - Medium

## Problem Statement:

Given the root of a binary tree, return the bottom-up level order traversal of its nodes' values. (i.e., from left to right, level by level from leaf to root).
 
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
- reverse

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
    def levelOrderBottom(self, root: Optional[TreeNode]) -> List[List[int]]:
        if not root: return []
        res = collections.deque()
        deque = collections.deque([root])
        while deque:
            level = []
            for _ in range(len(deque)):
                curr = deque.popleft()
                level.append(curr.val)
                curr.left and deque.append(curr.left)
                curr.right and deque.append(curr.right)
            res.appendleft(level)
        return list(res)

# Solution 2: BFS Recursion
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def levelOrderBottom(self, root: Optional[TreeNode]) -> List[List[int]]:
        if not root: return []
        res = []

        def bfs (node, level):
            if not node: return 

            if len(res) == level:
                res.append([])

            res[level].append(node.val)
            node.left and bfs(node.left, level + 1)
            node.right and bfs(node.right, level + 1)

        bfs(root, 0)
        return res[::-1]
```

```JavaScript
// Solution 1: BFS
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
var levelOrderBottom = function(root) {
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
        res.unshift(level);
    }
    return res;
};

// Solution 2: Reverse
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
var levelOrderBottom = function(root) {
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
    return res.reverse();
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
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (root == null) return res;
        Deque<TreeNode> dq = new LinkedList<>();
        dq.offer(root);
        while (!dq.isEmpty()) {
            ArrayList<Integer> item = new ArrayList<>();
            int len = dq.size();
            while(len > 0){
                TreeNode curr = dq.removeFirst();
                item.add(curr.val);
                if (curr.left != null) dq.offer(curr.left);
                if (curr.right != null) dq.offer(curr.right);
                len--;
            }
            res.addFirst(item);
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
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (root == null) return res;
        Integer deep = 0;
        traversal(root, res, deep);
        Collections.reverse(res);
        return res;
    }

    public void traversal(TreeNode root, List<List<Integer>> res, Integer deep){
        if (root == null) return;
        deep++;

        if (res.size() < deep) {
            ArrayList<Integer> item = new ArrayList<>();
            res.add(item);
        }
        res.get(deep - 1).add(root.val);
        traversal(root.left, res, deep);
        traversal(root.right, res, deep);
    }
    
}
```