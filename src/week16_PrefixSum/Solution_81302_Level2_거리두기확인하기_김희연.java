package week16_PrefixSum;

public class Solution_81302_Level2_거리두기확인하기_김희연 {

	public int solution(int[][] board, int[][] skill) {
		
        int answer = 0;
        int N = board.length;
        int M = board[0].length;
        int preSum[][] = new int[N+1][M+1];
        
        for(int i=0; i<skill.length; i++){
            int type = skill[i][0];
            int r1 = skill[i][1], c1 = skill[i][2];
            int r2 = skill[i][3], c2 = skill[i][4];
            int degree = skill[i][5];
            
            if(type == 1){
                preSum[r1][c1] += -degree;
                preSum[r2+1][c1] += degree;
                preSum[r1][c2+1] += degree;
                preSum[r2+1][c2+1] += -degree;
            }else{ 
                preSum[r1][c1] += degree;
                preSum[r2+1][c1] += -degree;
                preSum[r1][c2+1] += -degree;
                preSum[r2+1][c2+1] += degree;
            }
        }
        
       
        for(int i=0; i<N+1; i++){
            int sum = 0;
            for(int j=0; j<M+1; j++){
                sum += preSum[i][j];
                preSum[i][j] = sum;
            }
        }
        
               
        for(int i=0; i<M; i++){
            int sum = 0;
            for(int j=0; j<N; j++){
                sum += preSum[j][i];
                preSum[j][i] = sum;
            }
        }
        
        
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(preSum[i][j] + board[i][j] > 0 ) answer++;
            }
        }                   
        
        return answer;
    }
}
