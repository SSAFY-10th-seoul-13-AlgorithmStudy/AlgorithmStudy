package week22_Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_12851_G4_숨바꼭질2_김희연 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		int[] time = new int[100001];
		Queue<Integer> queue = new LinkedList<>();

		queue.add(n);
		time[n] = 1;

		int minTime = Integer.MAX_VALUE;
		int cnt = 0;

		while(!queue.isEmpty()){
			int now = queue.poll();

			if(minTime < time[now])
				return;

			for(int i=0; i<3; i++){
				int next;

				if(i==0)
					next = now + 1;
				else if(i==1)
					next = now - 1;
				else
					next = now * 2;

				if(next < 0 || next > 100000)
					continue;

				if(next == k){
					minTime = time[now];
					cnt++;
				}

				if(time[next] == 0 || time[next] == time[now] + 1){
					queue.add(next);
					time[next] = time[now] + 1;
				}
			}
		}

		System.out.println(minTime);
		System.out.println(cnt);
	}
}
