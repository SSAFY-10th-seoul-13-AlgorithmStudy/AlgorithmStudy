package week60_Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1461_G4_도서관_신문영 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		Queue<Integer> plus = new PriorityQueue<Integer>(Comparator.reverseOrder());
		Queue<Integer> minus = new PriorityQueue<Integer>(Comparator.reverseOrder());
		for (int i = 0; i < N; i++) {
			int distance = Integer.parseInt(st.nextToken());
			if (distance < 0) {
				minus.add(distance * -1);
			} else {
				plus.add(distance);
			}
		}
		
		int answer = 0;
		int multiplier = 1;
		while (!plus.isEmpty() || !minus.isEmpty()) {
			if ((!plus.isEmpty() && minus.isEmpty()) || 
					(!plus.isEmpty() && !minus.isEmpty() && plus.peek() > minus.peek())) {
				answer += plus.poll() * multiplier;
				int cnt = M - 1;
				while (!plus.isEmpty() && cnt > 0) {
					plus.poll();
					cnt--;
				}
			} else if ((plus.isEmpty() && !minus.isEmpty()) || 
					(!plus.isEmpty() && !minus.isEmpty() && plus.peek() < minus.peek())) {
				answer += minus.poll() * multiplier;
				int cnt = M - 1;
				while (!minus.isEmpty() && cnt > 0) {
					minus.poll();
					cnt--;
				}
			} else {
				Queue<Integer> plusTemp = new ArrayDeque<>();
				int plusSum = plus.poll();
				int cnt = M - 1;
				while (!plus.isEmpty() && cnt > 0) {
					plusTemp.add(plus.poll());
					cnt--;
				}
				
				Queue<Integer> minusTemp = new ArrayDeque<>();
				int minusSum = minus.poll();
				cnt = M - 1;
				while (!minus.isEmpty() && cnt > 0) {
					minusTemp.add(minus.poll());
					cnt--;
				}
				
				if (plusSum > minusSum) {
					answer += plusSum * multiplier;
					minus.addAll(minusTemp);
				} else {
					answer += minusSum * multiplier;
					plus.addAll(plusTemp);
				}
			}
			
			if (multiplier != 2) multiplier = 2;
		}
		
		System.out.println(answer);
	}
}
