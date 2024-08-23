package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main_14658_G3_하늘에서별똥별이빗발친다 {

    static int N, M, L, K;
    static int l, r, b, t;
    static Pair[] arr;
    static Set<Integer> xPos;
    static Set<Integer> yPos;

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void solve() {
        int minRemainCnt = K;
        for (int i = 0; i < K; i++) {
            // 해당 점이 특정 변에 포함되도록 설정
            Pair cur = arr[i];
            // 왼쪽 변
            l = cur.x;
            r = cur.x + L;
            for (Integer y : yPos) {
                t = y;
                b = y + L;
                minRemainCnt = Math.min(minRemainCnt, getRemainCnt());
            }
            // 오른쪽 변
            l = cur.x - L;
            r = cur.x;
            for (Integer y : yPos) {
                t = y;
                b = y + L;
                minRemainCnt = Math.min(minRemainCnt, getRemainCnt());
            }
            // 윗변
            t = cur.y;
            b = cur.y + L;
            for (Integer x : xPos) {
                l = x;
                r = x + L;
                minRemainCnt = Math.min(minRemainCnt, getRemainCnt());
            }
            // 아랫변
            t = cur.y - L;
            b = cur.y;
            for (Integer x : xPos) {
                l = x;
                r = x + L;
                minRemainCnt = Math.min(minRemainCnt, getRemainCnt());
            }
        }
        System.out.println(minRemainCnt);
    }

    private static int getRemainCnt() {
        int coveredCnt = 0;
        for (int i = 0; i < K; i++) {
            Pair cur = arr[i];
            if (inRange(cur.x, cur.y)) coveredCnt++;
        }
        return K - coveredCnt;
    }

    private static boolean inRange(int x, int y) {
        return (l <= x && x <= r) && (t <= y && y <= b);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new Pair[K];
        xPos = new TreeSet<>();
        yPos = new TreeSet<>();
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            arr[i] = new Pair(x, y);
            xPos.add(x);
            yPos.add(y);
        }

    }

    static class Pair {
        int x, y;
        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
