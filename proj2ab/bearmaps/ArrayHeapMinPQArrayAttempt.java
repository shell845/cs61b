/**
 * @author YC 02/17/2020
 */

/**
 * This attempt use array to solve the problem.
 * Need further enhance add() method as it's ok to not strickly follow BST.
 * Other methods are not yet complete.
 */

/*

package bearmaps;

import java.util.HashSet;

import static bearmaps.PrintHeapDemo.printFancyHeapDrawing;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {
    private Node[] itemList;
    private HashSet<T> itemSet;
    private int initialArraySize = 100;
    private int numOfNode;

    // Construct ArrayHeapMinPQ
    public ArrayHeapMinPQ() {
        itemList = new ArrayHeapMinPQ.Node[initialArraySize];
        itemSet = new HashSet<>();
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
            throw new IllegalArgumentException();
        }
        int newIndex = insertNode(newNode);
        checkAndSwap(newNode, newIndex);
        itemSet.add(item);
        numOfNode += 1;
    }

    private int insertNode(Node n) {
        if (numOfNode == 0) {
            itemList[0] = n;
            return 0;
        } else {
            return insertNodeHelper(n, 0);
        }
    }

    private int insertNodeHelper(Node n, int parentIndex) {
        Node parentNode = itemList[parentIndex];

        if (parentNode == null) {
            itemList[parentIndex] = n;
            return parentIndex;
        }

        if (n.compareTo(parentNode) <= 0) {
            return insertNodeHelper(n, getLCIndex(parentIndex));
        } else {
            return insertNodeHelper(n, getRCIndex(parentIndex));
        }
    }

    private void checkAndSwap(Node newNode, int newIndex) {
        int parentIndex = getParentIndex(newIndex);
        if (parentIndex >= 0) {
            Node parentNode = itemList[parentIndex];
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
        itemList[aInd] = b;
        itemList[bInd] = a;
    }

    @Override
    public boolean contains(T item) {
        return itemSet.contains(item);
    }

    @Override
    public T getSmallest() {
        return itemList[0].item;
    }

    @Override
    public T removeSmallest() {
        return null;
    }

    @Override
    public int size() {
        return numOfNode;
    }

    @Override
    public void changePriority(T item, double priority) {

    }

    public T[] arrayT = (T[]) new Object[100];
    public void printPQ(T t) {
        for (int i = 0; i < 15; i++) {
            arrayT[i+1] = t;
            if (itemList[i] != null) {
                System.out.println(itemList[i] + " " + i);
                arrayT[i+1] = itemList[i].item;
            }
        }
    }

    public static void main(String[] args) {
        ArrayHeapMinPQ<Character> mP = new ArrayHeapMinPQ<>();
        mP.add('a', 3);
        mP.add('b', 4);
        mP.add('c', 6);
        mP.add('d', 2);
//        printFancyHeapDrawing(mP.arrayT);
        mP.add('e', 2.5);
//        mP.printPQ(' ');
//        System.out.println("Size is " + mP.size());
//        printFancyHeapDrawing(mP.arrayT);
        mP.add('f', 3.5);
        mP.printPQ(' ');
        System.out.println("Size is " + mP.size());
        printFancyHeapDrawing(mP.arrayT);
    }
}
*/
