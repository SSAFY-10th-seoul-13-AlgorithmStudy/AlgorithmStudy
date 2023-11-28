package week04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1184_G1_귀농_김태수 {
	static int[][] map;
	static int N;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		StringTokenizer st;
		for(int i = 0 ; i <N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int result = 0;
		for(int i = 0 ; i < N-1;i++) {
			for(int j = 0; j< N-1;j++) {
				result += validate(i,j);
			}
		}
		System.out.println(result);
	}
	
	public static int validate(int x,int y) {
		ArrayList<Integer> left = new ArrayList<>();
		ArrayList<Integer> right = new ArrayList<>();
		int[][] tempMap = new int[N][N];
		int count = 0;
		//1사분면 조사
		for(int i = x; i >= 0; i--) {
			for(int j = y+1;j < N;j++) {
				tempMap[i][j] = tempMap[i+1][j] + tempMap[i][j-1] + map[i][j] - tempMap[i+1][j-1];
				right.add(tempMap[i][j]);
			}
		}
		//3사분면 조사
		tempMap = new int[N][N];
		for(int i = x+1; i<N;i++) {
			for(int j = y;j>=0;j--) {
				tempMap[i][j] = tempMap[i-1][j] + tempMap[i][j+1] + map[i][j] - tempMap[i-1][j+1];
				left.add(tempMap[i][j]);
			}
		}
		//같은게 있는지 찾기
		for(int i = 0,end=left.size();i<end;i++) {
			int temp = left.get(i);
			for(int j = 0,end1=right.size();j<end1;j++) {
				if(right.get(j) == temp) count++; 
			}
		}
		right = new ArrayList<>();
		left = new ArrayList<>();
		//4사분면 조사
		tempMap = new int[N][N];
		for(int i = x+1; i<N;i++) {
			for(int j = y+1;j<N;j++) {
				tempMap[i][j] = tempMap[i-1][j] + tempMap[i][j-1] + map[i][j] - tempMap[i-1][j-1];
				right.add(tempMap[i][j]);
			}
		}
		//2사분면 조사
		tempMap = new int[N][N];
		for(int i = x; i>=0;i--) {
			for(int j = y;j>=0;j--) {
				tempMap[i][j] = tempMap[i+1][j] + tempMap[i][j+1] + map[i][j] - tempMap[i+1][j+1];
				left.add(tempMap[i][j]);
			}
		}
		//같은거 세기
		for(int i = 0,end=left.size();i<end;i++) {
			int temp = left.get(i);
			for(int j = 0,end1=right.size();j<end1;j++) {
				if(right.get(j) == temp) count++; 
			}
		}
		
		return count;
	}
	
	
}
