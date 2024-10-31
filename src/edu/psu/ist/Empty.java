package edu.psu.ist;

// note: this could be modeled more (space) efficiently as a singleton
public record Empty() implements BSTree {

    @Override public BSTree insert(Integer toAdd) {
        return new NonEmpty(BSTree.empty(), toAdd, BSTree.empty());
    }

    @Override public String preOrder() {
        return "";
    }

    @Override public int size() {
        return 0;
    }

    @Override public String toString() {
        return "empty";
    }
}
