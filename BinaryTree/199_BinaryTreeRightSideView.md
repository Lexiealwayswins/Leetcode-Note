# 199. Binary Tree Right Side View - Medium

## Problem Statement:

Given the root of a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.
 
### Example 1:  

**Input:** root = \[1,2,3,null,5,null,4] 
**Output:** \[[3],[9,20],[15,7]]
**Explanation:**
![](https://assets.leetcode.com/uploads/2024/11/24/tmpd5jn43fs-1.png)

### Example 2:  

**Input:** root = \[1,2,3,4,null,null,null,5]  
**Output:** \[1,3,4,5]
**Explanation:**
![](https://assets.leetcode.com/uploads/2024/11/24/tmpkpe40xeh-1.png)

### Example 3:

**Input:** root = \[1,null,3]
**Output:** \[1,3]

### Example 4:

**Input:** root = []
**Output:** []

### Constraints:  
- The number of nodes in the tree is in the range [0, 100].
- -100 <= Node.val <= 100

## Solution Notes: 
- BFS Iteration
- BFS Recursion

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
    def rightSideView(self, root: Optional[TreeNode]) -> List[int]:
        if not root: return []
        res = []
        deque = collections.deque([root])
        while deque:
            for _ in range(len(deque)):         
                curr = deque.popleft()
                curr.left and deque.append(curr.left)
                curr.right and deque.append(curr.right)
            res.append(curr.val)
        return res


# Solution 2: BFS Recursion
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def rightSideView(self, root: Optional[TreeNode]) -> List[int]:
        if not root: return []
        res = []
        
        def bfs(node, level):
            if not node: return
            
            if level == len(res):
                res.append(node.val)

            bfs(node.right, level + 1)
            bfs(node.left, level + 1)

        bfs(root, 0)
        return res
```

```JavaScript
// Solution 1: BFS Iteration
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
var rightSideView = function(root) {
    if (!root) return [];
    let res = [];
    let queue = [root];
    while (queue.length) {
        let count = queue.length;
        let curr;
        while (count > 0) {
            curr = queue.shift();
            curr.left && queue.push(curr.left);
            curr.right && queue.push(curr.right);
            count--;
        }
        res.push(curr.val);
    }
    return res;
};

// Solution 1: BFS Recursion
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
var rightSideView = function(root) {
    if (!root) return [];
    let res = [];
    const bfs = (node, level) => {
        if (!node) return;
        if (level == res.length) {
            res.push(node.val)
        }
        bfs(node.right, level + 1);
        bfs(node.left, level + 1);
    }
    bfs(root, 0);
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
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Deque<TreeNode> dq = new LinkedList<>();
        dq.offer(root);
        while (!dq.isEmpty()) {
            int len = dq.size();
            ArrayList<Integer> item = new ArrayList<>();
            while (len > 0) {
                TreeNode curr = dq.poll();
                item.add(curr.val);
                if (curr.left != null) {
                    dq.offer(curr.left);
                } 
                if (curr.right != null) {
                    dq.offer(curr.right);
                } 
                len--;
            }
            res.add(item.get(item.size() - 1));
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
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        int deep = 0;
        rightview(root, res, deep);
        return res;
    }

    public void rightview(TreeNode root, List<Integer> res, int deep){
        if (root == null) return;
        deep++;

        if (deep > res.size()) {
            res.add(root.val);
        }
        rightview(root.right, res, deep);
        rightview(root.left, res, deep);
    }
}
```