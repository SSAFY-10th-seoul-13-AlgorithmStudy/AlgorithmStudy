package week17_BFS_DFS;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class Solution_81302_Level2_거리두기확인하기_강이규 {

    static char[][] map;
    static boolean[][] visited;
    static final int N = 5;
    static Set<Pos> people;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public int[] solution(String[][] places) {
        int[] result = new int[N];

        Room: for (int rIdx = 0; rIdx < N; rIdx++) {
            String[] room = places[rIdx];
            map = new char[N][N];
            // init
            people = new HashSet<>();
            for (int i = 0; i < N; i++) {
                String row = room[i];
                for (int j = 0; j < N; j++) {
                    map[i][j] = row.charAt(j);
                    if (map[i][j] == 'P') {
                        people.add(new Pos(i, j));
                    }
                }
            }
            // main logic
            for (Pos person : people) {
                visited = new boolean[N][N];
                if (!bfs(person)) {
                    result[rIdx] = 0;
                    continue Room;
                }
            }
            result[rIdx] = 1;
        }
        return result;
    }

    private static boolean bfs(Pos start) {
        Queue<Pos> q = new ArrayDeque<>();
        q.offer(start);
        visited[start.x][start.y] = true;

        int maxD = 3;
        while (!q.isEmpty() && maxD-- > 0) {
            int qSize = q.size();
            for (int i = 0; i < qSize; i++) {
                Pos cur = q.poll();

                for (int d = 0; d < 4; d++) {
                    int nx = cur.x + dx[d];
                    int ny = cur.y + dy[d];
                    if (!isValid(nx, ny)) continue;
                    if (map[nx][ny] == 'P') {
                        return false;
                    }
                    q.offer(new Pos(nx, ny));
                    visited[nx][ny] = true;
                }
            }
        }
        return true;
    }

    private static boolean isValid(int x, int y) {
        return (0 <= x && x < 5) &&
                (0 <= y && y < 5) &&
                !visited[x][y] &&
                map[x][y] != 'X';
    }

    static class Pos {
        int x, y;
        Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
