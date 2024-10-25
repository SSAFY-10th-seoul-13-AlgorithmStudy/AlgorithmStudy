import java.util.*;
class Solution {
    static int[] SingleOrBull, DoubleOrTriple;
    static int[][] dp;
    
    static void init(int target){
        dp = new int[100001][2];
        
        for(int i = 1; i <= target; i++){
            dp[i][0] = 100001; // dp max 값으로 초기화
        }
        
        SingleOrBull = new int[21];
        for(int i = 0; i < 21; i++){
            SingleOrBull[i] = i + 1;
        }
        SingleOrBull[20] = 50;
        
        DoubleOrTriple = new int[]{
            21, 22, 24, 26, 27, 28, 30, 32, 33, 34, 36, 38, 39, 40, 42, 45, 48, 51, 54, 57, 60
        };
        
        for(int i : SingleOrBull){
            dp[i][0] = 1;
            dp[i][1] = 1; // '싱글' 또는 '불' 던진 횟수
        }
        
        for(int i : DoubleOrTriple){
            dp[i][0] = 1;
        }
    }
    
    public int[] solution(int target) {        
        init(target);
        
        for(int i = 1; i <= target; i++){
            for(int cur : SingleOrBull){
                if(cur > i) break;
                if(dp[i][0] > dp[i - cur][0] + dp[cur][0]){ // 더 적은 다트로 갱신 할 수 있는 경우
                    dp[i][0] = dp[i - cur][0] + dp[cur][0]; // 다트 수 갱신
                    dp[i][1] = dp[i - cur][1] + dp[cur][1];
                } else if(dp[i][0] == dp[i - cur][0] + dp[cur][0]
                         && dp[i][1] < dp[i - cur][1] + dp[cur][1]){ // '싱글' 또는 '불' 더 던지기
                    dp[i][1] = dp[i - cur][1] + dp[cur][1];
                } 
            }
            for(int cur : DoubleOrTriple){
                if(cur > i) break;
                if(dp[i][0] > dp[i - cur][0] + dp[cur][0]){ // 더 적은 다트로 갱신 할 수 있는 경우
                    dp[i][0] = dp[i - cur][0] + dp[cur][0]; // 다트 수 갱신
                    dp[i][1] = dp[i - cur][1] + dp[cur][1];
                } else if(dp[i][0] == dp[i - cur][0] + dp[cur][0]
                         && dp[i][1] < dp[i - cur][1] + dp[cur][1]){ // '싱글' 또는 '불' 더 던지기
                    dp[i][1] = dp[i - cur][1] + dp[cur][1];
                } 
            }
        }
        
        return dp[target];
    }
}
