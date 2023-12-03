package week04;

import java.util.*;
import java.io.*;
public class Main_2571_G3_색종이3_김아린 {
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(in.readLine());
        int ans = -1;
        int[][] arr = new int[100][100];
        for(int i = 0; i < N; i++) {
            st= new StringTokenizer(in.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            //색종이 다 칠하기
            for(int a = x; a < x+10; a++) {
                for (int b = y; b < y + 10; b++) {
                    arr[a][b] = 1;
                }
            }
        }


        for(int i = 0; i < 99; i++) {
            for(int j = 0; j < 100; j++) {
                //빈공간이 아니면!!
                if(arr[i][j] != 0 && arr[i+1][j] != 0) {
                    arr[i+1][j] = arr[i][j] + 1;
                }
            }
        }

        //위치에 따라 최대 사각형은 변하므로 전부 확인해줘야 함
        for(int i = 0; i < 100; i++) {
            for(int j = 0; j < 100; j++) {
                //각 위치에서의 최대
                int max = Integer.MAX_VALUE;

                for(int k = j; k < 100; k++) {
                    max = Math.min(arr[i][k], max);
                    if(max == 0) //빈공간 적발
                        break;
                    ans = Math.max(ans, max*(k-j+1));
                }
            }
        }

        System.out.println(ans);
    }
}