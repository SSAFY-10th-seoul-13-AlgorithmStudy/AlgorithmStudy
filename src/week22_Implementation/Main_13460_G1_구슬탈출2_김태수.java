package week22_Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main_13460_G1_구슬탈출2_김태수 {
    public static int direction[][] = new int[][] {{1,0},{-1,0},{0,-1},{0,1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] bb = new int[2];
        int[] rr = new int[2];

        char[][] map = new char[N][M];
        boolean visited[][][][] = new boolean[N][M][N][M];
        for(int i = 0 ; i < N;i++){
            String s = br.readLine();
            for(int j = 0 ; j < M;j++){
                map[i][j] = s.charAt(j);
                if(map[i][j] == 'B'){
                    bb[0] = i;
                    bb[1] = j;
                }
                if(map[i][j] == 'R'){
                    rr[0] = i;
                    rr[1] = j;
                }
            }
        }

        ArrayDeque<int[]> qq = new ArrayDeque<>();

        qq.add(new int[] {rr[0],rr[1],bb[0],bb[1],1});
        visited[rr[0]][rr[1]][bb[0]][bb[1]] = true;

        while(!qq.isEmpty()){
            int [] cur = qq.poll();

            if(cur[4] > 10) break;

            for(int[] dir: direction){
                int rx = cur[0];
                int ry = cur[1];
                int rcnt = 0;
                while(map[rx + dir[0]][ry+dir[1]] != '#' && map[rx][ry] != 'O'){
                    rx += dir[0];
                    ry += dir[1];
                    rcnt++;
                }

                int bx = cur[2];
                int by = cur[3];
                int bcnt = 0;
                while(map[bx + dir[0]][by+dir[1]] != '#' && map[bx][by] != 'O'){
                    bx += dir[0];
                    by += dir[1];
                    bcnt++;
                }

                if(map[bx][by] == 'O') continue;
                if(map[rx][ry] == 'O'){
                    System.out.println(cur[4]);
                    return;
                }

                if(rx == bx && ry == by){
                    if(rcnt > bcnt){
                        rx -= dir[0];
                        ry -= dir[1];
                    }
                    else{
                        bx -= dir[0];
                        by -= dir[1];
                    }
                }

                if(!visited[rx][ry][bx][by]) {
                    visited[rx][ry][bx][by] = true;
                    qq.add(new int[] {rx,ry,bx,by,cur[4]+1});
                }

            }
        }
        System.out.println(-1);
        
    }
}
