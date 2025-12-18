# 203. Remove Linked List Elements - Easy

## Problem Statement:
Given the head of a linked list and an integer val, remove all the nodes of the linked list that has Node.val == val, and return the new head.


### Example 1:
![](https://assets.leetcode.com/uploads/2021/03/06/removelinked-list.jpg)

**Intput:** head = [1,2,6,3,4,5,6], val = 6
**Output:** [1,2,3,4,5]

### Example 2:

**Intput:** head = [], val = 1
**Output:** []

### Example 3:

**Intput:** head = [7,7,7,7], val = 7
**Output:** []
 
### Constraints:

- The number of nodes in the list is in the range [0, 104].
- 1 <= Node.val <= 50
- 0 <= val <= 50

## Solution Notes:
- Make use of the dummyhead

## Codes:
- Python
```Python
# Solution 1

# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def removeElements(self, head: Optional[ListNode], val: int) -> Optional[ListNode]:
        dummy = ListNode(next = head)
        curr = dummy
        while curr.next:
            if curr.next.val == val:
                curr.next = curr.next.next
            else:
                curr = curr.next
        return dummy.next

# Solution 2:

# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def removeElements(self, head: Optional[ListNode], val: int) -> Optional[ListNode]:
        if head == None: return None
        while head != None and head.val == val:
            head = head.next
            if head == None: return None
        
        curr = head
        while curr.next != None:
            if curr.next.val == val:
                curr.next = curr.next.next
            else:
                curr = curr.next
        return head
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
 * @param {number} val
 * @return {ListNode}
 */
var removeElements = function(head, val) {
    const dummy = new ListNode(0, head);
    let curr = dummy;
    while (curr.next) {
        if (curr.next.val == val) {
            curr.next = curr.next.next;
        } else {
            curr = curr.next;
        }
    }
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
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) return null;

        while (head != null && head.val == val) {
            head = head.next;
            if (head == null) return null;
        }

        ListNode dummyhead = head;
        while (dummyhead.next != null){
            if (dummyhead.next.val == val) {
                dummyhead.next = dummyhead.next.next;
            } else {
                dummyhead = dummyhead.next;
            }
        }
        return head;
    }
}
```
