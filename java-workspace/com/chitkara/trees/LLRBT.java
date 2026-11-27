package com.chitkara.trees;

public class LLRBT {
    private ColoredNode root;


    public void insert(Integer k, String v) {
        //System.out.println("inserting " + k);
        root = insert(root, k, v);
        root.color = Constants.BLACK;
    }

    private ColoredNode insert(ColoredNode n, Integer k, String v) {
        if (n == null)
            return new ColoredNode(k, v);
        int cmp = k.compareTo(n.key);
        if (cmp == 0) {
            n.value = v; // update case
        } else if (cmp > 0) {
            n.right = insert(n.right, k, v);
        } else {
            n.left = insert(n.left, k, v);
        }

        if (colorOf(n.right) == Constants.RED && colorOf(n.left) == Constants.BLACK) {
            n = leftRotate(n);
        }
        if (colorOf(n.left) == Constants.RED && colorOf(n.left.left) == Constants.RED) {
            n = rightRotate(n);
        }

        if (colorOf(n.right) == Constants.RED && colorOf(n.left) == Constants.RED) {
            n = flipColor(n);
        }

        n.N = 1 + sizeOf(n.left) + sizeOf(n.right);
        return n;
    }

    private ColoredNode flipColor(ColoredNode n) {
        //System.out.println("flipping color " + n.key);
        n.left.color = n.color;
        n.right.color = n.color;
        n.color = Constants.RED;
        return n;
    }

    private ColoredNode rightRotate(ColoredNode n) {
        //System.out.println("right rotating " + n.key);
        ColoredNode x = n.left;
        n.left = x.right;
        x.right = n;
        x.color = n.color;
        n.color = Constants.RED;
        return x;
    }

    private ColoredNode leftRotate(ColoredNode n) {
        //System.out.println("left rotating " + n.key);
        ColoredNode x = n.right;
        n.right = x.left;
        x.left = n;
        x.color = n.color;
        n.color = Constants.RED;
        return x;
    }

    private boolean colorOf(ColoredNode n) {
        return n == null ? Constants.BLACK : n.color;
    }

    public int size() {
        return sizeOf(root);
    }

    private int sizeOf(ColoredNode n) {
        return n == null ? 0 : n.N;
    }

    public String search(Integer k) {
        ColoredNode x = search(root, k);
        if (x != null)
            return x.value;
        return null;
    }

    private ColoredNode search(ColoredNode n, Integer k) {
        if (n == null)
            return null;
        int cmp = k.compareTo(n.key);
        if (cmp == 0)
            return n;
        if (cmp > 0)
            return search(n.right, k);
        return search(n.left, k);
    }

    public int height() {
        return height(root);
    }

    private int height(ColoredNode n) {
        if (n == null) return 0;
        return 1 + Math.max(height(n.left), height(n.right));
    }
}
