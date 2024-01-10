package week10_BackTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_12100_G2_2048Easy_김희연 {
    static int n, answer;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        map = new int[n][n];

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0);
        System.out.println(answer);
    }

    public static void dfs(int cnt){
        if(cnt == 5){
            for(int i = 0; i < n; i++)
                for(int j = 0; j < n; j++)
                    answer = Math.max(answer, map[i][j]);
            return;
        }

        int[][] copy = new int[n][n];

        for(int i=0; i<n; i++){
            copy[i] = map[i].clone();
        }

        for(int i=0; i<4; i++){
            move(i);
            dfs(cnt+1);
            for(int j=0; j<n; j++){
                map[j] = copy[j].clone();
            }
        }
    }

    public static void move(int dir){
        switch (dir){
            case 0:
                for(int i=0; i<n; i++){
                    int index = 0;
                    int block = 0;
                    for(int j=0; j<n; j++){
                        if(map[j][i] != 0){
                            if(block == map[j][i]){
                                map[index-1][i] = block * 2;
                                block = 0;
                                map[j][i] = 0;
                            } else {
                                block = map[j][i];
                                map[j][i] = 0;
                                map[index][i] = block;
                                index++;
                            }
                        }
                    }
                }
                break;
            case 1:
                for(int i=0; i<n; i++){
                    int index = n-1;
                    int block = 0;
                    for(int j=n-1; j>=0; j--){
                        if(map[j][i] != 0){
                            if(block == map[j][i]){
                                map[index+1][i] = block * 2;
                                block = 0;
                                map[j][i] = 0;
                            } else {
                                block = map[j][i];
                                map[j][i] = 0;
                                map[index][i] = block;
                                index--;
                            }
                        }
                    }
                }
                break;
            case 2:
                for(int i=0; i<n; i++){
                    int index = 0;
                    int block = 0;
                    for(int j=0; j<n; j++){
                        if(map[i][j] != 0){
                            if(block == map[i][j]){
                                map[i][index-1] = block * 2;
                                block = 0;
                                map[i][j] = 0;
                            } else {
                                block = map[i][j];
                                map[i][j] = 0;
                                map[i][index] = block;
                                index++;
                            }
                        }
                    }
                }
            break;
            case 3:
                for(int i=0; i<n; i++){
                    int index = n-1;
                    int block = 0;
                    for(int j=n-1; j>=0; j--){
                        if(map[i][j] != 0){
                            if(block == map[i][j]){
                                map[i][index+1] = block * 2;
                                block = 0;
                                map[i][j] = 0;
                            } else {
                                block = map[i][j];
                                map[i][j] = 0;
                                map[i][index] = block;
                                index--;
                            }
                        }
                    }
                }
            break;
        }
    }
}
