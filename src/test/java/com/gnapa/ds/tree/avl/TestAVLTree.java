package com.gnapa.ds.tree.avl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * <p>
 * Unit tests for AVL Trees.
 * </p>
 * 
 * @author Guillermo Narvaez
 */
public class TestAVLTree {

    @Test
    public void heights() {
        AVLTree<Integer, Integer> tree = new AVLTree<>();
        tree.insert(3, 3);
        assertTrue(tree.root().getHeight() == 0);
        tree.insert(2, 2);
        assertTrue(tree.root().getHeight() == 1);
        assertTrue(tree.root().left().getHeight() == 0);

        tree.insert(5, 5);
        assertTrue(tree.root().getHeight() == 1);
        assertTrue(tree.root().left().getHeight() == 0);
        assertTrue(tree.root().right().getHeight() == 0);

        tree.insert(1, 1);
        assertTrue(tree.root().getHeight() == 2);
        assertTrue(tree.root().left().getHeight() == 1);
        assertTrue(tree.root().right().getHeight() == 0);
        assertTrue(tree.root().left().left().getHeight() == 0);

        tree.insert(4, 4);
        assertTrue(tree.root().getHeight() == 2);
        assertTrue(tree.root().left().getHeight() == 1);
        assertTrue(tree.root().right().getHeight() == 1);
        assertTrue(tree.root().left().left().getHeight() == 0);
        assertTrue(tree.root().right().left().getHeight() == 0);
    }

    @Test
    public void simpleInsert() {
        AVLTree<Integer, Integer> tree = new AVLTree<>();
        tree.insert(3, 3);
        assertEquals("[3]", tree.serialize());
        tree.insert(2, 2);
        assertEquals("[3,2]", tree.serialize());
        tree.insert(5, 5);
        assertEquals("[3,2,5]", tree.serialize());
        tree.insert(1, 1);
        assertEquals("[3,2,5,1]", tree.serialize());
        tree.insert(4, 4);
        assertEquals("[3,2,5,1,null,4]", tree.serialize());
    }

    @Test
    public void insertWithLeftRightRebalance() {
        AVLTree<Integer, Integer> tree = new AVLTree<>();
        // rotation involving the root
        tree.insert(5, 5);
        assertEquals("[5]", tree.serialize());
        tree.insert(3, 3);
        assertEquals("[5,3]", tree.serialize());
        tree.insert(4, 4);
        assertEquals("[4,3,5]", tree.serialize());

        // rotation without the root
        tree.insert(1, 1);
        assertEquals("[4,3,5,1]", tree.serialize());
        tree.insert(2, 2);
        assertEquals("[4,2,5,1,3]", tree.serialize());
    }

    @Test
    public void insertWithRightRightRebalance() {
        AVLTree<Integer, Integer> tree = new AVLTree<>();
        // rotation involving the root
        tree.insert(5, 5);
        assertEquals("[5]", tree.serialize());
        tree.insert(7, 7);
        assertEquals("[5,null,7]", tree.serialize());
        tree.insert(8, 8);
        assertEquals("[7,5,8]", tree.serialize());

        // rotation without the root
        tree.insert(9, 9);
        assertEquals("[7,5,8,null,null,null,9]", tree.serialize());
        tree.insert(10, 10);
        assertEquals("[7,5,9,null,null,8,10]", tree.serialize());
    }

    @Test
    public void insertWithRightLeftRebalance() {
        AVLTree<Integer, Integer> tree = new AVLTree<>();
        // rotation involving the root
        tree.insert(5, 5);
        assertEquals("[5]", tree.serialize());
        tree.insert(8, 8);
        assertEquals("[5,null,8]", tree.serialize());
        tree.insert(7, 7);
        assertEquals("[7,5,8]", tree.serialize());

        // rotation without the root
        tree.insert(12, 12);
        assertEquals("[7,5,8,null,null,null,12]", tree.serialize());
        tree.insert(10, 10);
        assertEquals("[7,5,10,null,null,8,12]", tree.serialize());
    }

    @Test
    public void insertWithLeftLeftRebalance() {
        AVLTree<Integer, Integer> tree = new AVLTree<>();
        // rotation involving the root
        tree.insert(5, 5);
        assertEquals("[5]", tree.serialize());
        tree.insert(3, 3);
        assertEquals("[5,3]", tree.serialize());
        tree.insert(2, 2);
        assertEquals("[3,2,5]", tree.serialize());

        // rotation without the root
        tree.insert(1, 1);
        assertEquals("[3,2,5,1]", tree.serialize());
        tree.insert(0, 0);
        assertEquals("[3,1,5,0,2]", tree.serialize());
    }

    @Test
    public void deletions() {
        AVLTree<Integer, Integer> tree = new AVLTree<>();
        // rotation involving the root
        tree.insert(5, 5);
        assertEquals("[5]", tree.serialize());
        tree.insert(3, 3);
        assertEquals("[5,3]", tree.serialize());
        tree.insert(2, 2);
        assertEquals("[3,2,5]", tree.serialize());
        tree.remove(2);
        assertEquals("[3,null,5]", tree.serialize());
        tree.remove(5);
        assertEquals("[3]", tree.serialize());
        tree.insert(-10, -10);
        tree.insert(20, 20);
        tree.insert(-20, -20);
        tree.insert(-7, -7);
        tree.insert(10, 10);
        tree.insert(30, 30);
        tree.insert(-25, -25);
        tree.insert(-18, -18);
        tree.insert(-9, -9);
        tree.insert(-6, -6);
        tree.insert(7, 7);
        tree.insert(12, 12);
        tree.insert(25, 25);
        tree.insert(32, 32);
        tree.insert(-30, -30);
        tree.insert(-8, -8);
        tree.insert(11, 11);
        tree.insert(40, 40);
        assertEquals("[3," + 
                    "-10,20," + 
                    "-20,-7,10,30," + 
                    "-25,-18,-9,-6,7,12,25,32," + 
                    "-30,null,null,null,null,-8,null,null,null,null,11,null,null,null,null,40]", 
            tree.serialize());

        // left-right case
        assertEquals(-6, tree.remove(-6));
        assertEquals("[3," + 
                    "-10,20," + 
                    "-20,-8,10,30," + 
                    "-25,-18,-9,-7,7,12,25,32," + 
                    "-30,null,null,null,null,null,null,null,null,null,11,null,null,null,null,40]", 
            tree.serialize());

        // left-left case
        assertEquals(-18, tree.remove(-18));
        assertEquals("[3," + 
                    "-10,20," + 
                    "-25,-8,10,30," + 
                    "-30,-20,-9,-7,7,12,25,32," + 
                    "null,null,null,null,null,null,null,null,null,null,11,null,null,null,null,40]", 
            tree.serialize());

        // right-left case
        assertEquals(7, tree.remove(7));
        assertEquals("[3," + 
                    "-10,20," + 
                    "-25,-8,11,30," + 
                    "-30,-20,-9,-7,10,12,25,32," + 
                    "null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,40]", 
            tree.serialize());

        // right-right case
        assertEquals(25, tree.remove(25));
        assertEquals("[3," + 
                    "-10,20," + 
                    "-25,-8,11,32," + 
                    "-30,-20,-9,-7,10,12,30,40]",
            tree.serialize());

        // delete non-existent node
        assertNull(tree.remove(77));
        assertEquals("[3," + 
                    "-10,20," + 
                    "-25,-8,11,32," + 
                    "-30,-20,-9,-7,10,12,30,40]",
            tree.serialize());
    }
}
