package week07_DynamicProgramming;

import java.io.*;
import java.util.*;

public class Main_12015_G2_가장긴증가하는부분수열2_김아린 {
	public static void main(String[] args) throws IOException {
		// 실2문제랑 완전 똑같은데 범위만 다르네 O(n^2)이므로 이대로 풀면 시간초과 각
		// 이분탐색..?
		// ArrayList가 비어 있거나, ArrayList의 마지막 요소보다 현재 요소가 크다면, ArrayList에 현재 요소를 추가
		// 그렇지 않다면, ArrayList에서 이진 탐색을 사용하여 현재 요소보다 크거나 같은 첫 번째 요소의 위치를 찾고, 그 위치의 요소를 현재 요소로 교체
		// O(nlogn)

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int[] array = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			array[i] = Integer.parseInt(st.nextToken());
		}

		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < N; i++) {
			if (list.isEmpty() || list.get(list.size() - 1) < array[i])
				list.add(array[i]);
			else {
				int left = 0;
				int right = list.size() - 1;
				while (left < right) {
					int mid = (left + right) / 2;
					if (list.get(mid) < array[i]) {
						left = mid + 1;
					} else {
						right = mid;
					}
				}
				list.set(right, array[i]);
			}
		}

		System.out.println(list.size());
	}
}
