package week16_PrefixSum;

public class Solution_92344_Level3_파괴되지않은건물_강이규 {

    static int N, M;
    static int[][] map;
    static int[][] skill;

    public int solution(int[][] board, int[][] skillInput) {
        N = board.length;
        M = board[0].length;
        map = new int[N+2][M+2];
        skill = skillInput;

        mark();
        fill();
        return addInitValAndCount(board);
    }

    private int addInitValAndCount(int[][] board) {
        int cnt = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                map[i][j] += board[i-1][j-1];
                cnt += 0 < map[i][j] ? 1 : 0;
            }
        }
        return cnt;
    }

    private void mark() {
        int amount, revAmount, r1, r2, c1, c2;
        for (int[] s : skill) {
            amount = s[5] * (s[0] != 2 ? -1 : 1);
            revAmount = amount * -1;
            r1 = s[1] + 1;
            c1 = s[2] + 1;
            r2 = s[3] + 2;
            c2 = s[4] + 2;

            map[r1][c1] += amount;
            map[r1][c2] += revAmount;
            map[r2][c1] += revAmount;
            map[r2][c2] += amount;
        }
    }

    private void fill() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                map[i][j] = map[i-1][j] + map[i][j-1] - map[i-1][j-1] + map[i][j];
            }
        }
    }

//    private int count() {
//        int cnt = 0;
//        for (int i = 1; i <= N; i++) {
//            for (int j = 1; j <= M; j++) {
//                cnt += 0 < map[i][j] ? 1 : 0;
//            }
//        }
//        return cnt;
//    }
}
