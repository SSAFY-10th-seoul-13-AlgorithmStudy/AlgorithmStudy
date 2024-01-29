package week12_BinarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2473_G3_세용액_김태수 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		long[] line = new long[N];
		long answer = Long.MAX_VALUE;
		long num1 = 0;
		long num2 = 0;
		long num3 = 0;
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < N;i++) {
			line[i] = Long.parseLong(st.nextToken());
		}
		Arrays.sort(line);
		top:
		for(int i = 0; i < N-2;i++) {
			int left = i+1;
			int right = N-1;
			while(left < right) {
				long temp = line[i] + line[left] + line[right];
				if(Math.abs(temp) < answer) {
					answer = Math.abs(temp);
					num1 = line[i];
					num2 = line[left];
					num3 = line[right];
				}
				if(temp < 0) left++;
				else if(temp > 0)right--;
				else break top;
			}
		}
		StringBuilder sb = new StringBuilder();
		sb.append(num1).append(" ").append(num2).append(" ").append(num3).append(" ");
		System.out.println(sb);
	}
}
