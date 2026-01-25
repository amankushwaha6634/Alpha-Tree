import java.util.*;

public class BFS_Level_Order_Traversal_Vector2 {

    // 🌿 Node class representing each element in the binary tree
    static class Node {
        int data;        // Value stored in the node
        Node left;       // Reference to left child
        Node right;      // Reference to right child

        // 🧱 Constructor to initialize node with data
        Node(int data) {
            this.data = data;
            this.left = null;   // Initially no left child
            this.right = null;  // Initially no right child
        }
    }

    // 🌳 BinaryTree class containing tree-related operations
    static class BinaryTree {

        // 🔢 Static index used to track position in preorder array
        static int idx = -1;

        /*
        =====================================================
        🏗️ BUILD TREE FROM PREORDER ARRAY
        =====================================================
        - Input array is in preorder format: Root → Left → Right
        - -1 represents a null node
        - Uses recursion to construct tree
        */
        public static Node buildTree(int[] nodes) {

            idx++; // Move to next index in preorder array

            // 🛑 If current value is -1, this node is null
            if (nodes[idx] == -1) {
                return null;
            }

            // 🌱 Create a new node with current value
            Node newNode = new Node(nodes[idx]);

            // 🔽 Recursively build left subtree
            newNode.left = buildTree(nodes);

            // 🔼 Recursively build right subtree
            newNode.right = buildTree(nodes);

            // ↩️ Return this node to its parent call
            return newNode;
        }

        /*
        =====================================================
        🌐 LEVEL ORDER TRAVERSAL (BFS)
        =====================================================
        - Traverses the tree level by level
        - Uses Queue for BFS
        - Uses queue size to identify each level
        - Stores each level in a separate vector (List<Integer>)
        - Final result stored as List<List<Integer>>
        */
        public static List<List<Integer>> levelOrder(Node root) {

            // 📦 This will store all levels
            List<List<Integer>> result = new ArrayList<>();

            // 🛑 Edge case: empty tree
            if (root == null) return result;

            // 🧺 Queue used for BFS traversal
            Queue<Node> q = new LinkedList<>();

            // 🚀 Start BFS by adding root node
            q.add(root);

            // 🔁 Continue until all nodes are processed
            while (!q.isEmpty()) {

                // 🔢 Number of nodes at current level
                int levelSize = q.size();

                // 📥 Vector to store current level nodes
                List<Integer> currentLevel = new ArrayList<>();

                // 🚶 Process all nodes of current level
                for (int i = 0; i < levelSize; i++) {

                    // 🚶 Remove front node from queue
                    Node curr = q.remove();

                    // 🖨️ Store node value in current level
                    currentLevel.add(curr.data);

                    // 👶 Add left child to queue (if exists)
                    if (curr.left != null) {
                        q.add(curr.left);
                    }

                    // 👶 Add right child to queue (if exists)
                    if (curr.right != null) {
                        q.add(curr.right);
                    }
                }

                // 📦 Add current level vector to final result
                result.add(currentLevel);
            }

            return result;
        }
    }

    // 🚀 Main method
    public static void main(String[] args) {

        /*
        =====================================================
        🌱 PREORDER ARRAY USED TO BUILD TREE
        =====================================================
        - -1 represents null node
        - Format: Root → Left → Right
        */
        int[] preorder = {
                1,
                2,
                4, -1, -1,
                5, -1, -1,
                3, -1,
                6, -1, -1
        };

        // 🛠️ Step 1: Build binary tree
        Node root = BinaryTree.buildTree(preorder);

        // 🧪 Step 2: Perform level order traversal
        List<List<Integer>> levels = BinaryTree.levelOrder(root);

        // 🖨️ Step 3: Print traversal result
        System.out.println("Level Order Traversal (each level in vector):");
        System.out.println(levels);
    }
}


/*
=====================================================
👀 DRY RUN – LEVEL ORDER TRAVERSAL (BFS)
=====================================================

Preorder array:
{1, 2, 4, -1, -1, 5, -1, -1, 3, -1, 6, -1, -1}

✅ Constructed Binary Tree:

                   🔵1
                 /     \
              🔵2       🔵3
             /   \         \
           🔵4   🔵5       🔵6

=====================================================
🧺 BFS USING QUEUE + LEVEL SIZE
=====================================================

Initial Queue:
[1]

Level 0:
- levelSize = 1
- Process node: 1
- currentLevel = [1]
- Add children → 2, 3
- Queue = [2, 3]
→ result = [[1]]

Level 1:
- levelSize = 2
- Process nodes: 2, 3
- currentLevel = [2, 3]
- Add children → 4, 5, 6
- Queue = [4, 5, 6]
→ result = [[1], [2, 3]]

Level 2:
- levelSize = 3
- Process nodes: 4, 5, 6
- currentLevel = [4, 5, 6]
- No children to add
- Queue becomes empty
→ result = [[1], [2, 3], [4, 5, 6]]

=====================================================
🖨️ FINAL OUTPUT:
[[1], [2, 3], [4, 5, 6]]
=====================================================
*/
