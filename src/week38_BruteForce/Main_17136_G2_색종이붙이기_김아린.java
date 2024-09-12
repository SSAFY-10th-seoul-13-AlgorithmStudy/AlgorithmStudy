import java.util.*;
import java.io.*;

public class Main {
    static int[][] map;
    static int ans;
    static int[] paper = {0, 5, 5, 5, 5, 5};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        ans = Integer.MAX_VALUE;
        
        StringTokenizer st = null;
        map = new int[10][10];
        
        for (int i = 0; i < 10; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 10; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        //가장 큰걸로 덮어야 무조건 이득 => 잠깐만 아닌데?? 그러면 dfs..? 
        //붙이고 확인 / 떼고 확인.. 
        
        //x, y 위치 cnt 
        dfs(0, 0, 0);
        
        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans); 
    }
    
    public static void dfs(int x, int y, int cnt) {
        //종료 조건 => 끝까지 확인하면
        if(x >= 9 && y > 9) {
            ans = Math.min(ans, cnt);
            return;
        }     
        
        if(y > 9) {
            dfs(x+1, 0, cnt);
            return;
        }
        
        //일단 지금것 검사
        if(map[x][y] == 1) {
            for(int s = 5; s >= 1; s--) {
                //만약에 붙일 수 있고 && 색종이 남아있으면
                if(paper[s] > 0 && find(x, y, s)) {
                    //붙이고
                    add(x, y, s, 0);
                    paper[s]--;
                    // System.out.println(x + " " + y + " 에 " + s + "만큼 붙임");
                    //넘어가고
                    dfs(x, y+1, cnt+1);
                    
                    //떼고
                    add(x, y, s, 1);
                    paper[s]++;
                }   
            }
        } else { //할 게 없으니 넘어가기
             dfs(x, y+1, cnt);
        }
        
    }
    
    public static boolean find(int x, int y, int s) {
        //x, y에 대해 크기만큼 존재하는지??
        
        //이미 범위를 넘어갔으면 바로 return
        if(x+s > 10 || y+s > 10)
            return false;
        
        for (int i = x; i < x+s; i++) {
            for (int j = y; j < y+s; j++) {
                if(map[i][j] != 1)
                    return false; //바로 리턴
            }
        }
        
        return true;
    }
    
    public static void add(int x, int y, int s, int paper) {
        
        for (int i = x; i < x+s; i++) {
            for (int j = y; j < y+s; j++) {
                map[i][j] = paper;
            }
        }
    }
}
