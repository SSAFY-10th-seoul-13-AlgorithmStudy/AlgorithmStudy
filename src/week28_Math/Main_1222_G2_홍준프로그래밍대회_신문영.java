package week28_Math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1222_G2_홍준프로그래밍대회_신문영 {
	static int student;
	static int[] divCounter;
	static boolean[] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] divs = new int[1416];
		divCounter = new int[2000000 + 1];
		visited = new boolean[2000000 + 1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			student = Integer.parseInt(st.nextToken());
			
			int divsIdx = 0;
			int max = student;
			if (student >= 9) max = (int) Math.sqrt((double) student);
			for (int j = 1; j <= max; j++) {
				if (student % j == 0) {
					recordDiv(j);
					divs[divsIdx++] = j;
				}
			}
			
			for (int j = 0; j < divs.length; j++) {
				if (divs[j] == 0) break;
				visited[divs[j]] = false;
				visited[student / divs[j]] = false;
				divs[j] = 0;
			}
		}
		
		long answer = 0L;
		for (int div = 1; div <= 2000000; div++) {
			if (divCounter[div] <= 1) continue;
			answer = Math.max(answer, (long) div * (long) divCounter[div]);
		}
		
		System.out.println(answer);
	}
	
	public static void recordDiv(int div) {
		if (!visited[div]) {
			divCounter[div]++;
			visited[div] = true;
		}
		
		if (!visited[student / div]) {
			divCounter[student / div]++;
			visited[student / div] = true;
		}
	}
}
