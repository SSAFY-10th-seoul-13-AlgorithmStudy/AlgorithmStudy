import java.util.*;

class Solution {
    public int[] solution(int target) {
        int[] answer = new int[2];
        
        //최소한의 다트 / 싱글 혹은 불 최대한 많이
        //싱글/불 먼저 따지고 < 면 교체
        //최소 | 싱글/불
        
        int[][] dp = new int[target+1][2];
        
        for(int i = 0; i <= target; i++) {
            dp[i][0] = 1000001;
        }
        
        dp[0][0] = 0;
        int bol = 50;
        
        for (int i = 1; i <= target; i++) {
            for (int j = 1; j <= 20; j++) {
                int single = j;
                int doub = j*2;
                int triple = j*3;
                
                if(i >= bol) { //50
                    if(dp[i][0] > dp[i - bol][0] + 1) { //불 뺀 것보다 작아야
                        dp[i][0] = dp[i - bol][0] + 1;
                        dp[i][1] = dp[i - bol][1] + 1;
                    } else if (dp[i][0] == dp[i - bol][0] + 1) {
                        dp[i][1] = Math.max(dp[i][1], dp[i - bol][1] + 1);
                    }
                }
                
                
                if(i >= single) { //싱글
                    if(dp[i][0] > dp[i - single][0] + 1) { //싱글 뺀 것보다 작아야
                        dp[i][0] = dp[i - single][0] + 1;
                        dp[i][1] = dp[i - single][1] + 1;
                    } else if (dp[i][0] == dp[i - single][0] + 1) {
                        dp[i][1] = Math.max(dp[i][1], dp[i - single][1] + 1);
                    }
                }
                
                if(i >= doub) {
                    if(dp[i][0] > dp[i - doub][0] + 1) {
                        dp[i][0] = dp[i - doub][0] + 1;
                        dp[i][1] = dp[i - doub][1];
                    }
                }
                
                if(i >= triple) {
                    if(dp[i][0] > dp[i - triple][0] + 1) {
                        dp[i][0] = dp[i - triple][0] + 1;
                        dp[i][1] = dp[i - triple][1];
                    }
                }
            }
        }
        
        answer[0] = dp[target][0];
        answer[1] = dp[target][1];
        
        return answer;
    }
}
