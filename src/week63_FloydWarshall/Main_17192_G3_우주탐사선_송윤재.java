import java.util.*;
import java.io.*;
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int N, K, T[][], res;
    
    static void init(){
        T = new int[N][N];
        res = Integer.MAX_VALUE;
    }
    
    static void input() throws IOException{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        init();
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                T[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
    
    static void solve(){
        floydWarshall();
        dfs(K, 0, 1 << K); 
        sb.append(res);
    }
    
    static void floydWarshall(){
        for(int k = 0; k < N; k++){
            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    if(T[i][j] > T[i][k] + T[k][j]){
                        T[i][j] = T[i][k] + T[k][j];
                    }
                }
            }
        }
    }
    
    static void dfs(int cur, int time, int visited){
        if(visited == (1 << N) - 1){
            res = Math.min(time, res);
            return;
        }
        
        for(int i = 0; i < N; i++){
            if((visited & (1 << i)) != 0) continue;
            dfs(i, time + T[cur][i], visited ^ (1 << i));
        }
    }
    
    public static void main(String[] args) throws IOException{
        input();
        solve();
        
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
