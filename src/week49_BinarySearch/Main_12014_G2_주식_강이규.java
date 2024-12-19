package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_12014_G2_주식_강이규 {

    static int T, N, K;
    static int[] price;
    static List<Integer> lis;
    static final String ansPrefix = "Case #";

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());
        int t = T;
        while (t-- > 0) {
            init();
            sb.append(ansPrefix).append(T - t).append("\n").append(solve()).append("\n");
        }
        System.out.println(sb);
    }

    private static int solve() {
        lis.add(price[0]);
        for (int p : price) {
            if (p > lis.get(lis.size() - 1)) lis.add(p);
            else {
                int idx = binarySearch(lis, p);
                lis.set(idx, p);
            }
        }
        return lis.size() >= K ? 1 : 0;
    }

    private static int binarySearch(List<Integer> list, int x) {
        int l = 0;
        int r = list.size();

        while (l < r) {
            int mid = (l + r) >> 1;

            int v = list.get(mid);
            if (x > v) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        price = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            price[i] = Integer.parseInt(st.nextToken());
        }

        lis = new ArrayList<>();
    }
}
