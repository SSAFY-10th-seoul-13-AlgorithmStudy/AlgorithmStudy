package week10_BackTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_18430_G4_무기공학_김희연 {
    static int n, m;
    static int[][] tree;
    static boolean[][] visit;
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        tree = new int[n][m];
        visit = new boolean[n][m];

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++){
                tree[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0);

        System.out.println(result);
    }

    public static void dfs(int depth, int temp){
        if(depth == n * m){
            result = Math.max(result, temp);
            return;
        }

        int x = depth / m;
        int y = depth % m;

        if(!visit[x][y]){
            if(x+1 < n && y-1 >= 0 && !visit[x+1][y] && !visit[x][y-1]){
                visit[x+1][y] = true;
                visit[x][y] = true;
                visit[x][y-1] = true;
                dfs(depth+1, temp + tree[x+1][y] + tree[x][y-1] + tree[x][y] * 2);
                visit[x+1][y] = false;
                visit[x][y] = false;
                visit[x][y-1] = false;
            }

            if(x-1 >= 0 && y-1 >= 0 && !visit[x-1][y] && !visit[x][y-1]){
                visit[x-1][y] = true;
                visit[x][y] = true;
                visit[x][y-1] = true;
                dfs(depth+1, temp + tree[x-1][y] + tree[x][y-1] + tree[x][y] * 2);
                visit[x-1][y] = false;
                visit[x][y] = false;
                visit[x][y-1] = false;
            }

            if(x-1 >= 0 && y+1 < m && !visit[x-1][y] && !visit[x][y+1]){
                visit[x-1][y] = true;
                visit[x][y] = true;
                visit[x][y+1] = true;
                dfs(depth+1, temp + tree[x-1][y] + tree[x][y+1] + tree[x][y] * 2);
                visit[x-1][y] = false;
                visit[x][y] = false;
                visit[x][y+1] = false;
            }

            if(x+1 < n && y+1 < m && !visit[x+1][y] && !visit[x][y+1]){
                visit[x+1][y] = true;
                visit[x][y] = true;
                visit[x][y+1] = true;
                dfs(depth+1, temp + tree[x+1][y] + tree[x][y+1] + tree[x][y] * 2);
                visit[x+1][y] = false;
                visit[x][y] = false;
                visit[x][y+1] = false;
            }
        }

        dfs(depth+1, temp);
    }
}