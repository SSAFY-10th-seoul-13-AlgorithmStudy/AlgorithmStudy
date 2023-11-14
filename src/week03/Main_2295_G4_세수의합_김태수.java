package week03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_2295_G4_세수의합_김태수 {
	public static int N, answer, idx;
	public static int[] list, sum;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		list = new int[N];
		answer = 0;
		for(int i=0;i<N;i++) {
			list[i] = Integer.parseInt(br.readLine());
		}
		
		sum = new int[N*N];
		idx = 0;
		for(int i =0; i< N;i++) {
			for(int j = 0; j < N;j++) {
				sum[idx++] = list[i] + list[j]; 
			}
		}
		
		Arrays.sort(sum, 0, idx-1);
		
		for(int i = N - 1; i >= 0;i--) {
			for(int j = 0; j < i; j++) {
				int temp = list[i] - list[j];

				if(binarySearch(temp) && list[i] > answer) answer = list[i];
				
			}
		}
		System.out.println(answer);
	}
	
	public static boolean binarySearch(int target) {
		int start = 0;
		int end = idx - 1;
		int mid = 0;
		
		while(start < end) {
			mid = (start + end) / 2;
			if(sum[mid] < target) start = mid + 1;
			else if( sum[mid] > target) end = mid - 1;
			else if(sum[mid] == target) return true; 
		}
		return false;
	}
}
