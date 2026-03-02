import java.util.*;

// 🌳 Node class for Binary Tree
class Node {
    int data;
    Node left, right;

    Node(int val) {
        this.data = val;
        this.left = null;
        this.right = null;
    }
}

public class NodesAtKDistance {

    // 🔁 Step 1: Map each node to its parent
    private static void mapParents(Node root, Map<Node, Node> parentMap) {
        Queue<Node> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            Node curr = q.poll();

            if (curr.left != null) {
                parentMap.put(curr.left, curr);
                q.add(curr.left);
            }

            if (curr.right != null) {
                parentMap.put(curr.right, curr);
                q.add(curr.right);
            }
        }
    }

    // 🎯 Step 2: BFS from target node to find nodes at distance K
    public static List<Integer> printNodesAtDistanceK(Node root, Node target, int K) {
        Map<Node, Node> parentMap = new HashMap<>();
        mapParents(root, parentMap); // 🔁 Fill parent mapping
        /*
        🧠 DRY RUN:
        Tree:
                     3
                    / \
                   5   1
                  / \  / \
                 6  2 0  8
                   / \
                  7   4
                parentMap:
                6 → 5
                2 → 5
                5 → 3
                1 → 3
                0 → 1
                8 → 1
                7 → 2
                4 → 2   */

        Queue<Node> q = new LinkedList<>();
        q.add(target);             // 🎯 Start BFS from target
        Set<Node> visited = new HashSet<>();
        visited.add(target);         // ✅ Mark target as visited

        int currentLevel = 0;

        /*
        🧠 DRY RUN:

        Tree:
                     3
                    / \
                   5   1
                  / \  / \
                 6  2 0  8
                   / \
                  7   4

        Target = 5, K = 2

        Initial Queue = [5]
        visited = [5]
        currentLevel = 0

        --> Process level 0:
            Nodes = [5]
            Enqueue: 6, 2 (children), 3 (parent of 5)
            Queue after level 0 = [6, 2, 3]
            currentLevel = 1

        --> Process level 1:
            Nodes = [6, 2, 3]
            From 6 → no new nodes
            From 2 → 7, 4
            From 3 → 1
            Queue after level 1 = [7, 4, 1]
            currentLevel = 2

        Done (reached level K = 2)

        Final queue = [7, 4, 1]
        Answer = [7, 4, 1]
        */

        while (!q.isEmpty()) {
            if (currentLevel == K) break;
            int size = q.size();

            for (int i = 0; i < size; i++) {
                Node curr = q.poll();

                // Check left
                if (curr.left != null && !visited.contains(curr.left)) {
                    visited.add(curr.left);
                    q.offer(curr.left);
                }

                // Check right
                if (curr.right != null && !visited.contains(curr.right)) {
                    visited.add(curr.right);
                    q.offer(curr.right);
                }

                // Check parent
                Node parent = parentMap.get(curr);
                if (parent != null && !visited.contains(parent)) {
                    visited.add(parent);
                    q.offer(parent);
                }
            }

            currentLevel++;  // ⏫ Go to next level
        }

        // 📦 Collect all nodes at distance K
        List<Integer> result = new ArrayList<>();
        while (!q.isEmpty()) {
            result.add(q.poll().data);
        }

        return result;
    }

    // 🧪 Main Method
    public static void main(String[] args) {
        /*
        Build this tree:

                 3
                / \
               5   1
              / \  / \
             6  2 0  8
               / \
              7   4
        */

        Node root = new Node(3);
        root.left = new Node(5);
        root.right = new Node(1);
        root.left.left = new Node(6);
        root.left.right = new Node(2);
        root.right.left = new Node(0);
        root.right.right = new Node(8);
        root.left.right.left = new Node(7);
        root.left.right.right = new Node(4);

        Node target = root.left; // target = 5
        int k = 2;

        List<Integer> ans = printNodesAtDistanceK(root, target, k);
        System.out.println("Nodes at distance " + k + " from target: " + ans);  // [7, 4, 1]
    }
}


/*
🔍 What is happening:
- We store each node’s parent so that we can go up the tree.
- Then we do BFS (level order traversal) starting from the target node.
- After K levels of BFS, the queue will have all nodes at distance K.
- We collect those nodes into a list and return.

⏱️ Time Complexity: O(N)
📦 Space Complexity: O(N)

Why?
- We may visit all N nodes to find all nodes at distance K.
- Parent mapping + BFS queue + visited set all take space.

*/

/*
🧪 DRY RUN: Print All Nodes at Distance K from Target Node (K = 2)

🔧 Given Binary Tree:

             3
           /   \
          5     1
         / \   / \
        6   2 0   8
           / \
          7   4

🎯 Target Node = 5
🔢 K = 2 → We want all nodes at exactly 2 edges away from node 5

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
Step 1: Build Parent Map

Traverse tree and store:
child → parent in a map

parentMap:
6 → 5
2 → 5
5 → 3
1 → 3
0 → 1
8 → 1
7 → 2
4 → 2

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
Step 2: BFS from Target Node

Initialize:
queue = [5]
visited = {5}
level = 0

             3
           /   \
          5     1
         / \   / \
        6   2 0   8
           / \
          7   4

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🔁 Level 0:

queue = [5]
visited = {5}

➡️ Explore node 5:
  - left: 6  → added to queue
  - right: 2 → added to queue
  - parent: 3 → added to queue

queue = [6, 2, 3]
visited = {5, 6, 2, 3}
level = 1

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🔁 Level 1:

queue = [6, 2, 3]
visited = {5, 6, 2, 3}

➡️ Explore 6: only parent is 5 → already visited
➡️ Explore 2:
   - left: 7 → added
   - right: 4 → added
   - parent: 5 → already visited

➡️ Explore 3:
   - left: 5 → already visited
   - right: 1 → added

queue = [7, 4, 1]
visited = {5, 6, 2, 3, 7, 4, 1}
level = 2 ✅

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
Level = K = 2 → Stop here

🎯 Nodes at distance K = 2: [7, 4, 1]

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
📤 OUTPUT: 7 4 1
*/
