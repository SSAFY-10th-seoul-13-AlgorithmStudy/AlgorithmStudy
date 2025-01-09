package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_14923_G4_미로탈출_강이규 {

    static class Node {
        int r, c, dist;
        int useCnt;

        public Node(int r, int c, int dist, int useCnt) {
            this.r = r;
            this.c = c;
            this.dist = dist;
            this.useCnt = useCnt;
        }
    }

    static int N, M;
    static boolean[][] moveable;
    static boolean[][][] visited; // r, c, used
    // 방향 : 상 좌 하 우
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, -1, 0, 1};

    static int HR, HC, ER, EC;
    static int D;

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(bfs());
    }

    private static int bfs() {
        Queue<Node> q = new ArrayDeque<>();
        q.offer(new Node(HR, HC, 0, 0));
        visited[HR][HC][0] = true;

        while (!q.isEmpty()) {
            Node cur = q.poll();

            if (cur.r == ER && cur.c == EC) {
                return cur.dist;
            }

            for (int d = 0; d < 4; d++) {
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];
                if (!inRange(nr, nc)) continue;
                if (!moveable[nr][nc]) {
                    if (cur.useCnt > 0) continue;
                    if (visited[nr][nc][1]) continue;
                    // 한번 무시하기. (여기서 이동한 칸을 중복 방문할 일은 없다.)
                    visited[nr][nc][1] = true;
                    q.offer(new Node(nr, nc, cur.dist + 1, 1));
                    continue;
                }
                // 빈 칸이라면
                if (visited[nr][nc][cur.useCnt]) continue;
                visited[nr][nc][cur.useCnt] = true;
                q.offer(new Node(nr, nc, cur.dist + 1, cur.useCnt));
            }
        }
        return -1;
    }

    private static boolean inRange(int r, int c) {
        return (0 <= r && r < N) && (0 <= c && c < M);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 홍익이
        st = new StringTokenizer(br.readLine());
        HR = Integer.parseInt(st.nextToken()) - 1;
        HC = Integer.parseInt(st.nextToken()) - 1;

        // 도착지
        st = new StringTokenizer(br.readLine());
        ER = Integer.parseInt(st.nextToken()) - 1;
        EC = Integer.parseInt(st.nextToken()) - 1;

        // map
        moveable = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                moveable[i][j] = st.nextToken().equals("0");
            }
        }
        visited = new boolean[N][M][2];
        D = Integer.MAX_VALUE;
    }
}
