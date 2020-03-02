import edu.princeton.cs.algs4.BST;

import java.util.Iterator;
import java.util.Set;

/**
 * @author YC 02/13/2020
 */

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    // Build a node
    private class TreeNode {
        private K key;
        private V val;
        private TreeNode leftC, rightC;

        TreeNode(K k, V v) {
            key = k;
            val = v;
            leftC = null;
            rightC = null;
        }
    }

    private TreeNode root;
    private int size;

    // Build a tree
    public BSTMap() {
        size = 0;
    }

    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        size = 0;
        root = null;
    }

    /* Returns true if this map contains a mapping for the specified key. */
    @Override
    public boolean containsKey(K k) {
        if ( searchHelper(root, k) != null) {
            return true;
        }
        return false;
    }

    // Search helper to search key k
    private TreeNode searchHelper(TreeNode t, K k) {
        if ( t == null) {
            return null;
        }

        if (k.compareTo(t.key) == 0) {
            return t;
        } else if (t.leftC != null && k.compareTo(t.key) < 0) {
            return searchHelper(t.leftC, k);
        } else if (t.rightC != null && k.compareTo(t.key) > 0) {
            return searchHelper(t.rightC, k);
        }

        return null;
    }

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K k) {
        TreeNode getKV = searchHelper(root, k);
        if ( getKV != null) {
            return getKV.val;
        }
        return null;
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    /* Associates the specified value with the specified key in this map. */
    @Override
    public void put(K k, V v) {
        if ( root == null) {
            root = new TreeNode(k, v);
            size += 1;
            System.out.println("Put new node, size is " + size);
        } else {
            putHelper(root, k, v);
        }
    }

    // Put helper to put key and value
    private void putHelper(TreeNode t, K k, V v) {
        if (k.compareTo(t.key) == 0) {
            t.val = v;
        } else if (k.compareTo(t.key) < 0) {
            if (t.leftC == null) {
                TreeNode newNode = new TreeNode(k, v);
                t.leftC = newNode;
                size += 1;
            } else {
                putHelper(t.leftC, k , v);
            }
        } else if (k.compareTo(t.key) > 0) {
            if (t.rightC == null) {
                TreeNode newNode = new TreeNode(k, v);
                t.rightC = newNode;
                size += 1;
            } else {
                putHelper(t.rightC, k, v);
            }
        }
    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException("Unsupported Operation.");
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException("Unsupported Operation.");
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException("Unsupported Operation.");
    }

    @Override
    public Iterator iterator() {
        throw new UnsupportedOperationException("Unsupported Operation.");
    }

    // Prints out BSTMap in order of increasing Key
    public void printInOrder() {
        if (root != null) {
            findSmall(root);
        }
    }

    private void findSmall(TreeNode t) {
        if (t.leftC != null) {
            findSmall(t.leftC);
            System.out.println("Key value pair (" + t.key + ", " + t.val + ")");
        } else if (t.rightC != null) {
            System.out.println("Key value pair (" + t.key + ", " + t.val + ")");
            findSmall(t.rightC);
        } else {
            System.out.println("Key value pair (" + t.key + ", " + t.val + ")");
        }
    }

    public static void main(String[] args) {
        BSTMap b = new BSTMap();
        b.put("C", "3");
        b.put("B", "2");
        b.put("A", "1");
        b.put("A", "5");
        System.out.println("Size is " + b.size());
        b.printInOrder();
    }
}
