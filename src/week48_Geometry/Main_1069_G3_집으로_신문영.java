package week48_Geometry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1069_G3_집으로_신문영 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int X = Integer.parseInt(st.nextToken());
		int Y = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		
		double dist = Math.sqrt(X * X + Y * Y);
		double answer = dist;
		
		if (D >= T) {
			int jump = (int) (dist / D);
			dist = dist % D;
			
			if (jump == 0) {
				answer = Math.min(answer, Math.min(T + D - dist, T * 2));
			} else {
				answer = Math.min(answer, Math.min(jump * T + dist, (jump + 1) * T));
			}
		}

		System.out.println(answer);
	}

}
