import java.util.*;

public class BFS_Level_Order_Traversal_Iteration {

    // 🌳 Node class - represents each node of the binary tree
    static class Node {
        int data;
        Node left, right;

        Node(int val) {
            this.data = val;
        }
    }

    // 🔁 BFS Level Order Traversal (Level by Level)
    public static void bfsLevelWise(Node root) {

        // Base case: if tree is empty
        if (root == null) return;

        // 🧺 Queue for BFS (FIFO)
        Queue<Node> q = new LinkedList<>();

        // Start with root
        q.offer(root);

        // Traverse until queue becomes empty
        while (!q.isEmpty()) {

            // 📏 Number of nodes present at current level
            int levelSize = q.size();

            // Process exactly these many nodes (current level)
            for (int i = 0; i < levelSize; i++) {

                // Remove front node
                Node curr = q.poll();

                // Visit node
                System.out.print(curr.data + " ");

                // Add left child for next level
                if (curr.left != null)
                    q.offer(curr.left);

                // Add right child for next level
                if (curr.right != null)
                    q.offer(curr.right);
            }

            // Move to next line after finishing one level
            System.out.println();
        }
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
        System.out.println("Level Order Traversal:");
        bfsLevelWise(root);
    }
}


/*
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🧠 SHORT NOTES: BFS Level Order (Level-wise)

📌 Goal:
Traverse the tree level by level from left to right.

📘 Concept:
- Use Queue (FIFO)
- At each step:
   • Queue contains nodes of current level
   • levelSize = number of nodes in that level

🛠️ Steps:
1. If root is null → return
2. Add root to queue
3. While queue is not empty:
   a. levelSize = q.size()
   b. Loop levelSize times:
       - Remove node
       - Print/process it
       - Add its left child
       - Add its right child
4. After loop → one level completed

📦 Data Structure:
Queue<Node>

🕒 Time Complexity: O(N)
🧠 Space Complexity: O(N)  (maximum nodes at a level)

🎯 Use Cases:
- Level order traversal
- Level-wise printing
- Right/Left view
- Zigzag traversal
- Level sum / average
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
*/


/*
🧪 DRY RUN

Tree:
            1
          /   \
         2     3
        / \   / \
       4   5 6   7

Initial:
Queue = [1]

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
Level 0:
levelSize = 1

Remove 1 → print 1
Add children → 2, 3

Queue after level: [2, 3]

Output:
1

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
Level 1:
levelSize = 2

Remove 2 → print 2
Add 4, 5

Remove 3 → print 3
Add 6, 7

Queue after level: [4, 5, 6, 7]

Output:
2 3

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
Level 2:
levelSize = 4

Remove 4 → print 4
Remove 5 → print 5
Remove 6 → print 6
Remove 7 → print 7

Queue = []

Output:
4 5 6 7

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
Final Output:

1
2 3
4 5 6 7
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
*/