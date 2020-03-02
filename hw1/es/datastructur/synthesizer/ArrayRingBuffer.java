package es.datastructur.synthesizer;
import java.util.Iterator;
import java.util.Objects;

//TODO: Make sure to that this class and all of its methods are public
//TODO: Make sure to add the override tag for all overridden methods
//TODO: Make sure to make this class implement BoundedQueue<T>

public class ArrayRingBuffer<T> implements BoundedQueue<T>  {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;
    private int buffSize;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        first = 0;
        last = 0;
        fillCount = 0;
        rb = (T[]) new Object[capacity];
        buffSize = capacity;
    }

    // return iterator
    @Override
    public Iterator<T> iterator() {
        return new ArrayRingIterator();
    }

    private class ArrayRingIterator implements Iterator<T> {
        private int wizPos;

        public ArrayRingIterator() {
            wizPos = 0;
        }

        public boolean hasNext() {
            return wizPos < buffSize;
        }

        public T next() {
            T returnItem = rb[wizPos];
            wizPos += 1;
            return returnItem;
        }
    }

    // overwrite equals
    @Override
    public boolean equals(Object o) {
        // check if the object is ArrayRingBuffer with the same values
        return false;
    }

    // return size of the buffer
    @Override
    public int capacity() {
        return buffSize;
    }

    // return number of items currently in the buffer
    @Override
    public int fillCount() {
        return fillCount;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    @Override
    public void enqueue(T x) {
        // TODO: Enqueue the item. Don't forget to increase fillCount and update
        //       last.
        if (fillCount == buffSize) {
            throw new RuntimeException("Ring buffer overflow");
        }
        rb[last] = x;
        last = last + 1;
        fillCount = fillCount + 1;
        if (last == buffSize) {
            last = 0;
        }
//        System.out.println("enqueue " + x);
        return;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T dequeue() {
        // TODO: Dequeue the first item. Don't forget to decrease fillCount and
        //       update first.
        if (fillCount == 0) {
            throw new RuntimeException("Ring buffer underflow");
        }
        T d = rb[first];
        first = first + 1;
        fillCount = fillCount - 1;
        if (first == buffSize) {
            first = 0;
        }
//        System.out.println("dequeue " + d);
        return d;
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T peek() {
        // TODO: Return the first item. None of your instance variables should
        //       change.
        if (fillCount == 0) {
            throw new RuntimeException("Ring buffer underflow");
        }
        return rb[first];
    }

    public void printD() {
        for (int i=0; i < fillCount; i ++) {
            System.out.println(rb[i]);
        }
    }

    // TODO: When you get to part 4, implement the needed code to support
    //       iteration and equals.

    public static void main(String[] args) {
        ArrayRingBuffer<Double> aB = new ArrayRingBuffer<>(3);
        System.out.println("capacity is " + aB.capacity());

        aB.enqueue(1.0);
        aB.enqueue(2.0);
        aB.enqueue(3.0);
//        aB.enqueue(4.0);
        System.out.println("peek " + aB.peek());
        aB.dequeue();
        System.out.println("peek " + aB.peek());
        aB.dequeue();
        System.out.println("peek " + aB.peek());
        aB.dequeue();
//        aB.dequeue();
//        System.out.println(aB.rb);
    }
}

