package week01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2636_G4_치즈_김태수 {
	static int r,c,count;
	static int[][] map;
	static int[][] direction = {
			{1,0},
			{0,1},
			{-1,0},
			{0,-1}
	};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		map = new int[r][c];
		count = 0;
		int answer = 0;
		ArrayList<Integer> list = new ArrayList<>();
		for(int i = 0 ; i < r;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < c;j++) {
				int temp = Integer.parseInt(st.nextToken());
				map[i][j] = temp;
				if(temp == 1) count++;
			}
		}
		list.add(count);
		while(count > 0) {
			changeTo2();
			rot();
			clearMap();
			answer++;
			list.add(count);
			//printMap();
		}
		
		System.out.println(answer);
		if(list.size() >= 2) System.out.println(list.get(list.size() - 2));
		else System.out.println(list.get(0));
		
	}
	
	public static void changeTo2() {
		Queue<int[]> qq = new LinkedList<>();
		//테두리는 치즈가 없는게 보장
		qq.add(new int[] {0,0});
		map[0][0] = 2;
		while(!qq.isEmpty()) {
			int[] cur = qq.poll();
			for(int[] dir:direction) {
				int dx = cur[0] + dir[0];
				int dy = cur[1] + dir[1];
				if(dx >= r || dx < 0 || dy >= c || dy < 0) continue;
				if(map[dx][dy] == 0) {
					map[dx][dy] = 2;
					qq.add(new int[] {dx,dy});
				} 
			}
		}
	}
	
	public static void rot() {
		for(int i = 0 ; i < r; i++) {
			for(int j = 0; j < c;j++) {
				if(map[i][j] == 1) {
					for(int[] dir:direction) {
						int dx = i + dir[0];
						int dy = j + dir[1];
						if(dx >= r || dx < 0 || dy >= c || dy < 0) continue;
						if(map[dx][dy] == 2) {
							count--;
							map[i][j] = 0;
							break;
						}
					}
				}
			}
		}
	}
	
	public static void clearMap() {
		for(int i = 0; i < r;i++) {
			for(int j = 0; j < c;j++) {
				if(map[i][j] == 2) map[i][j] = 0;
			}
		}
	}
	
	public static void printMap() {
		for(int i = 0; i<r;i++) System.out.println(Arrays.toString(map[i]));
		System.out.println();
	}
}
