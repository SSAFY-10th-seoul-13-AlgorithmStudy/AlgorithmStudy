package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_17140_G4_이차원배열과연산_강이규 {

    static class Number implements Comparable<Number> {
        int v;
        int cnt;

        public Number(int v, int cnt) {
            this.v = v;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Number o) {
            return this.cnt != o.cnt ? Integer.compare(this.cnt, o.cnt) : Integer.compare(this.v, o.v);
        }
    }

    static int N, M;
    static int R, C, K;
    static List<List<Integer>> map;

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void solve() {
        int time = 0;
        while (time <= 100) {
            if (check()) {
                System.out.println(time);
                return;
            }
            if (N >= M) sortRow();
            else sortCol();
            time++;
        }
        System.out.println(-1);
    }

    private static boolean check() {
        if (R >= map.size() || C >= map.get(0).size()) return false;
        return map.get(R).get(C) == K;
    }

    private static void sortRow() {
        List<Number[]> ops = new ArrayList<>(N);

        // 읽기
        for (int r = 0; r < N; r++) {
            List<Integer> row = map.get(r);
            // 등장 횟수를 저장하기 위한 변수들
            int[] cnts = new int[101];
            Set<Integer> vals = new HashSet<>();

            // 값과 등장횟수 기록
            int rowSize = row.size();
            for (int c = 0; c < rowSize; c++) {
                int cur = row.get(c);
                cnts[cur]++;
                vals.add(cur);
            }

            // 정렬된 정보 저장
            ops.add(getSortedInfo(vals, cnts));
        }

        // map에 쓰기
        int maxSize = -1;
        for (Number[] op : ops) {
            maxSize = Math.max(maxSize, op.length);
        }

        // mapSize 업데이트
        M = Math.min(maxSize * 2, 100);

        for (int r = 0; r < N; r++) {
            List<Integer> row = new ArrayList<>();
            Number[] opRow = ops.get(r);
            int curSize = opRow.length;
            for (int c = 0, end = M / 2; c < end; c++) {
                if (c >= curSize) {
                    row.add(0);
                    row.add(0);
                } else {
                    Number cur = opRow[c];
                    row.add(cur.v);
                    row.add(cur.cnt);
                }
            }
            map.set(r, row);
        }
    }

    private static void sortCol() {
        List<Number[]> ops = new ArrayList<>(M);

        for (int c = 0; c < M; c++) {
            // 등장 횟수를 저장하기 위한 변수들
            int[] cnts = new int[101];
            Set<Integer> vals = new HashSet<>();

            // 값과 등장횟수 기록
            for (int r = 0; r < N; r++) {
                int cur = map.get(r).get(c);
                cnts[cur]++;
                vals.add(cur);
            }

            // 정렬된 정보 저장
            ops.add(getSortedInfo(vals, cnts));
        }

        // map에 쓰기
        int maxSize = -1;
        for (Number[] op : ops) {
            maxSize = Math.max(maxSize, op.length);
        }

        N = Math.min(maxSize * 2, 100);

        map = new ArrayList<>(N);
        for (int i = 0; i < N; i++) {
            map.add(new ArrayList<>(M));
        }

        for (int c = 0; c < M; c++) {
            Number[] opCol = ops.get(c);
            int curSize = opCol.length;
            for (int r = 0; r < N; r += 2) {
                int opIdx = r / 2;
                if (opIdx >= curSize) {
                    map.get(r).add(0);
                    map.get(r+1).add(0);
                } else {
                    Number cur = opCol[opIdx];
                    map.get(r).add(cur.v);
                    map.get(r+1).add(cur.cnt);
                }
            }
        }
    }

    private static Number[] getSortedInfo(Set<Integer> vals, int[] cnts) {
        // 정렬된 정보 저장
        List<Number> numbers = new ArrayList<>();
        for (int val : vals) {
            if (val == 0) continue;
            numbers.add(new Number(val, cnts[val]));
        }
        if (!numbers.isEmpty()) Collections.sort(numbers);
        return numbers.toArray(new Number[]{});
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = M = 3;

        R = Integer.parseInt(st.nextToken()) - 1;
        C = Integer.parseInt(st.nextToken()) - 1;
        K = Integer.parseInt(st.nextToken());

        map = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            List<Integer> row = new ArrayList<>();
            map.add(row);

            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                row.add(Integer.parseInt(st.nextToken()));
            }
        }

    }
}
