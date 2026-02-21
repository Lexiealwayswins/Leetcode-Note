# 515. Find Largest Value in Each Tree Row - Medium

## Problem Statement:

Given the root of a binary tree, return an array of the largest value in each row of the tree (0-indexed).
 
### Example 1:  
![](https://assets.leetcode.com/uploads/2020/08/21/largest_e1.jpg)

**Input:** root = \[1,3,2,5,3,null,9]
**Output:** \[1,3,9]

### Example 2:  

**Input:** root = \[1,2,3]
**Output:** \[1,3]

### Constraints:  
- The number of nodes in the tree will be in the range [0, 104].
- 2^31 <= Node.val <= 2^31 - 1

## Solution Notes: 
- BFS Iteration

## Codes:
```Python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def largestValues(self, root: Optional[TreeNode]) -> List[int]:
        if not root: return []
        res = []
        deque = collections.deque([root])
        while deque:
            max_node = float('-inf')
            for _ in range(len(deque)):
                curr = deque.popleft()
                if curr.val > max_node:
                    max_node = curr.val
                curr.left and deque.append(curr.left)
                curr.right and deque.append(curr.right)
            res.append(max_node)
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
 * @return {number[]}
 */
var largestValues = function(root) {
    if (!root) return [];
    let res = [];
    let queue = [root];
    while (queue.length) {
        let max = -Infinity;
        let count = queue.length;
        while (count > 0) {
            let curr = queue.shift();
            if (curr.val > max) max = curr.val;
            curr.left && queue.push(curr.left);
            curr.right && queue.push(curr.right);
            count--;
        }
        res.push(max);
    }
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
class Solution {
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int max = Integer.MIN_VALUE;
            int len = queue.size();
            while(len > 0){
                TreeNode curr = queue.poll();
                max = Math.max(max, curr.val);
                if (curr.left != null) queue.offer(curr.left);
                if (curr.right != null) queue.offer(curr.right);
                len--;
            }
            res.add(max);
        }
        return res;
    }
}
```