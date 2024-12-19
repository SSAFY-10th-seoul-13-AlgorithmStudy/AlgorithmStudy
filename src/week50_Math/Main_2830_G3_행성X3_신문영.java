package week50_Math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2830_G3_행성X3_신문영 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		
        int[] num = new int[20];

        for (int i = 0; i < N; i++) {
            int person = Integer.parseInt(br.readLine());

            int k = 0;
            while (person > 0) {
                num[k++] += person % 2;
                person /= 2;
            }
        }

        long sum = 0;
        for (int i = 0; i < 20; i++) {
            sum += (1 << i) * (long)num[i] * (N - num[i]);
        }
        System.out.println(sum);
	}
}
