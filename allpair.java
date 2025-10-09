public class Allpair {
    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {
        // Input matrix from the image you provided
        int[][] distances = {
            {0,   8,   INF, 1},
            {INF, 0,   1,   INF},
            {4,   INF, 0,   INF},
            {INF, 2,   9,   0}
        };

        int numPlaces = 4;

        // Run Floyd-Warshall algorithm
        floydWarshall(distances, numPlaces);

        // Print the shortest distances matrix
        System.out.println("Shortest distances between all pairs:");
        printMatrix(distances);
    }

    // Floyd-Warshall algorithm
    private static void floydWarshall(int[][] dist, int n) {
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dist[i][k] != INF && dist[k][j] != INF 
                        && dist[i][j] > dist[i][k] + dist[k][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }
    }

    // Utility method to print the matrix
    private static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == INF)
                    System.out.print("INF ");
                else
                    System.out.print(matrix[i][j] + "   ");
            }
            System.out.println();
        }
    }
}
