package edu.psu.ist;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public final class BSTreeTests {

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
    }

    @Test public void testFailureDup() {
        var tr = BSTree.empty();

    }
}
