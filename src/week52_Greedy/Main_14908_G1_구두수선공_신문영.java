package week52_Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_14908_G1_구두수선공_신문영 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		List<Work> workList = new ArrayList<>();
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			workList.add(new Work(i, t, s));
		}
		
		workList.sort(null);
		
		for (Work work : workList) {
			System.out.print(work.number + " ");
		}
	}
	
	static class Work implements Comparable<Work> {
		int number;
		int t;
		int s;
		
		Work(int number, int t, int s) {
			this.number = number;
			this.t = t;
			this.s = s;
		}
		
		@Override
		public int compareTo(Work o) {
			int thisCost = this.t * o.s;
			int otherCost = o.t * this.s;
			
			if (thisCost == otherCost) {
				return Integer.compare(this.number, o.number);
			} else {
				return Double.compare(thisCost, otherCost);
			}
		}
	}
}
