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

public class Main_14391_G3_종이조각_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N, M, map[][], max;
	static boolean isVertical[][];
	
	static void init() {
		map = new int[N][M];
		isVertical = new boolean[N][M];
	}
	
	static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		init();
		for(int i = 0; i < N; i++) {
			String str = br.readLine();
			for(int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}
	}

	static void solve() {
		dfs(0, 0);
		sb.append(max);
	}
	
	static void dfs(int r, int c) {
		if(c == M) {
			c = 0;
			r++;
		}
		if(r == N) {
			check();
			return;
		}
		// 각 칸은 세로 또는 가로 조각일 수 밖에 없다.
		isVertical[r][c] = true;
		dfs(r, c + 1);
		isVertical[r][c] = false;
		dfs(r, c + 1);
	}
	
	static void check() {
		int sum = 0, tmp = 0;

        // 가로
        for (int i = 0; i < N; i++) {
            tmp = 0;
            for (int j = 0; j < M; j++) {
                if (!isVertical[i][j]) {
                    tmp *= 10;
                    tmp += map[i][j];
                } else {
                    sum += tmp;
                    tmp = 0;
                }
            }
            sum += tmp;
        }

        // 세로
        for (int j = 0; j < M; j++) {
            tmp = 0;
            for (int i = 0; i < N; i++) {
            	if (isVertical[i][j]) {
                    tmp *= 10;
                    tmp += map[i][j];
                } else {
                    sum += tmp;
                    tmp = 0;
                }
            }
            sum += tmp;
        }

        // 최대값 갱신
        max = Math.max(max, sum);
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
