package distance;

import java.lang.Math;

public class LevenshteinDistance implements EditDistance {
    private String str1;
    private String str2;
    private int levenshteinDistance = 0;
    private int[][] matrixDistances; // matrix used for calculating LD

    public LevenshteinDistance(String str1, String str2) {
        this.str1 = str1;
        this.str2 = str2;
    }

    public int getDistance() {
        return levenshteinDistance;
    }

    public int[][] constructInitialMatrix(int n, int m) {
        // function creating matrix and initializing first row and column
        matrixDistances = new int[n + 1][m + 1];

        for (int i = 0; i <= n; i++) {
            // retains cost for building the first i characters of string str1
            matrixDistances[i][0] = i;
        }
        for (int j = 0; j <= m; j++) {
            // retains cost for building the first j characters of string str2
            matrixDistances[0][j] = j;
        }
        return matrixDistances;
    }

    public void calculateDistance() {
        // function calculating Levenshtein distance
        int n = str1.length();
        int m = str2.length();
        matrixDistances = constructInitialMatrix(n, m);

        // value present at the position [i][j] represents
        // the distance between the first "i" characters of str1 and the first "j" characters of str2
        for (int j = 1; j <= m; j++) {
            for (int i = 1; i <= n; i++) {
                int substitutionCost = 0;

                if (str1.charAt(i - 1) != str2.charAt(j - 1)) {
                    // character in str1 can potentially be replaced
                    substitutionCost = 1;
                }

                int costDeletion = matrixDistances[i - 1][j] + 1;
                int costInsertion = matrixDistances[i][j - 1] + 1;
                int costSubstitution = matrixDistances[i - 1][j - 1] + substitutionCost;

                // compare the action costs and choose the minimum
                matrixDistances[i][j] = Math.min(Math.min(costDeletion, costInsertion), costSubstitution);
            }
        }

        // the Levenshtein Distance will always be in the bottom right corner
        levenshteinDistance = matrixDistances[n][m];
    }

    public int getEditDistance() {
        // function returning edit distance based on the Levenshtein distance
        return levenshteinDistance; // Levenshtein Distance can also be called Edit Distance
    }
}
