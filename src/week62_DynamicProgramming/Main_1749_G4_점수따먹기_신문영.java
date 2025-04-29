package week62_DynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1749_G4_점수따먹기_신문영 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        long[][] board = new long[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 1; j <= M; j++) {
            	// 2차원 배열 누적합
                board[i][j] = Integer.parseInt(st.nextToken()) + board[i - 1][j] + board[i][j - 1] - board[i - 1][j - 1];
            }
        }

        long answer = Integer.MIN_VALUE;
        for (int width = 1; width <= N; width++) {
            for (int height = 1; height <= M; height++) {
                for (int row = width; row <= N; row++) {
                    for (int col = height; col <= M; col++) {
                        answer = Math.max(answer, board[row][col] - (board[row - width][col] + board[row][col - height]) + board[row - width][col - height]);
                    }
                }
            }
        }

        System.out.println(answer);
    }
}
