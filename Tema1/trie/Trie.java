package trie;

import java.io.PrintWriter;
import java.util.ArrayList;

interface Trie {
    // basic Standard and Suffix Trie functions
    void insert(String word);
    void search(ArrayList<String> patterns, PrintWriter writer);
}
