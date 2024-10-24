package week43_Dijkstra;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main_10282_G4_해킹_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N, D, C;
	static List<int[]>[] graph;
		
	static void init() {
		graph = new List[N + 1];
		for(int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
	}
			
	static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		init();
		for(int i = 0; i < D; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			graph[b].add(new int[] {a, s});
		}
	}

	static void solve() {
		PriorityQueue<int[]> pq = new PriorityQueue<int[]>((o1, o2) -> o1[1] - o2[1]);
		for(int[] i : graph[C]) {
			pq.offer(new int[] {i[0], i[1]});
		}
		int count = 1;
		int total = 0;
		boolean infected[] = new boolean[N + 1];
		infected[C] = true;
		
		while(!pq.isEmpty()) {
			int[] cur = pq.poll();
			int next = cur[0];
			int time = cur[1];
			if(infected[next]) continue;
//			System.out.println("next : " + next + "\t time : " + time);
			infected[next] = true;
			count++;
			total = time;
			for(int[] i : graph[next]) {
				pq.offer(new int[] {i[0], i[1] + time});
			}
		}
		sb.append(count).append(" ").append(total).append("\n");
	}
	
	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		for(int i = 0; i < T; i++) {
			input();
			solve();
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
