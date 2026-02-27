import java.util.*;

public class  Main {

    // 🌿 Node structure
    static class Node {
        int val;
        Node left, right;

        Node(int val) {
            this.val = val;
        }
    }

    // 🌐 Helper class to keep track of node and its position/index
    static class Pair {
        Node node;
        int index;

        Pair(Node node, int index) {
            this.node = node;
            this.index = index;
        }
    }

    // 🚀 Function to calculate maximum width
    public static int widthOfBinaryTree(Node root) {
        if (root == null) return 0;

        int maxWidth = 0;

        // 🧺 Use Queue for level-order traversal (BFS)
        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(root, 0));  // 🌟 root has index 0

        while (!q.isEmpty()) {
            int size = q.size();
            int minIndex = q.peek().index; // 🟢 index of leftmost node at this level

            int first = 0, last = 0;  // To store index of first and last node at current level

            for (int i = 0; i < size; i++) {
                Pair curr = q.poll();
                int curIndex = curr.index - minIndex; // Normalize to prevent overflow
                Node node = curr.node;

                if (i == 0) first = curIndex;  // 🔢 first node index
                if (i == size - 1) last = curIndex; // 🔚 last node index

                // 📥 Left child index = 2 * index
                if (node.left != null)
                    q.offer(new Pair(node.left, 2 * curIndex + 1));

                // 📤 Right child index = 2 * index + 1
                if (node.right != null)
                    q.offer(new Pair(node.right, 2 * curIndex + 2));
            }

            int width = last - first + 1;  // 📏 width at current level
            maxWidth = Math.max(maxWidth, width);  // ✅ update maxWidth
        }

        return maxWidth;
    }

    public static void main(String[] args) {
        /*
                   1
                 /   \
               2       3
              /         \
             4           5

        Widths:
        Level 0:        1 → width = 1
        Level 1:     2     3 → width = 2
        Level 2:   4         5 → width = 4 (index 0 to 3)

        Expected Output: 4
        */

        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.right.right = new Node(5);

        System.out.println("Maximum Width of Binary Tree: " + widthOfBinaryTree(root)); // Output: 4
    }
}

/*
🧠 SHORT NOTES: Maximum Width of Binary Tree

📌 Goal:
Find the maximum width among all levels.
Width = distance between leftmost and rightmost nodes (including null gaps).

📘 Concept:
- Use Level Order Traversal (BFS)
- Assign each node an index as if the tree is a complete binary tree
    Left  = 2*i + 1
    Right = 2*i + 2
- Width of level = lastIndex - firstIndex + 1

💡 Important Optimization:
curIndex = index - minIndex
→ Prevents integer overflow for deep trees.

🛠️ Steps:
1. Push root with index = 0
2. For each level:
   - Store minIndex (first node index)
   - Normalize indices
   - Track first and last index
   - Calculate width
3. Update maxWidth

📦 Data Structures:
- Queue<Pair(node, index)> → BFS

🕒 Time Complexity: O(N)
🧠 Space Complexity: O(N)

🎯 Output:
Maximum width across all levels
*/

/*
🧪 DRY RUN: Maximum Width of Binary Tree (Using Index-Based BFS)

Example Tree:
                   1
                 /   \
               2       3
              /         \
             4           5

Indexing Rule (like complete binary tree):
Left child  → 2*i + 1
Right child → 2*i + 2

-------------------------------------------------

▶ Initial State:
Queue = [(1, index=0)]
maxWidth = 0

-------------------------------------------------

Level 0:
Nodes: [1]
minIndex = 0

Process:
curIndex = 0 - 0 = 0
first = 0, last = 0

Add children:
2 → index = 1
3 → index = 2

Width = last - first + 1 = 1
maxWidth = 1

Queue = [(2,1), (3,2)]

-------------------------------------------------

Level 1:
minIndex = 1

Process 2:
curIndex = 1-1 = 0 → first = 0
Add left child 4 → index = 1

Process 3:
curIndex = 2-1 = 1 → last = 1
Add right child 5 → index = 4

Width = 1 - 0 + 1 = 2
maxWidth = 2

Queue = [(4,1), (5,4)]

-------------------------------------------------

Level 2:
minIndex = 1

Process 4:
curIndex = 1-1 = 0 → first = 0

Process 5:
curIndex = 4-1 = 3 → last = 3

Width = 3 - 0 + 1 = 4
maxWidth = 4

Queue = []

-------------------------------------------------

📤 Final Answer:
Maximum Width = 4
*/