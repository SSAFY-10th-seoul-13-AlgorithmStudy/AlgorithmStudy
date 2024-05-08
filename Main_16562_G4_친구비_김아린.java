import java.util.*;
import java.io.*;

public class Main {
    static int[] friend, money;
    static boolean[] visited;
    static int N, M, K;
	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st = new StringTokenizer(br.readLine());
	    
	    int ans = 0;
	    N = Integer.parseInt(st.nextToken());
	    M = Integer.parseInt(st.nextToken());
	    K = Integer.parseInt(st.nextToken());
	    
	    //가중치가 있는 unionfind?
	    
	    money = new int[N+1];
	    visited = new boolean[N+1];
	    st = new StringTokenizer(br.readLine());
	    for (int i = 1; i <= N; i++) {
	        money[i] = Integer.parseInt(st.nextToken());
	    }
	    
	    init();
	    
	    int v, w;
	    for (int i = 0; i < M; i++) {
	        st = new StringTokenizer(br.readLine());
	        v = Integer.parseInt(st.nextToken());
	        w = Integer.parseInt(st.nextToken());
	        union(v, w);
	    }
	    
	    for (int i = 1; i <= N; i++) {
	        ans += money[i];
	    }
	    
	    System.out.println( ans > K ? "Oh no" : ans);
	}
	
	public static void init() {
	    friend = new int[N+1];
	    for (int i = 1; i <= N; i++) {
	        friend[i] = i;
	    }
	}
	
	public static int find(int v) {
	    if (v == friend[v]) {
	        return v;
	    }
	    return friend[v] = find(friend[v]);
	}
	
	public static void union(int v, int w) {
	    int rootV = find(v);
	    int rootW = find(w);
	    if(rootV != rootW) {
            friend[rootW] = rootV;
            money[rootV] = Math.min(money[rootV], money[rootW]);
            money[rootW] = 0;
	    }
	}
	
}
