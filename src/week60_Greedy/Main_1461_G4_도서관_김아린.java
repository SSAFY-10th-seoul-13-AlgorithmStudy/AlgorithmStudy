import java.util.*;
import java.io.*;

public class Main {
    static int N, M, ans;
    static PriorityQueue<Integer> minusQ, plusQ;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        minusQ = new PriorityQueue<>(Collections.reverseOrder());
        plusQ = new PriorityQueue<>(Collections.reverseOrder());
        
        int max = 0, maxD = 0;
        ans = 0;
        // - 먼저 나눠주기 vs + 먼저 나눠주기 결과가 다를 수 있음
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int tmp = Integer.parseInt(st.nextToken());
            if(tmp < 0) {
                minusQ.add(Math.abs(tmp));
            } else {
                plusQ.add(tmp);
            }
            
            if(max < Math.abs(tmp)) {
                max = Math.abs(tmp);
                maxD = tmp < 0 ? -1 : 1;
            }
        }
        
        //가장 절댓값이 큰 값을 제일 나중에 가야함
        if(maxD < 0) { //음수먼저
            getQ(minusQ);
            getQ(plusQ);
        } else {
            getQ(plusQ);
            getQ(minusQ);
        }
        
        System.out.println(ans-max);
    }
    
    public static void getQ(PriorityQueue<Integer> q) {
        while(!q.isEmpty()) {
            int tmp = q.poll();
            
            for (int i = 0; i < M-1; i++) {
                q.poll();
                
                if(q.isEmpty()) {
                    break;
                }
            }
            
            ans += tmp * 2;
        }
    }
}
