package com.chitkara.trees;

public class Node {
    public String key;
    public Integer value;
    public Node left;
    public Node right;

    public Node(String k, Integer v) {
        this.key = k;
        this.value = v;
    }


    @Override
    public String toString() {
        return "key=" + key + ":value=" + value;
    }

}
