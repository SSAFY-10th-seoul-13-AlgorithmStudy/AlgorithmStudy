package week33_DynamicProgramming;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main_2550_G3_전구_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N, origin_sw[], bulb[];
	static List<Integer> lis; // LIS를 저장할 리스트
	static Map<Integer, Integer> map; // <스위치 값, 왼쪽 인덱스>
	
	static class Node{
		int num;
        int idx;
 
        public Node(int num, int idx) {
            this.num = num;
            this.idx = idx;
        }
	}
	
	static void init() {
		origin_sw = new int[N];
		bulb = new int[N];
		lis = new ArrayList<>();
		map = new HashMap<>();
	}
		
	static void input() throws IOException {
		N = Integer.parseInt(br.readLine());
		init();
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			int in = Integer.parseInt(st.nextToken());
			origin_sw[i] = in;
			map.put(in, i);
		}
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			bulb[i] = map.get(Integer.parseInt(st.nextToken()));			
		}
	}

	static void solve() {
		Node[] nodes = new Node[N];
		
        for(int i = 0; i < N; i++) { 
        	int size = lis.size();
            if(size == 0 || lis.get(size - 1) < bulb[i]) {
                lis.add(bulb[i]);
                nodes[i] = new Node(bulb[i], size); // sw와 연결된 전구의 LIS위치
            } else {
                int idx = binarySearch(bulb[i]);
                lis.set(idx, bulb[i]);
                nodes[i] = new Node(bulb[i], idx);
            }
        } 
 
        int idx = lis.size() - 1;
        for(int i = N - 1; i >= 0; i--) {
            if(nodes[i].idx == idx) {
                lis.set(idx--, origin_sw[nodes[i].num]);
            }
        }
        sb.append(lis.size()).append("\n");
 
        Collections.sort(lis);        
        for(int i = 0, size = lis.size(); i < size; i++) {
            sb.append(lis.get(i) + " ");
        }
	}
	

    public static int binarySearch(int num) {
        int left = 0;
        int right = lis.size() - 1;
        
        while(left <= right) {
            int mid = (left + right) / 2;
 
            if(lis.get(mid) < num) {
                left = mid + 1;
            } else{
                right = mid - 1;
            }
        }
        return left;
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
