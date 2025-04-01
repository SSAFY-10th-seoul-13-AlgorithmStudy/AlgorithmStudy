package week54_SegmentTree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_2268_G1_수들의합7_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N, M;
	static long nodes[];
	
	static void init() {
		nodes = new long[N << 2];
	}
	
	static void input_solve() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		init();
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int order = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if(order == 0) {
				int min = Math.min(a, b);
				int max = Math.max(a, b);
				
				sb.append(sum(1, N, min, max, 1)).append("\n");
			} else {
				modify(1, N, a, b, 1);
			}
		}
	}
	
	static long sum(int start, int end, int left, int right, int node) {
		if(left > end || right < start) return 0;
		if(left <= start && right >= end) return nodes[node];
		int mid = (start + end) >> 1;
		return sum(start, mid, left, right, node * 2) + sum(mid + 1, end, left, right, node * 2 + 1);
	}
	
	static long modify(int start, int end, int idx, int value, int node) {
		if(idx < start || idx > end) return nodes[node];
		if(start == end) return nodes[node] = value;
		int mid = (start + end) >> 1;
		return nodes[node] = modify(start, mid, idx, value, node * 2) + modify(mid + 1, end, idx, value, node * 2 + 1);
	}
	
	public static void main(String[] args) throws IOException {
		input_solve();

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
