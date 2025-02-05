package week56_Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_9576_G2_책나눠주기_신문영 {
	static int[] parents;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int t = Integer.parseInt(st.nextToken());
		for (int i = 0; i < t; i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			
			Queue<Pair> requestQueue = new PriorityQueue<>();
			int[] books = new int[n];
			for (int j = 0; j < m; j++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken()) - 1;
				int b = Integer.parseInt(st.nextToken()) - 1;
				requestQueue.add(new Pair(a, b));
			}
			
			int answer = 0;
			while (!requestQueue.isEmpty()) {
				Pair request = requestQueue.poll();
				for (int j = request.start; j <= request.end; j++) {
					if (books[j] == 0) {
						answer += 1;
						books[j] += 1;
						break;
					}
				}
			}
			
			System.out.println(answer);
		}
	}
	
	static class Pair implements Comparable<Pair> {
		int start;
		int end;
		
		Pair (int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(Pair o) {
			if (this.end == o.end) {
				return Integer.compare(this.start, o.start);
			}
			return Integer.compare(this.end, o.end);
		}
		
	}
}
