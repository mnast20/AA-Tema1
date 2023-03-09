import trie.TestTrie;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TrieMain {
    public static void main(String[] args) throws IOException {
        String inputFileName = "./test.in";
        String outputFileName = "./test.out";
        
        // open output file
        File outputFile = new File(outputFileName);
        File existingOutputFile = new File(outputFileName);

        // if output file already exists, delete and generate it
        if (existingOutputFile.delete()) {
            outputFile = new File(outputFileName);
        }

        try {
            // get input file
            File inputFile = new File(inputFileName);
            Scanner readFile = new Scanner(inputFile);
            String testedAlgorithm;

            if (readFile.hasNextLine()) {
                // get the algorithm to be tested
                testedAlgorithm = readFile.nextLine();
            } else {
                // the file is empty
                readFile.close();
                return;
            }
            if (testedAlgorithm.compareTo("Trie") == 0) {
                // the tested algorithm is Trie
                TestTrie trieTest = new TestTrie(outputFile);
                String text;
                if (readFile.hasNextLine()) {
                    // get text to be inserted in Trie
                    text = readFile.nextLine();
                } else {
                    // there is no other line in file
                    readFile.close();
                    return;
                }
                ArrayList<String> patterns = new ArrayList<>();

                while (readFile.hasNextLine()) {
                    // get the list of patterns to be searched from file
                    String str = readFile.nextLine();

                    patterns.add(str);
                }
                // test Trie functionalities
                trieTest.testTrie(text, patterns);
            }
            // close file
            readFile.close();
        } catch (FileNotFoundException exception) {
            // no file found
            System.out.println("Could not find file");
            exception.printStackTrace();
        }
    }
}
