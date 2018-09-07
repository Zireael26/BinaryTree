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
    }
}
