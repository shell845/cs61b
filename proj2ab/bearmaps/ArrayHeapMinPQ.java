/**
 * @author YC 02/17/2020
 */

package bearmaps;

import edu.princeton.cs.algs4.Stopwatch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Random;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {
    private ArrayList<Node> itemList;
    private HashMap<T, Integer> itemSet;
    private int numOfNode;

    // Construct ArrayHeapMinPQ
    public ArrayHeapMinPQ() {
        itemList = new ArrayList<>();
        itemSet = new HashMap<>();
        numOfNode = 0;
    }

    // Construct Node
    private class Node implements Comparable<Node> {
        private T item;
        private double priority;

        private Node(T item, double priority) {
            this.item = item;
            this.priority = priority;
        }

        @Override
        public int compareTo(Node o) {
            if (o == null) {
                return -1;
            }
            return Double.compare(this.priority, o.priority);
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || o.getClass() != this.getClass()) {
                return false;
            } else {
                return this.item.equals(((Node) o).item);
            }
        }

        @Override
        public String toString() {
            return "(" + item + ", " + priority + ")";
        }
    }

    @Override
    public void add(T item, double priority) {
        Node newNode = new Node(item, priority);
        if ( contains(item)) {
            //throw new IllegalArgumentException();
            return;
        }
        itemList.add(newNode);
        itemSet.put(item, numOfNode);
        numOfNode += 1;
        checkAndSwap(newNode, numOfNode - 1);
    }

    private void checkAndSwap(Node newNode, int newIndex) {
        int parentIndex = getParentIndex(newIndex);
        if (parentIndex >= 0) {
            Node parentNode = itemList.get(parentIndex);
            if (newNode.compareTo(parentNode) < 0) {
                swap(newNode, newIndex, parentNode, parentIndex);
                checkAndSwap(newNode, parentIndex);
            } else {
                return;
            }
        }
    }

    private int getParentIndex(int itemIndex) {
        return (itemIndex - 1) / 2;
    }

    // Left child index
    private int getLCIndex(int itemIndex) {
        return itemIndex * 2 + 1;
    }

    // Right child index
    private int getRCIndex(int itemIndex) {
        return itemIndex * 2 + 2;
    }

    private void swap(Node a, int aInd, Node b, int bInd) {
        itemList.set(aInd, b);
        itemList.set(bInd, a);
        itemSet.put(a.item, bInd);
        itemSet.put(b.item, aInd);
    }

    @Override
    public boolean contains(T item) {
        return itemSet.containsKey(item);
    }

    @Override
    public T getSmallest() {
        if (size() <= 0) {
            throw new NoSuchElementException();
        }
        return itemList.get(0).item;
    }

    @Override
    public T removeSmallest() {
        if (size() <= 0) {
            throw new NoSuchElementException();
        }
        Node removeNode = itemList.get(0);
        swap(removeNode, 0, itemList.get(size() - 1), size() - 1);
        itemList.remove(size() - 1);
        itemSet.remove(removeNode.item);
        numOfNode -= 1;
        reOrg(0);
        return removeNode.item;
    }

    private void reOrg(int index) {
        while (getLCIndex(index) < size()) {
            int r = getLCIndex(index);
            if (getRCIndex(index) < size() && itemList.get(getRCIndex(index)).compareTo(itemList.get(r)) < 0) {
                r =getRCIndex(index);
            }
            if (itemList.get(index).compareTo(itemList.get(r)) <= 0) {
                break;
            }
            swap(itemList.get(index), index, itemList.get(r), r);
            index = r;
        }
    }

    @Override
    public int size() {
        return numOfNode;
    }

    @Override
    public void changePriority(T item, double priority) {
        if (!contains(item)) {
            throw new NoSuchElementException();
        }

        Node changeNode = new Node(item, priority);
        int index = itemSet.get(item);
        double oldPriority = itemList.get(index).priority;
        itemList.set(index, changeNode);
        if (oldPriority < priority) {
            reOrg(index);
        } else {
            checkAndSwap(changeNode, index);
        }
    }

    public void printPQ() {
        for (Node t:itemList) {
            System.out.println(t);
        }
    }

    public static void main(String[] args) {
        ArrayHeapMinPQ<Integer> mP = new ArrayHeapMinPQ<>();
        Random r = new Random();

        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i += 1) {
            mP.add(r.nextInt(500000), r.nextDouble());
        }
        long end = System.currentTimeMillis();
        System.out.println("Total time elapsed: " + (end - start)/1000.0 +  " seconds.");

        start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i += 1) {
            mP.getSmallest();
        }
        end = System.currentTimeMillis();
        System.out.println("Total time elapsed: " + (end - start)/1000.0 +  " seconds.");

    }
}
