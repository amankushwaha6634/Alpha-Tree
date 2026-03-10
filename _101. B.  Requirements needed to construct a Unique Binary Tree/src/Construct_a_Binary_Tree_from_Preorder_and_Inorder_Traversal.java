
import java.util.*;

public class Construct_a_Binary_Tree_from_Preorder_and_Inorder_Traversal {
    // 🌳 Basic Node definition
    static class Node {
        int data;
        Node left, right;
        Node(int val) {
            this.data = val;
        }
    }

    // 🧠 Index to keep track of current element in preorder array
    static int preIndex = 0;

    public static Node buildTree(int[] preorder, int[] inorder) {
        // 🗺️ Map to store inorder indices for quick lookup
        Map<Integer, Integer> inMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inMap.put(inorder[i], i);
        }
        /*
        ➡ Inorder:  [9, 3, 15, 20, 7] → (Left, Root, Right)
        - `inorderMap` = {9:0, 3:1, 15:2, 20:3, 7:4}
        */
        return build(preorder, 0, inorder.length - 1, inMap);
    }

    private static Node build(int[] preorder, int inStart, int inEnd, Map<Integer, Integer> inMap) {
        // ⛔ Base condition: if inorder range is invalid
        if (inStart > inEnd) return null;

        // 🎯 Pick current node from preorder
        int rootVal = preorder[preIndex++];
        Node root = new Node(rootVal);

        // 📍 Get inorder index of root
        int inIndex = inMap.get(rootVal);

        // 🔁 Recursively build left and right subtrees
        root.left = build(preorder, inStart, inIndex - 1, inMap);
        root.right = build(preorder, inIndex + 1, inEnd, inMap);

        return root;
    }

    // 📤 Print the tree in postorder to verify
    public static void printPostorder(Node root) {
        if (root == null) return;
        printPostorder(root.left);
        printPostorder(root.right);
        System.out.print(root.data + " ");
    }

    public static void main(String[] args) {
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};

        Node root = buildTree(preorder, inorder);

        System.out.print("Postorder of constructed tree: ");
        printPostorder(root);  // Expected Output: 9 15 7 20 3
    }
}
/*
🧠 DRY RUN: Build Binary Tree from Preorder + Inorder

Given:
➡ Preorder: [3, 9, 20, 15, 7] → (Root, Left, Right)
➡ Inorder:  [9, 3, 15, 20, 7] → (Left, Root, Right)

📦 Supporting Data Structures:
- `preIndex` = 0 (tracks index in preorder)
- `inorderMap` = {9:0, 3:1, 15:2, 20:3, 7:4}
- Recursively build the tree using preIndex and left/right index bounds of inorder

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🔹 Step 1: buildSubTree(0, 4)

preIndex = 0 → preorder[0] = 3 → 🪵 root = 3
inorderMap[3] = 1

↙ Left Inorder: inorder[0 to 0] → [9]
↘ Right Inorder: inorder[2 to 4] → [15, 20, 7]

🌳 Tree so far:
        3

Data snapshot:
- preIndex → 1
- Left buildSubTree(0, 0) is next

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🔹 Step 2: buildSubTree(0, 0)

preIndex = 1 → preorder[1] = 9 → 🪵 root = 9
inorderMap[9] = 0

🛑 No left or right children (since start == end)

🌳 Tree so far:
        3
       /
      9

Data snapshot:
- preIndex → 2
- Right buildSubTree(2, 4) is next

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🔹 Step 3: buildSubTree(2, 4)

preIndex = 2 → preorder[2] = 20 → 🪵 root = 20
inorderMap[20] = 3

↙ Left Inorder: inorder[2 to 2] → [15]
↘ Right Inorder: inorder[4 to 4] → [7]

🌳 Tree so far:
        3
       / \
      9   20

Data snapshot:
- preIndex → 3
- Left buildSubTree(2, 2) is next

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🔹 Step 4: buildSubTree(2, 2)

preIndex = 3 → preorder[3] = 15 → 🪵 root = 15
inorderMap[15] = 2

🛑 No left or right children (start == end)

🌳 Tree so far:
        3
       / \
      9   20
          /
         15

Data snapshot:
- preIndex → 4
- Right buildSubTree(4, 4) is next

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🔹 Step 5: buildSubTree(4, 4)

preIndex = 4 → preorder[4] = 7 → 🪵 root = 7
inorderMap[7] = 4

🛑 No left or right children

🌳 Final Tree:
        3
       / \
      9   20
          / \
        15   7

Data snapshot:
- preIndex → 5 (end of preorder)

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
✅ FINAL OUTPUT:
Reconstructed Binary Tree:
        3
       / \
      9   20
          / \
        15   7

📈 Time Complexity: O(N)
   → Each node visited once, map lookups are O(1)

📦 Space Complexity: O(N)
   → Recursion stack + HashMap
*/




/**
 * ⏱️ Time Complexity: O(N)
 *
 * Explanation:
 * - Each node from the preorder array is processed exactly once.
 * - For every node:
 *      1. We create a Node object → O(1)
 *      2. We lookup its index in the inorder array using HashMap → O(1)
 *      3. We recursively build left and right subtree.
 *
 * - Since there are N nodes in the tree and each node is handled once,
 *   the total time complexity becomes:
 *
 *      O(N)
 *
 * -------------------------------------------------------
 *
 * 📦 Space Complexity: O(N)
 *
 * There are two contributors:
 *
 * 1️⃣ HashMap (inMap)
 * - Stores all elements of inorder traversal.
 * - Size = N
 * - Space = O(N)
 *
 * 2️⃣ Recursion Stack
 * - In the worst case (skewed tree), recursion depth can reach N.
 * - Example: tree like 1 → 2 → 3 → 4 → 5
 * - Space = O(N)
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
