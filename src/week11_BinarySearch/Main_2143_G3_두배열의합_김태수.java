package week11_BinarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2143_G3_두배열의합_김태수 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		long T = Long.parseLong(br.readLine());
		int temp = 0;
		int A = Integer.parseInt(br.readLine());
		int[] listA = new int[A];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i <A;i++) {
			temp = Integer.parseInt(st.nextToken());
			listA[i] = temp;
		}
		int B = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		int[] listB = new int[B];
		for(int i = 0; i < B;i++) {
			temp = Integer.parseInt(st.nextToken());
			listB[i] = temp;
		}
		int aSize = (A+1) * A / 2;
		int bSize = (B+1) * B / 2;
		long[] sumA = new long[aSize];
		long[] sumB = new long[bSize];
		int idx = 0;
		for(int i = 0;i < A;i++) {
			temp = 0;
			for(int j = i; j <A;j++) {
				temp += listA[j];
				sumA[idx++] = temp;
			}
		}
		idx = 0;
		for(int i = 0; i < B;i++) {
			temp = 0;
			for(int j = i;j < B;j++) {
				temp += listB[j];
				sumB[idx++] = temp;
			}
		}
		Arrays.sort(sumA);
		Arrays.sort(sumB);
		int left = 0;
		int right = bSize - 1;
		long count = 0;
		
		while(left < aSize && right >=0) {
			long nowA = sumA[left];
			long nowB = sumB[right];
			long sum = sumA[left] + sumB[right];
			
			if(sum == T) {
				long a = 0;
				long b = 0;
				while (left < aSize &&  nowA == sumA[left]) {
					a++;
					left++;
				}
				while (right >= 0 && nowB == sumB[right]) {
					b++;
					right--;
				}
				count += a * b;
			}
			else if(sum > T) {
				right--;
			}
			else {
				left++;
			}
		}
		System.out.println(count);
	}
}
