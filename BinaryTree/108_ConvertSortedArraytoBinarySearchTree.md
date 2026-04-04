# 108. Convert Sorted Array to Binary Search Tree - Easy

## Problem Statement:

Given an integer array nums where the elements are sorted in ascending order, convert it to a height-balanced binary search tree.
 
### Example 1:  
![](https://assets.leetcode.com/uploads/2021/02/18/btree1.jpg)

**Input:** nums = \[-10,-3,0,5,9]
**Output:** \[0,-3,9,-10,null,5]
**Explanation:**: [0,-10,5,null,-3,null,9] is also accepted:
![](https://assets.leetcode.com/uploads/2021/02/18/btree2.jpg)

### Example 2:  
![](https://assets.leetcode.com/uploads/2021/02/18/btree.jpg)

**Input:** nums = \[1,3]
**Output:** \[3,1]
**Explanation:**: [1,null,3] and [3,1] are both height-balanced BSTs.

### Constraints:  
- 1 <= nums.length <= 104
- -104 <= nums[i] <= 104
- nums is sorted in a strictly increasing order.

## Solution Notes: 
- reverse

## Codes:
```Python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def sortedArrayToBST(self, nums: List[int]) -> Optional[TreeNode]:
        if not len(nums): return None
        if len(nums) == 1:
            return TreeNode(nums[0])
        return self.traversal(nums, 0, len(nums) - 1)
        
    def traversal(self, nums: List[int], l: int, r: int) -> Optional[TreeNode]:
        if l > r: return None

        m = floor((l + r) / 2)
        root = TreeNode(nums[m])
        root.left = self.traversal(nums, l, m - 1)
        root.right = self.traversal(nums, m + 1, r)

        return root
            
```

```TypeScript
/**
 * Definition for a binary tree node.
 * class TreeNode {
 *     val: number
 *     left: TreeNode | null
 *     right: TreeNode | null
 *     constructor(val?: number, left?: TreeNode | null, right?: TreeNode | null) {
 *         this.val = (val===undefined ? 0 : val)
 *         this.left = (left===undefined ? null : left)
 *         this.right = (right===undefined ? null : right)
 *     }
 * }
 */

function sortedArrayToBST(nums: number[]): TreeNode | null {
    if (!nums.length) return null;
    if (nums.length === 1) return new TreeNode(nums[0]);

    function recur(nums: number[], l: number, r: number): TreeNode | null {
        if (l > r) return null;
        const m = Math.floor((l + r) / 2);
        const root = new TreeNode(nums[m]);
        root.left = recur(nums, l, m - 1);
        root.right = recur(nums, m + 1, r);
        return root;
    }
 
    return recur(nums, 0, nums.length - 1);
};
```

```Java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
        return getTree(nums, 0, nums.length - 1);
    }

    public TreeNode getTree(int[] nums, int l, int r){
        if (l > r) return null;
        int mid = l + ((r - l) >> 1);
        TreeNode root = new TreeNode(nums[mid]);
        root.left = getTree(nums, l, mid - 1);
        root.right = getTree(nums, mid + 1, r);
        return root;
    }
}
```