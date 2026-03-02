import java.util.*;

public class BFS_Level_Order_Traversal_Vector2 {

    // 🌳 Node class
    static class Node {
        int data;
        Node left, right;

        Node(int val) {
            this.data = val;
        }
    }

    // 🔁 BFS Level Order and store all levels
    public static List<List<Integer>> levelOrder(Node root) {

        // 📦 This will store all levels
        List<List<Integer>> result = new ArrayList<>();

        // Base case
        if (root == null) return result;

        // Queue for BFS
        Queue<Node> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {

            int levelSize = q.size();  // Number of nodes in current level

            // 📦 List to store current level values
            List<Integer> level = new ArrayList<>();

            for (int i = 0; i < levelSize; i++) {

                Node curr = q.poll();

                // Store node value
                level.add(curr.data);

                // Add children for next level
                if (curr.left != null)
                    q.offer(curr.left);

                if (curr.right != null)
                    q.offer(curr.right);
            }

            // Add completed level to result
            result.add(level);
        }

        return result;
    }

    // 🔧 Build sample tree
    /*
            1
          /   \
         2     3
        / \   / \
       4   5 6   7
    */
    public static Node buildTree() {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);
        return root;
    }

    public static void main(String[] args) {
        Node root = buildTree();

        List<List<Integer>> result = levelOrder(root);

        System.out.println(result);
    }
}

/*
🧠 SHORT NOTES: BFS Store Levels

        📌 Goal:
        Return level order traversal as List<List<Integer>>

        📘 Concept:
        Use Queue and process tree level by level.

        🛠️ Steps:
        1. Create result list
        2. Add root to queue
        3. While queue not empty:
        a. levelSize = q.size()
        b. Create new level list
        c. Loop levelSize times:
        - Remove node
        - Add value to level list
        - Add its children
        d. Add level list to result

        📦 Data Structures:
        Queue<Node>
        List<List<Integer>>

        🕒 Time Complexity: O(N)
        🧠 Space Complexity: O(N)

        🎯 Output:
        [[1], [2,3], [4,5,6,7]]
*/