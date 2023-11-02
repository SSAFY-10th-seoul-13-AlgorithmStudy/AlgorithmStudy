import java.io.*;
import java.util.*;
 
public class Main_1766_G2_문제집_김아린 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
 
        int[] indegree = new int[N + 1];
        List<Integer>[] adjList = new ArrayList[N+1];

 
        for (int i = 0; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }
 
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(in.readLine());
            int first = Integer.parseInt(st.nextToken());
            int last = Integer.parseInt(st.nextToken());
            adjList[first].add(last);
            indegree[last]++;
        }
 
        PriorityQueue<Integer> pq = new PriorityQueue<>();
 
        for (int i = 1; i <= N; i++) {
            if (indegree[i] == 0) { // 먼저 풀어야하는 문제가 없는 경우
                pq.offer(i);
            }
        }
 
        while (!pq.isEmpty()) {
            int now = pq.poll();
            sb.append(now + " ");
            
            for (int next : adjList[now]) {
                indegree[next]--;
                if (indegree[next] == 0) {
                    pq.offer(next);
                }
            }
        }
        
        System.out.println(sb);
    }
}