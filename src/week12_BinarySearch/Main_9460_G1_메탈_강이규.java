package week12_BinarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_9460_G1_메탈_강이규 {

    static int N;
    static int K;
    static Metal[] metal;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            init();
            sb.append(String.format("%.1f", binSearch())).append("\n");
        }
        System.out.println(sb);
    }

    private static boolean isPossible(double x) {
        int cnt = 1;
        int minY, maxY;

        minY = maxY = metal[0].y;

        for (int i = 1; i < N; i++) {
            minY = Math.min(minY, metal[i].y);
            maxY = Math.max(maxY, metal[i].y);

            // 터널이 더 필요할 때
            if (maxY - minY > 2 * x) {
                minY = maxY = metal[i].y; // 하나 더 놓기
                cnt++;
            }
        }
        return cnt <= K;
    }

    private static double binSearch() {
        double ans = 0;
        double l = 0, r = 200_000_000; // 나올 수 있는 최대값의 범위
        while (0.01 < r - l) {
            ans = (l + r) / 2;
            if (isPossible(ans)) {
                r = ans;
            } else {
                l = ans;
            }
        }
        return ans;
    }

    private static void init() throws IOException {
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        metal = new Metal[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            metal[i] = new Metal(x, y);
        }
        Arrays.sort(metal);
    }

    static class Metal implements Comparable<Metal> {
        int x, y;
        public Metal(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Metal o) {
            return this.x - o.x; // max +- 2억
        }
    }
}
