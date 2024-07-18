import java.io.*;
import java.util.*;

public class Main {
    static int N, M, H, W, SR, SC, FR, FC;
    static int[][] map;
    static Queue<int[]> q;
    static Set<int[]> wall;
    static int[][] dir = { {-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static boolean[][] visited;
    static int ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];
        wall = new HashSet<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) {
                    wall.add(new int[]{i, j});
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        SR = Integer.parseInt(st.nextToken()) - 1;
        SC = Integer.parseInt(st.nextToken()) - 1;
        FR = Integer.parseInt(st.nextToken()) - 1;
        FC = Integer.parseInt(st.nextToken()) - 1;

        queue = new ArrayDeque<>();
        queue.add(new int[] { SR, SC, 0 });
        visited[SR][SC] = true;

        ans = -1;
        search();
        System.out.println(ans);
    }

    private static void search() {
        while (!q.isEmpty()) {
            int[] p = q.poll();
            int x = p[0], y = p[1], cnt = p[2];

            if (x == FR && y == FC) {
                ans = cnt;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dir[0][i];
                int ny = y + dir[1][i];

                if (nx < 0 || ny < 0 || nx + H - 1 >= N || ny + W - 1 >= M || visited[nx][ny] || isWall(nx, ny))
                    continue;

                queue.add(new int[] { nx, ny, cnt + 1 });
                visited[nr][nc] = true;
            }
        }
    }

    private static boolean isWall(int x, int y) {
    for (int[] w : wall) {
        //이 조건이 나는 제일 힘들었음..
        if (w[0] >= x && w[0] < x + H && w[1] >= y && w[1] < y + W) {
            return true;
        }
    }
    return false;
}

}
