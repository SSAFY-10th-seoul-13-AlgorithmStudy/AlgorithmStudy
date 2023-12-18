package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_2696_G2_중앙값구하기_강이규 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            // init
            PriorityQueue<Integer> maxQ = new PriorityQueue<>(Comparator.reverseOrder());
            PriorityQueue<Integer> minQ = new PriorityQueue<>();

            int M = Integer.parseInt(br.readLine());
            List<Integer> mids = new ArrayList<>(M >> 2 + 1);

            st = new StringTokenizer(br.readLine());
            // 첫 요소
            int start = Integer.parseInt(st.nextToken());
            maxQ.offer(start);
            mids.add(start);

            // 메인 로직
            for (int i = 1; i < M; i++) {
                if (i % 10 == 0) st = new StringTokenizer(br.readLine());
                int cur = Integer.parseInt(st.nextToken());
                if (maxQ.size() > minQ.size()) {
                    if (cur < maxQ.peek()) { // (cur 포함시)maxQ.size - minQ.size == 2가 되는 경우에 걸린다.
                        minQ.offer(maxQ.poll());
                        maxQ.offer(cur);
                    }
                    else minQ.offer(cur);
                } else if (maxQ.size() == minQ.size()) {
                    if (cur > maxQ.peek()) { // 중앙값 오른쪽.size - 왼쪽.size == 2가 되는 경우에 걸린다.
                        minQ.offer(cur);
                        maxQ.offer(minQ.poll());
                    }
                    else maxQ.offer(cur);
                }
                // 홀수 번째인지
                if (i % 2 == 0) mids.add(maxQ.peek());
            }
            // 출력
            int size = mids.size();
            sb.append(size).append("\n");
            for (int i = 1; i <= size; i++) {
                sb.append(mids.get(i-1)).append(i % 10 != 0 ? " " : "\n");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
