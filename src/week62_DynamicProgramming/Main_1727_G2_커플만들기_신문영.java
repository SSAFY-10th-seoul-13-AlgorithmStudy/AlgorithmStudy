package week62_DynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1727_G2_커플만들기_신문영 {
    static int n,m;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int dp[][] = new int[n + 1][m + 1];
        for(int i=0; i<dp.length; i++) {
            dp[i][0] = 0;
        }
        for(int i=0; i<dp[0].length; i++) {
            dp[0][i] = 0;
        }
        
        int male[] = new int[n];
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            male[i] = Integer.parseInt(st2.nextToken());
        }
        
        int female[] = new int[m];
        StringTokenizer st3 = new StringTokenizer(br.readLine());
        for(int i=0; i<m; i++) {
            female[i] = Integer.parseInt(st3.nextToken());
        }
        
        //n == m인 경우에는 남자들의 성격과 여자들의 성격을 오름차순으로 정렬하고, 각 인덱스의 차이의 합이 최적해
        //n != m인 경우, 남자 또는 여자가 추가되었을 때, 반대편 성별의 마지막 사람과 합을 맞추어 보고 크기 비교
        Arrays.sort(male);
        Arrays.sort(female);
        
        for(int i=1; i<=n; i++) {
            for(int j=1; j<=m; j++) {
                if(i == j) {
                    dp[i][j] = dp[i - 1][j - 1] + Math.abs(male[i - 1] - female[j - 1]);
                } else if(i > j) {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j - 1] + Math.abs(male[i - 1] - female[j - 1]));
                } else {
                    //i < j
                    dp[i][j] = Math.min(dp[i][j - 1], dp[i - 1][j - 1] + Math.abs(male[i - 1] - female[j - 1]));
                }
            }
        }
        
        System.out.println(dp[n][m]);
    }
}
