# 501. Find Mode in Binary Search Tree - Easy

## Problem Statement:

Given the root of a binary search tree (BST) with duplicates, return all the mode(s) (i.e., the most frequently occurred element) in it.

If the tree has more than one mode, return them in any order.

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than or equal to the node's key.
The right subtree of a node contains only nodes with keys greater than or equal to the node's key.
Both the left and right subtrees must also be binary search trees.
 

### Example 1:
![](https://assets.leetcode.com/uploads/2021/03/11/mode-tree.jpg)

**Input:** root = [1,null,2,2]  
**Output:** [2]  

### Example 2:  

**Input:** root = [0]  
**Output:** [0]  
 

## Constraints:

- The number of nodes in the tree is in the range [1, 104].
- -105 <= Node.val <= 105
 

### Follow up: Could you do that without using any extra space? (Assume that the implicit stack space incurred due to recursion does not count).

## Solution Notes:  
- Recursion
- Use inorder traversion
- Use hashmap to record the frequency

## Codes:
```Python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def __init__(self):
        self.count = 0
        self.prev = None
        self.maxMode = float("-inf")
        self.res = []

    def findMode(self, root: Optional[TreeNode]) -> List[int]:
        if not root: return []
        self.getFreq(root)
        return self.res

    def getFreq(self, node: Optional[TreeNode]) -> None:
        if not node: return

        self.getFreq(node.left)

        if not self.prev:
            self.count = 1
        elif self.prev.val == node.val:
            self.count += 1
        else:
            self.count = 1
        self.prev = node

        if self.maxMode == self.count:
            self.res.append(node.val)
        if self.maxMode < self.count:
            self.maxMode = self.count
            self.res.clear()
            self.res.append(node.val)

        self.getFreq(node.right)
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

function findMode(root: TreeNode | null): number[] {
    if (!root) return [];
    let res: number[] = [];
    let curr: TreeNode | null = root;
    let prev: TreeNode | null = null;
    let count: number = 0;
    let maxMode: number = 0;

    let stack: TreeNode[] = [];

    while (curr || stack.length) {
        if (curr) {
            stack.push(curr);
            curr = curr.left;
        } else {
            curr = stack.pop();
            if (!prev) {
                count = 1;
            } else if (prev.val === curr.val) {
                count++;
            } else {
                count = 1;
            }
            if (maxMode === count) {
                res.push(curr.val)
            } else if (maxMode < count) {
                res = [];
                maxMode = count;
                res.push(curr.val);
            }
            prev = curr;
            curr = curr.right;
        }
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
    public int[] findMode(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        Stack<TreeNode> s = new Stack<>();
        int max = 0;
        TreeNode curr = root;
        while(curr != null || !s.isEmpty()){
            if (curr != null){
                s.push(curr);
                curr = curr.left;
            } else {
                curr = s.pop();
                int count = map.getOrDefault(curr.val, 0) + 1;
                if (count > max) {
                    max = count;
                    res.clear();
                    res.add(curr.val);
                } else if (count == max) {
                    res.add(curr.val);
                }
                map.put(curr.val, map.getOrDefault(curr.val, 0) + 1);
                curr = curr.right;
            }
        }

        int[] result = new int[res.size()];
        for (int i = 0; i < res.size(); i++){
            result[i] = res.get(i);
        }
        return result;
    }
}
```