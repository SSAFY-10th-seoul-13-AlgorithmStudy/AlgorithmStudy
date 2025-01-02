import java.util.*;
import java.io.*;

public class Main {
    static class Work implements Comparable<Work> {
        int time;
        int money;
        int idx;
        
        public Work(int time, int money, int idx) {
            this.time = time;
            this.money = money;
            this.idx = idx;
        }
        
        @Override
        public int compareTo(Work o) {
            if(o.money*time == o.time*money)
                return Integer.compare(idx, o.idx);
            return o.money*time - o.time*money;
        }
        
    }
    static StringTokenizer st;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //돈이 싸고 시간이 짧은 순
        //내돈*너시간해서 크면 돈쪽이 앞쪽 (같으면 index 순으로 정렬)
        
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Work> pq = new PriorityQueue<>();
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            pq.add(new Work(a, b, i+1));
            
        }
        
        while(!pq.isEmpty()) {
            Work o = pq.poll();
            // System.out.println(o.idx + " " + o.time + " " + o.money);
            System.out.print(o.idx + " ");
        }
        
    }
}
