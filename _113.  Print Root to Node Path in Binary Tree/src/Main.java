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

        // ❌ Backtrack
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
🧠 SHORT NOTES: Root to Node Path (Wrapper + Helper)

📌 Goal:
Return path from root to a given target node.

📘 Design Pattern:
Wrapper + Helper

Wrapper (solve):
- Creates empty path list
- Handles null root
- Calls recursive helper
- Returns final path

Helper (getPath):
1. If node is null → return false
2. Add current node to path
3. If node == target → return true
4. Recurse left OR right
5. If both false → backtrack (remove node) and return false

💡 Key Concept: Backtracking
Only nodes on the correct path remain in the list.

💡 Short-Circuit Optimization:
getPath(left) || getPath(right)
If left returns true → right is NOT executed.

📦 Data Structures:
- ArrayList → stores path
- Recursion stack

🕒 Time Complexity: O(N)
🧠 Space Complexity: O(H)  (H = tree height)

🎯 Output:
Path from root to target node
*/


/*
🧪 DRY RUN: Root to Node Path (Wrapper + Helper)

Example Tree:
        1
       / \
      2   3
     / \
    4   5

Target = 5

▶ Step 0: Wrapper Call
solve(root, 5)
- path = []
- Calls getPath(1, [], 5)

-------------------------------------------------

1️⃣ getPath(1)
   path = [1]
   1 != 5
   → search left subtree

2️⃣ getPath(2)
   path = [1, 2]
   2 != 5
   → search left subtree

3️⃣ getPath(4)
   path = [1, 2, 4]
   4 != 5
   → left = null, right = null
   ❌ Not found → Backtrack
   path = [1, 2]

4️⃣ getPath(5)
   path = [1, 2, 5]
   5 == target ✅
   → return true

✔ Due to short-circuit OR:
Right subtree of node 1 is NOT explored.

-------------------------------------------------

📤 Final Path:
[1, 2, 5]
*/