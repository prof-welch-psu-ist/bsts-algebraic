package edu.psu.ist;

public final class Exercises {

    /**
     * Creates a greeting message based on the contents of a binary search tree (BST).
     *
     * <p>Uses <a href="https://docs.oracle.com/en/java/javase/17/language/switch-expressions-and-statements.html">Java 17 switch expressions</a> and pattern matching:</p>
     * <ul>
     *   <li><strong>Deconstruction:</strong> Breaks down a BST node into its left subtree, data, and right subtree.</li>
     *   <li><strong>Wildcard (_):</strong> Matches any value but ignores it.</li>
     * </ul>
     *
     * <p>Handled cases:</p>
     * <ul>
     *   <li><strong>NonEmpty:</strong> Deconstructs the node to use its data and left subtree.</li>
     *   <li><strong>Empty:</strong> Matches with a wildcard.</li>
     * </ul>
     *
     * @param t the binary search tree to create the greeting from
     * @return a greeting string based on the tree's contents
     * @see <a href="https://blog.jetbrains.com/idea/2020/09/java-15-and-intellij-idea/">Sealed Interfaces and Records Article</a>
     * @see <a href="https://docs.oracle.com/en/java/javase/17/language/switch-expressions-and-statements.html">Java Switch Documentation</a>
     */
    public static String hello(BSTree t) {
        String myReturnStr = switch (t) {
            case NonEmpty(var l, var d, _) ->
                    "Hello, I'm a BST with data: " + d + "\n" + "Here's the size of my left subtree: " + l.size();
            case Empty e -> "Empty :-(";
        };
        return myReturnStr;
    }

    // version 1: newer way
    public static int sumAll(BSTree t) {
        int theSum = switch (t) {
            case Empty e -> 0;
            case NonEmpty e -> {
                BSTree left = e.left();
                BSTree right = e.right();
                int data = e.data();
                yield data + sumAll(left) + sumAll(right);
            }
        };
        return theSum;
    }

    public static int sumAll2(BSTree t) {
        int theSum = switch (t) {
            case Empty _ -> 0;
            case NonEmpty(BSTree left, Integer d, BSTree right) -> {
                yield d + sumAll(left) + sumAll(right);
            }
        };
        return theSum;
    }

    public static int sumAll3(BSTree t) {
        return switch (t) {
            case Empty _ -> 0;
            case NonEmpty(var left, var d, var right) ->
                    d + sumAll(left) + sumAll(right);
        };
    }

    public static int sumLeafs(BSTree t) {
        return switch (t) {
            // case 1: looking at the empty tree
            case Empty _ -> 0;
            // case 2: we're looking at leaf node (left and right subtrees
            //          are empty)
            case NonEmpty(Empty _, var d, Empty _) -> d;
            // case 3: we're looking at a non-leaf node:
            case NonEmpty(var left, _, var right) ->
                    sumLeafs(left) + sumLeafs(right);
        };
    }

    public static int maxDepth(BSTree t) {
        return switch (t) {
            // case 1: looking at an empty tree
            case Empty _ -> 0;

            // case 2: we're looking at a leaf node (ALL leafs
            //          have a depth of 1).
            case NonEmpty(Empty _, _, Empty _) -> 1;

            // case 3: we're looking at a node that might or might
            //      not have a non-empty left or right
            case NonEmpty(var left, var d, var right) -> {
                int maxDepthOfLeft  = maxDepth(left);
                int maxDepthOfRight = maxDepth(right);
                if (maxDepthOfLeft < maxDepthOfRight) {
                    yield 1 + maxDepthOfRight;
                } else {
                    yield 1 + maxDepthOfLeft;
                }
            }
        };
    }

    // version 2: older version that uses explicit instanceof checks
    public static void main(String[] args) {
        var exampleBst = BSTree.empty();
        BSTree tr = BSTree.empty()
                .insert(10).insert(2)
                .insert(5)
                .insert(20)
                .insert(15);
        int res = Exercises.maxDepth(tr);
        System.out.println(res); // sum of all nodes is 50
    }
}
