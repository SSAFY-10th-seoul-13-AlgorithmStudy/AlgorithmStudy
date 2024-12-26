import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static int[] parent;
    
    public static void main(String[] args) throws Exception {
        //일반적인 그래프로는 백만개의 간선을 하나하나 이어주면서 사이클 판단.. 안될듯
        //union-find 로 사이클 찾을 수 있음 (root가 같으면 같은 집단에 속한거니까 사이클)
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        //초기화
        parent = new int[N];
        for (int i = 0; i < N; i++)
            parent[i] = i;
        
        //점들 입력 받으면서 잇기
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            if(find(a) == find(b)) {
                System.out.println(i+1);
                return;
            } else {
                union(a, b);
            }
        }
        
        System.out.println(0);
        
    }
    
    public static int find(int x) {
        if(x == parent[x])
            return parent[x];
        return parent[x] = find(parent[x]);
    }
    
    public static void union(int x, int y) {
        x = find(x);
        y = find(y);
        
        parent[x] = y;
    }
}
