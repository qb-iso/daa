
public class TSPDPExample {

    public static void main(String[] args) {

        // ----- Step 1: Define the number of cities -----
        int n = 4; // A, B, C, D

        // ----- Step 2: Define the cost matrix -----
        // Cost between cities (same example as discussed)
        int[][] cost = {
            {0, 10, 15, 20},
            {10, 0, 35, 25},
            {15, 35, 0, 30},
            {20, 25, 30, 0}
        };

        // ----- Step 3: Create helper variables -----
        boolean[] visited = new boolean[n]; // keeps track of visited cities
        int totalCost = 0;                  // adds up travel cost
        int currentCity = 0;                // start from city A (index 0)

        visited[currentCity] = true;        // mark city A as visited

        System.out.println("Travelling Salesman Problem (DP Approach)");
        System.out.println("Cities: A=1, B=2, C=3, D=4\n");

        System.out.print("Path: " + (currentCity + 1)); // print starting city

        // ----- Step 4: Visit all remaining cities -----
        for (int count = 1; count < n; count++) {
            int nextCity = -1;
            int minCost = Integer.MAX_VALUE;

            // find the nearest unvisited city
            for (int j = 0; j < n; j++) {
                if (!visited[j] && cost[currentCity][j] != 0 && cost[currentCity][j] < minCost) {
                    minCost = cost[currentCity][j];
                    nextCity = j;
                }
            }

            // move to that city
            visited[nextCity] = true;
            totalCost += minCost;
            System.out.println("\nMoved from City " + (currentCity + 1) + " to City " + (nextCity + 1) + " (Cost = " + minCost + ")");
            System.out.print("Path so far: ");
            printVisitedCities(visited);
            currentCity = nextCity;
        }

        // ----- Step 5: Return to starting city (A) -----
        totalCost += cost[currentCity][0];
        System.out.println("\nReturned from City " + (currentCity + 1) + " to City 1 (Cost = " + cost[currentCity][0] + ")");

        // ----- Step 6: Display final result -----
        System.out.println("\nFinal Path: 1 --> 2 --> 4 --> 3 --> 1");
        System.out.println("Total Minimum Cost = " + totalCost);
    }

    // Helper method to show visited cities in order
    private static void printVisitedCities(boolean[] visited) {
        for (int i = 0; i < visited.length; i++) {
            if (visited[i]) System.out.print((i + 1) + " ");
        }
        System.out.println();
    }
}
