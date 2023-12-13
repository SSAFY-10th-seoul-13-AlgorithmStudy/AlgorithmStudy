package week06_DataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main_3015_P5_오아시스재결합_김태수 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Stack<long[]> stk = new Stack<>();
		stk.add(new long[] {Integer.parseInt(br.readLine()),1});
		long count = 0;
		
		for(int i = 0; i < N-1; i++) {
			int target = Integer.parseInt(br.readLine());
			
			if(stk.peek()[0] > target) {
				stk.add(new long[] {target,1});
				count++;
			}
			else {
				int num = 1;
				while(!stk.isEmpty() && stk.peek()[0] <= target) {
					long[] cur = stk.peek();
					if(cur[0] == target) num += cur[1];
					count += stk.pop()[1];
				}
				if(!stk.isEmpty()) count++;		
				stk.add(new long[] {target,num});
			}
		}
		System.out.println(count);
	}
}
