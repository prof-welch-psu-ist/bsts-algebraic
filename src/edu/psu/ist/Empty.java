package edu.psu.ist;

// this record will represent THE empty tree...
public record Empty() implements BSTree {

    @Override public BSTree insert(Integer toAdd) {
        return new NonEmpty(new Empty(), toAdd, new Empty());
    }

    @Override public String preOrder() {
        return "";
    }

    @Override public int size() {
        return 0;
    }
}
