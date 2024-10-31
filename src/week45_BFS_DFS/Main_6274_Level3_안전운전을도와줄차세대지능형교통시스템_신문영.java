package week45_BFS_DFS;

import java.io.*;
import java.util.*;

public class Main_6274_Level3_안전운전을도와줄차세대지능형교통시스템_신문영 {
    static int[][] direction = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}};
    static int[][] lightMap = {
        {0, 1, 3},
        {0, 1, 2},
        {1, 2, 3},
        {0, 2, 3},
        
        {0, 1},
        {1, 2},
        {2, 3},
        {3, 0},
        
        {0, 3},
        {1, 0},
        {2, 1},
        {3, 2},
    };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        int[][][] map = new int[N][N][4];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < 4; k++) {
                    map[i][j][k] = Integer.parseInt(st.nextToken()) - 1;
                }
            }
        }

        boolean[][] visited = new boolean[N][N];
        visited[0][0] = true;
        Queue<Car> queue = new ArrayDeque<>();
        queue.add(new Car(0, 0, 1));
        
        int currentTime = 0;
        while (currentTime < T) {
            Queue<Car> tracker = new ArrayDeque<>();
            
            while (!queue.isEmpty()) {
            	Car current = queue.poll();  
                int currentLight = map[current.r][current.c][currentTime % 4];
                if (currentLight % 4 == current.from) {
                    for (int light : lightMap[currentLight]) {
                        int nextR = current.r + direction[light][0];
                        int nextC = current.c + direction[light][1];
    
                        if (nextR < 0 || nextC < 0 || nextR >= N || nextC >= N) continue;
    
                        visited[nextR][nextC] = true;
                        tracker.add(new Car(nextR, nextC, light));
                    }
                }
            }

            queue.addAll(tracker);
            tracker.clear();
            currentTime++;
        }

        int answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                answer += visited[i][j] ? 1 : 0;
            }
        }

        System.out.println(answer);
    }

    public static class Car {
        int r;
        int c;
        int from;
        public Car(int r, int c, int from) {
            this.r = r;
            this.c = c;
            this.from = from;
        }
    }
}
