package week25_Greedy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main_1082_G3_방번호_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N, M;
	static Num P[];
	
	static class Num implements Comparable<Num>{
		int n, price;
		Num(int n, int price){
			this.n = n;
			this.price = price;
		}
		
		@Override
		public int compareTo(Num o) {
			if(o.price == this.price) return Integer.compare(o.n, this.n);
			return Integer.compare(this.price, o.price);
		}
	}
	
	static void init() {
		P = new Num[N];
	}
	
	static void input() throws IOException {
		N = Integer.parseInt(br.readLine());
		init();
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) P[i] = new Num(i, Integer.parseInt(st.nextToken()));
		M = Integer.parseInt(br.readLine());
	}

	static void solve() {
		int money = M;
		Num arr[] = new Num[50];
		int idx = 0;
		Num sortP[] = new Num[N];
		
		for(int i = 0; i < N; i++) {
			sortP[i] = new Num(P[i].n, P[i].price);
		}
		
		Arrays.sort(sortP);		
		
		int min_num = sortP[0].n;
		int min_price = sortP[0].price;
		
		if(N != 1) {
			int sec_price = sortP[1].price;
			
			if(min_num == 0 && money >= sec_price) {
				money -= sec_price;
				arr[idx++] = sortP[1];
			}
		}
		
		while(money >= min_price) {
			arr[idx++] = sortP[0];
			money -= min_price;
		}
		
		for(int i = 0; i < idx; i++) {
			int cur_price = arr[i].price;
			for(int j = N - 1; j >= 0; j--) {
				int next_price = P[j].price;
				if(next_price <= money + cur_price) {
					arr[i] = P[j];
					money += cur_price - next_price;
					break;
				}
			}
		}
		
		if(arr[0].n == 0) {
			sb.append(0);
			return;
		}
		
		for(int i = 0; i < idx; i++) {
			sb.append(arr[i].n);			
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
