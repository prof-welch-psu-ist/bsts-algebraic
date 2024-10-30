package edu.psu.ist;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public final class BSTreeTests {

    @Test public void testInsert01() {
        BSTree myTree = new Empty();
        Assertions.assertEquals(0, myTree.size());

        myTree = myTree.insert(10);
        Assertions.assertEquals(1, myTree.size());

        myTree = myTree.insert(5);
        Assertions.assertEquals(2, myTree.size());

        Assertions.assertEquals("10 5", myTree.preOrder());
    }

    @Test public void testFailDupKey01() {
        BSTree myTree = new Empty().insert(10);
        Assertions.assertThrows(IllegalArgumentException.class, () -> myTree.insert(10));
    }

    @Test public void testFailDupKey02() {
        BSTree myTree = BSTree.empty() //
                .insert(10) //
                .insert(5); //
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> myTree.insert(5));
    }

}
