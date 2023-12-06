package week06_DataStructure;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_3015_P5_오아시스재결합_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N;
	
	static void input_solve() throws IOException{
		N = Integer.parseInt(br.readLine());
		Stack<long[]> stack = new Stack<>(); // stack은 해당하는 키와 그 키를 갖고있는 사람의 수를 갖는다.
		long result = 0;
		
		stack.push(new long[] {Integer.parseInt(br.readLine()), 1}); // 첫번째 관객
		
		/**
		 * 새로운 관객이 들어왔을 때 마주칠 수 있는 상황을 2가지로 나눠본다.
		 * (공통적으로 해야할 것은 어찌됐든 stack에 들어가야한다.)
		 * 1. 현재 stack에 가장 윗 부분보다 키가 작은 경우
		 *   - stack 가장 윗 부분에 해당하는 사람과만 서로 볼 수 있다.
		 * 2. 현재 stack에 가장 윗 부분보다 키가 크거나 같은 경우
		 *   - stack 가장 윗 부분보다 키가 작아지거나 stack이 빌 때 까지 pop을 해줘야한다.
		 *   - pop이 끝났음에도 stack이 비지 않았다면 stack 윗 부분에 해당하는 사람과 마주볼 수 있다.
		 */
		for(int i = 0, end = N - 1; i < end; i++) {
			int cur = Integer.parseInt(br.readLine());
			if(stack.peek()[0] > cur){
				stack.push(new long[] {cur, 1});
				result++;
			}			
			else{
				int cnt = 1;
				while(!stack.isEmpty() && stack.peek()[0] <= cur) {
					long[] peek = stack.peek();
					if(peek[0] == cur) cnt += peek[1]; // 키가 같은 관객은 한번에 처리를 해줘야한다.
					result += stack.pop()[1];
				}
				if(!stack.isEmpty()) result++;
				stack.push(new long[] {cur, cnt});
			}
		}
		
		sb.append(result);
	}
	
	public static void main(String[] args) throws IOException{
		input_solve();
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
