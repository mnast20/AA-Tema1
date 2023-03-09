package trie;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class StandardTrie implements Trie{
    private final TrieNode root = new TrieNode();

    public StandardTrie(String text) {
        ArrayList<String> textList = new ArrayList<>(Arrays.asList(text.split(" ")));

        // insert every word from text in the trie
        for (String word: textList) {
            insert(word);
        }
    }

    public TrieNode getRoot() {
        return root;
    }

    public void insert(String word) {
        // function inserting word in Trie
        TrieNode currentNode = root;

        for (int i = 0; i < word.length(); i++) {
            // get index of character in ascii table
            int index = word.charAt(i);
            TrieNode[] children = currentNode.getChildren();

            // no other inserted word contains the same character at the position i
            if (children[index] == null) {
                // create new node at index in ascii table
                children[index] = new TrieNode();
            }
            // advance down in the hierarchy
            currentNode = children[index];
        }
        // the full word was inserted
        currentNode.setWordEnd(true);
    }

    public boolean searchPattern(String pattern, int checkWord) {
        // function searching for given pattern
        // search starting from root
        TrieNode currentNode = root;
        for (int i = 0; i < pattern.length(); i++) {
            // get index of character in ascii table
            int index = pattern.charAt(i);
            TrieNode[] children = currentNode.getChildren();

            // the index in ascii table is not occupied
            if (children[index] == null) {
                // the pattern is not a prefix/word
                return false;
            }
            // advance down in the hierarchy
            currentNode = children[index];
        }

        // the searched pattern is required to be a word
        if (checkWord == 1) {
            return currentNode.isWordEnd();
        }

        // prefix/word was found as pattern
        return true;
    }

    public void search(ArrayList<String> patterns, PrintWriter writer) {
        // function searching for every pattern in the given ArrayList
        for (String pattern: patterns) {
            writer.printf("Searching for the pattern '%s' in the Standard Trie:\n", pattern);
            boolean result = searchPattern(pattern, 0);
            if (!result) {
                // pattern not found
                writer.printf("Pattern either doesn't exist or it isn't a word or a prefix in the given text\n", pattern);
            } else {
                // pattern found
                writer.printf("Pattern was found\n", pattern);
            }
            writer.println();
        }
    }

    static boolean isEmpty(TrieNode node)
    {
        // function checking if node has children
        TrieNode[] children = node.getChildren();
        for (TrieNode child : children) {
            if (child != null) {
                // node has at least one child
                return false;
            }
        }
        return true;
    }

    public TrieNode delete(TrieNode root, String word, int depth, CheckDelete checkDelete) {
        // function deleting full word from Trie
        if (root == null) {
            // word doesn't exist inside the text
            checkDelete.nonExistent = 1;
            return null;
        }

        // last character of word was reached
        if (depth == word.length()) {
            // removal of last character
            // check if node is the end of word
            if (root.isWordEnd()) {
                root.setWordEnd(false);
                // word will be deleted
                checkDelete.deleted = 1;
            } else {
                // pattern was only a prefix in the text
                checkDelete.notAWord = 1;
            }
            // delete respective node
            if (isEmpty(root)) {
                root = null;
            }
            return root;
        }

        // get index of characters in ascii table
        int index = word.charAt(depth);

        root.getChildren()[index] = delete(root.getChildren()[index], word, depth + 1, checkDelete);
        // root's children were deleted, and it isn't end of word
        if (isEmpty(root) && !root.isWordEnd()){
            // delete node
            root = null;
        }
        return root;
    }
}
