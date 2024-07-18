package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_13164_G5_행복유치원_강이규 {

    static int N, K;
    static int[] arr;
    static Interval[] intervals;
    static long res = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N];
        intervals = new Interval[N-1];
        st = new StringTokenizer(br.readLine());

        arr[0] = Integer.parseInt(st.nextToken());
        for (int i = 1; i < N; i++) {
            int cur = Integer.parseInt(st.nextToken());
            arr[i] = cur;
            intervals[i-1] = new Interval(i-1, i, arr[i] - arr[i-1]);
        }
        Arrays.sort(intervals);

        for (int i = 0, end = N - K; i < end; i++) {
            res += intervals[i].gap;
        }
        System.out.println(res);
    }

    static class Interval implements Comparable<Interval> {
        int a, b, gap;

        public Interval(int a, int b, int gap) {
            this.a = a;
            this.b = b;
            this.gap = gap;
        }

        @Override
        public int compareTo(Interval o) {
            return this.gap - o.gap;
        }
    }
}
