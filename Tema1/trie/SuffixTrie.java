package trie;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SuffixTrie implements Trie {
    private final SuffixTrieNode root = new SuffixTrieNode();

    public SuffixTrie(String text) {
        insert(text);
    }

    public void insert(String str) {
        // function inserting string in Suffix Trie
        for (int i = 0; i < str.length(); i++) {
            // insert every suffix/substring of the given string in the tree
            insertSuffix(root, str.substring(i), i);
        }
    }

    public void insertSuffix(SuffixTrieNode node, String suffix, int index) {
        // function inserting suffix in tree
        List<Integer> indexes = node.getIndexes();
        SuffixTrieNode[] children = node.getChildren();

        // add index to list of indexes
        indexes.add(index);
        // check is suffix was fully inserted
        if (suffix.length() > 0) {
            // get index of first character in ascii table
            char character = suffix.charAt(0);

            // character wasn't found at its designated spot in ascii table
            if (children[character] == null) {
                // create new child node
                children[character] = new SuffixTrieNode();
            }
            // recursively insert rest of substring
            insertSuffix(children[character], suffix.substring(1), index + 1);
        }
    }

    public List<Integer> searchPattern(SuffixTrieNode node, String pattern) {
        // function searching for given pattern
        List<Integer> indexes = node.getIndexes();
        SuffixTrieNode[] children = node.getChildren();

        if (pattern.length() == 0) {
            // get list of indexes where pattern was found
            return indexes;
        }
        char character = pattern.charAt(0);

        // character was found at its designated spot in ascii table
        if (children[character] != null) {
            // advance down in the hierarchy and search for substring of pattern
            return searchPattern(children[character], pattern.substring(1));
        }
        else {
            // full pattern was not found
            return null;
        }
    }

    public void search(ArrayList<String> patterns, PrintWriter writer) {
        // function searching for every pattern in the given ArrayList
        for (String pattern: patterns) {
            writer.printf("Searching for the pattern '%s' in the Suffix Trie:\n", pattern);
            List<Integer> result = searchPattern(root, pattern);
            if (result == null) {
                // pattern not found
                writer.println("Pattern was not found");
            } else {
                // pattern found
                int nrAppearances = result.size();

                // get number of appearances
                writer.printf("Pattern appeared %d times\n", nrAppearances);
                int patternLength = pattern.length();

                writer.print("Pattern was found at the positions:");
                int count = 0;
                for (Integer i : result) {
                    // traverse list of indexes that pattern was found at
                    if (count != 0) {
                        writer.print(",");
                    }
                    // get index where pattern started in the text
                    writer.printf(" %d", (i - patternLength));
                    count++;
                }
                if (pattern.compareTo(patterns.get(patterns.size() - 1)) != 0) {
                    writer.println();
                }
            }
            if (pattern.compareTo(patterns.get(patterns.size() - 1)) != 0) {
                writer.println();
            }
        }
    }

    static boolean isEmpty(SuffixTrieNode root)
    {
        // function checking if node has children
        SuffixTrieNode[] children = root.getChildren();
        for (SuffixTrieNode child : children)
            if (child != null) {
                // node has at least one child
                return false;
            }
        return true;
    }

}
