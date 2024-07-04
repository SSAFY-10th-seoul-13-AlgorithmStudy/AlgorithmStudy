import java.util.*;
import java.io.*;

public class Main {
    static int r, c, namOrigin, movOrigin;
    static int[][] map;
    static int[][] dir = {{1,0}, {0, 1}, {-1, 0}, {0, -1}};
    public static void main(String[] args) throws Exception {
        //이거 생긴게 n퀸 풀었던거랑 비슷한데
        //n퀸을 어케 풀엇는지 기억이 안나네 쉬운건 재귀.. 어려운건 뭐엿지?? 압축했던거같음..
        // = = 0 0 -> 0 0 = 0 가 된다
        //핀의 개수 최소, 필요한 이동횟수 구하기
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        
        int N = Integer.parseInt(br.readLine());
        
        r = 5;
        c = 9;
        //빈칸 = 0, 핀 꽂 = 1, 구멍없 = -1
        
        for (int testcase = 0; testcase < N; testcase++) {
            map = new int[r][c];
            
            int nam = 0;
            for (int i = 0; i < r; i++) {
                String input = br.readLine();
                
                for (int j = 0; j < c; j++) {
                    if(input.charAt(j) == '#') {
                        map[i][j] = -1;
                    } else if (input.charAt(j) == '.') {
                        map[i][j] = 0;
                    } else {
                       map[i][j] = 1;
                        nam++;
                    }
                }
            }
            
            namOrigin = nam;
            movOrigin = 0;
            
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    if(map[i][j] == 1)
                        recur(i, j, nam, 0);
                }
            }
            
            br.readLine();//rormwlrkxdms
            
            System.out.println(namOrigin + " " + movOrigin);
        }
    }
    
    static void recur(int x, int y, int nam, int move) {
        if(nam <= namOrigin) {//현재 남은 것보다 원래 남은게 적어졌다면?
            namOrigin = nam; //갱신
            movOrigin = move;
        }
        
        
        for (int i = 0; i < 4; i++) {
            int cx = x + dir[i][0];
            int cy = y + dir[i][1];
            
            if (cx < 0 || cx >= r || cy < 0 || cy >= c) continue;
            //애초에 핀이라면
            if (map[cx][cy] == 1) {
                int mvx = cx + dir[i][0];
                int mvy = cy + dir[i][1];

                //다음칸은 비어있어야지
                if (mvx < 0 || mvx >= r || mvy < 0 || mvy >= c) continue;
                if (map[mvx][mvy] != 0) continue;
                
                map[x][y] = 0;
                map[cx][cy] = 0;
                map[mvx][mvy] = 1;
                
                //다시 타고 들어가기
                for (int a = 0; a < r; a++) {
                    for (int b = 0; b < c; b++) {
                        if(map[a][b] == 1)
                            recur(a, b, nam-1, move+1);
                    }
                }
                
                map[x][y] = 1;
                map[cx][cy] = 1;
                map[mvx][mvy] = 0;
            }
        }
    }
}
