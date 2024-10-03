package week39_DataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_1863_G4_스카이라인쉬운거_신문영 {
    public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int answer = 0;
		Stack<Integer> stack = new Stack<>();
		stack.push(0);
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			while (y < stack.peek()) {
				stack.pop();
				answer++;
			}
			
			if (y > stack.peek()) {
				stack.push(y);
			}
		}
		
		while (0 < stack.peek()) {
			stack.pop();
			answer++;
		}
		
		System.out.println(answer);
    }
}