package week10_BackTracking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_16638_G1_괄호추가하기2_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N, max;
	static String str;
	

	static void input() throws IOException {
		N = Integer.parseInt(br.readLine());
		str = br.readLine();
	}
	
	static void solve() {
		max = Integer.MIN_VALUE;
		boolean selected[] = new boolean[N];
		subset(1, selected); // 부분집합을 통해 괄호 씌울 연산자를 찾음
		sb.append(max);
	}
	
	static void subset(int idx, boolean selected[]) {
		if(idx >= N) {
			int temp = 0; // 괄호가 추가될 때마다 인덱스가 변하므로
			StringBuilder m_sb = new StringBuilder(); // 수식을 담을 StringBuilder
			m_sb.append(str);
			for(int i = 1; i < N; i += 2) {
				if(selected[i]) {
					m_sb.insert(i - 1 + temp, '(');
					m_sb.insert(i + 3 + temp, ')');
					temp += 2;
				}
			}
			int result = getResult(m_sb.toString());
			max = Math.max(max, result);
			return;
		}
		
		if(idx != 1 && selected[idx - 2]) subset(idx + 2, selected);
		else {
			selected[idx] = true;
			subset(idx + 2, selected);
			selected[idx] = false;
			subset(idx + 2, selected);
		}
	}
	
	static int getResult(String str) { // 문자열의 연산 결과를 리턴
		Stack<Integer> values = new Stack<>();
		Stack<Character> operators = new Stack<>();
		
		for(int i = 0, size = str.length(); i < size; i++) {
			char cur = str.charAt(i);
			if(Character.isDigit(cur)) {
				values.push((int) (cur -'0'));
			} else if(cur == '(') {
				operators.push(cur);
			} else if(cur == ')') {
				// 괄호 안에 연산자 하나만 들어있으므로
                values.push(calculate(values.pop(), values.pop(), operators.pop()));
				operators.pop(); // '(' 제거
			} else if(cur == '+' || cur == '-'){ // +, - 연산자가 나오면 그 앞에 우선순위 높은 연산을 모두 처리
				while(!operators.isEmpty() && operators.peek() != '(') {
					values.push(calculate(values.pop(), values.pop(), operators.pop()));
				}
				operators.push(cur);
			} else {
				operators.push(cur);
			}
		}
		while(!operators.isEmpty()) { // 남아있는 연산들 처리
			values.push(calculate(values.pop(), values.pop(), operators.pop()));
		}
		return values.pop(); 
	}
	
	static int calculate(int a, int b, char operator) {
		switch(operator){
		case '+':
			return a + b;
		case '-':
			return b - a;
		default:
			return a * b;
		}
	}
	
	public static void main(String[] args) throws IOException {
		input();
		solve();

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
