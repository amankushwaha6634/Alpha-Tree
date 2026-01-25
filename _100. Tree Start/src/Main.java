public class Main {

    // Node class to represent each tree node

    static class Node {
        int data;
        Node left, right;

        // Constructor to initialize node with data
        public Node(int key) {
            data = key;
            left = right = null;
        }
    }

    public static void main(String[] args) {
        // Step 1: Create the root node
        Node root = new Node(1);

        /*
        Tree after Step 1:

            🔵1
           /   \
        ❌     ❌
        */

        // Step 2: Add left child to root
        root.left = new Node(2);

        /*
        Tree after Step 2:

            🔵1
           /   \
        🔵2     ❌
       /   \
    ❌     ❌
        */

        // Step 3: Add right child to root
        root.right = new Node(3);

        /*
        Tree after Step 3:

            🔵1
           /   \
        🔵2     🔵3
       /  \    /   \
    ❌   ❌  ❌   ❌
        */

        // Step 4: Add left child to node 3
        root.right.left = new Node(5);

        /*
        Final Tree after Step 4:

              🔵1
            /     \
         🔵2       🔵3
        /  \     /    \
      ❌  ❌   🔵5     ❌
               / \
             ❌   ❌
        */

        // Displaying the tree structure in text
        System.out.println("Root: " + root.data);
        System.out.println("Left Child of Root: " + root.left.data);
        System.out.println("Right Child of Root: " + root.right.data);
        System.out.println("Left Child of Right Node: " + root.right.left.data);
    }
}

/*
=====================================================
❓ WHY Node CLASS IS DECLARED AS `static`
=====================================================

Reason:
- Node is defined INSIDE the Main class.
- A non-static inner class always needs an object of the outer class.
- But here, Node represents a DATA STRUCTURE, not behavior of Main.

If Node was NOT static:
- You would need to create Main object first:
    Main m = new Main();
    Main.Node n = m.new Node(1);   ❌ unnecessary & messy

By making Node `static`:
- Node belongs to the class, not to any instance of Main.
- We can directly create nodes like:
    Node root = new Node(1);       ✅ clean & correct

📌 Interview one-liner:
“Node is static because it doesn’t depend on Main’s instance; it’s just a tree node structure.”
*/


/*
=====================================================
❓ WHY CONSTRUCTOR IS `public`
=====================================================

Reason:
- We want to create Node objects freely wherever needed.
- Even though Node is inside Main, `public` is standard practice.

If constructor was private:
- You could NOT create nodes outside the class ❌

Example needed:
    root.left = new Node(2);
    root.right = new Node(3);

📌 Best practice:
- Keep constructors public unless you want to restrict object creation.

📌 Interview one-liner:
“Constructor is public to allow easy and unrestricted creation of tree nodes.”
*/


/*
=====================================================
❓ WHY `this` IS NOT USED IN CONSTRUCTOR ASSIGNMENT
=====================================================

Constructor code:
    public Node(int key) {
        data = key;
        left = right = null;
    }

Reason:
- There is NO naming conflict.
- Parameter name is `key`
- Instance variable name is `data`

So Java clearly understands:
    data → instance variable
    key  → constructor parameter

When `this` IS required:
    public Node(int data) {
        this.data = data;   // needed because names are same
    }

📌 Rule to remember:
- Use `this` ONLY when parameter name and instance variable name are SAME.

📌 Interview one-liner:
“`this` is not required here because there’s no variable shadowing.”
*/


/*
=====================================================
📌 QUICK INTERVIEW SUMMARY (VERY IMPORTANT)
=====================================================

✔ Node is static → avoids dependency on Main object
✔ Constructor is public → allows node creation freely
✔ `this` not used → no naming conflict

These choices make the code:
- Cleaner
- Easier to use
- Interview-ready
*/
