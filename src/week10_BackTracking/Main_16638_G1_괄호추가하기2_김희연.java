package week10_BackTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class Main_16638_G1_괄호추가하기2_김희연 {
	static int n;
	static int max = Integer.MIN_VALUE;
	static boolean[] brackets;
	static Formula[] input;

	public static class Formula{
		char character;
		int priority;

		Formula(char character, int priority){
			this.character = character;
			this.priority = priority;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());

		brackets = new boolean[n];
		input = new Formula[n];

		String str = br.readLine();
		for(int i=0; i<str.length(); i++){
			char c = str.charAt(i);
			if(c == '*')
				input[i] = new Formula(c, 2);
			else if(c == '+' || c == '-')
				input[i] = new Formula(c, 3);
			else
				input[i] = new Formula(c, 0);
		}

		dfs(1);

		System.out.println(max);
	}

	public static void dfs(int depth){
		if(depth >= n){
			int result = calculate(infixToPostfix());
			max = Math.max(max, result);
			return;
		}

		if(depth == 1){
			brackets[depth] = true;
			dfs(depth+2);
			brackets[depth] = false;
		} else {
			if(!brackets[depth-2]){
				brackets[depth] = true;
				dfs(depth+2);
				brackets[depth] = false;
			}
		}
		dfs(depth+2);
	}

	public static ArrayList<Formula> infixToPostfix(){
		ArrayList<Formula> postfix = new ArrayList<>();
		Deque<Formula> deque = new ArrayDeque<>();

		for(int i=0; i<n; i++){
			if(input[i].priority == 0){
				postfix.add(input[i]);
			} else {
				int priority = input[i].priority;
				if(brackets[i]){
					priority = 1;
				}

				while(!deque.isEmpty() && deque.peek().priority <= priority){
					postfix.add(deque.pop());
				}

				deque.push(new Formula(input[i].character, priority));
			}
		}

		while(!deque.isEmpty()){
			postfix.add(deque.pop());
		}

		return postfix;
	}

	public static int calculate(ArrayList<Formula> postfix){
		Deque<Integer> deque = new ArrayDeque<>();
		int num = 0;
		for(int i=0; i<n; i++){
			if(postfix.get(i).priority == 0){
				deque.push(postfix.get(i).character - '0');
			} else {
				int a = deque.pop();
				int b = deque.pop();
				char op = postfix.get(i).character;
				switch (op) {
					case '+':
						num = b + a;
						break;
					case '-':
						num = b - a;
						break;
					case '*':
						num = b * a;
						break;
				}
				deque.push(num);
			}
		}
		return deque.pop();
	}
}
