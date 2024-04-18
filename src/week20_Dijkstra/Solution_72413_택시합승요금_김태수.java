import java.util.*;

class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int INF = 100000 * n + 1;
        int[][] map = new int[n][n];
        
        for(int i = 0; i < n;i++){
            for(int j = 0 ; j < n;j++){
                if(i == j) map[i][j] = 0;
                else map[i][j] = INF;
            }
        }
        
        for(int i = 0,end = fares.length ; i < end;i++){
            int left = fares[i][0]-1;
            int right = fares[i][1]-1;
            int fee = fares[i][2];
            map[left][right] = fee;
            map[right][left] = fee;
        }
        
        for(int k = 0; k < n;k++){
            for(int i = 0 ; i < n;i++){
                for(int j = 0 ; j < n;j++){
                    map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
                }
            }
        }
        
        int answer = map[s-1][a-1] + map[s-1][b-1];
        for(int i = 0;i < n;i++){
            answer = Math.min(map[s-1][i] + map[i][a-1] + map[i][b-1], answer);    
        }
        return answer;
    }
}