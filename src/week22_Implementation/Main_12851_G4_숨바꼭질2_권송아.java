package boj;

import java.util.*;
import java.io.*;

public class boj12851 {


    static int N, K;
    static int minCnt = 0;
    static final int INF = Integer.MAX_VALUE;
    static int minTime = INF;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        find();

        System.out.println(minTime);
        System.out.print(minCnt);
    }

    static void find(){

        Queue<Integer> q = new LinkedList<>();
        q.add(N);

        int[] visited = new int[100001];
        Arrays.fill(visited, INF);
        visited[N] = 0;
        int currTime = 0;

        while(!q.isEmpty()){

            int size = q.size();
            while(size-->0){
                int curr = q.poll();

                if(curr == K){
                    if(minTime > currTime){
                        minTime = currTime;
                        minCnt = 1;
                        continue;
                    }

                    if(minTime == currTime){
                        ++minCnt;
                    }
                    continue;
                }

                if(curr-1>-1 && visited[curr-1] > currTime){
                    q.add(curr-1);
                    visited[curr-1] = currTime+1;
                }

                if(curr+1<100001 && visited[curr+1] > currTime){
                    q.add(curr+1);
                    visited[curr+1] = currTime+1;
                }

                if(curr*2<100001 && visited[curr*2] > currTime){
                    q.add(curr*2);
                    visited[curr*2] = currTime+1;
                }
            }
            ++currTime;
            if(currTime > minTime){
                break;
            }
        }


    }
}
