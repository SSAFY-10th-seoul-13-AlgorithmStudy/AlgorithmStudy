package week41_BackTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_2661_G4_좋은수열_신문영 {
	static StringBuilder answer;
	static int N;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		answer = new StringBuilder("1");

		dfs(1);

		System.out.println(answer);
	}
	
	public static boolean dfs(int n) {
		if (n == N) return true;
		
		for (int i = 1; i <= 3; i++) {
			answer.append(i);
			
			int length = answer.length();
			int maxSize = length / 2;
			
			boolean isDuplicated = false;
			loop:
			for (int size = 1; size <= maxSize; size++) {
				int end = length - size;
				for (int start = 0; start < end; start++) {
					if (start + size + size <= length) {
						String first = answer.substring(start, start + size);
						String second = answer.substring(start + size, start + size + size);
						
						if (first.equals(second)) {
							isDuplicated = true;
							break loop;
						}
					}
				}
			}
			
			if (isDuplicated) {
				answer.delete(length - 1, length);
				continue;
			} else {
				if (dfs(n + 1)) {
					return true;
				} else {
					answer.delete(length - 1, length);
					continue;
				}
			}
		}
		
		return false;
	}
}