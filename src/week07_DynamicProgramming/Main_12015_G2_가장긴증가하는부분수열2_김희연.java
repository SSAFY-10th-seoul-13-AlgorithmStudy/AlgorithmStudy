package week07_DynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_12015_G2_가장긴증가하는부분수열2_김희연 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		int[] A = new int[N];
		int[] dp = new int[N];

		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++){
			A[i] = Integer.parseInt(st.nextToken());
		}

		dp[0] = A[0];
		int length = 1;

		for(int i=1; i<N; i++){

			int key = A[i];

			if(dp[length-1] < key){
				dp[length] = key;
				length++;
			} else{
				int start = 0;
				int end = length;
				while(start < end){
					int mid = (start + end) >>> 1;

					if(dp[mid] < key){
						start = mid + 1;
					} else{
						end = mid;
					}
				}
				dp[start] = key;
			}
		}

		System.out.println(length);
	}
}
