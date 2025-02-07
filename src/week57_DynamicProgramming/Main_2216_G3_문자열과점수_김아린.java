import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        
        char[] cx = br.readLine().toCharArray();
        char[] cy = br.readLine().toCharArray();
        
        //재귀 완탐 bfs X
        //최대 총점을 구하기 위해선 무조건 끝까지는 가봐야함
        //두 문자열을 비교하기, i번째와 j번째를 비교한다..
        int[][] dp = new int[3001][3001];
        
        int x = cx.length;
        int y = cy.length;

        //초기값 (i와 j번째 문자가 만나지 않았다 => 그만큼의 공백-문자 조합이 생기므로 B점 생긴다 생각)
        //그냥 Integer.MIN_VALUE로 할 경우 이런 경우를 생각하지 못해 오답
        for (int i = 1; i <= x; i++) {
            dp[i][0] = i * B;
        }
        
        for (int i = 1; i <= y; i++) {
            dp[0][i] = i * B;
        }        
        
        dp[0][0] = 0;
        
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                //x만 나옴
                //y만 나옴
                dp[i+1][j+1] = Math.max(dp[i][j+1]+B, dp[i+1][j]+B);
                //x y 만남
                dp[i+1][j+1] = Math.max(dp[i+1][j+1], dp[i][j] + (cx[i] == cy[j] ? A : C));
            }
        }
        
        System.out.println(dp[x][y]);
        
    }
}
