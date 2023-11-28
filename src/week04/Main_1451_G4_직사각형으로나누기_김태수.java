package week04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1451_G4_직사각형으로나누기_김태수 {
	static long[][] sum;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int[][] map = new int[R+1][C+1];
		
		for(int i = 1; i <= R;i++) {
			String ss = br.readLine();
			for(int j = 0; j <C;j++) {
				map[i][j+1] = ss.charAt(j) - '0';
			}
		}
		long maxValue = -1;
		sum = new long[R+1][C+1];
		long rec1 = 0;
		long rec2 = 0;
		long rec3 = 0;
		
		for(int i = 1 ; i <= R;i++) {
			for(int j = 1; j <= C;j++) {
				sum[i][j] = sum[i][j-1] + sum[i-1][j] - sum[i-1][j-1] + (long)map[i][j];
			}
		}
		
		//세로만 자르기
		for(int i = 1 ; i < C-1;i++) {
			for(int j = i+1; j < C;j++) {
				rec1 = rec(1,1,R,i);
				rec2 = rec(1,i+1,R,j);
				rec3 = rec(1,j+1,R,C);
				maxValue = Math.max(maxValue, rec1*rec2*rec3);
			}
		}
		//가로로만 자르기
		for(int i=1;i<R-1;i++) {
			for(int j=i+1;j<R;j++) {
				rec1 = rec(1,1,i,C);
				rec2 = rec(i+1,1,j,C);
				rec3 = rec(j+1,1,R,C);
				maxValue = Math.max(maxValue, rec1*rec2*rec3);
			}
		}
		for(int i = 1;i<C;i++) {
			for(int j = 1; j<R;j++) {
				//ㅓ 형태로 자르기
				rec1 = rec(1,1,j,i);
				rec2 = rec(j+1,1,R,i);
				rec3 = rec(1,1+i,R,C);
				maxValue = Math.max(maxValue, rec1*rec2*rec3);
				//ㅏ 형태로 자르기
				rec1 = rec(1,1,R,i);
				rec2 = rec(1,i+1,j,C);
				rec3 = rec(j+1,i+1,R,C);
				maxValue = Math.max(maxValue, rec1*rec2*rec3);
			}
		}
		
		for(int i =1 ; i< R;i++) {
			for(int j = 1;j<C;j++) {
				// ㅗ 형태
				rec1 = rec(1,1,i,j);
				rec2 = rec(1,j+1,i,C);
				rec3 = rec(i+1,1,R,C);
				maxValue = Math.max(maxValue, rec1*rec2*rec3);
				
				// ㅜ 형태
				rec1 = rec(1,1,i,C);
				rec2 = rec(i+1,1,R,j);
				rec3 = rec(i+1,j+1,R,C);
				maxValue = Math.max(maxValue, rec1*rec2*rec3);
			}
		}
		
		System.out.println(maxValue);
	}
	
	public static long rec(int x1, int y1, int x2, int y2) {
		return sum[x2][y2] - sum[x2][y1-1] - sum[x1-1][y2] + sum[x1-1][y1-1];
	}
}
