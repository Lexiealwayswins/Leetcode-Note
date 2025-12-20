# 24. Swap Nodes in Pairs - Medium

## Problem Statement:

Given a linked list, swap every two adjacent nodes and return its head. You must solve the problem without modifying the values in the list's nodes (i.e., only nodes themselves may be changed.)


### Example 1:

![](https://assets.leetcode.com/uploads/2020/10/03/swap_ex1.jpg)

**Input:** head = [1,2,3,4]
**Output:** [2,1,4,3]

### Example 2:

**Input:** head = []
**Output:** []

### Example 3:

**Input:** head = [1]
**Output:** [1]
 
### Constraints:

- The number of nodes in the list is in the range [0, 100].
- 0 <= Node.val <= 100

## Solution Notes:
- use dummy node
- Try to use recursion

## Codes:
- Python
```python
# Dummy node:

# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def swapPairs(self, head: Optional[ListNode]) -> Optional[ListNode]:
        dummy = ListNode(0, head)
        curr = dummy
        while curr.next and curr.next.next:
            temp1 = curr.next
            temp2 = curr.next.next.next
            curr.next = curr.next.next
            curr.next.next = temp1
            temp1.next = temp2
            curr = curr.next.next
        return dummy.next


# Recursion:
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def swapPairs(self, head: Optional[ListNode]) -> Optional[ListNode]:
        if not head or not head.next: return head

        temp = head.next
        newNode = self.swapPairs(temp.next)
        temp.next = head
        head.next = newNode

        return temp
```

- JavaScript
```JavaScript
// Dummy Node
/**
 * Definition for singly-linked list.
 * function ListNode(val, next) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.next = (next===undefined ? null : next)
 * }
 */
/**
 * @param {ListNode} head
 * @return {ListNode}
 */
var swapPairs = function(head) {
    let dummy = new ListNode(0, head);
    let curr = dummy;

    while (curr.next && curr.next.next) {
        let temp1 = curr.next;
        let temp2 = curr.next.next.next;
        curr.next = curr.next.next;
        curr.next.next = temp1;
        temp1.next = temp2;
        curr = curr.next.next;
    }

    return dummy.next;

};

// Recursion
/**
 * Definition for singly-linked list.
 * function ListNode(val, next) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.next = (next===undefined ? null : next)
 * }
 */
/**
 * @param {ListNode} head
 * @return {ListNode}
 */
var swapPairs = function(head) {
    if (!head || !head.next) return head;

    let temp = head.next;
    let newNode = swapPairs(temp.next);
    temp.next = head;
    head.next = newNode;

    return temp;

};
```



- Java
```Java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode tmp = head.next;
        head.next = swapPairs(head.next.next);
        tmp.next = head;

        return tmp;
    }
}
```