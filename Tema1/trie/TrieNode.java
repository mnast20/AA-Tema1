package trie;

public class TrieNode {
    static final int MAX_LEN = 256;
    private final TrieNode[] children = new TrieNode[MAX_LEN]; // list of node's children
    // determine whether node is positioned at the end of the word or not
    private boolean wordEnd;

    public TrieNode() {
        // declare children as empty
        for (int i = 0; i < MAX_LEN; i++) {
            children[i] = null;
        }
        wordEnd = false;
    }

    public TrieNode[] getChildren() {
        return children;
    }

    public boolean isWordEnd() {
        return wordEnd;
    }

    public void setWordEnd(boolean wordEnd) {
        this.wordEnd = wordEnd;
    }
}
