import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author YC 02/18/2020
 */

public class MyTrieSet implements TrieSet61B {
    // Construct Node
    private class Node {
        private boolean isKey;
        private HashMap<Character, Node> maps;

        private Node(boolean isKey) {
            this.isKey = isKey;
            maps = new HashMap<>();
        }
    }

    private Node root;
    private char[] ch;

    // Construct MyTrieSet
    public MyTrieSet() {
        root = new Node(false);
    }

    @Override
    public void clear() {
        root = new Node(false);;
    }

    @Override
    public boolean contains(String key) {
        this.ch = key.toCharArray();
        Node n = searchKeyHelper(root, 0);
        if (n != null && n.isKey == true) {
            return true;
        }
        return false;
    }

    @Override
    public void add(String key) {
        if (key == null || key.length() < 1) {
            return;
        }
        this.ch = key.toCharArray();
        addHelper(root, 0);
    }

    private void addHelper(Node node, int index) {
        if (index == ch.length) {
            node.isKey = true;
            return;
        }
        if (!node.maps.containsKey(ch[index])) {
            node.maps.put(ch[index], new Node(false));
        }
        addHelper(node.maps.get(ch[index]), index + 1);
    }

    @Override
    public List<String> keysWithPrefix(String prefix) {
        this.ch = prefix.toCharArray();
        List<String> result = new ArrayList<>();

        Node n = searchKeyHelper(root, 0);

        if (n != null) {
            findKeyHelper(n, prefix, result);
        }

        for (String s:result) {
            System.out.println(s);
        }

        return result;
    }

    private Node searchKeyHelper(Node n, int index) {
        while (index < ch.length) {
            if (n.maps.containsKey(ch[index])) {
                n = n.maps.get(ch[index]);
                index = index + 1;
            } else {
                return null;
            }
        }
        return n;
    }

    private void findKeyHelper(Node n, String s, List<String> result) {
        if (n.isKey == true) {
            result.add(s);
        }
        for (char c:n.maps.keySet()) {
            findKeyHelper(n.maps.get(c), s + c, result);
        }
    }

    @Override
    public String longestPrefixOf(String key) {
        throw new UnsupportedOperationException();
    }

    public static void main(String[] args) {
        MyTrieSet t = new MyTrieSet();
        t.add("hello");
        t.add("hi");
        t.add("help");
//        t.add("zebra");
        t.keysWithPrefix("he");
    }
}
