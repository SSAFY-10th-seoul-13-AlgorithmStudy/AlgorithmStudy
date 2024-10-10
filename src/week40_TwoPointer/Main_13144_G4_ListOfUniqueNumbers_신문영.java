package week40_TwoPointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main_13144_G4_ListOfUniqueNumbers_신문영 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		long answer = 0;
		Set<Integer> set = new HashSet<>();
		int left = 0;
		for (int right = 0; right < N; right++) {
			while (set.contains(arr[right])) {
				set.remove(arr[left]);
				answer += right - left;
				left++;
			}
			set.add(arr[right]);
		}
		
		long size = set.size();
		for (int i = 1; i <= size; i++) {
			answer += i;
		}
		
		System.out.println(answer);
	}
}
