package BOJ;

import javax.swing.tree.TreePath;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_12764_G3_싸지방에간준하_강이규 {

    static int N;
    static int[][] input;
    static PriorityQueue<SeatInfo> pq;
    static PriorityQueue<Integer> emptySeats;
    static List<Integer> cnts;

    public static void main(String[] args) throws IOException {
        init();
        solve();
        print();
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        input = new int[N][2];
        pq = new PriorityQueue<>();
        emptySeats = new PriorityQueue<>();
        cnts = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int sTime = Integer.parseInt(st.nextToken());
            int eTime = Integer.parseInt(st.nextToken());

            input[i][0] = sTime;
            input[i][1] = eTime;
        }
        Arrays.sort(input, Comparator.comparingInt(a1 -> a1[0]));
    }

    private static void solve() {
        // 첫 요소
        pq.offer(new SeatInfo(0, input[0][1]));
        cnts.add(1);

        for (int i = 1; i < N; i++) {
            int[] cur = input[i];

            while (!pq.isEmpty() && pq.peek().eTime <= cur[0]) {
                emptySeats.offer(pq.poll().no);
            }
            if (!emptySeats.isEmpty()) {
                int no = emptySeats.poll();
                pq.offer(new SeatInfo(no, cur[1]));
                cnts.set(no, cnts.get(no)+1);
            } else {
                pq.offer(new SeatInfo(cnts.size(), cur[1]));
                cnts.add(1);
            }
        }
    }

    private static void print() {
        StringBuilder sb = new StringBuilder();
        sb.append(cnts.size()).append("\n");
        for (int i : cnts) {
            sb.append(i).append(" ");
        }
        System.out.println(sb);
    }

    static class SeatInfo implements Comparable<SeatInfo> {
        int no, eTime;
        SeatInfo(int no, int eTime) {
            this.no = no;
            this.eTime = eTime;
        }

        @Override
        public int compareTo(SeatInfo o) {
            return eTime - o.eTime;
        }
    }
}
