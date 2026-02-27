import java.util.*;

public class Main {

    // 🌿 Node class - represents each node of the binary tree
    static class Node {
        int data;
        Node left, right;

        Node(int data) {
            this.data = data; // Initialize node value
        }
    }

    // 🔍 Function to check Children Sum Property
    public static boolean isChildrenSum(Node root) {

        // 📌 Base Case 1: Empty tree satisfies property
        if (root == null) return true;

        // 📌 Base Case 2: Leaf node (no children) always satisfies property
        if (root.left == null && root.right == null) return true;

        int left = 0, right = 0;

        // Get left child value (if exists)
        if (root.left != null)
            left = root.left.data;

        // Get right child value (if exists)
        if (root.right != null)
            right = root.right.data;

        // ⭐ Condition to check:
        // Current node value should be equal to sum of left and right child
        // AND both left and right subtrees should also satisfy property
        if (root.data == left + right &&
                isChildrenSum(root.left) &&
                isChildrenSum(root.right)) {
            return true;
        }

        // ❌ If condition fails
        return false;
    }

    public static void main(String[] args) {

        /*
                 10
                /  \
               8    2
              / \    \
             3   5    2

        8 = 3 + 5
        2 = 0 + 2
        10 = 8 + 2  ✅ Valid
        */

        // 🌳 Tree construction
        Node root = new Node(10);
        root.left = new Node(8);
        root.right = new Node(2);
        root.left.left = new Node(3);
        root.left.right = new Node(5);
        root.right.right = new Node(2);

        // 🚀 Check Children Sum Property
        if (isChildrenSum(root))
            System.out.println("Children Sum Property satisfied");
        else
            System.out.println("Not satisfied");
    }
}

/*
🧠 SHORT NOTES: Children Sum Property (Check)

📌 Goal:
Verify that every node’s value equals the sum of its children.

📘 Rules:
- Leaf node → always valid
- If a child is missing → treat its value as 0

🛠️ Steps:
1. If root is null → return true
2. If leaf node → return true
3. Get left child value (if exists)
4. Get right child value (if exists)
5. Check:
   root.data == left + right
6. Recursively check left and right subtrees

📦 Data Structures:
- Recursion (DFS)

🕒 Time Complexity: O(N)
→ Every node is visited once

🧠 Space Complexity: O(H)
→ Recursion stack (H = tree height)

🎯 Output:
Returns true if entire tree satisfies Children Sum Property
*/


/*
🧪 DRY RUN: Children Sum Property (Check)

Example Tree:
                 10
                /  \
               8    2
              / \    \
             3   5    2

Rule:
For every node:
node.data == left.data + right.data
(Missing child → value = 0)

-------------------------------------------------

1️⃣ Check Node 10
   left = 8
   right = 2
   sum = 10 → matches ✅

   Now check subtrees

-------------------------------------------------

2️⃣ Check Node 8
   left = 3
   right = 5
   sum = 8 → matches ✅

-------------------------------------------------

3️⃣ Check Node 2 (right subtree)
   left = 0
   right = 2
   sum = 2 → matches ✅

-------------------------------------------------

4️⃣ Leaf Nodes (3, 5, 2)
   Leaf nodes automatically satisfy property ✅

-------------------------------------------------

📤 Final Result:
All nodes satisfy condition → TRUE
Children Sum Property satisfied
*/