import java.util.LinkedList;
import java.util.Scanner;

public class BinaryTree {

    private class Node{
        int data;
        Node left;
        Node right;

        public Node(int data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    private Node root;
    private int size;

    public BinaryTree() {
        Scanner scn = new Scanner(System.in);
        this.root = this.takeInput(scn, null, false);
    }

//    private Node construct(Scanner scn,Node parent, boolean isLeftChild) {
//        if (isLeftChild) {
//
//        }
//    }

    private Node takeInput(Scanner scn, Node parent, boolean isLeftChild) {
        if (parent == null){
            System.out.print("Enter the data for root: ");
        } else {
            if (isLeftChild) {
                System.out.print("Enter the data for left child of " + parent.data + ": ");
            } else {
                System.out.print("Enter the data for right child of " + parent.data + ": ");
            }
        }

        int childData = scn.nextInt();
        Node child = new Node(childData, null, null);
        size++;

        System.out.print("Do you have a left child for " + child.data + ": "); // answer in true or false
        boolean hasLeftChild = scn.nextBoolean();

        if (hasLeftChild) {
            child.left = this.takeInput(scn, child, true);
        }

        System.out.print("Do you have a right child for " + child.data + ": ");
        boolean hasRightChild = scn.nextBoolean();
        if (hasRightChild) {
            child.right = this.takeInput(scn, child, false);
        }

        return child;
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return (this.size() == 0);
    }

    public void display() {
        this.display(this.root);
    }

    private void display(Node node) { // expectation is that this function will display the whole binary tree as: left child -> node <- right child
        if(node == null) { // if there are no children, return
            return;
        }

        String outputString = "";
        outputString += node.left != null ? node.left.data:"."; // if node.left is not null, add data to string, else add full stop
        outputString += " -> " + node.data + " <- ";
        outputString += node.right != null ? node.right.data:"."; // if node.left is not null, add data to string, else add full stop

        System.out.println(outputString);

        this.display(node.left); // faith in left child
        this.display(node.right); // faith in right child
    }

    public int getSize(){ // method to calculate size, if we don't have a size variable
        return this.getSize(this.root);
    }

    private int getSize(Node node) {  //  expectation is that the method will count and return the size of the tree
        if (node == null) { // base case, if node is null, its size is 0
            return 0;
        }

        int leftSize = this.getSize(node.left); // faith that we will get size of left subtree
        int rightSize = this.getSize(node.right); // faith that we will get size of right subtree

        return (leftSize + rightSize + 1); // so actual size will be leftSize + rightSize + 1 (for the root node itself)
    }

    public int max() {
        return this.max(this.root);
    }

    private int max(Node node) { // expectation is that this function returns the max of the given tree

        if (node == null) { // base case, if node is null, it shouldn't make a contribution to max
            return Integer.MIN_VALUE;
        }

        int leftMax = this.max(node.left); // faith that this call returns max of left subtree
        int rightMax = this.max(node.right); // faith that this call returns the max of right subtree

        // simply compare the three values of node.data, leftMax, rightMax using Math.max()
        return Math.max(node.data, Math.max(leftMax, rightMax));
    }

    public int height() { // max depth (how far is its deepest node), depth =  number of edges away from root
        return this.height(this.root);
    }

    private int height(Node node) {
        if (node == null) {
            return -1; // return -1 if height is counted by edges, or 0 if height is counted by nodes
        }

        int leftHeight = this.height(node.left); // faith that it will return the height of the left subtree
        int rightHeight = this.height(node.right); // faith that it will return the right subtree
        // for every leaf node, the comparison below will return max(-1, -1) + 1 = 0 when counting by edges
        // and max(0, 0) + 1 = 1 when counting by nodes
        return (Math.max(leftHeight, rightHeight) + 1);
    }

    public boolean find(int key) {
        return this.find(this.root, key);
    }

    private boolean find(Node node, int key) {
        if (node == null) { // base case for when you hit leaf nodes
            return false;
        }

        if (node.data == key) { // if key is found in the root node itself
            return true;
        }

        boolean foundInLeftChild = this.find(node.left, key); // faith that this call will find the key in left subtree
        if (foundInLeftChild) {
            return true;
        }

        boolean foundInRightChild = this.find(node.right, key); // faith that this call will find the key in right subtree
        if (foundInRightChild) {
            return true;
        }

        return false;
    }

    // Pre-Order traversal can be thought of in 3 ways
    // Node-Left-Right
    // Print while going into recursion
    // Euler-left path
    // Root will be the first item
    public void preOrderTraversal() {
        this.preOrderTraversal(this.root);
        System.out.println(".");
    }

    private void preOrderTraversal(Node node) {
        if (node == null) { // when there are no children (leaf-node), return
            return;
        }

        // faith calls for subtrees
        System.out.print(node.data + ", "); // Node
        preOrderTraversal(node.left);         // Left
        preOrderTraversal(node.right);        // Right
    }

    // Post-Order traversal is like
    // Left-Right-Node
    // Print while coming out of recursion
    // Euler-right path
    // Root will be last
    public void postOrderTraversal() {
        this.postOrderTraversal(this.root);
        System.out.println(".");
    }

    private void postOrderTraversal(Node node) {
        if (node == null) {
            return;
        }

        // faith calls for subtrees
        postOrderTraversal(node.left);      // Left
        postOrderTraversal(node.right);     // Right
        System.out.print(node.data + ", "); // Node
    }

    // In-Order Traversal is like
    // Left-Node-Right
    // Root will be in the middle
    public void inOrderTraversal() {
        this.inOrderTraversal(this.root);
        System.out.println(".");
    }

    private void inOrderTraversal(Node node) {
        if(node == null) {
            return;
        }

        // faith calls for subtrees
        inOrderTraversal(node.left);         // Left
        System.out.print(node.data + ", ");  // Node
        inOrderTraversal(node.right);        // Right
    }

    // BFS in graphs
    // 3 Step process
    // Remove from queue, Print, Enqueue its children
    public void levelOrderTraversal() {
        this.levelOrderTraversal(this.root);
    }

    // Iterative Algorithm
    private void levelOrderTraversal(Node node) {
        LinkedList<Node> queue = new LinkedList<>();

        queue.addLast(node);

        while (!queue.isEmpty()) {
            Node removedNode = queue.removeFirst();     // Step 1
            System.out.print(removedNode.data + ", ");  // Step 2

            if (removedNode.left != null) {             // Step 3
                queue.addLast(removedNode.left);
            }
            if (removedNode.right != null) {             // Step 3
                queue.addLast(removedNode.right);
            }
        }

        System.out.println(".");
    }

    public void printSingleChild() {
        this.printSingleChild(this.root, this.root.left);
        this.printSingleChild(this.root, this.root.right);
    }

    private void printSingleChild(Node parent, Node child) {
        if (child == null) {
            return;
        }

        if ((parent.left == child && parent.right == null) // to check if the child is a single child, we see if
                || (parent.right == child && parent.left == null)) { // the parent's other child is null
            System.out.print(child.data + " "); // and then print this child, if its true
        }

        this.printSingleChild(child, child.left);   // faith call to left subtree
        this.printSingleChild(child, child.right);  // faith call to right subtree
    }

    public void removeLeafNodes() {
        this.removeLeafNodes(this.root, this.root.left);
        this.removeLeafNodes(this.root, this.root.right);
    }

    private void removeLeafNodes(Node parent, Node child) {
        if (child == null) { // when child be null, just return because null.left and null.right ain't a thing
            return;
        }

        if (child.left == null && child.right == null) { // if child is a leaf node
           if (parent.left == child) { // then find out if its the left child or the right child
               parent.left = null;     // and remove it accordingly
           } else {
               parent.right = null;
           }
           return; // since we have removed the leaf, lets return from here
        }

        this.removeLeafNodes(child, child.left);  // recursive call to left subtree
        this.removeLeafNodes(child, child.right); // recursive call to right subtree
    }

    public void printRootToLeafPaths(int target) {
        this.printRootToLeafPaths(this.root, 0, "", target);
    }

    private void printRootToLeafPaths(Node node, int sumSofar, String pathSoFar, int target) {
        if(node == null) {
            return;
        }

        if (node.left == null &&node.right == null) { // when you reach a leaf node
            if (sumSofar + node.data < target) { // add the leaf data first and check if it is still less than target
                System.out.println(pathSoFar + " -> " + node.data); // if so, print the path
            }
            return;
        }

        // faith call to left subtree
        printRootToLeafPaths(node.left, sumSofar + node.data, pathSoFar + " -> " + node.data, target);
        // faith call to right subtree
        printRootToLeafPaths(node.right, sumSofar + node.data, pathSoFar + " -> " + node.data, target);
    }
}