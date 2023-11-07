package week02;

import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class Main_7662_G4_이중우선순위큐_김아린 {
	public static void main(String[] args) throws Exception {
		// 삽입연산, 삭제(우선순위 낮은거)연산, 삭제(우선순위 높은거)연산
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());

		StringBuilder sb = new StringBuilder();

		for (int testcase = 1; testcase <= T; testcase++) {
			int k = Integer.parseInt(in.readLine());
			StringTokenizer st = null;
			TreeMap<Integer, Integer> map = new TreeMap<>();
			HashSet<Integer> set = new HashSet<>();

			// hashSet 사용 이유 : 처음에는 boolean[수의 범위]로 하려 했으나, 2^32 범위를 선언하면 공간 터질 것 같아서
			// hashSet으로 수를 저장하고 포함 여부를 판단하기로 함
			// TreeMap 사용 이유 : 키 값 자동 정렬 / 최대,최소 값 검색 기능 제공 / 탐색 시간복잡도 logN
			int insertCnt = 0;
			for (int i = 0; i < k; i++) {

				st = new StringTokenizer(in.readLine());

				if (st.nextToken().equals("I")) { // 삽입
					int tmp = Integer.parseInt(st.nextToken());

					insertCnt++;
					if (set.contains(tmp)) { // 이미 있다면?
						map.put(tmp, map.get(tmp) + 1);
					} else { // 처음 넣는 값이면?
						map.put(tmp, 0);
						set.add(tmp);
					}
				} else { // 삭제
					Entry<Integer, Integer> key = null;
					if (st.nextToken().equals("-1")) { // 최솟값 삭제
						key = map.firstEntry();
					} else { // 최댓값 삭제
						key = map.lastEntry();
					}

					if (key == null)
						continue;

					if (key.getValue() > 0) { // 중복 값이 여러개 있으면?
						map.put(key.getKey(), key.getValue() - 1);
					} else { // 하나였다면?
						map.remove(key.getKey());
						set.remove(key.getKey());
					}
				}
			}

			if (map.isEmpty()) {
				sb.append("EMPTY").append("\n");
			} else {
				sb.append(map.lastKey()).append(" ").append(map.firstKey()).append("\n");
			}
		}
		System.out.println(sb);
	}
}