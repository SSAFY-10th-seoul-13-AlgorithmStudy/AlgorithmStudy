package week11_BinarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2110_G4_공유기설치_김태수 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		int[] list = new int[N];
		
		for(int i = 0; i < N;i++) {
			int temp = Integer.parseInt(br.readLine());
			list[i] = temp;
		}
		Arrays.sort(list);
		int result = 0;
		if(C == 2) {
			System.out.println(list[N-1] - list[0]);
		}
		else {
			int right = list[N-1] - list[0];
			int left = 1;
			int mid = 0;
			while(left < right) {
				mid = (right - left) / 2 + left;
				int count = 1;
				int now = list[0];
				for(int i = 0;i<N;i++) {
					if((list[i] - now) >= mid) {
						count++;
						now = list[i];
					}
				}
				
				if(count >= C) {
					result = mid;
					left = mid +1;
				}
				else {
					right = mid;
				}
			}
			System.out.println(result);
		}
		
	}
}
