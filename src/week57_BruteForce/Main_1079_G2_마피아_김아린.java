import java.util.*;
import java.io.*;

public class Main {
    static int N, jin, ans;
    static int[] guilt;
    static int[][] R;
    static boolean[] live;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
                
        guilt = new int[N];
        R = new int[N][N];
        live = new boolean[N];
        ans = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            guilt[i] = Integer.parseInt(st.nextToken());
            live[i] = true;
        }
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                R[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        jin = Integer.parseInt(br.readLine());
        
        play(0, N);
        
        System.out.println(ans);
    }
    
    public static void play(int round, int total) {
        if(total == 1 || !live[jin]) {
            ans = Math.max(ans, round);
            return;
        }
        
        if(total % 2 == 0) { //밤
            for (int i = 0; i < N; i++) {
                if(!live[i]) continue;
                if(i == jin) continue;
                
                live[i] = false; //죽이기
                for (int j = 0; j < N; j++) { //유죄 계산
                    // if(!live[j]) continue;
                    guilt[j] += R[i][j];
                }
                play(round+1, total-1);
                live[i] = true; //살리기
                for (int j = 0; j < N; j++) { //유죄 계산
                    // if(!live[j]) continue;
                    guilt[j] -= R[i][j];
                }
            }
        } else { //낮
            int tmpIdx = -1; //죽일 idx
            int tmp = Integer.MIN_VALUE; //죄
            for (int i = 0; i < N; i++) {
                if(!live[i]) continue;
                
                if(guilt[i] > tmp) {
                    tmp = guilt[i];
                    tmpIdx = i;
                }
            }
        
            live[tmpIdx] = false; //죽이기
            play(round, total-1);
            live[tmpIdx] = true; //살리기
        }
    }
}
