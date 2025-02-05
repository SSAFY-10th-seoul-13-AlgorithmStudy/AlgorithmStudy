package week56_Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Main_25381_G3_ABBC_신문영 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		
		int answer = 0;
		ArrayDeque<Integer> a = new ArrayDeque<>();
		ArrayDeque<Integer> b = new ArrayDeque<>();
		for (int i = 0; i < input.length(); i++) {
			char current = input.charAt(i);
			if (current == 'A') {
				a.add(i);
			} else if (current == 'B') {
				b.add(i);
			} else {
				if (b.size() > 0) {
					b.poll();
					answer++;
				}
			}
		}
		
		while (!a.isEmpty() && !b.isEmpty()) {
			int aIndex = a.peek();
			int bIndex = b.peek();
			if (bIndex > aIndex) {
				a.poll();
				b.poll();
				answer++;
			} else {
				b.poll();
			}
		}

		System.out.println(answer);
	}
}
