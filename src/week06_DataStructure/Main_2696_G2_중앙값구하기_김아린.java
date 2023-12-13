package week06_DataStructure;

import java.io.*;
import java.util.*;

public class Main_2696_G2_중앙값구하기_김아린 {
	public static void main(String[] args) throws Exception {
		// 수열을 읽고 홀수번째 수를 읽을 때마다 지금까지 입력받은 값의 중앙값 출력
		// 비슷한 문제 푼 적 있는거같은데?
		// 삽입정렬 or 우선순위큐
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		for (int testcase = 0; testcase < T; testcase++) {
			int M = Integer.parseInt(br.readLine());

			sb.append((M + 1) / 2);
			sb.append("\n");
			
			PriorityQueue<Integer> max = new PriorityQueue<>();
			PriorityQueue<Integer> min = new PriorityQueue<>(Collections.reverseOrder());

			int cnt = 0;

			// 아 입력이...
			for (int i = 0; i < M; i++) {
				if (i % 10 == 0)
					st = new StringTokenizer(br.readLine());

				int input = Integer.parseInt(st.nextToken());

				if (min.size() == max.size())
					max.add(input);
				else
					min.add(input);

				// 최소힙의 최대값과 최대힙의 최솟값 비교해서 최소힙의 최댓값이 최대힙의 최솟값보다 크다면?
				// 서로 다른 힙에 저장된 값이므로 교환
				if (!min.isEmpty() && !max.isEmpty()) {
					if (min.peek() > max.peek()) {
						int tmp = max.poll();
						max.add(min.poll());
						min.add(tmp);
					}
				}

				// 중앙값 출력
				if (i % 2 == 0) {
					sb.append(max.peek());
					sb.append(" ");

					if (++cnt % 10 == 0)
						sb.append("\n");
				}
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
