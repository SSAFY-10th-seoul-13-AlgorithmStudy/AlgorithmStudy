package week55_BFS_DFS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main_1525_G2_퍼즐_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int[][] origin;
	static int[] dr = {1, -1, 0, 0};
	static int[] dc = {0, 0, 1, -1};
	
	static void init() {
		origin = new int[3][3];
	}
	
	static void input() throws IOException {
		init();
		
		for(int i = 0; i < 3; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 3; j++) {
				origin[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}
	
	static void solve() {
		Queue<Integer> que = new ArrayDeque<Integer>();
		Set<Integer> visited = new HashSet<Integer>();
		int num = covertNum(origin);
		que.offer(num);
		visited.add(num);
		int depth = 0;
		
		while(!que.isEmpty()) {
			for(int cycle = 0, size = que.size(); cycle < size; cycle++) {
				int cur = que.poll();
				if(cur == 123456780) {
					sb.append(depth);
					return;
				}
				int[][] table = convertTable(cur);
				int cr = 0;
				int cc = 0;
				top:
				for(int i = 0; i < 3; i++) {
					for(int j = 0; j < 3; j++) {
						if(table[i][j] == 0) {
							cr = i;
							cc = j;
							break top;
						}
//						System.out.print(table[i][j] + " ");
					}
//					System.out.println();
				}
				
				for(int i = 0; i < 4; i++) {
					int nr = cr + dr[i];
					int nc = cc + dc[i];
					
					if(nr < 0 || nr > 2 || nc < 0 || nc > 2) continue;
					
					table[cr][cc] = table[nr][nc];
					table[nr][nc] = 0;
					
					int next = covertNum(table);
					
					if(visited.contains(next)) {
						table[nr][nc] = table[cr][cc];
						table[cr][cc] = 0;
						continue;
					}
					
					que.offer(next);
					visited.add(next);
					
					table[nr][nc] = table[cr][cc];
					table[cr][cc] = 0;
				}
			}
			depth++;
		}
		
		sb.append(-1);
	}
	
	static int covertNum(int[][] table) {
		int ret = 0;
		int pow = 8;
		
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				ret += table[i][j] * Math.pow(10, pow--);
			}
		}
		return ret;		
	}
	
	static int[][] convertTable(int num){
		int[][] ret = new int[3][3];
		int pow = 8;
		
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				ret[i][j] = (int) (num / Math.pow(10, pow--) % 10);
			}
		}
		
		return ret;
	}
	
	public static void main(String[] args) throws IOException {
		input();
		solve();

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
