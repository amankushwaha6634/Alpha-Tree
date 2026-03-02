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