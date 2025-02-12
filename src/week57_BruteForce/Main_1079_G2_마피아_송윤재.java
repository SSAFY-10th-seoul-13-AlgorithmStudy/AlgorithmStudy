import java.util.*;
import java.io.*;
public class Main {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int N, arr[], R[][], EZ, max;
    
    static void init(){
        arr = new int[N];
        R = new int[N][N];        
    }

    static void input() throws IOException{
        N = Integer.parseInt(br.readLine());
        init();
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                R[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        EZ = Integer.parseInt(br.readLine());
    }
    
    static void solve(){
        dfs(N, new boolean[N], 0);
        sb.append(max);
    }
    
    static void dfs(int survivor, boolean[] isDie, int depth){
        if(max < depth) max = depth;
        if(survivor == 1) return;
        
        if(survivor % 2 == 0){ // 밤
            for(int i = 0; i < N; i++){
                if(!isDie[i] && i != EZ){
                    isDie[i] = true;
                    for(int j = 0; j < N; j++){
                        arr[j] += R[i][j];
                    }
                    dfs(survivor - 1, isDie, depth + 1);
                    isDie[i] = false;
                    for(int j = 0; j < N; j++){
                        arr[j] -= R[i][j];
                    }
                }
            }
        } else{ // 낮
            int maxCrime = Integer.MIN_VALUE;
            int target = -1;
            for(int i = 0; i < N; i++){
                if(!isDie[i]){
                    if(maxCrime < arr[i]){
                        target = i;
                        maxCrime = arr[i];
                    }
                }
            }
            if(target == EZ) return;
            isDie[target] = true;
            dfs(survivor - 1, isDie, depth);
            isDie[target] = false;
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
