package com.chitkara.trees;

import java.util.*;

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

    private Node[] csdl(Node n) {
        Node[] res = new Node[2];
        if (n == null) return res;
        if (n.left != null) {
            Node[] lc = csdl(n.left);
            Node t = lc[1] == null ? lc[0] : lc[1];
            t.right = n;
            n.left = t;
            res[0] = lc[0];
        }

        if (n.right != null) {
            Node[] rc = csdl(n.right);
            rc[0].left = n;
            n.right = rc[0];
            res[1] = rc[1] == null ? rc[0] : rc[1];
        }
        if (res[0] == null) res[0] = n;
        return res;
    }

    public void csdl() {
        if (root == null)
            return;
        Node[] res = csdl(root);
        //circular
        if (res[1] != null) {
            res[1].right = res[0];
            res[0].left = res[1];
        }
        root = res[0];

    }

    public void printCsdl() {
        System.out.print("\nCircular linked list : ");
        Node temp = root;
        do {
            System.out.print(temp.key + "<=>");
            temp = temp.right;
        } while (!Objects.equals(temp.key, root.key));
        System.out.print("(" + root.key + ")\n");
    }

    public int fca(int k1, int k2) {
        return fca(root, k1, k2, null);
    }

    private int fca(Node n, int k1, int k2, Node p) {
        if (n == null) return -1;
        if (n.key == k1 || n.key == k2) return p == null ? -1 : p.key;
        if (n.key > k1 && n.key > k2) return fca(n.left, k1, k2, n);
        if (n.key < k1 && n.key < k2) return fca(n.right, k1, k2, n);
        return n.key;
    }

    public void topView() {
        Set<Integer> visited = new HashSet<>();
        Queue<NodeWithCoordinate> q = new LinkedList<>();
        q.add(new NodeWithCoordinate(root, 0));
        while (!q.isEmpty()) {
            NodeWithCoordinate t = q.poll();
            if (!visited.contains(t.c)) {
                System.out.println(t.n.key);
                visited.add(t.c);
            }
            if (t.n.left != null)
                q.add(new NodeWithCoordinate(t.n.left, t.c - 1));
            if (t.n.right != null)
                q.add(new NodeWithCoordinate(t.n.right, t.c + 1));
        }
    }

    private static class NodeWithCoordinate {
        private final Node n;
        private final int c;

        private NodeWithCoordinate(Node n, int c) {
            this.n = n;
            this.c = c;
        }
    }

}
