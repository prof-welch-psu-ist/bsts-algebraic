package edu.psu.ist;

public record Empty() implements BSTree {

    @Override public BSTree insert(Integer toAdd) {
        // becomes a new leaf
        return new NonEmpty(new Empty(), toAdd, new Empty());
    }

    @Override public String preOrder() {
        return "";
    }

    @Override public int size() { return 0;}
}
