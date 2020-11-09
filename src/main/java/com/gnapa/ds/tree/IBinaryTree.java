package com.gnapa.ds.tree;

/**
 * <p>
 * TODO
 * </p>
 * 
 * @author Guillermo Narvaez
 */
public interface IBinaryTree<K, V> {
    
    /**
     * <p>
     * TODO
     * </p>
     * 
     * @param key
     * @param value
     */
    public void insert(K key, V value);

    /**
     * <p>
     * TODO
     * </p>
     * 
     * @param key
     * @return
     */
    public V get(K key);

    /**
     * <p>
     * TODO
     * </p>
     * 
     * @param key
     * @return
     */
    public V remove(K key);

    /**
     * <p>
     * TODO
     * </p>
     * 
     * @return
     */
    public String serialize();
    
}
