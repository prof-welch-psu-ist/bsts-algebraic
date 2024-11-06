package edu.psu.ist;

import java.util.function.Function;
import java.util.function.Predicate;

public final class Exercises {

    public static int sumAll(BSTree tree) {
        return switch (tree) {
            case Empty _ ->  0;
            case NonEmpty(var l, var data, var r) ->
                data + sumAll(l) + sumAll(r);
        };
    }

    //          10
    //     5         20
    //                     25
    public static int maxDepth(BSTree tree) {
        return switch (tree) {
            case Empty _                   -> 0;
            case NonEmpty(var l, _, var r) -> {
                var depthOfLeft = maxDepth(l);
                var depthOfRight = maxDepth(r);
                if (depthOfLeft < depthOfRight) {
                    yield 1 + depthOfRight;
                }
                else {
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
            // case 1: looking at empty trees....
            case Empty _ when target == 0 -> true;
            case Empty _                  -> false;

            // case 2: looking at a leaf node....
            case NonEmpty(Empty _, var d, Empty _) -> target - d == 0;

            // case 3: looking at a nonempty tree
            //          where the left or right kids might be nonempty
            case NonEmpty(var l, var d, var r) -> {
               // two possibilities:
               //   1) we either take data d from THIS node and subtract it from
               //       our target amt
               //   2) we DONT take the data d from THIS node, and still recurse
                //
            }

        };
    }


    //              10                                          f(10)
    //          5        20   === allSatisfy(tree, f) ==> f(5)          f(20)
    //                15                                            f(15)
    public static boolean allSatisfy(BSTree tree,
                                     Predicate<Integer> f) {
        return switch (tree) {
            case Empty _                         -> true;
            case NonEmpty(var lf, var x, var rt) ->
                f.test(x) && allSatisfy(lf, f) && allSatisfy(rt, f);
        };
    }

}
