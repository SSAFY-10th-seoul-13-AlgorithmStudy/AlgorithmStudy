package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main_20061_G2_모노미노도미노2_강이규 {

    static class Pos {
        int r, c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static class Block {
        List<Pos> body; // put할 때 범위 초과 안 되도록 보장
        List<Pos> headOfGreen;
        List<Pos> headOfBlue;

        public Block(List<Pos> body, List<Pos> headOfGreen, List<Pos> headOfBlue) {
            this.body = body;
            this.headOfGreen = headOfGreen;
            this.headOfBlue = headOfBlue;
        }
    }

    static class GreenBoard {
        boolean[][] visited; // r = 6, c = 4

        GreenBoard() {
            this.visited = new boolean[6][4];
        }

        int put(int bIdx, int sc) {
            int score = 0;
            Block block = blocks[bIdx];

            // 0행부터 시작해서, 이동량 계산
            int minFallAmount = 6;
            for (Pos head : block.headOfGreen) {
                int fallAmount = getFallAmount(head.r, sc + head.c);
                minFallAmount = Math.min(minFallAmount, fallAmount);
            }
            // 이동, 표시
            for (Pos p : block.body) {
                int r = p.r + minFallAmount;
                int c = p.c + sc;
                visited[r][c] = true;
            }
            // 점수 처리
            Row: for (int r = 0; r <= 5; r++) {
                Column: for (int c = 0; c < 4; c++) {
                    if (!visited[r][c]) continue Row;
                }
                // 점수 났으면 처리(지우고, 위에 한줄 내리고, 점수++)
                erase(r);
                fallUpper(r);
                score++;
            }
            // 아래로 밀어야 하면 처리
            int pushAmount = 0;
            for (int r = 0; r <= 1; r++) {
                boolean empty = true;
                for (int c = 0; c < 4; c++) {
                    if (visited[r][c]) {
                        empty = false;
                        break;
                    }
                }
                if (!empty) pushAmount++;
            }
            for (int i = 0; i < pushAmount; i++) {
                push();
            }

            return score;
        }

        private int getFallAmount(int sr, int c) {
            int fallAmount = 0;
            for (int r = sr + 1; r <= 5; r++) {
                if (visited[r][c]) break;
                fallAmount++;
            }
            return fallAmount;
        }

        private void push() {
            for (int r = 5; r >= 1; r--) {
                // 아래로 한 행 내리기
                for (int c = 0; c < 4; c++) {
                    visited[r][c] = visited[r-1][c];
                }
            }
            // 맨 위에 새 행
            Arrays.fill(visited[0], false);
        }

        private void erase(int r) {
            Arrays.fill(visited[r], false);
        }

        private void fallUpper(int sr) {
            for (int r = sr; r >= 1; r--) {
                for (int c = 0; c < 4; c++) {
                    visited[r][c] = visited[r-1][c];
                }
            }
            Arrays.fill(visited[0], false);
        }

        int getRemainCount() {
            int cnt = 0;
            for (boolean[] row : visited) {
                for (boolean filled : row) {
                    if (filled) cnt++;
                }
            }
            return cnt;
        }
    }

    static class BlueBoard {
        boolean[][] visited; // r = 4, c = 6;

        BlueBoard() {
            this.visited = new boolean[4][6];
        }

        int put(int bIdx, int sr) {
            int score = 0;
            Block block = blocks[bIdx];

            // 0열부터 시작해서, 이동량 계산
            int minFallAmount = 6;
            for (Pos head : block.headOfBlue) {
                int fallAmount = getFallAmount(sr + head.r, head.c);
                minFallAmount = Math.min(minFallAmount, fallAmount);
            }
            // 이동, 표시
            for (Pos p : block.body) {
                int r = p.r + sr;
                int c = p.c + minFallAmount;
                visited[r][c] = true;
            }
            // 점수 처리
            Column: for (int c = 0; c <= 5; c++) {
                Row: for (int r = 0; r < 4; r++) {
                    if (!visited[r][c]) continue Column;
                }
                // 점수 났으면 처리(지우고, 위에 한줄 내리고, 점수++)
                erase(c);
                fallUpper(c);
                score++;
            }
            // 아래로 밀어야 하면 처리
            int pushAmount = 0;
            for (int c = 0; c <= 1; c++) {
                boolean empty = true;
                for (int r = 0; r < 4; r++) {
                    if (visited[r][c]) {
                        empty = false;
                        break;
                    }
                }
                if (!empty) pushAmount++;
            }
            for (int i = 0; i < pushAmount; i++) {
                push();
            }

            return score;
        }

        private int getFallAmount(int r, int sc) {
            int fallAmount = 0;
            for (int c = sc + 1; c <= 5; c++) {
                if (visited[r][c]) break;
                fallAmount++;
            }
            return fallAmount;
        }

        private void push() {
            for (int c = 5; c >= 1; c--) {
                // 아래로 한 열 내리기
                for (int r = 0; r < 4; r++) {
                    visited[r][c] = visited[r][c-1];
                }
            }
            // 맨 위에 새 열
            for (int r = 0; r < 4; r++) {
                visited[r][0] = false;
            }
        }

        private void erase(int c) {
            for (int r = 0; r < 4; r++) {
                visited[r][c] = false;
            }
        }

        private void fallUpper(int sc) {
            for (int c = sc; c >= 1; c--) {
                for (int r = 0; r < 4; r++) {
                    visited[r][c] = visited[r][c-1];
                }
            }
            for (int r = 0; r < 4; r++) {
                visited[r][0] = false;
            }
        }

        int getRemainCount() {
            int cnt = 0;
            for (boolean[] row : visited) {
                for (boolean filled : row) {
                    if (filled) cnt++;
                }
            }
            return cnt;
        }
    }

    static int N;
    static Block[] blocks; // 1-based
    static GreenBoard greenBoard;
    static BlueBoard blueBoard;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        initBlocks();
        int score = 0;
        int remainCnt = 0;
        greenBoard = new GreenBoard();
        blueBoard = new BlueBoard();

//        int cnt = 1;
        while(N-- > 0) {
            st = new StringTokenizer(br.readLine());
            int bIdx = Integer.parseInt(st.nextToken());
            int sr = Integer.parseInt(st.nextToken());
            int sc = Integer.parseInt(st.nextToken());

            score += greenBoard.put(bIdx, sc);
            score += blueBoard.put(bIdx, sr);
//            System.out.println(cnt++);
        }
        // 남은 칸 개수 출력
        remainCnt += greenBoard.getRemainCount();
        remainCnt += blueBoard.getRemainCount();

        System.out.println(score + "\n" + remainCnt);
    }

    private static void initBlocks() {
        blocks = new Block[4];
        // 블록 1
        Pos p1 = new Pos(0, 0);
        List<Pos> b1 = new ArrayList<>(1);
        b1.add(p1);
        List<Pos> hg1 = new ArrayList<>(1);
        hg1.add(p1);
        List<Pos> hb1 = new ArrayList<>(1);
        hb1.add(p1);
        blocks[1] = new Block(b1, hg1, hb1);

        // 블록 2
        Pos p2 = new Pos(0, 1);
        List<Pos> b2 = new ArrayList<>(2);
        b2.add(p1);
        b2.add(p2);
        List<Pos> hg2 = new ArrayList<>(2);
        hg2.add(p1);
        hg2.add(p2);
        List<Pos> hb2 = new ArrayList<>(1);
        hb2.add(p2);
        blocks[2] = new Block(b2, hg2, hb2);

        // 블록 2
        Pos p3 = new Pos(1, 0);
        List<Pos> b3 = new ArrayList<>(2);
        b3.add(p1);
        b3.add(p3);
        List<Pos> hg3 = new ArrayList<>(1);
        hg3.add(p3);
        List<Pos> hb3 = new ArrayList<>(2);
        hb3.add(p1);
        hb3.add(p3);
        blocks[3] = new Block(b3, hg3, hb3);
    }

}
