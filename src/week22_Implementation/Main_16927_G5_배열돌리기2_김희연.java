package week22_Implementation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main_16927_G5_배열돌리기2_김희연 {
	static int N, M, R;
	static int arr[][];
	static int[] dx = {0,1,0,-1}; 
	static int[] dy = {1,0,-1,0};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		arr = new int[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int n = N, m = M;
		for(int i=0; i<Math.min(N,M)/2; i++) {
			rotate(i, (n+m)*2-4); //각 변의 길이를 모두 더하면 꼭지점에서 4가 추가되므로 4를 빼줌
			n -= 2; //다음 그룹으로 넘어가면 각 변의 길이가 2씩 줄어듬
			m -= 2;
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				sb.append(arr[i][j] + " ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
	
	public static void rotate(int start, int len) {
		int s = R%len; //반복되는 연산 최소화
		
		for(int i=0; i<s; i++) {
			int direction = 0;
			int x = start;
			int y = start;
			int temp = arr[x][y]; //마지막에 넣을 값
			
			while(direction < 4) {
				int nx = x + dx[direction];
				int ny = y + dy[direction];
				
				if(nx>=start && ny>=start && nx<N-start && ny<M-start) {
					arr[x][y] = arr[nx][ny];
					x = nx;
					y = ny;
				}
				else
					direction++;
			}
			arr[start+1][start] = temp;
		}
	}
}