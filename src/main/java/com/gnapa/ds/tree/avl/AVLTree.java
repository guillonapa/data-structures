package com.gnapa.ds.tree.avl;

import com.gnapa.ds.tree.BinarySearchTree;
import com.gnapa.ds.tree.TreeNode;

/**
 * <p>
 * TODO
 * </p>
 * 
 * @author Guillermo Narvaez
 */
public class AVLTree<K extends Comparable<K>, V> extends BinarySearchTree<K, V> {

    /**
     * <p>
     * TODO
     * </p>
     */
    @Override
    public void insert(K key, V value) {
        TreeNode<K, V> node = new TreeNode<>(key, value);
        if (root == null) {
            root = node;
        } else {
            root = insertNode(node, root);
            root.setParent(null);
        }
    }

    /**
     * <p>
     * TODO
     * </p>
     */
    @Override
    protected TreeNode<K, V> insertNode(TreeNode<K, V> node, TreeNode<K, V> root) {
        // deal with the empty tree
        if (root == null) {
            return node;
        }

        // adjust current node children
        if (node.key().compareTo(root.key()) < 0) {
            root.setLeft(insertNode(node, root.left()));
        } else {
            root.setRight(insertNode(node, root.right()));
        }

        // adjust height
        root.setHeight(1 + maxChildHeight(root));

        // perform any required rotations
        return rebalanceInsertion(node, root);
    }

    /**
     * <p>
     * TODO
     * </p>
     */
    @Override
    public V remove(K key) {
        V value = get(key);
        root = removeNode(root, key);
        if (root != null) {
            root.setParent(null);
        }
        return value;
    }

    /**
     * <p>
     * TODO
     * </p>
     * 
     * @param root
     * @param key
     * @return
     */
    protected TreeNode<K, V> removeNode(TreeNode<K, V> root, K key) {
        if (root == null) {
            return null;
        }

        // make the deletion
        if (key.compareTo(root.key()) < 0) {
            root.setLeft(removeNode(root.left(), key));
        } else if (key.compareTo(root.key()) > 0) {
            root.setRight(removeNode(root.right(), key));
        } else {
            // delete
            if (root.left() == null || root.right() == null) {
                if (root.left() == null && root.right() == null) {
                    root = null;
                } else if (root.left() == null) {
                    root = root.right();
                } else {
                    root = root.left();
                }
            } else {
                TreeNode<K, V> temp = root;
                root = getSuccessor(root);
                root.setLeft(temp.left());
                root.setRight(temp.right());
                root.setRight(removeNode(root.right(), root.key()));
            }
        }

        if (root == null) return root;

        // adjust the height
        root.setHeight(1 + maxChildHeight(root));

        // rebalance the subtree
        return rebalanceDeletion(root);
    }

    /**
     * <p>
     * TODO
     * </p>
     * 
     * @param root
     * @param inserted
     */
    private TreeNode<K, V> rebalanceInsertion(TreeNode<K, V> node, TreeNode<K, V> root) {
        int balanceFactor = balanceFactor(root);
        // left-left
        if (balanceFactor > 1 && node.key().compareTo(root.left().key()) < 0) {
            return rightRotation(root);
        }
        // left-right
        if (balanceFactor > 1 && node.key().compareTo(root.left().key()) > 0) {
            root.setLeft(leftRotation(root.left()));
            return rightRotation(root);
        }
        // right-right
        if (balanceFactor < -1 && node.key().compareTo(root.right().key()) > 0) {
            return leftRotation(root);
        }
        // right-left
        if (balanceFactor < -1 && node.key().compareTo(root.right().key()) < 0) {
            root.setRight(rightRotation(root.right()));
            return leftRotation(root);
        }
        // return original root
        return root;
    }

    /**
     * <p>
     * TODO
     * </p>
     * 
     * @param node
     * @return
     */
    private TreeNode<K, V> rebalanceDeletion(TreeNode<K, V> node) {
        int balanceFactor = balanceFactor(node);
        if (balanceFactor > 1 && balanceFactor(node.left()) >= 0) {
            return rightRotation(node);
        }
        if (balanceFactor > 1 && balanceFactor(node.left()) < 0) {
            node.setLeft(leftRotation(node.left()));
            return rightRotation(node);
        }
        if (balanceFactor < -1 && balanceFactor(node.right()) <= 0) {
            return leftRotation(node);
        }
        if (balanceFactor < -1 && balanceFactor(node.right()) > 0) {
            node.setRight(rightRotation(node.right()));
            return leftRotation(node);
        }
        return node;
    }

    /**
     * <p>
     * TODO
     * </p>
     * 
     * @param node
     * @return
     */
    private TreeNode<K, V> leftRotation(TreeNode<K, V> node) {
        TreeNode<K, V> newRoot = node.right(); // 4
        TreeNode<K, V> temp = newRoot.left(); // null
        node.setRight(temp);
        newRoot.setLeft(node);
        node.setHeight(1 + maxChildHeight(node));
        newRoot.setHeight(1 + maxChildHeight(newRoot));
        return newRoot;
    }

    /**
     * <p>
     * TODO
     * </p>
     * 
     * @param node
     * @return
     */
    private TreeNode<K, V> rightRotation(TreeNode<K, V> node) {
        TreeNode<K, V> newRoot = node.left();
        TreeNode<K, V> temp = newRoot.right();
        node.setLeft(temp);
        newRoot.setRight(node);
        node.setHeight(1 + maxChildHeight(node));
        newRoot.setHeight(1 + maxChildHeight(newRoot));
        return newRoot;
    }

    /**
     * <p>
     * TODO
     * </p>
     * 
     * @param root
     * @return
     */
    private TreeNode<K, V> getSuccessor(TreeNode<K, V> root) {
        if (root == null) return root;
        while (root.left() != null) {
            root = root.left();
        }
        return new TreeNode<K, V>(root.key(), root.value());
    }

    /**
     * <p>
     * TODO
     * </p>
     * 
     * @param node
     * @return
     */
    private int balanceFactor(TreeNode<K, V> node) {
        return (node.left() == null ? -1 : node.left().getHeight())
                - (node.right() == null ? -1 : node.right().getHeight());
    }
}
