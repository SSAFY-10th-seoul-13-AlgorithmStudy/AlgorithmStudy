package week03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_13334_G2_철로_김태수 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[][] list = new int[N][2];
		StringTokenizer st;
		for(int i = 0; i < N;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if(a<b) {
				list[i][0] = a;
				list[i][1] = b;
			}
			else {
				list[i][0] = b;
				list[i][1] = a;
			}
		}
		int D = Integer.parseInt(br.readLine());
		Arrays.sort(list, (a,b) -> a[1] -b[1]);
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		//철도의 양단
		int left = Integer.MIN_VALUE;
		int right = left + D;
		int cnt = 0;
		int answer = 0;
		for(int i = 0; i < N;i++) {
			int[] cur = list[i];
			if((cur[1] - cur[0]) > D || left > cur[0]) continue;
			if(right < cur[1]) {
				//철도를 이동
				right = cur[1];
				left = right - D;
				while(!pq.isEmpty() && pq.peek() < left) {
					pq.poll();
					cnt--;
				}
			}
			pq.add(cur[0]);
			cnt++;
			answer = Math.max(answer, cnt);
		}
		System.out.println(answer);
	}
}
