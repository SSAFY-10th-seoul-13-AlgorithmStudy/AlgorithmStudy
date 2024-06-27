package week29_Math;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_15791_G1_세진이의미팅_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static final int MOD = 1_000_000_007;
	static int N, M;
		
	static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
	}

	static void solve() {
		long a = 1;
		long b = 1;
		for(int i = 0; i < M; i++) {
			a = (a * (N - i)) % MOD;
		}
		
		for(int i = 2; i <= M; i++) {
			b = (b * i) % MOD;
		}
		// a/b = a * b^(p-2) mod p
		
		long b2 = 1;
		int exp = MOD - 2;
		
		while(exp > 0) {
            if(exp % 2 > 0) {
                b2 = (b2 * b) % MOD; // 홀수인 경우 1/2 할 때 버려지므로 미리 계산
            }

            b = (b * b) % MOD; // 제곱을 만들어 줌으로 연산을 줄임
            exp /=2;
        }
		
		long result = (a * b2) % MOD;
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
