package week13_BruteForce;

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

public class Main_1285_G1_동전뒤집기_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N, min;
	static char[][] coins;
	
	static void init() {
		coins = new char[N][N];
		min = Integer.MAX_VALUE;
	}
	
	static void input() throws IOException {
		N = Integer.parseInt(br.readLine());
		init();
		for(int i = 0; i < N; i++) {
			String str = br.readLine();
			for(int j = 0; j < N; j++) {
				coins[i][j] = str.charAt(j);
			}
		}
	}

	static void solve() {
		for (int bit = 0; bit < (1 << N); bit++) { //어떤 행끼리 뒤집어 볼지 비트 마스킹으로 조합을 결정한다.
            int sum = 0;
            for (int i = 0; i < N; i++) {
                int oppo = 0;
                for (int j = 0; j < N; j++) {
                    char temp = coins[i][j];
                    if ((bit & (1 << j)) != 0) { // i행에 해당하는 j열이라면 뒤집는다.
                        temp = (temp ==  'T') ? 'H' : 'T';
                    }
                    if (temp == 'T') oppo++;
                }
                sum += Math.min(oppo, N - oppo); //뒤집어본 것과 아닌 것 중에 가장 작은 값을 저장한다.
            }
            min = Math.min(min, sum);
        }
		sb.append(min);
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
