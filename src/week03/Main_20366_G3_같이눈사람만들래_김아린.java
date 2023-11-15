package week03;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main_20366_G3_같이눈사람만들래_김아린 {

	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(in.readLine());
		ArrayList<Integer> list = new ArrayList<>();
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		for (int i = 0; i < N; i++) {
			list.add(Integer.parseInt(st.nextToken()));
		}
		
		//Arrays.sort() DualPivotQuicksort	평균 : O(nlog(n)) / 최악 : O(n^2)
		//Collections.sort() TimeSort (삽입정렬과 합병정렬을 결합한 정렬) 평균, 최악 : O(nlog(n))
		Collections.sort(list);
		
		
		//조합으로 뽑는다? 에바쎄바 N^4임
		//투포인터로 어떻게 할 수 있으려나
		//우선 각각의 합을 따로 구해준 다음에 투포인터로 순회하기?
		int ans = Integer.MAX_VALUE;
		
		top:
		for (int i = 0; i < N; i++) {
			for (int j = i+1; j < N; j++) {
				int sum = list.get(i) + list.get(j);
				
				int start = 0;
				int end = N-1;
				
				while(start < end) {
					if (start == i || start == j) {
						start++;
						continue;
					}
					if(end == i || end == j) {
						end--;
						continue;
					}
					int tmp = list.get(start) + list.get(end);
					ans = Math.min(Math.abs(sum-tmp), ans);
					
					if (sum > tmp) {
						start++;
					}else if (sum < tmp) {
						end--;
					} else {
						break top;
					}
				}
			}
		}
		
		System.out.println(ans);
	}

}