# 206. Reverse Linked List - Easy

## Problem Statement:
Given the head of a singly linked list, reverse the list, and return the reversed list.

### Example 1:

![](https://assets.leetcode.com/uploads/2021/02/19/rev1ex1.jpg)

**Intput:** head = [1,2,3,4,5]
**Output:** [5,4,3,2,1]

### Example 2:

![](https://assets.leetcode.com/uploads/2021/02/19/rev1ex2.jpg)

**Intput:** head = [1,2]
**Output:** [2,1]

### Example 3:

**Intput:** head = []
**Output:** []
 
### Constraints:

- The number of nodes in the list is the range [0, 5000].
- -5000 <= Node.val <= 5000
 

### Follow up: A linked list can be reversed either iteratively or recursively. Could you implement both?

## Solution Notes:
- Try to use a prev node
- Two pointer approach
- Recursion

## Codes
- Python
```Python
# two pointer
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def reverseList(self, head: Optional[ListNode]) -> Optional[ListNode]:
        dummy = None
        curr = head
        while curr:
            temp = curr.next
            curr.next = dummy
            dummy = curr
            curr = temp
        return dummy

# recursion
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def reverseList(self, head: Optional[ListNode]) -> Optional[ListNode]:
        return self.reverse(None, head)

    def reverse(self, pre: Optional[ListNode], head: Optional[ListNode]) -> Optional[ListNode]:
        if not head: return pre
        temp = head.next
        head.next = pre
        pre = head
        return self.reverse(pre, temp)
```

- JavaScript
```JavaScript

// two pointer
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
var reverseList = function(head) {
    let dummy = null;
    let curr = head;
    let temp;
    while (curr) {
        temp = curr.next;
        curr.next = dummy;
        dummy = curr;
        curr = temp;
    }
    return dummy;
};

// recursion
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
var reverseList = function(head) {
    return reverse(null, head);
};

var reverse = function(dummy, head) {
    if (!head) return dummy;
    let temp = head.next;
    head.next = dummy;
    dummy = head
    return reverse(dummy, temp);
}

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
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;

        while (curr != null){
            ListNode tmp = curr.next;
            curr.next = prev;

            prev = curr;
            curr = tmp;
        }

        return prev;
    }
}
```