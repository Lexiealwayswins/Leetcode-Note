# 2. Add Two Numbers - Medium

## Problem Statement:

You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order, and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.


### Example 1:

![](https://assets.leetcode.com/uploads/2020/10/02/addtwonumber1.jpg)

**Input:** l1 = [2,4,3], l2 = [5,6,4]
**Output:** [7,0,8]
**Explanation:** 342 + 465 = 807.

### Example 2:

**Input:** l1 = [0], l2 = [0]
**Output:** [0]

### Example 3:

**Input:** l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
**Output:** [8,9,9,9,0,0,0,1]
 
### Constraints:

- The number of nodes in each linked list is in the range [1, 100].
- 0 <= Node.val <= 9
- It is guaranteed that the list represents a number that does not have leading zeros.

## Solution Notes:
- Use dummy node
- Always check whether the node exist before referring its value or its next node
- Use a carry variable

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

function addTwoNumbers(l1: ListNode | null, l2: ListNode | null): ListNode | null {
    
    let dummy = new ListNode(0);
    let curr = dummy;
    let sum = 0;
    let carry = 0;
    let v1 = 0;
    let v2 = 0
    
    while (l1 || l2 || carry) {
        v1 = l1 ? l1.val : 0;
        v2 = l2 ? l2.val : 0;
        sum  = v1 + v2 + carry;

        if (sum < 10) {
            curr.next = new ListNode(sum);
            carry = 0;
        } else {
            curr.next = new ListNode(sum % 10);
            carry = 1;
        }
        curr = curr.next;
        l1 = l1 ? l1.next : null;
        l2 = l2 ? l2.next : null;
    }
    return dummy.next;
};
```


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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode ans = dummyHead;

        int carrier = 0;
        while (l1 != null || l2 != null || carrier != 0) {
            int num1 = l1 == null ? 0 : l1.val;
            int num2 = l2 == null ? 0 : l2.val;
            int sum = num1 + num2 + carrier;
            ListNode curr = new ListNode(sum);
            if (sum >= 10){
                curr.val = sum - 10;
                carrier = 1;
            } else {
                carrier = 0;
            }

            ans.next = curr;
            ans = ans.next;

            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
        }
        return dummyHead.next;
    }
}
```