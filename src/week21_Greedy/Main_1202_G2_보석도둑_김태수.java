package week21_Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_1202_G2_보석도둑_김태수 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N][2];
		
		for(int i = 0 ; i < N;i++) {
			st = new StringTokenizer(br.readLine());
			map[i] = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
		}
		
		int[] bags = new int[K];
		for(int i = 0 ; i < K;i++) {
			bags[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(bags);
		Arrays.sort(map, (o1,o2)->(o1[0] - o2[0]));
		
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2) -> (o2[1] - o1[1]));
		
		int idx = 0;
		
		int answer = 0;
		
		for(int i = 0 ; i < K;i++) {
			int now = bags[i];
			
			while(idx < N) {
				if(map[idx][0] <= now) {
					pq.add(map[idx]);
					idx++;
				}
				else break;
			}
			
			if(!pq.isEmpty()) {
				answer += pq.poll()[1];
			}
		}
		
		System.out.println(answer);
	}
}
