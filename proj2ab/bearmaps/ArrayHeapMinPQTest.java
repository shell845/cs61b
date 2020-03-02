package bearmaps;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ArrayHeapMinPQTest {
    @Test
    public void testAdd() {
        ArrayHeapMinPQ<Character> mP = new ArrayHeapMinPQ<>();
        mP.add('a', 3);
        mP.add('b', 4);
        mP.add('c', 6);
        assertEquals(3, mP.size());
    }

    @Test
    public void testContains() {
        ArrayHeapMinPQ<Character> mP = new ArrayHeapMinPQ<>();
        mP.add('a', 3);
        mP.add('b', 4);
        mP.add('c', 6);
        assertEquals(true, mP.contains('c'));
        assertEquals(false, mP.contains('z'));
    }

    @Test
    public void testGetSmallest() {
        ArrayHeapMinPQ<Character> mP = new ArrayHeapMinPQ<>();
        mP.add('a', 3);
        mP.add('c', 6);
        mP.add('d', 2);
        assertEquals('d', (char) mP.getSmallest());
    }

    @Test
    public void testSizeAndRemoveSmallest() {
        ArrayHeapMinPQ<Character> mP = new ArrayHeapMinPQ<>();
        mP.add('a', 3);
        mP.add('b', 4);
        mP.add('c', 6);
        mP.add('d', 2);
        mP.add('e', 2.5);
        mP.add('f', 3.5);
        assertEquals(6, mP.size());
        assertEquals('d', (char) mP.removeSmallest());
        assertEquals(5, mP.size());
    }

    @Test
    public void changePriority() {
        ArrayHeapMinPQ<Character> mP = new ArrayHeapMinPQ<>();
        mP.add('a', 3);
        mP.add('b', 4);
        mP.add('c', 6);
        mP.add('d', 2);
        mP.changePriority('d', 10);
        assertEquals('a', (char) mP.getSmallest());
        mP.changePriority('a', 10);
        assertEquals('b', (char) mP.getSmallest());
    }

}
