package week12_BinarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2473_G3_세용액_김희연 {
	static long[] arr;
	static long[] liquid = new long[3];
	static long max = Long.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());

		arr = new long[n];
		st = new StringTokenizer(br.readLine());

		for(int i=0; i<n; i++){
			arr[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr);

		for(int i=0; i<n-2; i++)
			binarySearch(i); //index, left, right 순일 경우가 마지막이기 때문에 n-3, n-2, n-1 이므로 n-3까지 돌린다.

		for(int i=0; i<3; i++)
			System.out.print(liquid[i] + " ");
	}

	public static void binarySearch(int index){
		int left = index+1;
		int right = arr.length-1;

		while(left < right){
			long sum = arr[left] + arr[right] + arr[index];
			long abs = Math.abs(sum);

			if(abs < max){
				liquid[0] = arr[index];
				liquid[1] = arr[left];
				liquid[2] = arr[right];
				max = abs;
			}

			if(sum < 0)
				left++;
			else
				right--;
		}
	}
}