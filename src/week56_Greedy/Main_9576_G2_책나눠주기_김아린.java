import java.util.*;
import java.io.*;

public class Main {
    static class Book implements Comparable<Book>{
        int left;
        int right;
        
        public Book(int left, int right) {
            this.left = left;
            this.right = right;
        }
        
        @Override
        public int compareTo(Book o) {
          //끝나는 시간이 빠른 순
            if(o.right== this.right) {
                return Integer.compare(this.left, o.left);
            }
            return Integer.compare(this.right, o.right);
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int T = Integer.parseInt(br.readLine());
        for (int testcase = 0; testcase < T; testcase++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            
            PriorityQueue<Book> pq = new PriorityQueue<>();
            
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                
                pq.add(new Book(a, b));
                
            }
            
            boolean[] shelf = new boolean[N+1];
            int cnt = 0;
            
            while(!pq.isEmpty()) {
                Book now = pq.poll();
                for (int i = now.left; i <= now.right; i++) {
                    if(!shelf[i]) {
                        shelf[i] = true;
                        cnt++;
                        break;
                    }
                }
            }
            
            System.out.println(cnt);
        }
    }
}
