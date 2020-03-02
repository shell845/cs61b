public class LinkedListDeque<Item> {
    private class DequeNode {
        private Item item;
        private DequeNode prev, next;

        DequeNode(Item i, DequeNode p, DequeNode n) {
            item = i;
            prev = p;
            next = n;
        }
    }

    /* Initial sentinel node. */
    private DequeNode sentinel;
    private int size;

    /* Create an empty linked list deque. */
    public LinkedListDeque() {
        sentinel = new DequeNode(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    /* Creates a deep copy of other deque. */
    public LinkedListDeque(LinkedListDeque other) {
        size = other.size;

        DequeNode copyNode = other.sentinel.next;
        while (copyNode != other.sentinel) {
            addFirst(copyNode.item);
            copyNode = copyNode.next;
        }
    }

    /* Create an non-empty linked list deque. */
    public LinkedListDeque(Item x) {
        sentinel = new DequeNode(null,  null, null);
        sentinel.next = new DequeNode(x, sentinel, sentinel);
        sentinel.prev = sentinel.next;
        size = 1;
    }

    /* Returns true if deque is empty, false otherwise. */
    public boolean isEmpty() {
        return size == 0;
    }

    /* Add node to the front. */
    public void addFirst(Item x) {
        DequeNode newNode = new DequeNode(x, sentinel, sentinel.next);
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
        size += 1;
    }

    /* Add node to the back. */
    public void addLast(Item x) {
        DequeNode newNode = new DequeNode(x, sentinel.prev, sentinel);
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
        size += 1;
    }

    /* Remove the first node. */
    public void removeFirst() {
        DequeNode removeNode = sentinel.next;
        sentinel.next = removeNode.next;
        removeNode.next.prev = sentinel;
        size -= 1;
    }

    /* Remove the last node. */
    public void removeLast() {
        DequeNode removeNode = sentinel.prev;
        sentinel.prev = removeNode.prev;
        removeNode.prev.next = sentinel;
        size -= 1;
    }

    /* Return the first node in the deque. */
    public Item getFirst() {
        if (sentinel.next == null) {
            System.out.println("Empty deque.");
        }
        return sentinel.next.item;
    }

    /* Return the item at the given index using iteration. */
    public Item get(int index) {
        if (index >= size) {
            return null;
        }

        DequeNode p = sentinel.next;
        while (index > 0) {
            p = p.next;
            index -= 1;
        }
        return p.item;
    }

    /* Return the item at the given index using recursion. */
    public Item getRecursive(int index) {
        if (index == 0) {
            return sentinel.next.item;
        }
        return getHelper(index - 1, sentinel.next.next);
    }

    public Item getHelper(int index, DequeNode n) {
        System.out.println("index " + index);
        if (index == 0) {
            return n.item;
        }
        return getHelper(index - 1, n.next);
    }

    /* Return the size of the deque. */
    public int size() {
        return size;
    }

    /* Print the items in the deque from first to last. */
    public void printDeque() {
        DequeNode p = sentinel.next;
        while (p != sentinel) {
            System.out.print(p.item + " ");
            p = p.next;
        }
        System.out.print("\n");
    }

    public static void main(String[] args) {
        LinkedListDeque<Integer> N = new LinkedListDeque<>(5);
        N.addFirst(4);
        N.addLast((3));
        N.addLast((2));
        N.printDeque();
        System.out.println(N.getRecursive(0));
        LinkedListDeque<String> S = new LinkedListDeque<>("test");
        System.out.println(S.get(0));
    }

}
