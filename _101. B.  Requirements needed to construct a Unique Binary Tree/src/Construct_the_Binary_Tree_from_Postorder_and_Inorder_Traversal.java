import java.util.*;

public class Construct_the_Binary_Tree_from_Postorder_and_Inorder_Traversal  {
    // 🌳 Node structure
    static class Node {
        int data;
        Node left, right;
        Node(int val) {
            this.data = val;
        }
    }

    // ⏳ Global index for postorder array (starts from end)
    static int postIndex;

    public static Node buildTree(int[] inorder, int[] postorder) {
        postIndex = postorder.length - 1; // 4

        // 🗺️ Map to store inorder value -> index for O(1) lookup
        Map<Integer, Integer> inMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inMap.put(inorder[i], i);
        }

        /*
        ➡ Postorder: [9, 15, 7, 20, 3] → (Left, Right, Root)
        ➡ Inorder:   [9, 3, 15, 20, 7] → (Left, Root, Right)

        - `inorderMap` = {9:0, 3:1, 15:2, 20:3, 7:4}  */

        return build(postorder, 0, inorder.length - 1, inMap);
    }

    private static Node build(int[] postorder, int inStart, int inEnd, Map<Integer, Integer> inMap) {
        // ⛔ Base case
        if (inStart > inEnd) return null;

        // 🎯 Current root from postorder
        int rootVal = postorder[postIndex--];
        Node root = new Node(rootVal);

        // 📍 Get index of root in inorder
        int inIndex = inMap.get(rootVal);

        // ⚠️ Build right subtree first (postorder goes left-right-root, so we go reverse)
        root.right = build(postorder, inIndex + 1, inEnd, inMap);
        root.left = build(postorder, inStart, inIndex - 1, inMap);

        return root;
    }

    // 🧪 Inorder traversal to verify structure
    public static void printInorder(Node root) {
        if (root == null) return;
        printInorder(root.left);
        System.out.print(root.data + " ");
        printInorder(root.right);
    }

    public static void main(String[] args) {
        int[] inorder =  {9, 3, 15, 20, 7};
        int[] postorder = {9, 15, 7, 20, 3};

        Node root = buildTree(inorder, postorder);

        System.out.print("Inorder of constructed tree: ");
        printInorder(root);  // Expected: 9 3 15 20 7
    }
}

/*
🧠 DRY RUN: Build Binary Tree from Postorder + Inorder

Given:
➡ Postorder: [9, 15, 7, 20, 3] → (Left, Right, Root)
➡ Inorder:   [9, 3, 15, 20, 7] → (Left, Root, Right)

📦 Supporting Data Structures:
- `postIndex` = 4 (starts from last element of postorder)
- `inorderMap` = {9:0, 3:1, 15:2, 20:3, 7:4}
- Recursively build tree using inorder boundaries

⚠ Important:
Since Postorder is (Left → Right → Root),
we traverse it **in reverse** → (Root → Right → Left)

So recursion order becomes:
1️⃣ Build RIGHT subtree
2️⃣ Build LEFT subtree

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🔹 Step 1: build(0, 4)

postIndex = 4 → postorder[4] = 3 → 🪵 root = 3
inorderIndex(3) = 1

↙ Left Inorder: inorder[0 to 0] → [9]
↘ Right Inorder: inorder[2 to 4] → [15, 20, 7]

🌳 Tree so far:
        3

Data snapshot:
- postIndex → 3
- Build RIGHT subtree first

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🔹 Step 2: build(2, 4)

postIndex = 3 → postorder[3] = 20 → 🪵 root = 20
inorderIndex(20) = 3

↙ Left Inorder: inorder[2 to 2] → [15]
↘ Right Inorder: inorder[4 to 4] → [7]

🌳 Tree so far:
        3
         \
         20

Data snapshot:
- postIndex → 2
- Build RIGHT subtree first

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🔹 Step 3: build(4, 4)

postIndex = 2 → postorder[2] = 7 → 🪵 root = 7
inorderIndex(7) = 4

🛑 No children

🌳 Tree so far:
        3
         \
         20
            \
             7

Data snapshot:
- postIndex → 1
- Build LEFT subtree of 20

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🔹 Step 4: build(2, 2)

postIndex = 1 → postorder[1] = 15 → 🪵 root = 15
inorderIndex(15) = 2

🛑 No children

🌳 Tree so far:
        3
         \
         20
        /  \
       15   7

Data snapshot:
- postIndex → 0
- Build LEFT subtree of 3

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🔹 Step 5: build(0, 0)

postIndex = 0 → postorder[0] = 9 → 🪵 root = 9
inorderIndex(9) = 0

🛑 No children

🌳 Final Tree:
        3
       / \
      9   20
          / \
        15   7

Data snapshot:
- postIndex → -1 (all nodes processed)

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
✅ FINAL OUTPUT:
Reconstructed Binary Tree:
        3
       / \
      9   20
          / \
        15   7

📈 Time Complexity: O(N)
→ Each node processed once

📦 Space Complexity: O(N)
→ HashMap + recursion stack
*/


/**
 * ⏱️ Time Complexity: O(N)
 *
 * Explanation:
 * - Each node from the postorder array is processed exactly once.
 * - For every node:
 *      1. Create Node object → O(1)
 *      2. Lookup its index in inorder using HashMap → O(1)
 *      3. Recursively construct left and right subtrees
 *
 * Since there are N nodes:
 *
 *      Time Complexity = O(N)
 *
 * -------------------------------------------------------
 *
 * 📦 Space Complexity: O(N)
 *
 * Contributors:
 *
 * 1️⃣ HashMap
 * - Stores inorder value → index
 * - Size = N
 *
 * 2️⃣ Recursion Stack
 * - Worst case (skewed tree) recursion depth = N
 *
 * Example skewed tree:
 * 1
 *  \
 *   2
 *    \
 *     3
 *
 * Total Space Complexity:
 *
 *      O(N) + O(N) ≈ O(N)
 *
 * -------------------------------------------------------
 *
 * ✔ Final Complexity
 *
 * Time  : O(N)
 * Space : O(N)
 */