package trie;

import java.io.*;
import java.util.ArrayList;

public class TestTrie {
    private final File outputFile;

    public TestTrie(File outputFile) {
        this.outputFile = outputFile;
    }

    public void testTrie(String text, ArrayList<String> patterns) throws IOException {
        FileWriter fw = new FileWriter(outputFile);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter writer = new PrintWriter(bw);
        
        // insert text in standard trie
        StandardTrie standardTrie = new StandardTrie(text);

        writer.println("Testing Standard Tree:");

        // search patterns in standard trie
        standardTrie.search(patterns, writer);

        // testing deletion of the first pattern
        writer.println("Deleting the word '" + patterns.get(0) + "' from the Standard Trie:");
        CheckDelete checkDelete = new CheckDelete();
        standardTrie.delete(standardTrie.getRoot(), patterns.get(0), 0, checkDelete);
        if (checkDelete.nonExistent == 1) {
            // nothing needed to be deleted
            writer.println("Word doesn't exist in the text, thus no deletion available");
        } else if (checkDelete.notAWord == 1) {
            writer.println("The given pattern was only a prefix in the text, thus nothing to be done");
        } else {
            // check if the deletion failed or if word remains only a suffix in text
            boolean searchPrefix = standardTrie.searchPattern(patterns.get(0), 0);
            boolean searchWord = standardTrie.searchPattern(patterns.get(0), 1);
            if (searchWord) {
                writer.println("The removal of the word failed");
            } else {
                if (searchPrefix) {
                    // the removed word was already a prefix in the text
                    writer.println("The word was deleted but remains only a prefix in the text");
                } else {
                    // the pattern was only a word in the text
                    writer.println("The word was removed successfully and isn't a prefix in the text");
                }
            }
        }

        writer.println();

        // insert text in suffix tree
        SuffixTrie suffixTrie = new SuffixTrie(text);

        writer.write("Testing Suffix Tree:\n");

        // search patterns in suffix trie
        suffixTrie.search(patterns, writer);

        writer.close();
    }
}
