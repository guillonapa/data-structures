package com.gnapa.ds.tree;

import java.util.ArrayDeque;

/**
 * <p>
 * TODO
 * </p>
 * 
 * @author Guillermo Narvaez
 */
public class BinarySearchTree<K extends Comparable<K>, V> implements IBinaryTree<K, V> {

    protected TreeNode<K, V> root;

    /**
     * <p>
     * TODO
     * </p>
     */
    public BinarySearchTree() {
    }

    /**
     * <p>
     * TODO
     * </p>
     * 
     * @param key
     * @param value
     */
    public BinarySearchTree(K key, V value) {
        root = new TreeNode<K, V>(key, value);
    }

    /**
     * <p>
     * TODO
     * </p>
     * 
     * @return
     */
    public TreeNode<K, V> root() {
        return root;
    }

    /**
     * <p>
     * TODO
     * </p>
     * 
     */
    @Override
    public void insert(K key, V value) {
        TreeNode<K, V> node = new TreeNode<>(key, value);
        if (root == null) {
            root = node;
            return;
        }
        insertNode(node, root);
    }

    /**
     * <p>
     * TODO
     * </p>
     * 
     * @param node
     * @param root
     */
    protected TreeNode<K, V> insertNode(TreeNode<K, V> node, TreeNode<K, V> root) {
        TreeNode<K, V> nextRoot;
        if (node.key().compareTo(root.key()) < 0) {
            // goes to the left of the root
            if (root.left() == null) {
                node.setParent(root);
                root.setLeft(node);
                return node;
            }
            nextRoot = root.left();
        } else {
            // goes to the right of the root
            if (root.right() == null) {
                node.setParent(root);
                root.setRight(node);
                return node;
            }
            nextRoot = root.right();
        }
        TreeNode<K, V> inserted = insertNode(node, nextRoot);
        nextRoot.setHeight(1 + maxChildHeight(nextRoot));
        return inserted;
    }

    /**
     * <p>
     * TODO
     * </p>
     */
    @Override
    public V get(K key) {
        return find(key, root);
    }

    /**
     * <p>
     * TODO
     * </p>
     * 
     * @param key
     * @param node
     * @return
     */
    private V find(K key, TreeNode<K, V> node) {
        if (node.key().equals(key)) return node.value();
        TreeNode<K, V> nextRoot;
        if (key.compareTo(node.key()) < 0) {
            // goes to the left of the root
            if (node.left() == null) {
                return null;
            }
            nextRoot = node.left();
        } else {
            // goes to the right of the root
            if (node.right() == null) {
                return null;
            }
            nextRoot = node.right();
        }
        return find(key, nextRoot);
    }

    /**
     * <p>
     * TODO
     * </p>
     */
    @Override
    public V remove(K key) {
        TreeNode<K, V> removed = removeNode(key);
        if (removed != null) {
            return removed.value();
        }
        return null;
    }

    /**
     * <p>
     * TODO
     * </p>
     * 
     * @param key
     * @return
     */
    protected TreeNode<K, V> removeNode(K key) {
        if (root == null) return null;
        TreeNode<K, V> parent = null;
        TreeNode<K, V> curr = root;
        while (curr != null && !curr.key().equals(key)) {
            if (key.compareTo(curr.key()) < 0) {
                parent = curr;
                curr = curr.left();
            } else {
                parent = curr;
                curr = curr.right();
            }
        }

        // if key not found return null
        if (curr == null) return null;

        // removing the root node
        if (parent == null) {
            if (curr.left() == null && curr.right() == null) {
                root = null;
            } else if (curr.left() == null) {
                root = curr.right();
            } else if (curr.right() == null) {
                root = curr.left();
            } else {
                root = makeMinRoot(curr.right());
                root.setLeft(curr.left());
            }
            root.setParent(null);
            return curr;
        }

        // removing child node
        if (curr.equals(parent.left())) {
            if (curr.left() == null && curr.right() == null) {
                parent.setLeft(null);
            } else if (curr.left() == null) {
                parent.setLeft(curr.right());
                curr.right().setParent(parent);
            } else if (curr.right() == null) {
                parent.setLeft(curr.left());
                curr.left().setParent(parent);
            } else {
                // remove the minimum from the right child
                // and replace it with the one we are removing
                TreeNode<K, V> subRoot = makeMinRoot(curr.right());
                subRoot.setLeft(curr.left());
                parent.setLeft(subRoot);
                subRoot.setParent(parent);
                // update height of all ancestors of subRoot
                while (subRoot != null) {
                    subRoot.setHeight(1 + maxChildHeight(subRoot));
                    subRoot = subRoot.parent();
                }
            }
        } else {
            if (curr.left() == null && curr.right() == null) {
                parent.setRight(null);
            } else if (curr.left() == null) {
                parent.setRight(curr.right());
                curr.right().setParent(parent);
            } else if (curr.right() == null) {
                parent.setRight(curr.left());
                curr.left().setParent(parent);
            } else {
                // remove the minimum from the right child
                // and replace it with the one we are removing
                TreeNode<K, V> subRoot = makeMinRoot(curr.right());
                subRoot.setLeft(curr.left());
                parent.setRight(subRoot);
                subRoot.setParent(parent);
                // update height of all ancestors of subRoot
                while (subRoot != null) {
                    subRoot.setHeight(1 + maxChildHeight(subRoot));
                    subRoot = subRoot.parent();
                }
            }
        }
        return curr;
    }

    /**
     * <p>
     * TODO
     * </p>
     * 
     * @param root
     * @return
     */
    protected int maxChildHeight(TreeNode<K, V> root) {
        if (root.left() == null && root.right() == null) {
            return -1;
        }
        return Math.max(root.left() != null ? root.left().getHeight() : 0,
                root.right() != null ? root.right().getHeight() : 0);
    }

    /**
     * <p>
     * TODO
     * </p>
     * 
     * @param root
     * @return
     */
    protected TreeNode<K, V> makeMinRoot(TreeNode<K, V> root) {
        // we expect right to not be null
        if (root.left() == null) return root;
        TreeNode<K, V> parent = root;
        TreeNode<K, V> min = root.left();
        while (min.left() != null) {
            parent = min;
            min = min.left();
        }
        parent.setLeft(min.right());
        if (min.right() != null) {
            min.right().setParent(parent);
        }
        min.setRight(root);
        root.setParent(min);
        while (true) {
            parent.setHeight(1 + maxChildHeight(parent));
            if (parent.equals(min)) {
                break;
            }
            parent = parent.parent();
        }
        return min;
    }

    /**
     * <p>
     * TODO
     * </p>
     */
    @Override
    public String serialize() {
        if (root == null) return "[]";
        StringBuffer buffer = new StringBuffer();
        ArrayDeque<Object> queue = new ArrayDeque<>();
        buffer.append("[");
        buffer.append(root.value());
        queue.addLast(root.left() == null ? "null" : root.left());
        queue.addLast(root.right() == null ? "null" : root.right());
        int numOfNulls = 0;
        while (!queue.isEmpty()) {
            Object curr = queue.removeFirst();
            if (!(curr instanceof TreeNode)) {
                numOfNulls++;
            } else {
                while (numOfNulls > 0) {
                    buffer.append(",null");
                    numOfNulls--;
                }
                buffer.append(String.format(",%s", ((TreeNode<?, ?>) curr).value()));
            }
            if (curr instanceof TreeNode) {
                TreeNode<?, ?> currNode = (TreeNode<?, ?>) curr;
                queue.addLast(currNode.left() == null ? "null" : currNode.left());
                queue.addLast(currNode.right() == null ? "null" : currNode.right());
            }
        }
        buffer.append("]");
        return buffer.toString();
    }
}
