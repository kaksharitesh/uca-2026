package com.chitkara.trees;

import com.chitkara.trees.Node;

public class BST {
    private Node root;

    public void insert(int k, String v) {
        root = insert(root, k, v);
    }

    private Node insert(Node n, int k, String v) {
        if (n == null)
            return new Node(k, v);
        if (k == n.getKey()) {
            n.setValue(v); // update case
            return n;
        }
        if (k > n.getKey()) {
            n.right = insert(n.right, k, v);
            return n;
        }
        n.left = insert(n.left, k, v);
        return n;
    }

    public String search(int k) {
        Node x = search(root, k);
        if (x != null)
            return x.value;
        return null;
    }

    private Node search(Node n, int k) {
        if (n == null)
            return null;
        if (k == n.getKey()) {
            return n;
        }
        if (k > n.getKey()) {
            return search(n.right, k);
        }
        return search(n.left, k);
    }

    public static void main(String[] args) {
        BST b = new BST();
        b.insert(5, "a");
        b.insert(3, "b");
        b.insert(4, "c");
        System.out.println(b.search(4));
        System.out.println(b.search(2));

        b.insert(2, "d");

        System.out.println(b.search(2));
    }

}
