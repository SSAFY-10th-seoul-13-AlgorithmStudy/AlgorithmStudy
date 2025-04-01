package week63_Implementation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_17837_G2_새로운게임2_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N, K, map[][];
	static List<Integer> state[][];
	static Horse horses[];
	static int dr[] = {0, 0, 0, -1, 1};
	static int dc[] = {0, 1, -1, 0, 0};
	static final int RED = 1;
	static final int BLUE = 2;
	
	static class Horse{
		int num, r, c, dir;
		public Horse(int num, int r, int c, int dir) {
			this.num = num;
			this.r = r;
			this.c = c;
			this.dir = dir;
		}
		public void changeDir() {
			if(dir == 1) dir = 2;
			else if(dir == 2) dir = 1;
			else if(dir == 3) dir = 4;
			else dir = 3;
		}
		public void setLocate(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static void init() {
		map = new int[N + 1][N + 1];
		state = new List[N + 1][N + 1];
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				state[i][j] = new ArrayList<>();
			}
		}
		horses = new Horse[K + 1];
	}
	
	static void input() throws IOException{
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		init();
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for(int i = 1; i <= K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			Horse cur = new Horse(i, r, c, dir);
			horses[i] = cur;
			state[r][c].add(i);
		}
	}
	
	static void solve() {
		int cnt = 1;
		top:
		while(cnt < 1001) {
//			System.out.println("================" + cnt + "=================");
			for(int k = 1; k <= K; k++) {
				Horse cur = horses[k];
				int num = cur.num;
				int cr = cur.r;
				int cc = cur.c;
				int dir = cur.dir;
				List<Integer> subList = new ArrayList<>();
//				System.out.println("시작 위치 : " + cr + ", " + cc);
				
				for(int i = 0, size = state[cr][cc].size(); i < size; i++) {
					if(state[cr][cc].get(i) == num) {
						subList = new ArrayList<>(state[cr][cc].subList(i, size));
						state[cr][cc].subList(i, size).clear();
						break;
					}
				} // 이동할 서브리스트
//				System.out.println("서브리스트 출력 ...");
//				for(int h : subList) {
//					System.out.println(h);
//				}
				
				int nr = cr + dr[dir];
				int nc = cc + dc[dir];
				if(isBlue(nr, nc)) { // 나가거나 파랑칸 일 때
					cur.changeDir();
	                dir = cur.dir; // 바뀐 방향 적용
	                nr = cr + dr[dir];
	                nc = cc + dc[dir];
	                if (isBlue(nr, nc)) { 
	                    state[cr][cc].addAll(subList); // 다시 제자리로
	                    continue;
	                }
				}
				if(map[nr][nc] == RED) {
					Collections.reverse(subList);
				}
	            state[nr][nc].addAll(subList);
	            
//				System.out.println("현재 위치 : " + nr + ", " + nc);
				for(int h : state[nr][nc]) {
//					System.out.println("번호 : " + h + " 방향 : " + horses[h].dir);
					horses[h].setLocate(nr, nc);
				}
	            
				if(state[nr][nc].size() >= 4) {
					break top;
				}
			}
			cnt++;
		}
		sb.append(cnt >= 1000 ? -1 : cnt);
	}
	
	static boolean isBlue(int r, int c) {
		return (r <= 0 || r > N || c <= 0 || c > N) || map[r][c] == BLUE;
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
