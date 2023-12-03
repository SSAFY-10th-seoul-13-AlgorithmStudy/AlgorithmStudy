package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2571_G3_색종이3_강이규 {

    static int N;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        init();
        calSums();
        System.out.println(findRect());
    }

    private static void calSums() {
        for (int i = 0; i < 100; i++) {
            for (int j = 1; j < 100; j++) {
                if (arr[i][j-1] != 0 && arr[i][j] != 0)
                    arr[i][j] = arr[i][j-1] + 1;
            }
        }
    }

    private static int findRect() {
        int res = 0;

        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                int cur = arr[i][j];

                for (int k = i; k < 100; k++) { // 진행방향 : 세로
                    cur = Math.min(cur, arr[k][j]);
                    if (cur < 1) break;
                    res = Math.max(res, cur * (k-i+1));
                }
            }
        }
        return res;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[100][100];

        StringTokenizer st;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int left = Integer.parseInt(st.nextToken());
            int top = Integer.parseInt(st.nextToken()); // 상하 반전돼도 상관없음
            for (int h = 0; h < 10; h++) {
                int row = top + h;
                for (int w = 0; w < 10; w++) {
                    int col = left + w;
                    arr[row][col] = 1;
                }
            }
        }
    }
}