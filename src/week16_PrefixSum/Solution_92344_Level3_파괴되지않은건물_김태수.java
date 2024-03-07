package week16_PrefixSum;

public class Solution_92344_Level3_파괴되지않은건물_김태수 {
	public int solution(int[][] board, int[][] skill) {
        int answer = 0;
        int N = board.length;
        int M = board[0].length;
        
        int[][] sum = new int[N+1][M+1];
        
        for(int i = 0 ; i < skill.length;i++){
            int type = skill[i][0];
            int x1 = skill[i][1];
            int y1 = skill[i][2];
            int x2 = skill[i][3];
            int y2 = skill[i][4];
            int degree = skill[i][5];
            
            if(type == 2){    
                sum[x1][y1] += degree;
                sum[x1][y2+1] -= degree;
                sum[x2+1][y1] -= degree;
                sum[x2+1][y2+1] += degree;
            }
            else{
                sum[x1][y1] -= degree;
                sum[x1][y2+1] += degree;
                sum[x2+1][y1] += degree;
                sum[x2+1][y2+1] -= degree;
            }
        }
        
        for(int j = 0 ; j < sum[0].length;j++){
            for(int i = 1; i < sum.length;i++){
                sum[i][j] += sum[i-1][j];
            }
        }
        
        for(int i = 0 ; i< sum.length;i++){
            for(int j = 1 ; j < sum[0].length;j++){
                sum[i][j] += sum[i][j-1];
            }
        }
    
        for(int i = 0 ; i< board.length;i++){
            for(int j = 0 ; j < board[0].length;j++){
                if(board[i][j] + sum[i][j] > 0) answer++;
            }
        }
        
        return answer;
    }
}
