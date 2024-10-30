package edu.psu.ist;

public record NonEmpty(
        BSTree left, // <-- field
        Integer data, // <-- field
        BSTree right) implements BSTree {

    @Override public BSTree insert(Integer toAdd) {

        if (toAdd < this.data) {
            return new NonEmpty(left.insert(toAdd), data, right);
        }
        else if (toAdd > this.data) {
            return new NonEmpty(left, data, right.insert(toAdd));
        }
        else { // implies that toAdd == data (meaning: dup key!)
            throw new IllegalArgumentException("dup key");
        }
    }

    @Override public String preOrder() {
        String result = data + "";
        // first: test if there are nodes in the left subtree
        if (left.size() != 0) {
            result += " " + left.preOrder();
        }
        if (right.size() != 0) {
            result += " " + right.preOrder();
        }
        return result;
    }

    @Override public int size() {
        // we want to save the number of nodes in the left
        // subtree
        int leftCt = this.left.size(); // recursively calling size on left subtreee
        int rightCt = this.right.size();
        return 1 + leftCt + rightCt;
    }
}
