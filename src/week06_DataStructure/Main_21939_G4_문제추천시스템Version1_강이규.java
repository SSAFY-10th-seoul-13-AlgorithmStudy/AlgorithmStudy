package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main_21939_G4_문제추천시스템Version1_강이규 {

    static int N, M;
    static TreeSet<Problem> ts;
    static HashMap<Integer, Problem> map; // solve 시 문제 번호로 삭제할 문제를 찾기 위함
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        // 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        ts = new TreeSet<>();
        map = new HashMap<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int pNo = Integer.parseInt(st.nextToken());
            int level = Integer.parseInt(st.nextToken());

            Problem p = new Problem(pNo, level);
            ts.add(p);
            map.put(pNo, p);

        }
        M = Integer.parseInt(br.readLine());
        // 실행
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            char op = st.nextToken().charAt(0);

            switch (op) {
                case 'r':
                    boolean ascFlag = st.nextToken().charAt(0) == '-';
                    Problem p = ascFlag ? ts.last() : ts.first();
                    sb.append(p.pNo).append("\n");
                    break;
                case 'a':
                    int pNo = Integer.parseInt(st.nextToken());
                    int level = Integer.parseInt(st.nextToken());

                    Problem p2 = new Problem(pNo, level);
                    ts.add(p2);
                    map.put(pNo, p2);
                    break;
                case 's':
                    int pNum = Integer.parseInt(st.nextToken());
                    ts.remove(map.get(pNum));
                    break;
            }
        }
        System.out.println(sb);
    }

    static class Problem implements Comparable<Problem> {
        int pNo;
        int l;
        Problem(int pNo, int l) {
            this.pNo = pNo;
            this.l = l;
        }

        @Override
        public int compareTo(Problem o) {
            if (o.l != this.l) return o.l - this.l;
            return o.pNo - this.pNo;
        }
    }
}
