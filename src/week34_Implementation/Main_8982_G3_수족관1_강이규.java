package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_8982_G3_수족관1_강이규 {

    static int N, M, K;
    static int[] wall;
    static int[] remainWater;
    static Sink[] sinks;

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(getRemainWater());
    }

    // 구멍들을 순회하면서, 있는 바닥의 물높이를 0으로(다 빠질 것이므로)
    private static int getRemainWater() {
        for (int i = 0; i < K; i++) {
            // 구멍이 있는 바닥의 물 높이를 0으로
            Sink cur = sinks[i];
            for (int x = cur.sx; x < cur.ex; x++) {
                remainWater[x] = 0;
            }
            // 왼쪽에서 빠질 수 있는 물들을 뺌
            int height = cur.y;
            for (int x = cur.sx - 1; x >= 0; x--) {
                if (wall[x] < height)  // 구멍 높이보다 현재 칸 높이가 더 높으면
                    height = wall[x];
                if (wall[x] - remainWater[x] < height)  // 현재 칸 높이 + 남아있는 물 높이가 구멍 높이보다 높으면
                    remainWater[x] = wall[x] - height;
            }
            // 오른쪽도
            int height2 = cur.y;
            for (int x = cur.ex; x < M; x++) {
                if (wall[x] < height2)  // 구멍 높이보다 현재 칸 높이가 더 높으면
                    height2 = wall[x];
                if (wall[x] - remainWater[x] < height2)  // 현재 칸 높이 + 남아있는 물 높이가 구멍 높이보다 높으면
                    remainWater[x] = wall[x] - height2;
            }
        }
        // 남아있는 물 계산
        int totalRemainWater = 0;
        for (int i = 0; i < M; i++) {
            totalRemainWater += remainWater[i];
        }
        return totalRemainWater;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        wall = new int[40001];
        remainWater = new int[40001];

        int tempA = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (i % 2 == 0) {
                for (int j = tempA; j < a; j++) {
                    wall[j] = b;
                    remainWater[j] = b;
                }
            } else {
                tempA = a;
            }
            if (i == N - 1) M = a;
        }

        K = Integer.parseInt(br.readLine());
        sinks = new Sink[K];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int sx, ex, y;
            sx = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            ex = Integer.parseInt(st.nextToken());
            sinks[i] = new Sink(sx, ex, y);
        }

        Arrays.sort(sinks);
    }

    static class Sink implements Comparable<Sink> {
        int sx, ex, y;
        Sink(int sx, int ex, int y) {
            this.sx = sx;
            this.ex = ex;
            this.y = y;
        }

        @Override
        public int compareTo(Sink o) {
            return Integer.compare(o.y, this.y); // 내림차순
        }
    }
}
