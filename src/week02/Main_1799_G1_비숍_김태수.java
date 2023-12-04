package week02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main_1799_G1_비숍_김태수 {

	static int whiteMaxVal,blackMaxVal, N;
	static ArrayList<int[]> black, white;
	static boolean[][] blackV, whiteV;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		int[][] map = new int[N][N];
		StringTokenizer st;
		black = new ArrayList<>();
		white = new ArrayList<>();
		
		blackMaxVal = -1;
		whiteMaxVal = -1;
		for(int i = 0 ; i < N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0;j<N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if((i+j) % 2 == 0 && map[i][j] == 1) black.add(new int[] {i,j});
				if((i+j) % 2 == 1 && map[i][j] == 1) white.add(new int[] {i,j});
			}
		}
//		for(int[] next: black) {
//			System.out.println(Arrays.toString(next));
//		}
//		System.out.println();
//		for(int[] next: white) {
//			System.out.println(Arrays.toString(next));
//		}
		
		for(int i = 0, end= black.size();i < end;i++) {
			blackV = new boolean[N][N];
			dfsBlack(1,black.get(i));
		}
		for(int i = 0,end= white.size(); i < end;i++) {
			whiteV = new boolean[N][N];
			dfsWhite(1,white.get(i));
		}
		System.out.println(blackMaxVal + whiteMaxVal);
		//System.out.println(whiteMaxVal);
	}
	
	public static void dfsBlack(int count, int[] cur) {
		if(!check(cur, black, blackV)) return;
		blackV[cur[0]][cur[1]] = true;
		blackMaxVal = Math.max(blackMaxVal, count);
		
		for(int[] next: black) {
			if(blackV[next[0]][next[1]]) continue;
			dfsBlack(count+1,next);
			//blackV[next[0]][next[1]] = false;
		}
		blackV[cur[0]][cur[1]] = false;
	}
	public static void dfsWhite(int count,int[] cur) {
		
		if(!check(cur,white, whiteV)) return;
		whiteV[cur[0]][cur[1]] = true;
		whiteMaxVal = Math.max(whiteMaxVal, count);
//		if(count == 2) {
//			System.out.println(Arrays.toString(cur));
//			for(boolean[] next: whiteV) {
//				System.out.println(Arrays.toString(next));
//			}
//			System.out.println();
//		}
		for(int[] next: white) {
			if(whiteV[next[0]][next[1]]) continue;
			dfsWhite(count+1,next);
			//whiteV[next[0]][next[1]] = false;
		}
		whiteV[cur[0]][cur[1]] = false;
	}
	
	public static boolean check(int[] target, ArrayList<int[]> bw, boolean[][] visited) {
		int sum = target[0] + target[1];
		int dif = target[0] - target[1];
		for(int i = 0 ; i < N;i++) {
			//System.out.println(Arrays.toString(new int[] {i, sum-i, dif+i}));
			//System.out.println(visited[i][i-sum]);
			//System.out.println(visited[i][dif+i]);
			for(int[] next: bw) {
				if(sum-i >= 0 && next[0] == i && next[1] == sum -i && visited[i][sum-i]) return false;
				if(dif+i < N && next[0] == i && next[1] == i + dif && visited[dif+i][i]) return false;
			}
		}
		return true;
	}
}
