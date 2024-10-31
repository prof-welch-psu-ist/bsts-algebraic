package edu.psu.ist;

public final class Exercises {

    private static int sum(BSTree tr) {
        // without
        if (tr.size() == 0) {
            return 0;
        }
        else if (tr instanceof NonEmpty neTr) { // neTr = "non-empty tree"
            return neTr.data() + sum(neTr.left()) + sum(neTr.right());
        }
        else { // Empty tree case (technically redundant given first if-stmt)
            return 0;
        }
        // using new fangled jdk23 style pattern matching:
        /*return switch (tr) {
            case NonEmpty(var l, var d, var r) -> d + sum(l) + sum(r);
            case Empty _                       -> 0;
        };*/
    }

    /**
     * Write a static method, {@code average}, that takes as a parameter
     * an {@link BSTree} and returns the average of all values in the tree.
     * <p>
     * <em>Do this recursively</em>
     */
    public static int average(BSTree tr) {
        return switch (tr) {
            case Empty _ -> 0;
            default      -> sum(tr) / tr.size();
        };
    }


}
