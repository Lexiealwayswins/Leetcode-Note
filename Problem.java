import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import javax.swing.tree.TreeNode;

import org.w3c.dom.Node;

public class Problem {
    // Definition for a Node.
    /**
 * Definition for a binary tree node.
 * */
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
           this.val = val;
           this.left = left;
           this.right = right;
       }
    }
 
    class Solution {
        public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
            List<List<Integer>> res = new ArrayList<>();
            if (root == null) return res;

            Stack<Object> stack = new Stack<>();
            stack.push(root);
            stack.push(root.val);

            Stack<List<Integer>> s = new Stack<>();
            List<Integer> item = new ArrayList<>();
            item.add(root.val);
            s.push(item);

            while(!stack.isEmpty()){
                Integer sum = (Integer) stack.pop();
                TreeNode curr = (TreeNode) stack.pop();
                List<Integer> path = s.pop();
                if (curr.left == null && curr.right == null){
                    if (sum == targetSum) res.add(path);
                }
                if (curr.right != null) {
                    stack.push(curr.right);
                    stack.push(curr.right.val + sum);
                    path.add(curr.right.val);
                    s.push(path);
                }
                if (curr.left != null) {
                    stack.push(curr.left);
                    stack.push(curr.left.val + sum);
                    s.push(path.add(curr.left.val));
                }
            }
            return res;
        }
    }

    public static void main(String[] args) {
        Problem exercise = new Problem();
        Solution solution = exercise.new Solution();

        // Node node5 = exercise.new Node(5);
        // Node node6 = exercise.new Node(6);
        // List<Node> l3 = new ArrayList<>();
        // l3.add(node5);
        // l3.add(node6);
        // Node node3 = exercise.new Node(3, l3);
        // Node node2 = exercise.new Node(2);
        // Node node4 = exercise.new Node(4);
        // List<Node> l1 = new ArrayList<>();
        // l1.add(node3);
        // l1.add(node2);
        // l1.add(node4);
        // Node node1 = exercise.new Node(1, l1);
        // List<List<Integer>> list = solution.levelOrder(node1);
        // for (List<Integer> item : list)
        //     System.out.println(item);

        
        

        TreeNode node4 = exercise.new TreeNode(7);
        TreeNode node5 = exercise.new TreeNode(2);
        TreeNode node3 = exercise.new TreeNode(11, node4, node5);
        TreeNode node2 = exercise.new TreeNode(4, node3, null);

        TreeNode node6 = exercise.new TreeNode(5);
        TreeNode node7 = exercise.new TreeNode(1);
        TreeNode node10 = exercise.new TreeNode(4, node6, node7);
        TreeNode node9 = exercise.new TreeNode(13);
        TreeNode node8 = exercise.new TreeNode(8, node9, node10);

        TreeNode root = exercise.new TreeNode(5, node2, node8);

        int targetSum = 22;

        List<List<Integer>> list = solution.pathSum(root, targetSum);
        for (List<Integer> item : list)
            System.out.println(item);
    }
}