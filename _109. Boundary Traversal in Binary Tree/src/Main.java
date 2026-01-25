import java.util.*;

public class Main {

    // 🌱 Binary Tree Node definition
    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    // ✅ Utility function to check if a node is a leaf
    // Leaf node = no left and no right child
    static boolean isLeaf(TreeNode node) {
        return (node.left == null && node.right == null);
    }

    // ⬅️ Add the left boundary (excluding leaf nodes)
    static void addLeftBoundary(TreeNode node, List<Integer> res) {
        TreeNode curr = node.left; // Start from left child of root

        while (curr != null) {
            if (!isLeaf(curr)) {
                res.add(curr.val); // Add if it's not a leaf
            }

            // Go as left as possible; if left is null, go right
            if (curr.left != null)
                curr = curr.left;
            else
                curr = curr.right;
        }
    }

    // 🍃 Add all the leaf nodes using DFS (left → right)
    static void addLeaves(TreeNode node, List<Integer> res) {
        if (node == null) return;

        if (isLeaf(node)) {
            res.add(node.val); // Only add if it's a leaf
            return;
        }

        // Recur left and right
        addLeaves(node.left, res);
        addLeaves(node.right, res);
    }

    // ➡️ Add the right boundary (excluding leaf nodes), bottom-up
    static void addRightBoundary(TreeNode node, List<Integer> res) {
        TreeNode curr = node.right; // Start from right child
        List<Integer> temp = new ArrayList<>(); // Store to reverse later

        while (curr != null) {
            if (!isLeaf(curr)) {
                temp.add(curr.val); // Add to temp list
            }

            // Go as right as possible; if right is null, go left
            if (curr.right != null)
                curr = curr.right;
            else
                curr = curr.left;
        }

        // ⏪ Reverse the collected right boundary
        Collections.reverse(temp);

        // Add reversed values to result
        res.addAll(temp);
    }

    // 🌳 Main function to perform boundary traversal
    public static List<Integer> boundaryTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();

        if (root == null) return res; // Base case: empty tree

        // Add root value only if it's not a leaf
        if (!isLeaf(root)) {
            res.add(root.val);
        }

        addLeftBoundary(root, res);   // Add left boundary
        addLeaves(root, res);         // Add all leaf nodes
        addRightBoundary(root, res);  // Add right boundary

        return res; // Final result list
    }

    // 🧪 Driver code to test the function
    public static void main(String[] args) {
        /*
        Constructed Tree:

                1
               / \
              2   3
             / \   \
            4   5   6
               / \   \
              7   8   9

        Expected Boundary Traversal:
        Left boundary: 1 → 2 → 4
        Leaf nodes: 7 → 8 → 9
        Right boundary (bottom-up): 6 → 3

        👉 Output: 1 2 4 7 8 9 6 3
        */

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(8);
        root.right.right = new TreeNode(6);
        root.right.right.right = new TreeNode(9);

        List<Integer> boundary = boundaryTraversal(root);

        System.out.println("Boundary Traversal:");
        for (int val : boundary) {
            System.out.print(val + " ");
        }
    }
}

   /*
        ------------------------------------------------------------
        🔁 Strategy:
        1. Add root node (if it's not a leaf).
        2. Add left boundary (top-down, excluding leaves).
        3. Add all leaf nodes (DFS, left to right).
        4. Add right boundary (bottom-up, excluding leaves).
        ------------------------------------------------------------

        🧪 Dry Run Example:
            Tree:
                    1
                   / \
                  2   3
                 / \   \
                4   5   6
                   / \   \
                  7   8   9

        Step 1 → Root: [1]
        Step 2 → Left boundary: [2, 4]  → [1, 2, 4]
        Step 3 → Leaves: [7, 8, 9]      → [1, 2, 4, 7, 8, 9]
        Step 4 → Right boundary: [6, 3] → [1, 2, 4, 7, 8, 9, 6, 3]

        ✅ Final Output: 1 2 4 7 8 9 6 3
        ------------------------------------------------------------

        📝 Notes:
        - Time Complexity: O(N), visiting each node once.
        - Space Complexity: O(N) for result + temporary right boundary list.
        - Avoid duplicates: do not add leaves in left/right boundary steps.
        - Node 5 is skipped because it’s not on boundary and not a leaf.
        ------------------------------------------------------------
        */

/*
Tree:

        1
       / \
      2   3
     / \   \
    4   5   6
       / \   \
      7   8   9

Left boundary: 1, 2, 4
Leaves: 7, 8, 9
Right boundary (bottom up): 6, 3

Final Result: 1 2 4 7 8 9 6 3

 */


/*
🌳 Tree Structure:

        1
       / \
      2   3
     / \   \
    4   5   6
       / \   \
      7   8   9

✅ Boundary Traversal includes 3 parts:
1️⃣ Left Boundary (excluding leaves):
    - Traverse top-down the leftmost edge.
    - Nodes: 1 → 2 → 4
    - 4 is a leaf but lies on boundary → included

2️⃣ Leaf Nodes (from left to right):
    - Nodes with no children: 4, 7, 8, 9
    - 4 is already in left boundary, but if not handled, may duplicate
    - Final unique leaves: 7, 8, 9

3️⃣ Right Boundary (excluding leaves, in bottom-up order):
    - Traverse down rightmost path skipping leaves, then reverse it
    - Nodes: 3 → 6 (9 is a leaf, so skip it)
    - Reversed → 6, 3

🧾 Final Boundary Traversal Output:
    → 1 2 4 7 8 9 6 3

❓ Why is node 5 not included?
- 5 is an internal node (not a leaf)
- It's not on the left or right boundary
- So it’s **not** included in boundary traversal (expected behavior)

📌 Boundary Traversal Rule:
Only include nodes that are:
    - On the left boundary (excluding leaves)
    - All leaf nodes
    - On the right boundary (excluding leaves), in bottom-up order
*/
