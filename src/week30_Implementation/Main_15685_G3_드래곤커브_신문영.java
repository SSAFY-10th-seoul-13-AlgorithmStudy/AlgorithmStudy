package week30_Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Main_15685_G3_드래곤커브_신문영 {

	public static void main(String[] args) throws IOException {
		boolean[][] map = new boolean[101][101];
		int[][] direction = {{1, 0}, {0, -1}, {-1, 0}, {0, 1}};
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(stringTokenizer.nextToken());
		
		for (int i = 0; i < N; i++) {
			stringTokenizer = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(stringTokenizer.nextToken());
			int y = Integer.parseInt(stringTokenizer.nextToken());
			int d = Integer.parseInt(stringTokenizer.nextToken());
			int g = Integer.parseInt(stringTokenizer.nextToken());
			
			List<List<DragonCurve>> dragonCurveGenerations = new ArrayList<>();
			List<DragonCurve> firstGeneration = new ArrayList<>();
			firstGeneration.add(DragonCurve.of(x, y));
			firstGeneration.add(DragonCurve.of(x + direction[d][0], y + direction[d][1]));
			dragonCurveGenerations.add(firstGeneration);
			
			for (int j = 1; j <= g; j++) {
				List<DragonCurve> lastGeneration = dragonCurveGenerations.get(j - 1);
				DragonCurve lastGenerationEndPoint = lastGeneration.get(lastGeneration.size() - 1);
				int diffX = -lastGenerationEndPoint.x;
				int diffY = -lastGenerationEndPoint.y;
				
				Collections.reverse(lastGeneration);
				
				List<DragonCurve> newGeneration = lastGeneration.stream()
					.skip(1)
					.map(dragonCurve -> DragonCurve.adjust(dragonCurve, diffX, diffY))
					.map(DragonCurve::rotate)
					.map(dragonCurve -> DragonCurve.rollBack(dragonCurve, diffX, diffY))
					.collect(Collectors.toList());
				
				boolean isAvailable = true;
				for (DragonCurve newDragonCurve: newGeneration) {
					if (!(newDragonCurve.x >= 0 && newDragonCurve.y >= 0 && newDragonCurve.x < 101 && newDragonCurve.y < 101)) {
						isAvailable = false;
						break;
					}
				}
				
				if (isAvailable) {
					Collections.reverse(lastGeneration);
					lastGeneration.addAll(newGeneration);
					dragonCurveGenerations.add(lastGeneration);
				} else {
					break;
				}
			}
			
			for (DragonCurve dragonCurve : dragonCurveGenerations.get(g)) {
				if (dragonCurve.x >= 0 && dragonCurve.y >= 0 && dragonCurve.x < 101 && dragonCurve.y < 101) {
					map[dragonCurve.x][dragonCurve.y] = true;
				}
			}
		}
		
		int answer = 0;
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				int count = 0;
				
				for (int r = i; r <= i + 1; r++) {
					for (int c = j; c <= j + 1; c++) {
						if (map[r][c]) count++;
					}
				}
				
				if (count == 4) answer++;
			}
		}
		
		System.out.println(answer);

	}
	
	static class DragonCurve {
		int x;
		int y;
		
		static DragonCurve of (int x, int y) {
			DragonCurve dragonCurve = new DragonCurve();
			dragonCurve.x = x;
			dragonCurve.y = y;
			return dragonCurve;
		}
		
		static DragonCurve adjust(DragonCurve dragonCurve, int diffX, int diffY) {
			return DragonCurve.of(dragonCurve.x + diffX, dragonCurve.y + diffY);
		}
		
		static DragonCurve rollBack(DragonCurve dragonCurve, int diffX, int diffY) {
			return DragonCurve.of(dragonCurve.x - diffX, dragonCurve.y - diffY);
		}
		
		static DragonCurve rotate(DragonCurve adjust) {
			DragonCurve dragonCurve = new DragonCurve();
			dragonCurve.x = adjust.y * -1;
			dragonCurve.y = adjust.x;
			return dragonCurve;
		}
	}

}