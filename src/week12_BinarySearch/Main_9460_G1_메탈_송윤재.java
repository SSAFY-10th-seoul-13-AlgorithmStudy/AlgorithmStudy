package week12_BinarySearch;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main_9460_G1_메탈_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int n, k, p[][];
	
	static void init() {
		p = new int[n][2];
	}
	
	static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		init();
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			p[i][0] = Integer.parseInt(st.nextToken());
			p[i][1] = Integer.parseInt(st.nextToken());
		}
	}

	static void solve() {
		Arrays.sort(p, (o1, o2) -> o1[0] - o2[0]); // x좌표를 기준으로 정렬
		
		double start = 0;
		double end = 200_000_000;
		double mid = 0;
		
		while(0.01 < end - start) { // cost(P)를 이분탐색으로 찾아냄
			mid = (start + end) / 2;
			if(isPossible(mid))
				end = mid;
			else
				start = mid;
		}
		sb.append(String.format("%.1f", mid)).append("\n");
	}	
	
	static boolean isPossible(double cost) {
		int cnt = 1;
		int min = p[0][1]; // 해당 구간 y좌표의 최대값과 최소값
		int max = p[0][1];
		
		for(int i = 1; i < n; i++) {
			if(p[i][1] < min)
				min = p[i][1];
			if(p[i][1] > max)
				max = p[i][1];
			if((max - min) < 2 * cost)
				continue; // 현재 cost로 처리할 수 있을 때
			else { // 수평 터널을 추가해야 할 때
				min = max = p[i][1];
				cnt++;
			}
		}
		
		if(cnt > k) return false; // k개 보다 많이 필요하면 불가능
		else return true; // k개로 만들 수 있으면 가능
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
