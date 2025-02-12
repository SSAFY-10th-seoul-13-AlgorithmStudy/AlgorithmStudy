package week58_DynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2352_G2_반도체설계_신문영 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n]; 
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < arr.length; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] LIS = new int[n];
		Arrays.fill(LIS, Integer.MAX_VALUE);
		
		int maxIndex = Integer.MIN_VALUE;
		for (int i = 0; i < arr.length; i++) {
			int index = Arrays.binarySearch(LIS, arr[i]);
			index = -index - 1;
			LIS[index] = arr[i];
			maxIndex = Math.max(maxIndex, index);
		}
		
		System.out.println(maxIndex + 1);
	}
}