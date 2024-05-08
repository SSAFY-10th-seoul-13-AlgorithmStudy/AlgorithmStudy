package boj;

import java.util.*;
import java.io.*;
public class Main_16562_G4_친구비_권송아  {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int N, M, k;
    static Set<Integer>[] graph;
    static int[] cost;
    static boolean[] visited;

    static void input() throws Exception{
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        graph = new Set[N+1];
        cost = new int[N+1];
        visited = new boolean[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<N+1; ++i){
            graph[i] = new HashSet<>();
            cost[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=0; i<M; ++i){
            st = new StringTokenizer(br.readLine());

            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());


            if(v1==v2) continue;
            graph[v1].add(v2);
            graph[v2].add(v1);
        }
    }

    static void close() throws Exception{
        br.close();
    }

    public static void main(String[] args) throws Exception{
        input();
        int sum = go();
        output(sum);
        close();
    }

    static int min = 0;
    static int go(){
        int sum = 0;
        for(int i=1; i<N+1; ++i){
            if(!visited[i]){
                min = Integer.MAX_VALUE;
                dfs(i);
                sum += min;
            }
        }

        return sum;
    }

    static void dfs(int curr){
        visited[curr] = true;
        min = Math.min(min, cost[curr]);
        for(int next : graph[curr]){
            if(visited[next]) continue;

            dfs(next);
        }
    }

    static void output(int sum){

        if(sum > k){
            System.out.println("Oh no");
            return;
        }

        System.out.println(sum);
    }

}