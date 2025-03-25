package BOJ;

import java.io.*;
import java.util.*;

public class Main_1461_G4_도서관_강이규 {
    static int N, M;
    static List<Integer> minus;
    static List<Integer> plus;
    static int maxAbs = -1;

    public static void main(String[] args) throws IOException {
        init();
        int res = 0;
        res += getSum(minus);
        res += getSum(plus);
        System.out.println(res * 2 - maxAbs);
    }

    private static int getSum(List<Integer> list) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int i : list) {
            pq.offer(i);
        }
        int sum = 0;

        int m = M;
        while (!pq.isEmpty()) {
            int cur = pq.poll();
            if (m == M) {
                sum += cur;
                maxAbs = Math.max(maxAbs, cur);
            }
            if (--m == 0) m = M;
        }
        return sum;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        minus = new ArrayList<>();
        plus = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int cur = Integer.parseInt(st.nextToken());
            if (cur < 0) minus.add(Math.abs(cur));
            else plus.add(cur);
        }
    }
}
