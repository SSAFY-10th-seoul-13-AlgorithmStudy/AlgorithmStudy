import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static ArrayList<Integer>[] rc;
    static StringTokenizer st;
    static boolean[][] visit; //DP는 [그날][떡의종류]
    static int[] ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
                
        rc = new ArrayList[N];
        visit = new boolean[N+1][10];
        ans = new int[N+1];
        for (int i = 0; i < N; i++) {
            rc[i] = new ArrayList<>();
        }
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            
            int tmp = Integer.parseInt(st.nextToken());
            for (int j = 0; j < tmp; j++) {
                int tt = Integer.parseInt(st.nextToken());
                rc[i].add(tt);
            }
        }
        
        dfs(0, -1);
        System.out.println(-1);
    }
    
    public static void dfs(int idx, int prev) {
        if(idx == N) {
            for (int i = 0; i < N; i++) {
                System.out.println(ans[i]);
            }
            System.exit(0);
        }
        
        for (int i = 0; i < rc[idx].size(); i++) {
            int tmp = rc[idx].get(i);
            if(!visit[idx][tmp] && tmp != prev) {
                visit[idx][tmp] = true;
                ans[idx] = tmp;
                dfs(idx+1, tmp);
            }
        }
    }
}
