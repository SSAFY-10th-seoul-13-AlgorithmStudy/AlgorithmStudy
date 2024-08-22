package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_2933_G1_미네랄_강이규 {

    static int R, C, N;
    static char[][] map;
    static int[][] visited;
    static int[] sticks;
    // 방향 : 상좌하우
    static int[] dr = {1, 0, -1, 0};
    static int[] dc = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        init();
        throwSticks();
        printMap();
    }

    private static void printMap() {
        StringBuilder sb = new StringBuilder();
        for (int i = R; i > 0; i--) {
            for (int j = 0; j < C; j++) {
                sb.append(map[i][j]);
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    private static void throwSticks() {
        for (int i = 0; i < N; i++) {
            int r = sticks[i];
            int c;
            if (i % 2 == 0) { // left -> right
                c = 0;
                while (c < C) {
                    if (map[r][c] == 'x') {
                        collideAction(r, c);
                        break;
                    }
                    c++;
                }
            } else {
                c = C - 1;
                while (c >= 0) {
                    if (map[r][c] == 'x') {
                        collideAction(r, c);
                        break;
                    }
                    c--;
                }
            }
        }
    }

    // 해당 칸 미네랄 없애고, visited 관리하며 완탐하다 클러스터 만나면 bfs 호출
    // 응답으로 받은 클러스터는, 떨어질건지 여부를 확인해서 맞다면 떨군다.
    private static void collideAction(int r, int c) {
        visited = new int[R+1][C];
        int visitedMark = 1;
        // 미네랄 없애기
        map[r][c] = '.';
        // 탐지해야 하는 클러스터는, 해당 칸과 인접
        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            if (!isValid(nr, nc)) continue;
            if (visited[nr][nc] != 0) continue;
            Cluster cluster = bfs(nr, nc, visitedMark++);
            if (!cluster.willBeFall) continue;
            // fallAmount가 1이상인 경우만 남는다.
            int fallAmount = checkFallAmount(cluster);
            fall(cluster, fallAmount);
            break; // 한번에 한 클러스터가 떨어지는 경우만 나온다.
        }
    }

    // Cluster는 떨어지고 나면 다시 안 쓰므로, belows 업데이트는 생략
    private static void fall(Cluster cluster, int fallAmount) {
        for (Pair oldPos : cluster.minerals) {
            map[oldPos.r][oldPos.c] = '.';
            oldPos.r -= fallAmount;
        }
        for (Pair newPos : cluster.minerals) {
            map[newPos.r][newPos.c] = 'x';
        }
    }

    private static int checkFallAmount(Cluster cluster) {
        int minAmount = R;
        for (Pair s : cluster.belows) {
            int amount = 0;
            int curR = s.r;
            while (true) {
                curR -= 1;
                if (curR == 0) break;
                if (map[curR][s.c] != '.' && visited[curR][s.c] != cluster.visitedMark) break;
                amount++;
            }
            minAmount = Math.min(minAmount, amount);
        }
        return minAmount;
    }

    private static Cluster bfs(int sr, int sc, int visitedMark) {
        Cluster res = new Cluster();
        res.visitedMark = visitedMark;
        List<Pair> minerals = new ArrayList<>();
        List<Pair> belows = new ArrayList<>();
        Queue<Pair> q = new ArrayDeque<>();

        Pair s = new Pair(sr, sc);
        q.offer(s);
        visited[sr][sc] = visitedMark;
        minerals.add(s);

        while (!q.isEmpty()) {
            Pair cur = q.poll();

            boolean isBelow = true;
            for (int d = 0; d < 4; d++) {
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];
                if (!isValid(nr, nc)) continue;
                if (d == 2) isBelow = false;
                if (visited[nr][nc] != 0) continue;
                Pair next = new Pair(nr, nc);
                q.offer(next);
                visited[nr][nc] = visitedMark;
                minerals.add(next);
            }
            if (isBelow) belows.add(cur);
        }
        // 아랫면 중 땅과 닿아있는 칸이 없으면, 떨어진다.
        // (아래가 미네랄인데 visited인 경우는, (조건 체크 순서로 인해)belows에 포함되지 않음
        res.belows = belows;
        res.minerals = minerals;
        for (Pair below : belows) {
            if (below.r != 1) continue;
            // 땅과 닿아있어, 떨어지지 않을 때
            res.willBeFall = false;
            return res;
        }
        // 위에서 설정한대로라면, belows가 없는 경우(다른 클러스터 위에 있는 경우)도 고려해야.
        res.willBeFall = !belows.isEmpty();
        return res;
    }

    private static boolean isValid(int r, int c) {
        return (1 <= r && r <= R) && (0 <= c && c < C)
                && map[r][c] == 'x';
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R+1][C];

        for (int i = R; i > 0; i--) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = line.charAt(j);
            }
        }
        N = Integer.parseInt(br.readLine());
        sticks = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            sticks[i] = Integer.parseInt(st.nextToken());
        }
    }

    static class Cluster {
        List<Pair> minerals;
        List<Pair> belows;
        boolean willBeFall;
        int visitedMark;
    }

    static class Pair {
        int r, c;
        Pair(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
