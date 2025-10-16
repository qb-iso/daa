import java.util.Scanner;

public class BellmanFordSimple {

    // Structure to store an edge
    static class Edge {
        int src, dest, weight;
        Edge(int s, int d, int w) {
            src = s;
            dest = d;
            weight = w;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input: number of vertices and edges
        System.out.print("Enter number of vertices: ");
        int V = sc.nextInt();

        System.out.print("Enter number of edges: ");
        int E = sc.nextInt();

        Edge[] edge = new Edge[E];

        System.out.println("Enter each edge (source, destination, weight):");
        for (int i = 0; i < E; i++) {
            int s = sc.nextInt();
            int d = sc.nextInt();
            int w = sc.nextInt();
            edge[i] = new Edge(s, d, w);
        }

        System.out.print("Enter the source vertex: ");
        int source = sc.nextInt();

        // Use 1-based indexing: array size = V + 1
        int[] dist = new int[V + 1];
        for (int i = 1; i <= V; i++) {
            dist[i] = 99999;  // infinity
        }
        dist[source] = 0;

        // Relax all edges V-1 times
        for (int i = 1; i <= V - 1; i++) {
            for (int j = 0; j < E; j++) {
                int u = edge[j].src;
                int v = edge[j].dest;
                int w = edge[j].weight;

                if (dist[u] != 99999 && dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                }
            }
        }

        // Check for negative weight cycle
        boolean negativeCycle = false;
        for (int j = 0; j < E; j++) {
            int u = edge[j].src;
            int v = edge[j].dest;
            int w = edge[j].weight;
            if (dist[u] != 99999 && dist[u] + w < dist[v]) {
                negativeCycle = true;
                break;
            }
        }

        // Output results
        if (negativeCycle) {
            System.out.println("\nGraph contains a negative weight cycle!");
        } else {
            System.out.println("\nShortest distances from source " + source + ":");
            for (int i = 1; i <= V; i++) {
                System.out.println("Vertex " + i + " : " + dist[i]);
            }
        }

        sc.close();
    }
}
