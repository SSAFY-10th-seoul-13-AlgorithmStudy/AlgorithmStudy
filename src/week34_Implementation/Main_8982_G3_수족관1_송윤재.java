package week34_Implementation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_8982_G3_수족관1_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N, K, floor[], len, water[];
	static List<Integer> holes;
	
	static void init() {
		floor = new int[40001];
		water = new int[40001];
		holes = new ArrayList<>();
	}
		
	static void input() throws IOException {
		N = Integer.parseInt(br.readLine());
		init();
		br.readLine(); // 첫 꼭지점 버리기
		for(int i = 0; i < (N / 2) - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int depth = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			int end = Integer.parseInt(st.nextToken());
			for(int j = start; j < end; j++) {
				floor[j] = depth;
			}
		}
		st = new StringTokenizer(br.readLine());
		len = Integer.parseInt(st.nextToken());
		
		K = Integer.parseInt(br.readLine());
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			st.nextToken();
			int end = Integer.parseInt(st.nextToken());
			holes.add(start);
			for(int j = start; j < end; j++) {
				water[j] = floor[j];				
			}
		}
	}

	static void solve() {
		int result = 0;
		for(int i : holes) {
			int cur = floor[i];
			// left
			for(int j = i - 1; j >= 0; j--) {
				if(floor[j] < cur) {
					water[j] = floor[j];
					cur = floor[j];
				}
				else if(water[j] <= cur) {
					water[j] = cur;
				}
			}
			
			cur = floor[i];
			//right
			for(int j = i + 1; j < len; j++) {
				if(floor[j] < cur) {
					water[j] = floor[j];
					cur = floor[j];
				}
				else if(water[j] <= cur) {
					water[j] = cur;
				}
			}
		}
		
		for(int i = 0; i < len; i++) {
			result += floor[i] - water[i];
		}
		sb.append(result);
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
