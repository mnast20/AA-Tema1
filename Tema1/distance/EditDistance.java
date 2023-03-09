package distance;

interface EditDistance {
    // basic Edit Distance functions
    int[][] constructInitialMatrix(int n, int m);

    void calculateDistance();

    int getDistance();

    int getEditDistance();
}
