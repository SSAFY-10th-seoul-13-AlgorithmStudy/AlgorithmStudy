package week22_Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main_12851_G4_숨바꼭질2_김태수 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
	
		ArrayDeque<Integer> qq = new ArrayDeque<>();
		
		qq.add(N);
		int answer = 0;
		int[] v = new int[100001];
		if(N==K) {
			System.out.println(0);
			System.out.println(1);
			return;
		}
		
		while(!qq.isEmpty()) {
			int cur = qq.poll();
			
			if(cur == K ) {
				answer++;
				continue;
			}
			
			if(cur+1 >= 0 && cur+1 <= 100_000 && (v[cur+1] == 0 || v[cur+1] >= v[cur]+ 1)) {
				qq.add(cur+1);
				v[cur+1] = v[cur] + 1;
			} 
			
			if(cur-1 >= 0 && cur-1 <= 100_000 && (v[cur-1] == 0 || v[cur-1] >= v[cur]+ 1)) {
				qq.add(cur-1);
				v[cur-1] = v[cur] + 1;
			} 
			if(cur*2 >= 0 && cur*2 <= 100_000 && (v[cur*2] == 0 || v[cur*2] >= v[cur]+ 1)) {
				qq.add(cur * 2);
				v[cur*2] = v[cur] + 1;
			} 
			
		}
		System.out.println(v[K]);
		System.out.println(answer);

	}
}
