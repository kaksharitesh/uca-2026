package com.chitkara.tries;

public class Trie {

    private final TrieNode root;

    public Trie() {
        this.root = new TrieNode();
    }

    public void insert(String w, int val) {
        TrieNode t = root;
        for (char ch : w.toCharArray()) {
            int index = ch - 'a';
            if (t.children[index] == null) t.children[index] = new TrieNode();
            t = t.children[index];
        }
        t.val = val;
    }

    private TrieNode searchNode(String w) {
        TrieNode t = root;
        for (char ch : w.toCharArray()) {
            int index = ch - 'a';
            if (t.children[index] == null) return null;
            t = t.children[index];
        }
        return t;
    }

    public int search(String w) {
        TrieNode t = searchNode(w);
        if (t == null) return -1;
        return t.val == null ? -1 : t.val;
    }

    public boolean startsWith(String w) {
        TrieNode t = searchNode(w);
        return t != null;
    }

    public void del(String w) {
        del(root, w, 0);
    }

    private boolean hasChild(TrieNode n) {
        for (TrieNode t : n.children) {
            if (t != null) return true;
        }
        return false;
    }

    private boolean del(TrieNode n, String w, int d) {
        if (n == null) return false;
        if (d == w.length()) {
            n.val = null;
            return !hasChild(n);
        }
        int index = w.charAt(d) - 'a';
        if (del(n.children[index], w, d + 1)) {
            n.children[index] = null;
            return n.val == null && !hasChild(n);
        }
        return false;
    }
}
