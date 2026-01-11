import java.util.Arrays;
import java.util.Scanner;

public class Lab4BellmanFord {

    static final int INF = 999999;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of vertices: ");
        int n = sc.nextInt();

        int[][] graph = new int[n][n];

        System.out.println("Enter weight matrix (999999 for no edge):");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                graph[i][j] = sc.nextInt();
            }
        }

        System.out.print("Enter source vertex (1-based index): ");
        int source = sc.nextInt() - 1;

        bellmanFord(graph, n, source);
        sc.close();
    }

    static void bellmanFord(int[][] graph, int n, int source) {

        int[] dist = new int[n];
        int[] parent = new int[n];

        Arrays.fill(dist, INF);
        Arrays.fill(parent, -1);

        dist[source] = 0;

        // Relax edges V-1 times
        for (int i = 0; i < n - 1; i++) {
            for (int u = 0; u < n; u++) {
                for (int v = 0; v < n; v++) {
                    if (graph[u][v] != INF && dist[u] != INF
                            && dist[u] + graph[u][v] < dist[v]) {
                        dist[v] = dist[u] + graph[u][v];
                        parent[v] = u;
                    }
                }
            }
        }

        // Check negative weight cycle
        for (int u = 0; u < n; u++) {
            for (int v = 0; v < n; v++) {
                if (graph[u][v] != INF && dist[u] != INF
                        && dist[u] + graph[u][v] < dist[v]) {
                    System.out.println("Negative weight cycle detected");
                    return;
                }
            }
        }

        printSolution(dist, parent, source);
    }

    static void printSolution(int[] dist, int[] parent, int source) {

        System.out.println("\nVertex\tDistance\tPath");

        for (int i = 0; i < dist.length; i++) {
            System.out.print((i + 1) + "\t\t");

            if (dist[i] == INF) {
                System.out.println("INF\t\tNo path");
            } else {
                System.out.print(dist[i] + "\t\t");
                printPath(parent, i);
                System.out.println();
            }
        }
    }

    static void printPath(int[] parent, int vertex) {
        if (vertex == -1)
            return;
        printPath(parent, parent[vertex]);
        System.out.print((vertex + 1) + " ");
    }
}
