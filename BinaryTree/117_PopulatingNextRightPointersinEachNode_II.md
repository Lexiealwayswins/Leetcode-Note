# 117. Populating Next Right Pointers in Each Node II - Medium

## Problem Statement:

Given a binary tree:

struct Node {
  int val;
  Node *left;
  Node *right;
  Node *next;
}
Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.

Initially, all next pointers are set to NULL.
 
### Example 1:  
![](https://assets.leetcode.com/uploads/2019/02/15/117_sample.png)

**Input:** root = \[1,2,3,4,5,null,7]
**Output:** \[1,#,2,3,#,4,5,7,#]
**Explanation:** Given the above binary tree (Figure A), your function should populate each next pointer to point to its next right node, just like in Figure B. The serialized output is in level order as connected by the next pointers, with '#' signifying the end of each level.

### Example 2:  

**Input:** root = \[]
**Output:** \[]

### Constraints:  
- The number of nodes in the tree is in the range [0, 6000].
- -100 <= Node.val <= 100

### Follow-up:

- You may only use constant extra space.
- The recursive approach is fine. You may assume implicit stack space does not count as extra space for this problem.


## Solution Notes: 
- BFS Iteration
- Using the next pointers to traverse current level, and use dummy/tail to construct the next level.

## Codes:
```Python
#  Using the next pointers already established at each level, construct the next level.
"""
# Definition for a Node.
class Node:
    def __init__(self, val: int = 0, left: 'Node' = None, right: 'Node' = None, next: 'Node' = None):
        self.val = val
        self.left = left
        self.right = right
        self.next = next
"""

class Solution:
    def connect(self, root: 'Node') -> 'Node':
        if not root: return root
        curr = root
        while curr:
            dummy = Node(0)
            tail = dummy
            while curr:
                if curr.left:
                    tail.next = curr.left
                    tail = tail.next
                if curr.right:
                    tail.next = curr.right
                    tail = tail.next
                curr = curr.next
            curr = dummy.next
        return root
```

```JavaScript
// Solution 1: BFS
/**
 * // Definition for a _Node.
 * function _Node(val, left, right, next) {
 *    this.val = val === undefined ? null : val;
 *    this.left = left === undefined ? null : left;
 *    this.right = right === undefined ? null : right;
 *    this.next = next === undefined ? null : next;
 * };
 */

/**
 * @param {_Node} root
 * @return {_Node}
 */
var connect = function(root) {
    if (!root) return root;
    let queue = [root];
    while (queue.length) {
        let prev = null;
        let count = queue.length;
        while (count > 0) {
            let curr = queue.shift();
            if (prev) {
                prev.next = curr;
            }
            prev = curr;

            curr.left && queue.push(curr.left);
            curr.right && queue.push(curr.right);
            
            count--;
        }
    }
    return root;
};

// Solution 2: Using the next pointers to traverse current level, and use dummy/tail to construct the next level.
/**
 * // Definition for a _Node.
 * function _Node(val, left, right, next) {
 *    this.val = val === undefined ? null : val;
 *    this.left = left === undefined ? null : left;
 *    this.right = right === undefined ? null : right;
 *    this.next = next === undefined ? null : next;
 * };
 */

/**
 * @param {_Node} root
 * @return {_Node}
 */
var connect = function(root) {
    if (!root) return root;
    let curr = root;
    while (curr) {
        let dummy = new _Node(0);
        let tail = dummy;
        while (curr) {
            if (curr.left) {
                tail.next = curr.left;
                tail = tail.next;
            }
            if (curr.right) {
                tail.next = curr.right;
                tail = tail.next;
            }
            curr = curr.next;
        }
        curr = dummy.next;
    }
    return root;
};
```

```Java
/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}
    
    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/

class Solution {
    public Node connect(Node root) {
        if (root == null) return null;
        Queue<Node> q = new LinkedList<>();
        q.offer(root);
        while(!q.isEmpty()){
            Node rightNode = null;
            for (int i = q.size(); i > 0; i--) {
                Node curr = q.poll();
                curr.next = rightNode;
                rightNode = curr;
                if (curr.right != null) q.offer(curr.right);
                if (curr.left != null) q.offer(curr.left);
            }
        }
        return root;
    }
}
```