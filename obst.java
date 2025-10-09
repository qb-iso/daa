import java.util.Scanner;

public class OptimalBST {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of keys: ");
        int n = scanner.nextInt();

        int[] keys = new int[n + 1]; // 1-based indexing
        int[] freq = new int[n + 1];

        // Input keys and frequencies
        for (int i = 1; i <= n; i++) {
            System.out.print("Enter key " + i + ": ");
            keys[i] = scanner.nextInt();

            System.out.print("Enter frequency of key " + keys[i] + ": ");
            freq[i] = scanner.nextInt();
        }

        // cost[i][j] = minimum cost of OBST for keys i to j
        int[][] cost = new int[n + 1][n + 1];

        // Base case: single keys
        for (int i = 1; i <= n; i++) {
            cost[i][i] = freq[i];
        }

        // Fill cost matrix for chains of length 2 to n
        for (int length = 2; length <= n; length++) {
            for (int i = 1; i <= n - length + 1; i++) {
                int j = i + length - 1;
                cost[i][j] = Integer.MAX_VALUE;

                // Calculate sum of frequencies from i to j
                int weight = 0;
                for (int k = i; k <= j; k++) {
                    weight += freq[k];
                }

                // Try all keys as root
                for (int r = i; r <= j; r++) {
                    int left = (r > i) ? cost[i][r - 1] : 0;
                    int right = (r < j) ? cost[r + 1][j] : 0;
                    int totalCost = left + right + weight;

                    if (totalCost < cost[i][j]) {
                        cost[i][j] = totalCost;
                    }
                }
            }
        }

        // Print the cost matrix
        System.out.println("\nCost Matrix:");
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                System.out.printf("%5d", cost[i][j]);
            }
            System.out.println();
        }

        // Print the minimum cost of OBST
        System.out.println("\nMinimum cost of Optimal BST: " + cost[1][n]);

        scanner.close();
    }
}

