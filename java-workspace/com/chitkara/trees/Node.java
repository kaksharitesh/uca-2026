package com.chitkara.trees;

public class Node {
    public Integer key;
    public String value;
    public Node left;
    public Node right;
    public int N;

    public Node(Integer k, String v) {
        this.key = k;
        this.value = v;
        this.N = 1;
    }


    @Override
    public String toString() {
        return "key=" + key + ":value=" + value;
    }

}
