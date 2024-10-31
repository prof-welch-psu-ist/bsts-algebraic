package edu.psu.ist;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public final class BSTreeTests {

    @Test public void testInsert00() {
        BSTree tr = new Empty(); // "tr" short for "tree"
        Assertions.assertEquals(0, tr.size());
    }

    @Test public void testInsert01() {
        var tr = BSTree.empty();
        Assertions.assertEquals(0, tr.size());
        Assertions.assertEquals("empty", tr.toString());

        tr = tr.insert(10);
        Assertions.assertEquals(1, tr.size());
        Assertions.assertEquals("10", tr.preOrder());

        tr = tr.insert(5);
        Assertions.assertEquals(2, tr.size());
        Assertions.assertEquals("10 5", tr.preOrder());

        tr = tr.insert(20);
        Assertions.assertEquals(3, tr.size());
        Assertions.assertEquals("10 5 20", tr.preOrder());

        tr = tr.insert(8);
        Assertions.assertEquals(4, tr.size());
        Assertions.assertEquals("10 5 8 20", tr.preOrder());
    }

    @Test public void testFailureDup01() {
        final var tr = BSTree.empty();
        Assertions.assertThrows(IllegalArgumentException.class, //
                () -> tr.insert(10) //
                        .insert(10));
    }

    @Test public void testFailureDup02() {
        final var tr = BSTree.empty();
        Assertions.assertThrows(IllegalArgumentException.class, //
                () -> tr.insert(10) //
                        .insert(5)  //
                        .insert(10));
    }


    @Test public void testInsert02() {
        BSTree tr = new Empty();
        tr = tr.insert(10);
        tr = tr.insert(5);
        Assertions.assertEquals("10 5", tr.preOrder());

        tr = tr.insert(20);

        Assertions.assertEquals("10 5 20", tr.preOrder());
        Assertions.assertEquals(3, tr.size());
    }

    @Test public void testInsertDupFail01() {
        var tr = new Empty().insert(10);
        Assertions.assertThrows(IllegalArgumentException.class, () -> tr.insert(10));
    }

    @Test public void testInsertDupFail02() {
        var tr = new Empty().insert(10).insert(5);
        Assertions.assertEquals("10 5", tr.preOrder());
        Assertions.assertThrows(IllegalArgumentException.class, () -> tr.insert(10));
    }

    @Test public void testLopsidedTree() {
        var tr = BSTree.empty()//
                .insert(10)//
                .insert(5)//
                .insert(2)//
                .insert(0);
        Assertions.assertEquals("10 5 2 0", tr.preOrder());
    }
}
