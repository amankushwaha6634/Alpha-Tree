public class DeleteInBST_Iterative_GoodOne {

    // 🌳 BST Node structure
    static class Node {
        int data;          // Value stored in node
        Node left, right;  // References to left and right child

        Node(int val) {
            this.data = val;
            left = right = null;
        }
    }

    // 🔧 Iterative delete function in BST
    public static Node deleteNode(Node root, int key) {

        Node parent = null;   // Will store parent of node to delete
        Node curr = root;     // Start searching from root

        // 🔍 Step 1: Search for the node with given key
        // Also keep track of its parent
        while (curr != null && curr.data != key) {
            parent = curr;

            // Move left or right using BST property
            if (key < curr.data)
                curr = curr.left;
            else
                curr = curr.right;
        }

        // ❌ Key not found → no deletion needed
        if (curr == null) return root;

        // =====================================================
        // Case 1: Node is a LEAF (no children)
        // =====================================================
        if (curr.left == null && curr.right == null) {

            // If the node to delete is root
            if (curr == root) return null;

            // Disconnect node from parent
            if (parent.left == curr)
                parent.left = null;
            else
                parent.right = null;
        }

        // =====================================================
        // Case 2: Node has ONLY ONE child
        // =====================================================
        else if (curr.left == null || curr.right == null) {

            // Identify the existing child
            Node child = (curr.left != null) ? curr.left : curr.right;

            // If deleting root, return child as new root
            if (curr == root) return child;

            // Connect parent directly to child (bypass curr)
            if (parent.left == curr)
                parent.left = child;
            else
                parent.right = child;
        }

        // =====================================================
        // Case 3: Node has TWO children
        // Approach:
        // Replace node with its left subtree
        // Attach original right subtree to the rightmost node of left subtree
        // =====================================================
        else {

            // Step 1: Save left subtree
            Node leftSub = curr.left;

            // Step 2: Find the rightmost node in left subtree
            // (This is the maximum element of left subtree)
            Node rightMost = leftSub;
            while (rightMost.right != null) {
                rightMost = rightMost.right;
            }

            // Step 3: Attach the original right subtree
            // to the rightmost node of left subtree
            rightMost.right = curr.right;

            // Step 4: Replace curr with left subtree
            if (curr == root) return leftSub;

            if (parent.left == curr)
                parent.left = leftSub;
            else
                parent.right = leftSub;
        }

        return root;  // Return updated root
    }

    // 🧾 Inorder Traversal (prints BST in sorted order)
    public static void inorder(Node root) {
        if (root == null) return;
        inorder(root.left);
        System.out.print(root.data + " ");
        inorder(root.right);
    }

    // 🔨 Build sample BST
    /*
              8
            /   \
           3     10
          / \      \
         1   6      14
            / \     /
           4   7   13
    */
    public static Node buildTree() {
        Node root = new Node(8);
        root.left = new Node(3);
        root.right = new Node(10);
        root.left.left = new Node(1);
        root.left.right = new Node(6);
        root.left.right.left = new Node(4);
        root.left.right.right = new Node(7);
        root.right.right = new Node(14);
        root.right.right.left = new Node(13);
        return root;
    }

    public static void main(String[] args) {
        Node root = buildTree();

        System.out.print("Inorder before deletion: ");
        inorder(root);

        int key = 3;
        root = deleteNode(root, key);  // Delete node with value 3

        System.out.println("\nInorder after deleting " + key + ": ");
        inorder(root);
    }
}
/*
🧪 DRY RUN: Delete Node = 3

Original Tree:
              8
            /   \
           3     10
          / \      \
         1   6      14
            / \     /
           4   7   13

-------------------------------------------------

1️⃣ Search Node
curr = 3
parent = 8

Node 3 has two children → Case 4
-------------------------------------------------

2️⃣ Left Subtree of 3:
        1
         \
          (no right)

Rightmost of left subtree = 1

-------------------------------------------------

3️⃣ Attach Right Subtree of 3 to 1 (as its rightmost node of left subtree)
Right subtree = 6

After attaching:
        1
         \
          6
         / \
        4   7

-------------------------------------------------

4️⃣ Replace 3 with left subtree

New Tree:
              8
            /   \
           1     10
            \      \
             6      14
            / \     /
           4   7   13

-------------------------------------------------

📤 Final Inorder:
1 4 6 7 8 10 13 14

BST property maintained ✅
*/

/*
🧠 SHORT NOTES: Delete Node in BST (Iterative – Attach Right to Left)

📌 Goal:
Delete a node from a Binary Search Tree while maintaining BST property.

📘 BST Property:
Left subtree < Node < Right subtree

📘 Deletion Cases:

1️⃣ Node not found
→ Return original root

2️⃣ Node is a leaf
→ Simply remove the node

3️⃣ Node has one child
→ Connect parent directly to the child

4️⃣ Node has two children
→ Instead of inorder successor:
   - Take left subtree
   - Find its rightmost node (maximum)
   - Attach original right subtree to it
   - Replace node with left subtree

🛠️ Steps:
1. Search the node iteratively and track parent
2. Handle leaf case
3. Handle single child case
4. If two children:
   a. leftSub = curr.left
   b. Find rightMost in leftSub
   c. rightMost.right = curr.right
   d. Replace curr with leftSub

📦 Data Structures:
- No extra DS
- Pure pointer manipulation

🕒 Time Complexity: O(H)
→ H = height of tree

🧠 Space Complexity: O(1)
→ Fully iterative (no recursion)

🎯 Output:
BST remains valid after deletion
*/