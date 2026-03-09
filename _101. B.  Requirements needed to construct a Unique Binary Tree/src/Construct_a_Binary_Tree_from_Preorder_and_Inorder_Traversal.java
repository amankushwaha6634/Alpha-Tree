
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


/*
🧠 Goal: Reconstruct Binary Tree from:
Preorder = [3, 9, 20, 15, 7]
Inorder  = [9, 3, 15, 20, 7]

📍 Step-by-step Construction:

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🔸 Step 1: Build root node from preorder[0] = 3
inorderIndex(3) = 1

➡ Left Subtree: inorder[0 to 0] → [9]
➡ Right Subtree: inorder[2 to 4] → [15, 20, 7]

Tree so far:
       3
      / \
  ( ? )  ( ? )

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🔸 Step 2: Build left child of 3 using preorder[1] = 9
inorderIndex(9) = 0 → no left/right children

Tree so far:
       3
      / \
     9   ?

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🔸 Step 3: Build right child of 3 using preorder[2] = 20
inorderIndex(20) = 3

➡ Left Subtree: inorder[2 to 2] → [15]
➡ Right Subtree: inorder[4 to 4] → [7]

Tree so far:
       3
      / \
     9   20
         / \
     ( ? ) ( ? )

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🔸 Step 4: Build left child of 20 using preorder[3] = 15
inorderIndex(15) = 2 → no children

Tree so far:
       3
      / \
     9   20
         / \
       15   ?

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🔸 Step 5: Build right child of 20 using preorder[4] = 7
inorderIndex(7) = 4 → no children

✅ Final Constructed Tree:
       3
      / \
     9   20
         / \
       15   7

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
📊 Index Tracking:
preIndex at each step → [0, 1, 2, 3, 4]
Each index corresponds to a root built at that stage

🕒 Time Complexity: O(N)
→ Each node is created once and map lookup is O(1)

📦 Space Complexity: O(N)
→ Recursion stack + HashMap storing inorder indices

*/
