package week05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_19535_G3_ㄷㄷㄷㅈ_김태수 {
	static ArrayList<int[]> edges;
	static int N;
	static long totalD, totalG;
	static long[] child;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		edges = new ArrayList<>();
		child = new long[N+1];
		
		StringTokenizer st;
		for(int i = 1 ; i < N;i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			child[from]++;
			child[to]++;
			edges.add(new int[] {from,to});
		}
		findD();
		findG();
		//System.out.println(totalD);
		//System.out.println(totalG);
		if(totalD == totalG * 3) {
			System.out.println("DUDUDUNGA");
		}
		else if(totalD > totalG * 3) {
			System.out.println("D");
		}
		else {
			System.out.println("G");
		}	
	}
	public static void findD() {
		for(int[] edge: edges) {
			totalD += (child[edge[0]] - 1 ) * (child[edge[1]] - 1);
		}
	}
	public static void findG() {
		for(int i = 1 ; i<= N ;i++) {
			if(child[i] >= 3) {
				totalG += (child[i]) * (child[i]-1) * (child[i] - 2) / 6;
			}
		}
	}
}
