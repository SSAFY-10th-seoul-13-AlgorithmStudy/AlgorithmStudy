package week30_Implementation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_15685_G3_드래곤커브_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N, dragon[][];
	static boolean map[][];
	static int dx[] = {1, 0, -1, 0};
	static int dy[] = {0, -1, 0, 1};	
	
	static void init() {
		dragon = new int[N][4];
		map = new boolean[101][101];
	}
		
	static void input() throws IOException {
		N = Integer.parseInt(br.readLine());
		init();
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 4; j++) {
				dragon[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}

	static void solve() {
		for(int i = 0; i < N; i++) {
			int sx = dragon[i][0];
			int sy = dragon[i][1];
			int dir = dragon[i][2];
			int gen = dragon[i][3];
			
			List<int[]> vertex = new ArrayList<>();
			
			vertex.add(new int[] {sx, sy});
			vertex.add(new int[] {sx + dx[dir], sy + dy[dir]}); // 0세대까지 넣기
			
			while(gen-- > 0) {
				rotate(vertex);
			}
			for(int[] v : vertex) {
				map[v[0]][v[1]] = true;
			}
		}
		int result = 0;
		for(int i = 0; i < 100; i++) {
			for(int j = 0; j < 100; j++) {
				if(map[i][j] && map[i + 1][j] && map[i][j + 1] && map[i + 1][j + 1]) // 사각형 다 점 찍힌 경우
					result++;
			}
		}
		sb.append(result);
	}
	
	static void rotate(List<int[]> vertex) {
		int size = vertex.size();
		int[] end = vertex.get(size - 1);
		
		for(int i = size - 2; i >= 0; i--) { // 끝점과 가까운 점들부터 시계방향 90도 회전 시키며 vertex에 추가
			int[] cur = vertex.get(i);
			int[] next = new int[2];
			next[0] = end[0] + (end[1] - cur[1]);
			next[1] = end[1] - (end[0] - cur[0]);
			vertex.add(next);
		}
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
