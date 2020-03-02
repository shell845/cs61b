public class ArrayDeque<Item> {
    private  Item[] items;
    private int size;

    /* Create an empty list. */
    public ArrayDeque() {
        items = (Item[]) new Object[100];
        size = 0;
    }

    /* Resize the array. */
    private  void resize(int capacity) {
        Item[] a = (Item[]) new Object[capacity];
        System.arraycopy(items, 0, a, 0, size);
        items = a;
    }

    /* Add to the front of the list */
    public void addFirst(Item x) {
        if (size > 0) {
            Item[] a = (Item[]) new Object[items.length+1];
            System.arraycopy(items, 0, a, 1, size);
            items = a;
        }
        items[0] = x;
        size += 1;
    }
    /* Add to the back of the list. */
    public void addLast(Item x) {
        if (size == items.length) {
            resize(size*2);
        }

        items[size] = x;
        size += 1;
    }

    /* Remove first item from the list. */
    public void removeFirst() {
        size -= 1;
        Item[] a = (Item[]) new Object[size];
        System.arraycopy(items, 1, a, 0, size);
        items = a;
    }

    /* Remove last item from the list. */
    public void removeLast() {
        size -= 1;
        items[size] = null;
    }

    /* Get the ith item in the list (0 is the front). */
    public Item get(int i) {
        return items[i];
    }

    /* Return the size of the array. */
    public int size() {
        return size;
    }

    /* Print the items in the array from first to last. */
    public void printDeque() {
        for (int i=0; i < size; i++) System.out.print(items[i] + " ");
        System.out.print("\n");
    }

    /* Returns true if array is empty, false otherwise. */
    public boolean isEmpty() {
        return size == 0;
    }

    public static void main(String[] args) {
        ArrayDeque<Integer> N = new ArrayDeque<>();
    }
}
