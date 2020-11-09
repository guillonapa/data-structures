package com.gnapa.ds.tree;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TestBinarySearchTree {
    
    @Test
    public void simpleInsertions() {
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
    public void simpleDeletions() {
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
}
