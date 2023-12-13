package week06_DataStructure;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_2696_G2_중앙값구하기_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int M;
	static ArrayList<Integer> list;
	
	static void init() {
		list = new ArrayList<Integer>();
	}
	
	static void input_solve() throws IOException{
		M = Integer.parseInt(br.readLine());
		init();
		sb.append(M / 2 + 1).append("\n"); // 중앙값의 개수 출력
		
		for(int i = 0; i < M; i++) {
			if(i % 10 == 0) st = new StringTokenizer(br.readLine()); 
			int in = Integer.parseInt(st.nextToken());
			
			if(i == 0) list.add(in);
			else binarySearch(0, i - 1, in);
			
			if(i % 2 == 0) { // 홀수번째(i는 0이 첫번째이므로)
				sb.append(list.get(i / 2)).append(" ");
			}
			if((i + 1) % 20 == 0) sb.append("\n"); // 10번마다 줄바꿈
		}
		sb.append("\n");
	}
	
	static void binarySearch(int start, int end, int target) {
		int mid = (start + end) / 2;
		
		if(start == end) {
			// 새로 들어올 위치를 찾았을 때 현재 그 위치에 있는 숫자와 
			// 비교를 하여 알맞은 위치에 넣어줘야함
			if(list.get(mid) > target) {
				list.add(mid, target);
			}
			else {				
				if(mid == list.size()) list.add(target);
				else list.add(mid + 1, target);
			}
			return;
		}
		
		if(list.get(mid) > target) binarySearch(start, mid, target);
		else binarySearch(mid + 1, end, target);
	}
	
	public static void main(String[] args) throws IOException{
		int tc = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < tc; i++) {
			input_solve();
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
