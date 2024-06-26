package week28_Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_21609_G2_상어중학교_신문영 {
	final static int BLACK = -1;
	final static int RAINBOW = 0;
	final static int BLANK = -2;
	static int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int answer = 0;
		Block standard = null;
		
		while ((standard = findLargestBlockGroup(map)) != null) {
			answer += calculatePoint(map, standard);
			gravity(map);
			map = rotate(map);
			gravity(map);
		}
		
		System.out.println(answer);
	}
	
	static Block findLargestBlockGroup(int[][] map) {
		Block result = null;
		BlockGroup blockGroup = null;
		List<Block> rainbowList = null;

		int n = map.length;
		boolean[][] visited = new boolean[n][n];
		Queue<Block> queue = new ArrayDeque<>();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				int blocks = 0;
				int rainbowBlocks = 0;
				
				if (!visited[i][j] && map[i][j] != RAINBOW && map[i][j] != BLACK && map[i][j] != BLANK) {
					rainbowList = new ArrayList<>();
					
					int color = map[i][j];
					Block standard = Block.of(i, j, color);
					visited[i][j] = true;
					queue.add(standard);
					while(!queue.isEmpty()) {
						Block currentBlock = queue.poll();
						
						blocks++;
						
						if (map[currentBlock.i][currentBlock.j] == RAINBOW) {
							rainbowBlocks++;
						} else {
							standard = Block.order(currentBlock, standard);
						}
						
						for (int k = 0; k < direction.length; k++) {
							int nextI = currentBlock.i + direction[k][0];
							int nextJ = currentBlock.j + direction[k][1];
							
							if (!(nextI >= 0 && nextJ >= 0 && nextI < n && nextJ < n)) continue;
							if (visited[nextI][nextJ]) continue;
							
							if (map[nextI][nextJ] == color || map[nextI][nextJ] == RAINBOW) {
								if (map[nextI][nextJ] == RAINBOW) rainbowList.add(Block.of(nextI, nextJ));
								visited[nextI][nextJ] = true;
								queue.add(Block.of(nextI, nextJ));
							}
						}
					}
					
					if (blocks > 1) {
						if (blockGroup == null) {
							blockGroup = BlockGroup.of(blocks, rainbowBlocks, standard);
						}
						else {
							blockGroup = BlockGroup.max(blockGroup, BlockGroup.of(blocks, rainbowBlocks, standard));
						}
						
						result = blockGroup.standard;
					}
					
					for (Block rainbow : rainbowList) {
						visited[rainbow.i][rainbow.j] = false;
					}
				}
			}
		}
		
		return result;
	}
	
	static int calculatePoint(int[][] map, Block standard) {
		int result = 0;
		int color = standard.color;
		
		int n = map.length;
		boolean[][] visited = new boolean[n][n];
		Queue<Block> queue = new ArrayDeque<>();
		queue.add(standard);
		while (!queue.isEmpty()) {
			Block currentBlock = queue.poll();
			result++;
			map[currentBlock.i][currentBlock.j] = BLANK;
			
			for (int k = 0; k < direction.length; k++) {
				int nextI = currentBlock.i + direction[k][0];
				int nextJ = currentBlock.j + direction[k][1];
				
				if (!(nextI >= 0 && nextJ >= 0 && nextI < n && nextJ < n)) continue;
				if (visited[nextI][nextJ]) continue;
				
				if (map[nextI][nextJ] == color || map[nextI][nextJ] == RAINBOW) {
					visited[nextI][nextJ] = true;
					queue.add(Block.of(nextI, nextJ));
				}
			}
		}
		
		return result * result;
	}
	
	static void gravity(int[][] map) {
		int n = map.length;
		for (int j = 0; j < n; j++) {
			for (int i = n - 1; i >= 0; i--) {
				if (map[i][j] == BLACK || map[i][j] == BLANK) continue;
				
				int next = i + 1;
				while (next < n && map[next][j] == BLANK) {
					next++;
				}
				
				map[next - 1][j] = map[i][j];
				if (next != i + 1) map[i][j] = BLANK;
			}
		}
	}
	
	static int[][] rotate(int[][] map) {
		int n = map.length;
		int[][] rotated = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				rotated[i][j] = map[j][n - 1 - i];
			}
		}
		return rotated;
	}
	
	static class Block {
		int i;
		int j;
		int color = 0;
		
		static Block of (int i, int j) {
			Block block = new Block();
			block.i = i;
			block.j = j;
			return block;
		}
		
		static Block of (int i, int j, int color) {
			Block block = Block.of(i, j);
			block.color = color;
			return block;
		}
		
		static Block order(Block block1, Block block2) {
			if (Integer.compare(block1.i, block2.i) != 0) {
				if (Integer.compare(block1.i, block2.i) > 0) {
					return block2;
				} else {
					return block1;
				}
			} else {
				if (Integer.compare(block1.j, block2.j) > 0) {
					return block2;
				} else {
					return block1;
				}
			}
		}
	}
	
	static class BlockGroup {
		int blocks;
		int rainbowBlocks;
		Block standard;
		
		static BlockGroup of (int blocks, int rainbowBlocks, Block standard) {
			BlockGroup blockGroup = new BlockGroup();
			blockGroup.blocks = blocks;
			blockGroup.standard = standard;
			blockGroup.rainbowBlocks = rainbowBlocks;
			return blockGroup;
		}
		
		static BlockGroup max(BlockGroup blockGroup1, BlockGroup blockGroup2) {
			if (Integer.compare(blockGroup1.blocks, blockGroup2.blocks) != 0) {
				if (Integer.compare(blockGroup1.blocks, blockGroup2.blocks) > 0) {
					return blockGroup1;
				} else {
					return blockGroup2;
				}
			} else {
				if (Integer.compare(blockGroup1.rainbowBlocks, blockGroup2.rainbowBlocks) != 0) {
					if (Integer.compare(blockGroup1.rainbowBlocks, blockGroup2.rainbowBlocks) > 0) {
						return blockGroup1;
					} else {
						return blockGroup2;
					}
				} else {
					Block standard1 = blockGroup1.standard;
					Block standard2 = blockGroup2.standard;
					if (Integer.compare(standard1.i, standard2.i) != 0) {
						if (Integer.compare(standard1.i, standard2.i) > 0) {
							return blockGroup1;
						} else {
							return blockGroup2;
						}
					} else {
						if (Integer.compare(standard1.j, standard2.j) != 0) {
							if (Integer.compare(standard1.j, standard2.j) > 0) {
								return blockGroup1;
							} else {
								return blockGroup2;
							}
						}
					}
				}
			}
			return null;
		}
	}
}