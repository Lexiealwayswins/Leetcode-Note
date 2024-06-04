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
