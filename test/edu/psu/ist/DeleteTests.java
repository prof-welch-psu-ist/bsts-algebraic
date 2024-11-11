package edu.psu.ist;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public final class DeleteTests {
    /**
     * Helper method to build and return a tree from an array of integers.
     */
    private BSTree buildTree(int... keys) {
        BSTree tr = BSTree.empty();
        for (int key : keys) {
            tr = tr.insert(key);
        }
        return tr;
    }

    @Test public void testDelete_LeafNode() {
        BSTree tr = buildTree(10, 5, 20);
        tr = Exercises.delete(tr, 5);
        String expectedPreOrder = "10 20";
        Assertions.assertEquals(expectedPreOrder, tr.preOrder(), "Deleting a leaf node failed.");
    }

    @Test public void testDelete_NodeWithOneLeftChild() {
        BSTree tr = buildTree(10, 5, 20, 3);
        tr = Exercises.delete(tr, 5);
        String expectedPreOrder = "10 3 20";
        Assertions.assertEquals(expectedPreOrder, tr.preOrder(), "Deleting a node with one left child failed.");
    }

    @Test public void testDelete_NodeWithOneRightChild() {
        BSTree tr = buildTree(10, 5, 20, 25);
        tr = Exercises.delete(tr, 20);
        String expectedPreOrder = "10 5 25";
        Assertions.assertEquals(expectedPreOrder, tr.preOrder(), "Deleting a node with one right child failed.");
    }

    @Test public void testDelete_NodeWithTwoChildren() {
        BSTree tr = buildTree(10, 5, 15, 3, 7, 20);
        tr = Exercises.delete(tr, 5);
        String expectedPreOrder = "10 7 3 15 20";
        Assertions.assertEquals(expectedPreOrder, tr.preOrder(), "Deleting a node with two children failed.");
    }

    @Test public void testDelete_RootNode() {
        BSTree tr = buildTree(10, 5, 15);
        tr = Exercises.delete(tr, 10);
        String expectedPreOrder = "15 5";
        Assertions.assertEquals(expectedPreOrder, tr.preOrder(), "Deleting the root node failed.");
    }

    @Test public void testDelete_NonExistentElement() {
        BSTree tr = buildTree(10, 5, 15);
        tr = Exercises.delete(tr, 20);
        String expectedPreOrder = "10 5 15";
        Assertions.assertEquals(expectedPreOrder, tr.preOrder(), "Deleting a non-existent element should not alter the tree.");
    }

    @Test public void testDelete_FromEmptyTree() {
        BSTree tr = BSTree.empty();
        tr = Exercises.delete(tr, 10);
        String expectedPreOrder = "";
        Assertions.assertEquals(expectedPreOrder, tr.preOrder(), "Deleting from an empty tree should leave it unchanged.");
    }

    @Test public void testDelete_SingleNodeTree() {
        BSTree tr = buildTree(10);
        tr = Exercises.delete(tr, 10);
        String expectedPreOrder = "";
        Assertions.assertEquals(expectedPreOrder, tr.preOrder(), "Deleting the only node should result in an empty tree.");
    }

    @Test public void testDelete_NodeWithTwoChildren_SuccessorHasRightChild() {
        BSTree tr = buildTree(10, 5, 15, 12, 20, 13);
        tr = Exercises.delete(tr, 15);
        String expectedPreOrder = "10 5 20 12 13";
        Assertions.assertEquals(expectedPreOrder, tr.preOrder(), "Deleting a node with two children where the successor has a right child failed.");
    }

    @Test public void testDelete_NodeWithTwoChildren_SuccessorIsImmediateRightChild() {
        BSTree tr = buildTree(10, 15, 12, 20);
        tr = Exercises.delete(tr, 15);
        String expectedPreOrder = "10 20 12";
        Assertions.assertEquals(expectedPreOrder, tr.preOrder(), "Deleting a node with two children where the successor is the immediate right child failed.");
    }

    @Test public void testDelete_AllNodes() {
        BSTree tr = buildTree(10, 5, 15, 3, 7, 12, 20);
        int[] keysToDelete = {3, 7, 5, 12, 20, 15, 10};
        for (int key : keysToDelete) {
            tr = Exercises.delete(tr, key);
        }
        String expectedPreOrder = "";
        Assertions.assertEquals(expectedPreOrder, tr.preOrder(), "Deleting all nodes should result in an empty tree.");
    }
}
