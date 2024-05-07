import java.io.*;
import java.util.*;
 
public class Main {
    static int[] parent;
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
 
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
 
        //초기화
        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }
 
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
 
            if (command == 0) {
                union(a, b);
            } else if (command == 1) {
                String ans = isConnect(a, b) ? "YES" : "NO";
                System.out.println(ans);
            } else {
                continue;
            }
        }

    }
 
    public static int find(int x) {
        if (x == parent[x]) {
            return x;
        }
 
        return parent[x] = find(parent[x]);
    }
 
    public static void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX != rootY) {
            parent[rootY] = rootX;
        }
    }
 
    public static boolean isConnect(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX == rootY) {
            return true;
        }
        return false;
    }
 
}
