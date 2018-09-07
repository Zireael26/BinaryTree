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

        this.display(node.left); //faith in left child
        this.display(node.right); // faith in right child
    }
}
