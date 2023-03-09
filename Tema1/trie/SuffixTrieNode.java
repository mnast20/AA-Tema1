package trie;

import java.util.LinkedList;
import java.util.List;

public class SuffixTrieNode {
    final static int MAX_LEN = 256;
    private final SuffixTrieNode[] children = new SuffixTrieNode[MAX_LEN]; // list of node's children
    private final List<Integer> indexes = new LinkedList<>(); // list of indexes in string

    public SuffixTrieNode() {
        // declare list of children as empty
        for (int i = 0; i < MAX_LEN; i++) {
            children[i] = null;
        }
    }

    public SuffixTrieNode[] getChildren() {
        return children;
    }

    public List<Integer> getIndexes() {
        return indexes;
    }
}
