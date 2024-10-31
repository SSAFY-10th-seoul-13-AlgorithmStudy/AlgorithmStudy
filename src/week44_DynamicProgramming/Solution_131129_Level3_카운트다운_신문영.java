package week44_DynamicProgramming;

class Solution {
    public int[] solution(int target) {
        int[][] dp = new int[target + 1][2];
        
        for (int i = 0; i <= target; i++) {
            dp[i][0] = Integer.MAX_VALUE;
            dp[i][1] = 0;
        }
        dp[0][0] = 0;
        
        for (int i = 1; i <= target; i++) {
            
            for (int n = 1; n <= 3; n++) {
                for (int m = 1; m <= 20; m++) {
                    if (i >= n * m) {
                        if (dp[i][0] > dp[i - n * m][0] + 1) {
                            dp[i][0] = dp[i - n * m][0] + 1;
                            if (n == 1) {
                                dp[i][1] = dp[i - n * m][1] + 1;
                            } else {
                                dp[i][1] = dp[i - n * m][1];
                            }
                        } else if (dp[i][0] == dp[i - n * m][0] + 1 && n == 1) {
                            dp[i][1] = Math.max(dp[i][1], dp[i - n * m][1] + 1);
                        }
                    }
                }
            }
            
            if (i >= 50) {
                if (dp[i][0] > dp[i - 50][0] + 1) {
                    dp[i][0] = dp[i - 50][0] + 1;
                    dp[i][1] = dp[i - 50][1] + 1;
                } else if (dp[i][0] == dp[i - 50][0] + 1) {
                    dp[i][1] = Math.max(dp[i][1], dp[i - 50][1] + 1);
                }
            } 
        }
        
        int[] answer = new int[2];
        answer[0] = dp[target][0];
        answer[1] = dp[target][1];
        return answer;
    }
}