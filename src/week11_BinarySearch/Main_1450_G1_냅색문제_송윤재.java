package week11_BinarySearch;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main_1450_G1_냅색문제_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N, C, arr[];
	static List<Integer> left, right;
	
	static void init() {
		left = new ArrayList<>();
		right = new ArrayList<>();
		arr = new int[N];
	}

	static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		init();
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
	}

	static void solve() {
		/*
		 * left와 rigth에는 각각 arr를 반으로 나누었을 때 
		 * 만들 수 있는 무게의 경우의 수가 모두 포함된다.
		 */
		subset(left, 0, N / 2, 0);
		subset(right, N / 2, N, 0);
		
		Collections.sort(left);
		Collections.sort(right);
		
		int result = 0;
		int left_size = left.size();
		int idx = right.size() - 1;
		
		/*
		 * left와 right는 정렬되어 있으므로
		 * left의 작은 값과 right의 큰 값부터 비교를 시작한다.
		 * 만약 left의 현재 인덱스와 right의 인덱스에 해당하는 값들의 합이 가방에 넣을 수 있으면
		 * 그보다 작은 right의 요소들은 탐색할 필요가 없다.
		 * 마찬가지로 left가 점점 커지므로 탐색한 right의 요소들은 다시 탐색할 필요가 없다.
		 */		
		for(int i = 0; i < left_size; i++) {
			while(idx >= 0 && right.get(idx) + left.get(i) > C) {
				idx--;
			}
			result += idx + 1;
		}
		
		sb.append(result);
	}
	
	static void subset(List<Integer> list, int idx, int end, int sum) {
		if(sum > C) return;
		if(idx == end) {
			list.add(sum); // 만들어진 sum을 list에 넣는다.
			return;
		}
		
		subset(list, idx + 1, end, sum); // 물건 안 넣었을 때
		subset(list, idx + 1, end, sum + arr[idx]); // idx번째 물건 넣었을 때
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
