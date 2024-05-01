import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M, R;
    static int[][] map;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); //세로
        M = Integer.parseInt(st.nextToken()); //가로
        R = Integer.parseInt(st.nextToken()); //회전

        map = new int[N][M];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        // 각 단계에 대한 최대 높이..
        int min = Math.min(N, M)/2;

        // 각 단계에 대해 몇 번 회전을 해야 하는지 계산
        // 전체 테두리의 길이 -4는 네 꼭지점 빼기
        // 나머지를 cnt로 사용하는 이유는 테두리 길이 이상으로 회전시키면 초기 위치로 돌아오니까
        for(int i = 0; i < min; i++) {
            int cnt = R % (((N - 2 * i) + (M - 2 * i)) * 2 - 4);
            rotate(i, cnt);
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

    static void rotate(int idx, int cnt) {
        for(int r = 0; r < cnt; r++) {

            int x = idx;
            int y = idx;
            int tmp = map[y][x];

            int dir = 0;

            while (dir < 4) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];

                if (nx < idx || ny < idx || nx >= M - idx || ny >= N - idx) {
                    dir++;
                    continue;
                }

                map[y][x] = map[ny][nx];
                y = ny;
                x = nx;
            }
            map[idx + 1][idx] = tmp;
        }
    }
}
