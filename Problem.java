import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Problem {
    // Definition for a Node.
    class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    };


    class Solution {
        public List<List<Integer>> levelOrder(Node root) {
            List<List<Integer>> res = new ArrayList<List<Integer>>();
            if (root == null) return res;
            Deque<Node> d = new LinkedList<>();
            d.offerLast(root);
            while(!d.isEmpty()){
                int len = d.size();
                ArrayList<Integer> item = new ArrayList<>();
                while(len > 0){
                    Node curr = d.pollFirst();
                    item.add(curr.val);
                    List<Node> children = curr.children;
                    if (children == null || children.size() == 0) {
                        len--;
                        continue;
                    }
                    
                    for (Node child: children){
                        if (child != null) d.offerLast(child);
                    }
                    len--;
                }
                res.add(item);
            }
            return res;
        }
    }

    public static void main(String[] args) {
        Problem exercise = new Problem();
        Solution solution = exercise.new Solution();

        Node node5 = exercise.new Node(5);
        Node node6 = exercise.new Node(6);
        List<Node> l3 = new ArrayList<>();
        l3.add(node5);
        l3.add(node6);
        Node node3 = exercise.new Node(3, l3);
        Node node2 = exercise.new Node(2);
        Node node4 = exercise.new Node(4);
        List<Node> l1 = new ArrayList<>();
        l1.add(node3);
        l1.add(node2);
        l1.add(node4);
        Node node1 = exercise.new Node(1, l1);
        List<List<Integer>> list = solution.levelOrder(node1);
        for (List<Integer> item : list)
            System.out.println(item);
    }
}