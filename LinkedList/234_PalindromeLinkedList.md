# 234. Palindrome Linked List - Easy

## Problem Statement:

Given the head of a singly linked list, return true if it is a palindrome or false otherwise.


### Example 1:

![](https://assets.leetcode.com/uploads/2021/03/03/pal1linked-list.jpg)

Input: head = [1,2,2,1]
Output: true

### Example 2:
![](https://assets.leetcode.com/uploads/2021/03/03/pal2linked-list.jpg)

Input: head = [1,2]
Output: false
 
### Constraints:

- TThe number of nodes in the list is in the range [1, 105].
- 0 <= Node.val <= 9

Follow up: Could you do it in O(n) time and O(1) space?

## Solution Notes:
- Get the half point
- Reserse the second half
- Then compare the node's value

## Codes:
```TypeScript
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     val: number
 *     next: ListNode | null
 *     constructor(val?: number, next?: ListNode | null) {
 *         this.val = (val===undefined ? 0 : val)
 *         this.next = (next===undefined ? null : next)
 *     }
 * }
 */

function isPalindrome(head: ListNode | null): boolean {
    if (!head || !head.next) return true;

    // get the middle point
    let f = head;
    let s = head;
    while (f && f.next) {
        f = f.next.next;
        s = s.next;
    }

    // reverse the second half
    let reversed = null;
    let curr = s;
    while (curr) {
        let tmp = curr.next;
        curr.next = reversed;
        reversed = curr;
        curr = tmp;
    }

    while (head && reversed) {
        if (head.val !== reversed.val) return false;
        head = head.next;
        reversed = reversed.next;
    }
    return true;
};
```

