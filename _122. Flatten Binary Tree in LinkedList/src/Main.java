import java.util.*;

public class Main {

    // 🌳 Binary Tree Node
    static class Node {
        int data;
        Node left, right;

        Node(int val) {
            this.data = val;
        }
    }

    // =====================================================
    // 🔁 Flatten Binary Tree using Morris Traversal
    // =====================================================
    public static void flatten(Node root) {

        Node curr = root; // Start from root

        // Traverse until all nodes are processed
        while (curr != null) {

            // =================================================
            // Case 1️⃣: If left child is NULL
            // =================================================
            // Simply move to right
            if (curr.left == null) {
                curr = curr.right;
            }

            // =================================================
            // Case 2️⃣: Left child exists
            // =================================================
            else {

                // Find predecessor (rightmost node of left subtree)
                Node pred = curr.left;

                while (pred.right != null) {
                    pred = pred.right;
                }

                // Connect predecessor to current right subtree
                pred.right = curr.right;

                // Move left subtree to right
                curr.right = curr.left;

                // Set left child to NULL
                curr.left = null;

                // Move to next node
                curr = curr.right;
            }
        }
    }

    // 🔧 Build Sample Tree
    /*
            1
           / \
          2   5
         / \   \
        3   4   6
    */
    public static Node buildTree() {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(5);
        root.left.left = new Node(3);
        root.left.right = new Node(4);
        root.right.right = new Node(6);
        return root;
    }

    // 🔍 Print Flattened Tree
    public static void printFlattened(Node root) {

        Node curr = root;

        while (curr != null) {
            System.out.print(curr.data + " ");
            curr = curr.right;
        }
    }

    public static void main(String[] args) {

        Node root = buildTree();

        flatten(root);

        System.out.print("Flattened Tree: ");
        printFlattened(root);
    }
}


/*
Goal:
Flatten binary tree into a linked list using preorder order.

Rules:
- All left pointers become NULL
- Right pointer forms linked list
- Order must follow PREORDER (Root → Left → Right)

Steps:

1. Start from root node.

2. If left child is NULL:
      move to right.

3. If left child exists:
      find rightmost node of left subtree (predecessor).

4. Connect predecessor.right → current.right

5. Move left subtree to right:
      current.right = current.left

6. Set current.left = NULL

7. Move current = current.right



Key Points:

✔ Uses Morris Traversal idea  
✔ No recursion  
✔ No stack  
✔ Tree flattened in-place



Time Complexity: O(N)
Each node visited once.

Space Complexity: O(1)
No recursion or stack used.
*/

/*
🧠 Flatten Binary Tree to Linked List – Morris Style (O(1) Space)

Goal:
Convert binary tree into a linked list **in-place** using preorder order.

Final structure must look like:

1
 \
  2
   \
    3
     \
      4

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

Original Tree:

        1
       / \
      2   5
     / \   \
    3   4   6

Expected Flattened List:

1 → 2 → 3 → 4 → 5 → 6

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

Step 1: curr = 1

Left subtree exists.

Find predecessor of 1:
Rightmost node in left subtree = 4

Attach:
4.right → 5

Tree becomes:

        1
       / \
      2   5
     / \
    3   4
         \
          5
           \
            6

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

Step 2

Move left subtree to right

1.right = 2
1.left = NULL

Tree now:

1
 \
  2
 / \
3   4
     \
      5
       \
        6

Move curr = 2

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

Step 3: curr = 2

Left subtree exists.

Predecessor = 3

Attach:
3.right → 4

Tree becomes:

        1
         \
          2
         / \
        3   4
         \   \
          4   5
           \   \
            5   6
             \
              6

Move left subtree to right.

2.right = 3
2.left = NULL

Tree now:

1
 \
  2
   \
    3
     \
      4
       \
        5
         \
          6

Move curr = 3

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

Step 4

Nodes 3, 4, 5 have no left child.

So we simply move right.

curr = curr.right

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

Final Flattened Tree:

1
 \
  2
   \
    3
     \
      4
       \
        5
         \
          6

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

Key Observations:

✔ No recursion used
✔ No stack used
✔ Tree modified in-place
✔ Same idea as Morris traversal

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

Time Complexity:
O(N)

Each node processed once.

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

Space Complexity:
O(1)

No extra memory used.
*/