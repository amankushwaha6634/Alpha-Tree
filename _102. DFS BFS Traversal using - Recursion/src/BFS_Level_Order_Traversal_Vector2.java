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
            // Initial state : result = [ ]

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
👀 DETAILED DRY RUN – LEVEL ORDER TRAVERSAL (BFS)
=====================================================

Preorder array used to build the tree:
{1, 2, 4, -1, -1, 5, -1, -1, 3, -1, 6, -1, -1}

After buildTree(), the tree looks like:

                   1
                 /   \
               2       3
             /   \       \
            4     5       6

-----------------------------------------------------
📌 DATA STRUCTURES USED
-----------------------------------------------------
Queue<Node> q          → stores nodes to be processed
List<List<Integer>> result → stores nodes level by level

-----------------------------------------------------
🚀 INITIAL STATE
-----------------------------------------------------
q = [1]
result = [ ]

=====================================================
LEVEL 0 (Root Level)
=====================================================

Step 1:
- q.size() = 1 → levelSize = 1
- Means: there is ONLY 1 node at this level

Step 2:
- Create empty list for this level
  currentLevel = [ ]

Step 3:
- Remove node from queue → 1
- Add node value to currentLevel
  currentLevel = [1]

Step 4:
- Add children of node 1 to queue
  left child → 2
  right child → 3

Queue after adding children:
q = [2, 3]

Step 5:
- Add currentLevel to result

result = [
  [1]
]

=====================================================
LEVEL 1
=====================================================

Step 1:
- q.size() = 2 → levelSize = 2
- Means: there are 2 nodes at this level

Step 2:
- Create empty list for this level
  currentLevel = [ ]

Step 3:
- Remove node → 2
- Add to currentLevel
  currentLevel = [2]
- Add children of 2 → 4 and 5

Queue now:
q = [3, 4, 5]

Step 4:
- Remove next node → 3
- Add to currentLevel
  currentLevel = [2, 3]
- Add children of 3 → only right child 6

Queue now:
q = [4, 5, 6]

Step 5:
- Add currentLevel to result

result = [
  [1],
  [2, 3]
]

=====================================================
LEVEL 2
=====================================================

Step 1:
- q.size() = 3 → levelSize = 3
- Means: there are 3 nodes at this level

Step 2:
- Create empty list for this level
  currentLevel = [ ]

Step 3:
- Remove node → 4
- Add to currentLevel → [4]
- Node 4 has no children

Step 4:
- Remove node → 5
- Add to currentLevel → [4, 5]
- Node 5 has no children

Step 5:
- Remove node → 6
- Add to currentLevel → [4, 5, 6]
- Node 6 has no children

Queue after processing all nodes:
q = [ ]

Step 6:
- Add currentLevel to result

result = [
  [1],
  [2, 3],
  [4, 5, 6]
]

=====================================================
🖨️ FINAL RESULT
=====================================================

result = [
  [1],
  [2, 3],
  [4, 5, 6]
]

📌 KEY IDEA TO REMEMBER:
- Queue size tells how many nodes belong to ONE level
- currentLevel stores nodes of that level
- result stores all levels together
=====================================================
*/


/*
=====================================================
💾 SPACE COMPLEXITY OF BFS IN COMPLETE / PERFECT BT
=====================================================

Key idea:
- BFS space depends on the maximum width of the tree
- Width = maximum number of nodes at any level

Perfect Binary Tree:
- Last level contains about n/2 nodes
- Queue may store all these nodes at once

Complete Binary Tree:
- Last level is almost full
- Still Θ(n/2) nodes in worst case

Therefore:
Auxiliary Space (queue) = O(n)

📌 Interview one-liner:
“In complete or perfect binary trees, BFS uses O(n)
space because the queue can hold nodes of the widest level.”
=====================================================
*/
