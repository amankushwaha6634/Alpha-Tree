import java.util.*;

public class RightViewDFS {

    // 👉 Function to get Right View using Recursion
    public static List<Integer> rightView(Node root) {

        List<Integer> result = new ArrayList<>();

        // Start recursion from level 0
        rightViewUtil(root, 0, result);

        return result;
    }

    // 🔁 Recursive helper function
    static void rightViewUtil(Node node, int level, List<Integer> result) {

        // Base case
        if (node == null) return;

        // ⭐ If visiting this level first time → this node is rightmost
        if (level == result.size()) {
            result.add(node.data);
        }

        // Important: Visit RIGHT first, then LEFT
        rightViewUtil(node.right, level + 1, result);
        rightViewUtil(node.left, level + 1, result);
    }

    // 🌳 Driver Code
    public static void main(String[] args) {

        /*
                 1
               /   \
              2     3
             / \     \
            4   5     7
                       /
                      8

        Right View:
        1 3 7 8
        */

        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.right = new Node(7);
        root.right.right.left = new Node(8);

        List<Integer> output = rightView(root);

        System.out.println("Right View (Recursion):");
        for (int val : output) {
            System.out.print(val + " ");
        }
    }

    // Node class
    static class Node {
        int data;
        Node left, right;

        Node(int data) {
            this.data = data;
        }
    }
}

/*
🧠 SHORT NOTES: Right View (Recursion)

📌 Goal:
Print nodes visible from the RIGHT side.

📘 Concept:
- Traverse tree using DFS
- Visit RIGHT before LEFT
- First node visited at each level is the rightmost

🛠️ Steps:
1. Create result list
2. Start recursion with level = 0
3. If level == result.size() → add node
4. Recurse:
   - right child
   - left child

📦 Data Structures:
- List<Integer> → stores result
- Recursion stack

🕒 Time: O(N)
🧠 Space: O(H)  // H = height of tree

🎯 Output: First node encountered at each level from right side
*/


/*
🧪 DRY RUN: Right View using Recursion

Example Tree:
             1
           /   \
         2       3
        / \       \
       4   5       7
                    /
                   8

📌 Rule:
Visit RIGHT first.
If level == result.size() → first node seen at this level → add it.

🔁 Traversal Order:

Call (1, level 0)
➤ level == 0 → add 1 → result = [1]

Go Right → (3, level 1)
➤ level == 1 → add 3 → result = [1, 3]

Go Right → (7, level 2)
➤ level == 2 → add 7 → result = [1, 3, 7]

Go Right → null
Go Left → (8, level 3)
➤ level == 3 → add 8 → result = [1, 3, 7, 8]

Backtrack:
Left subtree nodes (2,4,5) are ignored because their levels already filled.

🎯 Final Output:
1 3 7 8
*/