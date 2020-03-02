public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> charDeque = new LinkedListDeque<>();
        for (char c : word.toCharArray()) {
            charDeque.addLast(c);
        }
      //charDeque.printDeque();
        return charDeque;
    }

    public boolean isPalindrome(String word) {
        Deque<Character> D = wordToDeque(word);
        return helper(D);
    }

    private boolean helper(Deque<Character> D) {
        if (D.size() <= 1) {
            return true;
        }
        if (D.removeFirst() != D.removeLast()) {
            return false;
        }
        return helper(D);
    }

    public boolean isPalindrome(Character x, Character y) {
        OffByOne oO = new OffByOne();
        return oO.equalChars(x, y);
    }

    /* Task 4. */
    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> wordDeque = wordToDeque(word);
        return helperCC(wordDeque, cc);
    }

    private boolean helperCC(Deque<Character> d, CharacterComparator c) {
        if (d.size() <= 1) {
            return true;
        }
        if (!c.equalChars(d.removeFirst(), d.removeLast())) {
            return false;
        }
        return helperCC(d, c);
    }
    
    public static void main(String[] args) {
        Palindrome S = new Palindrome();
        Deque<Character> D = S.wordToDeque("test");
        System.out.println("Palindrome: " + S.isPalindrome("b"));

        OffByOne oO = new OffByOne();
        System.out.println("OffByOne: " + S.isPalindrome("za", oO));

        OffByN oN = new OffByN(3);
        System.out.println("OffByN: " + S.isPalindrome("zahdw", oN));
    }
}
