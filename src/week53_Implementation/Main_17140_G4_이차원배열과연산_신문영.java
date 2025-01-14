package week53_Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Main_17140_G4_이차원배열과연산_신문영 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken()) - 1;
		int c = Integer.parseInt(st.nextToken()) - 1;
		int k = Integer.parseInt(st.nextToken());
		int[][] matrix = new int[3][3];
		for (int i = 0; i < matrix.length; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < matrix.length; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int time = 0;
		while (time <= 100) {
			if (matrix.length > r && matrix[r].length > c && matrix[r][c] == k) break;
			time++;

			int rowSize = matrix.length;
			int colSize = 0;
			for (int[] row : matrix) {
				colSize = Math.max(colSize, row.length);
			}
			
			if (rowSize >= colSize) {
				matrix = RCalculate(matrix);
			} else {
				matrix = CCalculate(matrix);
			}
		}
		
		System.out.println(time > 100 ? -1 : time);
	}
	
	static int[][] RCalculate(int[][] matrix) {
		int maxRowSize = 0; 
		for (int i = 0; i < matrix.length; i++) {
			int rowSize = 0;
			boolean[] visited = new boolean[101];
			for (int j = 0; j < matrix[i].length; j++) {
				if (matrix[i][j] != 0 && !visited[matrix[i][j]]) {
					visited[matrix[i][j]] = true;
					rowSize++; 
				}
			}
			
			maxRowSize = Math.max(maxRowSize, rowSize);
		}
		
		int[][] newMatrix = new int[matrix.length][maxRowSize * 2];
		for (int i = 0; i < matrix.length; i++) {
			int[] frequency = new int[101];
			for (int j = 0; j < matrix[i].length; j++) {
				if (matrix[i][j] > 0) frequency[matrix[i][j]]++;
			}
			
			List<Pair> list = new ArrayList<>();
			for (int j = 100; j >= 1; j--) {
				if (frequency[j] > 0) {
					list.add(new Pair(j, frequency[j]));
				}
			}
			
			list.sort(null);
			
			while (list.size() < maxRowSize) {
				list.add(new Pair(0, 0));
			}
			
			newMatrix[i] = list.stream()
							.flatMapToInt(pair -> IntStream.of(pair.number, pair.frequency))
							.limit(100)
							.toArray();
		}
		
		return newMatrix;
	}
	
	static int[][] CCalculate(int[][] matrix) {
		int[][] reversedMatrix = new int[matrix[0].length][matrix.length];
		
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				reversedMatrix[j][i] = matrix[i][j];
			}
		}
		
		reversedMatrix = RCalculate(reversedMatrix);
		
		int[][] result = new int[reversedMatrix[0].length][reversedMatrix.length];
		for (int i = 0; i < reversedMatrix[0].length; i++) {
			for (int j = 0; j < reversedMatrix.length; j++) {
				result[i][j] = reversedMatrix[j][i];
			}
		}
		
		return result;
	}
	
	static class Pair implements Comparable<Pair> {
		int number;
		int frequency;
		
		Pair(int number, int frequency) {
			this.number = number;
			this.frequency = frequency;
		}
		
		@Override
		public int compareTo(Pair o) {
			if (this.frequency == o.frequency) return Integer.compare(this.number, o.number);
			return Integer.compare(this.frequency, o.frequency);
		}
	}
}
