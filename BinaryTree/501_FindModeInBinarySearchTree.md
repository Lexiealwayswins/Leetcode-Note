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
- Use inorder traversion
- Use hashmap to record the frequency

## Codes:
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
```Java