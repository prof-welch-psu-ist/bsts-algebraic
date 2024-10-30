package edu.psu.ist;

// "immutable tree"
public interface BSTree {

    // "mini-design pattern":
    // "static factory method"
    static BSTree empty() {
        return new Empty();
    }

    /** Inserts {@code item} into the binary search tree. */
    BSTree insert(Integer toAdd);

    String preOrder();

    //String inOrder();
    //String postOrder();

    int size();
}
