package week48_Geometry;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_1069_G3_집으로_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static double X, Y, D, T;
	
	static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		X = Double.parseDouble(st.nextToken());
		Y = Double.parseDouble(st.nextToken());
		D = Double.parseDouble(st.nextToken());
		T = Double.parseDouble(st.nextToken());
	}

	static void solve() {
		sb.append(cal());
	}
	
	static double cal() {
		double dist = Math.sqrt(X * X + Y * Y);
		double time = 0;
		
		if(D > T && dist > T) {
			while(dist > D * 2) {
				dist -= D;
				time += T;
			}
			double noJump = time + dist;
			double oneJump = time + T + Math.abs(dist - D);
			double twoJump = time + T * 2;
			
//			System.out.println("noJump : " + noJump);
//			System.out.println("oneJump : " + oneJump);
//			System.out.println("twoJump : " + twoJump);
			
			double Jump = oneJump < twoJump ? oneJump : twoJump;
			
			time = noJump < Jump ? noJump : Jump;
		}
		else 
			time = dist;
		
		return time;
	}
	
	public static void main(String[] args) throws IOException {
		input();
		solve();

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
