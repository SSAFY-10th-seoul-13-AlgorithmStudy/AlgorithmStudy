package week10_BackTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_15684_G3_사다리조작_김희연 {
	static int n, m, h, answer;
	static int[][] ladder;
	static boolean finish = false;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());

		ladder = new int[h+1][n+1];

		for(int i=0; i<m; i++){
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			ladder[a][b] = 1;
			ladder[a][b+1] = -1;
		}

		for(int i=0; i<=3; i++){
			answer = i;
			dfs(1, 0);
			if(finish) break;
		}

		System.out.println((finish) ? answer : -1);
	}

	public static void dfs(int x, int cnt){
		if(finish) return;
		if(answer == cnt){
			if(check()) {
				finish = true;
			}
			return;
		}

		for(int i=x; i<h+1; i++){
			for(int j=1; j<n; j++){
				if(ladder[i][j] == 0 && ladder[i][j+1] == 0){
					ladder[i][j] = 1;
					ladder[i][j+1] = -1;
					dfs(i, cnt+1);
					ladder[i][j] = 0;
					ladder[i][j+1] = 0;
				}
			}
		}
	}

	public static boolean check(){
		for(int i=1; i<=n; i++){
			int x=1;
			int y=i;
			for(int j=0; j<h; j++){
				if(ladder[x][y] == 1) y++;
				else if (ladder[x][y] == -1) y--;
				x++;
			}
			if(i != y)
				return false;
		}
		return true;
	}
}
