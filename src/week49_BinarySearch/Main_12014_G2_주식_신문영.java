package week49_BinarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_12014_G2_주식_신문영 {
	static int length;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		for (int i = 1; i <= T; i++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			int[] LIS = new int[N];
			Arrays.fill(LIS, Integer.MAX_VALUE);
			
			int answer = 0;
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int stockPrice = Integer.parseInt(st.nextToken());
				int insertionPoint = Arrays.binarySearch(LIS, stockPrice);
				int idx = - insertionPoint - 1;
				if (idx < 0) continue;
				LIS[idx] = stockPrice;
				answer = Math.max(answer, idx);
			}
			
			System.out.println("Case #" + i);
			System.out.println(answer + 1 >= K ? 1 : 0);
		}
	}
}
