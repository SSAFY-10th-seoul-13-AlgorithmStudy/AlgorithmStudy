```java
public class Solution {
    public int solution(int[][] board, int[][] skill) {
        int answer = 0;
        int rows = board.length;
        int cols = board[0].length;
        int[][] effects = new int[rows + 1][cols + 1];
        
        // 스킬 적용 계산
        for (int[] action : skill) {
            int type = action[0], startRow = action[1], startCol = action[2],
                endRow = action[3], endCol = action[4], strength = action[5];
            
            int effect = (type == 1) ? -strength : strength;
            
            effects[startRow][startCol] += effect;
            effects[startRow][endCol + 1] -= effect;
            effects[endRow + 1][startCol] -= effect;
            effects[endRow + 1][endCol + 1] += effect;
        }
        
        // 가로 방향 누적합
        for (int i = 0; i < rows; i++) {
            for (int j = 1; j <= cols; j++) {
                effects[i][j] += effects[i][j - 1];
            }
        }

        // 세로 방향 누적합
        for (int j = 0; j < cols; j++) {
            for (int i = 1; i <= rows; i++) {
                effects[i][j] += effects[i - 1][j];
            }
        }
        
        // 최종 상태에서 정답 찾기
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] += effects[i][j];
                if (board[i][j] > 0) {
                    answer++;
                }
            }
        }
        
        return answer;
    }
}
```
