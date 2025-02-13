package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1079_G2_마피아_강이규 {
    static int N;
    static int[] guiltyPoints;
    static int[][] guiltyEffects;
    static int eunjin;
    static int maxNightCnt;
    static boolean[] dead;

    public static void main(String[] args) throws IOException {
        init();
        recur(0, N);
        System.out.println(maxNightCnt);
    }

    private static void recur(int nightCnt, int remain) {
        if (remain == 1) {
            maxNightCnt = Math.max(maxNightCnt, nightCnt);
            return;
        }

        if (remain % 2 == 0) {
            for (int i = 0; i < N; i++) {
                if (dead[i] || i == eunjin) continue;
                // 죽이기
                dead[i] = true;
                for (int j = 0; j < N; j++) {
                    guiltyPoints[j] += guiltyEffects[i][j];
                }
                recur(nightCnt + 1, remain - 1);
                // 복구
                dead[i] = false;
                for (int j = 0; j < N; j++) {
                    guiltyPoints[j] -= guiltyEffects[i][j];
                }
            }
        } else {
            int maxGuilty = Integer.MIN_VALUE;
            int target = -1;
            for (int i = 0; i < N; i++) {
                if (dead[i]) continue;
                if (guiltyPoints[i] > maxGuilty) { // 값이 같으면, 번호가 작은 순
                    maxGuilty = guiltyPoints[i];
                    target = i;
                }
            }
            dead[target] = true;
            if (target == eunjin) {
                maxNightCnt = Math.max(maxNightCnt, nightCnt);
                dead[target] = false;
                return;
            }
            recur(nightCnt, remain - 1);
            dead[target] = false;
        }
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        guiltyPoints = new int[N];
        dead = new boolean[N];
        guiltyEffects = new int[N][N];
        maxNightCnt = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            guiltyPoints[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                guiltyEffects[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        eunjin = Integer.parseInt(br.readLine());
    }
}
