package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_2638_G3_치즈_강이규 {

    static int N, M;
    static Pos[][] map;
    static boolean[][] visited;
    static int cheeseCnt;
    // 방향 : 상좌하우
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(cheeseCnt > 0 ? bfs() : 0);
    }

    private static int bfs() {
        int time = 0;
        boolean[][] alreadyAdded = new boolean[N][M];
        while (++time > 0) {
            Queue<Pos> q = new ArrayDeque<>();
            visited = new boolean[N][M];
            q.offer(map[0][0]);
            visited[0][0] = true;
            List<Pos> willMelt = new ArrayList<>();

            while (!q.isEmpty()) {
                Pos cur = q.poll();

                for (int d = 0; d < 4; d++) {
                    int nr = cur.r + dr[d];
                    int nc = cur.c + dc[d];
                    if (!inRange(nr, nc)) continue;
                    Pos next = map[nr][nc];
                    if (!next.isCheese) {
                        if (visited[nr][nc]) continue;
                        q.offer(next);
                        visited[nr][nc] = true;
                        continue;
                    } else {
                        if (visited[nr][nc]) { // 2회 방문함 -> 녹을 치즈 리스트에 추가
                            if (alreadyAdded[nr][nc]) {
                                continue; // 이미 willMelt에 추가됨
                            }
                            willMelt.add(next);
                            alreadyAdded[nr][nc] = true;
                        } else { // 첫 방문 : 마킹
                            visited[nr][nc] = true;
                        }
                    }
                }
            }
            // 녹을 치즈 녹이기
            for (Pos cheese : willMelt) {
                cheese.isCheese = false;
                if (--cheeseCnt == 0) {
                    return time;
                }
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

        map = new Pos[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = new Pos(i, j, st.nextToken().equals("1"));
                if (map[i][j].isCheese) cheeseCnt++;
            }
        }
    }

    static class Pos {
        int r, c;
        boolean isCheese;

        public Pos(int r, int c, boolean isCheese) {
            this.r = r;
            this.c = c;
            this.isCheese = isCheese;
        }
    }
}
