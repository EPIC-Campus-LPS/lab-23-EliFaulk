public class BST<E extends Comparable<E>> {

    private TreeNode<E> root;


    public BST(TreeNode<E> root) {
        this.root = root;
    }


    public void add(E value) {
        TreeNode<E> current = root;
        while (true) {
            E currentValue = current.getValue();
            if (value.compareTo(currentValue) <= 0) {   //If new value is <= current's, check left
                if (current.getLeftChild() != null) {
                    current = current.getLeftChild();
                } else {
                    current.setLeftChild(new TreeNode<E>(value, null, null));
                    break;
                }
            }
            else {  //If new value > current's, check right
                if (current.getRightChild() != null) {
                    current = current.getRightChild();
                } else {
                    current.setRightChild(new TreeNode<E>(value, null, null));
                    break;
                }
            }
        }

    }


    public boolean contains(E value) {
        TreeNode<E> current = root;
        while (true) {
            if (current == null) {
                break;
            }
            E currentValue = current.getValue();
            if (value.compareTo(currentValue) == 0) {
                return true;
            }
            else if (value.compareTo(currentValue) < 0) {
                current = current.getLeftChild();
            }
            else if (value.compareTo(currentValue) > 0) {
                current = current.getRightChild();
            }
        }
        return false;
    }


    public int countNodes() {
        if (root == null) {
            return 0;
        }
        BST<E> leftTree = new BST<E>(root.getLeftChild());
        BST<E> rightTree = new BST<E>(root.getRightChild());
        return 1 + leftTree.countNodes() + rightTree.countNodes();
    }


    public int countLeafNodes() {
        if (root.getLeftChild() == null && root.getRightChild() == null) {
            return 1;
        }
        int total = 0;
        if (root.getLeftChild() != null) {
            BST<E> leftTree = new BST<E>(root.getLeftChild());
            total += leftTree.countLeafNodes();
        }
        if (root.getRightChild() != null) {
            BST<E> rightTree = new BST<E>(root.getRightChild());
            total += rightTree.countLeafNodes();
        }
        return total;
    }


    public int getHeight() {
        if (root.getLeftChild() == null && root.getRightChild() == null) {
            return 1;
        }
        int height = 0;
        int left = 0;
        int right = 0;
        if (root.getLeftChild() != null) {
            BST<E> leftTree = new BST<E>(root.getLeftChild());
            left = 1 + leftTree.getHeight();
        }
        if (root.getRightChild() != null) {
            BST<E> rightTree = new BST<E>(root.getRightChild());
            right = 1 + rightTree.getHeight();
        }
        if (right > left) {
            height += right;
        } else {
            height += left;
        }
        return height;
    }


    public void printInorder() {
        BST<E> leftTree;
        if (root.getLeftChild() != null) {
            leftTree = new BST<E>(root.getLeftChild());
            leftTree.printInorder();
        }
        System.out.print(root.getValue() + " ");
        BST<E> rightTree;
        if (root.getRightChild() != null) {
            rightTree = new BST<E>(root.getRightChild());
            rightTree.printInorder();
        }
    }


    public void printPreorder() {
        System.out.print(root.getValue() + " ");
        BST<E> leftTree;
        if (root.getLeftChild() != null) {
            leftTree = new BST<E>(root.getLeftChild());
            leftTree.printPreorder();
        }
        BST<E> rightTree;
        if (root.getRightChild() != null) {
            rightTree = new BST<E>(root.getRightChild());
            rightTree.printPreorder();
        }
    }


    public void printPostorder() {
        BST<E> leftTree;
        if (root.getLeftChild() != null) {
            leftTree = new BST<E>(root.getLeftChild());
            leftTree.printPostorder();
        }
        BST<E> rightTree;
        if (root.getRightChild() != null) {
            rightTree = new BST<E>(root.getRightChild());
            rightTree.printPostorder();
        }
        System.out.print(root.getValue() + " ");
    }


    public E delete(E value) {
        TreeNode<E> current = root;
        TreeNode<E> parent = root;
        TreeNode<E> deleting;
        boolean isLeftChild = false;
        while (true) {
            if (value.compareTo(current.getValue()) == 0) {
                break;
            }
            else if (value.compareTo(current.getValue()) < 0) {
                isLeftChild = true;
                parent = current;
                current = current.getLeftChild();
            }
            else if (value.compareTo(current.getValue()) > 0) {
                isLeftChild = false;
                parent = current;
                current = current.getRightChild();
            }
        }

        deleting = current;

        if (current.getLeftChild() == null && current.getRightChild() == null) { //If no children, delete node
            if (isLeftChild) {
                parent.setLeftChild(null);
            }
            else {
                parent.setRightChild(null);
            }
        }
        else if (current.getLeftChild() != null) {   //If one child, delete and replace with child
            if (isLeftChild) {
                parent.setLeftChild(current.getLeftChild());
            }
            else {
                parent.setLeftChild(current.getLeftChild());
            }
        }
        else if (current.getRightChild() != null) {
            if (isLeftChild) {
                parent.setLeftChild(current.getRightChild());
            }
            else {
                parent.setRightChild(current.getRightChild());
            }
        }
        else {  //If two children, delete, replaced with left, and re-add right into tree
            if (isLeftChild) {
                parent.setLeftChild(current.getLeftChild());
            }
            else {
                parent.setRightChild(current.getLeftChild());
            }
            E rightValue = current.getRightChild().getValue();
            TreeNode<E> x = root;
            while (true) {
                E currentValue = x.getValue();
                if (rightValue.compareTo(currentValue) <= 0) {   //If new value is <= current's, check left
                    if (x.getLeftChild() != null) {
                        x = x.getLeftChild();
                    } else {
                        x.setLeftChild(current.getRightChild());
                        break;
                    }
                }
                else {  //If new value > current's, check right
                    if (x.getRightChild() != null) {
                        x = x.getRightChild();
                    } else {
                        x.setRightChild(current.getRightChild());
                        break;
                    }
                }
            }
        }
        return deleting.getValue();
    }
}
