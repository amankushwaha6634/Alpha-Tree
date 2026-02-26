import java.util.*;

public class TopView {

    // 🔝 Function to print Top View of Binary Tree
    public static List<Integer> topView(Node root) {

        // 📌 Edge case
        if (root == null) return new ArrayList<>();

        // TreeMap: HD -> first node at that HD (topmost)
        TreeMap<Integer, Integer> map = new TreeMap<>();

        // Queue for BFS: stores (node, hd)
        Queue<Pair> q = new LinkedList<>();

        // Start from root at hd = 0
        q.add(new Pair(root, 0));

        // 🔄 BFS Traversal
        while (!q.isEmpty()) {

            Pair curr = q.poll();
            Node node = curr.node;
            int hd = curr.hd;

            // ⭐ Store only first node at this HD
            if (!map.containsKey(hd)) {
                map.put(hd, node.data);
            }

            // Add left child
            if (node.left != null)
                q.add(new Pair(node.left, hd - 1));

            // Add right child
            if (node.right != null)
                q.add(new Pair(node.right, hd + 1));
        }

        // 📦 Collect result from TreeMap (sorted HD)
        List<Integer> result = new ArrayList<>();
        for (int val : map.values()) {
            result.add(val);
        }

        return result;
    }

    // 🌳 Driver Code
    public static void main(String[] args) {

        /*
                 1
               /   \
              2     3
             / \   / \
            4   5 6   7
                   /
                  8

        Top View:
        4 2 1 3 7
        */

        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);
        root.right.right.left = new Node(8);

        List<Integer> output = topView(root);

        System.out.println("Top View:");
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

    // Pair class (Node + Horizontal Distance)
    static class Pair {
        Node node;
        int hd;

        Pair(Node node, int hd) {
            this.node = node;
            this.hd = hd;
        }
    }
}


/*
🧪 DRY RUN: Top View of Binary Tree (BFS with horizontal distance)

Example Tree:
             1
           /   \
         2       3
        / \     / \
       4   5   6   7
                  /
                 8

📌 Rule:
For each Horizontal Distance (HD), keep only the FIRST node encountered
(Because it is the topmost node).

▶ Initial Setup:
- TreeMap: hd → first node at that horizontal distance
- Queue: stores Pair(node, hd)

📦 Start with:
Queue = [(1, 0)]
Map = {}

🔁 Step-by-step Traversal:

1️⃣ Pop (1, 0)
   ➤ hd 0 not present → map = {0: 1}
   ➤ Add (2, -1) and (3, 1)
Queue = [(2, -1), (3, 1)]

2️⃣ Pop (2, -1)
   ➤ hd -1 not present → map = {-1: 2, 0: 1}
   ➤ Add (4, -2) and (5, 0)
Queue = [(3, 1), (4, -2), (5, 0)]

3️⃣ Pop (3, 1)
   ➤ hd 1 not present → map = {-1: 2, 0: 1, 1: 3}
   ➤ Add (6, 0) and (7, 2)
Queue = [(4, -2), (5, 0), (6, 0), (7, 2)]

4️⃣ Pop (4, -2)
   ➤ hd -2 not present → map = {-2: 4, -1: 2, 0: 1, 1: 3}
Queue = [(5, 0), (6, 0), (7, 2)]

5️⃣ Pop (5, 0)
   ➤ hd 0 already exists → ignore (1 is topmost)
Queue = [(6, 0), (7, 2)]

6️⃣ Pop (6, 0)
   ➤ hd 0 already exists → ignore
Queue = [(7, 2)]

7️⃣ Pop (7, 2)
   ➤ hd 2 not present → map = {-2: 4, -1: 2, 0: 1, 1: 3, 2: 7}
   ➤ Add (8, 1)
Queue = [(8, 1)]

8️⃣ Pop (8, 1)
   ➤ hd 1 already exists → ignore (3 is topmost)
Queue = []

📤 Final TreeMap (Top View):
- hd -2 → 4
- hd -1 → 2
- hd  0 → 1
- hd  1 → 3
- hd  2 → 7

🎯 Final Output (Left → Right):
4 2 1 3 7
*/


/*
🧠 SHORT NOTES: Top View (BFS + Map)

📌 Goal:
Print nodes visible when the tree is viewed from the TOP.

📘 Concept:
- Use BFS to process nodes level by level
- Track horizontal distance (hd):
   ➤ Root = 0
   ➤ Left = hd - 1
   ➤ Right = hd + 1
- For each hd, store ONLY the first node encountered
  (This will be the topmost node)

🛠️ Steps:
1. Use Queue<Pair<Node, hd>> for BFS
2. Start with root at hd = 0
3. While queue is not empty:
   - Pop node
   - If hd not in map → store node
   - Add left (hd - 1)
   - Add right (hd + 1)
4. Print TreeMap values in sorted hd order

📦 Data Structures:
- TreeMap<Integer, Integer> → hd → node value
- Queue<Pair<Node, hd>> → BFS traversal

🕒 Time: O(N log N) → TreeMap insertion
🧠 Space: O(N)

🎯 Output: First (topmost) node from each vertical column
*/