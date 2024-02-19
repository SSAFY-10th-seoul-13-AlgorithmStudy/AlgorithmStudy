package week13_BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.swing.text.AbstractDocument.BranchElement;

public class Main_16637_G3_괄호추가하기_김태수 {
	public static int N;
	public static int result;
	public static ArrayList<Integer> list;
	public static ArrayList<Character> list_op;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		String ss = br.readLine();
		list = new ArrayList();
		list_op = new ArrayList();
		result = Integer.MIN_VALUE;
		for(int i = 0 ; i < N;i++) {
			if(i % 2 == 0 ) {
				list.add(ss.charAt(i) - '0');
			}
			else {
				list_op.add(ss.charAt(i));
			}
		}
		
		dfs(0, list.get(0));
		System.out.println(result);
	}
	
	public static void dfs(int depth, int value) {
		if(depth == list_op.size()) {
			result = Math.max(value, result);
			return;
		}
		int temp = calculate(value,list.get(depth+1), list_op.get(depth));
		dfs(depth+1, temp);
		if(depth+1 < list_op.size()) {
			int temp1 = calculate(list.get(depth+1),list.get(depth+2),list_op.get(depth+1));
			int temp2 = calculate(value, temp1, list_op.get(depth));
			dfs(depth+2, temp2);
		}
		
	}
	
	public static int calculate(int x, int y, char op) {
		if(op == '+') {
			return x+y;
		}
		else if(op == '-') {
			return x - y;
		}
		else return x * y;
		
	}
}
