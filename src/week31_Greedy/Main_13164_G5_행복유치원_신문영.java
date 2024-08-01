package week31_Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main_13164_G5_행복유치원_신문영 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(stringTokenizer.nextToken());
		int K = Integer.parseInt(stringTokenizer.nextToken());
		
		int[] kids = new int[N];
		stringTokenizer = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			kids[i] = Integer.parseInt(stringTokenizer.nextToken());
		}
		
		List<Integer> diffs = new ArrayList<>();
		
		for (int i = 0; i < N - 1; i++) {
			diffs.add(kids[i + 1] - kids[i]);
		}
		
		Collections.sort(diffs);
		
		System.out.println(diffs.stream().limit(N - K).mapToInt(diff -> diff).sum());
	}
	
}
