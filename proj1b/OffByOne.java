import org.junit.Test;
import static org.junit.Assert.*;

public class OffByOne implements CharacterComparator {

    @Override
    public boolean equalChars(char x, char y) {
        return Math.abs(x - y) == 1;
    }

    /*@Test
    public void testOffByOne() {
        OffByOne OO = new OffByOne();
        boolean result = OO.equalChars('a', 'b');
        assertEquals(true, result);
        result = OO.equalChars('b', 'b');
        assertEquals(false, result);

    }*/
}
