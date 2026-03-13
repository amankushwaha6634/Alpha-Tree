import java.util.*;

public class SerializeDeserializeBinaryTree {

    // 🌳 Node class
    static class Node {
        int val;
        Node left, right;

        Node(int x) {
            val = x;
        }
    }

// =========================================================
// 🔐 SERIALIZE: Convert Binary Tree → String (Level Order BFS)
// =========================================================
    public static String serialize(Node root) {

        // 🛑 If tree is empty, return empty string
        if (root == null) return "";

        // 📦 Queue for Level Order Traversal (BFS)
        Queue<Node> queue = new LinkedList<>();

        // 🧵 StringBuilder to efficiently build the serialized string
        StringBuilder sb = new StringBuilder();

        // 🌱 Start BFS from root
        queue.offer(root);

        // 🔁 Continue until all levels are processed
        while (!queue.isEmpty()) {

            // 📏 Number of nodes at current level
            // (Used to process tree level-by-level)
            int levelSize = queue.size();

            // Process all nodes in this level
            for (int i = 0; i < levelSize; i++) {

                // 🟢 Remove current node from queue
                Node node = queue.poll();

                // ❗ If node is null:
                // We must store it explicitly to preserve tree structure
                if (node == null) {
                    sb.append("null,");
                    continue; // No children to add
                }

                // ✅ Append current node value
                sb.append(node.val).append(",");

                // 📥 Add left child (can be null)
                queue.offer(node.left);

                // 📤 Add right child (can be null)
                queue.offer(node.right);
            }
        }

        // 🧹 Remove the last extra comma
        sb.setLength(sb.length() - 1);

        // 🔁 Return final serialized string
        return sb.toString();
    }

/*
 Serialization:
---------------
- Traverse level by level
- Append value
- If node is null → append "null"
- Push left & right for each node

🧪 DRY RUN: Serialization
------------------------
Tree:
        1
       / \
      2   3
         / \
        4   5

Level 0: [1]
Result: 1,

Level 1: [2,3]
Result: 1,2,3,

Level 2: [null,null,4,5]
Result: 1,2,3,null,null,4,5,

Level 3: [null,null,null,null]
Result:
1,2,3,null,null,4,5,null,null,null,null

Final Serialized String:
"1,2,3,null,null,4,5,null,null,null,null"
*/


// =========================================================
// 🔓 DESERIALIZE: Convert String → Binary Tree (Level Order BFS)
// =========================================================
    public static Node deserialize(String data) {

        // 🛑 If input string is empty → tree is empty
        if (data.isEmpty()) return null;

        // 🔍 Split string into individual values
        String[] values = data.split(",");  // [ 1,2,3,null,null,4,5,null,null,null,null ]

        // 🌱 First value represents the root node
        //        1
        //       / \
        Node root = new Node(Integer.parseInt(values[0]));

        // 📦 Queue for level-order reconstruction
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);  // [ 1 ]

        // 📌 Index to traverse the values array
        int i = 1;

        // 🔁 Rebuild the tree level by level
        while (!queue.isEmpty() && i < values.length) {

            // 📏 Number of parent nodes at current level
            int levelSize = queue.size();

            // Process each parent in this level
            for (int j = 0; j < levelSize && i < values.length; j++) {

                // 🟢 Current parent node
                Node parent = queue.poll();

                // -------------------------------------------------
                // 👈 Create LEFT child
                // -------------------------------------------------
                // If value is not "null", create node and attach
                if (!values[i].equals("null")) {
                    parent.left = new Node(Integer.parseInt(values[i]));
                    queue.offer(parent.left); // Add for future processing
                }
                i++; // Move to next value

                // -------------------------------------------------
                // 👉 Create RIGHT child
                // -------------------------------------------------
                // Check boundary and ensure value is not "null"
                if (i < values.length && !values[i].equals("null")) {
                    parent.right = new Node(Integer.parseInt(values[i]));
                    queue.offer(parent.right); // Add for future processing
                }
                i++; // Move to next value
            }
        }

        // 🌳 Return reconstructed tree root
        return root;
    }


/*
Deserialization:
---------------
- Read values sequentially
- Rebuild tree level by level
- Each parent gets two children

🧪 DRY RUN: Deserialization
---------------------------
Input:
1,2,3,null,null,4,5,null,null,null,null

Step 1:
Create root = 1
Queue = [1]

Step 2:
Parent = 1
→ left = 2
→ right = 3
Queue = [2,3]

Step 3:
Parent = 2
→ left = null
→ right = null

Step 4:
Parent = 3
→ left = 4
→ right = 5

Final Tree:
        1
       / \
      2   3
         / \
        4   5
*/


    // 🖨️ Inorder (verification)
    public static void printInorder(Node root) {
        if (root == null) return;
        printInorder(root.left);
        System.out.print(root.val + " ");
        printInorder(root.right);
    }

    // =========================================================
    // 🧪 MAIN
    // =========================================================
    public static void main(String[] args) {

        /*
                1
               / \
              2   3
                 / \
                4   5
        */

        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.right.left = new Node(4);
        root.right.right = new Node(5);

        // Serialize
        String serialized = serialize(root);
        System.out.println("Serialized: " + serialized);

        // Deserialize
        Node newRoot = deserialize(serialized);

        System.out.print("Inorder after deserialization: ");
        printInorder(newRoot);
    }
}

/*
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🧠 SHORT NOTES: Serialize & Deserialize (Level Order)

📌 Goal:
Convert Binary Tree ↔ String using BFS.

📘 Concept:
Use Level Order Traversal and explicitly store nulls.

💡 Why levelSize?
→ Keeps traversal strictly level-wise
→ Same BFS pattern used in:
   - Burn Tree
   - Distance K
   - Level Order Traversal

📦 Data Structures:
- Queue<Node>

🕒 Time Complexity: O(N)
🧠 Space Complexity: O(N)
*/







