package week66_FloydWarshall;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1507_G2_궁금한민호_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N, max, dist[][];
	
	static void init() {
		dist = new int[N + 1][N + 1];
	}
	
	static void input() throws IOException{
		N = Integer.parseInt(br.readLine());
		init();
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= N; j++) {
				int len = Integer.parseInt(st.nextToken());
				if(i >= j) continue;
				max += len;
				dist[i][j] = len;
				dist[j][i] = len;
			}
		}
	}
	
	static void solve() {
		int res = 0;
		boolean visited[][] = new boolean[N + 1][N + 1];
		
		for(int k = 1; k <= N; k++) {
			for(int i = 1; i <= N; i++) {
				for(int j = 1; j <= N; j++) {
					if(i == k || k == j || j == i) continue;
 					if(dist[i][k] + dist[k][j] < dist[i][j]) { // 안되는 경우
						sb.append(-1);
						return;
					}
					if(dist[i][k] + dist[k][j] == dist[i][j]) { // 지워도 되는 경우
						if(!visited[i][j]) {
							max -= dist[i][j];
							visited[i][j] = true;
							visited[j][i] = true;
						}
					}
				}
			}
		}
		sb.append(max);
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
