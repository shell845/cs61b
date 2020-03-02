/**
 * @author YC 02/15/2020
 */

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.ArrayList;

public class MyHashMap<K, V> implements Map61B<K, V> {
    private int initialSize;
    private double loadFactor;

    private ArrayList<ArrayList<Entry>> hashTable;
    private HashSet<K> keySet;
    private int numOfEntries;
    private int numOfBkts;
    private double maxAvgBktsSize;

    /* Constructor
    initialSize = 16 and loadFactor = 0.75. */
    public MyHashMap() {
        this(16, 0.75);
    }
    public MyHashMap(int initialSize) {
        this(initialSize, 0.75);
    }

    public MyHashMap(int initialSize, double loadFactor) {
        this.initialSize = initialSize;
        this.loadFactor = loadFactor;
        hashTable = new ArrayList<>();
        keySet = new HashSet<>();
        numOfBkts = initialSize;
        maxAvgBktsSize = loadFactor;
        numOfEntries = 0;
        for (int i = 0; i < numOfBkts; i++) {
            hashTable.add(new ArrayList<Entry>());
        }
    }

    private class Entry {
        K key;
        V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    /* Removes all of the mappings from this map. */
    public void clear() {
        numOfBkts = initialSize;
        numOfEntries = 0;
        hashTable = new ArrayList<>();
        keySet = new HashSet();
    }

    /* Returns true if this map contains a mapping for the specified key. */
    public boolean containsKey(K key) {
        return keySet.contains(key);
    }

    /*
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    public V get(K key) {
        if (keySet.contains(key)) {
            for (Entry e : hashTable.get(myHashCode(key))) {
                if (e.key.equals(key)) {
                    return e.value;
                }
            }
        }
        return null;
    }

    /* Returns the number of key-value mappings in this map. */
    public int size() {
        return numOfEntries;
    }

    /*
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key,
     * the old value is replaced.
     */
    public void put(K key, V value) {
        if (containsKey(key)) {
            updateEntry(key, value);
            return;
        }

        if (numOfEntries > numOfBkts * maxAvgBktsSize) {
            resize(numOfBkts * 2);
        }

        addEntry(key, value);

    }

    private void addEntry(K key, V value) {
        (hashTable.get(myHashCode(key))).add(new Entry(key, value));
        keySet.add(key);
        numOfEntries += 1;
    }

    private void updateEntry(K key, V value) {
        ArrayList<Entry> entries = hashTable.get(myHashCode(key));
        for (Entry e:entries) {
            if (e.key.equals(key)) {
                e.value = value;
            }
        }
    }

    private void resize(int capacity) {
        numOfBkts = capacity;
        ArrayList<ArrayList<Entry>> newHashTable = new ArrayList<>();
        for (int i = 0; i < capacity; i++) {
            newHashTable.add(new ArrayList<>());
        }

        // Way 1
//        System.out.println("Size " + this);
//        for (K key:this) {
//            System.out.println(key);
//        }

        // Way 2
        for (int i = 0; i < numOfBkts / 2; i++) {
            for (Entry e:hashTable.get(i)) {
                (newHashTable.get(myHashCode(e.key))).add(e);
            }
        }
        hashTable = newHashTable;
    }

    private int myHashCode(K key) {
        int hashCode = Math.floorMod(key.hashCode(), numOfBkts);
        return hashCode;
    }

    /* Returns a Set view of the keys contained in this map. */
    public Set<K> keySet() {
        return keySet;
    }

    /*
     * Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    /*
     * Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.
     */
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    public Iterator<K> iterator() {
        return keySet.iterator();
    }

    public static void main(String[] args) {
        MyHashMap<Integer, String> map = new MyHashMap();
        map.put(3, "abc");
        map.put(6, "opq");
        map.put(12, "xyz");
        System.out.println(map.get(6));
        map.resize(16);
    }
}