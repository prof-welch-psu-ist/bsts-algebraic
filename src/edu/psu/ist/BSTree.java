package edu.psu.ist;

public sealed interface BSTree permits Empty, NonEmpty {

    // a "static factory" method for constructing an empty tree
    static BSTree empty() { return new Empty(); }

    /** Inserts {@code item} into the binary search tree. */
    BSTree insert(Integer toAdd);

    /** Returns a preorder string representation of this tree. */
    String preOrder();

    int size();
}
