package week25_Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1082_G3_방번호_신문영 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int m  = Integer.parseInt(br.readLine());
		
		Number[] dp = new Number[m + 1];
		dp[0] = new Number();
		for (int i = 1; i < dp.length; i++) {
			Number max = null;
			for (int j = n - 1; j >= 0; j--) {
				if (i - arr[j] >= 0 && m >= dp[i - arr[j]].getValue(arr) + arr[j]) {
					Number operand = dp[i - arr[j]];
					
					if (j != 0) {
						while (i >= operand.getValue(arr) + arr[j]) {
							operand = new Number(operand.addFirst(j));
						}
					} else {
						while (i >= operand.getValue(arr) + arr[j]) {
							operand = new Number(operand.addLast(j));
							if (operand.queue.peek() == 0) break;
						}
					}

					if (max == null) {
						max = Number.max(dp[i - 1], operand);
					} else {
						max = Number.max(max, Number.max(dp[i - 1], operand));
					}
				}
			}
			
			if (max != null) {
				dp[i] = new Number(max.queue);
			}
			else {
				dp[i] = new Number(dp[i - 1].queue);
			}
		}
		
		System.out.println(dp[m]);
	}
	
	public static class Number {
		Queue<Integer> queue;
		
		public Number() {
			this.queue = new ArrayDeque<>();
		}
		
		public Number(Queue<Integer> queue) {
			this.queue = new ArrayDeque<>();
			for (int i : queue) {
				this.queue.add(i);
			}
		}
		
		public Queue<Integer> addFirst(int i) {
			ArrayDeque<Integer> temp = new ArrayDeque<>();
			for (int j : queue) {
				temp.add(j);
			}
			
			if ((i == 0 && temp.size() == 0) || (i != 0)) {
				temp.addFirst(i);
			}
			return temp;
		}
		
		public Queue<Integer> addLast(int i) {
			ArrayDeque<Integer> temp = new ArrayDeque<>();
			for (int j : queue) {
				temp.add(j);
			}
			
			if (temp.size() == 0) {
				temp.addLast(i);
			} else if (temp.size() > 0 && temp.peek() != 0) {
				temp.addLast(i);
			}
			return temp;
		}
		
		public int getValue(int[] valueMap) {
			int sum = 0;
			for (int i : queue) {
				sum += valueMap[i];
			}
			return sum;
		}
		
		public String toString() {
			StringBuilder stringBuilder = new StringBuilder();
			for (int i : queue) {
				stringBuilder.append(i);
			}
			return stringBuilder.toString();
		}
		
		public static Number max(Number a, Number b) {
			Number answer = null;
			
			if (a.queue.size() > b.queue.size()) {
				answer = a;
			} else if (a.queue.size() < b.queue.size()) {
				answer = b;
			} else {
				if (a.queue.equals(b.queue)) return a;
				
				Queue<Integer> tempA = new ArrayDeque<>();
				Queue<Integer> tempB = new ArrayDeque<>();
				while (!a.queue.isEmpty()) {
					Integer aNum = a.queue.poll();
					Integer bNum = b.queue.poll();
					tempA.add(aNum);
					tempB.add(bNum);
					
					if (aNum > bNum) {
						answer = a;
						break;
					} else if (aNum < bNum) {
						answer = b;
						break;
					}
				}
				
				while (!a.queue.isEmpty()) {
					int aNum = a.queue.poll();
					tempA.add(aNum);
				}
				
				while (!b.queue.isEmpty()) {
					int bNum = b.queue.poll();
					tempB.add(bNum);
				}
				
				a.queue = tempA;
				b.queue = tempB;
			}
			
			if (answer == null) answer = b;
			return answer;
		}
	}

}
