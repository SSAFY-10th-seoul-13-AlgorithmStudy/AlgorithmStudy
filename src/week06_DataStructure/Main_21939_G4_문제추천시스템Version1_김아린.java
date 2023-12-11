package week06_DataStructure;

import java.io.*;
import java.util.*;

class Problem implements Comparable<Problem> {
	int num, dfc;

	public Problem(int num, int dfc) {
		this.num = num;
		this.dfc = dfc;
	}

	@Override
	public String toString() {
		return "Problem [num=" + num + ", dfc=" + dfc + "] ";
	}

	@Override
	public int compareTo(Problem o) {
		return this.dfc != o.dfc ? o.dfc - this.dfc : o.num - this.num;
	}
}

public class Main_21939_G4_문제추천시스템Version1_김아린 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		// 우선순위 큐 생성
		PriorityQueue<Problem> pq = new PriorityQueue<Problem>();
		PriorityQueue<Problem> rpq = new PriorityQueue<Problem>(Collections.reverseOrder());

		// 자료들을 저장할 map 생성 -> 추가삭제할때 쓸거임
		Set<Problem> set = new TreeSet<Problem>();
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			int dfc = Integer.parseInt(st.nextToken());
			pq.add(new Problem(num, dfc));
			rpq.add(new Problem(num, dfc));
			set.add(new Problem(num, dfc));
			map.put(num, dfc);
		}

		// 그냥 toString 써서 출력할 때랑 pq 순회하면서 출력할때랑 다르게 결과가 나온다
		// 이유는 Java의 PriorityQueue는 내부적으로 힙(heap)을 이용하여 구현되어 있기 때문에, 중간 과정에서는 원하는 정렬
		// 순서대로 보이지 않을 수 있습니다.
//		System.out.println(pq);
//		while(!pq.isEmpty()){
//		    Problem p = pq.poll();
//		    System.out.println("Problem [num=" + p.num + ", dfc=" + p.dfc + "]");
//		}

		int M = Integer.parseInt(br.readLine());

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			String command = st.nextToken();
			if (command.equals("add")) { // 추가
				int num = Integer.parseInt(st.nextToken());
				int dfc = Integer.parseInt(st.nextToken());
				pq.add(new Problem(num, dfc));
				rpq.add(new Problem(num, dfc));
				map.put(num, dfc);
				set.add(new Problem(num, dfc));
			} else if (command.equals("recommend")) {
				// 1이면 가장 앞 -1이면 가장 뒤
				// 아니 그냥 조회만 하면 되네..?? 제거안해도 되네...
				int comm = Integer.parseInt(st.nextToken());
				if (comm == 1) {
					while (true) {
						if (!set.contains(pq.peek())) {
							pq.poll();
							continue;
						}
						break;
					}
					sb.append(pq.peek().num).append("\n");
				} else {
					while (true) {
						if (!set.contains(rpq.peek())) {
							rpq.poll();
							continue;
						}
						break;
					}
					sb.append(rpq.peek().num).append("\n");
				}
			} else if (command.equals("solved")) { // 해결
				int find = Integer.parseInt(st.nextToken());
				Problem tmp = new Problem(find, map.get(find));
				set.remove(tmp);
				map.remove(find);
			}

		}

		System.out.println(sb);
	}
}
/**
2
1000 1
2000 1
4
recommend -1
solved 1000
add 1000 1000
recommend -1
*/