package week06_DataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main_5430_G5_AC_김희연 {
	static StringBuilder sb = new StringBuilder();
	static Deque<String> deque = new ArrayDeque<>();
	static char[] p;
	static boolean isLeft = true;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		while (T-- > 0) {
			p = br.readLine().toCharArray();

			int n = Integer.parseInt(br.readLine());

			String str = br.readLine();
			String[] nums = str.substring(1, str.length() - 1).split(",");

			deque = new ArrayDeque<>();

			for (int i = 0; i < n; i++) {
				deque.add(nums[i]);
			}

			isLeft = true;

			if(AC())
				makePrintString();
			else
				sb.append("error").append("\n");
		}

		System.out.println(sb);
	}

	public static boolean AC(){
		for (char command : p) {
			if (command == 'R') {
				isLeft = !isLeft;
			} else {
				if (deque.isEmpty()) {
					return false;
				}
				if (isLeft) {
					deque.pollFirst();
				} else
					deque.pollLast();
			}
		}
		return true;
	}

	public static void makePrintString(){
		sb.append("[");
		if (!deque.isEmpty()) {

			if (isLeft) {
				sb.append(deque.pollFirst());

				while(!deque.isEmpty()){
					sb.append("," + deque.pollFirst());
				}
			} else{
				sb.append(deque.pollLast());

				while(!deque.isEmpty()){
					sb.append("," + deque.pollLast());
				}
			}
		}
		sb.append("]").append("\n");
	}
}
