package week02;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main_1799_G4_이중우선순위큐_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st; 
    static TreeMap<Integer, Integer> map;
    
    static void input_solve() throws IOException{
    	int k = Integer.parseInt(br.readLine());
    	map = new TreeMap<Integer, Integer>();
    	
    	for(int i = 0; i < k; i++) {
    		st = new StringTokenizer(br.readLine());
    		char cmd = st.nextToken().charAt(0);
    		int num = Integer.parseInt(st.nextToken()); 
    		cal(cmd, num);
    	}
    	if(map.isEmpty()) sb.append("EMPTY").append("\n");
    	else {
    		sb.append(map.lastKey()).append(" ").append(map.firstKey()).append("\n");
    	}
    }   
    
    static void cal(char cmd, int num) {
    	if(cmd == 'I') {
    		if(map.containsKey(num)) {
    			map.put(num, map.get(num) + 1);
    		} else {
    			map.put(num, 1);
    		}
    	} else {
    		if(map.isEmpty()) return;
    		if(num == 1) {
    			int key = map.lastKey();
    			int value = map.get(key);
    			if(value > 1) map.put(key, value - 1);
    			else map.pollLastEntry();
    		} else {
    			int key = map.firstKey();
    			int value = map.get(key);
    			if(value > 1) map.put(key, value - 1);
    			else map.pollFirstEntry();
    		}
    	}
    }
    
	public static void main(String[] args) throws IOException{
		int tc = Integer.parseInt(br.readLine());
		for(int t = 1; t <= tc; t++) {
			input_solve();
		}
        
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
	}
}
