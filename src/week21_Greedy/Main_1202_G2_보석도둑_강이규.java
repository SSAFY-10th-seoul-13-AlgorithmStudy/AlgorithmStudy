package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1202_G2_보석도둑_강이규 {

    static int N, K;
    static int[][] gems;
    static int[] bags;

    static Long res;

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void solve() {
        // 현재 가방에 담을 수 있는 것 중, 가치 내림차순으로 정렬됨(가방이 무게 오름차순이므로)
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o2[1] - o1[1]);

        int idx = 0;

        for (int bag : bags) {
            while (idx < N && gems[idx][0] <= bag) {
                pq.offer(gems[idx++]);
            }
            if (!pq.isEmpty()) {
                res += pq.poll()[1];
            }
        }
        System.out.println(res);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        gems = new int[N][2];
        bags = new int[K];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            gems[i][0] = Integer.parseInt(st.nextToken()); // m
            gems[i][1] = Integer.parseInt(st.nextToken()); // v
        }
        for (int i = 0; i < K; i++) {
            bags[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(gems, (o1, o2) -> o1[0] - o2[0]); // 무게 기준 오름차순
        Arrays.sort(bags);
        res = 0L;
    }
}
