import java.io.*;

public class Main {
    static int N;
    static double INF = 1e18;
    static double[][] map, dp;
    static int[][] coords;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());
        
        coords = new int[N][2];
    
        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            coords[i][0] = Integer.parseInt(input[0]);
            coords[i][1] = Integer.parseInt(input[1]);
        }
        
        dp = new double[N][1 << N];
        map = new double[N][N];
        
        System.out.printf("%.9f%n", tsp(0, 1));
    }
    
    static double distance(int i, int j) {
        if (map[i][j] == 0) {
            int dx = coords[i][0] - coords[j][0];
            int dy = coords[i][1] - coords[j][1];
            map[i][j] = map[j][i] = Math.sqrt(dx * dx + dy * dy);
        }
        return map[i][j];
    }
    
    static double tsp(int node, int visited) {
        if (visited == (1 << N) - 1) return distance(node, 0);
            
        if (dp[node][visited] != 0) return dp[node][visited];
            
        dp[node][visited] = INF;
        for (int next = 0; next < N; next++) {
            if ((visited & (1 << next)) != 0) continue;
            
            dp[node][visited] = Math.min(dp[node][visited], 
                distance(node, next) + tsp(next, visited | (1 << next)));
        }
        
        return dp[node][visited];
    }
}
