package week06_DataStructure;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_12764_G3_싸지방에간준하_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N, Navys[][];
	
	static class Navy implements Comparable<Navy>{
		int in, out, com;

		public Navy(int in, int out, int com) {
			this.in = in;
			this.out = out;
			this.com = com;
		}

		@Override
		public int compareTo(Navy o) {
			return Integer.compare(this.out, o.out);
		}
	}
	
	static void init() {
		Navys = new int[N][2];
	}
	
	static void input() throws IOException{
		N = Integer.parseInt(br.readLine());
		init();
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int q = Integer.parseInt(st.nextToken());
			Navys[i] = new int[] {p, q};
		}
	}
	
	static void solve() {
		PriorityQueue<Navy> navy_pq = new PriorityQueue<>(); // 나갈 순서대로 정렬된 해군들
		PriorityQueue<Integer> com_pq = new PriorityQueue<>(); // 비어있는 컴퓨터
		
		ArrayList<Integer> list = new ArrayList<>(); // 컴퓨터 사용횟수
		list.add(0);
		int X = 0;
		
		Arrays.sort(Navys, new Comparator<int[]>(){ // 들어온 순서대로 정렬
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
		});
		
		for(int i = 0; i < N; i++) {
			int p = Navys[i][0];
			int q = Navys[i][1];
			
			// 다음 사람이 들어오기 전 나가는 인원 처리
			while(!navy_pq.isEmpty() && navy_pq.peek().out < p) {
				com_pq.offer(navy_pq.poll().com);
			}
			
			// 중간에 빈 컴퓨터가 있으면 거기부터 채워줘야함
			if(!com_pq.isEmpty()) {
				int next_com = com_pq.poll();
				navy_pq.offer(new Navy(p, q, next_com));
				list.set(next_com, list.get(next_com) + 1);
			}
			else {
				navy_pq.offer(new Navy(p, q, ++X));
				list.add(1);
			}
		}
		sb.append(X).append("\n");
		for(int i = 1; i <= X; i++) 
			sb.append(list.get(i)).append(" ");
	}
	
	public static void main(String[] args) throws IOException{
		input();
		solve();
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
