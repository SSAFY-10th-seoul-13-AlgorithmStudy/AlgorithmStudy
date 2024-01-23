package week10_BackTracking;

import java.io.*;
import java.util.*;

//https://jellyinghead.tistory.com/53
//문제 이해가 생각보다 안됐다ㅎㅎ.....
public class Main_12100_G2_2048easy_김아린 {
    
    static int n, answer, map[][];
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        answer = 0;
        map = new int[n][n];
        StringTokenizer stz;
        for(int i = 0; i < n; i++) {
            stz = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++)
                map[i][j] = Integer.parseInt(stz.nextToken());
        }
        
        game(0);
        System.out.println(answer);
    }
    
    public static void game(int count) {
        // 움직임이 5번이 된 경우, 가장 큰 블록 값 저장
        if(count == 5) {
            findMax();
            return;
        }
        
        // 움직이기 전의 게임판 상태를 copy에 저장
        int copy[][] = new int[n][n];
        for(int i = 0; i < n; i++)
            copy[i] = map[i].clone();
        
        for(int i = 0; i < 4; i++) {
            move(i);
            game(count+1);
            for(int a = 0; a < n; a++)
                map[a] = copy[a].clone();
        }
    }
    
    // 주어진 방향으로 블록을 움직이며, 같은 값의 블록이 만나면 합쳐짐
    public static void move(int dir) {
        switch(dir) {
            //위로 몰기
            case 0:
                for(int i = 0; i < n; i++) {
                    int index = 0;
                    int block = 0;
                    for(int j = 0; j < n; j++) {
                        if(map[j][i] != 0) {
                            if(block == map[j][i]) {
                                map[index - 1][i] = block * 2;
                                block = 0;
                                map[j][i] = 0;
                            }
                            else {
                                block = map[j][i];
                                map[j][i] = 0;
                                map[index][i] = block;
                                index++;
                            }
                        }
                    }
                }
                break;
            //왼쪽으로 몰기
            case 1:
                for(int i = 0; i < n; i++) {
                    int index = n - 1;
                    int block = 0;
                    for(int j = n - 1; j >= 0; j--) {
                        if(map[j][i] != 0) {
                            if(block == map[j][i]) {
                                map[index + 1][i] = block * 2;
                                block = 0;
                                map[j][i] = 0;
                            }
                            else {
                                block = map[j][i];
                                map[j][i] = 0;
                                map[index][i] = block;
                                index--;
                            }
                        }
                    }
                }
                break;
            //왼쪽으로 몰기
            case 2:
                for(int i = 0; i < n; i++) {
                    int index = 0;
                    int block = 0;
                    for(int j = 0; j < n; j++) {
                        if(map[i][j] != 0) {
                            if(block == map[i][j]) {
                                map[i][index - 1] = block * 2;
                                block = 0;
                                map[i][j] = 0;
                            }
                            else {
                                block = map[i][j];
                                map[i][j] = 0;
                                map[i][index] = block;
                                index++;
                            }
                        }
                    }
                }
                break;
            //오른쪽으로 몰기
            case 3:
                for(int i = 0; i < n; i++) {
                    int index = n - 1;
                    int block = 0;
                    for(int j = n - 1; j >= 0; j--) {
                        if(map[i][j] != 0) {
                            if(block == map[i][j]) {
                                map[i][index + 1] = block * 2;
                                block = 0;
                                map[i][j] = 0;
                            }
                            else {
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
    
    public static void findMax() {
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                answer = Math.max(answer, map[i][j]);
    }
}