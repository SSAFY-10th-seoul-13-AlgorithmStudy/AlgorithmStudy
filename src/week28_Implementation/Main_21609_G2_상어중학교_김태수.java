package week28_Implementation;

import java.io.*;
import java.util.*;

public class Main_21609_G2_상어중학교_김태수 {
    static int n, m, score = 0;
    static int[][] block;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        block = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                block[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (true) {
            visited = new boolean[n][n];
            List<int[]> groups = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (block[i][j] >= 1 && !visited[i][j]) {
                        visited[i][j] = true;
                        int[] group = findBlock(i, j, block[i][j]);

                        if (group[0] >= 2) {
                            groups.add(group);
                        }
                    }
                }
            }

            if (groups.isEmpty()) break;

            groups.sort((a, b) -> {
                if (a[0] != b[0]) return b[0] - a[0];
                if (a[1] != b[1]) return b[1] - a[1];
                if (a[2] != b[2]) return b[2] - a[2];
                return b[3] - a[3];
            });

            removeBlock(groups.get(0));
            gravity();
            rotateBlock();
            gravity();
        }

        System.out.println(score);
    }

    public static int[] findBlock(int i, int j, int blockNum) {
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.add(new int[]{i, j});

        List<int[]> normals = new ArrayList<>();
        List<int[]> rainbows = new ArrayList<>();
        normals.add(new int[]{i, j});

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0], y = cur[1];

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (nx >= 0 && ny >= 0 && nx < n && ny < n) {
                    if (block[nx][ny] == 0 && !visited[nx][ny]) {
                        q.add(new int[]{nx, ny});
                        rainbows.add(new int[]{nx, ny});
                        visited[nx][ny] = true;
                    } else if (block[nx][ny] == blockNum && !visited[nx][ny]) {
                        q.add(new int[]{nx, ny});
                        normals.add(new int[]{nx, ny});
                        visited[nx][ny] = true;
                    }
                }
            }
        }

        for (int[] rb : rainbows) {
            visited[rb[0]][rb[1]] = false;
        }

        normals.addAll(rainbows);
        int[] result = new int[normals.size() * 2 + 2];
        result[0] = normals.size();
        result[1] = rainbows.size();
        for (int k = 0; k < normals.size(); k++) {
            result[2 + k * 2] = normals.get(k)[0];
            result[3 + k * 2] = normals.get(k)[1];
        }

        return result;
    }

    public static void removeBlock(int[] group) {
        score += group[0] * group[0];

        for (int i = 2; i < group.length; i += 2) {
            int x = group[i], y = group[i + 1];
            block[x][y] = -2;
        }
    }

    public static void gravity() {
        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j < n; j++) {
                if (block[i][j] != -1) {
                    int pointer = i;

                    while (pointer + 1 < n && block[pointer + 1][j] == -2) {
                        block[pointer + 1][j] = block[pointer][j];
                        block[pointer][j] = -2;
                        pointer++;
                    }
                }
            }
        }
    }

    public static void rotateBlock() {
        int[][] tmp = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                tmp[n - j - 1][i] = block[i][j];
            }
        }

        block = tmp;
    }
}
