import java.util.*;
import java.io.*;

public class Main {
    static int N, score;
    static int[][] green, blue;
    static StringTokenizer st;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());
        green = new int[6][4];
        blue = new int[4][6];
        score = 0;
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            
            move(t, x, y); // 블록을 이동시킴
            checkIfFull(); // 채운 행/열을 삭제
            checkBufferZone(); // 버퍼존 처리 (위쪽 두 줄 처리)
        }
        
        // print(); // 디버깅용 출력
        int cnt = 0;
        // 남은 블록 수 계산
        for (int i = 2; i < 6; i++) {
            for (int j = 0; j < 4; j++) {
                if (green[i][j] > 0) {
                    cnt++;
                }
                if (blue[j][i] > 0) {
                    cnt++; 
                }
            }
        }
        
        System.out.println(score);
        System.out.println(cnt);
    }
    
    public static void checkBufferZone() {
        // Green
        for (int i = 0; i < 2; i++) {
            boolean flag = false;
            for (int j = 0; j < 4; j++) {
                if (green[i][j] != 0) {
                    flag = true;
                    break;
                }
            }
            if (flag) {
                // 있으면, 아래로 내리기
                for (int row = 5; row > 0; row--) {
                    for (int col = 0; col < 4; col++) {
                        green[row][col] = green[row - 1][col];
                    }
                }
                // 비우기
                for (int col = 0; col < 4; col++) {
                    green[0][col] = 0;
                }
            }
        }

        // Blue
        for (int i = 0; i < 2; i++) {
            boolean flag = false;
            for (int j = 0; j < 4; j++) {
                if (blue[j][i] != 0) {
                    flag = true;
                    break;
                }
            }
            if (flag) {
                // 있으면, 아래로 내리기
                for (int col = 5; col > 0; col--) {
                    for (int row = 0; row < 4; row++) {
                        blue[row][col] = blue[row][col - 1];
                    }
                }
                // 비우기
                for (int row = 0; row < 4; row++) {
                    blue[row][0] = 0;
                }
            }
        }
    }
    
    public static void checkIfFull() {
        int cnt = 0;
        
        // Green 확인
        for (int i = 2; i < 6; i++) {
            boolean full = true;
            for (int j = 0; j < 4; j++) {
                if (green[i][j] == 0) {
                    full = false;
                    break;
                }
            }
            if (full) {
                // 삭제하고 위로 올리기
                cnt++;
                for (int row = i; row > 0; row--) {
                    for (int col = 0; col < 4; col++) {
                        green[row][col] = green[row - 1][col];
                    }
                }
                // 사용하지 않으므로 비워주기
                for (int col = 0; col < 4; col++) {
                    green[0][col] = 0;
                }
            }
        }

        // Blue 확인
        for (int i = 2; i < 6; i++) {
            boolean full = true;
            for (int j = 0; j < 4; j++) {
                if (blue[j][i] == 0) {
                    full = false;
                    break;
                }
            }
            if (full) {
                // 삭제하고 위로 올리기
                cnt++;
                for (int col = i; col > 0; col--) {
                    for (int row = 0; row < 4; row++) {
                        blue[row][col] = blue[row][col - 1];
                    }
                }
                // 첫 번째 열 비워주기
                for (int row = 0; row < 4; row++) {
                    blue[row][0] = 0;
                }
            }
        }
        
        score += cnt;
    }

    public static void move(int t, int x, int y) {
        int greenX = 5;
        int blueY = 5;
        
        if (t == 1) { // 1x1
            // Green 내리기
            for (int i = 0; i < 6; i++) {
                if (green[i][y] > 0) {
                    greenX = i - 1;
                    break;
                }
            }

            // Blue 내리기
            for (int i = 0; i < 6; i++) {
                if (blue[x][i] > 0) {
                    blueY = i - 1;
                    break;
                }
            }

            green[greenX][y] = 1;
            blue[x][blueY] = 1;
        } else if (t == 2) { // 1x2
            // Green 내리기
            for (int i = 0; i < 6; i++) {
                boolean canPlace = true;
                for (int j = 0; j < 2; j++) {
                    if (green[i][y + j] > 0) {
                        canPlace = false;
                        break;
                    }
                }
                if (!canPlace) {
                    greenX = i - 1;
                    break;
                }
            }

            // Blue 내리기
            for (int i = 0; i < 6; i++) {
                if (blue[x][i] > 0) {
                    blueY = i - 1;
                    break;
                }
            }

            green[greenX][y] = 2;
            green[greenX][y + 1] = 2;
            blue[x][blueY] = 2;
            blue[x][blueY - 1] = 2;
        } else { // 2x1
            // Green 내리기
            for (int i = 0; i < 6; i++) {
                if (green[i][y] > 0) {
                    greenX = i - 1;
                    break;
                }
            }

            // Blue 내리기
            for (int i = 0; i < 6; i++) {
                boolean canPlace = true;
                for (int j = 0; j < 2; j++) {
                    if (blue[x + j][i] > 0) {
                        canPlace = false;
                        break;
                    }
                }
                if (!canPlace) {
                    blueY = i - 1;
                    break;
                }
            }

            green[greenX][y] = 3;
            green[greenX - 1][y] = 3;
            blue[x][blueY] = 3;
            blue[x + 1][blueY] = 3;
        }
    }

    public static void print() {
        // 디버깅용 출력
        System.out.println("Green : ");
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(green[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
        
        System.out.println("Blue : ");
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 6; j++) {
                System.out.print(blue[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
