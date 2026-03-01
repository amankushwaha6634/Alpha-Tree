public class InsertInBST_Iterative {

    // 🌳 Binary Tree Node definition
    static class Node {
        int data;
        Node left, right;

        Node(int val) {
            this.data = val;
            this.left = this.right = null;
        }
    }

    // 🔁 Iterative function to insert a node into the BST
    public static Node insert(Node root, int key) {
        Node newNode = new Node(key); // 🧱 Create new node

        if (root == null) {
            // ⛳ If tree is empty, new node becomes root
            return newNode;
        }

        Node curr = root;
        Node parent = null;

        // 🔁 Traverse the tree to find insertion point
        while (curr != null) {
            parent = curr;

            if (key < curr.data) {
                curr = curr.left;
            } else if (key > curr.data) {
                curr = curr.right;
            } else {
                // ❗ Duplicate found → no insertion (standard BST)
                return root;
            }
        }

        // 📍 Insert newNode to the correct child of parent
        if (key < parent.data) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }

        return root;
    }

    // 🌿 Inorder traversal (to verify BST order)
    public static void inorder(Node root) {
        if (root == null) return;
        inorder(root.left);
        System.out.print(root.data + " ");
        inorder(root.right);
    }

    // 🧱 Build example BST:
    /*
                8
              /   \
             4     12
            / \   /  \
           2   6 10  14
    */
    public static Node buildTree() {
        Node root = new Node(8);
        root.left = new Node(4);
        root.right = new Node(12);
        root.left.left = new Node(2);
        root.left.right = new Node(6);
        root.right.left = new Node(10);
        root.right.right = new Node(14);
        return root;
    }

    public static void main(String[] args) {
        Node root = buildTree();
        int key = 5;

        root = insert(root, key); // 🌱 Insert key into BST

        System.out.print("Inorder after insertion: ");
        inorder(root); // ✅ Output: 2 4 5 6 8 10 12 14
    }
}

/*
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🧠 DRY RUN: Insert 5 into BST (Iterative)

Initial Tree:
            8
          /   \
         4     12
        / \   /  \
       2   6 10  14

📍 Step 1: root = 8
→ key = 5 < 8 → move to left

📍 Step 2: curr = 4
→ key = 5 > 4 → move to right

📍 Step 3: curr = 6
→ key = 5 < 6 → move to left

📍 Step 4: curr = null → insert 5 as left child of 6

🧱 Final Tree:
            8
          /   \
         4     12
        / \   /  \
       2   6 10  14
          /
         5

✅ Inorder Traversal → 2 4 5 6 8 10 12 14

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
⏱ TIME COMPLEXITY: O(H)
- H = height of tree
- Balanced BST: O(log N)
- Skewed BST  : O(N)

📦 SPACE COMPLEXITY: O(1)
- Purely iterative → no recursion stack

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
📌 NOTES:
- Standard BSTs do NOT allow duplicates
- If duplicates must be inserted:
  → Define whether to place them on left/right consistently
*/

/*
🧠 SHORT NOTES: Insert in BST (Iterative)

📌 Goal:
Insert a new value into a Binary Search Tree while maintaining BST property.

📘 BST Property:
Left subtree < Node < Right subtree

🛠️ Steps:
1. Create a new node with given key
2. If root is null → return new node (tree was empty)
3. Start from root and traverse iteratively:
   - If key < curr.data → move left
   - If key > curr.data → move right
   - If key == curr.data → duplicate → do not insert
4. When curr becomes null:
   - Insert new node as child of last parent

📦 Data Structures:
- No extra data structure
- Only pointers (curr, parent)

🕒 Time Complexity: O(H)
→ H = height of tree
- Balanced BST → O(log N)
- Skewed BST → O(N)

🧠 Space Complexity: O(1)
→ Iterative approach, no recursion

🎯 Output:
BST remains valid and inorder traversal stays sorted.
*/