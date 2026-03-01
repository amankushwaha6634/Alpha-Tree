public class KthSmallestInBST {

    // 🌳 Tree Node definition
    static class Node {
        int data;        // Value stored in node
        Node left, right; // Left and right child references

        Node(int val) {
            this.data = val;
            this.left = this.right = null;
        }
    }

    // 🧮 Global counter to track number of visited nodes during inorder
    static int count = 0;

    // 🔍 Function to find Kth smallest element
    public static int kthSmallest(Node root, int k) {
        count = 0;               // Reset counter for each function call
        return inorder(root, k); // Perform inorder traversal
    }

    // 🔁 Inorder Traversal (Left → Node → Right)
    private static int inorder(Node node, int k) {

        // Base case: reached null node
        if (node == null) return -1;

        // 1️⃣ Traverse left subtree first (smaller elements)
        int left = inorder(node.left, k);

        // If kth element already found in left subtree, return it immediately
        if (left != -1) return left;

        // 2️⃣ Visit current node
        count++;  // Increase visited node count

        // If this is the kth visited node → this is the answer
        if (count == k) return node.data;

        // 3️⃣ Traverse right subtree (larger elements)
        return inorder(node.right, k);
    }

    // 🔧 Build example BST
    /*
                5
              /   \
             3     7
            / \   / \
           2   4 6   8
    */
    public static Node buildTestTree() {
        Node root = new Node(5);
        root.left = new Node(3);
        root.right = new Node(7);
        root.left.left = new Node(2);
        root.left.right = new Node(4);
        root.right.left = new Node(6);
        root.right.right = new Node(8);
        return root;
    }

    public static void main(String[] args) {
        Node root = buildTestTree();
        int k = 3;

        // Find kth smallest element
        int ans = kthSmallest(root, k);

        System.out.println("Kth Smallest Element = " + ans);
    }
}

/*
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
⚠️ IMPORTANT: Why this line is needed?

if (left != -1) return left;

Key Idea:
This condition is NOT triggered at the node where the answer is found.
It works when recursion returns back to the ancestor nodes.

It helps in EARLY TERMINATION so that the remaining tree is not traversed.

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🧪 Example: Find k = 3

Tree:
        5
       / \
      3   7
     / \
    2   4

Inorder sequence → 2, 3, 4, 5, 6, 7, 8
Answer = 4

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
Execution Flow (Call Stack)

Step 1:
inorder(5)
→ calls inorder(3)

Step 2:
inorder(3)
→ calls inorder(2)

Step 3:
inorder(2)
left = -1
visit → count = 1
right = -1
return -1

Back to node 3
left = -1 → condition NOT triggered

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
Step 4:
Visit node 3 → count = 2
Call inorder(4)

Step 5:
inorder(4)
left = -1
visit → count = 3 ✅ FOUND
return 4

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
Now recursion starts returning upward

Back to node 3:
right call returned 4
→ node 3 returns 4

Back to node 5:
left = 4

Now this line executes:

if (left != -1) return left;   // ✅ TRIGGERS HERE

Effect:
Node 5 will NOT:
- Visit itself
- Traverse right subtree (7, 8)

Traversal stops early.

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
💡 Without this check:
After finding 4, traversal would still visit:
5 → 6 → 7 → 8  (unnecessary work)

With this check:
Time complexity improves to O(H + K)

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
📌 Summary:
- Answer found at node 4
- But early stop happens when recursion returns to its ancestors
- This prevents extra traversal
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
*/

/*
📊 TIME & SPACE COMPLEXITY

⏱ Time: O(H + K)
→ Worst case: Must explore up to height H + k nodes

📦 Space:
- Recursive: O(H) stack space
- Iterative: O(H) with stack

🎯 Bonus: Can also be done iteratively with explicit stack if needed
*/


// 🧠 ALGORITHM: K-th Smallest in BST
/*
💡 KEY IDEA:
- Inorder traversal of a Binary Search Tree (BST) always gives elements in **sorted order**.
- So, the K-th smallest element is just the **K-th node visited** in an inorder traversal.

📌 ALGORITHM STEPS:
1. Perform **inorder traversal** (Left → Node → Right).
2. Keep a global `count` variable to track how many nodes you've visited.
3. When `count == k`, that node is your answer.

📦 WHY IT WORKS:
- Because of BST property: left < root < right
- Inorder traversal visits nodes in increasing order
- So, the K-th node you visit is the K-th smallest

🎯 Example:
For BST → [2, 3, 4, 5, 6, 7, 8]
→ 3rd smallest = 4

🕒 TIME COMPLEXITY:
- Best case: O(H + K)
- H = height of BST (log N for balanced)
- Worst case: O(N) if unbalanced  | But still we are eliminating 0(NLogN) like other sorting algorithm

📦 SPACE COMPLEXITY:
- O(H) due to recursion (or O(H) with stack if iterative)
*/
