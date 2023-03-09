package distance;

import java.lang.Math;

public class LongestCommonSubsequenceDistance implements EditDistance {
    private final String str1;
    private final String str2;
    private int LCSDistance = 0;
    private int[][] matrixDistances;

    public LongestCommonSubsequenceDistance(String str1, String str2) {
        this.str1 = str1;
        this.str2 = str2;
    }

    public int getDistance() {
        return LCSDistance;
    }

    public int[][] constructInitialMatrix(int n, int m) {
        // function creating matrix and initializing first row and column
        matrixDistances = new int[n + 1][m + 1];

        // first row and column should be empty because there is no common subsequence yet
        for (int i = 0; i <= n; i++) {
            matrixDistances[i][0] = 0;
        }
        for (int j = 0; j <= m; j++) {
            matrixDistances[0][j] = 0;
        }
        return matrixDistances;
    }

    public void calculateDistance() {
        // function calculating LCS distance
        int n = str1.length();
        int m = str2.length();
        matrixDistances = constructInitialMatrix(n, m);

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    // the longest common subsequence increases in length
                    // because two identical characters were found
                    matrixDistances[i][j] = matrixDistances[i - 1][j - 1] + 1;
                } else {
                    // find the longest/maximum common sequence
                    // between the first "i" characters of str1 and the first "j" characters of str2
                    matrixDistances[i][j] = Math.max(matrixDistances[i][j - 1], matrixDistances[i - 1][j]);
                }
            }
        }
        // The LCS Distance will always be in the bottom right corner
        LCSDistance = matrixDistances[n][m];
    }

    public int getEditDistance() {
        // function returning edit distance based on the LCS distance
        return str1.length() + str2.length() - 2 * LCSDistance;
    }

    public String findLCS() {
        // function finding the longest common subsequence of the two strings
        int m = str2.length();
        int n = str1.length();
        StringBuilder LongestCommonSubsequence = new StringBuilder();
        int count = LCSDistance;
        int i = n;
        int j = m;

        // traverse matrix until all characters of LCS were reached
        while (count > 0) {
            if (matrixDistances[i][j - 1] > matrixDistances[i - 1][j]) {
                // go to position of maximum, move left
                j--;
            } else if (matrixDistances[i][j - 1] < matrixDistances[i - 1][j]){
                // go to position of maximum, move up
                i--;
            } else {
                if (matrixDistances[i - 1][j] != count) {
                    if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                        // character in LCS found
                        LongestCommonSubsequence.append(str2.charAt(j - 1));
                        // number of searched characters decreases
                        count--;
                    }
                    // move diagonally
                    i--;
                    j--;
                } else {
                    // move up
                    i--;
                }
            }
        }
        // because search started from the bottom right, the found subsequence will be reversed
        LongestCommonSubsequence.reverse();
        return LongestCommonSubsequence.toString();
    }
}
