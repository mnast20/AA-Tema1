package distance;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class TestEditDistance {
    private final File outputFile;
    public TestEditDistance(File outputFile) {
        this.outputFile = outputFile;
    }

    public void testEditDistance(String str1, String str2) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(outputFile);
        writer.printf("Getting distances between '%s' and '%s'\n", str1, str2);
        writer.println();

        writer.println("Testing Levenshtein Distance:");
        LevenshteinDistance LD = new LevenshteinDistance(str1, str2);

        // calculate value of Levenshtein Distance
        LD.calculateDistance();

        // display Levenshtein Distance
        writer.print("Levenshtein Distance:\n");
        writer.println(LD.getDistance());
        writer.println();

        // get Edit Distance value based on Levenshtein Distance
        writer.print("Edit Distance (based on Levenshtein Distance):\n");
        writer.println(LD.getEditDistance());

        writer.println();

        writer.println("Testing Longest Common Subsequence Distance:");
        LongestCommonSubsequenceDistance LCS = new LongestCommonSubsequenceDistance(str1, str2);

        // calculate value of LCS Distance
        LCS.calculateDistance();

        // display LCS Distance
        writer.print("Longest Common Subsequence Distance:\n");
        writer.println(LCS.getDistance());
        writer.println();

        // display LCS string
        writer.print("Longest Common Subsequence:\n");
        String LongestCommonSubsequence = LCS.findLCS();
        writer.println(LongestCommonSubsequence);
        writer.println();

        // get Edit Distance value based on LCS Distance
        writer.print("Edit Distance(based on LCS Distance):\n");
        writer.println(LCS.getEditDistance());
        writer.close();
    }
}
