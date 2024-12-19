package week50_Implementation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_20061_G2_모노미노도미노_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static boolean green[][], blue[][];
	
	static void init() {
		green = new boolean[6][4];
		blue = new boolean[6][4];
	}
	
	static void input_solve() throws IOException {
		init();
		int N = Integer.parseInt(br.readLine());
		int result = 0;
		int cnt = 0;
		
		for(int i = 0 ; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			result += block(t, x, y);
		}
		
		
		for(int i = 2; i < 6; i++) {
			for(int j = 0; j < 4; j++) {
				if(green[i][j]) cnt++;
				if(blue[i][j]) cnt++;
			}
		}
		
		sb.append(result).append("\n").append(cnt);
	}
	
	static int block(int t, int x, int y) {
		int ret = 0;
		
		slide(t, x, y);
		ret = get_score();		
		check_special();
		
		return ret;
	}
	
	static void slide(int t, int x, int y) {
		int cur;
		
		if(t == 1) {
			cur = 0;
			while(true) {
				if(cur == 5)
					break;
				if(green[cur + 1][y])
					break;
				cur++;
			}
			green[cur][y] = true;

			cur = 0;
			while(true) {
				if(cur == 5)
					break;
				if(blue[cur + 1][x])
					break;
				cur++;
			}
			blue[cur][x] = true;
		} else if(t == 2) {
			cur = 0;
			while(true) {
				if(cur == 5)
					break;
				if(green[cur + 1][y] || green[cur + 1][y + 1])
					break;
				cur++;
			}
			green[cur][y] = true;
			green[cur][y + 1] = true;
			
			cur = 1;
			while(true) {
				if(cur == 5)
					break;
				if(blue[cur + 1][x])
					break;
				cur++;
			}
			blue[cur][x] = true;
			blue[cur - 1][x] = true;
		} else if(t == 3) {
			cur = 1;
			while(true) {
				if(cur == 5)
					break;
				if(green[cur + 1][y])
					break;
				cur++;
			}
			green[cur][y] = true;
			green[cur - 1][y] = true;
			
			cur = 0;
			while(true) {
				if(cur == 5)
					break;
				if(blue[cur + 1][x] || blue[cur + 1][x + 1])
					break;
				cur++;
			}
			blue[cur][x] = true;
			blue[cur][x + 1] = true;
		}
	}

	static int get_score() {
		int ret = 0;
		boolean green_flag, blue_flag;
		
		for(int i = 2; i < 6; i++) {
			green_flag = true;
			blue_flag = true;
			for(int j = 0; j < 4; j++) {
				if(!green[i][j]) // 빈칸 있으면 false
					green_flag = false;
				if(!blue[i][j])
					blue_flag = false;
			}
			if(green_flag) { // 한 줄 꽉 찬 경우
				ret++;
				int temp = i;
				while(temp > 0) {
					for(int j = 0; j < 4; j++) {
						green[temp][j] = green[temp - 1][j]; // 한칸 내리기
					}
					temp--;
				}
			}
			if(blue_flag) {
				ret++;
				int temp = i;
				while(temp > 0) {
					for(int j = 0; j < 4; j++) {
						blue[temp][j] = blue[temp - 1][j]; // 한칸 내리기
					}
					temp--;
				}
			}
		}
		
		return ret;
	}
	
	static void check_special() {
		boolean green_flag, blue_flag;
		for(int i = 0; i < 2; i++) {
			green_flag = true;
			blue_flag = true;
			for(int j = 0; j < 4; j++) {
				if(green[i][j]) // 처리할 블록이 남아있으면 false
					green_flag = false;
				if(blue[i][j])
					blue_flag = false;
			}
			if(!green_flag) {
				int temp = 5;
				while(temp > 0) {
					for(int j = 0; j < 4; j++) {
						green[temp][j] = green[temp - 1][j]; // 내리기
					}
					temp--;
				}
			}
			if(!blue_flag) {
				int temp = 5;
				while(temp > 0) {
					for(int j = 0; j < 4; j++) {
						blue[temp][j] = blue[temp - 1][j]; // 내리기
					}
					temp--;
				}
			}
		}
		
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 4; j++) {
				green[i][j] = false;
				blue[i][j] = false;
			}
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		input_solve();

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
