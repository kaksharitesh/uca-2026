package com.chitkara.tries;

public class TrieTest {

    public static void main(String[] args) {
        Trie t = new Trie();
        t.insert("tommy", 26);
        System.out.println(t.search("tommy")); // 26
        System.out.println(t.search("tom")); // -1
        System.out.println(t.startsWith("tom")); // true

        t.insert("tom", 10);
        System.out.println(t.search("tommy")); // 26
        System.out.println(t.search("tom")); // 10
        System.out.println(t.startsWith("tom")); // true


        t.del("tom");
        System.out.println(t.search("tommy")); // 26
        System.out.println(t.search("tom")); // -1
        System.out.println(t.startsWith("tom")); // true

        t.insert("tom", 10);
        t.del("tommy");
        System.out.println(t.search("tommy")); // -1
        System.out.println(t.search("tom")); // 10
        System.out.println(t.startsWith("tom")); // true
        System.out.println(t.startsWith("tomm")); // false

    }
}
