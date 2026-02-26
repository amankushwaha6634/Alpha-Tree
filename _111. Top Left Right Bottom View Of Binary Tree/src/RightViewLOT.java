import java.util.*;

public class RightViewLOT {

    // 👉 Function to get Right View of Binary Tree
    public static List<Integer> rightView(Node root) {

        // 📌 Edge case
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        // Queue for level order traversal (BFS)
        Queue<Node> q = new LinkedList<>();
        q.add(root);

        // 🔄 Level Order Traversal
        while (!q.isEmpty()) {

            int size = q.size(); // Number of nodes at current level

            for (int i = 0; i < size; i++) {

                Node curr = q.poll();

                // ⭐ If last node of this level → visible from right
                if (i == size - 1) {
                    result.add(curr.data);
                }

                // Add left child
                if (curr.left != null)
                    q.add(curr.left);

                // Add right child
                if (curr.right != null)
                    q.add(curr.right);
            }
        }

        return result;
    }

    // 🌳 Driver Code
    public static void main(String[] args) {

        /*
                 1
               /   \
              2     3
             / \     \
            4   5     7
                       /
                      8

        Right View:
        1 3 7 8
        */

        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.right = new Node(7);
        root.right.right.left = new Node(8);

        List<Integer> output = rightView(root);

        System.out.println("Right View:");
        for (int val : output) {
            System.out.print(val + " ");
        }
    }

    // Node class
    static class Node {
        int data;
        Node left, right;

        Node(int data) {
            this.data = data;
        }
    }
}

/*
🧠 SHORT NOTES: Right View (BFS)

📌 Goal:
Print nodes visible when tree is viewed from the RIGHT side.

📘 Concept:
- Use Level Order Traversal (BFS)
- For each level, the LAST node is visible from the right

🛠️ Steps:
1. Add root to queue
2. While queue not empty:
   - Get level size
   - Traverse level nodes
   - If i == size - 1 → add node to result
3. Return result

📦 Data Structures:
- Queue<Node> → BFS traversal
- List<Integer> → result

🕒 Time: O(N)
🧠 Space: O(N)

🎯 Output: Last node from each level
*/

/*
🧪 DRY RUN: Right View of Binary Tree (Level Order)

Example Tree:
             1
           /   \
         2       3
        / \       \
       4   5       7
                    /
                   8

📌 Rule:
At each level, the LAST node seen in BFS is the right view.

▶ Initial Setup:
Queue = [1]
Result = []

🔁 Step-by-step Traversal:

Level 1:
Nodes = [1]
➤ Last node = 1 → result = [1]
Queue = [2, 3]

Level 2:
Nodes = [2, 3]
➤ Last node = 3 → result = [1, 3]
Queue = [4, 5, 7]

Level 3:
Nodes = [4, 5, 7]
➤ Last node = 7 → result = [1, 3, 7]
Queue = [8]

Level 4:
Nodes = [8]
➤ Last node = 8 → result = [1, 3, 7, 8]
Queue = []

🎯 Final Output:
1 3 7 8
*/


