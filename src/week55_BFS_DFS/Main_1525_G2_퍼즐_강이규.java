package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class Main_1525_G2_퍼즐_강이규 {

    static class BfsNode {
        String mapState;
        int moveCnt;
        int zeroPos;

        public BfsNode(String mapState, int moveCnt, int zeroPos) {
            this.mapState = mapState;
            this.moveCnt = moveCnt;
            this.zeroPos = zeroPos;
        }
    }

    static final int N = 3;
    static final int LAST_BLOCK_NO = 8;
    static String START;
    static final String END = "123456780";

    static Set<String> visited;
    // 방향 : 상 좌 하 우
    static int[] dx = {-3, -1, 3, 1};

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(bfs());
    }

    private static int bfs() {
        Queue<BfsNode> q = new ArrayDeque<>();
        q.offer(new BfsNode(START, 0, START.indexOf('0')));

        while (!q.isEmpty()) {
            BfsNode cur = q.poll();

            for (int d = 0; d < 4; d++) {
                if (!inRange(cur.zeroPos, d)) continue;
                String newMapState = getStateAfterMove(cur, dx[d]);
                if (visited.contains(newMapState)) continue;
                // 완성했다면
                if (newMapState.equals(END)) return cur.moveCnt + 1;
                // visited
                visited.add(newMapState);
                q.offer(new BfsNode(newMapState, cur.moveCnt + 1, cur.zeroPos + dx[d]));
            }
        }
        return -1;
    }

    private static boolean inRange(int curZeroPos, int d) {
        if (d % 2 == 0) { // 상하 이동 체크
            int moveAmount = dx[d];
            int newPos = curZeroPos + moveAmount;
            return 0 <= newPos && newPos <= LAST_BLOCK_NO;
        } else {
            // 좌우 이동 체크
            if (curZeroPos % 3 == 0 && d == 1) return false; // curZeroPos가 0, 3, 6일 때, 좌로 이동 불가
            else if (curZeroPos % 3 == 2 && d == 3) return false; // cur가 2, 5, 8일 때, 우로 이동 불가
            return true;
        }
    }

    private static String getStateAfterMove(BfsNode cur, int moveAmount) {
        char[] mapState = cur.mapState.toCharArray();
        swap(mapState, cur.zeroPos, cur.zeroPos + moveAmount);
        return new String(mapState);
    }

    private static void swap(char[] arr, int idx1, int idx2) {
        char tmp = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = tmp;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        START = "";

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                START += line.charAt(j * 2);
            }
        }

        if (START.equals(END)) {
            System.out.println(0);
            System.exit(0);
        }

        visited = new HashSet<>();
        visited.add(START);
    }
}
