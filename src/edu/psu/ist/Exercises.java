package edu.psu.ist;

public class Exercises {

    // write a static method, average, that takes a BST<Integer>
    // and returns an Integer. The integer returned should be the
    // average

    private static int sum(BSTree tr) {
        return switch (tr) {
            case NonEmpty(var l, var d, var r) -> d + sum(l) + sum(r);
            case Empty _                       -> 0;
        };
    }
    
    /**
     * Write a static method, {@code average}, that takes as a parameter
     * an {@link BSTree} and returns the average of all values in the tree.
     * <p>
     * <em>Do this recursively</em>
     */
    public static int average(BSTree tr) {
        // step 1: compute the sum of all values in the tree
        var sum =
        return
    }
}
