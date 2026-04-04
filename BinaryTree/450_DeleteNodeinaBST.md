# 450. Delete Node in a BST - Medium

## Problem Statement:

Given a root node reference of a BST and a key, delete the node with the given key in the BST. Return the root node reference (possibly updated) of the BST.

Basically, the deletion can be divided into two stages:

Search for a node to remove.
If the node is found, delete the node.
 
### Example 1:  
![](https://assets.leetcode.com/uploads/2020/09/04/del_node_1.jpg)

**Input:** root = \[5,3,6,2,4,null,7], key = 3
**Output:** \[5,4,6,2,null,null,7]
**Explanation:** Given key to delete is 3. So we find the node with value 3 and delete it.
One valid answer is [5,4,6,2,null,null,7], shown in the above BST.
Please notice that another valid answer is [5,2,6,null,4,null,7] and it's also accepted.

![](https://assets.leetcode.com/uploads/2020/09/04/del_node_supp.jpg)

### Example 2:  

**Input:** root = \root = [5,3,6,2,4,null,7], key = 0
**Output:** \[5,3,6,2,4,null,7]
**Explanation:** The tree does not contain a node with value = 0.

### Example 3:  
**Input:** root = [], key = 0
**Output:** \[]

### Constraints:  
- The number of nodes in the tree is in the range [0, 104].
- -105 <= Node.val <= 105
- Each node has a unique value.
- root is a valid binary search tree.
- -105 <= key <= 105

## Solution Notes: 
- Recursion

Follow up: Could you solve it with time complexity O(height of tree)?

## Codes:
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

function deleteNode(root: TreeNode | null, key: number): TreeNode | null {
    if (!root) return null;
    if (root.val > key) {
        root.left = deleteNode(root.left, key);
    } else if (root.val < key) {
        root.right = deleteNode(root.right, key);
    } else {
        if (!root.left) {
            return root.right;
        } else if (!root.right) {
            return root.left;
        } else {
            let curr = root.right;
            while (curr.left) {
                curr = curr.left
            }
            curr.left = root.left;
            return root.right;
        }
    }
    return root;
};
```

```Python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def deleteNode(self, root: Optional[TreeNode], key: int) -> Optional[TreeNode]:
        if not root: return None

        if key < root.val:
            root.left = self.deleteNode(root.left, key)
        elif key > root.val:
            root.right = self.deleteNode(root.right, key)
        else:
            if not root.left:
                return root.right
            elif not root.right:
                return root.left
            else:
                curr = root.left
                while curr.right:
                    curr = curr.right
                curr.right = root.right
                return root.left
        return root
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
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return root;

        if (key < root.val) root.left = deleteNode(root.left, key);
        if (key > root.val) root.right = deleteNode(root.right, key);

        if (key == root.val) {
            if (root.left != null && root.right == null) {
                return root.left;
            } else if (root.left == null && root.right != null) {
                return root.right;
            } else if (root.left == null && root.right == null) {
                return null;
            } else {
                TreeNode curr = root.left;
                while (curr.right != null){
                    curr = curr.right;
                }
                curr.right = root.right;
                root.right = null;
                root = root.left;
            }
        }
        return root;
    }
}
```