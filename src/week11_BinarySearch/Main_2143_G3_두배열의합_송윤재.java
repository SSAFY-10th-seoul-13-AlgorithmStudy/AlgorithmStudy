package week11_BinarySearch;

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

public class Main_2143_G3_두배열의합_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int T, n, m, prefixSumA[], prefixSumB[];
	
	static void input() throws IOException {
		T = Integer.parseInt(br.readLine());
		
		n = Integer.parseInt(br.readLine());
		prefixSumA = new int[n + 1];
		st = new StringTokenizer(br.readLine());
		prefixSumA[1] = Integer.parseInt(st.nextToken());
		for(int i = 1; i < n; i++) 
			prefixSumA[i + 1] = prefixSumA[i] + Integer.parseInt(st.nextToken());
		
		m = Integer.parseInt(br.readLine());
		prefixSumB = new int[m + 1];
		st = new StringTokenizer(br.readLine());
		prefixSumB[1] = Integer.parseInt(st.nextToken());
		for(int i = 1; i < m; i++)
			prefixSumB[i + 1] = prefixSumB[i] + Integer.parseInt(st.nextToken());
	}

	static void solve() {
		int sizeA = (n * (n + 1)) / 2;
		int sizeB = (m * (m + 1)) / 2;
		
		int subA[] = new int[sizeA];
		int subB[] = new int[sizeB];
		
		int index = 0;
		for(int i = n; i > 0; i--) {
			for(int j = 0; j < i; j++) {
				subA[index++] = prefixSumA[i] - prefixSumA[j]; 
			}
		}
		
		index = 0;
		for(int i = m; i > 0; i--) {
			for(int j = 0; j < i; j++) {
				subB[index++] = prefixSumB[i] - prefixSumB[j]; 
			}
		}
		
		Arrays.sort(subA);
		Arrays.sort(subB);
		
		long result = 0;
		
		int left = 0;
		int right = sizeB - 1;
		
		while(left < sizeA && right >=0) {
			long nowA = subA[left];
			long nowB = subB[right];
			long sum = subA[left] + subB[right];
			
			if(sum == T) {
				long a = 0;
				long b = 0;
				while (left < sizeA &&  nowA == subA[left]) {
					a++;
					left++;
				}
				while (right >= 0 && nowB == subB[right]) {
					b++;
					right--;
				}
				result += a * b;
			}
			else if(sum > T) {
				right--;
			}
			else {
				left++;
			}
		}
		sb.append(result);
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
