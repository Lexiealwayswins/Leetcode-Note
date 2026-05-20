# 114. Flatten Binary Tree to Linked List - Medium

## Problem Statement:

Given the root of a binary tree, flatten the tree into a "linked list":

The "linked list" should use the same TreeNode class where the right child pointer points to the next node in the list and the left child pointer is always null.
The "linked list" should be in the same order as a pre-order traversal of the binary tree.

### Example 1:  
![](https://assets.leetcode.com/uploads/2021/01/14/flaten.jpg)

Input: root = [1,2,5,3,4,null,6]
Output: [1,null,2,null,3,null,4,null,5,null,6]

### Example 2:  

Input: root = []
Output: []

### Example 3:  

Input: root = [0]
Output: [0]
 

## Constraints:

- The number of nodes in the tree is in the range [0, 2000].
- -100 <= Node.val <= 100

Follow up: Can you flatten the tree in-place (with O(1) extra space)?

## Solution Notes:  
### 1. Recursion
- Use reverse DFS:
    - First flatten right subtree
    - Then flatten left subtree
- Maintain a pointer prev to track the previously processed node
- At each node:
    - Set root.right = prev
    - Set root.left = null
    - Update prev = root
- This effectively builds the linked list in-place

### 2. Iteration
- Start from the root node.

- If the current node has a left child:

    - Find the rightmost node of the left subtree.
    - Attach that node’s right to the current node’s right subtree.
    - Move the left subtree to the right and set left = null.
    
- Move to the right child and repeat.

## Codes:
```TypeScript
// Solution 1: Recursion
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

/**
 Do not return anything, modify root in-place instead.
 */
let prev: TreeNode | null = null;
function flatten(root: TreeNode | null): void {
    if (!root) return;
    flatten(root.right);
    flatten(root.left);
    root.right = prev;
    root.left = null;
    prev = root;
};

// Solution 2: iteration
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

/**
 Do not return anything, modify root in-place instead.
 */

function flatten(root: TreeNode | null): void {
    if (!root) return;
    let curr: TreeNode = root;
    while (curr) {
        if (curr.left) {
            let tmp = curr.left;
            while (tmp.right) tmp = tmp.right;
            tmp.right = curr.right;
            curr.right = curr.left;
            curr.left = null;
        }
        curr = curr.right;
    }
};
```