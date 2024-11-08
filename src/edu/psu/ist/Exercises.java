package edu.psu.ist;

import java.util.ArrayList;
import java.util.function.Function;
import java.util.function.Predicate;

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

            // case 4: is it possible to reach the target amount
            //          by going down the left (l) or the right subtree (r)
            case NonEmpty(var l, int d, var r) -> {
                boolean possibleWithLeft = pathSum(l, target - d) ||
                                            pathSum(l, target);

                boolean possibleWithRight = pathSum(r, target - d) ||
                                            pathSum(r, target);
                yield possibleWithLeft || possibleWithRight;

            }

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
