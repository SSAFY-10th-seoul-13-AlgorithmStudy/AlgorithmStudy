package week37_Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1083_G4_소트_신문영 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int S = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			int target = arr[i];
			int idx = i + 1;
			int cnt = 0;
			int maxExchangeable = 0;
			int maxExchangeableIdx = 0;
			int maxCnt = 0;
			boolean isFound = false;
			
			while (cnt < S && idx < N) {
				cnt++;

				if (arr[idx] > target) {
					isFound = true;
					if (arr[idx] > maxExchangeable) {
						maxExchangeable = arr[idx];
						maxExchangeableIdx = idx;
						maxCnt = cnt;
					}
				}
				
				idx++;
			}
			
			if (isFound) {
				while (maxExchangeableIdx > i) {
					arr[maxExchangeableIdx] ^= arr[maxExchangeableIdx - 1];
					arr[maxExchangeableIdx - 1] ^= arr[maxExchangeableIdx];
					arr[maxExchangeableIdx] ^= arr[maxExchangeableIdx - 1];
					maxExchangeableIdx--;
				}
				
				S -= maxCnt;
			}
			
			if (S == 0) break; 
		}
		
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < N; i++) {
			stringBuilder.append(arr[i]).append(" ");
		}
		
		System.out.println(stringBuilder.delete(stringBuilder.length() - " ".length(), stringBuilder.length()));
	}
}
