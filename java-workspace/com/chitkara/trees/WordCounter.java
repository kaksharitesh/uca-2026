package com.chitkara.trees;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class WordCounter {

    public static void main(String[] args) throws IOException {
        BST bst = new BST();
        try (BufferedReader br = new BufferedReader((new FileReader(new File("input.txt"))))) {
            String s = br.readLine();
            while (s != null) {
                System.out.println(s);
                String[] tokens = s.split(" ");
                for (String token : tokens) {
                    if(bst.search(token) != null){
                        bst.insert(token, 1+bst.search(token));
                    }else {
                        bst.insert(token, 1);
                    }
                }
                s = br.readLine();
            }
        }
        String word = "the";
        System.out.println("count of word " + word + " is = " + bst.search(word));

    }
}
