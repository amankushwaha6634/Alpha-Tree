import java.util.*;

public class Main {

    // 🌳 Node class
    static class Node {
        int data;
        Node left, right;

        Node(int val) {
            this.data = val;
        }
    }

    // 🔁 Step 1: Map parents and find target node
    static Node mapParents(Node root, Map<Node, Node> parentMap, int target) {

        Queue<Node> q = new LinkedList<>();
        q.offer(root);

        Node targetNode = null;

        while (!q.isEmpty()) {
            int size = q.size();   // Level-wise traversal

            for (int i = 0; i < size; i++) {
                Node curr = q.poll();

                // Find target node
                if (curr.data == target)
                    targetNode = curr;

                if (curr.left != null) {
                    parentMap.put(curr.left, curr);
                    q.offer(curr.left);
                }

                if (curr.right != null) {
                    parentMap.put(curr.right, curr);
                    q.offer(curr.right);
                }
            }
        }
        return targetNode;
    }

    // 🔥 Step 2: Burn tree using BFS (levelSize = time units)
    static int burnTree(Node root, int target) {

        Map<Node, Node> parentMap = new HashMap<>();

        // Get target reference + parent mapping
        Node targetNode = mapParents(root, parentMap, target);

        // BFS from target
        Queue<Node> q = new LinkedList<>();
        Set<Node> visited = new HashSet<>();

        q.offer(targetNode);
        visited.add(targetNode);

        int time = 0;

        while (!q.isEmpty()) {

            int levelSize = q.size();   // Nodes burning at current time
            boolean burned = false;

            for (int i = 0; i < levelSize; i++) {

                Node curr = q.poll();

                // Burn left
                if (curr.left != null && !visited.contains(curr.left)) {
                    visited.add(curr.left);
                    q.offer(curr.left);
                    burned = true;
                }

                // Burn right
                if (curr.right != null && !visited.contains(curr.right)) {
                    visited.add(curr.right);
                    q.offer(curr.right);
                    burned = true;
                }

                // Burn parent
                if (parentMap.containsKey(curr)) {
                    Node parent = parentMap.get(curr);
                    if (!visited.contains(parent)) {
                        visited.add(parent);
                        q.offer(parent);
                        burned = true;
                    }
                }
            }

            // Increase time only if fire spread
            if (burned) time++;
        }

        return time;
    }

    public static void main(String[] args) {

        /*
                  1
                /   \
               2     3
              / \     \
             4   5     7
                        \
                         8
        */

        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.right = new Node(7);
        root.right.right.right = new Node(8);

        int target = 5;

        System.out.println("🔥 Time to burn tree: " + burnTree(root, target));
    }
}

/*
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🧠 SHORT NOTES: Burn Binary Tree from Target

📌 Goal:
Find the minimum time required to burn the entire tree
if fire starts from a given target node.

🔥 Fire spreads to:
- Left child
- Right child
- Parent

Each spread takes 1 unit time.

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
💡 Key Idea:
Binary tree nodes do not have parent pointers.
So first we create a mapping:
child → parent

Then we perform BFS starting from the target node.
Each BFS level represents 1 unit of time.

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🛠️ Steps:

Step 1: Map Parents (BFS)
- Traverse tree level-wise
- Store parent of each node in a HashMap
- Also find reference of target node

Step 2: BFS from Target
- Add target node to queue
- Mark it visited
- For each level:
    • Burn left child (if not visited)
    • Burn right child (if not visited)
    • Burn parent (if not visited)
- If fire spreads in this level → time++

Step 3:
When queue becomes empty → entire tree is burned

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
📦 Data Structures Used:
- HashMap<Node, Node> → parentMap
- HashMap<Node, Boolean> or Set<Node> → visited
- Queue<Node> → BFS traversal

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
⏱️ Time Complexity: O(N)
- Parent mapping: O(N)
- Burning BFS: O(N)

📦 Space Complexity: O(N)
- Parent map + visited + queue

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🎯 Pattern Insight:
This problem is equivalent to:
→ BFS on an **undirected graph**
where each node connects to:
    left, right, parent
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
*/

/*
🧪 DRY RUN: Burn Binary Tree from Node 5

Binary Tree:
              1
            /   \
           2     3
          / \     \
         4   5     7
                    \
                     8

🎯 Start burning from Node 5

Step 1️⃣: Build parent mapping using BFS
  parentMap:
    2 -> 1
    3 -> 1
    4 -> 2
    5 -> 2
    7 -> 3
    8 -> 7
  targetNode = Node with value 5

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
Step 2️⃣: Start BFS from targetNode (5)
We'll simulate fire spread by levels (time units):

⏱️ time = 0
queue: [5]
visited: {5}

📍 At time = 0:
- Burn node 5
  → left = null
  → right = null
  → parent = 2 → Add to queue ✅
newQueue = [2]
visited = {5, 2}
burnedAny = true → time++

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
⏱️ time = 1
queue: [2]
visited: {5, 2}

📍 At time = 1:
- Burn node 2
  → left = 4 → Add to queue ✅
  → right = 5 (already visited)
  → parent = 1 → Add to queue ✅
newQueue = [4, 1]
visited = {5, 2, 4, 1}
burnedAny = true → time++

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
⏱️ time = 2
queue: [4, 1]
visited: {5, 2, 4, 1}

📍 At time = 2:
- Burn node 4 → no children, skip
- Burn node 1
  → left = 2 (already visited)
  → right = 3 → Add to queue ✅
newQueue = [3]
visited = {5, 2, 4, 1, 3}
burnedAny = true → time++

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
⏱️ time = 3
queue: [3]
visited: {5, 2, 4, 1, 3}

📍 At time = 3:
- Burn node 3
  → left = null
  → right = 7 → Add to queue ✅
  → parent = 1 (already visited)
newQueue = [7]
visited = {5, 2, 4, 1, 3, 7}
burnedAny = true → time++

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
⏱️ time = 4
queue: [7]
visited: {5, 2, 4, 1, 3, 7}

📍 At time = 4:
- Burn node 7
  → right = 8 → Add to queue ✅
  → left = null
  → parent = 3 (already visited)
newQueue = [8]
visited = {5, 2, 4, 1, 3, 7, 8}
burnedAny = true → time++

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
⏱️ time = 5
queue: [8]
visited: {5, 2, 4, 1, 3, 7, 8}

📍 At time = 5:
- Burn node 8 → no children
  → parent = 7 (already visited)
newQueue = []
No nodes left to burn → fire stops

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
✅ Final Answer:
🔥 Minimum time to burn tree from node 5 = **5 units**

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
📈 Time Complexity = O(N) → every node is visited once
📦 Space Complexity = O(N) → parent map + visited map + queue
*/
