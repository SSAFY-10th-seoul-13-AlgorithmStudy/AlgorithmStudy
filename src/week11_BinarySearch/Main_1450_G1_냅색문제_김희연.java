package week11_BinarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main_1450_G1_냅색문제_김희연 {
	static int c;
	static int[] arr;
	static List<Integer> list1, list2;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		arr = new int[n];

		st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++){
			arr[i] = Integer.parseInt(st.nextToken());
		}

		list1 = new ArrayList<>();
		list2 = new ArrayList<>();
		dfs(list1, 0, n/2, 0);
		dfs(list2, n/2, n, 0);
		Collections.sort(list2);

		int cnt = 0;
		int idx = 0;
		for(int i = 0; i< list1.size(); i++) {
			idx = upperbound(0, list2.size()-1, list1.get(i));
			cnt += idx+1;
		}
		System.out.println(cnt);
	}

	static int upperbound(int start, int end, int value){
		int num = c - value;
		while(start <= end){
			int mid = (start + end) / 2;
			if(list2.get(mid) <= num)
				start = mid + 1;
			else
				end = mid - 1;
		}
		return end;
	}

	static void dfs(List<Integer> list, int start, int end, int sum){
		if(sum > c)
			return;
		if(start == end){
			list.add(sum);
			return;
		}
		dfs(list, start+1, end, sum);
		dfs(list, start+1, end, sum + arr[start]);
	}
}