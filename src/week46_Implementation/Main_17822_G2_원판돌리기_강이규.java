package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main_17822_G2_원판돌리기_강이규 {

    static int N, M, T;
    static int[][] map;
    static int[] ptrs;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int erasedCnt = 0;

    static final int ERASED = -50;

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void solve() throws IOException {
        int total = N * M;
        while (T-- > 0) {
            if (erasedCnt == total) break;

            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            Set<Pos> matched = new HashSet<>();
            for (int i = x; i <= N; i += x) {
                rotate(i, k, d);
            }
            check(matched);

            if (!matched.isEmpty()) {
                erase(matched);
            } else {
                adjust();
            }

        }
        print();
    }

    private static void print() {
        int sum = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < M; j++) {
                sum += map[i][j] == ERASED ? 0 : map[i][j];
            }
        }
        System.out.println(sum);
    }
    private static void rotate(int idx, int amount, int d) {
        if (d == 0) { // 시계방향 => ptr -
            ptrs[idx] -= amount;
            if (ptrs[idx] < 0) ptrs[idx] += M; // max amount < M
        } else { // 반시계방향 => ptr +
            ptrs[idx] = (ptrs[idx] + amount) % M;
        }
    }

    private static void adjust() {
        // 평균 구하기
        int sum = 0;
        int validCnt = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] != ERASED) {
                    sum += map[i][j];
                    validCnt++;
                }
            }
        }
        double average = ((double) sum) / validCnt;
        // 조정
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] != ERASED) {
                    if (map[i][j] > average) map[i][j] -= 1;
                    else if (map[i][j] < average) map[i][j] += 1;
                }
            }
        }
    }

    private static void erase(Set<Pos> matched) {
        for (Pos pos : matched) {
            map[pos.r][pos.c] = ERASED;
            erasedCnt++;
        }
    }

    private static void check(Set<Pos> matched) {
        for (int i = 1; i < N; i++) {
            // 원판 간 비교
            int cur = ptrs[i], next = ptrs[i+1];
            int m = M;
            while (m-- > 0) {
                if (map[i][cur] == map[i+1][next] && map[i][cur] != ERASED) {
                    matched.add(new Pos(i, cur));
                    matched.add(new Pos(i+1, next));
                }
                // update idx
                cur = (cur + 1) % M;
                next = (next + 1) % M;
            }
            // 원판 내 비교 : ptr과 상관없다.
            // first
            if (map[i][0] == map[i][M-1] && map[i][0] != ERASED) {
                matched.add(new Pos(i, 0));
                matched.add(new Pos(i, M - 1));
            }
            if (map[i][0] == map[i][1] && map[i][0] != ERASED) {
                matched.add(new Pos(i, 0));
                matched.add(new Pos(i, 1));
            }
            // middle
            for (int j = 1, end = M - 1; j < end; j++) {
                if (map[i][j] == map[i][j-1] && map[i][j] != ERASED) {
                    matched.add(new Pos(i, j));
                    matched.add(new Pos(i, j - 1));
                }
                if (map[i][j] == map[i][j+1] && map[i][j] != ERASED) {
                    matched.add(new Pos(i, j));
                    matched.add(new Pos(i, j + 1));
                }
            }
            // last는 j=0과 j=M-1일 때 했다.
        }
        // 마지막 원판 내부 비교
        // first
        if (map[N][0] == map[N][M-1] && map[N][0] != ERASED) {
            matched.add(new Pos(N, 0));
            matched.add(new Pos(N, M - 1));
        }
        if (map[N][0] == map[N][1] && map[N][0] != ERASED) {
            matched.add(new Pos(N, 0));
            matched.add(new Pos(N, 1));
        }
        // middle
        for (int j = 1, end = M - 1; j < end; j++) {
            if (map[N][j] == map[N][j-1] && map[N][j] != ERASED) {
                matched.add(new Pos(N, j));
                matched.add(new Pos(N, j - 1));
            }
            if (map[N][j] == map[N][j+1] && map[N][j] != ERASED) {
                matched.add(new Pos(N, j));
                matched.add(new Pos(N, j + 1));
            }
        }
        // last는 j=0과 j=M-1일 때 했다.
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        map = new int[N+1][M];
        ptrs = new int[N+1]; // filled with 0

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static class Pos {
        int r, c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public boolean equals(Object o) {
            Pos pos = (Pos) o;
            return r == pos.r && c == pos.c;
        }

        @Override
        public int hashCode() {
            return r * 100 + c;
        }
    }
}
