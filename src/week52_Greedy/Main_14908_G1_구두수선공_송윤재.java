package week52_Greedy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_14908_G1_구두수선공_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static class Customer implements Comparable<Customer>{
		int i, t, s;
		double comp;
		
		public Customer(int i, int t, int s, double comp) {
			this.i = i;
			this.t = t;
			this.s = s;
			this.comp = comp;
		}
		
		@Override
		public int compareTo(Customer o) {
			if(o.t * this.s == this.t * o.s) 
				return Integer.compare(this.i, o.i);	
			return Double.compare(this.t * o.s, o.t * this.s);		
			
//			if(o.comp == this.comp)
//				return Integer.compare(this.i, o.i);
//			return Double.compare(o.comp, this.comp);
		}
	}
	
	static void input_solve() throws IOException {
		PriorityQueue<Customer> pq = new PriorityQueue<>();
		int N = Integer.parseInt(br.readLine());
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			pq.offer(new Customer(i, t, s, s / t));
		}
		while(!pq.isEmpty()) {
			sb.append(pq.poll().i).append(" ");
		}
	}	
	
	public static void main(String[] args) throws IOException {
		input_solve();

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
