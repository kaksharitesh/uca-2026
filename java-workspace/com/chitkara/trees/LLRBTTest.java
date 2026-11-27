package com.chitkara.trees;

public class LLRBTTest {

    public static void main(String[] args) {
        testAscending();
        testDescending();
        testRandom();


    }

    private static void testAscending() {
        BST bst = new BST();
        bst.insert(2, "two");
        bst.insert(3, "three");
        bst.insert(4, "four");
        bst.insert(5, "five");
        bst.insert(7, "seven");
        bst.insert(9, "nine");


        LLRBT llrbt = new LLRBT();
        llrbt.insert(2, "two");
        llrbt.insert(3, "three");
        llrbt.insert(4, "four");
        llrbt.insert(5, "five");
        llrbt.insert(7, "seven");
        llrbt.insert(9, "nine");

        System.out.println("testAscending: Height of BST = " + bst.height() + " while Red black tree has height : " + llrbt.height());
    }

    private static void testDescending() {
        BST bst = new BST();
        bst.insert(9, "nine");
        bst.insert(7, "seven");
        bst.insert(5, "five");
        bst.insert(4, "four");
        bst.insert(3, "three");
        bst.insert(2, "two");


        LLRBT llrbt = new LLRBT();
        llrbt.insert(9, "nine");
        llrbt.insert(7, "seven");
        llrbt.insert(5, "five");
        llrbt.insert(4, "four");
        llrbt.insert(3, "three");
        llrbt.insert(2, "two");

        System.out.println("testDescending: Height of BST = " + bst.height() + " while Red black tree has height : " + llrbt.height());
    }

    private static void testRandom() {
        BST bst = new BST();
        bst.insert(5, "five");
        bst.insert(3, "three");
        bst.insert(2, "two");
        bst.insert(4, "four");
        bst.insert(7, "seven");
        bst.insert(9, "nine");


        LLRBT llrbt = new LLRBT();
        llrbt.insert(5, "five");
        llrbt.insert(3, "three");
        llrbt.insert(2, "two");
        llrbt.insert(4, "four");
        llrbt.insert(7, "seven");
        llrbt.insert(9, "nine");

        System.out.println("testRandom: Height of BST = " + bst.height() + " while Red black tree has height : " + llrbt.height());
    }
}
