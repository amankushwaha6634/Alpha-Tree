import java.util.*;

public class BottomView {

    // 🔽 Function to get Bottom View of Binary Tree
    public static List<Integer> bottomView(Node root) {

        // 📌 Edge case
        if (root == null) return new ArrayList<>();

        // TreeMap: hd → last node at that HD (bottommost)
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

            // ⭐ Always update → last node at this HD will remain (bottommost)
            map.put(hd, node.data);

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

        Bottom View:
        4 2 6 8 7
        */

        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);
        root.right.right.left = new Node(8);

        List<Integer> output = bottomView(root);

        System.out.println("Bottom View:");
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

    // Pair class
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
🧪 DRY RUN: Bottom View of Binary Tree (BFS with horizontal distance)

Example Tree:
             1
           /   \
         2       3
        / \     / \
       4   5   6   7
                  /
                 8

📌 Rule:
For each Horizontal Distance (HD), keep the LAST node encountered
(Because it will be the bottommost node).

▶ Initial Setup:
- TreeMap: hd → last node at that horizontal distance
- Queue: stores Pair(node, hd)

📦 Start with:
Queue = [(1, 0)]
Map = {}

🔁 Step-by-step Traversal:

1️⃣ Pop (1, 0)
   ➤ map = {0: 1}
   ➤ Add (2, -1), (3, 1)

2️⃣ Pop (2, -1)
   ➤ map = {-1: 2, 0: 1}
   ➤ Add (4, -2), (5, 0)

3️⃣ Pop (3, 1)
   ➤ map = {-1: 2, 0: 1, 1: 3}
   ➤ Add (6, 0), (7, 2)

4️⃣ Pop (4, -2)
   ➤ map = {-2: 4, -1: 2, 0: 1, 1: 3}

5️⃣ Pop (5, 0)
   ➤ overwrite hd 0 → map = {..., 0: 5}

6️⃣ Pop (6, 0)
   ➤ overwrite hd 0 → map = {..., 0: 6}

7️⃣ Pop (7, 2)
   ➤ map = {..., 2: 7}
   ➤ Add (8, 1)

8️⃣ Pop (8, 1)
   ➤ overwrite hd 1 → map = {..., 1: 8}

📤 Final TreeMap (Bottom View):
- hd -2 → 4
- hd -1 → 2
- hd  0 → 6
- hd  1 → 8
- hd  2 → 7

🎯 Final Output (Left → Right):
4 2 6 8 7
*/


/*
🧠 SHORT NOTES: Bottom View (BFS + Map)

📌 Goal:
Print nodes visible when the tree is viewed from the BOTTOM.

📘 Concept:
- Use BFS (level order)
- Track horizontal distance (hd)
- For each hd, keep updating the node value
  → Last value stored will be the bottommost node

🛠️ Steps:
1. Use Queue<Pair<Node, hd>>
2. Start with root at hd = 0
3. While queue is not empty:
   - Pop node
   - map.put(hd, node.data)  // always overwrite
   - Add left (hd - 1)
   - Add right (hd + 1)
4. Print TreeMap values in sorted hd order

📦 Data Structures:
- TreeMap<Integer, Integer> → hd → node value
- Queue<Pair<Node, hd>> → BFS

🕒 Time: O(N log N)
🧠 Space: O(N)

🎯 Output: Last (bottommost) node from each vertical column
*/