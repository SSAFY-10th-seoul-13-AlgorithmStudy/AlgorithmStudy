package week22_Implementation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_13460_G1_구슬탈출2_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N, M;
	static int dr[] = {-1, 1, 0, 0};
	static int dc[] = {0, 0, -1, 1};
	static char[][] board;
	
	static void init() {
		board = new char[N][M];
	}
    
    static void input() throws IOException {
    	st = new StringTokenizer(br.readLine());
    	N = Integer.parseInt(st.nextToken());
    	M = Integer.parseInt(st.nextToken());
    	init();
    	
    	for(int i = 0; i < N; i++) {
    		String str = br.readLine();
    		for(int j = 0; j < M; j++) {
    			board[i][j] = str.charAt(j);
    		}
    	}
    }
    
    static void solve(){
    	int r[] = new int[2];
    	int b[] = new int[2];
    	
    	for(int i = 0; i < N; i++) {
    		for(int j = 0; j < M; j++) {
    			if(board[i][j] == 'R') {
    				r[0] = i;
    				r[1] = j;
    			} else if(board[i][j] == 'B') {
    				b[0] = i;
    				b[1] = j;
    			}
    		}
    	}
    	sb.append(bfs(r, b));
	}
    
    static int bfs(int[] r, int[] b) {    	
    	Queue<int[]> que = new ArrayDeque<>();
    	que.offer(new int[] {r[0], r[1], b[0], b[1]});
    	int cnt = 0;
    	boolean flag = false;
    	boolean visited[][][][] = new boolean[N][M][N][M];
    	
    	top:
    	while(!que.isEmpty() && cnt < 10) {
    		int size = que.size();
    		cnt++;
    		for(int i = 0; i < size; i++) {
	    		int[] cur = que.poll();
	    		int cur_r[] = new int[] {cur[0], cur[1]};
	    		int cur_b[] = new int[] {cur[2], cur[3]};	    		
	    		for(int d = 0; d < 4; d++) {
                    // 빨간 구슬과 파란 구슬을 굴림
                    int[] next_r = roll(cur_r, d);
                    int[] next_b = roll(cur_b, d);

                    // 파란 구슬 빠진 경우 continue;
                    if (board[next_b[0]][next_b[1]] == 'O') continue;                   
                    
                    if (board[next_r[0]][next_r[1]] == 'O') {
                    	flag = true;
                        break top;
                    }

                    // 빨간 구슬과 파란 구슬이 겹친 경우 이동 거리가 더 긴 구슬을 한 칸 뒤로 이동시킴 
                    if (next_r[0] == next_b[0] && next_r[1] == next_b[1]) {
                        int dist_r = Math.abs(cur_r[0] - next_r[0]) + Math.abs(cur_r[1] - next_r[1]);
                        int dist_b = Math.abs(cur_b[0] - next_b[0]) + Math.abs(cur_b[1] - next_b[1]);

                        if (dist_r > dist_b) {
                            next_r[0] -= dr[d];
                            next_r[1] -= dc[d];
                        } else {
                            next_b[0] -= dr[d];
                            next_b[1] -= dc[d];
                        }
                    }
                    if (!visited[next_r[0]][next_r[1]][next_b[0]][next_b[1]]) {
                        visited[next_r[0]][next_r[1]][next_b[0]][next_b[1]] = true;
                        que.offer(new int[]{next_r[0], next_r[1], next_b[0], next_b[1]});
                    }
	    		}
    		}
    	}
    	if(!flag) return -1;
    	else return cnt;
    }
    
    static int[] roll(int[] cur, int dir) {
        int row = cur[0];
        int col = cur[1];

        while (board[row + dr[dir]][col + dc[dir]] != '#' && board[row][col] != 'O') {
            row += dr[dir];
            col += dc[dir];
        }

        return new int[]{row, col};
    }
    
	
	public static void main(String[] args) throws IOException {		
		input();
		solve();

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}