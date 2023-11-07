package week02;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_1799_G1_비숍_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st; 
    static int map[][], N, size_a, size_b, max_a, max_b;
    static List<int[]> list_a, list_b;
    
    static void init() {
    	map = new int[N][N];
    	list_a = new ArrayList<>();
    	list_b = new ArrayList<>();
    }
    
    static void input() throws IOException{
    	N = Integer.parseInt(br.readLine());
    	init();
    	
    	for(int i = 0; i < N; i++) {
    		st = new StringTokenizer(br.readLine());
    		for(int j = 0; j < N; j++) {
    			map[i][j] = Integer.parseInt(st.nextToken());
    			if(map[i][j] == 1) {
    				if((i + j) % 2 == 0) list_a.add(new int[] {i, j});
    				else list_b.add(new int[] {i, j});
    			}
    		}
    	}
    }   
    
    static void solve() {
    	size_a = list_a.size();
    	size_b = list_b.size();
    	dfs_a(0, 0);
    	dfs_b(0, 0);
    	sb.append(max_a + max_b);
    }
    
    static void dfs_a(int idx, int cnt) {
    	if(idx == size_a) {
    		max_a = Math.max(max_a, cnt);
    		return;
    	}
    	
    	if(size_a - idx + cnt < max_a) return; // 앞으로 모든 빈칸에 비숍을 두어도 안되는 경우
    	
    	int[] next = list_a.get(idx);
    	if(check(next)) {
    		map[next[0]][next[1]] = 2;
    		dfs_a(idx + 1, cnt + 1);
    		map[next[0]][next[1]] = 0;
    	}
    	dfs_a(idx + 1, cnt);
    }
    
    static void dfs_b(int idx, int cnt) {
    	if(idx == size_b) {
    		max_b = Math.max(max_b, cnt);
    		return;
    	}
    	
    	if(size_b - idx + cnt < max_b) return; // 앞으로 모든 빈칸에 비숍을 두어도 안되는 경우
    	
    	int[] next = list_b.get(idx);
    	if(check(next)) {
    		map[next[0]][next[1]] = 2;
    		dfs_b(idx + 1, cnt + 1);
    		map[next[0]][next[1]] = 0;
    	}
    	dfs_b(idx + 1, cnt);
    }
    
    static boolean check(int[] temp) {
    	int dr[] = {-1, -1, 1, 1};
    	int dc[] = {-1, 1, 1, -1};
    	for(int i = 0; i < 4; i++) {
    		int nr = temp[0] + dr[i];
    		int nc = temp[1] + dc[i];
    		while(nr >= 0 && nr < N && nc >= 0 && nc < N) {
    			if(map[nr][nc] == 2) return false;
    			nr += dr[i];
    			nc += dc[i];
    		}
    	}
    	return true;
    }
    
	public static void main(String[] args) throws IOException{
		input();
		solve();
        
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
	}
}
