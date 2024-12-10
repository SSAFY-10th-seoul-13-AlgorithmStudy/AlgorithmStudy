import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        
        for (int testcase = 1; testcase <= T; testcase++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            
            int[] stock = new int[N+1];
            
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                stock[i] = Integer.parseInt(st.nextToken());
            }
            
            System.out.println("Case #" + testcase);
            
            //LIS
            ArrayList<Integer> lis = new ArrayList<>();
            
            lis.add(stock[1]);
            for (int i = 2; i <= N; i++) {
                if(lis.get(lis.size()-1) < stock[i]) {
                    lis.add(stock[i]);
                }
                else {
                    int left = 0;
                    int right = lis.size() - 1;
                    
                    while(left <= right) {
                        int mid = (left + right) / 2;
                        if(lis.get(mid) < stock[i]) {
                            left = mid + 1;
                        } else {
                            right = mid - 1;
                        }
                    }
                    lis.set(left, stock[i]);
                }
            }
            
            if(lis.size() < K)
                System.out.println("0");
            else
                System.out.println("1");
        }
    }
}
