import java.util.*;

public class SubsetSumWithSubsets {
    
    static int[][] dp; // DP table

    // Function to fill the DP table
    public static boolean[][] buildDP(int[] arr, int sum) {
        int n = arr.length;
        boolean[][] dp = new boolean[n + 1][sum + 1];

        // Initialize
        for (int i = 0; i <= n; i++)
            dp[i][0] = true; // Sum 0 is always possible

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= sum; j++) {
                if (arr[i - 1] > j)
                    dp[i][j] = dp[i - 1][j];
                else
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - arr[i - 1]];
            }
        }

        return dp;
    }

    // Recursive function to find and print all subsets that sum to 'sum'
    public static void printSubsets(int[] arr, int i, int sum, List<Integer> current, boolean[][] dp) {
        // Base case: reached sum
        if (sum == 0) {
            System.out.println(current);
            return;
        }

        // No items left or sum becomes negative
        if (i == 0)
            return;

        // If sum can be achieved without current element
        if (dp[i - 1][sum]) {
            List<Integer> temp = new ArrayList<>(current); // clone list
            printSubsets(arr, i - 1, sum, temp, dp);       // exclude current element
        }

        // If sum can be achieved by including current element
        if (sum >= arr[i - 1] && dp[i - 1][sum - arr[i - 1]]) {
            current.add(arr[i - 1]); // include current element
            printSubsets(arr, i - 1, sum - arr[i - 1], current, dp);
        }
    }

    public static void main(String[] args) {
        int[] arr = {3, 34, 4, 12, 5, 2};
        int sum = 9;

        boolean[][] dp = buildDP(arr, sum);

        if (!dp[arr.length][sum]) {
            System.out.println("No subset with sum " + sum + " exists.");
        } else {
            System.out.println("Subsets with sum " + sum + ":");
            printSubsets(arr, arr.length, sum, new ArrayList<>(), dp);
        }
    }
}
