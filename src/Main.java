import java.util.LinkedList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        BinaryTree binaryTree = new BinaryTree();
        System.out.println("\n" + binaryTree.size());
        // for the tree, lets enter input as
        // 50 true 25 true 12 false true 20 false false true 37 true 30 false false false true 75 true 62 false false true 87 false false
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

        binaryTree.printRootToLeafPaths(200);
    }

    // Print n=binaries upto n
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
