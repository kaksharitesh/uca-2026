package com.chitkara.trees;

public class BSTTest {
    public static void main(String[] args) {
        BST b = new BST();
        b.insert(5, "five");
        b.insert(3, "three");
        b.insert(4, "four");
        b.insert(2, "two");
        b.insert(7, "seven");
        b.insert(9, "nine");
        System.out.println(b.search(8));
        System.out.println(b.search(9));

        System.out.println(b.floor(3)); //3
        System.out.println(b.floor(8)); //7

        System.out.println(b.ceil(3));  //3
        System.out.println(b.ceil(8)); //9

        System.out.println("size = " + b.size());

        System.out.println("rank 3 = " + b.rank(3)); // 1
        System.out.println("rank 2 = " + b.rank(2)); // 0
        System.out.println("rank 7 = " + b.rank(7)); // 4
        System.out.println("rank 8 = " + b.rank(8)); // -1

        b.inOrder();
        System.out.println();
        b.levelOrder();
        System.out.println();
        b.zigZag();

        System.out.println(b.getMin());
        System.out.println(b.getMax());
        b.delMin();
        b.delMax();
        System.out.println(b.getMin());
        System.out.println(b.getMax());

        b.insert(9, "nine");
        b.insert(2, "two");

        b.del(3);
        System.out.println();
        b.inOrder();

    }
}
