package edu.psu.ist;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public final class ExercisesTests {

    @Test public void testSum01() {
        var tr = BSTree.empty() //
                .insert(10) //
                .insert(5) //
                .insert(20) //
                .insert(15); //
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

    @Test public void testSumLeafs01() {
        var tr = BSTree.empty()
                .insert(10)
                .insert(5)
                .insert(20)
                .insert(15);
        Assertions.assertEquals(20, Exercises.sumLeafs(tr));
    }

    @Test public void testMaxDepth() {
        var tr = BSTree.empty()
                .insert(10)
                .insert(5)
                .insert(20)
                .insert(25);
        Assertions.assertEquals(3, Exercises.maxDepth(tr));
    }
}
