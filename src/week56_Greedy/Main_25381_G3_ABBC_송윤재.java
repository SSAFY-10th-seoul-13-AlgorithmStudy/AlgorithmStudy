import java.util.*;
import java.io.*;
public class Main {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int N;
    static char S[];
    static boolean check[];
    
    static void init(){
        check = new boolean[N];
    }
    
    static void input() throws IOException{
        String str = br.readLine();
        N = str.length();
        init();
        S = str.toCharArray();
    }
    
    static void solve(){
        Queue<Integer> que = new ArrayDeque<>();
        int cnt = 0;
        for(int i = 0; i < N; i++){
            if(S[i] == 'B'){
                que.offer(i);
            } else if(S[i] == 'C'){
                if(!que.isEmpty()){
                    int b_idx = que.poll();
                    check[b_idx] = true;
                    check[i] = true;
                    cnt++;
                }
            }
        }
        
        que = new ArrayDeque<>();
        
        for(int i = N - 1; i >= 0; i--){
            if(check[i]) continue;
            if(S[i] == 'B'){
                que.offer(i);
            } else if(S[i] == 'A'){
                if(!que.isEmpty()){
                    int b_idx = que.poll();
                    check[b_idx] = true;
                    check[i] = true;
                    cnt++;
                }
            }
        }
        sb.append(cnt);
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
