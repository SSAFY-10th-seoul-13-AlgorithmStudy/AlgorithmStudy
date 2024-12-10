import java.util.*;
import java.io.*;

public class Main {
    static int N, M, K;
    static int[][] dp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        //조합 가능한 문자열의 최대 개수 = 200C100 쯤??
        //10억이기 때문에 냅다 문자열 구현 X
        //문자열의 가능한 개수를 구하고 이에 따라 K번째 문자열 찾기
        dp = new int[201][201];
        
        for (int i = 0; i <= 200; i++) {
            dp[i][0] = 1;
        }   
          
        for (int i = 1; i <= 200; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i][j] = dp[i-1][j] + dp[i-1][j-1];
                if(dp[i][j] > 1_000_000_000)
                    dp[i][j] = 1_000_000_001;
            }
        }
            
        StringBuilder sb = new StringBuilder();
        if(dp[M+N][M] < K) {
            System.out.println(-1);
        } else {
            // //앞에서부터 하나씩 붙이기
            // while(N+M != 0) {
                // System.out.println(dp[N+M-1][N] + " " + sb);
                // if(dp[N+M-1][N] >= K) { //붙일 조합의 개수가 K보다 크면?
                    // sb.append("a");
                    // N--;
                // } else {
                    // sb.append("z");
                    // K -= dp[N+M-1][N];
                    // M--;
                // }
            // }
            // System.out.println(sb);
            System.out.println(moonja(sb));
        }
    }
    
    public static String moonja(StringBuilder sb) {
        if(N == 0) {
            for (int i = 0; i < M; i++) {
                sb.append("z");
            }
            return sb.toString();
        }
        else if(M == 0) {
            for (int i = 0; i < N; i++) {
                sb.append("a");
            }
            return sb.toString();
        }
        
        int now = dp[N+M-1][M];
        // System.out.println(now + " " + sb);
        if(now >= K) {
            sb.append("a");
            N--;
            moonja(sb);
        } else {
            sb.append("z");
            M--;
            K -= now;
            moonja(sb);
        }
        
        return sb.toString();
    }
}
