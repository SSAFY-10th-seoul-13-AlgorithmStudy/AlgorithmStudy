package week22_Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_16927_G5_배열돌리기2_김태수 {
    static int N,M,R, map[][], newMap[][], direction[][] = {{0,1},{1,0},{0,-1},{-1,0}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for(int i = 0 ; i < N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < M;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        for(int i = 0,nn = N, mm = M, end = Math.min(M,N)/2; i < end;i++){

            int r = R % (2 * nn + 2 *mm - 4) ;
            rotate(i,r);
            nn -= 2;
            mm -= 2;
        }
        StringBuilder sb;
        for(int i = 0 ; i < N ;i++){
            sb = new StringBuilder();
            for(int j = 0 ; j < M ;j++){
                sb.append(map[i][j]).append(" ");
            }
            System.out.println(sb);
        }
    }

    public static void rotate(int start, int r){
        for(int i = 0 ; i < r;i++){
            int init = map[start][start];
            int idx = 0;
            int x = start;
            int y = start;
            while(idx < 4){
                int nx = x + direction[idx][0];
                int ny = y + direction[idx][1];
                if(nx >= start && nx < N - start && ny>= start && ny < M - start){
                    map[x][y] = map[nx][ny];
                    x = nx;             
                    y = ny;
                }
                else {
                    idx++;
                }
            }
            map[start+1][start] = init;
        }
    }
}
