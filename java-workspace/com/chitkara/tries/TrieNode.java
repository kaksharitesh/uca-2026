package com.chitkara.tries;

public class TrieNode {
    public TrieNode[] children;
    public Integer val;

    public TrieNode() {
        children = new TrieNode[26];
        val = null;
    }
}
