package week11_BinarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2110_G4_공유기설치_김희연 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());

		int[] house = new int[n];
		
		for(int i=0; i<n; i++){
			house[i] = Integer.parseInt(br.readLine());
		}

		Arrays.sort(house);

		int result = 0;
		int start = 1; //최소 간격
		int end = house[n-1] - house[0]; //최대 간격

		while(start <= end){
			int cnt = 1;
			int before = house[0];

			int mid = (start + end) / 2;

			for(int i=1; i<n; i++){
				if(house[i] - before >= mid){
					cnt++;
					before = house[i];
				}
			}

			if(cnt >= c){
				result = mid;
				start = mid+1;
			} else
				end = mid-1;
		}

		System.out.println(result);
	}
}