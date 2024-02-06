package week13_BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14391_G3_종이조각_김희연 {
	static int n, m;
	static int[][] arr;
	static boolean[][] visit;
	static int answer = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new int[n][m];
		visit = new boolean[n][m];

		for(int i=0; i<n; i++){
			String str = br.readLine();
			for(int j=0; j<m; j++){
				arr[i][j] = str.charAt(j) - '0';
			}
		}

		dfs(0, 0);
		System.out.println(answer);
	}

	public static void dfs(int depth, int sum){
		if(depth == n*m){
			answer = Math.max(sum, answer);
			return;
		}

		int x = depth / m;
		int y = depth % m;

		if(visit[x][y])
			dfs(depth+1, sum);
		else {
			//현재 위치 계산
			int num = 0;
			visit[x][y] = true;
			num = arr[x][y];
			dfs(depth+1, sum+num);

			//세로로 자르는 경우
			int i, temp = num;
			for(i=x+1; i<n; i++){
				if(visit[i][y])
					break;
				visit[i][y] = true;
				temp = temp*10 + arr[i][y];
				dfs(depth+1, sum+temp);
			}

			for(int j=x+1; j<i; j++)
				visit[j][y] = false;

			//가로로 자르는 경우
			temp = num;
			for(i=y+1; i<m; i++){
				if(visit[x][i])
					break;
				visit[x][i] = true;
				temp = temp*10 + arr[x][i];
				dfs(depth+i-y+1, sum+temp);
			}

			for(int j=y+1; j<i; j++)
				visit[x][j] = false;

			//현재 위치도 되돌리기
			visit[x][y] = false;
		}
	}
}
