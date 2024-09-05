package week33_DynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main_2550_G3_전구_신문영 {

    public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());

		
		st = new StringTokenizer(br.readLine());
		int[] switches = new int[N];
		Map<Integer, Integer> switchMap = new HashMap<>();
		for (int i = 0; i < N; i++) {
			switches[i] = Integer.parseInt(st.nextToken());
			switchMap.put(switches[i], i);
		}

		st = new StringTokenizer(br.readLine());
		int[] bulbs = new int[N];
		int[] connection = new int[N];
		for (int i = 0; i < N; i++) {
			int bulb = Integer.parseInt(st.nextToken());
			bulbs[i] = bulb;
			int switchNo = switchMap.get(bulb);
			connection[i] = switchNo + 1; 
		}
		
		int[] idxOrder = new int[N];
		int[] LIS = new int[N];
		Arrays.fill(LIS, Integer.MAX_VALUE);
		int LISLength = 0;
		for (int i = 0; i < N; i++) {
			int insertionPoint = Arrays.binarySearch(LIS, connection[i]);
			int idx = -(insertionPoint + 1);
			LIS[idx] = connection[i];
			idxOrder[i] = idx;
			LISLength = Math.max(LISLength, idx);
		}
		
		int answerIdx = LISLength;
		int[] answer = new int[answerIdx + 1];
		for (int i = N - 1; i >= 0; i--) {
			if (idxOrder[i] == answerIdx) {
				answer[answerIdx] = connection[i];
				answerIdx--;
			}
			
			if (answerIdx < 0) break;
		}

		System.out.println(LISLength + 1);
		Arrays.stream(answer)
			.map(idx -> switches[idx - 1])
			.sorted()
			.forEach(item -> System.out.print(item + " "));
    }
}