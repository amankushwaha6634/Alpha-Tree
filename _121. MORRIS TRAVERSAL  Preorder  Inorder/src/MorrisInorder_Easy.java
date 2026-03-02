import java.util.*;

public class MorrisInorder_Easy {

    // 🌳 Binary Tree Node
    static class Node {
        int data;
        Node left, right;

        Node(int val) {
            this.data = val;
        }
    }

    // =====================================================
    // 🔁 Morris Inorder Traversal (O(1) Space)
    // =====================================================
    public static void morrisInorder(Node root) {

        Node curr = root; // Start from root

        // Traverse until all nodes are processed
        while (curr != null) {

            // =================================================
            // Case 1️⃣: If left child is NULL
            // =================================================
            // No left subtree → visit current node
            if (curr.left == null) {
                System.out.print(curr.data + " ");

                // Move to right subtree
                curr = curr.right;
            }

            // =================================================
            // Case 2️⃣: Left child exists
            // =================================================
            else {
                // Find Inorder Predecessor (rightmost in left subtree)
                Node pred = curr.left;

                // Move to rightmost node OR until thread exists
                while (pred.right != null && pred.right != curr) {
                    pred = pred.right;
                }

                // -------------------------------------------------
                // Subcase A: Thread not created yet
                // -------------------------------------------------
                if (pred.right == null) {

                    // Create temporary thread back to current node
                    pred.right = curr;

                    // Move to left subtree
                    curr = curr.left;
                }

                // -------------------------------------------------
                // Subcase B: Thread already exists
                // -------------------------------------------------
                else {

                    // Remove thread (restore original tree)
                    pred.right = null;

                    // Visit current node
                    System.out.print(curr.data + " ");

                    // Move to right subtree
                    curr = curr.right;
                }
            }
        }
    }

    // 🔧 Build Sample Tree
    /*
            4
           / \
          2   6
         / \ / \
        1  3 5  7
    */
    public static Node buildTree() {
        Node root = new Node(4);
        root.left = new Node(2);
        root.right = new Node(6);
        root.left.left = new Node(1);
        root.left.right = new Node(3);
        root.right.left = new Node(5);
        root.right.right = new Node(7);
        return root;
    }

    public static void main(String[] args) {
        Node root = buildTree();

        System.out.print("Morris Inorder Traversal: ");
        morrisInorder(root);
    }
}


/*
Goal: Perform inorder traversal without recursion or stack.

Idea: Use tree itself to create temporary threads.

Steps:
1. If left is null:
      visit node
      move right

2. Else:
      Find inorder predecessor (rightmost of left subtree)

      If pred.right == null:
            create thread to current
            move left

      Else:
            remove thread
            visit current
            move right

Time Complexity: O(N)
Space Complexity: O(1)
 */


/*
🧪 Morris Inorder Traversal – Diagram Dry Run (with Threads)

Original Tree:
            4
          /   \
         2     6
        / \   / \
       1   3 5   7

Expected Inorder:
1 2 3 4 5 6 7

Rule:
- If left is NULL → print and move right
- Else:
    Find inorder predecessor (rightmost of left subtree)
    If predecessor.right == NULL → create thread
    Else → remove thread and print

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

Step 1: curr = 4
Left exists → predecessor = 3
Create thread: 3 → 4

Tree (thread shown):
            4
          /   \
         2     6
        / \   / \
       1   3 5   7
            \
             4   (thread)

Move curr = 2

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

Step 2: curr = 2
Left exists → predecessor = 1
Create thread: 1 → 2

Tree:
            4
          /   \
         2     6
        / \   / \
       1   3 5   7
        \   \
         2   4
     (thread) (thread)

Move curr = 1

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

Step 3: curr = 1
Left is NULL → print 1
Move via thread → curr = 2

Output: 1

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

Step 4: curr = 2
Predecessor = 1 and 1.right == curr
Remove thread: 1 → NULL
Print 2

Tree now:
            4
          /   \
         2     6
          \   / \
           3 5   7
            \
             4   (thread)

Move curr = 3

Output: 1 2

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

Step 5: curr = 3
Left is NULL → print 3
Move via thread → curr = 4

Output: 1 2 3

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

Step 6: curr = 4
Predecessor = 3 and 3.right == curr
Remove thread: 3 → NULL
Print 4

Tree now restored on left side:
            4
          /   \
         2     6
              / \
             5   7

Move curr = 6

Output: 1 2 3 4

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

Step 7: curr = 6
Left exists → predecessor = 5
Create thread: 5 → 6

Tree:
            4
          /   \
         2     6
              / \
             5   7
              \
               6  (thread)

Move curr = 5

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

Step 8: curr = 5
Left is NULL → print 5
Move via thread → curr = 6

Output: 1 2 3 4 5

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

Step 9: curr = 6
Predecessor = 5 and 5.right == curr
Remove thread: 5 → NULL
Print 6

Move curr = 7

Output: 1 2 3 4 5 6

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

Step 10: curr = 7
Left is NULL → print 7
Move curr = NULL

Output: 1 2 3 4 5 6 7

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

Final Result:
Inorder = 1 2 3 4 5 6 7

Important Points:
- Each thread is created once and removed once
- Tree structure is fully restored
- Time Complexity = O(N)
- Space Complexity = O(1)
*/