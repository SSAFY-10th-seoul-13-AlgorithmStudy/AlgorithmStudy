package week09_TopologySort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//https://blog.naver.com/ds4ouj/222561060594

public class Main_20119_G1_클레어와물약_김아린 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] count = new int[M];
        int[] targetNum = new int[M];
        List<Integer>[] list = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
        }

        Stack<Integer> stack = new Stack<>();
        Set<Integer> hs = new HashSet<>();

        for (int idx = 0; idx < M; idx++) {
            st = new StringTokenizer(br.readLine());
            count[idx] = Integer.parseInt(st.nextToken());
            for (int i = 0; i < count[idx]; i++) {
                list[Integer.parseInt(st.nextToken())].add(idx);
            }
            targetNum[idx] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int size = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < size; i++) {
            stack.add(Integer.parseInt(st.nextToken()));
            hs.add(stack.peek());
        }

        while (!stack.isEmpty()) {
            int now = stack.pop();
            for (int next : list[now]) {
                if (--count[next] == 0 && !hs.contains(targetNum[next])) {
                    hs.add(targetNum[next]);
                    stack.add(targetNum[next]);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.addAll(hs);
        size = pq.size();
        while (!pq.isEmpty()) {
            sb.append(pq.poll()).append(" ");
        }

        System.out.println(size + "\n" + sb.toString());
    }
}