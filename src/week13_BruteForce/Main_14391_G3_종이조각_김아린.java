package week13_BruteForce;

import java.util.*;
import java.io.*;

public class Main_14391_G3_종이조각_김아린 {
	static int N, M;
	static int[][] p;
	static boolean[][] visit;
	static int answer = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); 
		M = Integer.parseInt(st.nextToken()); 
		p = new int[N][M]; 
		visit = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			for (int j = 0; j < M; j++)
				p[i][j] = input.charAt(j) - '0';
		}

		DFS(0, 0, 0); 
		System.out.println(answer);
	}

	static void DFS(int depth, int num, int sum) {
		if (depth == N * M) { // 모든 칸을 다 확인했으면
			answer = Math.max(sum, answer); // 갱신
			return;
		}

		int r = depth / M; // 현재 확인할 칸의 행 좌표
		int c = depth % M; // 현재 확인할 칸의 열 좌표

		// 이미 방문한 칸이면 다음 칸으로 넘어감
		if (visit[r][c]) {
			DFS(depth + 1, num, sum);
		} else {
			visit[r][c] = true;
			num = num * 10 + p[r][c];
			DFS(depth + 1, 0, sum + num);

			int i, temp = num;
			// 같은 열에서 아래로 내려가며 확인
			for (i = r + 1; i < N; i++) {
				if (visit[i][c])
					break;

				visit[i][c] = true;
				temp = temp * 10 + p[i][c];
				DFS(depth + 1, 0, sum + temp);
			}
			// 방문했던 칸 방문 표시 취소
			for (int j = r + 1; j < i; j++)
				visit[j][c] = false;

			temp = num;
			// 같은 행에서 오른쪽으로 이동하며 확인
			for (i = c + 1; i < M; i++) {
				if (visit[r][i])
					break;
				visit[r][i] = true;
				temp = temp * 10 + p[r][i];
				DFS(depth + i - c + 1, 0, sum + temp);
			}
			// 방문했던 칸 방문 표시 취소
			for (int j = c + 1; j < i; j++)
				visit[r][j] = false;

			visit[r][c] = false;
		}
	}
}
