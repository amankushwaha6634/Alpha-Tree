import java.util.*;

public class Main {

    // 🌳 Node class
    static class Node {
        int data;
        Node left, right;

        Node(int data) {
            this.data = data;
        }
    }

    // 🌟 Wrapper Function (like your image)
    public static ArrayList<Integer> solve(Node root, int target) {

        ArrayList<Integer> path = new ArrayList<>();

        if (root == null) return path;

        getPath(root, path, target);

        return path;
    }

    // 🔁 Helper function (actual recursion)
    public static boolean getPath(Node root, ArrayList<Integer> path, int target) {

        if (root == null) return false;

        // Add current node
        path.add(root.data);

        // If target found
        if (root.data == target) return true;

        // Check left or right subtree
        if (getPath(root.left, path, target) ||
                getPath(root.right, path, target)) {
            return true;
        }

        // ❌ Backtrack : if neither left nor right returns true remove node from path and return false
        path.remove(path.size() - 1);
        return false;
    }

    public static void main(String[] args) {

        /*
              1
             / \
            2   3
           / \
          4   5
        */

        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);

        int target = 5;

        ArrayList<Integer> result = solve(root, target);

        System.out.println("Path: " + result);
    }
}

/*
📌 GOAL:
Find path from root to given node in binary tree.

🛠️ ALGORITHM:
1. Traverse tree recursively.
2. At each node, add it to path.
3. If node is the target → return true.
4. Else recur for left and right.
5. If neither returns true → backtrack (remove node from path).

💡 Tip:
Use backtracking — only keep the correct path.

🕒 Time: O(N) → visit all nodes in worst case
🧠 Space: O(H) → path list + recursion stack (H = height)

 */


/*
🧪 DRY RUN: getPath(root = 1, target = 5)

Tree:
        1
       / \
      2   3
     / \
    4   5

Target = 5

1️⃣ Call getPath(1, 5, [])
   - root = 1 → add 1 → path = [1]
   - 1 != 5 → call left and right

2️⃣ getPath(2, 5, [1])
   - root = 2 → add 2 → path = [1, 2]
   - 2 != 5 → call left and right

3️⃣ getPath(4, 5, [1, 2])
   - root = 4 → add 4 → path = [1, 2, 4]
   - 4 != 5 → no children → backtrack
   - remove 4 → path = [1, 2]

4️⃣ getPath(5, 5, [1, 2])
   - root = 5 → add 5 → path = [1, 2, 5]
   - 5 == 5 → ✅ return true

✅ Final Path: [1, 2, 5]

Output:
Path from root to node 5: [1, 2, 5]
*/


/*
🧠 SHORT-CIRCUIT LOGIC IN JAVA (|| - OR Operator):
if (getPath(root.left, target, path) || getPath(root.right, target, path)) {
        return true;
}

In this condition:
if (getPath(root.left, target, path) || getPath(root.right, target, path))

➡️ Java evaluates the left side first:
   - If getPath(root.left, ...) returns true,
     then the right side (getPath(root.right, ...)) is NOT evaluated.

✅ This is called "short-circuiting" with || (logical OR).
⚡ Benefit: Avoids unnecessary recursion into the right subtree
   if the path has already been found in the left subtree.

🧪 Example:
   if (true || someOtherCheck()) → "someOtherCheck()" will NOT be executed.

📌 Result: More efficient traversal.
*/

