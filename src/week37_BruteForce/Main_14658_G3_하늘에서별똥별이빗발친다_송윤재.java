package week37_BruteForce;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main_14658_G3_하늘에서별똥별이빗발친다_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N, M, L, K, stars[][];
	static Set<Integer> canX, canY;
	
	static void init() {
		stars = new int[K][2];
		canX = new HashSet<Integer>();
		canY = new HashSet<Integer>();
	}
		
	static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		init();
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()); 
			int y = Integer.parseInt(st.nextToken()); 
			stars[i][0] = x;
			stars[i][1] = y;
			canX.add(x);
			canY.add(y);
		}
	}

	static void solve() {
		int min = K;
		for(int x : canX) {
			for(int y : canY) {
				min = Math.min(min, countStar(x, y));				
			}
		}
		sb.append(min);
	}
	
	static int countStar(int x, int y) {
		int cnt = 0;
		for(int[] star : stars) {
			if(star[0] >= x && star[0] <= x + L && star[1] >= y && star[1] <= y + L) {				
				cnt++;				
			}
		}
		return K - cnt;
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
