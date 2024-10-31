package edu.psu.ist;

public record NonEmpty(
            BSTree left,
            Integer data,
            BSTree right)
        implements BSTree {

    @Override public BSTree insert(Integer toAdd) {
        if (toAdd < data) {
            return new NonEmpty(left.insert(toAdd), data, right);
        } else if (toAdd > data) {
            return new NonEmpty(left, data, right.insert(toAdd));
        } else {
            throw new IllegalArgumentException("dup key");
        }
        // alternate less-imperative more/functional way of writing the above:
        /*return switch (this) {
            case NonEmpty(_, var d1, _) when toAdd < d1 ->
                    new NonEmpty(left.insert(toAdd), data, right);
            case NonEmpty(_, var d2, _) when toAdd > d2 ->
                    new NonEmpty(left, data, right.insert(toAdd));
            default -> throw new IllegalArgumentException("dup key");
        };*/
    }

    @Override public String preOrder() {
        var result = data + "";

        if (left.size() != 0) { // i.e. if the left isn't empty
            result += " " + left.preOrder();
        }
        if (right.size() != 0) {
            result += " " + right.preOrder();
        }
        return result;
    }

    @Override public int size() {
        int leftCt = left.size();
        int rightCt = right.size();
        return 1 + leftCt + rightCt;
    }
}
