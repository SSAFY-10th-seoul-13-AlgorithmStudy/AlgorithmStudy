package BOJ;

import java.io.*;
import java.util.*;

public class Main_17837_G2_새로운게임2_강이규 {
    static class Cell {
        int r, c;
        Stack<Mal> stack;
        int color;

        Cell(int r, int c, Stack<Mal> stack, int color) {
            this.r = r;
            this.c = c;
            this.stack = stack;
            this.color = color;
        }
    }

    static class Mal {
        int no, r, c, d;
        Mal(int no, int r, int c, int d) {
            this.no = no;
            this.r = r;
            this.c = c;
            this.d = d;
        }

        void reverseDirection() {
            if (d < 2) d = (d + 1) % 2;
            else d = (d + 1) % 2 + 2;
        }
    }
    static int N, K;
    static Cell[][] map;
    static Mal[] mals;
    static Stack<Mal> tmpStack;
    // 방향 : 우 좌 상 하
    static int[] dr = {0, 0, -1, 1};
    static int[] dc = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void solve() {
        int turn = 0;
        Game: while (++turn <= 1000) {
            for (int i = 0; i < K; i++) {
                Mal mal = mals[i];
                Cell cur = map[mal.r][mal.c];
                Cell next = map[mal.r+dr[mal.d]][mal.c+dc[mal.d]];
                if (next.color == 1) {
                    if (!moveToRed(cur, next, mal.no)) break Game;
                    continue;
                }
                if (next.color == 0) {
                    if (!moveToWhite(cur, next, mal.no)) break Game;
                    continue;
                }
                // next.color == blue
                mal.reverseDirection();
                // new next
                next = map[mal.r+dr[mal.d]][mal.c+dc[mal.d]];

                if (next.color == 1) {
                    if (!moveToRed(cur, next, mal.no)) break Game;
                    continue;
                }
                if (next.color == 0) {
                    if (!moveToWhite(cur, next, mal.no)) break Game;
                    continue;
                }
            }
        }
        System.out.println(turn <= 1000 ? turn : -1);
    }

    private static boolean moveToRed(Cell from, Cell to, int malNo) {
        while (true) {
            Mal pop = from.stack.pop();
            pop.r = to.r;
            pop.c = to.c;
            to.stack.push(pop);
            if (to.stack.size() >= 4) return false;
            if (pop.no == malNo) break;
        }
        return true;
    }

    private static boolean moveToWhite(Cell from, Cell to, int malNo) {
        while (true) {
            Mal pop = from.stack.pop();
            tmpStack.push(pop);
            if (pop.no == malNo) break;
        }
        while (!tmpStack.isEmpty()) {
            Mal pop = tmpStack.pop();
            pop.r = to.r;
            pop.c = to.c;
            to.stack.push(pop);
            if (to.stack.size() >= 4) return false;
        }
        return true;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new Cell[N+2][N+2];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = new Cell(i, j, new Stack<>(), Integer.parseInt(st.nextToken()));
            }
        }
        // 테두리 파란색 칸으로 채우기
        for (int i = 0, end = N + 1; i <= end; i++) {
            map[0][i] = new Cell(0, i, null, 2); // 말이 테두리 칸으로 이동하는 경우는 없다.
            map[N+1][i] = new Cell(N+1, i, null, 2);
            map[i][0] = new Cell(i, 0, null, 2);
            map[i][N+1] = new Cell(i, N+1, null, 2);
        }

        mals = new Mal[K];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken()) - 1;

            mals[i] = new Mal(i, r, c, d);
            map[r][c].stack.push(mals[i]);
        }

        tmpStack = new Stack<>();
    }
}
