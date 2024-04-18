package week16_PrefixSum;

import java.util.*;
class Solution {
    static int[][] prefixSum;
    public int solution(int[][] board, int[][] skill) {
        int answer = 0;
        int N = board.length;
        int M = board[0].length;

        prefixSum = new int[N + 1][M + 1];
        for(int[] i : skill) {
        	do_skill(i[1], i[2], i[3] + 1, i[4] + 1, i[0] == 1 ? i[5] : -i[5]);
        }
        
        for(int j = 0 ; j < M + 1; j++){
            for(int i = 1; i < N + 1; i++){
            	prefixSum[i][j] += prefixSum[i-1][j];
            }
        }
        
        for(int i = 0 ; i < N + 1; i++){
            for(int j = 1 ; j < M + 1;j++){
            	prefixSum[i][j] += prefixSum[i][j-1];
            }
        }
    
        for(int i = 0 ; i < N; i++){
            for(int j = 0 ; j < M; j++){
                if(board[i][j] + prefixSum[i][j] > 0) answer++;
            }
        }
        
        return answer;
    }
    public void do_skill(int r1, int c1, int r2, int c2, int degree) {
    	prefixSum[r1][c1] -= degree;
    	prefixSum[r1][c2] += degree;
    	prefixSum[r2][c1] += degree;
    	prefixSum[r2][c2] -= degree;
    }
}