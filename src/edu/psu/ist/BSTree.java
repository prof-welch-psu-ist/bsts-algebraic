package edu.psu.ist;

public interface BSTree {

    // static factory method
    static BSTree empty() { throw new UnsupportedOperationException("not done"); }

    /** Inserts {@code item} into the binary search tree. */
    BSTree insert(Integer toAdd);

    String preOrder();

    String inOrder();

    String postOrder();

    int size();
}
