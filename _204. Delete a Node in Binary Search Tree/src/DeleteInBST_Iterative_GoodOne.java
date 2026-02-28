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
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🧠 DRY RUN: Delete 3 from BST (Iteratively)

Original Tree:
              8
            /   \
           3     10
          / \      \
         1   6      14
            / \     /
           4   7   13

🎯 Delete key = 3
→ Found 3 under parent 8

✔ 3 has two children (1 and 6)
→ Inorder Successor = 4 (leftmost in right subtree of 3)
→ Replace 3 with 4

🌿 Now delete node 4 (which has no left child)

Resulting Tree:
              8
            /   \
           4     10
          / \      \
         1   6      14
              \     /
               7   13

✅ Inorder After: 1 4 6 7 8 10 13 14

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
📌 Deletion Cases Summary (Iterative):

1️⃣ No child → Just disconnect
2️⃣ One child → Link parent to child
3️⃣ Two children → Replace with inorder successor, then delete successor

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
⏱ TIME COMPLEXITY:
- Average: O(log N)
- Worst case (skewed tree): O(N)

📦 SPACE COMPLEXITY:
- O(1) → Fully iterative, no recursion stack

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
*/



 /*
🧠 INORDER SUCCESSOR in BST (Used in Deletion)

🎯 What is it?
- The inorder successor of a node is the node with the **smallest value greater than the current node**.
- It is the **next node in inorder traversal**.

📌 When do we need it?
- During **deletion** of a node with **two children**, we replace the node's value with its inorder successor.

🔍 How to find it?
→ If node has a right child:
   - Go to right subtree
   - Keep going left until null
   - That leftmost node is the inorder successor

🧾 Example:
        8
      /   \
     3     10
    / \      \
   1   6      14
      / \     /
     4   7   13

Delete node 3:
→ Go to right: 6
→ Go left: 4 (no more left)
✅ Inorder Successor = 4

💡 Code Snippet:
Node succ = node.right;
while (succ.left != null) {
    succ = succ.left;
}

⌛ Time Complexity: O(H) → height of tree
*/
