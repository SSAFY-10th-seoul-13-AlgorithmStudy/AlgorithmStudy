import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static ArrayList<Integer>[] list;
    static int[] animal;
    static StringTokenizer st;
    // static long[] ans;
    // static long ans;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //이론상 각 ... 서브 트리의 총합을 구하면..되지 않나..
        //아 중간에 초기화될 수도 있으니 리프->루트로 탐색해야함
        N = Integer.parseInt(br.readLine());
        
        // ans = 0L;
        // ans = new long[N+1];
        animal = new int[N+1];
        list = new ArrayList[N+1];
        
        for (int i = 0; i < N+1; i++) {
            list[i] = new ArrayList<>();
        }
        
        for (int i = 2; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            
            String gubun = st.nextToken();
            int w = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            
            animal[i] = gubun.equals("W") ? -w : w;
            list[r].add(i);
        }
        // System.out.println(animal[2]);
        
        // dfs(1);
        
        System.out.println(dfs(1));
    }
    
    public static long dfs(int idx) {
        long ans = 0L;
        
        for (int i = 0; i < list[idx].size(); i++) {
            int tmp = list[idx].get(i);
            
            ans += dfs(tmp);
        }
        
        return ans + animal[idx] > 0 ? ans + animal[idx] : 0;
    }
}
