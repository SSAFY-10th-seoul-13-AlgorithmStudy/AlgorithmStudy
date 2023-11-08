package week02;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_2812_G3_크게만들기_김아린 {
	static int N, K, nums[];

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		String s = in.readLine();
		int[] list = new int[N];

		for (int i = 0; i < N; i++) {
			char c = s.charAt(i);
			list[i] = c - '0';
		}

		Stack<Integer> stack = new Stack<>();
		int cnt = 0, i;

		top: for (i = 0; i < N; i++) {
			while (!stack.isEmpty() && stack.peek() < list[i]) {
				stack.pop();

				if (++cnt == K)
					break top;
			}
			stack.push(list[i]);
		}

		// 이미 내림차순이어서 pop을 못했을 경우
		while (cnt++ < K)
			stack.pop();


		StringBuilder sb = new StringBuilder();
		while (!stack.isEmpty()) {
			sb.insert(0, stack.pop());
		}
		// pop이 먼저 끝나 다 못 넣었을 경우
		for (int j = i; j < N; j++) {
			sb.append(list[j]);
		}

		System.out.println(sb);

	}
}
