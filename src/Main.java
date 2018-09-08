import java.util.LinkedList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        BinaryTree binaryTree = new BinaryTree();
        System.out.println("\n" + binaryTree.size());
        // for the tree, lets enter input as
        // 50 true 25 true 12 false true 20 false false true 37 true 30 false false false true 75 true 62 false false true 87 false false
        // 1 true 2 true 3 true 4 false false true 5 true 6 false true 7 false false true 8 true 9 true 10 false false true 11 true 12 true 14 false false true 15 false false true 13 false false true 16 true 17 false false true 18 false false true 19 false false true 20 false false
        binaryTree.display();

        System.out.println("Calculated Size: " + binaryTree.getSize());
        System.out.println("Maximum element of the entered tree is: " + binaryTree.max());
        System.out.println("Height of the Binary tree is: " + binaryTree.height());
        System.out.print("Enter the element you wish to search for: " );
        int key = scan.nextInt();
        System.out.println("\nElement found: " + binaryTree.find(key));

        binaryTree.preOrderTraversal();
        binaryTree.postOrderTraversal();
        binaryTree.inOrderTraversal();
        binaryTree.levelOrderTraversal();
        printBinaries(15);

        binaryTree.printSingleChild();
        System.out.println();
        binaryTree.printRootToLeafPaths(200);

        System.out.println(binaryTree.rootToNodePath(12));

        // print all nodes at distance 3 from the node with data 9
        binaryTree.printKFar(9, 3);

        System.out.println("******* Displaying after removing leaf nodes *********");
        binaryTree.removeLeafNodes();
        binaryTree.display();

        int[] preO = {50, 25, 12, 20, 37, 30, 75, 62, 87};
        int[] inO = {12, 20, 25, 30, 37, 50, 62, 75, 87};
        int[] postO = {20, 12, 30, 37, 25, 62, 87, 75, 50};
        System.out.println("*****************************************");
        BinaryTree constructedTree = new BinaryTree(inO, postO);
        constructedTree.display();
    }

    // Print n binaries upto n
    private static class Pair{
        int n;
        String binary;

        public Pair(int n, String binary) {
            this.n = n;
            this.binary = binary;
        }
    }

    public static void printBinaries(int n) {
        LinkedList<Pair> queue = new LinkedList<>();

        queue.add(new Pair(1, "1"));

        while (!queue.isEmpty()) {
            Pair removedPair = queue.removeFirst();

            System.out.println(removedPair.n + " -> " + removedPair.binary);

            if (2 * removedPair.n <= n) { // stop when the target n is reached
                queue.addLast(new Pair(2 * removedPair.n, removedPair.binary + "0"));       // add 2n to left
            }

            if ((2 * removedPair.n + 1) <= n) {
                queue.addLast(new Pair(2 * removedPair.n + 1, removedPair.binary + "1"));   // add 2n+1 to right
            }
        }
    }
}
