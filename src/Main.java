public class Main {

    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        System.out.println("\n" + binaryTree.size());
        // for the tree, lets enter input as
        // 50 true 25 true 12 false true 20 false false true 37 true 30 false false false true 75 true 62 false false true 87 false false
        binaryTree.display();

        System.out.println("Calculated Size: " + binaryTree.getSize());
        System.out.println("Maximum element of the entered tree is: " + binaryTree.max());
    }
}
