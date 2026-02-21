# 637. Average of Levels in Binary Tree - Easy

## Problem Statement:

Given the root of a binary tree, return the average value of the nodes on each level in the form of an array. Answers within 10-5 of the actual answer will be accepted.
 
### Example 1:  
![](https://assets.leetcode.com/uploads/2021/03/09/avg1-tree.jpg)

**Input:** root = \[3,9,20,null,null,15,7]
**Output:** \[3.00000,14.50000,11.00000]
**Explanation:** The average value of nodes on level 0 is 3, on level 1 is 14.5, and on level 2 is 11.
Hence return [3, 14.5, 11].

### Example 2:  
![](https://assets.leetcode.com/uploads/2021/03/09/avg2-tree.jpg)

**Input:** root = \[3,9,20,15,7]
**Output:** \[3.00000,14.50000,11.00000]

### Constraints:  
- The number of nodes in the tree is in the range [1, 104].
-2^31 <= Node.val <= 2^31 - 1

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
    def averageOfLevels(self, root: Optional[TreeNode]) -> List[float]:
        if not root: return []
        res = []
        deque = collections.deque([root])
        while deque:
            total = 0
            div = len(deque)
            for _ in range(div):
                curr = deque.popleft()
                total += curr.val
                curr.left and deque.append(curr.left)
                curr.right and deque.append(curr.right)
            res.append(total / div)
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
var averageOfLevels = function(root) {
    if (!root) return [];
    let res = [];
    let queue = [root];
    while (queue.length) {
        let count = queue.length;
        let div = count;
        let sum = 0;
        while (count > 0) {
            let curr = queue.shift();
            sum += curr.val;
            curr.left && queue.push(curr.left);
            curr.right && queue.push(curr.right);
            count--;
        }
        res.push(sum / div);
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
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while(!q.isEmpty()){
            double[] sumCount = new double[2];
            int len = q.size();
            while (len > 0) {
                TreeNode curr = q.poll();
                sumCount[0]++;
                sumCount[1] += curr.val;
                if (curr.left != null) q.offer(curr.left);
                if (curr.right != null) q.offer(curr.right);
                len--;
            }
            res.add(sumCount[1] / sumCount[0]);
        }
        return res;
    }
}
```