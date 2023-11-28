package week04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14476_G2_최대공약수하나빼기_김태수 {
	public static void main(String[] args) throws IOException {
		//최대공약수 20년만에 봄
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] list = new int[N];
		
		for(int i = 0; i < N;i++) {
			list[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] rightSum = new int[N];
		int[] leftSum = new int[N];
		
		leftSum[0] = list[0];
		rightSum[N-1] = list[N-1];
		
		for(int i = 1; i<N;i++) {
			leftSum[i] = gcd(leftSum[i-1], list[i]);
		}
		for(int i = N-2;i>=0;i--) {
			rightSum[i] = gcd(rightSum[i+1],list[i]);
		}
		
		int maxValue = -1;
		int maxIdx = -1;
		
		for(int i = 0; i<N;i++) {
			int temp = 0;
			if(i==0) temp = rightSum[1];
			else if(i == N-1) temp = leftSum[N-2];
			else temp = gcd(leftSum[i-1], rightSum[i+1]);
			
			if(list[i] % temp != 0 && temp > maxValue) {
				maxValue = temp;
				maxIdx = i;
			}
		}
		StringBuilder sb = new StringBuilder();
		sb.append(maxValue)
		.append(maxIdx == -1 ? "" : " " + list[maxIdx]);
		System.out.println(sb);
	}
	public static int gcd(int a, int b) {
		if(b==0)return a;
		return gcd(b,a%b);
	}
	//https://darever.tistory.com/28?category=879301
}
