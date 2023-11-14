package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_G2_13334_철로_강이규 {

    static int N, D;
    static int[][] arr; // 끝 지점 기준으로 정렬될 arr
    static Queue<Integer> pq; // 시작 지점만 삽입될 pq
    static int max = 0;
    static int curCnt;
    static int left;
    static int right;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N][2];
        pq = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            arr[i][0] = Math.min(a, b);
            arr[i][1] = Math.max(a, b);
        }
        D = Integer.parseInt(br.readLine());

        Arrays.sort(arr, (a1, a2) -> a1[1] - a2[1]); // O(NlogN)

        left = Integer.MIN_VALUE;
        right = left + D;
        curCnt = 0;

        for (int i = 0; i < N; i++) { // checkPQ까지, 약 2N(추가, poll)
            int[] cur = arr[i];
            if (cur[0] < left || cur[1] - cur[0] > D) continue; // 어차피 못 담는 경우
            if (cur[1] > right) checkPQ(cur[1]); // 철로 길이가 모자랄 때 -> 왼쪽 걸 뺀다.
            // cur 추가
            pq.offer(cur[0]);
            max = Math.max(max, ++curCnt);
        }
        System.out.println(max);
    }

    private static void checkPQ(int newRight) {
        right = newRight;
        left = right - D;
        while (!pq.isEmpty() && pq.peek() < left) {
            pq.poll();
            curCnt--;
        }
    }
}
