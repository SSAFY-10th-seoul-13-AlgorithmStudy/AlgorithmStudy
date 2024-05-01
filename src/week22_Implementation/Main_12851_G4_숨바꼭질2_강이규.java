package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main_12851_G4_숨바꼭질2_강이규 {

    static int N;
    static int K;
    static int[] minTimes;
    static int minTime;
    static int cnt;

    public static void main(String[] args) throws IOException {
        init();
        if (N >= K) {
            System.out.println((N-K) + "\n1");
            return;
        }
        bfs();
        System.out.println(minTime + "\n" + cnt);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        K = Integer.parseInt(input[1]);

        minTimes = new int[100_001];
        minTime = Integer.MAX_VALUE;
        cnt = 0;
    }

    private static void bfs() {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(N);
        minTimes[N] = 1;

        while (!q.isEmpty()) {
            int cur = q.poll();
            if (minTimes[cur] > minTime) return;

            int[] nexts = {cur+1, cur-1, cur*2};
            for (int next : nexts) {
                if (next == K) {
                    minTime = minTimes[cur];
                    cnt++;
                }
                if (next < 0 || next > 100_000) continue;
                // 첫 방문이거나, 같은 시간에 같은 곳을 방문한 다른 경우의 수라면
                if (minTimes[next] == 0 || minTimes[next] == minTimes[cur] + 1) {
                    q.offer(next);
                    minTimes[next] = minTimes[cur] + 1;
                }
            }
        }
    }

}
