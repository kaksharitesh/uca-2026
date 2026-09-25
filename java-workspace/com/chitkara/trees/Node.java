package com.chitkara.trees;

public class Node {
    public int key;
    public String value;
    public Node left;
    public Node right;

    public Node(int k, String v) {
        this.key = k;
        this.value = v;
    }


    @Override
    public String toString() {
        return "key=" + key + ":value=" + value;
    }

    public int getKey() {
        return this.key;
    }

    public void setValue(String v) {
        this.value = v;
    }
}
