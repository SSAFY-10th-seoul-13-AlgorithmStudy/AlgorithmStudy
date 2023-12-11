package week06_DataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main_21939_G4_문제추천시스템Version1_김태수 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		TreeMap<Integer, TreeMap<Integer,Integer>> tm = new TreeMap<>();
		for(int i = 0; i < N;i++) {
			st = new StringTokenizer(br.readLine());
			int P = Integer.parseInt(st.nextToken());				//문제번호
			int L = Integer.parseInt(st.nextToken());				//난이도
			if(!tm.containsKey(L)) tm.put(L,new TreeMap<Integer, Integer>());
			tm.get(L).put(P, 1);
		}
		
		int M = Integer.parseInt(br.readLine());
		for(int i = 0 ; i < M;i++) {
			st = new StringTokenizer(br.readLine());
			String cmd = st.nextToken();
			if(cmd.equals("add")) {
				int P = Integer.parseInt(st.nextToken());				//문제번호
				int L = Integer.parseInt(st.nextToken());				//난이도
				if(!tm.containsKey(L)) tm.put(L,new TreeMap<Integer, Integer>());
				tm.get(L).put(P, 1);
			}
			else if(cmd.equals("recommend")) {
				int flag = Integer.parseInt(st.nextToken());
				int result = flag == 1 ? tm.get(tm.lastKey()).lastKey() : tm.get(tm.firstKey()).firstKey();
				System.out.println(result);
			}
			//solved: 완탐
			else {
				int target = Integer.parseInt(st.nextToken());
				ArrayList<Integer> temp = new ArrayList<>();
				for(int tr: tm.keySet()) {
					tm.get(tr).remove(target);
					if(tm.get(tr).size() == 0) temp.add(tr);
				}
				for(int tt: temp) tm.remove(tt);
			}
		}
	}
}
