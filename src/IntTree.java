/**
 * Created by Olivi on 08-10-2016.
 */

// Simple binary tree class that includes methods to construct a tree of ints,
// to print the structure, and to print the data using a preorder, inorder or
// postorder traversal.  The trees built have nodes numbered starting with 1
// and numbered sequentially level by level with no gaps in the tree.  The
// documentation refers to these as "sequential trees."

public class IntTree {
    private IntTreeNode overallRoot;

    // post: constructs an empty tree
    public IntTree() {
        overallRoot = null;
    }

    // post: value is added to overall tree so as to preserve the
    //       binary search tree property
    public void add(int value) {
        overallRoot = add(overallRoot, value);
    }

    // post: value is added to given tree so as to preserve the
    //       binary search tree property
    private IntTreeNode add(IntTreeNode root, int value) {
        if (root == null) {
            root = new IntTreeNode(value);
        } else if (value <= root.data) {
            root.left = add(root.left, value);
        } else {
            root.right = add(root.right, value);
        }
        return root;
    }

    // post: returns true if overall tree contains value
    public boolean contains(int value) {
        return contains(overallRoot, value);
    }

    // post: returns true if given tree contains value
    private boolean contains(IntTreeNode root, int value) {
        if (root == null) {
            return false;
        } else if (root.data == value) {
            return true;
        } else if (value < root.data) {
            return contains(root.left, value);
        } else {
            return contains(root.right, value);
        }
    }

    // pre : max >= 0 (throws IllegalArgumentException if not)
    // post: constructs a sequential tree with given number of nodes
    public IntTree(int max) {
        if (max < 0) {
            throw new IllegalArgumentException("max: " + max);
        }
        overallRoot = buildTree(1, max);
    }

    // post: returns a sequential tree with n as its root unless n is greater
    //       than max, in which case it returns an empty tree
    private IntTreeNode buildTree(int n, int max) {
        if (n > max) {
            return null;
        } else {
            IntTreeNode left = buildTree(2 * n, max);
            IntTreeNode right = buildTree(2 * n + 1, max);
            return new IntTreeNode(n, left, right);
        }
    }

    // post: prints the tree contents using a preorder traversal
    public void printPreorder() {
        System.out.print("preorder:");
        printPreorder(overallRoot);
        System.out.println();
    }

    // post: prints in preorder the tree with given root
    private void printPreorder(IntTreeNode root) {
        if (root != null) {
            System.out.print(" " + root.data);
            printPreorder(root.left);
            printPreorder(root.right);
        }
    }

    // post: prints the tree contents using an inorder traversal
    public void printInorder() {
        System.out.print("inorder:");
        printInorder(overallRoot);
        System.out.println();
    }

    // post: prints in inorder the tree with given root
    private void printInorder(IntTreeNode root) {
        if (root != null) {
            printInorder(root.left);
            System.out.print(" " + root.data);
            printInorder(root.right);
        }
    }

    // post: prints the tree contents using a postorder traversal
    public void printPostorder() {
        System.out.print("postorder:");
        printPostorder(overallRoot);
        System.out.println();
    }

    // post: prints in postorder the tree with given root
    private void printPostorder(IntTreeNode root) {
        if (root != null) {
            printPostorder(root.left);
            printPostorder(root.right);
            System.out.print(" " + root.data);
        }
    }

    // post: prints the tree contents, one per line, following an inorder
    //       traversal and using indentation to indicate node depth; prints
    //       right to left so that it looks correct when the output is rotated.
    public void printSideways() {
        printSideways(overallRoot, 0);
    }

    // post: prints in reversed preorder the tree with given root, indenting
    //       each line to the given level
    private void printSideways(IntTreeNode root, int level) {
        if (root != null) {
            printSideways(root.right, level + 1);
            for (int i = 0; i < level; i++) {
                System.out.print("    ");
            }
            System.out.println(root.data);
            printSideways(root.left, level + 1);
        }
    }

    //Ex1, p. 1080
    public int countLeftNodes() {
        if (overallRoot != null) {
            return countLeftNodes(overallRoot);
        }
        return 0;
    }

    private int countLeftNodes(IntTreeNode node) {
        int count = 0;
        if (node != null) {
            if (node.left != null) {
                count++;
            }
            return count + countLeftNodes(node.left) + countLeftNodes(node.right);
        }
        return 0;
    }

    //Ex2
    public int countEmpty () {
      if (overallRoot != null) {
          return countEmpty(overallRoot);
      }
      return 0;
    }

    private int countEmpty (IntTreeNode node) {
        int count = 0;
        if (node != null) {
            if (node.left == null || node.right == null ) {
                if (node.left == null) {
                    count++;
                }
                if (node.right == null) {
                    count++;
                }
            }
            return count + countEmpty(node.left) + countEmpty(node.right);
        }
        return count;
    }

    //Ex3
    public int depthSum () {
        if (overallRoot != null) {
            return depthSum(overallRoot, 1);
        }
        return 0;
    }


    private int depthSum (IntTreeNode node, int level) {
        if (node == null) {
            return 0;
        }
        return node.data * level + depthSum(node.left, level + 1) + depthSum(node.right, level + 1);
    }

    //s. 1054 //////////BLIVER IKKE BRUGT
    private int countLevels (IntTreeNode node) {
        if (node == null) {
            return 0;
        } else {
            return 1 + Math.max(countLevels(node.left), countLevels(node.right));
        }
    }

    //Ex3
    public int countEvenBranches () {
        if (overallRoot != null) {
            return countEvenBranches(overallRoot);
        }
        return 0;
    }

    private int countEvenBranches (IntTreeNode node) {
        int evenBranches = 0;

        if (node != null) {
            if (node.right == null && node.left == null) {
            } else if (node.data % 2 == 0) {
                evenBranches++;
            }
            evenBranches+= countEvenBranches(node.left) + countEvenBranches(node.right);
        }
        return evenBranches;
    }

    //Ex5
    private int levelmax = 0;
    public void printLevel (int level) {
        levelmax = level;
        if (overallRoot != null) {
            printLevel(overallRoot, 1);
        }
    }

    private void printLevel (IntTreeNode node, int level) {
        if (node != null) {
           if(level == levelmax) {
               System.out.println(node.data);
           }
            printLevel(node.left, level + 1);
            printLevel(node.right, level + 1);
        }
    }

    //Ex6
    public void printLeaves () {
        if (overallRoot != null) {
            printLeaves(overallRoot);
        }
    }

    private void printLeaves(IntTreeNode node) {
        if (node != null) {
            if (node.right == null || node.left == null){
                System.out.println(node.data);
            }
            printLeaves(node.left);
            printLeaves(node.right);
        }

    }

    //Ex7
    public boolean isFull() {
        if (overallRoot != null) {
            return isFull(overallRoot);
        }
        return false;
    }

    private boolean isFull(IntTreeNode node) {
        if (node != null) {
            if (node.right == null && node.left == null) {
                return true;
            }
            else if (node.right != null && node.left != null ) {
                return (isFull(node.left) && isFull(node.right));
            }

            else {
                System.out.println("No leaves");
                return false;
            }
        }
        return false;
    }

    //Ex8 - VIRKER IKKE
    public String toString () {
        if (overallRoot != null) {
            toString(overallRoot);
        }
        return "";
    }

    private String toString (IntTreeNode node) {
        String leftSubtree = "";
        String rightSubTree = "";

        if (node == null) {
            return "empty";
        }

        else if (node != null) {
            while (node.left != null && node.right != null) {
                leftSubtree += node.data;
                rightSubTree += node.data;
            }
        }

        return "(" + overallRoot + toString(node.right) + toString(node.left) +")";
    }

    //Ex9 - VIRKER IKKE!!!
    public boolean equals () {
        if (overallRoot != null) {
            return equals(overallRoot);
        }
        return false;
    }

    private boolean equals (IntTree tree) {
        if (tree != null) {
            if (overallRoot == tree.overallRoot) {
                return true;
            }
            else return false;
        }
        return false;
    }

    //Ex10 - IKKE LAVET




}
