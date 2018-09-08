import java.util.ArrayList;
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

//    public BinaryTree (int[] preOrder, int[] inOrder) {
//        this.root = this.construct(preOrder, 0, preOrder.length - 1, inOrder, 0, inOrder.length - 1);
//        this.size = preOrder.length;
//    }
//
//    // expectation -  we will get a tree from preOrder and inOrder arrays
//    // all indices are inclusive
//    private Node construct(int[] preOrder, int preStartIdx, int preLastIdx,
//                           int[] inOrder, int inStartIdx, int inLastIdx) {
//
//        if (preStartIdx > preLastIdx || inStartIdx > inLastIdx) {
//            return null;
//        }
//
//        Node node = new Node(0, null, null);
//        node.data = preOrder[preStartIdx];   // root data will be the start index of preOrder
//
//        int rootIdx = -1;   // To search the index of root node (found at start index of preOrder) in inOrder to separate left and right subtrees
//        for (rootIdx = inStartIdx; rootIdx <= inLastIdx; rootIdx++) {
//            if (preOrder[preStartIdx] == inOrder[rootIdx]) {
//                break;  // We now have the index of root inside our inOrder Traversal array
//                        // all items to its left are the left subtree, all items to its right are the right subtree
//            }
//        }               // rootIdx now contains the index of root in the inOrder Array
//
//        // inside the inOrder array
//        // left side will start at inStartIdx and end at rootIdx - 1
//        // right side will start at rootIdx + 1 and end at inLastIdx
//
//        // inside the preOrder Array
//        // left side will start at preStartIdx + 1 and end at preStartIdx + numElemLeft
//        // right side will start at preStartIdx + numElemLeft + 1 and end at preLastIdx
//        int numElemLeft = rootIdx - inStartIdx;
//        node.left = construct(preOrder, preStartIdx + 1, preLastIdx + numElemLeft,
//                inOrder, inStartIdx, rootIdx - 1);
//        node.right = construct(preOrder, preStartIdx + numElemLeft + 1, preLastIdx,
//                inOrder, rootIdx + 1, inLastIdx);
//
//        return node;
//    }

    public BinaryTree(int[] inOrder, int[] postOrder) {
        this.root = this.construct(inOrder, 0, inOrder.length - 1, postOrder, 0, postOrder.length - 1);
        this.size = postOrder.length;
    }

    private Node construct(int[] inOrder, int inStartIdx, int inLastIdx, int[] postOrder, int postStartIdx, int postLastIdx) {
        if (inStartIdx > inLastIdx || postStartIdx > postLastIdx) {
            return null;
        }

        Node node = new Node(0, null, null);
        node.data = postOrder[postLastIdx];  // root is the last element of postOrder array

        int rootIdx = -1;       // we will keep the index of root node in inOrder array here
        for (rootIdx = inStartIdx; rootIdx <= inLastIdx; rootIdx++) { // loop over inOrder array
            if (postOrder[postLastIdx] == inOrder[rootIdx]) { // and search for the root node
                break; // when found, break to store its index in rootIdx
            }
        }

        // inside the inOrder array
        // left side will start at inStartIdx and end at rootIdx - 1
        // right side will start at rootIdx + 1 and end at inLastIdx

        // inside the postOrder Array
        // left side will start at postStartIdx and end at postLastIdx - numElemRight - 1
        // right side will start at postLastIdx - numElemRight and end at postLastIdx - 1
        int numRightElem = inLastIdx - rootIdx; // calculate the number of elements in right subtree
        node.left = this.construct(inOrder, inStartIdx, rootIdx - 1,
                postOrder, postStartIdx,postLastIdx - numRightElem - 1);
        node.right = this.construct(inOrder, rootIdx + 1, inLastIdx,
                postOrder, postLastIdx - numRightElem, postLastIdx - 1);

        return node;
    }

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

    public ArrayList<Integer> rootToNodePath(int target) {
        return this.rootToNodePath(this.root, target);
    }

    private ArrayList<Integer> rootToNodePath(Node node, int target) {
        if (node == null) { // base case, a null node will return a new, empty ArrayList
            return new ArrayList<>();
        }

        if (node.data == target) { // if found, then return the path by adding the data to the ArrayList
            ArrayList<Integer> path = new ArrayList<>();
            path.add(node.data);
            return path;
        }

        // faith call to left child
        ArrayList<Integer> pathFromLeftChild = this.rootToNodePath(node.left, target);
        if (pathFromLeftChild.size() > 0) { // if there is a path that exists, then the ArrayList should be non-empty
            pathFromLeftChild.add(node.data);  // add current node to the path
            return pathFromLeftChild;          // return it
        }

        ArrayList<Integer> pathFromRightChild = this.rootToNodePath(node.right, target);
        if (pathFromRightChild.size() > 0) { // if there is a path that exists, then the ArrayList should be non-empty
            pathFromRightChild.add(node.data);  // add current node to the path
            return pathFromRightChild;          // return it
        }

        return new ArrayList<>();               // if not found, return empty ArrayList
    }

    // prints all nodes at 'k' distance from given node
    public void printKFar(int data, int k) {
        ArrayList<Node> path = this.rootToNodePathAsNodes(this.root, data);
        // the path obtained above will be [ node, ..., root ]
        // so path.get(0) will have the node itself and the last element in the ArrayList path will be the root

        for (int i = 0; (i < path.size()) && (i <= k); i++) {
            if (i == 0) {                   // for the node itself
                printKDown(path.get(0) ,k); // simply use printKDown
            } else if (i == k){                       // if i == k, then we don't need to look for its children,
                System.out.println(path.get(i).data); // this node is already 'k' far from starting node, so print data
            } else {
                Node currNode = path.get(i);       // we get current and previous both to see that we don't
                Node prevNode = path.get(i - 1);   // use printKDown on a path that already includes the current node

                if (currNode.left == prevNode) {  // if the current node is left, call printKDown on the right node with k-1
                    printKDown(currNode.right, k - i - 1); // i denotes how far the node is from the starting node
                } else {                          // else call printKDown on the left node with k-1
                    printKDown(currNode.left, k - i - 1);
                }
            }

        }

    }

    // prints all nodes below it at 'k' distance from given node
    private void printKDown(Node node, int k) {
        if (k < 0 || node == null) {
            return;
        }

        if (k == 0) {
            System.out.println(node.data);
            return;
        }

        printKDown(node.left, k - 1);
        printKDown(node.right, k - 1);
    }

    // Copy of rootToNodePath that returns an ArrayList of nodes instead of integers
    private ArrayList<Node> rootToNodePathAsNodes(Node node, int target) {
        if (node == null) { // base case, a null node will return a new, empty ArrayList
            return new ArrayList<>();
        }

        if (node.data == target) { // if found, then return the path by adding the data to the ArrayList
            ArrayList<Node> path = new ArrayList<>();
            path.add(node);
            return path;
        }

        // faith call to left child
        ArrayList<Node> pathFromLeftChild = this.rootToNodePathAsNodes(node.left, target);
        if (pathFromLeftChild.size() > 0) { // if there is a path that exists, then the ArrayList should be non-empty
            pathFromLeftChild.add(node);  // add current node to the path
            return pathFromLeftChild;          // return it
        }

        ArrayList<Node> pathFromRightChild = this.rootToNodePathAsNodes(node.right, target);
        if (pathFromRightChild.size() > 0) { // if there is a path that exists, then the ArrayList should be non-empty
            pathFromRightChild.add(node);  // add current node to the path
            return pathFromRightChild;          // return it
        }

        return new ArrayList<>();               // if not found, return empty ArrayList
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

    public int diameter() {
        return this.diameter(this.root);
    }

    // calculates the maximum distance / distance between two farthest nodes of the tree
    private int diameter(Node node) {
        if (node == null) {
            return 0;
        }

        int leftDiameter = this.diameter(node.left); // distance between farthest nodes in left subtree
        int rightDiameter = this.diameter(node.right); // distance between farthest nodes in right subtree

        // these height function calls make the complexity to O(n^2) because the previous diameter function calls
        // are already causing euler traversal, on each element of which, height function makes another full euler traversal
        int leftHeight = this.height(node.left);       // max height of left subtree
        int rightHeight = this.height(node.right);     // max height of right subtree
        int factorThroughRoot = leftHeight + rightHeight + 2; // distance between farthest nodes across left and right tree, through root

        return Math.max(Math.max(leftDiameter, rightDiameter), factorThroughRoot);
    }

    private class DiaPair{
        int height;
        int diameter;
    }

    // diameter improved
    public int diameter2() {
        return this.diameter2(this.root).diameter;
    }

    private DiaPair diameter2(Node node) {
        if (node == null) { // all magic of O(n^2) to O(n) is here in the base case, as in a single call, both height and diameter are calculated
            DiaPair basePair = new DiaPair();
            basePair.height = -1;
            basePair.diameter = 0;
            return basePair;
        }
        DiaPair leftPair = this.diameter2(node.left);   //  left call of faith
        DiaPair rightPair = this.diameter2(node.right); //  right call of faith

        DiaPair myPair = new DiaPair();
        myPair.height = Math.max(leftPair.height, rightPair.height) + 1;
        myPair.diameter = Math.max(leftPair.height + rightPair.height + 2, Math.max(leftPair.diameter, rightPair.diameter));

        return myPair;
    }

    //
    public boolean isBalanced() {
        return this.isBalanced(this.root);
    }

    // the rule for being balanced is if both left and right nodes are balanced and if absolute difference of node heights is <=1
    private boolean isBalanced(Node node) {
        if (node == null) {
            return true;
        }

        boolean isLeftBalanced = this.isBalanced(node.left);
        boolean isRightBalanced = this.isBalanced(node.right);

        int leftHeight = this.height(node.left);
        int rightHeight = this.height(node.right);

        if (isLeftBalanced && isRightBalanced && (Math.abs(leftHeight - rightHeight) <= 1)) {
            return true;
        } else {
            return false;
        }
    }

    private class BalPair{
        boolean isBalanced;
        int height;
    }

    public boolean isBalanced2() {
        return this.isBalanced2(this.root).isBalanced;
    }

    // Best possible complexity
    private BalPair isBalanced2(Node node) {
        if (node == null) {
            BalPair basePair = new BalPair();
            basePair.isBalanced = true;
            basePair.height = -1;
            return basePair;
        }

        BalPair leftPair = this.isBalanced2(node.left);
        BalPair rightPair = this.isBalanced2(node.right);

        BalPair myPair = new BalPair();
        myPair.height = Math.max(leftPair.height, rightPair.height) + 1;
        myPair.isBalanced = (leftPair.isBalanced && rightPair.isBalanced &&
                (Math.abs(leftPair.height - rightPair.height) <= 1) );

        return myPair;
    }
}