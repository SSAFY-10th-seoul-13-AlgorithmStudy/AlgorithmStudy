import java.util.*;
import java.io.*;

public class Main {
    static class Node {
        int idx;
        int order;
        int imax;
        
        public Node(int idx, int order, int imax) {
            this.idx = idx;
            this.order = order;
            this.imax = imax;
        }
    }
    
    static int K, M, P; 
    static ArrayList<Integer>[] list;
    static int[] degree;
    static int[] strahler;
    static int[] countMax;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int T = Integer.parseInt(br.readLine());
        
        for (int testcase = 1; testcase <= T; testcase++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            K = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            P = Integer.parseInt(st.nextToken());
            
            list = new ArrayList[M+1];
            for (int i = 0; i <= M; i++) {
                list[i] = new ArrayList<>();
            }
            
            degree    = new int[M+1];
            strahler  = new int[M+1];
            countMax  = new int[M+1];
            
            for (int i = 0; i < P; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                list[a].add(b);
                degree[b]++;
            }
            
            findH();
            
            System.out.println(K + " " + strahler[M]);
        }
    }


    public static void findH() {
        Queue<Integer> q = new ArrayDeque<>();
        
        for (int i = 1; i <= M; i++) {
            if (degree[i] == 0) {
                strahler[i] = 1;
                countMax[i] = 1;
                q.offer(i);
            }
        }
        
        while (!q.isEmpty()) {
            int curr = q.poll();
            
            for (int next : list[curr]) {
                int currOrder = strahler[curr];
                
                if (strahler[next] < currOrder) {
                    strahler[next] = currOrder;
                    countMax[next] = 1;
                }
                else if (strahler[next] == currOrder) {
                    countMax[next]++;
                }
                
                degree[next]--;
                
                if (degree[next] == 0) {
                    if (countMax[next] >= 2) {
                        strahler[next]++;
                    }
                    q.offer(next);
                }
            }
        }
    }
}
