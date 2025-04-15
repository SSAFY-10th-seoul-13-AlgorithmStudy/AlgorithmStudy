import java.util.*;
import java.io.*;
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int N, M, p[];
    static List<Integer>[] parties;
    
    static void init(){
        p = new int[N + 1];
        for(int i = 1; i <= N; i++){
            p[i] = i;
        }
        parties = new List[M];
        for(int i = 0; i < M; i++){
            parties[i] = new ArrayList<>();
        }
    }
    
    static void input_solve() throws IOException{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        init();
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        for(int i = 0; i < n; i++){
            union(0, Integer.parseInt(st.nextToken()));
        }
        
        for(int i = 0; i < M; i++){            
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            int cur = 0;
            if(n != 0) {
                cur = Integer.parseInt(st.nextToken());
                parties[i].add(cur);
            }
            for(int j = 1; j < n; j++){
                int next = Integer.parseInt(st.nextToken());
                union(cur, next);
                cur = next;
                parties[i].add(cur);
            }
        }
        
        int cnt = 0;
        for(int i = 0; i < M; i++){
            boolean flag = false;
            for(int j : parties[i]){
                if(find(j) == 0){
                    flag = true;
                    break;
                }
            }
            if(!flag) cnt++;
        }
        sb.append(cnt);
    }    
    
    static int find(int a){
        if(p[a] == a) return a;
        return p[a] = find(p[a]);
    }
    
    static boolean union(int a, int b){
        int pa = find(a);
        int pb = find(b);
        if(pa == pb) return false;
        if(pa < pb) p[pb] = pa;
        else p[pa] = pb;
        return true;
    }
    public static void main(String[] args) throws IOException {
        input_solve();
        
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
