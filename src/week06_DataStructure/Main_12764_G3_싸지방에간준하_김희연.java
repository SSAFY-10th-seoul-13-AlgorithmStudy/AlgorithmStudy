package week06_DataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_12764_G3_싸지방에간준하_김희연 {
	public static class Time implements Comparable<Time>{
		int start;
		int end;

		public Time(int start, int end){
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(Time o){
			return Integer.compare(start, o.start);
		}
	}

	public static class Computer implements Comparable<Computer>{
		int end;
		int idx;

		public Computer(int end, int idx){
			this.end = end;
			this.idx = idx;
		}

		@Override
		public int compareTo(Computer o){
			return Integer.compare(end, o.end);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		PriorityQueue<Time> times = new PriorityQueue<>();

		for(int i=0; i<N; i++){
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int q = Integer.parseInt(st.nextToken());

			times.add(new Time(p, q));
		}

		PriorityQueue<Computer> comEndTime = new PriorityQueue<>();
		PriorityQueue<Integer> usedCom = new PriorityQueue<>();

		int[] comIdx = new int[100001];
		int cnt = 0;

		for(int i=0; i<N; i++){
			while(!comEndTime.isEmpty() && times.peek().start > comEndTime.peek().end){
				usedCom.add(comEndTime.poll().idx);
			}

			if(usedCom.isEmpty()){
				comEndTime.add(new Computer(times.poll().end, cnt));
				comIdx[cnt]++;
				cnt++;
			} else {
				comEndTime.add(new Computer(times.poll().end, usedCom.peek()));
				comIdx[usedCom.poll()]++;
			}
		}

		sb.append(cnt).append("\n");
		for(int i=0; i<cnt; i++){
			sb.append(comIdx[i] + " ");
		}
		System.out.println(sb);
	}
}