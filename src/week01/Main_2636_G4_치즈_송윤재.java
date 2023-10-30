package week01;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2636_G4_치즈_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st; 
    static int R, C, map[][], count;
    static int dr[] = {1, -1, 0, 0};
    static int dc[] = {0, 0, 1, -1};
    
    static void init() {
    	map = new int[R][C];
    }
    
    static void input() throws IOException{
    	st = new StringTokenizer(br.readLine());
    	R = Integer.parseInt(st.nextToken());
    	C = Integer.parseInt(st.nextToken());
    	init();
    	
    	for(int i = 0; i < R; i++) {
    		st = new StringTokenizer(br.readLine());
    		for(int j = 0; j < C; j++) {
    			map[i][j] = Integer.parseInt(st.nextToken());
    			if(map[i][j] == 1) count++;
    		}
    	}
    }   
    
    static void solve() {
    	int time = 0;
    	int temp_cheeze = 0;
    	
    	while(count > 0) {
    		temp_cheeze = count;
    		bfs();
    		time++;
    	}
    	
    	sb.append(time).append("\n").append(temp_cheeze);
    }
    
    static void bfs() {
    	Queue<int[]> que = new ArrayDeque<>();
    	que.offer(new int[] {0, 0});
    	boolean visited[][] = new boolean[R][C];
    	
    	while(!que.isEmpty()) {
    		int[] cur = que.poll();
    		for(int i = 0; i < 4; i++) {
    			int nr = cur[0] + dr[i];
    			int nc = cur[1] + dc[i];
    			if(nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
    			if(visited[nr][nc]) continue;
    			visited[nr][nc] = true;
    			if(map[nr][nc] == 1) {
    				map[nr][nc] = 0;
    				count--;
    			}
    			else que.offer(new int[] {nr, nc});
    		}
    	}
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
