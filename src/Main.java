import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;


public class Main {
    public static <E extends Comparable<E>> void main(String[] args) throws FileNotFoundException{


        Scanner sc = new Scanner(System.in);
        System.out.println("Type the name of the file to read the binary tree from: ");
        String fileName = sc.nextLine();
        BST<Integer> tree = newTree(fileName);

        while (true) {
            System.out.println("1. Add a value to current tree");
            System.out.println("2. Delete a value from current tree");
            System.out.println("3. Check for a value in current tree");
            System.out.println("4. Print tree using In Order");
            System.out.println("5. Print tree using Pre Order");
            System.out.println("6. Print tree using Post Order");
            System.out.println("7. Print number of nodes");
            System.out.println("8. Print number of leaf nodes");
            System.out.println("9. Print height of current tree");
            System.out.println("10. Clear current tree");
            System.out.println("11. QUIT PROGRAM");
            System.out.println("Type a number to select: ");
            int value;

            int input = sc.nextInt();
            String buffer = sc.nextLine();
            switch (input) {
                case 1:
                    System.out.println("Enter value to be added: ");
                    value = sc.nextInt();
                    tree.add(value);
                    break;
                case 2:
                    System.out.println("Enter value to be deleted: ");
                    value = sc.nextInt();
                    tree.delete(value);
                    break;
                case 3:
                    System.out.println("Enter value to check for: ");
                    value = sc.nextInt();
                    if (tree.contains(value)) {
                        System.out.println("Tree contains " + value);
                    }
                    else {
                        System.out.println("Tree does not contain " + value);
                    }
                    break;
                case 4:
                    tree.printInorder();
                    System.out.println(" ");
                    break;
                case 5:
                    tree.printPreorder();
                    System.out.println(" ");
                    break;
                case 6:
                    tree.printPostorder();
                    System.out.println(" ");
                    break;
                case 7:
                    System.out.println("Number of nodes: " + tree.countNodes());
                    break;
                case 8:
                    System.out.println("Number of leaf nodes: " + tree.countLeafNodes());
                    break;
                case 9:
                    System.out.println("Height of tree: " + tree.getHeight());
                    break;
                case 10:
                    System.out.println("Tree cleared");
                    System.out.println("Type the name of the file to read the new binary tree from: ");
                    String x = sc.nextLine();
                    tree = newTree(x);
                    break;
                case 11:
                    sc.close();
                    break;
            }
        }
    }


    public static BST<Integer> newTree(String fileName) throws FileNotFoundException{
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);
        BST<Integer> output = new BST<>(new TreeNode<Integer>(scanner.nextInt(), null, null));

        while (scanner.hasNextLine()) {
            int value = scanner.nextInt();
            output.add(value);
        }

        scanner.close();
        return output;
    }
}