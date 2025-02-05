package week51_Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main_24337_G3_가희와탑_신문영 {
	static int[] sum;
	static boolean[] isModified;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		
		ArrayDeque<Integer> queue = new ArrayDeque<>();
		int push = 1;
		while (push < a) {
			queue.add(push);
			push++;
		}
		
		push = Math.max(a, b);
		queue.add(push);
		
		push = b - 1;
		while (push > 0) {
			queue.add(push);
			push--;
		}

		if (queue.size() > N) {
			System.out.println(-1);
			return;
		}
		
		if (a != 1) {
			while (queue.size() < N) {
				queue.addFirst(1);
			}
		} else {
			int pollFirst = queue.pollFirst();
			while (queue.size() < N - 1) {
				queue.addFirst(1);
			}
			queue.addFirst(pollFirst);
		}
		
		StringBuilder stringBuilder = new StringBuilder();
		while (!queue.isEmpty()) {
			stringBuilder.append(queue.poll()).append(" ");
		}
		
		System.out.println(stringBuilder);
	}
}
