package week07_DynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_G2_가장긴증가하는부분수열2_김태수 {	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		int[] list = new int[N];
		int[] lis = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N;i++) {
			list[i] = Integer.parseInt(st.nextToken());
		}
		lis[0] = list[0];
		int idx = 1;
		for(int i = 0 ; i < N;i++) {
			int current = list[i];
			if(lis[idx-1] < current) {
				idx++;
				lis[idx-1] = current;
			}
			//binary search
			else {
				int left = 0;
				int right = idx;
				
				while(left< right) {
					int mid = (right-left)/2 + left;
					//void result = lis[mid] < current ? left = mid+1 : right = mid;
					
					if(lis[mid] < current) left = mid +1;
					else right = mid;					
				}
				
				lis[left] = current;
			}
		}
		System.out.println(idx);
		
	}
}
