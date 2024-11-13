package edu.psu.ist;

import java.util.TreeSet;
import java.util.function.Function;

public final class Exercises {

    public static int sumAll(BSTree tree) {
        return switch (tree) {
            case Empty _ -> 0;
            case NonEmpty(var l, var data, var r) -> data + sumAll(l) + sumAll(r);
        };
    }

    //          10
    //     5         20
    //                     25
    public static int maxDepth(BSTree tree) {
        return switch (tree) {
            case Empty _ -> 0;
            case NonEmpty(var l, _, var r) -> {
                var depthOfLeft = maxDepth(l);
                var depthOfRight = maxDepth(r);
                if (depthOfLeft < depthOfRight) {
                    yield 1 + depthOfRight;
                } else {
                    yield 1 + depthOfLeft;
                }
            }
        };
    }

    /*
    // "algebraic type"
    sealed interface BSTree {
        record Empty()                                    implements BSTree {}
        record NonEmpty(BSTree left, int x, BSTree right) implements BSTree {}
    }
     */
    public static int sumAllLeafs(BSTree tree) {
        return switch (tree) {
            // empty tree (no leafs to sum)
            case Empty e -> 0;
            // leaf node:
            case NonEmpty(Empty left, int d, Empty right) -> d;
            // any inner node:
            case NonEmpty(BSTree left, int d, BSTree right) -> {
                int sumOfAllLeafsInLeft = sumAllLeafs(left);
                int sumOfAllLeafsInRight = sumAllLeafs(right);
                yield sumOfAllLeafsInLeft + sumOfAllLeafsInRight;
            }
        };
    }

    public static BSTree delete(BSTree tree, int key) {
        return switch (tree) {
            // tree empty... yield the empty tree:
            case Empty e -> e;

            // item we want to delete is in lhs subtree
            case NonEmpty(var l, var x, var r) when key < x -> new NonEmpty(delete(l, key), x, r);

            // item we want to delete is in rhs subtree
            case NonEmpty(var l, var x, var r) when key > x -> new NonEmpty(l, x, delete(r, key));

            // ---- all cases past here the data matches ---
            // leaf node to delete, yield the empty tree:
            case NonEmpty(Empty _, _, Empty _) -> new Empty();

            // node we want to delete only has a right kid, promote it
            case NonEmpty(Empty _, _, var r) -> r;

            // node we want to delete only has a left kid, promote it
            case NonEmpty(var l, _, Empty _) -> l;

            // node we want to delete has two kids: find the min-successor
            // (don't care "_" on the data since we know it matches key at this point)
            case NonEmpty(var l, _, var r) -> {
                var minKey              = minKey(r, key); // find the minimum key in r(ight) subtree
                var rightWithoutMinKey  = delete(r, minKey);
                yield new NonEmpty(l, minKey, rightWithoutMinKey);
            }
        };
    }

    // precondition: tree is not the empty tree
    private static int minKey(BSTree tree, int key) {
        return switch (tree) {
            case Empty _ -> throw new UnsupportedOperationException("precondition violation");

            // if we match on a node where the left subtree is empty, then we've
            // found the min node, return it
            case NonEmpty(Empty _, var x, _) -> x;

            // if we match on a node with a non-empty left subtree, recurse down it
            case NonEmpty(var l, _, _) -> minKey(l, key);
        };
    }

    public static BSTree mirror(BSTree tree) {
        return switch (tree) {
            case Empty _ -> tree;
            case NonEmpty(var l, var d, var r) ->
                    new NonEmpty(mirror(r), d, mirror(l));
        };
    }

    public static TreeSet<Integer> toTreeSet(BSTree tree) {
        return switch (tree) {
            // case 1: empty trees can't have data, so give
            //          back an empty treeset
            case Empty _ -> new TreeSet<>();
            // case 2: we see a leaf node (left and right kids are empty
            case NonEmpty(Empty _, int d, Empty _) -> {
                TreeSet<Integer> res = new TreeSet<>();
                res.add(d); // [d]
                yield res;
            }
            // case 3: general case....:
            case NonEmpty(BSTree l, int d, BSTree r) -> {
                var leftSubtreeDataSet = toTreeSet(l);
                var rightSubtreeDataSet = toTreeSet(r);
                // combine the sets of data collected from
                // the l(eft) and r(ight) subtrees:
                leftSubtreeDataSet.addAll(rightSubtreeDataSet);
                leftSubtreeDataSet.add(d);
                yield leftSubtreeDataSet;
            }
        };
    }

    //          8
    //      2
    public static double average(BSTree tree) {
        int sum = sumAll(tree);
        /*if (tree instanceof Empty) {
            return 0;
        }
        if (tree instanceof NonEmpty) {
            return (double) sum / tree.size();
        }*/
        return switch (tree) {
            case Empty _ -> 0;
            case NonEmpty t -> (double) (sum / t.size());
        };
    }

    public static boolean pathSum(BSTree tree, int target) {
        return switch (tree) {
            // case 1: the tree is empty and the target is 0
            // so we trivially satisfy the target
            case Empty _ when target == 0 -> true;

            // case 2: we have non-zero target amount BUT we have
            //      an empty tree on our hands, so it's impossible
            //      to reach the target, so give back false
            case Empty _ -> false;

            // case 3: we're at a leaf node... ask the question:
            //          does subtracting the leafs target - data == 0
            //          then we've found a satisfying path down through the
            //          tree
            case NonEmpty(Empty _, int d, Empty _) -> target - d == 0;

            // Case 3: Recursive case for non-leaf nodes, check left and right subtrees
            //          for a satisfying path...
            case NonEmpty(var l, int d, var r) -> pathSum(l, target - d) || pathSum(r, target - d);
        };
    }


    //              10                                          f(10)
    //          5        20   === allSatisfy(tree, f) ==> f(5)          f(20)
    //                15                                            f(15)
    public static boolean allSatisfy(BSTree tree, Function<Integer, Boolean> f) {
        return switch (tree) {
            // case 1: the tree is empty -- so it trivially satisfies f
            case Empty _ -> true;
            case NonEmpty(BSTree left, int data, BSTree right) ->
                    f.apply(data) && allSatisfy(left, f) && allSatisfy(right, f);
        };
    }
}
