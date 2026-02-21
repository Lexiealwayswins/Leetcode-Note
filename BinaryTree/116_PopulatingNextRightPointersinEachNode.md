# 116. Populating Next Right Pointers in Each Node - Medium

## Problem Statement:

You are given a perfect binary tree where all leaves are on the same level, and every parent has two children. The binary tree has the following definition:

struct Node {
  int val;
  Node *left;
  Node *right;
  Node *next;
}
Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.

Initially, all next pointers are set to NULL.
 
### Example 1:  
![](https://assets.leetcode.com/uploads/2019/02/14/116_sample.png)

**Input:** root = \[1,2,3,4,5,6,7]
**Output:** \[1,#,2,3,#,4,5,6,7,#]

### Example 2:  

**Input:** root = \[]
**Output:** \[]

### Constraints:  
- The number of nodes in the tree is in the range [0, 212 - 1].
- 1000 <= Node.val <= 1000

## Solution Notes: 
- BFS Iteration
- Using the next pointers already established at each level, construct the next level.

## Codes:
```Python
# Solution 1: BFS
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
    def connect(self, root: 'Optional[Node]') -> 'Optional[Node]':
        if not root: return 
        deque = collections.deque([root])
        while deque:
            # Initially, all next pointers are set to NULL.
            prev = None
            for _ in range(len(deque)):
                curr = deque.popleft()
                if prev: 
                    prev.next = curr
                prev = curr
                curr.left and deque.append(curr.left)
                curr.right and deque.append(curr.right)
        return root 

# Solution 2: Using the next pointers already established at each level, construct the next level.
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
    def connect(self, root: 'Optional[Node]') -> 'Optional[Node]':
        if not root: return root
        leftmost = root
        while leftmost.left:
            head = leftmost
            while head:
                head.left.next = head.right
                if head.next:
                    head.right.next = head.next.left
                head = head.next
            leftmost = leftmost.left
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
        let count  = queue.length;
        let prev = null;
        while (count > 0) {
            let curr = queue.shift();
            if (prev !== null) {
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

// Solution 2: Using the next pointers already established at each level, construct the next level.
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
    let leftmost = root;
    while (leftmost.left) {
        let head = leftmost;
        while (head) {
            head.left.next = head.right;
            if (head.next) {
                head.right.next = head.next.left;
            }
            head = head.next;
        }
        leftmost = leftmost.left;
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
            int len = q.size();
            Node curr = q.poll();
            if (curr.left != null) q.offer(curr.left);
            if (curr.right != null) q.offer(curr.right);
            while(len > 1){
                Node n = q.poll();
                if (n.left != null) q.offer(n.left);
                if (n.right != null) q.offer(n.right);

                curr.next = n;
                curr = n;
                len--;
            }
        }
        return root;
    }
}
```