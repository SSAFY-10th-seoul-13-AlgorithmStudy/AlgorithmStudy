package week10_BackTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_12100_G2_2048Easy_김태수 {
	static int n;
    static int[][] graph;
    static int ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(reader.readLine());

        graph = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            for (int j = 0; j < n; j++) {
                graph[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        dfs(graph, 0);
        System.out.println(ans);
    }

    static int[][] move(int[][] board, int dir) {
        int[][] newBoard = new int[n][n];

        if (dir == 0) { 
            for (int i = 0; i < n; i++) {
                int top = n - 1;
                for (int j = n - 2; j >= 0; j--) {
                    if (board[i][j] != 0) {
                        int tmp = board[i][j];
                        board[i][j] = 0;
                        if (board[i][top] == 0) {
                            board[i][top] = tmp;
                        } else if (board[i][top] == tmp) {
                            board[i][top] = tmp * 2;
                            top--;
                        } else {
                            top--;
                            board[i][top] = tmp;
                        }
                    }
                }
            }
        } else if (dir == 1) {
            for (int i = 0; i < n; i++) {
                int top = 0;
                for (int j = 1; j < n; j++) {
                    if (board[i][j] != 0) {
                        int tmp = board[i][j];
                        board[i][j] = 0;
                        if (board[i][top] == 0) {
                            board[i][top] = tmp;
                        } else if (board[i][top] == tmp) {
                            board[i][top] = tmp * 2;
                            top++;
                        } else {
                            top++;
                            board[i][top] = tmp;
                        }
                    }
                }
            }
        } else if (dir == 2) {
            for (int j = 0; j < n; j++) {
                int top = n - 1;
                for (int i = n - 2; i >= 0; i--) {
                    if (board[i][j] != 0) {
                        int tmp = board[i][j];
                        board[i][j] = 0;
                        if (board[top][j] == 0) {
                            board[top][j] = tmp;
                        } else if (board[top][j] == tmp) {
                            board[top][j] = tmp * 2;
                            top--;
                        } else {
                            top--;
                            board[top][j] = tmp;
                        }
                    }
                }
            }
        } else { 
            for (int j = 0; j < n; j++) {
                int top = 0;
                for (int i = 1; i < n; i++) {
                    if (board[i][j] != 0) {
                        int tmp = board[i][j];
                        board[i][j] = 0;
                        if (board[top][j] == 0) {
                            board[top][j] = tmp;
                        } else if (board[top][j] == tmp) {
                            board[top][j] = tmp * 2;
                            top++;
                        } else {
                            top++;
                            board[top][j] = tmp;
                        }
                    }
                }
            }
        }

        for (int i = 0; i < n; i++) {
            System.arraycopy(board[i], 0, newBoard[i], 0, n);
        }

        return newBoard;
    }

    static void dfs(int[][] board, int cnt) {
        if (cnt == 5) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    ans = Math.max(ans, board[i][j]);
                }
            }
            return;
        }

        for (int i = 0; i < 4; i++) {
            int[][] tmpBoard = move(deepCopy(board), i);
            dfs(tmpBoard, cnt + 1);
        }
    }

    static int[][] deepCopy(int[][] original) {
        int[][] copy = new int[n][];
        for (int i = 0; i < n; i++) {
            copy[i] = original[i].clone();
        }
        return copy;
    }
}
