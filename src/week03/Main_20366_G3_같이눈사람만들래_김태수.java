package week03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_20366_G3_같이눈사람만들래_김태수 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] list = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N;i++) {
			list[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(list);
		//이웃하는 두 수가 최솟값인 쌍을 두개 찾는다!
//		int minA = list[1] - list[0];
//		int minB = list[1] - list[0];
//		for(int i = 0 ; i < N - 1;i++) {
//			int temp = list[i+1] - list[i];
//			if(temp < minB) {
//				minB = temp;
//				if(temp < minA) {
//					minB = minA;
//					minA = temp;
//					i++;
//				}
//			} 
//		}
//		System.out.println(minA);
//		System.out.println(minB);
//		System.out.println(minA + minB);
		int answer = Integer.MAX_VALUE;
		for(int i = 0; i <N-3;i++) {
			for(int j = i + 3; j < N;j++) {
				int start = i +1;
				int end = j -1;
				while(start < end) {
					int snow1 = list[i] + list[j];
					int snow2 = list[start] + list[end];
					
					int diff = snow2 - snow1;
					answer = Math.min(answer, Math.abs(diff));
					if(diff>0) end--;
					else start++;
				}
			}
		}
		System.out.println(answer);
		
	}
}
