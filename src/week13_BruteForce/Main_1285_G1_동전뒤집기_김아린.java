package week13_BruteForce;

import java.io.*;

public class Main_1285_G1_동전뒤집기_김아린 {
	static int N;
	static char[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = s.charAt(j);
			}
		}
		
		int answer = Integer.MAX_VALUE; // 최소 동전 뒤집기 횟수
		
		for (int bit = 1; bit < (1 << N); bit++) { // 모든 행에 대해 동전을 뒤집을지 말지를 결정하는 비트마스크
			int sum = 0; // 각 열마다의 최소합
			for (int j = 0; j < N; j++) {
				int back = 0; // 각 열마다 뒷면의 개수 
				for (int i = 0; i < N; i++) {
					char curr = map[i][j]; // 현재 동전의 상태
					if ((bit & (1 << i)) != 0) { // 뒤집을까 말까?
						curr = reverse(curr); // 뒤집기
					}
					if (curr == 'T') // 뒷면이 나오면?
						back++;
				}
				sum += Math.min(back, N - back); // 동전을 뒤집는 최소 횟수 합산 
			}
			answer = Math.min(answer, sum); // 동전을 뒤집는 최소 횟수
		}
		System.out.println(answer);
	}

	// 뒤집는 함수
	public static char reverse(char curr) {
		return curr == 'T' ? 'H' : 'T';
	}
}