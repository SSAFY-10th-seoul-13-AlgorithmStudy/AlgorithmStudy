package week11_BinarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2143_G3_두배열의합_김희연 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		long t = Long.parseLong(br.readLine());

		int n = Integer.parseInt(br.readLine());
		int[] a = new int[n];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++){
			a[i] = Integer.parseInt(st.nextToken());
		}

		int m = Integer.parseInt(br.readLine());
		int[] b = new int[m];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<m; i++){
			b[i] = Integer.parseInt(st.nextToken());
		}

		for(int i=1; i<n; i++){
			a[i] += a[i-1];
		}

		for(int i=1; i<m; i++){
			b[i] += b[i-1];
		}

		int aSize = n*(n+1)/2;
		int bSize = m*(m+1)/2;

		long[] aSum = new long[aSize];
		int index=0;
		for(int i=0; i<n; i++){
			for(int j=i; j<n; j++){
				int num = a[j];
				if(i > 0) num -= a[i-1];
				aSum[index++] = num;
			}
		}

		long[] bSum = new long[bSize];
		index = 0;
		for(int i=0; i<m; i++){
			for(int j=i; j<m; j++){
				int num = b[j];
				if(i > 0) num -= b[i-1];
				bSum[index++] = num;
			}
		}

		Arrays.sort(aSum);
		Arrays.sort(bSum);
		int start = 0;
		int end = bSize-1;
		long cnt = 0;
		while(start < aSize && end > -1){
			long num1 = aSum[start];
			long num2 = bSum[end];
			long sum = num1 + num2;
			if(sum == t){
				long ac = 0, bc = 0;
				while(start < aSize && num1 == aSum[start]){
					start++;
					ac++;
				}

				while(end > -1 && num2 == bSum[end]){
					end--;
					bc++;
				}

				cnt += ac * bc;
			} else if(sum > t){
				end--;
			} else{
				start++;
			}
		}

		System.out.println(cnt);
	}
}