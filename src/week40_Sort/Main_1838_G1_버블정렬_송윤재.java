package week40_Sort;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main_1838_G1_버블정렬_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N;
	static Num nums[];
	
	static class Num implements Comparable<Num>{
		int value, index;
		
		Num(int value, int index){
			this.value = value;
			this.index = index;
		}
		
		@Override
		public int compareTo(Num o) {
			return Integer.compare(value, o.value);
		}
	}
	
	static void init() {
		nums = new Num[N];
	}
		
	static void input() throws IOException {
		N = Integer.parseInt(br.readLine());
		init();
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			nums[i] = new Num(Integer.parseInt(st.nextToken()), i);
		}
	}

	static void solve() {
		int result = 0;
		
		/*
		 * 풀이 1. PriorityQueue를 사용
		 * 시간 : 588ms
		 */
		
		PriorityQueue<Num> pq = new PriorityQueue<>();
		for(int i = 0; i < N; i++) {
			pq.add(nums[i]);
		}
		
		for (int i = 0; i < N; i++) {
            int index = pq.poll().index;
            if (index > i) {
                if (result < index - i) {
                    result = index - i;
                }
            }
        }

		/*
		 * 풀이 2. Arrays.sort를 사용
		 * 시간 : 1328ms
		 */
		
//		Arrays.sort(nums);
//		
//		for(int i = 0; i < N; i++) {
//			result = Math.max(nums[i].index - i, result);
//		}
		
		sb.append(result);
	}
	
	public static void main(String[] args) throws IOException {		
		input();
		solve();

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
