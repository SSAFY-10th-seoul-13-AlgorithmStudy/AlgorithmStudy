package week04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_1184_G1_귀농_김아린 {
    static int ans, N;
    static int[][] map;
    static int[][] prefixSum;
    static List<Integer> leftSum, rightSum;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(in.readLine());

        StringTokenizer st = null;
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        ans = 0;
        //꼭짓점 하나에서만 만난다...
        //50이라서 모든 점을 돌면서 \ / 방향으로 확인하면 될듯
        for (int i = 0; i < N-1; i++) {
            for (int j = 0; j < N-1; j++) {
                checkMap(i, j);
            }
        }

        System.out.println(ans);
    }

    private static void checkMap(int x, int y) {
        prefixSum = new int[N][N];
        leftSum = new ArrayList<>();
        rightSum = new ArrayList<>();
        //좌상
        for (int i = x; i >= 0; i--) {
            int rowSum = 0;
            for (int j = y; j >= 0; j--) {
                rowSum += map[i][j];
                prefixSum[i][j] = prefixSum[i + 1][j] + rowSum;

                leftSum.add(prefixSum[i][j]);
            }
        }
        
        //우하
        prefixSum = new int[N][N];
        for (int i = x+1; i < N; i++) {
            int rowSum = 0;
            for (int j = y+1; j < N; j++) {
                rowSum += map[i][j];
                prefixSum[i][j] = prefixSum[i - 1][j] + rowSum;

                rightSum.add(prefixSum[i][j]);
            }
        }

        checkSame();

        prefixSum = new int[N][N];
        leftSum = new ArrayList<>();
        rightSum = new ArrayList<>();
        //우상
        for (int i = x; i >= 0; i--) {
            int rowSum = 0;
            for (int j = y+1; j < N; j++) {
                rowSum += map[i][j];
                prefixSum[i][j] = prefixSum[i + 1][j] + rowSum;

                rightSum.add(prefixSum[i][j]);
            }
        }

        //좌하
        prefixSum = new int[N][N];
        for (int i = x+1; i < N; i++) {
            int rowSum = 0;
            for (int j = y; j >= 0; j--) {
                rowSum += map[i][j];
                prefixSum[i][j] = prefixSum[i - 1][j] + rowSum;

                leftSum.add(prefixSum[i][j]);
            }
        }

        checkSame();
    }

    private static void checkSame() {
        for(Integer i : leftSum) {
            for (Integer j : rightSum) {
                if(i == j)
                    ans++;
            }
        }
    }
}