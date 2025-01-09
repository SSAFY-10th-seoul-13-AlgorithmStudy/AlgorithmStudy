package week53_Implementation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main_17140_G4_이차원배열과연산_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int R, C, K, curR, curC, arr[][];
	
	static class CountNum implements Comparable<CountNum>{
		int num, cnt;
		
		public CountNum(int num, int cnt) {
			this.num = num;
			this.cnt = cnt;
		}
		
		@Override
		public int compareTo(CountNum o) {
			if(this.cnt != o.cnt) 
				return Integer.compare(this.cnt, o.cnt);
			else
				return Integer.compare(this.num, o.num);
		}
	}
	
	static void init() {
		arr = new int[101][101];
		curR = 3;
		curC = 3;
	}
	
	static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		init();
		
		for(int i = 1; i <= 3; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= 3; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}	
	
	static void solve() {
		int time = 0;
		while(arr[R][C] != K && time++ < 101) {
			if(curR >= curC) {
				funR();
			} else {
				funC();
			}
		}
		
		if(time > 100) sb.append(-1);
		else sb.append(time);
	}
	
	static void funR() {
		int copy[][] = new int[101][101];
		int maxIdx = 0;
		
		for(int i = 1; i <= curR; i++){
			int count[] = new int[101];
			List<CountNum> list = new ArrayList<>();
			for(int j = 1; j <= curC; j++){
				count[arr[i][j]]++;
			}		
			for(int k = 1; k <= 100; k++){
				if(count[k] != 0){
					list.add(new CountNum(k, count[k]));
				}
			}
			Collections.sort(list);
			int idx = 1;
			for(CountNum cn : list){
				if(idx > 100) break;
				copy[i][idx++] = cn.num;
				copy[i][idx++] = cn.cnt;
			}
			maxIdx = Math.max(maxIdx, idx - 1);
		}
		
		curC = maxIdx;
		deepCopy(copy);
	}
	
	static void funC() {
		int copy[][] = new int[101][101];
		int maxIdx = 0;
		
		for(int j = 1; j <= curC; j++){
			int count[] = new int[101];
			List<CountNum> list = new ArrayList<>();
			for(int i = 1; i <= curR; i++){
				count[arr[i][j]]++;
			}		
			for(int k = 1; k <= 100; k++){
				if(count[k] != 0){
					list.add(new CountNum(k, count[k]));
				}
			}
			Collections.sort(list);
			int idx = 1;
			for(CountNum cn : list){
				if(idx > 100) break;
				copy[idx++][j] = cn.num;
				copy[idx++][j] = cn.cnt;
			}
			maxIdx = Math.max(maxIdx, idx - 1);
		}

		curR = maxIdx;
		deepCopy(copy);
	}
	
	static void deepCopy(int[][] copy) {
		for(int i = 1; i <= 100; i++) {
			for(int j = 1; j <= 100; j++) {
				arr[i][j] = copy[i][j];
			}
		}
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
