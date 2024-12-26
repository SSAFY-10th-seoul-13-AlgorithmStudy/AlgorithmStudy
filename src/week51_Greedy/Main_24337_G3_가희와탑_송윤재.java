package week51_Greedy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_24337_G3_가희와탑_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N, a, b, building[];
	
	static void init() {
		building = new int[N + 1];
		
		for(int i = 1; i <= N; i++) {
			building[i] = 1;
		}
	}
	
	static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());
		init();
	}	
	
	static void solve() {
		if(a + b > N + 1) {
			sb.append(-1);
			return;
		}
		if(a == 1) {
			building[1] = b;
			for(int i = 0; i < b - 1; i++) {
				building[N - i] = i + 1;
			}
		}
		else if(a > b) {
			for(int i = 0; i < b - 1; i++) {
				building[N - i] = i + 1;
			}
			for(int i = 0; i < a; i++) {
				building[N - b + 1 - i] = a - i;
			}
		} else {
			for(int i = 0; i < b; i++) {
				building[N - i] = i + 1;
			}
			for(int i = 0; i < a - 1; i++) {
				building[N - b - i] = a - 1 - i;
			}
		}
		
		for(int i = 1; i <= N; i++) {
			sb.append(building[i]).append(" ");
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
