package week66_DynamicProgramming;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1053_G1_펠린드롬공장_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	/**
	 * 4가지 연산이 가능
	 * 1. 문자열의 어떤 위치에 어떤 문자를 삽입 (시작과 끝도 가능)
	 * 2. 어떤 위치에 있는 문자를 삭제
	 * 3. 어떤 위치에 있는 문자를 교환
	 * 4. 서로 다른 문자를 교환
	 * 여기서 4번은 가장 먼저 시행되어야 이득(1,2,3번 수행으로 같은 결과를 만들 수 있음)
	 * 1번 추가와 3번 교환은 같게 쳐도 되는것 아닐까..?
	 */
	static void input_solve() throws IOException{
		String str = br.readLine();
		char[] arr = str.toCharArray();
		
		int res = pelindrome(arr); // 초기 상태에서 펠린드롬 만들 수 있는 연산의 최소값
//		System.out.println(res);
		
		for(int i = 0; i < arr.length - 1; i++) {
			for(int j = i + 1; j < arr.length; j++) { // 4번 연산을 모든 요소에 대해 체크
				if(arr[i] == arr[j]) continue;
				
				swap(i, j, arr);
				res = Math.min(res, pelindrome(arr) + 1);
				swap(j, i, arr);
			}
		}
		sb.append(res);
	}
	
	/**
	 * dp[i][j] : 인덱스 i부터 j까지 펠린드롬을 만들 수 있는 연산의 최소값 
	 */
	static int pelindrome(char[] arr) {
		int size = arr.length;
		int dp[][] = new int[size][size];
		for(int i = 0; i < size; i++) {
			dp[i][i] = 0;
			if(i != size - 1)
				dp[i][i + 1] = arr[i] == arr[i + 1] ? 0 : 1; // i + 1과 다르면 연산이 필요하기 떄문
		}
		
		for(int i = 2; i < size; i++) {
			for(int j = 0; j < size - i; j++) {
				int cal1 = Math.min(dp[j + 1][j + i] + 1, dp[j][j + i - 1] + 1); // 왼쪽 또는 오른쪽에 삭제한 경우
				int cal2 = dp[j + 1][j + i - 1] + (arr[j] == arr[j + i] ? 0 : 1);
				dp[j][j + i] = Math.min(dp[j][j + i], cal1);
				dp[j][j + i] = Math.min(cal1, cal2);
			}
		}
		return dp[0][size - 1];
	}
	
	static void swap(int a, int b, char[] arr) {
		char temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}
	
	public static void main(String[] args) throws IOException{
		input_solve();
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
