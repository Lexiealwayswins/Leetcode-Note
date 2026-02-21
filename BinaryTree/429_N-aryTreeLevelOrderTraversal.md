# 429. N-ary Tree Level Order Traversal - Medium

## Problem Statement:

Given an n-ary tree, return the level order traversal of its nodes' values.

Nary-Tree input serialization is represented in their level order traversal, each group of children is separated by the null value (See examples).
 
### Example 1:  
![](https://assets.leetcode.com/uploads/2018/10/12/narytreeexample.png)

**Input:** root = \[1,null,3,2,4,null,5,6]
**Output:** \[[1],[3,2,4],[5,6]]

### Example 2:  
![](https://assets.leetcode.com/uploads/2019/11/08/sample_4_964.png)

**Input:** root = \[1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
**Output:** \[[1],[2,3,4,5],[6,7,8,9,10],[11,12,13],[14]]

### Constraints:  
- The height of the n-ary tree is less than or equal to 1000
- The total number of nodes is between [0, 104]

## Solution Notes: 
- BFS Iteration
- BFS Recursion

## Codes:
```Python
# Solution 1: BFS Iteration
"""
# Definition for a Node.
class Node:
    def __init__(self, val: Optional[int] = None, children: Optional[List['Node']] = None):
        self.val = val
        self.children = children
"""

class Solution:
    def levelOrder(self, root: 'Node') -> List[List[int]]:
        if not root: return []
        res = []
        deque = collections.deque([root])
        while deque:
            level = []
            for _ in range(len(deque)):
                curr = deque.popleft()
                level.append(curr.val)
                if curr.children:
                    for child in curr.children:
                        deque.append(child)
            res.append(level)
        return res

# Solution 2: BFS Recursion
"""
# Definition for a Node.
class Node:
    def __init__(self, val: Optional[int] = None, children: Optional[List['Node']] = None):
        self.val = val
        self.children = children
"""

class Solution:
    def levelOrder(self, root: 'Node') -> List[List[int]]:
        if not root: return []
        res = []
        
        def bfs (node, level):
            if not node: return

            if len(res) == level:
                res.append([])
            
            res[level].append(node.val)
            if node.children:
                for child in node.children:
                    bfs(child, level + 1)
        
        bfs(root, 0)
        return res
        
```

```JavaScript
// Solution 1: BFS Iteration
/**
 * // Definition for a _Node.
 * function _Node(val,children) {
 *    this.val = val;
 *    this.children = children;
 * };
 */

/**
 * @param {_Node|null} root
 * @return {number[][]}
 */
var levelOrder = function(root) {
    if (!root) return [];
    let res = [];
    let queue = [root];
    while (queue.length) {
        let count = queue.length;
        let level = [];
        while (count > 0) {
            let curr = queue.shift();
            level.push(curr.val);
            if (curr.children.length) {
                for (const child of curr.children) {
                    queue.push(child);
                }
            }
            count--;
        }
        res.push(level);
    }
    return res;
};

// Solution 2: BFS Recursion
/**
 * // Definition for a _Node.
 * function _Node(val,children) {
 *    this.val = val;
 *    this.children = children;
 * };
 */

/**
 * @param {_Node|null} root
 * @return {number[][]}
 */
var levelOrder = function(root) {
    if (!root) return [];
    let res = [];

    const bfs = (node, level) => {
        if (!node) return;
        if (level === res.length) {
            res.push([]);
        }
        res[level].push(node.val);
        if (node.children.length) {
            for (const child of node.children) {
                bfs(child, level + 1);
            }
        }
    }
    bfs(root, 0);
    return res;
};
```

```Java
/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/

class Solution {
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (root == null) return res;
        Deque<Node> d = new LinkedList<>();
        d.offerLast(root);
        while(!d.isEmpty()){
            int len = d.size();
            ArrayList<Integer> item = new ArrayList<>();
            while(len > 0){
                Node curr = d.pollFirst();
                item.add(curr.val);
                List<Node> children = curr.children;
                if (children == null || children.size() == 0) {
                    len--;
                    continue;
                }
                for (Node child: children){
                    if (child != null) d.offerLast(child);
                }
                len--;
            }
            res.add(item);
        }
        return res;
    }
}
```