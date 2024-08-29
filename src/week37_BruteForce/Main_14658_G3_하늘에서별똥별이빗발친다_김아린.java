import java.util.*;
import java.io.*;

public class Main {
    static int N, M, L, K; //가로 세로 트램펄린변 별똥별수
    static int[][] star;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        star = new int[K][2];
        
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            star[i][0] = Integer.parseInt(st.nextToken());
            star[i][1] = Integer.parseInt(st.nextToken());
        }
        
        int max = 0;
        for (int i = 0; i < K; i++) {
            for (int j = 0; j < K; j++) {
                //두 별을 기준으로 판단??
                // if(star[i][0] + L < star[j][0] || star[i][1] + L < star[j][1]) continue;
                
                max = Math.max(max, findMore(i, j));
            }
        }
        
        System.out.println(K - max);
    }
    
    public static int findMore(int a, int b) {
        int startX = Math.min(star[a][0], star[b][0]);
        int startY = Math.min(star[a][1], star[b][1]);
        
        //n^4???
        // for (int i = startX; i < startX + L; i++) {
            // for (int j = startY; j < startY + L; j++) {
                
            // }
        // }
        
        int cnt = 0;
        for (int i = 0; i < K; i++) {
            if(startX <= star[i][0] && startX + L >= star[i][0]) {
                if(startY <= star[i][1] && startY + L >= star[i][1]) {
                    cnt++;
                }
            }
        }
        
        return cnt;
    }
}
