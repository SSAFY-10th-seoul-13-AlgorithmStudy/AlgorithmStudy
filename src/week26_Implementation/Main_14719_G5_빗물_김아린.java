import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        
        int[] map = new int[W];
        
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < W; i++) {
            map[i] = Integer.parseInt(st.nextToken());     
        }
        
        int ans = 0;
        //옆의 벽과 비교
        for (int i = 1; i < W-1; i++) {
            int now = map[i];
            int leftSide = 0, rightSide = 0;
            
            for(int j = 0; j < i; j++) {
                leftSide = Math.max(leftSide, map[j]);
            }
            for (int j = i+1; j < W; j++) {
                rightSide = Math.max(rightSide, map[j]);
            }
            
            int tmp = Math.min(leftSide, rightSide) - now;
            ans += tmp > 0 ? tmp : 0;

        }
        
        System.out.println(ans);
    }
}
