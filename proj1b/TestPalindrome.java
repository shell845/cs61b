import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    /* Test isPalindrome method. */
    @Test
    public void testPalindrome() {
        boolean result = palindrome.isPalindrome("dog");
        assertEquals(false, result);
        result = palindrome.isPalindrome("dogod");
        assertEquals(true, result);
        result = palindrome.isPalindrome("noon");
        assertEquals(true, result);
        result = palindrome.isPalindrome("d");
        assertEquals(true, result);
    }

    /* Test new isPalindrome method OffByOne. */
    @Test
    public void testNewPalindrome() {
        boolean result = palindrome.isPalindrome('c', 'b');
        assertEquals(true, result);
        result = palindrome.isPalindrome('d', 'd');
        assertEquals(false, result);
    }
}
// Uncomment this class once you've created your Palindrome class.