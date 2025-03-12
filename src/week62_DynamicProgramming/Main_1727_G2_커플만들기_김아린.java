import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static int[] w, m;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        m = new int[N];
        w = new int[M];
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            m[i] = Integer.parseInt(st.nextToken());
        }
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            w[i] = Integer.parseInt(st.nextToken());
        }
        
        //....DP????
        //dp[N번째남자][M번째여자] = new Score(커플수, 성격합) ???
        //=> 완탐이나 마찬가지인데 대략 5억.. 시간복잡도 터짐
        
        //ㅇㅋ DP인데 dp[N번째남자][M번째여자] (N번째 남자가 M번째 여자를 선택했을때)
        //dp[i][j] = Math.min(dp[i][j-1], dp[i-1][j-1] + 차이);
        
        // Score[][] dp = new Score[N][M];
        int[][] dp = new int[N][M];
        
        Arrays.sort(w);
        Arrays.sort(m);

        dp[0][0] = Math.abs(w[0] - m[0]);
        for (int i = 1; i < M; i++) {
            dp[0][i] = Math.min(dp[0][i-1], Math.abs(w[i] - m[0]));
        }
        for (int i = 1; i < N; i++) {
            dp[i][0] = Math.abs(w[0] - m[i]);
        }
    
        
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < M; j++) {
                if(i > j) {
                    dp[i][j] = Math.min(dp[i-1][j], dp[i-1][j-1] + Math.abs(m[i] - w[j]));   
                }
                else if (i == j) {
                    dp[i][j] = dp[i-1][j-1] + Math.abs(m[i] - w[j]);
                }
                else if(i < j) {
                	dp[i][j] = Math.min(dp[i][j-1], dp[i-1][j-1] + Math.abs(m[i] - w[j]));
                }
            }
        }
        
        System.out.println(dp[N-1][M-1]);
    }
}

