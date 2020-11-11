package com.gnapa.ds.tree;

/**
 * <p>
 * TODO
 * </p>
 * 
 * @author Guillermo Narvae
 */
public class TreeNode<K, V> {

    private final K key;
    private V value;

    private int height;

    private TreeNode<K, V> parent;
    private TreeNode<K, V> left;
    private TreeNode<K, V> right;

    /**
     * <p>
     * TODO
     * </p>
     */
    public TreeNode(K key, V value) {
        assert key != null : "Key cannot be null";
        this.key = key;
        this.setValue(value);
    }

    /**
     * <p>
     * TODO
     * </p>
     * 
     * @return
     */
    public int getHeight() {
        return height;
    }

    /**
     * <p>
     * TODO
     * </p>
     * 
     * @param height
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * <p>
     * TODO
     * </p>
     * 
     * @return
     */
    public K key() {
        return key;
    }

    /**
     * <p>
     * TODO
     * </p>
     * 
     * @return
     */
    public V value() {
        return value;
    }

    /**
     * <p>
     * TODO
     * </p>
     * 
     * @param value
     */
    public void setValue(V value) {
        this.value = value;
    }

    /**
     * <p>
     * TODO
     * </p>
     * 
     * @return
     */
    public TreeNode<K, V> right() {
        return right;
    }

    /**
     * <p>
     * TODO
     * </p>
     * 
     * @param right
     */
    public void setRight(TreeNode<K, V> right) {
        this.right = right;
        if (right != null) {
            right.setParent(this);
        }
    }

    /**
     * <p>
     * TODO
     * </p>
     * 
     * @return
     */
    public TreeNode<K, V> left() {
        return left;
    }

    /**
     * <p>
     * TODO
     * </p>
     * 
     * @param left
     */
    public void setLeft(TreeNode<K, V> left) {
        this.left = left;
        if (left != null) {
            left.setParent(this);
        }
    }

    /**
     * <p>
     * TODO
     * </p>
     * 
     * @return
     */
    public TreeNode<K, V> parent() {
        return parent;
    }

    /**
     * <p>
     * TODO
     * </p>
     * 
     * @param parent
     */
    public void setParent(TreeNode<K, V> parent) {
        this.parent = parent;
    }

    /**
     * <p>
     * TODO
     * </p>
     */
    @Override
    public String toString() {
        return String.format("[%s: %s]", key.toString(), value.toString());
    }
}
