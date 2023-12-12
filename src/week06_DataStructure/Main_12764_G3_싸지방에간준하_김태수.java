package week06_DataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_12764_G3_싸지방에간준하_김태수 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		ArrayList<int[]> list = new ArrayList<>();
		
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for(int i = 0 ; i < N ;i++) {
			st = new StringTokenizer(br.readLine());
			int[] temp = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
			list.add(temp);
		}
		
		Collections.sort(list, new Comparator<int[]>() {
			@Override
			public int compare(int[] p1, int[] p2) {
				return p1[0] - p2[0];
			}
		});
		//int[] : {endTime, placeNumber}
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2)->{
			return o1[0] - o2[0];
		});
		int idx = 1;
		int[] cnt = new int[100_000];
		PriorityQueue<Integer> empty = new PriorityQueue<>();
		for(int[] zzuna: list) {
			while(!pq.isEmpty() && (zzuna[0] >= pq.peek()[0])) {
				empty.add(pq.poll()[1]);
			}
			
			if(empty.isEmpty()) {
				cnt[idx]++;
				pq.add(new int[] {zzuna[1], idx});
				idx++;
			}
			else {
				int place = empty.poll(); 
				cnt[place]++;
				pq.add(new int[] {zzuna[1], place});
			}
		}
		System.out.println(idx-1);
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i < idx ;i++) {
			if(cnt[i] != 0) sb.append(cnt[i]).append(" ");
		}
		System.out.println(sb);
	}
}

