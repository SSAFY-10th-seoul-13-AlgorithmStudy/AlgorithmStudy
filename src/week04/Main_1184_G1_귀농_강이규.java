package BOJ;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1184_G1_귀농_강이규 {

    /*
    풀이 :
    1. 입력 map을 받고, 누적합으로 초기화
    2. 두 면이 만나는 점을 기준으로 map 반복
      3. (2, 4사분면) 왼쪽 끝점과 오른쪽 끝점으로, 2중 반복문을 돈다.
      4. (1, 3사분면) "
      5. 두 가지수를 더한다.
    6. 결과 출력
     */

    static int N;
    static int[][] sum;
    static long res;

    public static void main(String[] args) throws IOException {
        init();
        solve();
        System.out.println(res);
    }

    private static void solve() {
        // 가운데 점 반복문
        for (int rMid = 1; rMid < N; rMid++) { // 현재칸 오른쪽 아래 점 기준
            for (int cMid = 1; cMid < N; cMid++) {
                // 2,4 사분면
                int lCnt = leftCheck(rMid, cMid);
                // 1,3 사분면
                int rCnt = rightCheck(rMid, cMid);
                res += lCnt + rCnt;
            }
        }
    }

    private static int leftCheck(int mr, int mc) {
        int res = 0;
        // 2사분면 꼭지점
        for (int i = 1; i <= mr; i++) {
            for (int j = 1; j <= mc; j++) {
                int leftSum = calWidth(i, j, mr, mc);
                // 4사분면 꼭지점
                int sr = mr+1;
                int sc = mc+1;
                for (int k = sr; k <= N; k++) {
                    for (int l = sc; l <= N; l++) {
                        int rightSum = calWidth(sr, sc, k, l);
                        if (leftSum != rightSum) continue;
                        res++;
                    }
                }
            }
        }
        return res;
    }

    private static int rightCheck(int mr, int mc) {
        int res = 0;
        // 1사분면
        for (int i = mr; i >= 1; i--) {
            for (int j = mc+1; j <= N; j++) {
                int rightSum = calWidth(i, mc+1, mr, j);
                // 3사분면
                int sr = mr+1;
                for (int k = sr; k <= N; k++) {
                    for (int l = mc; l >= 1; l--) {
                        int leftSum = calWidth(sr, l, k, mc);
                        if (leftSum != rightSum) continue;
                        res++;
                    }
                }
            }
        }
        return res;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        sum = new int[N+1][N+1];

        // 입력 & 가로
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                sum[i][j] = sum[i][j-1] + Integer.parseInt(st.nextToken());
            }
        }
        // 세로
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                sum[i][j] += sum[i-1][j];
            }
        }
    }

    private static int calWidth(int sr, int sc, int er, int ec) {
        return sum[er][ec] - sum[sr-1][ec] - sum[er][sc-1] + sum[sr-1][sc-1];
    }
}