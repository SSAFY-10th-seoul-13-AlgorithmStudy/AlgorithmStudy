package week40_Sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main_1838_G1_버블정렬_신문영 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < N; i++) {
			map.put(arr[i], i);
		}
		
		Arrays.sort(arr);
		
		int answer = 0;
		for (int i = 0; i < N; i++) {
			int idx = map.get(arr[i]);
			answer = Math.max(answer, idx - i);
		}
		
		System.out.println(answer);
	}
}
