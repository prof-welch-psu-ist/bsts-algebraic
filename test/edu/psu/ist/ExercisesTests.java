package edu.psu.ist;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.function.Predicate;

public final class ExercisesTests {


    @Test public void testSum01() {

        //         10
        var tr = BSTree.empty().insert(10); //

        //          10
        //     5
        tr = tr.insert(5);

        //          10
        //     5           20
        tr = tr.insert(20);

        //          10
        //     5           20
        //              15
        tr = tr.insert(15);
        Assertions.assertEquals(50, Exercises.sumAll(tr));
    }

    @Test public void testSum02() {
        var tr = BSTree.empty() //
                .insert(10);
        Assertions.assertEquals(10, Exercises.sumAll(tr));
    }

    @Test public void testSum03() {
        var tr = BSTree.empty();
        Assertions.assertEquals(0, Exercises.sumAll(tr));
    }

    /*@Test public void testSumLeafs01() {
        var tr = BSTree.empty()
                .insert(10)
                .insert(5)
                .insert(20)
                .insert(15);
        Assertions.assertEquals(20, Exercises.sumLeafs(tr));
    }*/

    @Test public void testMaxDepth() {
        //          10
        //     5         20
        //                     25
        var tr = BSTree.empty().insert(10).insert(5).insert(20).insert(25);
        Assertions.assertEquals(3, Exercises.maxDepth(tr));
    }

    //          10
    //     5         20
    //                     25
    //                  21
    @Test public void testMaxDepth02() {
        var tr = BSTree.empty().insert(10).insert(5).insert(20).insert(25).insert(21);
        Assertions.assertEquals(4, Exercises.maxDepth(tr));
    }

    //          10
    //     5         20
    //                     25
    //                  21
    @Test public void testLeafSum01() {
        var tr = BSTree.empty().insert(10).insert(5).insert(20).insert(25).insert(21);
        Assertions.assertEquals(26, Exercises.sumAllLeafs(tr));
    }


    @Test public void testLeafSum02() {
        var tr = BSTree.empty().insert(10);
        Assertions.assertEquals(10, Exercises.sumAllLeafs(tr));
    }

    //          10
    //     5         20
    //                     25
    // -------------------------
    //          10
    //     20         5
    // 25
    @Test public void testMirror01() {
        var tr = BSTree.empty().insert(10).insert(5).insert(20).insert(25);
        var flippedTr = Exercises.mirror(tr);
        var expectedFlippedTree = new NonEmpty(new NonEmpty(new NonEmpty(new Empty(), 25, new Empty()), 20, new Empty()),  // right
                10, new NonEmpty(new Empty(), 5, new Empty()) // left
        );
        Assertions.assertEquals("10 20 25 5", flippedTr.preOrder());
        Assertions.assertEquals(expectedFlippedTree, flippedTr);
    }

    //          10
    //     5
    @Test public void testMaxDepth03() {
        var tr = BSTree.empty().insert(10).insert(5);
        Assertions.assertEquals(2, Exercises.maxDepth(tr));
    }

    @Test public void testAllSat() {
        var tr = BSTree.empty().insert(10).insert(5).insert(20).insert(15);

        Predicate<Integer> myPred = (Integer y) -> y > 20;

        // example 1: check if all nodes are greater than 0
        boolean allPositive = Exercises.allSatisfy(tr, x -> x > 0);
        Assertions.assertTrue(allPositive);

        // example 2: check if all nodes are less than 10
        boolean allLessThan10 = Exercises.allSatisfy(tr, x -> x < 10);
        Assertions.assertFalse(allLessThan10);

        // example 3: check if all nodes are even
        boolean allEven = Exercises.allSatisfy(tr, x -> x % 2 == 0);
        Assertions.assertFalse(allEven);

        // example 4: check if all nodes are divisible by 5
        boolean allDivisibleBy5 = Exercises.allSatisfy(tr, x -> x % 5 == 0);
        Assertions.assertTrue(allDivisibleBy5);
    }

    @Test public void testPathSum() {

        //      10
        //   5      15
        // 1   7
        var tr = BSTree.empty() //
                .insert(10) //
                .insert(5) //
                .insert(15) //
                .insert(1) //
                .insert(7);
        Assertions.assertTrue(Exercises.pathSum(tr, 22));
    }

}
