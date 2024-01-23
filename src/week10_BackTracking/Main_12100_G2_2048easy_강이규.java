package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_12100_2048easy_강이규 {

    static int N;
    static int max = 0;
    public static int[] dx = {-1, 0, 1, 0};
    public static int[] dy = {0, 1, 0, -1};
    static int[][] map;

    public static void main(String[] args) throws IOException {
        init();
        bfs();
        System.out.println(max);
    }

    public static void move(int[][] map, int d){
        boolean[][] visited = new boolean[N][N];

        int s = 0, e = 0;
        if(d == 0 || d == 3) {
            e = N;
            for(int j=s;j<e;j++) {
                for(int i=s;i<e;i++) {
                    if(map[i][j] == 0) continue;
                    int x = i;
                    int y = j;
                    int temp = map[x][y];
                    map[x][y] = 0;
                    int nx = x + dx[d];
                    int ny = y + dy[d];

                    while(true) {
                        if(!inRange(nx, ny)) {
                            break;
                        }

                        if(map[nx][ny] == 0) {
                            x = nx;
                            y = ny;
                            nx = x + dx[d];
                            ny = y + dy[d];
                        } else if(!visited[nx][ny] && map[nx][ny] == temp) {
                            x = nx;
                            y = ny;
                            visited[nx][ny] = true;
                            break;
                        } else {
                            break;
                        }
                    }
                    map[x][y] += temp;
                }
            }
        } else {
            s = N-1;
            e = -1;

            for(int i=s;i>e;i--) {
                for(int j=s;j>e;j--) {
                    if(map[i][j] == 0) continue;
                    int x = i;
                    int y = j;
                    int temp = map[x][y];
                    map[x][y] = 0;
                    int nx = x + dx[d];
                    int ny = y + dy[d];

                    while(true) {
                        if(!inRange(nx, ny)) {
                            break;
                        }

                        if(map[nx][ny] == 0) {
                            x = nx;
                            y = ny;
                            nx = x + dx[d];
                            ny = y + dy[d];

                        } else if(!visited[nx][ny] && map[nx][ny] == temp) {
                            x = nx;
                            y = ny;
                            visited[nx][ny] = true;
                            break;
                        } else {
                            break;
                        }
                    }
                    map[x][y] += temp;
                }
            }
        }



    }

    private static boolean inRange(int x, int y) {
        return (0 <= x && x < N) && (0 <= y && y < N);
    }

    private static void bfs() {
        Queue<int[][]> q = new ArrayDeque<>();
        q.offer(map);
        int cnt = 0;
        while (!q.isEmpty()) {
            for (int l = 0, size = q.size(); l < size; l++) {
                int[][] map = q.poll();

                for (int d = 0; d < 4; d++) {
                    int[][] copyMap = new int[N][N];
                    for (int i = 0; i < N; i++) {
                        for (int j = 0; j < N; j++) {
                            copyMap[i][j] = map[i][j];
                        }
                    }
                    move(copyMap, d);
                    q.offer(copyMap);
                    for (int i = 0; i < N; i++) {
                        for (int j = 0; j < N; j++) {
                            max = Math.max(copyMap[i][j], max);
                        }
                    }
                }
            }
            cnt++;
            if (cnt >= 5) return;
        }
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
