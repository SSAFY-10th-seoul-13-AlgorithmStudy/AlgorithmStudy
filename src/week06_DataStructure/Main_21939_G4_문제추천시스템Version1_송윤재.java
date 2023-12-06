package week06_DataStructure;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main_21939_G4_문제추천시스템Version1_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static TreeSet<Problem> set;
	static HashMap<Integer, Integer> map;
	static int N, M, p, l;
	
	static class Problem implements Comparable<Problem>{
		int num, level;

		public Problem(int num, int level) {
			this.num = num;
			this.level = level;
		}

		@Override
		public int compareTo(Problem o) {
			if(this.level == o.level) return this.num - o.num;
			else return this.level - o.level;
		}
	}
	
	static void init() {
		set = new TreeSet<>();
		map = new HashMap<>();
	}
	
	static void input_solve() throws IOException{
		N = Integer.parseInt(br.readLine());
		
		init();
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			p = Integer.parseInt(st.nextToken());
			l = Integer.parseInt(st.nextToken());
			set.add(new Problem(p, l));
			map.put(p, l);
		}
		
		M = Integer.parseInt(br.readLine());		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			String command = st.nextToken();
			
			switch (command) {
			case "add":
				p = Integer.parseInt(st.nextToken());
				l = Integer.parseInt(st.nextToken());
				set.add(new Problem(p, l));
				map.put(p, l);
				break;
			case "recommend":
				int x = Integer.parseInt(st.nextToken());
				if(x < 0) sb.append(set.first().num);
				else sb.append(set.last().num);
				sb.append("\n");
				break;
			case "solved":
				p = Integer.parseInt(st.nextToken());
				set.remove(new Problem(p, map.get(p)));
				map.remove(p);
				break;
			}
		}
	}
	
	public static void main(String[] args) throws IOException{
		input_solve();
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}