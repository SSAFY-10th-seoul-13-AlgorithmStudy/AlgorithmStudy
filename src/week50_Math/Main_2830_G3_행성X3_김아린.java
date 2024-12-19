import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long N = Long.parseLong(br.readLine());
        long[][] sum = new long[21][2];

        //0과 1을 모아서 분석???
        for (int i = 0; i < N; i++) {
            int tmp = Integer.parseInt(br.readLine());
            
            int idx = 0;
            while(tmp > 0) {
                sum[idx++][tmp%2]++; 
                tmp /= 2;
            }
        }
        
        long ans = 0;
        
        for (int i = 0; i < 20; i++) {
            if(sum[i][1] != 0 && sum[i][0] + sum[i][1] != N) {
                sum[i][0] = N - sum[i][1];
            }
        }
        
        //11110100001000111111
        //11110100001001000000
        
        for (int i = 0; i < 20; i++) {
            ans += ((long) (1 << i) * (long)(sum[i][1] * sum[i][0]));
        }
        
        System.out.println(ans);
    }
}
