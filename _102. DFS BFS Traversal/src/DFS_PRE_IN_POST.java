public class DFS_PRE_IN_POST {

    // 🌿 Node class representing each element in the binary tree
    static class Node {
        int data;
        Node left;
        Node right;

        // 🧱 Constructor to initialize node data
        Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    static class BinaryTree {
        // 🔢 Static index for tracking position in preorder array
        static int idx = -1;

        // 🏗️ Function to build tree from preorder array where -1 represents null
        public static Node buildTree(int[] nodes) {
            idx++;  // Move to next element

            // 🛑 If current value is -1, return null (empty node)
            if (nodes[idx] == -1) {
                return null;
            }

            // ✅ Create a new node
            Node newNode = new Node(nodes[idx]);

            // 🔽 Recursively build left subtree
            newNode.left = buildTree(nodes);

            // 🔼 Recursively build right subtree
            newNode.right = buildTree(nodes);

            return newNode;  // Return this node back to its parent
        }

        // 🔁 Preorder Traversal: Root → Left → Right
        public static void preorder(Node root) {
            if (root == null) return;
            System.out.print(root.data + " ");
            preorder(root.left);
            preorder(root.right);
        }

        // 🔁 Inorder Traversal: Left → Root → Right
        public static void inorder(Node root) {
            if (root == null) return;
            inorder(root.left);
            System.out.print(root.data + " ");
            inorder(root.right);
        }

        // 🔁 Postorder Traversal: Left → Right → Root
        public static void postorder(Node root) {
            if (root == null) return;
            postorder(root.left);
            postorder(root.right);
            System.out.print(root.data + " ");
        }
    }

    public static void main(String[] args) {
        // 🌲 Preorder array (Root, Left, Right) with -1 as null
        int[] preorder = {
                1,
                2,
                4, -1, -1,
                5, -1, -1,
                3, -1,
                6, -1, -1
        };

        // 🛠️ Build the binary tree from the array
        Node root = BinaryTree.buildTree(preorder);

        // 🖨️ Traversal outputs
        System.out.print("Preorder Traversal: ");
        BinaryTree.preorder(root);    // ➡️ Output: 1 2 4 5 3 6
        System.out.println();

        System.out.print("Inorder Traversal: ");
        BinaryTree.inorder(root);     // ➡️ Output: 4 2 5 1 3 6
        System.out.println();

        System.out.print("Postorder Traversal: ");
        BinaryTree.postorder(root);   // ➡️ Output: 4 5 2 6 3 1
    }
}


/*
👀 DRY RUN of buildTree() with preorder input:
   int[] preorder = {1, 2, 4, -1, -1, 5, -1, -1, 3, -1, 6, -1, -1};

🔄 Preorder format: Root → Left → Right
🔢 -1 means null (❌), otherwise node (🔵)

Step-by-step Recursive Construction:

✅ Final Tree:

               🔵1
             /      \
         🔵2          🔵3
       /     \           \
    🔵4     🔵5         🔵6
   /  \     /  \        /  \
 ❌  ❌   ❌  ❌     ❌  ❌

Legend:
🔵 = Node
❌ = null (from -1)
?  = yet to be processed

➡️ Output: 1 2 4 5 3 6
➡️ Output: 4 2 5 1 3 6
➡️ Output: 4 5 2 6 3 1
*/


/*
=====================================================
❓ WHY preorder / inorder / postorder METHODS ARE STATIC
=====================================================

Reason 1️⃣:
- These traversal methods do NOT depend on any instance
  variables of the BinaryTree class.
- They only work on the Node reference passed as a parameter.

Example:
    preorder(Node root)
→ uses only:
  - root.data
  - root.left
  - root.right

Reason 2️⃣:
- Since no object-specific data is required, creating a
  BinaryTree object would be unnecessary.

Without static:
    BinaryTree tree = new BinaryTree();
    tree.preorder(root);     ❌ unnecessary object creation

With static:
    BinaryTree.preorder(root);   ✅ clean and efficient

Reason 3️⃣:
- In DSA, traversal methods are utility/helper functions.
- Utility functions should be static because they belong
  to the class logic, not to any particular object.

Reason 4️⃣:
- The tree structure is stored in Node objects, not inside
  the BinaryTree class itself.
- So traversal logic naturally operates on Node references.

📌 Interview one-liner:
“Traversal methods are static because they don’t rely on any
BinaryTree object state; they operate purely on Node references.”

=====================================================
*/

