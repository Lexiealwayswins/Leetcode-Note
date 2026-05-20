# 148. Sort List - Medium

## Problem Statement:

Given the head of a linked list, return the list after sorting it in ascending order.


### Example 1:

![](https://assets.leetcode.com/uploads/2020/09/14/sort_list_1.jpg)

Input: head = [4,2,1,3]
Output: [1,2,3,4]

### Example 2:
![](https://assets.leetcode.com/uploads/2020/09/14/sort_list_2.jpg)

Input: head = [-1,5,3,4,0]
Output: [-1,0,3,4,5]

### Example 3:

Input: head = []
Output: []
 
### Constraints:

- The number of nodes in the list is in the range [0, 5 * 104].
- -105 <= Node.val <= 105

## Solution Notes:
- Use dummy node
- Use merge sort and recursion

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

function sortList(head: ListNode | null): ListNode | null {
    if (!head || !head.next) return head;
    let f = head, s = head;
    let prev = null;
    while (f && f.next) {
        prev = s;
        f = f.next.next;
        s = s.next;
    }
    // use prev to cut into half
    prev.next = null;
    let left = sortList(head);
    let right = sortList(s);
    return merge(left, right);
};

function merge (l: ListNode, r: ListNode): ListNode | null {
    let dummy = new ListNode(0);
    let curr = dummy;
    while (l && r) {
        if (l.val < r.val) {
            curr.next = l;
            l = l.next;
        } else {
            curr.next = r;
            r = r.next;
        }
        curr = curr.next;
    }
    if (l) curr.next = l;
    if (r) curr.next = r;
    return dummy.next;
}
```