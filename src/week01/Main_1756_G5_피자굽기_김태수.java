package week01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1756_G5_피자굽기_김태수 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int D = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int[] list = new int[D+1];
		int bottom = D + 1;
		int min = Integer.MAX_VALUE;
		boolean flag = false;
		st = new StringTokenizer(br.readLine());
		for(int i = 1 ; i <= D;i++) {
			min = Math.min(Integer.parseInt(st.nextToken()), min);
			list[i] = min;
		}
		int[] pizza = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N;i++) {
			pizza[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 0 ; i < N;i++) {
			bottom--;
			while (list[bottom] < pizza[i]) {
				if(bottom == 0) {
					flag = true;
					break;
				} 
				bottom--;
			}
			if(flag) break;
		}
		System.out.println(bottom);
	}
}
