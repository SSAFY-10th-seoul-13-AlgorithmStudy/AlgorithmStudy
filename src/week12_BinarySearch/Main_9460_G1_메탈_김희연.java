package week12_BinarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main_9460_G1_메탈_김희연 {
	static class Pair {
		public int first;
		public int second;

		public Pair(int first, int second) {
			this.first = first;
			this.second = second;
		}
	}

	static int n, k;
	static Pair[] metal;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int t = Integer.parseInt(br.readLine());

		for (int tc = 0; tc < t; tc++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());

			metal = new Pair[n];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {

				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				metal[i] = new Pair(x, y);
			}

			Arrays.sort(metal, Comparator.comparing(p -> p.first));

			double bottom = 0, top = 200000001, answer = 0;

			while (0.01 < top - bottom) {
				answer = (bottom + top) / 2;

				if (isPossible(answer))
					top = answer;
				else
					bottom = answer;
			}

			sb.append(String.format("%.1f", answer)).append("\n");
		}

		System.out.println(sb);
	}

	static boolean isPossible(double X) {
		int cnt = 1;
		int minY, maxY;

		minY = maxY = metal[0].second;

		for (int i = 1; i < n; i++) {
			if (metal[i].second < minY)
				minY = metal[i].second;
			if (metal[i].second > maxY)
				maxY = metal[i].second;

			if (2 * X >= (maxY - minY))
				continue;
			else {
				minY = maxY = metal[i].second;
				cnt += 1;
			}
		}

		return cnt <= k;
	}
}