import java.io.*;
import java.util.*;
 
public class Main {
    static int N, M, K;
    static int r, c;
    static int[][] notebook;
    static int[][] sticker;
 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
 
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        notebook = new int[N][M];
 
        while (K-- > 0) {
            st = new StringTokenizer(br.readLine());
            r = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            sticker = new int[10][10];
 
            //스티커 입력
            for (int i = 0; i < r; i++) {
                st = new StringTokenizer(br.readLine());
 
                for (int j = 0; j < c; j++) {
                    sticker[i][j] = Integer.parseInt(st.nextToken());
                }
            }
 
            // 돌아가면서 스티커를 붙일 수 있는지 확인
            for (int dir = 0; dir < 4; dir++) {
                boolean isAttach = false;
 
                for (int i = 0; i <= N - r; i++) {
                    if (isAttach) {
                        break;
                    }
 
                    for (int j = 0; j <= M - c; j++) {
                        if (isPossible(i, j)) {
                            attach(i, j);
                            isAttach = true;
                            break;
                        }
                    }
                }
 
                if (isAttach) {
                    break;
                }
 
                // 스티커 회전
                rotate();
            }
        }
 
        // 붙인 스티커 개수 세기
        int cnt = 0;
 
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (notebook[i][j] == 1) {
                    cnt++;
                }
            }
        }
 
        System.out.println(cnt);
    }
 
    static boolean isPossible(int x, int y) {
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                //불가능
                if (notebook[x + i][y + j] == 1 && sticker[i][j] == 1) {
                    return false;
                }
            }
        }
        //쌉가능
        return true;
    }
 
    // 스티커 붙이기
    static void attach(int x, int y) {
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (sticker[i][j] == 1) {
                    notebook[x + i][y + j] = 1;
                }
            }
        }
    }
 
    // 스티커를 90도 회전
    static void rotate() {
        int[][] tmp = new int[10][10];
 
        // 현재 스티커를 tmp에 저장
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                tmp[i][j] = sticker[i][j];
            }
        }
 
        // 스티커 회전
        for (int i = 0; i < c; i++) {
            for (int j = 0; j < r; j++) {
                sticker[i][j] = tmp[r - 1 - j][i];
            }
        }
 
        // 돌았으니까 크기 변경해줘야함
        int temp = r;
        r = c;
        c = temp;
    }
}
