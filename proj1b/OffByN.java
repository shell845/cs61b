import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class OffByN implements CharacterComparator {
    private int N;

    public OffByN(int n) {
        N = n;
    }

    @Override
    public boolean equalChars(char x, char y) {
        return Math.abs(x - y) == N;
    }

    /*@Test
    public void testOffByN() {
        OffByN ON = new OffByN(1);
        boolean result = ON.equalChars('a', 'b');
        assertEquals(true, result);
        result = ON.equalChars('b', 'b');
        assertEquals(false, result);

    }*/
}
