# 160. Intersection of Two Linked Lists - Easy

## Problem Statement:
Given the heads of two singly linked-lists headA and headB, return the node at which the two lists intersect. If the two linked lists have no intersection at all, return null.

For example, the following two linked lists begin to intersect at node c1:

![](https://assets.leetcode.com/uploads/2021/03/05/160_statement.png)

The test cases are generated such that there are no cycles anywhere in the entire linked structure.

**Note** that the linked lists must **retain their original structure** after the function returns.

**Custom Judge:**

The inputs to the **judge** are given as follows (your program is not given these inputs):

- intersectVal - The value of the node where the intersection occurs. This is 0 if there is no intersected node.
- listA - The first linked list.
- listB - The second linked list.
- skipA - The number of nodes to skip ahead in listA (starting from the head) to get to the intersected node.
- skipB - The number of nodes to skip ahead in listB (starting from the head) to get to the intersected node.  

The judge will then create the linked structure based on these inputs and pass the two heads, headA and headB to your program. If you correctly return the intersected node, then your solution will be accepted.

### Example 1:

![](https://assets.leetcode.com/uploads/2021/03/05/160_example_1_1.png)

**Input:** intersectVal = 8, listA = [4,1,8,4,5], listB = [5,6,1,8,4,5], skipA = 2, skipB = 3  
**Output:** Intersected at '8'  
**Explanation**: The intersected node's value is 8 (note that this must not be 0 if the two lists intersect).  
From the head of A, it reads as [4,1,8,4,5]. From the head of B, it reads as [5,6,1,8,4,5]. There are 2 nodes before the intersected node in A; There are 3 nodes before the intersected node in B.
- Note that the intersected node's value is not 1 because the nodes with value 1 in A and B (2nd node in A and 3rd node in B) are different node references. In other words, they point to two different locations in memory, while the nodes with value 8 in A and B (3rd node in A and 4th node in B) point to the same location in memory.



### Example 2:

![](https://assets.leetcode.com/uploads/2021/03/05/160_example_2.png)

**Input:** intersectVal = 2, listA = [1,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1  
**Output:** Intersected at '2'  
**Explanation**: The intersected node's value is 2 (note that this must not be 0 if the two lists intersect).
From the head of A, it reads as [1,9,1,2,4]. From the head of B, it reads as [3,2,4]. There are 3 nodes before the intersected node in A; There are 1 node before the intersected node in B.

### Example 3:

![](https://assets.leetcode.com/uploads/2021/03/05/160_example_3.png)

**Input:** intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2    
**Output:** No intersection  
**Explanation**: From the head of A, it reads as [2,6,4]. From the head of B, it reads as [1,5]. Since the two lists do not intersect, intersectVal must be 0, while skipA and skipB can be arbitrary values.
Explanation: The two lists do not intersect, so return null.  
 

### Constraints:

- The number of nodes of listA is in the m.
- The number of nodes of listB is in the n.
- 1 <= m, n <= 3 * 104
- 1 <= Node.val <= 105
- 0 <= skipA < m
- 0 <= skipB < n
- intersectVal is 0 if listA and listB do not intersect.
- intersectVal == listA[skipA] == listB[skipB] if listA and listB intersect.
 

### Follow up: Could you write a solution that runs in O(m + n) time and use only O(1) memory?

## Solution Notes:
- Solution 1: Even if the lengths of the two linked lists are different, the sum of the two lengths is constant, so after walking the sum of the two lengths, they will definitely be in step with each other. When they are in step with each other, the while loop terminates, and the nodes where a and b are located are the same node.
- Solution 2: Compare the length of two lists, let the long list goes firstly for the length's gap's steps, then the shorter list starts to move with the long one until they are the same.

## Codes:
- Python
```Python
# Solution 1:
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    def getIntersectionNode(self, headA: ListNode, headB: ListNode) -> Optional[ListNode]:
        currA = headA
        currB = headB

        while currA != currB:
            currA = currA.next if currA else headB 
            currB = currB.next if currB else headA
        
        return currA


# Solution 2:
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    def getIntersectionNode(self, headA: ListNode, headB: ListNode) -> Optional[ListNode]:
        currA = headA
        currB = headB

        lenA = 0
        lenB = 0
        while currA:
            currA = currA.next
            lenA += 1
        while currB:
            currB = currB.next
            lenB += 1

        if lenA < lenB:
            currA = headB
            currB = headA
        else: 
            currA = headA
            currB = headB
        
        gap = abs(lenA - lenB)
        for i in range(gap):
            currA = currA.next
        
        while currA:
            if currA == currB: return currA
            currA = currA.next
            currB = currB.next
        
        return None

```

- JavaScript
```JavaScript
// Solution 1:
/**
 * Definition for singly-linked list.
 * function ListNode(val) {
 *     this.val = val;
 *     this.next = null;
 * }
 */

/**
 * @param {ListNode} headA
 * @param {ListNode} headB
 * @return {ListNode}
 */
var getIntersectionNode = function(headA, headB) {
    let currA = headA
    let currB = headB

    while (currA != currB) {
        currA = currA ? currA.next : headB 
        currB = currB ? currB.next : headA
    }
    return currA
};

// Solution 2:
/**
 * Definition for singly-linked list.
 * function ListNode(val) {
 *     this.val = val;
 *     this.next = null;
 * }
 */

/**
 * @param {ListNode} headA
 * @param {ListNode} headB
 * @return {ListNode}
 */
var getIntersectionNode = function(headA, headB) {
    let currA = headA;
    let currB = headB;

    let lenA = 0;
    let lenB = 0;
    while(currA) {
        currA = currA.next;
        lenA++;
    }
    while(currB) {
        currB = currB.next;
        lenB++;
    }

    if (lenB > lenA) {
        currA = headB;
        currB = headA;
    } else {
        currA = headA;
        currB = headB;
    }

    let gap = Math.abs(lenA - lenB);
    while (gap--) {
        currA = currA.next;
    }
    while(currA) {
        if (currA === currB) return currA;
        currA = currA.next;
        currB = currB.next;
    }
    return null;
};
```

- Java
```Java
// Solution 1:
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;

        ListNode a = headA;
        ListNode b = headB;

        while (a != b) {
            a = a == null? headB : a.next;
            b = b == null? headA : b.next;
        }

        return a;
    }
}
```