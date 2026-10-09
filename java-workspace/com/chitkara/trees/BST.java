package com.chitkara.trees;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BST {
    private Node root;

    public int rank(int k) {
        //if (search(k) == null) return -1;
        return rank(root, k);
    }

    private int rank(Node n, Integer k) {
        if (n == null) return -1; //dead code
        int cmp = k.compareTo(n.key);
        if (cmp == 0)
            return sizeOf(n.left);
        if (cmp > 0) {
            int r = rank(n.right, k);
            if (r == -1) return -1;
            return 1 + sizeOf(n.left) + r;
        }
        return rank(n.left, k);
    }

    public void insert(Integer k, String v) {
        root = insert(root, k, v);
    }

    private Node insert(Node n, Integer k, String v) {
        if (n == null)
            return new Node(k, v);
        int cmp = k.compareTo(n.key);
        if (cmp == 0) {
            n.value = v; // update case
        } else if (cmp > 0) {
            n.right = insert(n.right, k, v);
        } else {
            n.left = insert(n.left, k, v);
        }
        n.N = 1 + sizeOf(n.left) + sizeOf(n.right);
        return n;
    }

    public int size() {
        return sizeOf(root);
    }

    private int sizeOf(Node n) {
        return n == null ? 0 : n.N;
    }

    public String search(Integer k) {
        Node x = search(root, k);
        if (x != null)
            return x.value;
        return null;
    }

    private Node search(Node n, Integer k) {
        if (n == null)
            return null;
        int cmp = k.compareTo(n.key);
        if (cmp == 0)
            return n;
        if (cmp > 0)
            return search(n.right, k);
        return search(n.left, k);
    }

    public String floor(int k) {
        Node f = floor(root, k);
        return f == null ? null : f.value;
    }

    private Node floor(Node n, Integer k) {
        if (n == null) return null;
        int cmp = k.compareTo(n.key);
        if (cmp == 0)
            return n;
        if (cmp > 0) {
            Node f = floor(n.right, k);
            return f == null ? n : f;
        }
        return floor(n.left, k);
    }

    public String ceil(int k) {
        Node f = ceil(root, k);
        return f == null ? null : f.value;
    }

    private Node ceil(Node n, Integer k) {
        if (n == null) return null;
        int cmp = k.compareTo(n.key);
        if (cmp == 0)
            return n;
        if (cmp < 0) {
            Node f = ceil(n.left, k);
            return f == null ? n : f;
        }
        return ceil(n.right, k);
    }


    public void inOrder() {
        inOrder(root);
    }

    private void inOrder(Node n) {
        if (n == null) return;
        inOrder(n.left);
        System.out.print(n.key + " -> ");
        inOrder(n.right);
    }

    public void levelOrder() {
        if (root == null) return;
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            Node n = q.poll();
            System.out.print(n.key + " ->");
            if (n.left != null) q.add(n.left);
            if (n.right != null) q.add(n.right);
        }

    }

    public void zigZag() {
        if (root == null) return;
        Stack<Node> s = new Stack<>();
        s.push(root);
        boolean lTR = false;
        Stack<Node> temp = new Stack<>();
        while (!s.isEmpty()) {
            Node n = s.pop();
            System.out.print(n.key + " ->");

            if (lTR) {
                if (n.left != null) temp.push(n.left);
                if (n.right != null) temp.push(n.right);
            } else {
                if (n.right != null) temp.push(n.right);
                if (n.left != null) temp.push(n.left);
            }
            if (s.isEmpty()) {
                lTR = !lTR;
                s = temp;
                temp = new Stack<>();
            }
        }
    }

    public Integer getMin() {
        Node x = getMin(root);
        return x == null ? null : x.key;
    }

    public Node getMin(Node n) {
        if (n.left == null) return n;
        return getMin(n.left);
    }

    public Integer getMax() {
        Node x = getMax(root);
        return x == null ? null : x.key;
    }

    public Node getMax(Node n) {
        if (n.right == null) return n;
        return getMax(n.right);
    }

    public void delMin() {
        root = delMin(root);
    }

    public Node delMin(Node n) {
        if (n.left == null) return n.right;
        n.left = delMin(n.left);
        return n;
    }

    public void delMax() {
        root = delMax(root);
    }

    public Node delMax(Node n) {
        if (n.right == null) return n.left;
        n.right = delMax(n.right);
        return n;
    }

    public int height() {
        return height(root);
    }

    private int height(Node n) {
        if (n == null) return 0;
        return 1 + Math.max(height(n.left), height(n.right));
    }

    public void del(Integer key) {
        root = del(root, key);
    }

    public Node del(Node n, Integer key) {
        if (n == null) {
            System.out.println("key not found");
            return null; //not found case
        }
        int cmp = key.compareTo(n.key);
        if (cmp > 0) {
            n.right = del(n.right, key);
            return n;
        }

        if (cmp < 0) {
            n.left = del(n.left, key);
            return n;
        }

        if (n.left == null) {  // l = null r == null
            return n.right;
        }
        if (n.right == null) {
            return n.left;
        }

        Node x = getMax(n.left);
        n.left = delMax(n.left);   // c.left = c if code is written as just delMax(n.left);  hence height will go in infinite loop
        x.left = n.left;
        x.right = n.right;
        return x;

    }

}
