package boj;

import java.util.*;
import java.io.*;

public class boj16927 {

    static int[][] arr;
    static int R, C, rotate;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        rotate = Integer.parseInt(st.nextToken());

        arr = new int[R][C];
        for(int r=0; r<R; ++r){
            st = new StringTokenizer(br.readLine());
            for(int c=0; c<C; ++c){
                arr[r][c] = Integer.parseInt(st.nextToken());
            }
        }


        rotate();

        StringBuilder sb = new StringBuilder();
        for(int r=0; r<R; ++r){
            for(int c=0; c<C; ++c){
                sb.append(arr[r][c]).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    static void rotate(){

        int times = Math.min(R,C)/2;
        for(int t=0; t<times; ++t){
            List<Integer> list = new ArrayList<>();

            int sr = t;
            int sc = t;
            int er = R-1-t;
            int ec = C-1-t;

            int r = sr;
            int c = sc;
            int d = 0;

            if(sr==er || sc==ec){
                continue;
            }

            do{
                list.add(arr[r][c]);

                // 방향 업데이트
                r += dr[d];
                c += dc[d];

                if(r<sr || r>er || c<sc || c>ec){
                    r -= dr[d];
                    c -= dc[d];
                    ++d;

                    r += dr[d];
                    c += dc[d];
                }
            } while(r!=sr || c!=sc);

            int size = list.size();
            int newIdx = rotate%size;

            r = sr;
            c = sc;
            do{
                arr[r][c] = list.get(newIdx%size);
                newIdx = (newIdx+1)%size;
                // 방향 업데이트
                r += dr[d];
                c += dc[d];

                if(r<sr || r>er || c<sc || c>ec){
                    r -= dr[d];
                    c -= dc[d];
                    d = (d+1)%4;

                    r += dr[d];
                    c += dc[d];
                }
            } while(r!=sr || c!=sc);
        }
    }

}
