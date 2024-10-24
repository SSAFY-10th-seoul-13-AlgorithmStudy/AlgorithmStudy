import java.util.*;
import java.io.*;

public class Main {
    static int[] dp;
    static int[] coin = {1, 10, 25};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //두자리씩 끊어서 생각
        int T = Integer.parseInt(br.readLine());
        dp = new int[100];
        
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        
        //dp
        for (int i = 1; i < 100; i++) {
            for (int j = 0; j < 3; j++) {
                if(i >= coin[j])
                    dp[i] = Math.min(dp[i], dp[i-coin[j]] + 1);
            }
        }
        
        
        for (int tc = 0; tc < T; tc++) {
            long input = Long.parseLong(br.readLine());
            
            int sum = 0;
            while(input > 0) {
                sum += dp[(int) (input % 100)];
                input /= 100;
            }
            
            System.out.println(sum);
        }
    }
}
