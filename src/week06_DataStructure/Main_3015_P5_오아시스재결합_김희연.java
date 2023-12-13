package week06_DataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main_3015_P5_오아시스재결합_김희연 {
	public static class Height{
		int height;
		int cnt;

		public Height(int height, int cnt){
			this.height = height;
			this.cnt = cnt;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		Stack<Height> stack = new Stack<>();

		long answer = 0;
		for(int i=0; i<N; i++){
			int cur = Integer.parseInt(br.readLine());
			Height height = new Height(cur, 1);
			while(!stack.isEmpty() && stack.peek().height <= cur){
				Height pop = stack.pop();
				answer += pop.cnt;
				if(pop.height == cur)
					height.cnt += pop.cnt;
			}

			if(!stack.isEmpty()) answer++;

			stack.push(height);
		}
		System.out.println(answer);
	}
}