package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14712_G5_넴모넴모_강이규 {

    static int N, M;
    static boolean[][] map;
    static int res = 0;

    public static void main(String[] args) throws IOException {
        init();
        dfs(0);
        System.out.println(res);
    }

    private static void dfs(int depth) {
        if (depth >= N*M) {
            res++; // 중간(ex. n번째)에 카운트하던 경우는, "끝까지 방문했고 n번째 이후 칸을 모두 선택 안한 경우" 에 포함되므로.
            return;
        }

        int r = depth / M + 1;
        int c = depth % M + 1;
        if (!(map[r-1][c-1] && map[r-1][c] && map[r][c-1])) { // 네모 놓을수 있는 경우
            map[r][c] = true;
            dfs(depth+1);
        }
        map[r][c] = false;
        dfs(depth+1);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new boolean[N+1][M+1];
    }
}
