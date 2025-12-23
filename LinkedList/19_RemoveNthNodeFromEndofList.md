# 19. Remove Nth Node From End of List - Medium

## Problem Statement:
Given the head of a linked list, remove the nth node from the end of the list and return its head.


### Example 1:

![](https://assets.leetcode.com/uploads/2020/10/03/remove_ex1.jpg)

**Input:** head = [1,2,3,4,5], n = 2
**Output:** [1,2,3,5]

### Example 2:

**Input:** head = [1], n = 1
**Output:** []

### Example 3:

**Input:** head = [1,2], n = 1
**Output:** [1]
 

### Constraints:

- The number of nodes in the list is sz.
- 1 <= sz <= 30
- 0 <= Node.val <= 100
- 1 <= n <= sz
 
### Follow up: Could you do this in one pass?

## Solution Notes:
- Two pointers approach
- Let the fast pointer goes firstly for n steps, then the slow pointer starts to move with the faster pinter
- when the fast pointer reached the tail, the slow pointer reached the target

## Codes:
- Python 
```Python
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def removeNthFromEnd(self, head: Optional[ListNode], n: int) -> Optional[ListNode]:
        dummy = ListNode(0, head)
        f = dummy
        s = dummy
        for i in range(n):
            f = f.next

        while f.next:
            f = f.next
            s = s.next

        s.next = s.next.next

        return dummy.next
```

- JavaScript
```JavaScript
/**
 * Definition for singly-linked list.
 * function ListNode(val, next) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.next = (next===undefined ? null : next)
 * }
 */
/**
 * @param {ListNode} head
 * @param {number} n
 * @return {ListNode}
 */
var removeNthFromEnd = function(head, n) {
    const dummy = new ListNode(0, head);
    let f = dummy;
    let s = dummy;

    for (let i = 0; i < n; i++) {
        f = f.next;
    }

    while (f.next) {
        f = f.next;
        s = s.next;
    }

    s.next = s.next.next;
    return dummy.next;
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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) return null;

        if (head.next == null && n == 1) return null;

        ListNode fast = head;
        ListNode slow = head;

        int count = 0;
        while (count < n){
            fast = fast.next;
            count++;
        }

        if (fast == null) return head.next;
        
        while (fast.next != null){
            fast = fast.next;
            slow = slow.next;
        }
        
        slow.next = slow.next.next;

        return head;
    }
}
```