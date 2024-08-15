package week35_BFS_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main_16954_G3_움직이는미로탈출_신문영 {
	final static char EMPTY = '.';
	final static char WALL = '#';
	static int[][] direction = {
			{0, 0},
			{0, -1},
			{0, 1},
			{-1, 0},
			{1, 0},
			{1, -1},
			{1, 1},
			{-1, 1},
			{-1, -1},
	};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[][] chessBoard = new char[8][8];
		for (int i = 0; i < 8; i++) {
			String input = br.readLine();
			for (int j = 0; j < 8; j++) {
				chessBoard[i][j] = input.charAt(j);
			}
		}
		
		char[][][] chessBoards = new char[9][8][8];
		chessBoards[0] = chessBoard;
		
		for (int i = 1; i < 9; i++) {
			char[][] nextChessBoard = new char[8][8];
			
			for (int x = 7; x >= 0; x--) {
				for (int y = 0; y < 8; y++) {
					if (x - 1 >= 0 && chessBoards[i - 1][x - 1][y] == WALL) {
						nextChessBoard[x][y] = WALL;
						continue;
					}
					
					nextChessBoard[x][y] = EMPTY;
				}
			}
			
			chessBoards[i] = nextChessBoard;
		}
		
		Queue<Integer> queue = new ArrayDeque<>();
		Queue<Integer> nextCharacters = new ArrayDeque<>();
		queue.add((7 << 3) + 0);
		nextCharacters.add((7 << 3) + 0);
		
		int round = 0;
		chessBoard = chessBoards[round];
		boolean find = false;
		while (!queue.isEmpty()) {
			int current = queue.poll();
			int currentI = (current & (7 << 3)) >> 3;
			int currentJ = current & 7;
			
			if (currentI == 0 && currentJ == 7) {
				find = true;
				break;
			}
			
			for (int k = 0; k < direction.length; k++) {
				int nextI = currentI + direction[k][0];
				int nextJ = currentJ + direction[k][1];
				
				if (!(nextI >= 0 && nextJ >= 0 && nextI < 8 && nextJ < 8)) continue;
				if (chessBoard[nextI][nextJ] == WALL) continue;
				
				nextCharacters.add((nextI << 3) + nextJ);
			}
			
			if (queue.isEmpty()) {
				if (nextCharacters.isEmpty()) break;
				round = round + 1 < 9 ? round + 1 : 8;
				
				chessBoard = chessBoards[round];
				
				while (!nextCharacters.isEmpty()) {
					int nextCharacter = nextCharacters.poll();
					int nextCharacterI = (nextCharacter & (7 << 3)) >> 3;
					int nextCharacterJ = nextCharacter & 7;
					if (chessBoard[nextCharacterI][nextCharacterJ] == EMPTY) {
						queue.add((nextCharacterI << 3) + nextCharacterJ);
					}
				}
			}
		}
		
		System.out.println(find ? 1 : 0);
	}
}