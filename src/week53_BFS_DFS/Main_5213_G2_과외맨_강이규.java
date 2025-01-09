package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_5213_G2_과외맨_강이규 {

    static class Tile {
        int left, right, no, r, c;

        public Tile(int left, int right, int no, int r, int c) {
            this.left = left;
            this.right = right;
            this.no = no;
            this.r = r;
            this.c = c;
        }
    }

    static class Node {
        Tile tile;
        Node pre;

        public Node(Tile tile, Node pre) {
            this.tile = tile;
            this.pre = pre;
        }
    }

    static int N;
    static Tile[][] map;
    static boolean[][] visited;
    // 방향 : (0-based) 짝수일 때, 홀수일 때로 나눠서
    // ul, ur, l, r, dl, dr
    static int[] drEven = {-1, 0, 1, 1, 0, -1};
    static int[] dcEven = {-1, -1, -1, 0, 1, 0};
    static int[] drOdd = {-1, 0, 1, 1, 0, -1};
    static int[] dcOdd = {0, -1, 0, 1, 1, 1};
    static int maxTileNo;
    static Node resNode;
    static int LAST_TILE_NO;

    public static void main(String[] args) throws IOException {
        init();
        bfs();
        printResult();
    }

    private static void printResult() {
        StringBuilder sb = new StringBuilder();
        Stack<Integer> tileNumbers = new Stack<>();
        Node cur = resNode;

        while (cur != null) {
            tileNumbers.push(cur.tile.no);
            cur = cur.pre;
        }
        int size = tileNumbers.size();
        sb.append(size).append("\n");
        for (int i = 0; i < size; i++) {
            sb.append(tileNumbers.pop()).append(" ");
        }
        System.out.println(sb);
    }

    private static void bfs() {
        Queue<Node> q = new ArrayDeque<>();
        q.offer(new Node(map[0][0], null));

        while (!q.isEmpty()) {
            Node cur = q.poll();
            Tile tile = cur.tile;

            if (tile.no > maxTileNo) {
                resNode = cur;
                maxTileNo = tile.no;
                if (tile.no == LAST_TILE_NO) {
                    return;
                }
            }
            int[] dr = drEven;
            int[] dc = dcEven;
            if (tile.r % 2 != 0) {
                dr = drOdd;
                dc = dcOdd;
            }

            for (int d = 0; d < 6; d++) {
                int nr = tile.r + dr[d];
                int nc = tile.c + dc[d];
                if (!inRange(nr, nc)) continue;
                if (visited[nr][nc]) continue;
                // 값 비교
                Tile nextTile = map[nr][nc];
                if ((d < 3 && tile.left != nextTile.right) || d >= 3 && tile.right != nextTile.left) {
                    continue;
                }
                // 방문
                visited[nr][nc] = true;
                q.offer(new Node(nextTile, cur));
            }
        }
    }

    private static boolean inRange(int r, int c) {
        int m = N - (r % 2);
        return (0 <= r && r < N) && (0 <= c && c < m);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new Tile[N][N];
        visited = new boolean[N][N];

        int tileNo = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0, end = N - i % 2; j < end; j++) {
                st = new StringTokenizer(br.readLine());
                int left = Integer.parseInt(st.nextToken());
                int right = Integer.parseInt(st.nextToken());

                map[i][j] = new Tile(left, right, tileNo++, i, j);
            }
        }
        LAST_TILE_NO = tileNo - 1;
    }
}
