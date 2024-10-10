package week41_Implementation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_2064_G3_IP주소_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N, IP[][];
	
	static void init() {
		IP = new int[N][4];
	}
		
	static void input() throws IOException {
		N = Integer.parseInt(br.readLine());
		init();
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), ".");
			for(int j = 0; j < 4; j++) {
				IP[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}

	static void solve() {
		int[] m = new int[] {4, 0};
		int[] address = new int[] {0, 0, 0, 0};
		int[] mask = new int[] {0, 0, 0, 0};
		
		top:
		for(int j = 0; j < 4; j++) {
			for(int k = 7; k >= 0; k--) {
				for(int i = 0; i < N - 1; i++) {
					if((IP[i][j] & (1 << k)) != (IP[i + 1][j] & (1 << k))) {
						m = new int[] {j, k};
						break top;
					}
				}
			}
		}
		
		for(int i = 0; i < 4; i++) {
			if(i < m[0]) {
				address[i] = IP[0][i];
				mask[i] = 255;
			} else if(i == m[0]) {
				for(int j = 7; j > m[1]; j--) {
					address[i] += (IP[0][i] & (1 << j));
					mask[i] += (1 << j);
				}
			} else {
				address[i] = 0;
				mask[i] = 0;
			}
		}
		
		sb.append(address[0]).append(".").append(address[1]).append(".").append(address[2]).append(".").append(address[3]).append("\n");
		sb.append(mask[0]).append(".").append(mask[1]).append(".").append(mask[2]).append(".").append(mask[3]);
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
