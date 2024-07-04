package week29_BFS_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_9207_G4_페그솔리테어_신문영 {
	static final char EMPTY = '.';
	static final char PIN = 'o';
	static int pinCount;
	static int minPin;
	static int minMove;
	static int[][] direction = {{0, 2}, {0, -2}, {2, 0}, {-2, 0}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testcase = Integer.parseInt(br.readLine());
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < testcase; i++) {
			pinCount = 0;
			
			char[][] map = new char[5][9];
			for (int j = 0; j < 5; j++) {
				String line = new StringTokenizer(br.readLine()).nextToken();
				for (int k = 0; k < 9; k++) {
					map[j][k] = line.charAt(k);
					if (map[j][k] == PIN) pinCount++;
				}
			}
			
			if (i != testcase - 1) br.readLine();
			
			minPin = pinCount;
			minMove = 0;
			dfs(map, 0, 0);
			
			stringBuilder.append(minPin + " "  + minMove + "\n");
		}
		
		stringBuilder.delete(stringBuilder.length() - "\n".length(), stringBuilder.length());
		
		System.out.println(stringBuilder);
	}
	
	static void dfs(char[][] map, int removedPinCount, int moveCount) {
		boolean isDone = true;
		for (int r = 0; r < 5; r++) {
			for (int c = 0; c < 9; c++) {
				if (map[r][c] == PIN) {
					
					for (int k = 0; k < 4; k++) {
						int nextI = r + direction[k][0];
						int nextJ = c + direction[k][1];
						int adjI = r + direction[k][0] / 2;
						int adjJ = c + direction[k][1] / 2;
						
						if (!(nextI >= 0 && nextJ >= 0 && nextI < 5 && nextJ < 9)) continue;
						if (!(adjI >= 0 && adjJ >= 0 && adjI < 5 && adjJ < 9)) continue;
						if (map[nextI][nextJ] != EMPTY) continue;
						if (map[adjI][adjJ] != PIN) continue;
						
						isDone = false;
						
						map[r][c] = EMPTY;
						map[adjI][adjJ] = EMPTY;
						map[nextI][nextJ] = PIN;
						
						dfs(map, removedPinCount + 1, moveCount + 1);
						
						map[r][c] = PIN;
						map[adjI][adjJ] = PIN;
						map[nextI][nextJ] = EMPTY;
					}
					
				}
			}
		}
		
		if (isDone) {
			if (pinCount - removedPinCount < minPin) {
				minPin = pinCount - removedPinCount;
				minMove = moveCount;
			}
		}
	}
}
