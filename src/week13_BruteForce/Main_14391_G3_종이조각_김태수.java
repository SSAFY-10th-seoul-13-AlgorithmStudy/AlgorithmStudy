package week13_BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14391_G3종이조각_김태수 {
	 public static void main(String[] args) throws IOException {
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 StringTokenizer st = new StringTokenizer(br.readLine());
		 
		 int N = Integer.parseInt(st.nextToken());
		 int M = Integer.parseInt(st.nextToken());
		 int[][] map = new int[N][M];
		 
		 for(int i = 0 ; i < N ;i++) {
			 String s = br.readLine();
			 for(int j = 0 ; j < M ;j++) {
				 map[i][j] = s.charAt(j) - '0';
			 }
		 }
		 
		 int answer = 0;
		 int size = N * M;
		 for(int t =0; t < (1<<size);t++) {
			 int sum = 0;
			 
			 for(int i = 0 ; i < N;i++) {
				 int cur = 0;
				 for(int j = 0 ; j < M;j++) {
					 int k = i * M + j;
					 if((t&(1<<k))==0) {
						 cur *= 10;
						 cur += map[i][j];
					 }
					 else {
						 sum += cur;
						 cur = 0;
					 }
				 }
				 sum += cur;
			 }
			 
			 for(int j = 0 ; j < M ;j++) {
				 int cur = 0;
				 for(int i = 0; i< N;i++) {
					 int k = i*M +j;
					 if((t&(1<<k)) != 0) {
						 cur *= 10;
						 cur += map[i][j];
					 }
					 else {
						 sum += cur;
						 cur = 0;
					 }
				 }
				 sum += cur;
			 }
			 answer = Math.max(answer, sum);
		 }
		 System.out.println(answer);
	}
}
