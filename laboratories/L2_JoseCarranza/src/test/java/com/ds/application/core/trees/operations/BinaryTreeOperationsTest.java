package com.ds.application.core.trees.operations;



public class BinaryTreeOperationsTest extends TestCase {

    public void testBinaryTreeOperations() {
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(8);
        root.setLeft(new BinaryTreeNode<>(3));
        root.setRight(new BinaryTreeNode<>(10));
        root.getLeft().setLeft(new BinaryTreeNode<>(1));
        root.getLeft().setRight(new BinaryTreeNode<>(6));
        root.getRight().setRight(new BinaryTreeNode<>(14));

        BinaryTreeOperations operations = new BinaryTreeOperations(root);

        assertTrue(operations.contains(6));
        assertFalse(operations.contains(7));

        assertEquals(3, operations.height());
        assertEquals(6, operations.size());

        List<Integer> inOrder = operations.inOrder();
        assertEquals(6, inOrder.size());
        assertEquals(Integer.valueOf(1), inOrder.get(0));
        assertEquals(Integer.valueOf(14), inOrder.get(5));

        assertEquals("1 3 6 8 10 14", operations.inOrderString());
        assertEquals("8 3 1 6 10 14", operations.preOrderString());
        assertEquals("1 6 3 14 10 8", operations.postOrderString());
    }
}
