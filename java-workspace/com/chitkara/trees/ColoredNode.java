package com.chitkara.trees;

import static com.chitkara.trees.Constants.*;

public class ColoredNode {
    public Integer key;
    public String value;
    public ColoredNode left;
    public ColoredNode right;
    public int N;
    public boolean color;

    public ColoredNode(Integer k, String v) {
        this.key = k;
        this.value = v;
        this.N = 1;
        this.color = RED;
    }


    @Override
    public String toString() {
        return "key=" + key + ":value=" + value + " color =" + color;
    }

}
