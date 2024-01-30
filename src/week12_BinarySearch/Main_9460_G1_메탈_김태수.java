package week12_BinarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
// 골4 공유기 문제랑 비슷..? 찾는 값을 이분탐색한다 

public class Main_9460_G1_메탈_김태수 {
	static int N,K,list[][];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		while(T-->0) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 점 개수
			K = Integer.parseInt(st.nextToken()); // 터널 개수
			
			st = new StringTokenizer(br.readLine());
			list = new int[N][2];
			for(int i = 0 ; i < N;i++) {
				int[] temp = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
				list[i] = temp;
			}
			Arrays.sort(list,(a,b)->{
				return a[0] - b[0];
			});
			double right = 200000001;
			double left = 0;
			double mid = 0;
			while(0.01 < (right - left)) {
				//mid가 조건을 만족한다면 더 작은 값을 찾으러 right을 내리고, 만족하지 않다면 큰 값을 위해 left 올린다
				mid = (right + left) / (double)2;
				if(count(mid)) {
					right = mid;
				}
				else left = mid;
			}
			System.out.printf("%.1f\n",mid);
		}
		
	}
	
	static public boolean count(double M) {
		int stamp = 1;
		int min = list[0][1];
		int max = list[0][1];
		
		for(int i = 1 ; i < N;i++) {
			if(list[i][1] < min) min = list[i][1];
			if(list[i][1] > max) max = list[i][1];
			//지금까지 찾은 최소 최대 광물의 중간값((max-min)/2)이 원하는 값(M)을 넘으면 터널을 나눈다.
			//min, max를 현재 광물로 초기화해서 다시 탐색
			if((max - min) >= M * 2) {
				min = list[i][1];
				max = list[i][1];
				stamp++;
			}
		}
		
		return stamp <= K;
	}
}
