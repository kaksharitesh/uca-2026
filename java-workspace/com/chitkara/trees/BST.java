package com.chitkara.trees;

public class BST {
    private Node root;

    public static void main(String[] args) {
        BST b = new BST();
        b.insert("b", 2);
        b.insert("a", 1);
        b.insert("d", 4);
        b.insert("c", 3);
        b.insert("e", 5);

        System.out.println(b.getMax());
        System.out.println(b.getMin());

        System.out.println("height = " + b.height());
        b.del("d");

        System.out.println(b.search("a"));
        System.out.println(b.search("b"));
        System.out.println(b.search("c"));
        System.out.println(b.search("d"));
        System.out.println(b.search("e"));

        System.out.println("height = " + b.height());

    }

    public void insert(String k, int v) {
        root = insert(root, k, v);
    }

    private Node insert(Node n, String k, int v) {
        if (n == null)
            return new Node(k, v);

        int cmp = k.compareTo(n.key);

        if (cmp == 0) {
            n.value = v; // update case
            return n;
        }
        if (cmp > 0) {
            n.right = insert(n.right, k, v);
            return n;
        }
        n.left = insert(n.left, k, v);
        return n;
    }

    public Integer search(String k) {
        Node x = search(root, k);
        if (x != null)
            return x.value;
        return null;
    }

    private Node search(Node n, String k) {
        if (n == null)
            return null;

        int cmp = k.compareTo(n.key);

        if (cmp == 0) {
            return n;
        }
        if (cmp > 0) {
            return search(n.right, k);
        }
        return search(n.left, k);
    }

    public String getMin() {
        Node x = getMin(root);
        return x == null ? null : x.key;
    }

    public Node getMin(Node n) {
        if (n.left == null) return n;
        return getMin(n.left);
    }

    public String getMax() {
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

    public void del(String key) {
        root = del(root, key);
    }

    public Node del(Node n, String key) {
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
