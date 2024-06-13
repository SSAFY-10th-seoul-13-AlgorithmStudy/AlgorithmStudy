package week27_Trie;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main_14725_G3_개미굴_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N;
	static Node ROOT = new Node();
	
	static class Node{
		Map<String, Node> children;
		
		public Node() {
			this.children = new HashMap<>();
		}
	}
	
	static void input() throws IOException {
		N = Integer.parseInt(br.readLine());
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int k = Integer.parseInt(st.nextToken());
			Node cur = ROOT;
			for(int j = 0; j < k; j++) {
				String eat = st.nextToken();
				
				/**
				 *  case 1. contains로 확인하고 Node 생성 or get
				 *  116ms
				 */
				Node next; 
				if(!cur.children.containsKey(eat)) {
					next = new Node();
					cur.children.put(eat, next);					
				} else {
					next = cur.children.get(eat);
				}				
				cur = next;				

				/**
				 *  case 2. commputeIfAbsent로 간결하게 처리
				 *  220ms
				 */
//			    cur = cur.children.computeIfAbsent(eat, key -> new Node());
			}
		}
	}

	static void solve() {
		dfs(ROOT, 0);
	}
	
	static void dfs(Node node, int depth) {
		Map<String, Node> map = node.children;
		List<String> keySet = new ArrayList<>(map.keySet());
		
		Collections.sort(keySet); // 자녀들 사전순 정렬
		
		for(String key : keySet) {
			for(int i = 0; i < depth; i++) // 깊이 만큼 -- 추가
				sb.append("--");
			
			sb.append(key).append("\n");
			Node nextNode = map.get(key);
			dfs(nextNode, depth + 1);
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
