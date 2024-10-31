package edu.psu.ist;

public final class Exercises {

    /**
     * Generates a greeting message based on the contents of a binary search tree (BST).
     *
     * <p>This method demonstrates the use of Java's <a href="https://blog.jetbrains.com/idea/2020/09/java-15-and-intellij-idea/">sealed interfaces and records</a>,
     * as highlighted in an excellent article by JetBrains. Sealed interfaces restrict which other classes or interfaces may extend or implement them, enhancing type safety and
     * control over class hierarchies.</p>
     *
     * <p>Pattern matching is utilized via <a href="https://docs.oracle.com/en/java/javase/17/language/switch-expressions-and-statements.html">switch expressions</a> introduced in Java 17.
     * Specifically, this method employs a "deconstruction pattern"—a form of pattern matching found in various programming languages.</p>
     *
     * <p>In the context of this method:</p>
     * <ul>
     *   <li><strong>Deconstruction Pattern:</strong> Breaks down complex data structures into their constituent parts. Here, it deconstructs the BST into its left subtree, data, and right subtree.</li>
     *   <li><strong>Wildcard Match (_):</strong> Represents a 'wildcard' that matches any value but does not bind it to a variable. It's used to ignore certain parts of the data structure.</li>
     * </ul>
     *
     * <p>The method handles two cases of the BST:</p>
     * <ul>
     *   <li><strong>NonEmpty:</strong> Indicates a BST node that contains data. It deconstructs the node into its left subtree (<code>l</code>), data (<code>d</code>), and ignores the right subtree using the wildcard (<code>_</code>).</li>
     *   <li><strong>Empty:</strong> Represents an empty BST, matched using the wildcard.</li>
     * </ul>
     *
     * @param t the binary search tree to generate a greeting from
     * @return a greeting string based on the contents of the tree
     *
     * @see <a href="https://blog.jetbrains.com/idea/2020/09/java-15-and-intellij-idea/">Sealed Interfaces and Records Article by JetBrains</a>
     * @see <a href="https://docs.oracle.com/en/java/javase/17/language/switch-expressions-and-statements.html">Java Switch Expressions and Statements Documentation</a>
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
