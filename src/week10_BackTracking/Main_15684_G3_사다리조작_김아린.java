package week10_BackTracking;

import java.io.*;
import java.util.*;

public class Main_15684_G3_사다리조작_김아린 {
	static int N, M, H, ans;
	static boolean[][] garo;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		//세로선의 개수 N, 가로선의 개수 M, 세로선마다 가로선을 놓을 수 있는 위치의 개수 H
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		//가로선
		garo = new boolean[M][2];
		int a,b;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			garo[a-1][b-1] = true; //연결 관계를 이렇게 표현하기 
		}
		
		ans = 0;
		
		back(0, 0, 0);
		
	}
	
	private static boolean check() {
		//개인적으로 좀 빡셌
        for(int start = 0; start < N; start++) {
            int now = start;
            for(int j = 0; j < H; j++){
                if(garo[j][now]){
                    now += 1;
                } else if(now > 0 && garo[j][now - 1]){
                    now -= 1;
                }
            }
            if(now != start){
                return false;
            }
        }
        return true;
    }
	
	private static void back(int x, int y, int cnt) {
		ans = Math.min(ans, cnt);
		
		if(cnt == 3 || ans <= cnt) {
			return;
		}
		
		for(int i = x; i < H; i++){
            int now = (i == x) ? y : 0;
            for(int j = now; j < N - 1; j++){
                if(!garo[i][j] && !garo[i][j + 1]){
                    if(j > 0 && garo[i][j - 1]){
                        continue;
                    }
                    // 현재 위치에 가로방향을 설정
                    garo[i][j] = true;
                    // j + 2로 2칸 뒤로 넘어가는 것은, 현재 위치에서 오른쪽으로 사다리를 놓았다는 것을 의미합니다.
                    //사다리를 놓은 다음 위치에서는 사다리를 놓을 수 없으므로, 
                    //그 다음 사다리를 놓을 수 있는 위치는 최소 2칸 뒤
                    back(i, j + 2, cnt+1);
                    
                    garo[i][j] = false;
                }
            }
        }
	
	}
}
