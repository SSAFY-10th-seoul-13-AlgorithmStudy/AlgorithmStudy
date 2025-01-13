import java.util.*;
import java.io.*;

public class Main {
    static int[][] map;
    static int R, C, K;
    static int maxR, maxC, ans;
    
    static class Pair implements Comparable<Pair> {
        int num;
        int cnt;
        
        Pair(int num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }
        
        public int compareTo(Pair o) {
            if(this.cnt == o.cnt) {
                return this.num - o.num;
            }
            return this.cnt - o.cnt;
        }
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
            
        R = Integer.parseInt(st.nextToken())-1;
        C = Integer.parseInt(st.nextToken())-1;
        K = Integer.parseInt(st.nextToken());
        
        map = new int[101][101];
        maxR = 3;
        maxC = 3; 
        ans = -1;
        
        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        tryRC(0);

        
        // for (int i = 0; i < 3; i++) {
            // for (int j = 0; j < 6; j++) {
                // System.out.print(map[i][j] + " ");
            // }
            // System.out.println();
        // }
        System.out.println(ans);
    }
    
    public static void tryRC(int cnt) {
        //종료조건
        if(cnt > 100 || map[R][C] == K) {
            ans = cnt > 100 ? -1 : cnt;
            return;
        }
        
        // System.out.println(maxR + " " + maxC);
            
        //R작업
        if(maxR >= maxC) {
            for (int i = 0; i < maxR; i++)
                doR(i);
        } else { //C작업
            for (int i = 0; i < maxC; i++)
                doC(i);
        }
        
        tryRC(cnt+1);
    }
    
    public static void doR(int rIdx) {
        Map<Integer, Integer> m = new HashMap<>();
        
        for (int j = 0; j < maxC; j++) {
            if(map[rIdx][j] == 0) continue;   
            m.put(map[rIdx][j], m.getOrDefault(map[rIdx][j], 0) + 1);
        }
        
        //등장횟수가 큰 순 , 수가 커지는 순
        //바로 map에서 정렬하기...???를 어떻게 하지
        
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        
        for (Map.Entry<Integer, Integer> entry : m.entrySet())
            pq.add(new Pair(entry.getKey(), entry.getValue()));
        
        int idx = 0;
        while(!pq.isEmpty()) {
            Pair p = pq.poll();
            map[rIdx][idx] = p.num;
            map[rIdx][idx+1] = p.cnt;
            
            idx += 2;
        }
        
        maxC = maxC > idx ? maxC : idx;
        
        if(idx < maxC) {
            for (int i = idx; i < maxC; i++) {
                map[rIdx][i] = 0;
            }
        }
    }
    
    public static void doC(int cIdx) {
        Map<Integer, Integer> m = new HashMap<>();
        
        for (int j = 0; j < maxR; j++) {
            if(map[j][cIdx] == 0) continue;   
            m.put(map[j][cIdx], m.getOrDefault(map[j][cIdx], 0) + 1);
        }
        
        //등장횟수가 큰 순 , 수가 커지는 순
        //바로 map에서 정렬하기...???를 어떻게 하지
        
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        
        for (Map.Entry<Integer, Integer> entry : m.entrySet())
            pq.add(new Pair(entry.getKey(), entry.getValue()));
        
        // for (Pair p : pq) {
            // System.out.println(p.num + " " + p.cnt);
        // }
        
        int idx = 0;
        while(!pq.isEmpty()) {
            Pair p = pq.poll();
            map[idx][cIdx] = p.num;
            map[idx+1][cIdx] = p.cnt;
            
            idx += 2;
        }
        
        maxR = maxR > idx ? maxR : idx;
        
        if(idx < maxR) {
            for (int i = idx; i < maxR; i++) {
                map[i][cIdx] = 0;
            }
        }
    }
}
