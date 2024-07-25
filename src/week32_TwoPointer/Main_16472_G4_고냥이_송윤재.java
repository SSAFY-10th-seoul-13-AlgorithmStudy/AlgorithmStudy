package week32_TwoPointer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main_16472_G4_고냥이_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;	
	static int N, alphabet[], len;
	static char input[];
	
	static void init() {
		alphabet = new int[26];
	}
		
	static void input() throws IOException {
		N = Integer.parseInt(br.readLine());
		String str = br.readLine();
		init();
		len = str.length();
		input = new char[len];
		for(int i = 0, end = len; i < end; i++) {
			input[i] = str.charAt(i);
		}
	}

	static void solve() {
		Set<Character> set = new HashSet<>();
		int result = 0;
		int start = 0;
		int end = 0;
		
		while(end < len) {
			char cur = input[end];
			if(!set.contains(cur)) {
				if(set.size() == N) {
					while(--alphabet[input[start] - 'a'] > 0) {				
						start++;												
					}
					set.remove(input[start]);
					start++;
				}
				set.add(cur);
			}

			alphabet[cur - 'a']++;
			end++;
			result = Math.max(result, end - start);
		}
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
