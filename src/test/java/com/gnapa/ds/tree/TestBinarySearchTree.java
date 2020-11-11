package com.gnapa.ds.tree;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

/**
 * <p>
 * Unit tests for Binary Search Trees.
 * </p>
 * 
 * @author Guillermo Narvaez
 */
public class TestBinarySearchTree {
    
    @Test
    public void insertions() {
        BinarySearchTree<Integer, Integer> tree = new BinarySearchTree<>();
        tree.insert(5, 5);
        tree.insert(3, 3);
        tree.insert(2, 2);
        tree.insert(1, 1);
        tree.insert(7, 7);
        tree.insert(8, 8);
        tree.insert(9, 9);
        /*
                    5
                 3     7
              2           8
           1                 9
        */
        assertEquals("[5,3,7,2,null,null,8,1,null,null,9]", tree.serialize());
    }

    @Test
    public void deletions() {
        BinarySearchTree<Integer, Integer> tree = new BinarySearchTree<>();
        tree.insert(5, 5);
        tree.insert(3, 3);
        tree.insert(2, 2);
        tree.insert(1, 1);
        tree.insert(7, 7);
        tree.insert(8, 8);
        tree.insert(9, 9);
        tree.remove(2);
        tree.remove(8);
        tree.remove(5);
        /*
                    7
                 3     9
              1           
        */
        assertEquals("[7,3,9,1]", tree.serialize());
    }

    @Test
    public void getValue() {
        BinarySearchTree<Integer, Integer> tree = new BinarySearchTree<>();
        tree.insert(5, 5);
        tree.insert(3, 3);
        tree.insert(2, 2);
        tree.insert(1, 1);
        tree.insert(7, 7);
        tree.insert(8, 8);
        tree.insert(9, 9);
        tree.insert(20, 20);
        tree.insert(30, 30);
        tree.insert(25, 25);
        tree.insert(28, 28);
        assertNull(tree.get(4));
        assertEquals(5, tree.get(5));
        assertEquals(2, tree.get(2));
        assertNull(tree.get(29));
        assertEquals(28, tree.get(28));
    }
}
