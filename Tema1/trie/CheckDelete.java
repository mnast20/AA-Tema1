package trie;

public class CheckDelete {
    // class checking the outcome of deletion
    public int nonExistent = 0; // pattern to be deleted doesn't exist in trie or isn't a prefix
    public int deleted = 0; // pattern was successfully deleted
    public int notAWord = 0; // pattern is only a prefix in text
}