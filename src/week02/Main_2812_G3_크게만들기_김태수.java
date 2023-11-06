package week02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_2812_G3_크게만들기_김태수 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		//숫자 String을 int[]로 만드는 방법
		char[] target = br.readLine().toCharArray();
		int[] list = new int[N];
		for(int i =0 ; i < N;i++) {
			list[i] = target[i] - '0';
		}
		
		Stack<Integer> stk = new Stack<>();
		int count = 0;
		int i;
		top:
		for(i = 0; i < N;i++) {
			//자기보다 큰 숫자가 있을때까지 밀어낸다
			while(!stk.isEmpty() && stk.peek() < list[i]) {
				stk.pop();
				//pop count가 다 차면 아예 종료
				if(++count == K) break top;
			}
			//자신보다 큰 숫자를 만났다면 푸시
			stk.push(list[i]);
		}
		//숫자가 완성 되었지만, pop이 남아있을 경우
		while( count++ < K) stk.pop();
		StringBuilder sb = new StringBuilder();
		//stk을 sb로 옮기기
		for(int j=0, end=stk.size();j<end;j++) sb.insert(0, stk.pop());
		//만약 list가 끝나기 전에 pop을 다해서 list를 나왔다면, 남은 숫자는 무지성 더해주기
		for(int j= i; j < N;j++) sb.append(list[j]);
		System.out.println(sb.toString());
	}
}
