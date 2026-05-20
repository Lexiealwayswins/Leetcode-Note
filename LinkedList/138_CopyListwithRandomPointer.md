# 138. Copy List with Random Pointer - Medium

## Problem Statement:

A linked list of length n is given such that each node contains an additional random pointer, which could point to any node in the list, or null.

Construct a deep copy of the list. The deep copy should consist of exactly n brand new nodes, where each new node has its value set to the value of its corresponding original node. Both the next and random pointer of the new nodes should point to new nodes in the copied list such that the pointers in the original list and copied list represent the same list state. None of the pointers in the new list should point to nodes in the original list.

For example, if there are two nodes X and Y in the original list, where X.random --> Y, then for the corresponding two nodes x and y in the copied list, x.random --> y.

Return the head of the copied linked list.

The linked list is represented in the input/output as a list of n nodes. Each node is represented as a pair of [val, random_index] where:

val: an integer representing Node.val
random_index: the index of the node (range from 0 to n-1) that the random pointer points to, or null if it does not point to any node.
Your code will only be given the head of the original linked list.


### Example 1:

![](https://assets.leetcode.com/uploads/2019/12/18/e1.png)

**Input:** head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
**Output:** [[7,null],[13,0],[11,4],[10,2],[1,0]]

### Example 2:
![](https://assets.leetcode.com/uploads/2019/12/18/e2.png)

**Input:** head = [[1,1],[2,1]]
**Output:** [[1,1],[2,1]]

### Example 3:
![](https://assets.leetcode.com/uploads/2019/12/18/e3.png)

**Input:** head = [[3,null],[3,0],[3,null]]
**Output:** [[3,null],[3,0],[3,null]]
 
### Constraints:

- 0 <= n <= 1000
- -104 <= Node.val <= 104
- Node.random is null or is pointing to some node in the linked list.

## Solution Notes:
- Create new nodes copy for each old nodes
- Copy the random pointer to the new Nodes
- Seperate origin and copy

## Codes:
```TypeScript
/**
 * Definition for _Node.
 * class _Node {
 *     val: number
 *     next: _Node | null
 *     random: _Node | null
 * 
 *     constructor(val?: number, next?: _Node, random?: _Node) {
 *         this.val = (val===undefined ? 0 : val)
 *         this.next = (next===undefined ? null : next)
 *         this.random = (random===undefined ? null : random)
 *     }
 * }
 */


function copyRandomList(head: _Node | null): _Node | null {
    if (!head) return null;
    let curr = head;
    // 1. Create new nodes copy for each old nodes
    while (curr) {
        let newNode = new _Node(curr.val, curr.next);
        curr.next = newNode;
        curr = newNode.next;
    }

    
    // 2. Copy the random pointer to the new Nodes
    curr = head;
    while (curr) {
        if (curr.random) {
            // curr.next is new node copy of curr
            // curr.random.next is random node's copy
            // So here is pointing copy to random copy
            curr.next.random = curr.random.next;
        }
        curr = curr.next.next;
    }

    // 3. Seperate origin and copy
    const ori = head;
    const copy = head.next;
    let o_curr = ori;
    let n_curr = copy;
    while (n_curr) {
        o_curr.next = o_curr.next ? o_curr.next.next : null;
        n_curr.next = n_curr.next ? n_curr.next.next : null;
        o_curr = o_curr.next;
        n_curr = n_curr.next;
    }
    return copy;
};
```