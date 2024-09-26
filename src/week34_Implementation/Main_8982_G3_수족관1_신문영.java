package week34_Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_8982_G3_수족관1_신문영 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int[] water = new int[40000];
		int[] floor = new int[40000];

		st  = new StringTokenizer(br.readLine());
		int lastX = Integer.parseInt(st.nextToken());
		int length = 0;
		for (int i = 1; i < N; i++) {
			st  = new StringTokenizer(br.readLine());
			int currentX = Integer.parseInt(st.nextToken());
			int currentY = Integer.parseInt(st.nextToken());
			
			if (lastX != currentX) {
				for (int j = lastX; j < currentX; j++) {
					floor[j] = water[j] = currentY;
				}
			}
			
			lastX = length = currentX;
		}
		
		int K = Integer.parseInt(br.readLine());
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int leftX = Integer.parseInt(st.nextToken());
			int height = Integer.parseInt(st.nextToken());
			int rightX = Integer.parseInt(st.nextToken());
			
			for (int j = leftX; j < rightX; j++) {
				water[j] = 0;
			}
			
			if (leftX - 1 >= 0) {
				int leftHeight = height;
				for (int left = leftX - 1; left >= 0; left--) {
					if (floor[left] < leftHeight) leftHeight = floor[left];
					if (floor[left] - leftHeight < water[left]) water[left] = floor[left] - leftHeight;
				}
			}
			
			if (rightX < length) {
				int rightHeihgt = height;
				for (int right = rightX; right < length; right++) {
					if (floor[right] < rightHeihgt) rightHeihgt = floor[right];
					if (floor[right] - rightHeihgt < water[right]) water[right] = floor[right] -rightHeihgt; 
				}
			}
		}
		
		int answer = 0;
		for (int i = 0; i < length; i++) {
			answer += water[i];
		}
		
		System.out.println(answer);
	}
}