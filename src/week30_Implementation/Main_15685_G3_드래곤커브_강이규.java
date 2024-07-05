package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_15685_G3_드래곤커브_강이규 {

    static int N;
    static boolean[][] visited;
    // 방향 : 우 상 좌 하
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        // init
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        visited = new boolean[101][101];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x, y, d, g;
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            g = Integer.parseInt(st.nextToken());
            // 방문처리
            new Curve(x, y, d, g).draw();
        }

        // 사각형 세기
        int res = 0;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (visited[i][j] && visited[i+1][j] && visited[i][j+1] && visited[i+1][j+1]) res++;
            }
        }
        System.out.println(res);
    }


    static class Curve {
        static int[] shape; // 시작이 우측 기준, 방향들의 인덱스 정보
        static {
            makeCurve();
            System.out.println(Arrays.toString(shape));
        }

        static void makeCurve() {
            shape = new int[1 << 10]; // 2^10까지, 1부터

            shape[0] = 0;
            int gen = 10, copyRange = 1;
            for (int g = 1; g <= gen; g++) {
                int originPtr = 0;
                int copyStart = originPtr + copyRange;
                int copyEnd = copyStart + copyRange - 1;
                for (int copyPtr = copyEnd; copyPtr >= copyStart; copyPtr--) { // 끝점끼리 붙인다.
                    int newD = shape[originPtr++] - 1 - 2; // 끝점끼리 붙으면, 방향 reverse
                    if (newD < 0) newD += 4;
                    shape[copyPtr] = newD;
                }
                copyRange <<= 1;
            }
        }

        int x, y, d, gen;

        Curve(int x, int y, int d, int gen) {
            this.x = x;
            this.y = y;
            this.d = d;
            this.gen = gen;
        }

        void draw() {
            int curX = this.x, curY = this.y;
            visited[curY][curX] = true; // 시작점

            int range = 1 << gen;
            int d = this.d;
            for (int i = 0; i < range; i++) {
                int curD = (shape[i] + d) % 4;
                curX += dx[curD];
                curY += dy[curD];
                visited[curY][curX] = true;
            }
        }
    }
}
