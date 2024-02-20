package week14_BruteForce;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_21943_G2_연산최대로_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N, X[], P, Q, max, numbers[];
	static boolean operator[], visited[];
	
	static void init() {
		X = new int[N];
		numbers = new int[N];
		operator = new boolean[N];
		visited = new boolean[N];
	}
	
	static void input() throws IOException {
		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		init();
		for(int i = 0; i < N; i++) {
			X[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		P = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
	}
	
	static void solve() {
		perm(0);
		sb.append(max);
	}
	
	static void perm(int cnt) { // 숫자들 배치
		if(cnt == N) {
//			System.out.println("만들어진 숫자 배치 : " + Arrays.toString(numbers));
			combi(0, 0);
			return;
		}
		for(int i = 0; i < N; i++) {
			if(visited[i]) continue;
			numbers[cnt] = X[i];
			visited[i] = true;
			perm(cnt + 1);
			visited[i] = false;
		}
	}
	
	static void combi(int cnt, int tempQ) { // 연산자 배치 및 연산
		if(tempQ == Q) { // 배치한 * 연산자가 충분할 때
			int sum = numbers[0];
			int temp = 1;
			List<Integer> list = new ArrayList<>();
			
			for(int i = 0; i < N - 1; i++) {
				if(!operator[i])
					// 더하기 연산자가 들어가야 할 때(무조건 괄호 안에 들어가는게 더 큰 숫자를 만들 수 있음)
					sum += numbers[i + 1];
				else { 
					// 곱하기 연산자가 들어가야 할 때(뒤에 올 숫자를 모르므로 list에 add 한 후 사후 처리 
					list.add(sum);
					sum = numbers[i + 1];
				}
			}
			if(sum != 0) list.add(sum); // 연산해야하는 숫자가 남았을 때
			
			for(int i : list) temp *= i;
			
			max = Math.max(max, temp);
			
			return;
		}
		if(cnt == N - 1) return;		
	
		operator[cnt] = true;
		combi(cnt + 1, tempQ + 1);
		operator[cnt] = false;
		
		combi(cnt + 1, tempQ);
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
