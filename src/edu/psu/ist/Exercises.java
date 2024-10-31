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
     *
     * @see <a href="https://blog.jetbrains.com/idea/2020/09/java-15-and-intellij-idea/">Sealed Interfaces and Records Article</a>
     * @see <a href="https://docs.oracle.com/en/java/javase/17/language/switch-expressions-and-statements.html">Java Switch Documentation</a>
     */
    public static String hello(BSTree t) {
        String myReturnStr = switch (t) {
            case NonEmpty(var l, var d, _) ->
                    "Hello, I'm a BST with data: " + d + "\n" +
                            "Here's the size of my left subtree: " + l.size();
            case Empty _                   -> "Empty :-(";
        };
        return myReturnStr;
    }

    public static void main(String[] args) {
        var exampleBst = BSTree.empty()//
                .insert(10) //
                .insert(5) //
                .insert(39);
        System.out.println(hello(exampleBst));
    }
}
